package cn.mldn.dibmp.service.impl.ccc;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.ccc.service.ISubtypeService;
import cn.mldn.dibmp.dao.ccc.ISubtypeDAO;
import cn.mldn.dibmp.vo.Subtype;
import cn.mldn.util.service.abs.AbstractService;
@Service	
public class SubtypeServiceImpl extends AbstractService implements ISubtypeService {
	@Resource
	private ISubtypeDAO subtypeDAO;
	@Override
	public List<Subtype> findByWiid(Long wiid) {
		return subtypeDAO.findAllByWitem(wiid);
	}

}
