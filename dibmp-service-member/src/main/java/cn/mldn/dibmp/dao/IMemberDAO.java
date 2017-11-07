package cn.mldn.dibmp.dao;

import java.util.List;

import cn.mldn.dibmp.vo.Member;

public interface IMemberDAO {
	public Member findById(String id);
	public List<Member> findByDid(Long did) ;
	public Long findAllCount(Long did) ;
}
