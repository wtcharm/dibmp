package cn.mldn.dibmp.web.action.back;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dibmp.ccc.service.IGoodsService;
import cn.mldn.dibmp.ccc.service.ISubtypeService;
import cn.mldn.dibmp.ccc.service.IWitemService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.ISaleService;
import cn.mldn.dibmp.vo.Distribution;
import cn.mldn.dibmp.vo.DistributionDetails;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.GoodsNum;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.exception.CacheException;
import cn.mldn.util.fastdfs.FastDFS;
import cn.mldn.web.helper.SplitPageHelper;
import cn.mldn.web.util.CastUtil;
import cn.mldn.web.util.RedisUtil;
import cn.mldn.web.util.StringUtil;
import cn.mldn.web.util.WebUtil;

@Controller
@RequestMapping("/pages/back/admin/goods/*")
public class GoodsActionBack extends AbstractAction {

	private Logger logger = LoggerFactory.getLogger(GoodsActionBack.class);
	private static final String TITLE = "商品出库申请单";
    @Resource
    private ISaleService saleServicess;
    
    
    
    
    
    
    
    @Resource
	private IWitemService witemService;
	@Resource
	private ISubtypeService subtypeService;
	@Resource
	private IGoodsService goodsService;
	
	@RequestMapping("add_preGoods")
	public ModelAndView addPreGoods() {
		ModelAndView mav = new ModelAndView(super.getPage("goods.add.page"));
		mav.addObject("allWitem",witemService.list());
		return mav;
	}
	@RequestMapping("listSubtype")
	@ResponseBody
	public Object findByWiid(long wiid) {
		return subtypeService.findByWiid(wiid);
	}
	@RequestMapping("findByName")
	@ResponseBody
	public Object findByName(String name) {
		return goodsService.findByName(name)!=null;
	}
	@RequestMapping("addGoods")
	public ModelAndView addGoods(Goods goods, MultipartFile pic) {
		System.err.println(pic.getSize());
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		if(pic.getSize()==0) {
			goods.setPhoto("group1/M00/00/00/wKgclVoDyeOAJIEHAAA21Ria8C4574.jpg");
		}else {
			String fileName = FastDFS.upload(pic);
			goods.setPhoto(fileName);
		}
		if(goodsService.add(goods, (String)SecurityUtils.getSubject().getSession().getAttribute("mid"))) {
			super.setMsgAndUrl(mav, "goods.add.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "goods.add.action", "vo.add.failure", TITLE);
		}
		return mav;
	} 

	@RequestMapping("edit_pre")
	public ModelAndView editPre(long gid) { 
		ModelAndView mav = new ModelAndView(super.getPage("goods.edit.page"));
		mav.addObject("Goods", goodsService.editPre(gid));
		mav.addObject("allWitem", witemService.list());
		mav.addObject("allSubtype",subtypeService.findByWiid(goodsService.editPre(gid).getWiid()));
		return mav;
	} 
	@RequestMapping("edit")
	public ModelAndView edit(Goods goods,MultipartFile pic) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		if(pic.getSize()>0) {
			String fileName = FastDFS.upload(pic);
			goods.setPhoto(fileName);
		}
		if(goodsService.update(goods, (String)SecurityUtils.getSubject().getSession().getAttribute("mid"))) {
			super.setMsgAndUrl(mav, "goods.list.action", "vo.edit.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "goods.list.action", "vo.edit.failure", TITLE);
		}
		return mav;
	}
    
    
    
    
    
    
    
    @RequestMapping("list") 
    public ModelAndView list(HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView(super.getPage("goods.list.page"));
        String defaultColumn = "商品名称:name|商品价格:price";
        SplitPageHelper sph = new SplitPageHelper(request, super.getPage("goods.list.action"),defaultColumn);
	    Map<String, Object> map = this.saleServicess.list(sph.getColumn(), sph.getKeyWord(), sph.getCurrentPage(), sph.getLineSize());
	    mav.addAllObjects(map);
	    return mav;
     }
    
    @RequestMapping("show") 
    public ModelAndView show(Long gid) {
    	ModelAndView mav = new ModelAndView(super.getPage("goods.show.page"));
        mav.addAllObjects(this.saleServicess.findGoodsDetailsById(gid));
	    return mav;
     }
    
    @RequestMapping("detailSstorage") 
    public void Details(HttpServletResponse response,Long gid) {
    	WebUtil.writeJSON(response, this.saleServicess.findAllGoodsStorageDetails(gid));
     }
    
    @RequestMapping("storageDetails") 
    public void storageDetails(HttpServletResponse response,Long gid) {
    	WebUtil.writeJSON(response, this.saleServicess.findGoodsStorageDetails(gid));
     }
    
    @RequestMapping("unStorage") 
    public void unStorage(HttpServletRequest request,HttpServletResponse response,GoodsNum goodsNum) {
        String mid = (String) SecurityUtils.getSubject().getSession().getAttribute("mid");
        try {
              RedisUtil.put(mid,goodsNum,goodsNum.getGid());
              WebUtil.writeJSON(response, "true");
        }catch(Exception e) {
        	logger.error("缓存出现错误了", e);
        	 WebUtil.writeJSON(response, "false");
        }
     }
    
    /**
     * 从Redis中读取商品数据和所对应的联系人
     * @param request
     * @return
     */
    @RequestMapping("unStorageGoods") 
    public ModelAndView unStorageGoods(HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView(super.getPage("distribution.goods.list.page"));
    	 String mid = (String) SecurityUtils.getSubject().getSession().getAttribute("mid");
    	mav.addAllObjects(this.saleServicess.findAllUnStorageGoods(mid));
		return mav;
    }
    
    /**
     * 修改代购商品(修改redis的数据)
     * @param response
     */
    @RequestMapping("updateUnStorageGoods") 
    public void updateUnStorageGoods(HttpServletResponse response,GoodsNum goodsNum) {
    	 String mid = (String) SecurityUtils.getSubject().getSession().getAttribute("mid");
		 try {
			 if(RedisUtil.get(mid) == null) {
				 RedisUtil.put(mid, goodsNum,goodsNum.getGid());
				 WebUtil.writeJSON(response, "true");
			 }else {
				 RedisUtil.update(mid, goodsNum,goodsNum.getGid());
				 WebUtil.writeJSON(response, "true");
			 }
		 }catch(CacheException e) {
			 logger.error("修改代购商品数据",e);
			 WebUtil.writeJSON(response, "false");
		 }
    }
    
    @RequestMapping("deleteGoods") 
    public void deleteGoods(HttpServletResponse response,String ids) {
    	 String mid = (String) SecurityUtils.getSubject().getSession().getAttribute("mid");
    	 try {
    	    RedisUtil.del(mid, ids.substring(0, ids.length()-1));
    	    WebUtil.writeJSON(response, "true");
    	 }catch(Exception e) {
    		 logger.error("删除代购商品失败",e);
    		 WebUtil.writeJSON(response, "false");
    	 }
    }
    
    
    @RequestMapping("distributionGoods") 
    public void distributionGoods(HttpServletResponse response,Distribution distribution) {
    	 String mid = (String) SecurityUtils.getSubject().getSession().getAttribute("mid");
    	 
    	 try {
    	    RedisUtil.set(mid, distribution, distribution.getPid());
    	    WebUtil.writeJSON(response, "true");
    	 }catch(Exception e) {
    		 logger.error("创建出库商品失败",e);
    		 WebUtil.writeJSON(response, "false");
    	 }
    }

    
    @RequestMapping("list_myself")
    public ModelAndView mydistributionGoods(HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView(super.getPage("distribution.list.myself.page"));
    	String mid = (String) SecurityUtils.getSubject().getSession().getAttribute("mid");
  	    Map<String, Object> map = this.saleServicess.distributionGoods(mid);
  	    mav.addAllObjects(map);
		return mav;
    }
    
    @RequestMapping("add_pre") 
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getPage("distribution.add.page"));
		mav.addAllObjects(this.saleServicess.findAllProcince());
		return mav;
	}
    
    /**
     * 定义出库申请
     * @param request
     * @param vo
     * @return
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("add") 
	public ModelAndView add(HttpServletRequest request,Distribution distribution) {
    	Map<String,Map<String,Long>> midAmountSum = (Map<String, Map<String, Long>>) request.getSession().getAttribute("midAmountSum");
    	String mid = (String) SecurityUtils.getSubject().getSession().getAttribute("mid");
    	Map<String,Long> amountSum = midAmountSum.get(mid);
    	distribution.setPrice(Double.parseDouble((amountSum.get("sumPrices").toString())));
    	distribution.setGnum(Integer.parseInt(amountSum.get("amounts").toString()));
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		boolean flag = false;
		if(this.saleServicess.insert(distribution)) {
			Map<String, Object> map = this.saleServicess.distributionGoods(mid);
			List<DistributionDetails> allDistributionDetails = (List<DistributionDetails>) map.get("DistributionDetails");
			Iterator<DistributionDetails> iter = allDistributionDetails.iterator();
			while(iter.hasNext()) {
				DistributionDetails vo = iter.next();
				vo.setOutmid(mid);
				vo.setStatus(0);
				if(this.saleServicess.insertDistributionDetails(vo)) {
					flag = true;
					if(flag) {
						  RedisUtil.del(mid, vo.getGid().toString());
					}
					super.setMsgAndUrl(mav, "distribution.addPre.action", "vo.add.success",TITLE);
				}else {
					super.setMsgAndUrl(mav, "distribution.addPre.action", "vo.add.failure",TITLE);
				}
			}
		}else {
			super.setMsgAndUrl(mav, "distribution.addPre.action", "vo.add.failure",TITLE);
		}
		return mav;
	}
    
    @RequestMapping("listCity") 
   	public void listCity(HttpServletResponse response,Long pid) {
   		WebUtil.writeHTML(response, this.saleServicess.listCity(pid));
   	}
    
    /**
     * 保存商品的总数及总价和出库申请详情
     * @param amount
     * @param sumPrice
     */
    @RequestMapping("calculateAmountAndSumPrice") 
    public void calculateAmountAndSumPrice(HttpServletRequest request,
    		HttpServletResponse response,String amount,String sumPrice,String ids) {
    	String mid = (String) SecurityUtils.getSubject().getSession().getAttribute("mid");
    	Map<String,Map<String,Long>> midAmountSum = new LinkedHashMap<String,Map<String,Long>>();
    	Map<String,Long> amountSum = new HashMap<String,Long>();
    	try {
	    	if(StringUtil.isNotEmpty(amount) && StringUtil.isNotEmpty(sumPrice)) {
	    		Long amounts = CastUtil.castLong(amount);
	    		long sumPrices = CastUtil.castLong(sumPrice);
	    		amountSum.put("amounts", amounts);
	    		amountSum.put("sumPrices", sumPrices);
	    		midAmountSum.put(mid, amountSum);
	    		request.getSession().setAttribute("midAmountSum", midAmountSum);
	    		Set<Long> gids = CastUtil.castLongSet(ids.substring(0, ids.length()-1));
		    	Iterator<Long> iter = gids.iterator();
		    	while(iter.hasNext()) {
		    		Long gid = iter.next();
		    		DistributionDetails vo = this.saleServicess.findDistributionDetails(gid);
		    		try {
		    		  RedisUtil.set(mid, vo, gid);
		    		}catch(CacheException e) {
		    			logger.error("出库申请详情 缓存出错了",e);
		    			throw new RuntimeException(e);
		    		}
		    	}
	    		WebUtil.writeJSON(response, "true");
	    	}else {
	    		WebUtil.writeJSON(response, "false");
	    	}
    	}catch(Exception e) {
    		logger.error("出库申请详情 出错",e);
    	}
    }
    
    
   
    @RequestMapping("listDistribution") 
    public ModelAndView listDistribution(HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView(super.getPage("distribution.list.myself.page"));
        String defaultColumn = "出单编号:dsid|申请标题:title|商品总价格:price";
        SplitPageHelper sph = new SplitPageHelper(request, super.getPage("distribution.list.myself.action"),defaultColumn);
	    Map<String, Object> map = this.saleServicess.listDistribution(sph.getColumn(), sph.getKeyWord(), sph.getCurrentPage(), sph.getLineSize());
	    mav.addAllObjects(map);
	    return mav;
     }
    
    
    
    
}
