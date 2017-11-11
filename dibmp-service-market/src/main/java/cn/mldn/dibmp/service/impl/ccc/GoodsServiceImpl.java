package cn.mldn.dibmp.service.impl.ccc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.ccc.service.IGoodsService;
import cn.mldn.dibmp.dao.ccc.IGoodsDAO;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class GoodsServiceImpl extends AbstractService implements IGoodsService {
	@Resource
	private IGoodsDAO goodsDAO;
	@Override
	public Goods findByName(String name) {
		return goodsDAO.findByName(name);
	}
	@Override
	public boolean add(Goods vo,String mid) {
		vo.setLastin(new Date());
		vo.setStornum(0L);//增加是库存量为0
		vo.setRecorder(mid);
		vo.setDelflag(0);
		if(goodsDAO.findByName(vo.getName())!=null) {
			return false;
		}
		return goodsDAO.doCreate(vo);
	}
	@Override
	public Map<String,Object> list(String column, String keyWord, Long currentPage, Integer lineSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allGoods", goodsDAO.findAllSplit(super.paramToMap(currentPage, lineSize, column, keyWord)));
		map.put("allRecorders", goodsDAO.getSplitCount(super.paramToMap(column, keyWord)));
		return map;
	}
	@Override
	public Goods editPre(long gid) {
		return goodsDAO.findById(gid);
	}
	@Override
	public boolean update(Goods vo,String mid) {
		vo.setLastin(new Date());
		vo.setRecorder(mid);
		return goodsDAO.doEdit(vo);
	}
	@Override
	public Goods findById(Long gid) {
		return goodsDAO.findById(gid);
	}

}
