package cn.mldn.dibmp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.service.abs.AbstractService;
import cn.mldn.dibmp.vo.Citem;
import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.CustomerCitem;
import cn.mldn.dibmp.vo.CustomerRecord;
import cn.mldn.dibmp.vo.Distribution;
import cn.mldn.dibmp.vo.DistributionDetails;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.GoodsNum;
import cn.mldn.dibmp.vo.GoodsStorageAndApply;
import cn.mldn.dibmp.vo.GoodsStorageDetails;
import cn.mldn.dibmp.vo.Province;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.web.util.JavaSerializer;
import cn.mldn.web.util.RedisUtil;
import cn.mldn.dibmp.dao.CitemMapper;
import cn.mldn.dibmp.dao.CityMapper;
import cn.mldn.dibmp.dao.CsourceMapper;
import cn.mldn.dibmp.dao.CustomerMapper;
import cn.mldn.dibmp.dao.CustomerRecordMapper;
import cn.mldn.dibmp.dao.DistributionDetailsMapper;
import cn.mldn.dibmp.dao.DistributionMapper;
import cn.mldn.dibmp.dao.GoodsMapper;
import cn.mldn.dibmp.dao.ProvinceMapper;
import cn.mldn.dibmp.dao.WarehouseMapper;
import cn.mldn.dibmp.service.ISaleService;
/**
 * 销售业务中心
 * @author yaojia
 *
 */
@Service
public class SaleServiceImpl extends AbstractService implements ISaleService {

	@Resource
	private GoodsMapper goodssMapper;
	@Resource
    private CustomerMapper customerMapper;
	@Resource
    private CitemMapper citemMapper;
    @Resource
    private ProvinceMapper provinceMapper;
    @Resource
    private CityMapper cityMapper;
    @Resource
    private CsourceMapper csourceMapper;
    @Resource
    private WarehouseMapper warehouseMapper;
    @Resource
    private CustomerRecordMapper customerRecord;
    @Resource
    private DistributionDetailsMapper distributionDetailsMapper;
    @Resource
    private DistributionMapper distributionMapper;
	@Override
	public Map<String, Object> list(String column, String keyWord, Long currentPage, Integer lineSize) {
		// TODO Auto-generated method stub
		Map<String,Object> param = super.handleParams(column, keyWord, currentPage, lineSize);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allGoods", this.goodssMapper.findAllSplit(param));
		map.put("allRecorders", this.goodssMapper.getSplitCount(param));
		return map;
	}
	@Override
	public boolean edit(Goods vo) {
		// TODO Auto-generated method stub
		return this.goodssMapper.doUpdate(vo);
	}
	@Override

