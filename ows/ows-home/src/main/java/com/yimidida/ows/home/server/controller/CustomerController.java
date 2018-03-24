package com.yimidida.ows.home.server.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jetbrick.template.utils.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.params.HttpParams;
import org.mybatis.spring.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yimidida.ows.base.server.annotation.QueryPage;
import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.util.MD5Util;
import com.yimidida.ows.base.share.util.SystemParameterUtil;
import com.yimidida.ows.base.share.vo.QueryPageVo;
import com.yimidida.ows.common.util.TimeUtil;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.home.server.service.IAreaService;
import com.yimidida.ows.home.server.service.ICityService;
import com.yimidida.ows.home.server.service.IDepartmentService;
import com.yimidida.ows.home.server.service.ILeagueService;
import com.yimidida.ows.home.server.service.IOrderInfoService;
import com.yimidida.ows.home.server.service.IOwsUserService;
import com.yimidida.ows.home.server.service.IProvinceService;
import com.yimidida.ows.home.server.service.IUserPeopleService;
import com.yimidida.ows.home.server.util.HttpUtil;
import com.yimidida.ows.home.server.util.PinyinUtil;
import com.yimidida.ows.home.server.util.SendPhoneMsg;
import com.yimidida.ows.home.share.entity.Area;
import com.yimidida.ows.home.share.entity.City;
import com.yimidida.ows.home.share.entity.CollectionPojo;
import com.yimidida.ows.home.share.entity.Department;
import com.yimidida.ows.home.share.entity.League;
import com.yimidida.ows.home.share.entity.OrderInfo;
import com.yimidida.ows.home.share.entity.OwsUser;
import com.yimidida.ows.home.share.entity.Province;
import com.yimidida.ows.home.share.entity.UserPeople;
import com.yimidida.ows.home.share.vo.COD;
import com.yimidida.ows.home.share.vo.CODBack;
import com.yimidida.ows.home.share.vo.ReqNewSysWayBill;
import com.yimidida.ows.home.share.vo.ResOldWaybillRouting;
import com.yimidida.ows.home.share.vo.ResWayBillRouting;
import com.yimidida.ows.home.share.vo.ReturnFreight;
import com.yimidida.ows.home.share.vo.ReturnFreightCity;
import com.yimidida.ows.home.share.vo.TypeaheadData;
import com.yimidida.ymdp.core.share.entity.ResultEntity;
import com.yimidida.ymdp.core.share.ex.BusinessException;
import com.yimidida.ymdp.core.share.util.JsonUtil;

