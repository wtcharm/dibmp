package cn.mldn.dibmp.web.action.back;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.ccc.service.ICityService;
import cn.mldn.dibmp.ccc.service.IGoodsService;
import cn.mldn.dibmp.ccc.service.IMarketService;
import cn.mldn.dibmp.ccc.service.IProvinceService;
import cn.mldn.dibmp.ccc.service.IWitemService;
import cn.mldn.dibmp.fyh.service.IWarehouseService;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/storage/*")
public class StorageActionBack extends AbstractAction {
	private static final String TITLE = "商品入库" ;
	@Resource
	private cn.mldn.dibmp.ccc.service.IWarehouseService warehouseServiceC;
	
	
	
	@Resource
	private IProvinceService provinceService;
	@Resource
	private ICityService cityService;
	@Resource
	private IWitemService witemService;
	@Resource
	private IWarehouseService warehouseService;
	@Resource
	private IMarketService marketService;
	@Resource
	private IGoodsService GoodsService;
	
	@RequestMapping("add")
	public ModelAndView add(StorageApply vo) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		if(marketService.addStorageApply(vo, (String)SecurityUtils.getSubject().getSession().getAttribute("mid"))) {
			super.setMsgAndUrl(mav, "storage.add.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "storage.add.action", "vo.add.failure", TITLE);
		}
		return mav;
	}

	@RequestMapping("list_details") 
	public ModelAndView listDetails(Long sid) {
		ModelAndView mav = new ModelAndView(super.getPage("storage.list.details.page"));
		mav.addAllObjects(marketService.show(sid));
		return mav;
	}
	@RequestMapping("saveGoods") 
	@ResponseBody
	public Object saveGoods(StorageApplyDetails vo) {
		return marketService.saveGoods(vo);
	}
	@RequestMapping("removeGoods") 
	@ResponseBody
	public Object removeGoods(Long said,Long gid) {
		return marketService.removeGoods(said,gid);
	}
	@RequestMapping("findGoods") 
	@ResponseBody
	public Object findGoods(Long gid) {
		Goods goods = GoodsService.findById(gid);
		if(goods!=null) {
			return goods;
		}
		return false;
	}

	
	
//	@Resource
//	private IStorgeApplyService storageApplyService;	
	
	@RequestMapping("add_pre")
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getPage("storage.add.page"));
		mav.addObject("allProvinces",provinceService.findAll());
		mav.addObject("allWitem",witemService.list());
		return mav;
	}
	@RequestMapping("listCity")
	@ResponseBody
	public Object listCity(long pid) {
		return cityService.listByProvince(pid);
	}
	@RequestMapping("listWarehouse")
	@ResponseBody
	public Object listWarehouse(long wiid) {
		return warehouseServiceC.listByWitem(wiid);
	}
//	@RequestMapping("add")
//	public ModelAndView add(StorageApply vo) {
//		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
//		if(storageApplyService.add(vo, (String)SecurityUtils.getSubject().getSession().getAttribute("mid"))) {
//			super.setMsgAndUrl(mav, "storage.add.action", "vo.add.success", TITLE);
//		}else {
//			super.setMsgAndUrl(mav, "storage.add.action", "vo.add.failure", TITLE);
//		}
//		return mav;
//	}
	
	
//	@RequestMapping("add_pre")
//	public ModelAndView addPre() {
//		ModelAndView mav = new ModelAndView(super.getPage("storage.add.page"));
//		return mav;
//	}
//	@RequestMapping("add")
//	public ModelAndView add() {
//		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
//		super.setMsgAndUrl(mav, "storage.add.action", "vo.add.success", TITLE);
//		return mav;
//	}
	@RequestMapping("edit_pre")
	public ModelAndView editPre() {
		ModelAndView mav = new ModelAndView(super.getPage("storage.edit.page"));
		return mav;
	}
	@RequestMapping("edit")
	public ModelAndView edit() {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		super.setMsgAndUrl(mav, "storage.list.action", "vo.edit.success", TITLE);
		return mav;
	}
	@RequestMapping("list_myself") 
	public ModelAndView listMyself() {
		SplitPageUtil spu = new SplitPageUtil("入库单编号:said|申请标题:title", super.getPage("storage.list.myself.action")) ;
		ModelAndView mav = new ModelAndView(super.getPage("storage.list.myself.page"));
		mav.addAllObjects(warehouseService.listStorageApply(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyWord(),(String) SecurityUtils.getSubject().getSession().getAttribute("mid"))) ;
		return mav;
	}
	@RequestMapping("submit") 
	public ModelAndView submit(String sid) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		if(warehouseService.inSubmit(Long.parseLong(sid))) {
			super.setMsgAndUrl(mav, "storage.list.myself.action1", "vo.submit.success", "商品入库");
		}else {
			super.setMsgAndUrl(mav, "storage.list.myself.action1", "vo.submit.failure", "商品入库");
		}
		
		return mav;
	}
	
	@RequestMapping("remove") 
	public ModelAndView deleteStorageApply(String sid) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		if(warehouseService.removeStorageApply(Long.parseLong(sid))) {
			super.setMsgAndUrl(mav, "storage.list.myself.action1", "vo.delete.success", "入库单");
		}else {
			super.setMsgAndUrl(mav, "storage.list.myself.action1", "vo.delete.failure", "入库单");
		}
		
		return mav;
	}
	
}