	public Map<String, Object> findGoodsDetailsById(Long gid) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		GoodsStorageAndApply goodsStorageAndApply = this.goodssMapper.findGoodsStorageApply(gid);
		Province province = this.provinceMapper.findById(goodsStorageAndApply.getPid());
		City city = this.cityMapper.findById(goodsStorageAndApply.getCid());
		map.put("GoodsDetails", this.goodssMapper.findGoodsDetailsById(gid));
		map.put("city", city);
		map.put("province", province);
		return map;
	}
	@Override
	public GoodsStorageAndApply findGoodsStorageApply(Long gid) {
		// TODO Auto-generated method stub
		return this.goodssMapper.findGoodsStorageApply(gid);
	}

	public boolean add(Customer vo) {
		return this.customerMapper.doCreate(vo);
	}
	public Map<String, Object> getAddPre() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allCsource", this.csourceMapper.findAll());
		map.put("allProvinces",this.provinceMapper.findAll());
		map.put("allCitys",this.cityMapper.findAll());
		map.put("allCitems", this.citemMapper.findAll());
		return map;
	}
	public Map<String, Object> listC(long currentPage, int lineSize, String column, String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> param = super.handleParams(column, keyWord, currentPage, lineSize);
		map.put("allCustomers", this.customerMapper.findAllSplit(param));
		map.put("allCitems", this.citemMapper.findAll());
		map.put("allSpiltCount", this.customerMapper.getAllSplitCount(param));
		return map;
	}
	@Override
	public GoodsStorageDetails findGoodsStorageDetails(Long gid) {
		// TODO Auto-generated method stub
		GoodsStorageDetails goodsStorageDetails = this.goodssMapper.findGoodsStorageDetails(gid);
		Warehouse warehouse = this.warehouseMapper.findById(goodsStorageDetails.getWid());
		goodsStorageDetails.setAddress(warehouse.getAddress());
		goodsStorageDetails.setAdmin(warehouse.getAdmin());
		return goodsStorageDetails;
	}
	@Override
	public GoodsStorageDetails findAllGoodsStorageDetails(Long gid) {
		// TODO Auto-generated method stub
		GoodsStorageDetails goodsStorageDetails = this.goodssMapper.findGoodsStorageDetails(gid);
		Warehouse warehouse = this.warehouseMapper.findById(goodsStorageDetails.getWid());
		City city = this.cityMapper.findById(warehouse.getCid());
		Province pro = this.provinceMapper.findById(warehouse.getPid());
		goodsStorageDetails.setAddress(warehouse.getAddress());
		goodsStorageDetails.setAdmin(warehouse.getAdmin());
		goodsStorageDetails.setCityName(city.getTitle());
		goodsStorageDetails.setProviceName(pro.getTitle());
		return goodsStorageDetails;
	}
	
	/**
	 * 通过Redis中取得销售人员所对商品数据和所对应的客户信息
	 */
	@Override
	public Map<String, Object> findAllUnStorageGoods(String nameId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Set<String> keys = RedisUtil.keys() ; 
		Iterator<String> iter = keys.iterator() ;
		List<GoodsNum> allGoods = new ArrayList<GoodsNum>();
		while (iter.hasNext()) {
			String key = iter.next() ;
			String result [] = key.split(":");
			for(int x=0; x < result.length-1; x++) {
				String temp [] = result[0].split("_");
				if(temp[1].equals(nameId)) {
					allGoods.add(new JavaSerializer().deserialize(RedisUtil.getJedis().get(key.getBytes()), GoodsNum.class));
					System.err.println(key + " = " + RedisUtil.getJedis().get(key));
				}
			}
		}
		map.put("allRedisGoods", allGoods);
		List<CustomerRecord> allCustomerRecord = this.customerRecord.findByCmid(nameId);
		if(allCustomerRecord.size() == 1) {
			Iterator<CustomerRecord> iters = allCustomerRecord.iterator() ;
			Customer customer = this.customerMapper.findAllCustomerByCmid(iters.next().getCuid()).get(0);
			Citem citem = this.citemMapper.findById(customer.getCiid());
			CustomerCitem cc= new CustomerCitem();
			cc.setName(customer.getName());
			cc.setNote(customer.getNote());
			cc.setPhone(customer.getPhone());
			cc.setLevelTitle(citem.getTitle());
			map.put("customer", cc);
		}else {
			Iterator<CustomerRecord> iters = allCustomerRecord.iterator() ;
			while(iters.hasNext()) {
				CustomerRecord customerRecord = iters.next();
				List<Customer> allCustomer = this.customerMapper.findAllCustomerByCmid(customerRecord.getCuid());
				Iterator<Customer> alliter = allCustomer.iterator();
				List<CustomerCitem> allCustomerCitem = new ArrayList<CustomerCitem>();
				while(alliter.hasNext()) {
					Customer c = alliter.next();
					Citem citem = this.citemMapper.findById(c.getCiid());
					CustomerCitem cc= new CustomerCitem();
					cc.setName(c.getName());
					cc.setNote(c.getNote());
					cc.setPhone(c.getPhone());
					cc.setLevelTitle(citem.getTitle());
					allCustomerCitem.add(cc);
				}
				map.put("customer", allCustomerCitem);
			}
		}
		return map;
	}
	
	@Override
	public Map<String, Object> distributionGoods(String nameId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Set<String> keys = RedisUtil.getKeys() ; 
		Iterator<String> iter = keys.iterator() ;
		List<DistributionDetails> allDistributions = new ArrayList<DistributionDetails>();
		while (iter.hasNext()) {
			String key = iter.next() ;
			String result [] = key.split(":");
			for(int x=0; x < result.length; x++) {
				String temp [] = result[0].split("_");
				if(temp[1].equals(nameId)) {
					allDistributions.add(new JavaSerializer().deserialize(RedisUtil.getJedis().get(key.getBytes()), DistributionDetails.class));
					System.err.println(key + " = " + RedisUtil.getJedis().get(key));
				}
			}
		}
		map.put("DistributionDetails", allDistributions);
		return map;
	}
	
	@Override
	public Map<String, Object> listCity(Long pid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allCities",this.cityMapper.findByPid(pid));
		return map;
	}
	@Override
	public Map<String, Object> findAllProcince() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allProvinces",this.provinceMapper.findAll());
		return map;
	}
	@Override
	public DistributionDetails findDistributionDetails(Long gid) {
		// TODO Auto-generated method stub
		return this.distributionDetailsMapper.findDistributionDetails(gid);
	}
	@Override
	public boolean insert(Distribution vo) {
		// TODO Auto-generated method stub
		return this.distributionMapper.doCreate(vo);
	}
	@Override
	public boolean insertDistributionDetails(DistributionDetails vo) {
		// TODO Auto-generated method stub
		return this.distributionDetailsMapper.doCreate(vo);
	}
	@Override
	public Map<String, Object> listDistribution(String column, String keyWord, Long currentPage, Integer lineSize) {
		// TODO Auto-generated method stub
		Map<String,Object> param = super.handleParams(column, keyWord, currentPage, lineSize);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allDistribution", this.distributionMapper.findAllSplit(param));
		map.put("allRecorders", this.distributionMapper.getSplitCount(param));
		map.put("allProvices", this.provinceMapper.findAll());
		map.put("allCitys", this.cityMapper.findAll());
		return map;
	}
}
