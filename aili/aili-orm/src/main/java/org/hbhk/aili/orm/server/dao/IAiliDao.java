package org.hbhk.aili.orm.server.dao;

import java.io.Serializable;
import java.util.List;

import org.hbhk.aili.orm.server.surpport.DefaultPage;

public interface IAiliDao <T,PK extends Serializable> {
    /**
    *  按ID获取实体
    * @param id
    * @return 
    */
   public T get(PK id);
   /**
    * 按ID删除实体
    * @param id 
    */
   public void remove(PK id);
   /**
    * 按ID创建实体
    * @param entity 
    */
   public void create(T entity);
   /**
    * 更新实体
    * @param entity 
    */
   public void update(T entity);
  
   /**
    * 查询所有实体列表
    * @return 
    */
   public List<T> getAll();
   
   /**
    * 按分页查询所有实体
    * @param page
    * @return 
    */
   public List<T> getAllByPage(DefaultPage page);

}
