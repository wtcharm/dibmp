package cn.mldn.dibmp.service ;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dibmp.vo.Member;
public interface IMemberService {
	/**
	 * 根据用户ID获得一个用户的完整对象信息，包含有密码和锁定状态
	 * @param mid 要查询的用户ID
	 * @return 用户对象，如果用户不存在返回null
	 */
	public Member get(String mid) ; 
	/**
	 * 根据指定的用户编号获得其对应的所有授权信息（角色、权限）
	 * @param mid 用户ID
	 * @return 返回的信息里面包含有两类数据：
	 * 1、key = allRoles、value = 该用户具备的所有角色；
	 * 2、key = allActions、value = 该用户具备的所有权限
	 */
	public Map<String,Set<String>> getRoleAndActionByMember(String mid) ;
	/**
	 * 根据部门编号查找所有雇员信息，并完成分页
	 * @param did 部门编号
	 * @param current 当前页数
	 * @param lineSize 显示行数
	 * @return map
	 */
	public Map<String,Object> getByDid(Long did,Long current,Integer lineSize) ;
	/**
	 * 根据指定的用户编号获得其对应的所有信息（部门、职位）
	 * @param name 用户姓名
	 * @return 返回信息里面包含了 三类数据
	 * 1.key = allMember value =该用户的信息
	 * 2.key = allDept  value = 该用户的部门
	 * 3.key = allLeve  value = 该用户的职位
	 */
	public Map<String,Object> getDeptAndLevelByMember(String name);
}
