package com.yimidida.ows.home.server.util;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 *
 * 类名称：MailUtil
 * 类描述：发送邮件
 * 创建人：yanglong
 * 修改人：yanglong
 * 修改时间：2015年3月9日 上午11:48:07
 * 修改备注：
 * @version 1.0.0
 *
 */
public class MailUtil {

    public boolean sendMail(String mail,String content) throws MessagingException, UnsupportedEncodingException  {
    	// 配置发送邮件的环境属性
        final Properties props = new Properties();
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.163.com");
        // 发件人的账号
        props.put("mail.user", "15623160858@163.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "yimidida027"); // yimidida027是登录密码，需要客户端授权码，问马金会要
        //设置发件人名称
        props.put("mail.sendName", "大道物流");
        
        
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            	// 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"),props.getProperty("mail.sendName"));
        message.setFrom(form);

        // 设置收件人
        InternetAddress to = new InternetAddress(mail);
        message.setRecipient(RecipientType.TO, to);

        // 设置抄送
//        InternetAddress cc = new InternetAddress("520124470@qq.com");
//        message.setRecipient(RecipientType.CC, cc);
//
//        // 设置密送，其他的收件人不能看到密送的邮件地址
//        InternetAddress bcc = new InternetAddress("520124470@qq.com");
//        message.setRecipient(RecipientType.CC, bcc);

        // 设置邮件标题
//        message.setSubject(Constant.MAIL_TITLE);//要解注
        message.setSubject("找回密码");

        // 设置邮件内容
        message.setContent(content, "text/html;charset=UTF-8");

        // 发送邮件
        try {
			Transport.send(message);
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
    }
    
}