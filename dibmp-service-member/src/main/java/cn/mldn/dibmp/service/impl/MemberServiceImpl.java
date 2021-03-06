package cn.mldn.dibmp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IActionDAO;
import cn.mldn.dibmp.dao.IDeptDAO;
import cn.mldn.dibmp.dao.ILevelDAO;
import cn.mldn.dibmp.dao.IMemberDAO;
import cn.mldn.dibmp.dao.IRoleDAO;
import cn.mldn.dibmp.service.IMemberService;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class MemberServiceImpl extends AbstractService  implements IMemberService {
	@Resource
	private IMemberDAO memberDAO ;
	@Resource
	private IRoleDAO roleDAO ;
	@Resource
	private IActionDAO actionDAO ;
	@Resource
	private IDeptDAO deptDAO;
	@Resource
	private ILevelDAO levelDAO;
	@Override
	public Member get(String mid) {
		return this.memberDAO.findById(mid);
	}

	@Override
	public Map<String, Set<String>> getRoleAndActionByMember(String mid) {
		Map<String, Set<String>> map = new HashMap<>() ;
		map.put("allRoles", this.roleDAO.findAllByMember(mid)) ;
		map.put("allActions", this.actionDAO.findAllByMember(mid)) ;
		return map;
	}

	@Override
	public Map<String, Object> getByDid(Long did,Long current,Integer lineSize) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allMembers", memberDAO.findSplitByDid(super.paramToMap(did, current, lineSize))) ;
		map.put("allCounts", memberDAO.findAllCount(did)) ;
		return map ;
	}

	@Override
	public Map<String, Object> getDeptAndLevelByMember(String name) {
		    Map<String, Object> map = new HashMap<String,Object>();
		    Member member = this.memberDAO.findByName(name);
		    map.put("allMember", member);
		    map.put("allDept",this.deptDAO.findById(member.getDid()));
		    map.put("allLevle",this.levelDAO.findById(member.getLid()));
		   return map;
	}

}
