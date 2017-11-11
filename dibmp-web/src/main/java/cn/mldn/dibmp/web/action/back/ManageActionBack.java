package cn.mldn.dibmp.web.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.vo.StorageRecord;
import cn.mldn.dibmp.wt.service.IStorgeApplyService;
import cn.mldn.dibmp.wt.service.IStorgeInputService;
import cn.mldn.dibmp.wt.service.IStorgeRecordService;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/manage/*")
public class ManageActionBack extends AbstractAction {
	private static final String TITLE = "仓库管理" ;
	private static final String NOTSTATUS ="1";	//查询没有审核的信息
	private static final String YESSTATUS="5";	//查询已经审核通过的信息
	@Resource
	private IStorgeInputService inputService;
	@Resource
	private IStorgeApplyService applyService;
	@Resource
	private RedisTemplate<String, Long> redisString;
	@Resource
	private IStorgeRecordService recordServce;

	@RequestMapping("storage_input_pre")
	public ModelAndView storageInputPre() {
		ModelAndView mav = new ModelAndView(super.getPage("manage.storage.input.page"));
		return mav;
	}
	@RequestMapping("storage_input")
	public ModelAndView storageInput(String sid) {
		System.err.println("查询到的信息 -- +  " +sid);
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		String reqex = "[0-9]*";
		if(sid.matches(reqex)) {
			redisString.opsForValue().set("display-sid", Long.parseLong(sid));
			if(inputService.isSaidVo(Long.parseLong(sid),YESSTATUS)) {
				super.setMsgAndUrl(mav, "manage.storage.input.display.action", "vo.inputfound.failure", TITLE);
			}else {
				super.setMsgAndUrl(mav, "manage.storage.input.pre.action", "vo.inputfound.success", TITLE);
			}
		}else {
			super.setMsgAndUrl(mav, "manage.storage.input.pre.action", "vo.inputfound.success", TITLE);
		}
		return mav;
	}
	@RequestMapping("storage_display")
	public ModelAndView storageDisplay() {
		ModelAndView mav = new ModelAndView(super.getPage("manage.storage.page"));
		//Long sid = Long.parseLong( String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("sid")));
		Long sid = redisString.opsForValue().get("display-sid");
		System.err.println("sid==" + sid);
		Map<String, Object> map = inputService.listInputBcke(sid,YESSTATUS);
		mav.addAllObjects(map);
		return mav;
	}
	@ResponseBody
	@RequestMapping("record_shop")
	public Object recordPreserSucceed(StorageRecord record) {
		record.setStatus(5);//添加成功
		record.setInmid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("name")));
		record.setSaid(redisString.opsForValue().get("display-sid"));
		//System.err.println("传递进来的对象值---  | " + record);
		boolean add = recordServce.add(record);
		//System.err.println("添加数据-"+ add);
		return add;
	}
	@ResponseBody
	@RequestMapping("record_shop_error")
	public Object recordPreserError(StorageRecord record) {
		record.setStatus(3);//添加失败
		record.setInmid(String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("name")));
		record.setSaid(redisString.opsForValue().get("display-sid"));
		//System.err.println("传递进来的对象值---  | " + record);
		boolean add = recordServce.add(record);
		//System.err.println("添加数据-"+ add);
		return add;
	}

	@ResponseBody
	@RequestMapping("storage_shop")
	public Object storageShop() {
		long said = Long.parseLong(super.getRequest().getParameter("said"));
		return inputService.listInputBcke(said,YESSTATUS);

	}
	@RequestMapping("distribution_input_pre")
	public ModelAndView distributionInputPre() {
		ModelAndView mav = new ModelAndView(super.getPage("manage.distribution.input.page"));
		return mav;
	}
	@RequestMapping("distribution_input")
	public ModelAndView distributionInput() {
		ModelAndView mav = new ModelAndView(super.getPage("manage.distribution.page"));
		return mav;
	}
}
