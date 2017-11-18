package cn.mldn.dibmp.web.action.back;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.ISaleServicejdk;
import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.CustomerRecord;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/customer/*")
public class CustomerActionBack extends AbstractAction {
	private static final String TITLE = "客户" ;
	
	@Resource
	private ISaleServicejdk saleService;

	@RequestMapping("add_pre")
	public ModelAndView addPre(Customer vo,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
			try {
				Map<String,Object> map = this.saleService.getAddPre();  //增加前查询
				mav.addObject("allCsources", map.get("allCsources"));
				mav.addObject("allProvinces",map.get("allProvinces"));
				mav.addObject("allCitites", map.get("allCities"));
				mav.addObject("allCitems", map.get("allCitems"));
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName(super.getPage("customer.add.page"));
		    return mav;
	}
	
	@RequestMapping("listCity")
	@ResponseBody
	public Object listCity(long pid) {
		return saleService.listCity(pid);
	}
	@RequestMapping("listCritem")
	@ResponseBody
	public Object listCritem() {
		return saleService.listCritem();
	}

	@RequestMapping("add")
	public ModelAndView add(Customer vo) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		String mid = (String)super.getRequest().getSession().getAttribute("mid");
		vo.setRecorder(mid);
		try {
			if(this.saleService.add(vo)) {
				super.setMsgAndUrl(mav,"customer.add.action", "vo.add.success", TITLE);
			}else {
				super.setMsgAndUrl(mav,"customer.add.action", "vo.add.failure", TITLE);
			}
		}catch(Exception e) {
			super.setMsgAndUrl(mav,"customer.add.action","customer.add.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("add_customerrecord")
	@ResponseBody
	public Object addCustomerRecord(CustomerRecord vo) throws IOException {
		return this.saleService.addCustomerRecord(vo,(String)super.getRequest().getSession().getAttribute("mid"));
	}
	@RequestMapping("record_edit_pre")
	public ModelAndView editCritem(long criid) {
		ModelAndView mav = new ModelAndView(super.getPage("customer.record.page"));
		mav.addAllObjects(this.saleService.listCritem(criid));
		return mav;
	}
	@RequestMapping("edit")
	public ModelAndView edit() {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		super.setMsgAndUrl(mav, "customer.list.action", "vo.edit.success", TITLE);
		return mav;
	}

	@RequestMapping("list") 
	public ModelAndView list() {
		SplitPageUtil spu = new SplitPageUtil("客户姓名:name|联系电话:phone|客户地址:address", super.getPage("customer.list.action"));
		ModelAndView mav = new ModelAndView(super.getPage("customer.list.page"));
		Map<String,Object> map = this.saleService.listC(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyWord());
		mav.addAllObjects(map);
		return mav;
	}
	

	@RequestMapping("ListCustomerRecord")
	@ResponseBody
	public Object editRecord(Long cuid) throws IOException {
		Map<String,Object> map = new HashMap<>();
		map.put("allCr", saleService.getAllCrById(cuid));
		return map;
	}
	@RequestMapping("add_check") 
	public ModelAndView check(HttpServletResponse response,String name) {
		super.print(response, this.saleService.getName(name)==null);
		return null;
	}
}
