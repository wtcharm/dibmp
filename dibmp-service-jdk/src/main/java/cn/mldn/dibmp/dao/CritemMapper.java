package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Critem;
/**
 * 子类接口
 * @author JDK
 *
 */
public interface CritemMapper {
   /**
    * 查找所有的业务类型
    * @return
    */
   public List<Critem> findAll();
   /**
    * 追加客户业务类型
    * @return
    */
   public boolean doCreateCritem();
   /**
    * 根据业务分类id，取得title
    * @param criid
    * @return
    */
   public Critem findById(Long criid);
}
