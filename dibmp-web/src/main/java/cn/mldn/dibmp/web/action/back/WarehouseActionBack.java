package cn.mldn.dibmp.web.action.back;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.fyh.service.IWarehouseService;
import cn.mldn.dibmp.service.IMemberService;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.web.realm.MemberRealm;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/warehouse/*")
public class WarehouseActionBack extends AbstractAction {
	private static final String TITLE = "仓库" ;
	@Resource
	private IWarehouseService warehouseService ;
	@Resource
	private IMemberService MemberService ; 
	@RequestMapping("add_pre")
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getPage("warehouse.add.page"));
		return mav;
	}
	@RequestMapping("add")
	public ModelAndView add(Warehouse vo, MultipartFile pic) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		String mid = (String) SecurityUtils.getSubject().getSession().getAttribute("mid");
		vo.setRecorder(mid);
		vo.setCurrnum(0);
		if(pic==null||pic.isEmpty()) {
			vo.setPhoto("nophoto.jpg");
		}else {
			String fileExt = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
			String fileName = UUID.randomUUID()+ fileExt;
			String filePath = super.getRequest().getServletContext().getRealPath("/upload/warehouse/") + UUID.randomUUID() + fileExt;
			try {
				pic.transferTo(new File(filePath));
				vo.setPhoto(fileName);
				if(warehouseService.add(vo)) {
					super.setMsgAndUrl(mav, "warehouse.add.action", "vo.add.success", TITLE);
				}else {
					super.setMsgAndUrl(mav, "warehouse.add.action", "vo.add.failure", TITLE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl(mav, "warehouse.add.action", "vo.add.failure", TITLE);
			} // 进行文件转存
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
