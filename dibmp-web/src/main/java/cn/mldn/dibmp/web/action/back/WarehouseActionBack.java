package cn.mldn.dibmp.web.action.back;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.ccc.service.ICityService;
import cn.mldn.dibmp.ccc.service.IProvinceService;
import cn.mldn.dibmp.ccc.service.IWitemService;
import cn.mldn.dibmp.fyh.service.IWarehouseService;
import cn.mldn.dibmp.service.IMemberService;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.fastdfs.FastDFS;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/warehouse/*")
public class WarehouseActionBack extends AbstractAction {
	private static final String TITLE = "仓库" ;
	@Resource
	private IWarehouseService warehouseService ;
	@Resource
	private IMemberService MemberService ; 
	@Resource
	private IProvinceService provinceService;
	@Resource
	private ICityService cityService;
	@Resource
	private IWitemService witemService;
	@RequestMapping("add_pre")
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getPage("warehouse.add.page"));
		mav.addObject("allProvinces",provinceService.findAll());
		mav.addObject("allWitem",witemService.list());
		return mav;
	}
	@RequestMapping("listCity")
	@ResponseBody
	public Object listCity(long pid) {
		return cityService.listByProvince(pid);
	}
	@RequestMapping("add")
	public ModelAndView add(Warehouse vo, MultipartFile pic) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		String mid = (String) SecurityUtils.getSubject().getSession().getAttribute("mid");
		vo.setRecorder(mid);
		vo.setCurrnum(0);
		if(pic.getSize()==0) {
			vo.setPhoto("group1/M00/00/00/wKgclVoDyeOAJIEHAAA21Ria8C4574.jpg");
		}else {
			String fileName = FastDFS.upload(pic);
			vo.setPhoto(fileName);
		}
		if(warehouseService.add(vo)) {
			super.setMsgAndUrl(mav, "warehouse.add.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "warehouse.add.action", "vo.add.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("edit_pre")
	public ModelAndView editPre(long wid) {
		ModelAndView mav = new ModelAndView(super.getPage("warehouse.edit.page"));
		return mav;
	}
	@RequestMapping("edit")
	public ModelAndView edit() {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		super.setMsgAndUrl(mav, "warehouse.list.action", "vo.edit.success", TITLE);
		return mav;
	}
	@RequestMapping("list")
	public ModelAndView list() {
		SplitPageUtil spu = new SplitPageUtil("仓库名称:name|仓库地址:address", super.getPage("warehouse.list.action")) ;
		ModelAndView mav = new ModelAndView(super.getPage("warehouse.list.page"));
		mav.addAllObjects(warehouseService.list(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyWord())) ;
		return mav; 
	}
	@ResponseBody
	@RequestMapping("listByDid")
	public Object listByDid(String did) {
		SplitPageUtil spu = new SplitPageUtil(null,null) ;
		return MemberService.getByDid(Long.parseLong(did),spu.getCurrentPage(),spu.getLineSize()) ;
	}
	
	@ResponseBody
	@RequestMapping("addAdmin")
	public Object addAdmin(String admin,String wid) {
		Warehouse vo = new Warehouse() ;
		vo.setWid(Long.parseLong(wid));
		vo.setAdmin(admin);
		vo.setRecorder((String)SecurityUtils.getSubject().getSession().getAttribute("mid"));
		return warehouseService.addAdmin(vo) ;
	}
}
