package cn.mldn.dibmp.dao;

import java.util.List;

import cn.mldn.dibmp.vo.Citem;
/**
 * 子类接口
 * @author JDK
 *
 */
public interface CitemMapper {
   /**
    * 查找客户等级
    * @return
    */
   public List<Citem> findAll();
   
   /**
    * 查询客户等级
    * @param ciid
    * @return
    */
   public Citem findById(Long ciid);
}