/**
 * 客户中心(个人中心)
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends AbstractController {
  @Autowired
  IAreaService areaService;// -区
  @Autowired
  ICityService cityService;// -市
  @Autowired
  IProvinceService provinceService;// -省
  @Autowired
  ILeagueService leagueService;// -网点
  @Autowired
  IOwsUserService owsUserService;// 个人信息
  @Autowired
  IDepartmentService departmentService;// 网点 与大道系统同步

  @Autowired
  IUserPeopleService userPeopleService;
  @Autowired
  IOrderInfoService orderInfoService;

  @RequestMapping("getcount")
  @ResponseBody
  public Integer getCount(HttpSession session) {
    Integer count = (Integer) session.getAttribute("waybillcount") == null ? 0
        : (Integer) session.getAttribute("waybillcount");
    return count;
  }

  @RequestMapping("getwaybill")
  @ResponseBody
  public void getwaybill(HttpSession session, HttpServletResponse response, String orderNums)
      throws IOException {
    String json = "";

    if (orderNums == null || org.springframework.util.StringUtils.isEmpty(orderNums)) {
      json = "{'success':false,'data':'请输入运单号'}";
    }

    String[] wayBillNos = orderNums.split("%");
    if (wayBillNos == null) {
      json = "{'success':false,'data':'请输入运单号'}";
    } else if (wayBillNos.length > 10) {
      json = "{'success':false,'data':'每次最多查询10条运单'}";
    }

    if (json != null && json.length() > 0) {
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = null;
      out = response.getWriter();
      out.print(json);
      out.flush();
    } else {
      List<ResWayBillRouting> res = new ArrayList<ResWayBillRouting>();
      JSONObject jsonObject = null;
      String newSysRouting = requestNewSystemRouting(wayBillNos);
      if (newSysRouting != null && newSysRouting.trim().length() > 0) {
        jsonObject = JSONObject.parseObject(newSysRouting);
        if (jsonObject.containsKey("success") && jsonObject.getBooleanValue("success")
            && jsonObject.containsKey("data")) {
          List<ResWayBillRouting> routings =
              JSON.parseArray(jsonObject.getString("data"), ResWayBillRouting.class);
          if (routings != null && !routings.isEmpty()) {
            try {
              for (ResWayBillRouting vo : routings) {
                if (vo.getTrackList() != null && !vo.getTrackList().isEmpty()) {
                  for (ResWayBillRouting.WaybillTrackVo trackVo : vo.getTrackList()) {
                    if (!StringUtils.isEmpty(trackVo.getOperateTime())) {
                      String time = trackVo.getOperateTime().replace("T", " ").replace("Z", "");
                      // trackVo.setOperateTime(TimeUtil.formatTimeEight(time));
                      trackVo.setOperateTime(time);
                    }
                  }
                }
              }
            } catch (Exception e) {

            }
            res.addAll(routings);
          }

        }
      }

      List<String> unExistNewSysWaybillNos = new ArrayList<String>();
      for (String orderNum : wayBillNos) {
        boolean unExist = true;// 默认不存在
        for (ResWayBillRouting routing : res) {
          if (orderNum.equals(routing.getWaybillNo())) {
            unExist = !unExist;
            break;
          }
        }
        if (unExist) {
          unExistNewSysWaybillNos.add(orderNum);
        }
      }

      for (String waybillNo : unExistNewSysWaybillNos) {
        String oldSysRouting = requestOldSystemRouting(waybillNo);
        if (oldSysRouting != null && oldSysRouting.length() > 0) {
          jsonObject = JSONObject.parseObject(oldSysRouting);
          if (jsonObject.containsKey("success") && jsonObject.getBooleanValue("success")
              && jsonObject.containsKey("routeList")) {
            List<ResOldWaybillRouting> waybillRoutings =
                JSON.parseArray(jsonObject.getString("routeList"), ResOldWaybillRouting.class);
            if (waybillRoutings != null && !waybillRoutings.isEmpty()) {
              ResWayBillRouting routing = new ResWayBillRouting();
              routing.setWaybillNo(waybillNo);
              for (ResOldWaybillRouting resOldWaybillRouting : waybillRoutings) {
                if (resOldWaybillRouting.getBarList() != null
                    && !resOldWaybillRouting.getBarList().isEmpty()) {
                  for (ResOldWaybillRouting.Routing entity : resOldWaybillRouting.getBarList()) {
                    ResWayBillRouting.WaybillTrackVo vo = new ResWayBillRouting.WaybillTrackVo();
                    vo.setOperateTime(entity.getOperTm());
                    vo.setTrackInfo(entity.getContentValue());
                    routing.getTrackList().add(vo);
                  }
                  res.add(routing);
                }
              }
            }
          }
        }
      }

      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = null;
      out = response.getWriter();
      StringBuffer jsonResult = new StringBuffer("{\"success\":true, \"data\":");
      jsonResult.append(JSON.toJSONString(res));
      jsonResult.append("}");
      out.print(jsonResult.toString());
      System.out.println(jsonResult.toString());
      out.flush();

    }

  }


  /**
   * 请求新系统运单路由信息
   * 
   * @param waybillNos 运单号数组
   * @param trackScene 请求模板
   * @return
   * @throws MalformedURLException
   * @throws IOException
   */
  private String requestNewSystemRouting(String[] waybillNos)
      throws MalformedURLException, IOException {
    ReqNewSysWayBill waybillNo =
        new ReqNewSysWayBill(waybillNos, SystemParameterUtil.getValue("trackScene"));
    System.out.println(JSON.toJSONString(waybillNo));
    StringBuilder json = new StringBuilder();

    URL url = new URL(SystemParameterUtil.getValue("interface.yh")
        + "/galaxy-tms-business/waybillTrack/queryWaybillTracks");
    // URL url = new
    // URL("https://galaxy.yimidida.com/galaxy-tms-business/waybillTrack/queryWaybillTracks");
    System.out.println(url.toString());
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    connection.setDoInput(true);

    connection.setConnectTimeout(30000);// 连接超时 单位毫秒
    connection.setReadTimeout(2000);// 读取超时 单位毫秒
    connection.setUseCaches(false);
    connection.setInstanceFollowRedirects(true);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.connect();
    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
    out.writeBytes(JSON.toJSONString(waybillNo));
    out.flush();
    out.close();
    if (connection.getResponseCode() == 200) {
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String lines;
      while ((lines = reader.readLine()) != null) {
        json.append(lines);
      }
    }

    System.out.println(json);
    return json.toString();
  }

  /**
   * 获取老系统运单路由
   * 
   * @param waybillNo
   * @return
   * @throws MalformedURLException
   * @throws IOException
   */
  private String requestOldSystemRouting(String waybillNo)
      throws MalformedURLException, IOException {
    StringBuilder json = new StringBuilder();
    System.out.println(waybillNo);
    // 103.36.133.126-->对应内网地址10.9.1.122
    URL url = new URL(SystemParameterUtil.getValue("interface.ymdd")
        + "/query/queryWaybillForUnion.action?waybillNos=" + waybillNo);
    // URL url = new URL("http://10.10.0.6:9080/yimi/query/queryWaybillForUnion.action?waybillNos="
    // + waybillNo);
    System.out.println(url.toString());
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.connect();
    if (connection.getResponseCode() == 200) {
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
      String lines;
      while ((lines = reader.readLine()) != null) {
        json.append(lines);
      }
    }

    System.out.println(json.toString());
    return json.toString();
  }



  // 需要验证码
  // @RequestMapping("getwaybill")
  // @ResponseBody
  // public void getwaybill(HttpSession session, HttpServletResponse response, String orderNum,
  // String yzm)
  // throws IOException {
  // Integer count = (Integer) session.getAttribute("waybillcount") == null ? 0
  // : (Integer) session.getAttribute("waybillcount");
  // session.setAttribute("waybillcount", count + 1);
  // String resultStr = "";
  // if (count > 100) {
  // String sysyzm = (String) session.getAttribute("homeCode");
  // if (!sysyzm.toUpperCase().equals(yzm.toUpperCase())) {
  // resultStr = "{'success':false}";
  // } else {
  // try {
  // URL url = new URL(
  // "http://ddwl.yimidida.com:9082/yimi/query/queryWaybillForUnion.action?waybillNos="
  // //SystemParameterUtil.getValue("interface.ymdd")+"/query/queryWaybillForUnion.action?waybillNos="
  // + orderNum);
  // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  // connection.connect();
  // BufferedReader reader = new BufferedReader(
  // new InputStreamReader(connection.getInputStream(), "utf-8"));
  //
  // String lines;
  //
  // while ((lines = reader.readLine()) != null) {
  // resultStr += lines;
  // }
  // } catch (MalformedURLException e) {
  // e.printStackTrace();
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // }
  // } else {
  // try {
  // URL url = new URL(
  // "http://ddwl.yimidida.com:9082/yimi/query/queryWaybillForUnion.action?waybillNos="
  // //SystemParameterUtil.getValue("interface.ymdd")+"/query/queryWaybillForUnion.action?waybillNos="
  // + orderNum);
  // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  // connection.connect();
  // BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),
  // "utf-8"));
  //
  // String lines;
  //
  // while ((lines = reader.readLine()) != null) {
  // resultStr += lines;
  // }
  // } catch (MalformedURLException e) {
  // e.printStackTrace();
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // }
  //
  // response.setContentType("text/html; charset=UTF-8");
  // PrintWriter out = null;
  // session.setAttribute("traceOrderNo", orderNum);
  // out = response.getWriter();
  //
  // out.print(resultStr);
  // out.flush();
  // }

  public static void main(String args[]) {
    String resultStr = "";
    try {
      URL url = new URL(
          "http://client2.yimidida.com:9080/yimi/query/queryWaybillForUnion.action?waybillNos=024039740103");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.connect();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));

      String lines;

      while ((lines = reader.readLine()) != null) {
        resultStr += lines;
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(resultStr);
  }

  // 订单查询验证码
  @RequestMapping("/homecertpic")
  public String mailcertpic(Model model, String type, String title) {

    return "createCode/homecertpic";
  }

  // 获取省
  @RequestMapping("/getProvice")
  @ResponseBody
  public List<Province> getProvice() {
    List<Province> list = provinceService.get(null);
    return list;
  }

  // 获取市
  @RequestMapping("/getCity")
  @ResponseBody
  public List<City> getCity(String provinceCode) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotEmpty(provinceCode)) {
      params.put("provinceCode", provinceCode);
    }
    List<City> list = cityService.get(params);
    return list;
  }

  // 获取市
  @RequestMapping("/getArea")
  @ResponseBody
  public List<Area> getArea(String cityCode) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotEmpty(cityCode)) {
      params.put("cityCode", cityCode);
    }
    List<Area> list = areaService.get(params);
    return list;
  }

  // 获取网点
  @RequestMapping("/getLeague")
  @ResponseBody
  public ResultEntity getLeague(String province, String city, String deptName, String district) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotEmpty(district)) {
      params.put("district", district);
    }
    if (StringUtils.isNotEmpty(province)) {
      params.put("province", province);
    }
    if (StringUtils.isNotEmpty(city)) {
      params.put("city", city);
    }
    if (StringUtils.isNotEmpty(deptName)) {
      params.put("name", deptName);
    }
    List<League> list = leagueService.get(params);
    return returnSuccess(list);
  }

  // 获取网点
  @RequestMapping("/getLeaguePage")
  @ResponseBody
  public Pagination<League> getLeaguePage(@QueryPage QueryPageVo queryPageVo) {

    Pagination<League> page =
        leagueService.getLeague(queryPageVo.getParaMap(), queryPageVo.getPage());
    return page;
  }

  // 删除网点
  @RequestMapping("/deleteDept")
  @ResponseBody
  public ResultEntity deleteDept(String id) {
    int flag = leagueService.deleteById(id);
    if (flag == 1) {
      return returnSuccess();
    } else {
      return returnException();
    }

  }

  // 根据id获取网点
  @RequestMapping("/getLeagueById")
  @ResponseBody
  public ResultEntity getLeagueById(String id) {
    League league = leagueService.getById(id);
    if (league != null) {
      return returnSuccess(league);
    } else {
      return returnException();
    }
  }

  // 根据id获取网点
  @RequestMapping("/addOrUpdateLeague")
  @ResponseBody
  public ResultEntity addOrUpdateLeague(League league) {
    int flag = 0;
    if (StringUtils.isNotEmpty(league.getId())) {
      league.setChangeDate(new Date());
      league.setChangeUser(UserContext.getCurrentUser().getUserName());
      flag = leagueService.update(league);
    } else {
      league.setId(UuidUtil.getUuid());
      league.setCreateDate(new Date());
      league.setCreateUser(UserContext.getCurrentUser().getUserName());
      league.setChangeDate(new Date());
      league.setChangeUser(UserContext.getCurrentUser().getUserName());
      flag = leagueService.insert(league);
    }
    if (flag == 1) {
      return returnSuccess();
    } else {
      return returnException();
    }

  }



  /**
   * 修改用户密码
   * 
   * @param id
   * @param oldPassword
   * @param newPassword
   * @param rePassword
   * @return
   */
  @RequestMapping("/changePassword")
  @ResponseBody
  public Object changePassword(HttpSession session, String oldPassWord, String newPassWord,
      String rePassWord) {
    OwsUser user = (OwsUser) session.getAttribute("user");// 获取当前的登录用户
    Map<String, Object> map = new HashMap<String, Object>();
    String encode = MD5Util.encode(oldPassWord);// 用户输入的密码
    String userEncode = user.getPassword();// 获取用户原始密码
    if (StringUtils.isEmpty(user.getId())) {
      map.put("msg", "账号未登录！请先登录账号！");
      return map;
    }
    if (userEncode.equals(encode)) {// 旧密码输入正确
      // 在进行判断新密码和重复密码是否相同
      if (newPassWord.equals(rePassWord)) {
        // 修改密码成功
        owsUserService.changePassWord(user.getId(), MD5Util.encode(newPassWord));
        map.put("msg", "修改密码成功！");
      } else {
        // 新密码和重复密码填写不一致
        map.put("msg", "新密码和重复密码填写不一致!");
      }
    } else {
      // 告知用户旧密码填写错误
      map.put("msg", "原密码填写错误！");
    }
    return map;
  }

  @RequestMapping("/accordingUserDetails")
  @ResponseBody
  public ResultEntity accordingUserDetails(HttpSession session) {
    OwsUser user = (OwsUser) session.getAttribute("user");// 获取当前的登录用户
    OwsUser owsUser = owsUserService.getById(user.getId());
    return returnSuccess(owsUser);
  }

  /**
   * 保存个人详细信息
   * 
   * @param session
   * @param owsUser
   * @return
   */
  @RequestMapping("/savePersonalDetails")
  @ResponseBody
  public ResultEntity savePersonalDetails(HttpSession session, OwsUser o) {
    int flag = 0;
    Date now = new Date();
    Map<String, Object> map = new HashMap<String, Object>();
    OwsUser user = (OwsUser) session.getAttribute("user");// 获取当前的登录用户
    if (StringUtils.isEmpty(user.getId())) {// 判断用户id是否为空
      map.put("msg", "请先登录！");
      return returnSuccess(map);
    } else {
      o.setUpdateTime(now);
      o.setId(user.getId());
      flag = owsUserService.updateDetailsByuserId(o);
      map.put("msg", "保存个人信息成功!");
      return returnSuccess(map);
    }
  }

  /**
   * 获取旧邮箱
   * 
   * @param session
   * @return
   */
  @RequestMapping("/accordingOldEmail")
  @ResponseBody
  public ResultEntity accordingOldEmail(HttpSession session) {
    OwsUser user = (OwsUser) session.getAttribute("user");// 获取当前的登录用户
    Map<String, Object> map = new HashMap<String, Object>();
    if (StringUtils.isEmpty(user.getId())) {// 判断用户id是否为空
      map.put("msg", "请先登录！");
      return returnSuccess(map);
    }
    OwsUser o = owsUserService.getById(user.getId());
    return returnSuccess(o);
  }

  /**
   * 修改用户邮箱
   * 
   * @param session
   * @param oldEmail
   * @param newEmail
   * @return
   */
  @RequestMapping("/changeEmail")
  @ResponseBody
  public ResultEntity changeEmail(HttpSession session, String newEmail) {
    OwsUser user = (OwsUser) session.getAttribute("user");// 获取当前的登录用户
    Map<String, Object> map = new HashMap<String, Object>();
    String regex =
        "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";// 邮箱的校验格式
    Pattern p = Pattern.compile(regex);// 正则表达式
    Matcher m = p.matcher(newEmail);// 操作的字符串
    if (!m.matches()) {// 判断邮箱格式是否符合标准
      map.put("msg", "邮箱格式不正确!");
      return returnSuccess(map);// 记性执行下面的代码
    }
    if (StringUtils.isEmpty(user.getId())) {// 帐号为空-未登录
      map.put("msg", "请先去登录把！");
      return returnSuccess(map);
    }
    if (user.getEmail() == newEmail) {
      // 提示邮箱相同
      map.put("msg", "新邮箱和旧邮箱重复啦！");
    } else {
      // 修改成功
      user.setEmail(newEmail);
      owsUserService.changeEmail(user);
      map.put("msg", "新邮箱设置完成！");
    }
    return returnSuccess(map);
  }

  // 注册短信
  @RequestMapping("/getMobileCode")
  @ResponseBody
  public Object getMobileCode(String newMobile, HttpSession session, String phonecode) {
    if (newMobile == null) {
      return 0;// 无号码
    }
    // String sMobileCode =
    // String.valueOf(session.getAttribute("mobileMsgCode"));//获取客户端发送过来的短信转换成String

    String code = getCode().toString();
    String content = "修改手机号绑定验证码:" + code + ",有效时间为30分钟,如不是您本人操作请忽略此短信,谢谢!";// 发送的内容
    if (phonecode != null) {
      if (!phonecode.equals(session.getAttribute("phonecode"))) {
        return 3;
      }
    } else {
      return 3;
    }
    if (SendPhoneMsg.sendPhoneCode(newMobile, content)) {
      // 返回00则发送成功
      session.setAttribute(newMobile, code);
      System.out.println("注册验证码：" + code);
      return 1;// 成功
    } else {
      return 2;// 失败
    }

  }

  // 生成6位验证码
  private Integer getCode() {
    return (int) ((Math.random() * 9 + 1) * 100000);
  }

  /**
   * 校验登录密码,绑定新手机号码,获取手机验证码,校验手机发送的验证码,进行手机号码的修改
   * 
   * @param phone
   * @param password
   * @param session
   * @param mobileMsgCode
   * @return
   */
  @RequestMapping("/updateNewPhoneByCode")
  @ResponseBody
  public ResultEntity updateNewPhoneByCode(String newMobile, String password, String mobileMsgCode,
      HttpSession session) {
    OwsUser user = (OwsUser) session.getAttribute("user");// 获取当前的登录用户
    String mobileSessionCode = "";// 定义手机接收到的验证码
    if (session.getAttribute(newMobile) != null) { // 判断是否发送了验证码给手机
      mobileSessionCode = session.getAttribute(newMobile).toString(); // 获取到发给手机端的验证码
    }
    String rPasword = MD5Util.encode(password);
    Map<String, Object> map = new HashMap<String, Object>();
    if (StringUtils.isEmpty(user.getId())) {// 判断用户是否为空
      map.put("msg", "用户名不能为空");
      return returnSuccess(map);
    }
    if (!user.getPassword().equals(rPasword)) {// 判断密码是否正确
      map.put("msg", "密码错误！请重新输入！");
      return returnSuccess(map);
    }
    if (StringUtils.isEmpty(newMobile)) {// 判断手机号是否为空
      map.put("msg", "手机号码不能为空!");
      return returnSuccess(map);
    }
    if (!mobileMsgCode.equalsIgnoreCase(mobileSessionCode)) {// 进行手机验证码的校验！
      map.put("msg", "验证码不正确,请重新输入!");
      return returnSuccess(map);
    }
    user.setMobile(newMobile);
    owsUserService.updatePhoneById(user);
    map.put("msg", "手机号码更新成功！");
    return returnSuccess(map);
  }

  /**
   * 进行会员卡的添加-需要添加身份证、会员卡卡号、初始手机号、真实姓名
   * 
   * @param session
   * @param memberCard
   * @param userName
   * @param IDCard
   * @param initialPhone
   * @return
   */
  @RequestMapping("updateMemberCardById")
  @ResponseBody
  public ResultEntity updateMemberCardById(HttpSession session, String memberCard, String userName,
      String IDCard, String initialPhone) {
    OwsUser user = (OwsUser) session.getAttribute("user");// 获取当前的登录用户
    Map<String, Object> map = new HashMap<String, Object>();
    if (StringUtils.isEmpty(user.getId())) {// 判断用户是否为空
      map.put("msg", "用户名不能为空");
      return returnSuccess(map);
    }
    if (StringUtils.isEmpty(memberCard)) {// 会员卡号不能为空
      map.put("msg", "会员卡号不能为空");
      return returnSuccess(map);
    }
    String realName = user.getRealName();
    if (!realName.equals(userName)) {// 真实姓名不能为空且等于对应字段数值 true
      map.put("msg", "真实姓名填写错误!");
      return returnSuccess(map);
    }
    if (StringUtils.isEmpty(IDCard)) {// 身份证不能为空
      map.put("msg", "身份证号码不能为空!");
      return returnSuccess(map);
    }

    /*
     * if(initialPhone==null&&user.getInitialPhone().equals(initialPhone)){ //初始手机号不能为空且必须与数据库中相互对应
     * map.put("msg", "初始手机号填写错误!"); returnSuccess(map); }
     */
    user.setMemberCard(memberCard);// 会员卡号
    user.setIDCard(IDCard);// 身份证号
    user.setInitialPhone(initialPhone);// 后期需要修改的代码！因为这个初始手机号码和公司业务有关！暂时先进行绑定吧！
    owsUserService.updateMemberCardById(user);
    map.put("msg", "添加会员卡成功!");
    return returnSuccess(map);
  }

  @RequestMapping("/getSearchSourceCity")
  @ResponseBody
  public ResultEntity getSearchSourceCity(HttpSession session, String newEmail) {
    String resultStr = "";
//    resultStr = HttpUtil.sendPost(
//        SystemParameterUtil.getValue("interface.weixin") + "/ddwl/getSearchSourceCity", null,
//        String.class);
//    ReturnFreightCity citys = JsonUtil.parseJson(resultStr, ReturnFreightCity.class);
//    Map<String, Object> map = new HashMap<String, Object>();
//    if (citys.isSuccess()) {
//      map.put("list", citys.getData());
//    }
    return returnSuccess();
  }
  // 运价查询获取目的城市输入框城市查询输入框

  @RequestMapping("/getSearchDestCity")
  @ResponseBody
  public ResultEntity getSearchDestCity(HttpSession session, String newEmail) {
    String resultStr = "";
    resultStr = HttpUtil.sendPost(
        SystemParameterUtil.getValue("interface.weixin") + "/ddwl/getSearchDestCity", null,
        String.class);
    ReturnFreightCity citys = JsonUtil.parseJson(resultStr, ReturnFreightCity.class);
    Map<String, Object> map = new HashMap<String, Object>();
    if (citys.isSuccess()) {
      map.put("list", citys.getData());
    }
    return returnSuccess(map);
  }

  // 获取运费信息
  @RequestMapping("/getFreight")
  @ResponseBody
  public ResultEntity getFreight(HttpSession session, String sourceDistCode, String destDistCode) {
    String resultStr = "";
    MultiValueMap<String, Object> mulMap = new LinkedMultiValueMap<String, Object>();
    mulMap.add("sourceDistCode", sourceDistCode);
    mulMap.add("destDistCode", destDistCode);
    resultStr =
        HttpUtil.sendPost(SystemParameterUtil.getValue("interface.weixin") + "/ddwl/getFreight",
            mulMap, String.class);
    ReturnFreight citys = JsonUtil.parseJson(resultStr, ReturnFreight.class);
    Map<String, Object> map = new HashMap<String, Object>();
    if (citys.isSuccess()) {
      if (citys.getData() == null) {
        throw new BusinessException("参数不正确，请重试！");
      }
    } else {
      throw new BusinessException("系统错误");
    }
    return returnSuccess(citys.getData());
  }

  @RequestMapping("/getDepartMent")
  @ResponseBody
  public ResultEntity getDepartMent() {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("typeCode", "DT06");
    List<Department> list = departmentService.get(params);
    List<TypeaheadData> datas = new ArrayList<TypeaheadData>();
    for (Department department : list) {
      TypeaheadData t = new TypeaheadData();
      t.setId(department.getDeptCode());
      String deptName =
          PinyinUtil.converterToFirstSpell(department.getDeptName().substring(0, 1)).toUpperCase()
              + " - " + department.getDeptName();
      t.setName(deptName);
      datas.add(t);
    }
    return returnSuccess(datas);
  }

  // 代收货款查询
  @RequestMapping("/getCOD")
  @ResponseBody
  public ResultEntity getCOD(String waybillNo) {

    StringBuffer buffer = new StringBuffer(SystemParameterUtil.getValue("interface.yh"));
    buffer.append("/galaxy-finance-business/sys/cop/sendMoneyApi/queryTranCop?");
    buffer.append("waybillNo=").append(waybillNo);
    StringBuffer resultBuffer = new StringBuffer();
    try {
      URL url = new URL(buffer.toString());
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.connect();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
      String lines;
      while ((lines = reader.readLine()) != null) {
        resultBuffer.append(lines);
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    CollectionPojo pojo = null;
    if (StringUtils.isNotEmpty(resultBuffer)) {
      pojo = JsonUtil.parseJson(resultBuffer.toString(), CollectionPojo.class);
    }

    List<COD> cods = null;
    if (pojo != null && pojo.getData() != null && !pojo.getData().isEmpty()) {
      cods = new ArrayList<>();
      for (CollectionPojo.Data data : pojo.getData()) {
        COD cod = new COD();
        // cod.setConsName(data.getConsignorContName());
        cod.setConsName("");
        cod.setOutFeeAmt(data.getRealPayFee() == null ? "" : data.getRealPayFee().toString());
        cod.setPaymentState(data.getPaymentType());
        cod.setPaymentTm(StringUtils.isEmpty(data.getPaymentTm()) ? ""
            : data.getPaymentTm().replace("T", " ").replace("Z", ""));
        cod.setWaybillNo(data.getWaybillNo());
        cods.add(cod);
      }
    }


    String param = "{waybillNo:" + waybillNo + "}";
    MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
    params.add("jsonData", param);
    String resultStr = HttpUtil.sendPost(
        SystemParameterUtil.getValue("interface.ymdd") + "/pda/orderInfoSF02List.action", params,
        String.class);
    System.out.println(resultStr);
    CODBack back = JsonUtil.parseJson(resultStr, CODBack.class);

    if (back == null && cods != null) {
      back = new CODBack();
      back.setSuccess(true);
    }

    if (back != null && cods != null && !back.isSuccess()) {
      back.setSuccess(true);
    }

    if (cods != null) {
      if (back.getDataList() == null) {
        back.setDataList(new ArrayList<COD>());
      }
      back.getDataList().addAll(0, cods);

    }


    return returnSuccess(back);
  }

  // 代收货款查询
  @RequestMapping("/getUserInfo")
  @ResponseBody
  public ResultEntity getUserInfo(HttpSession session) {
    OwsUser user = (OwsUser) session.getAttribute("user");
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("userId", user.getId());// 获取当前用户的id
    List<UserPeople> list = userPeopleService.getByType(param);
    Map<String, Object> orderParam = new HashMap<String, Object>();
    orderParam.put("userId", user.getId());
    List<OrderInfo> orders = orderInfoService.get(orderParam);
    Integer hisOrder = orders.size();
    Integer toOrder = 0;
    Integer sendPeople = 0;
    Integer arrivePeople = 0;
    for (UserPeople userPeople : list) {
      if ("0".equals(userPeople.getUserType())) {
        sendPeople++;
      } else if ("1".equals(userPeople.getUserType())) {
        arrivePeople++;
      }
    }
    Date tody = new Date();
    for (OrderInfo orderInfo : orders) {
      if (DateUtils.isSameDay(tody, orderInfo.getCreateTm())) {
        toOrder++;
      }
    }
    List<Integer> returnList = new ArrayList<Integer>();
    returnList.add(hisOrder);
    returnList.add(toOrder);
    returnList.add(sendPeople);
    returnList.add(arrivePeople);
    return returnSuccess(returnList);
  }

}
