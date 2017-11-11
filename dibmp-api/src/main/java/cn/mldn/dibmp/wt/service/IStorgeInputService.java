package cn.mldn.dibmp.wt.service;
/**
 * 负责实现商品入库处理
 * @author Administrator
 *
 */

import java.util.Map;

public interface IStorgeInputService {

	/**
	 * 根据said 查询当前编号对应的仓库的信息
	 * @param said 仓库编号
	 * @return
	 */
	public Map<String, Object> listInputBcke(Long said,String status);
	/**
	 * 根据 仓库编号（said） 判断仓库是都存在
	 * @param said  仓库编号
	 * @return 存在返回true的否则返回 false
	 */
	public boolean isSaidVo(Long said,String status);
}
