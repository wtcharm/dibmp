package cn.mldn.dibmp.service.impl.ccc;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.ccc.service.IWitemService;
import cn.mldn.dibmp.dao.ccc.IWitemDAO;
import cn.mldn.dibmp.vo.Witem;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class WitemServiceImpl extends AbstractService implements IWitemService {
	@Resource
	private IWitemDAO witemDAO;
	
	@Override
	public List<Witem> list() {
		return witemDAO.findAll();
	}

}
