package org.mybatis.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.StatementType;
import org.mybatis.spring.support.DynamicSqlTemplate;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
public interface IBaseDao<T, PK> {

	/**
	 * 插入数据
	 * @param t
	 * @return
	 */
	@InsertProvider(type = DynamicSqlTemplate.class, method = "insert")
	int insert(T t);
	/**
	 * 插入数据并返回数据库生成id 目前只支持mysql
	 * @param t
	 * @return
	 */
	@InsertProvider(type = DynamicSqlTemplate.class, method = "insert")
	@SelectKey(statement={"select last_insert_id() as id"},keyProperty="id",before =false,
	resultType=Long.class,statementType = StatementType.STATEMENT)
	int insertWithId(T t);

	/**
	 * 根据id修改数据
	 * @param t
	 * @return
	 */
	@UpdateProvider(type = DynamicSqlTemplate.class, method = "update")
	int update(T t);
	/**
	 * 根据id获取数据
	 * @param id
	 * @return
	 */
	@SelectProvider(type = DynamicSqlTemplate.class, method = "getById")
	T getById(@Param("id") PK id);
	
	/**
	 * 动态查询单表数据
	 * @param params
	 * @return
	 */
	@SelectProvider(type = DynamicSqlTemplate.class, method = "get")
	List<T> get(Map<String, Object> params);
	
	/**
	 * 
	* @Description: 分页单表查询对应数据
	* @param params
	* @param pageNum
	* @param pageSize
	* @return   
	* List<T>   
	* @throws
	 */
	@SelectProvider(type = DynamicSqlTemplate.class, method = "getPage")
	List<T> getPage(Map<String, Object> params, @Param("pageNum")int pageNum, @Param("pageSize")int pageSize);
	/**
	 * 查询满足条件数据的总条数
	 * @param params
	 * @return
	 */
	@SelectProvider(type = DynamicSqlTemplate.class, method = "getPageTotalCount")
	int getPageTotalCount(Map<String, Object> params);
	
	/**
	 * 根据id删除数据
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = DynamicSqlTemplate.class, method = "deleteById")
	int deleteById(@Param("id") PK id);

	/**
	 * 根据id修改状态值
	 * @param id
	 * @param status
	 * @return
	 */
	@UpdateProvider(type = DynamicSqlTemplate.class, method = "updateStatusById")
	int updateStatusById(@Param("id") PK id, @Param("status") int status);
	/**
	 * 
	* @Description: 注解方式,不需要编写对应mapper，且只能是本dao单表
	* @param params
	* @param page
	* @return   
	* Pagination<UserInfo>   
	* @throws
	 */
	@SelectProvider(type = DynamicSqlTemplate.class, method = "getPagination")
	Pagination<T> getPagination(Map<String, Object> params, Page page,Sort... sorts);
	/**
	 * 
	* @Description: 编写对应mapper映射文件
	* @param params
	* @param page
	* @return   
	* Pagination<UserInfo>   
	* @throws
	 */
	<MT> Pagination<MT> getCustomPagination(Map<String, Object> params, Page page,Sort... sorts);

}