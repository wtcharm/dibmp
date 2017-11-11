package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Member;

public interface IMemberDAO {
	public Member findById(String id);
	public List<Member> findSplitByDid(Map<String,Object> params) ;
	public Long findAllCount(Long did) ;
	public Member findByName(String name);
}
