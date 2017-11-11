package cn.mldn.dibmp.web.action.back;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.IMemberService;
import cn.mldn.dibmp.wt.service.IStorgeApplyDetailsService;
import cn.mldn.dibmp.wt.service.IStorgeApplyService;
import cn.mldn.dibmp.wt.service.IStorgeRecordService;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/storageaudit/*")
public class StorageAuditActionBack extends AbstractAction {
	private static final String TITLE = "入库审核" ;
	private static final Integer NOTSTATUS =1;	//查询没有审核的信息
	private static final Integer YESSTATUS=5;	//查询已经审核通过的信息
	@Resource
	private IStorgeApplyService applyService;
	@Resource
	private IStorgeRecordService recordService;
	@Resource
	private IStorgeApplyDetailsService applyDetailsService;
	@Resource
	private IMemberService memberService;
	@RequestMapping("list_prepare") 
	public ModelAndView listDetails(HttpServletRequest request) {
		SplitPageUtil spu = new SplitPageUtil("申请标题:title",super.getPage("storage.audit.list.prepare.action1")) ;
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.list.prepare.page"));	
		mav.addAllObjects(applyService.listSplitFont(spu.getColumn(), spu.getKeyWord(), spu.getCurrentPage(), spu.getLineSize()));
		return mav;
	}
	@RequestMapping("edit_pre") 
	public ModelAndView editPre(Long sid) {
		System.err.println("================="+sid);
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.edit.page"));
		Map<String, Object> map = applyDetailsService.listGoodsBack(sid);
		mav.addAllObjects(map); 
		return mav;
	}
	@RequestMapping("list_history") 
	public ModelAndView listMyself() {
		SplitPageUtil spu = new SplitPageUtil("申请标题:title",super.getPage("storage.audit.list.history.action")) ;
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.list.history.page"));
		mav.addAllObjects(recordService.listRecord(spu.getColumn(), spu.getKeyWord(), spu.getCurrentPage(), spu.getLineSize()));
		return mav;
	}
	@RequestMapping("edit")
	public ModelAndView editRecord() {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		Integer status =Integer.parseInt(super.getRequest().getParameter("audit"));
		Long said = Long.parseLong(super.getRequest().getParameter("said"));
		String autime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		//当前用户名称
		String auname =String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("name"));
		System.err.println("当前用户--" + auname);
		if(status ==YESSTATUS) { //通过
			if(applyDetailsService.editSaid(said,status, autime,auname)) {
				super.setMsgAndUrl(mav, "storage.audit.list.prepare.action", "vo.editpass.success", TITLE); 		
			}
		}else if(status ==NOTSTATUS) {//拒绝
			if(applyDetailsService.editSaid(said,status,autime,auname)) {
				super.setMsgAndUrl(mav, "storage.audit.list.prepare.action", "vo.editpass.failure", TITLE); 		
			}
		}else {
			super.setMsgAndUrl(mav, "storage.audit.list.prepare.action", "vo.editpass.failure", TITLE); 
		}
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("wareHouseAndApply")
	public Object wareHouseAndApply(Long wid) {
		return this.applyService.listWareHouseAndApplyByWid(wid);
	}
	@ResponseBody
	@RequestMapping("edit_num")
	public Object editNum() {	
		String said = super.getRequest().getParameter("said");
		Map<String, Object> nums = applyDetailsService.listEditNum(Long.parseLong(said));
		return nums;
	}
	
	@ResponseBody
	@RequestMapping("levelanddeptby_member")
	public Object levelAndDeptByMember() {
		String mid = super.getRequest().getParameter("mid");
		Map<String, Object> map = memberService.getDeptAndLevelByMember(mid);
		return map;
	}
	@ResponseBody
	@RequestMapping("according_to")
	public Object accordingTo() { //当点击审核申请标题的时候显示数据,显示审核页面的所有点击页面
		long said = Long.parseLong(super.getRequest().getParameter("said"));
		int status = Integer.parseInt(super.getRequest().getParameter("status"));
		if(status==YESSTATUS) {
			return applyService.getWarehouseGoodsBySaid(said,status);
		}else if(status == NOTSTATUS) {
			return applyService.getWarehouseGoodsBySaid(said,status);	
		}else {
			return null;
		}
	}

}
