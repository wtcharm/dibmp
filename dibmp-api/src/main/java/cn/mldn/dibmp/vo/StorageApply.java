package cn.mldn.dibmp.vo;

import java.io.Serializable;
/**
 * 待审核清单
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class StorageApply implements Serializable {
	private  Long said;
	private String title;
	private Long pid;
	private Long cid;
	private Long wiid;
	private Long wid;
	private String note;
	private Integer status;		//0 待审核，1.审核通过
	private String appmid;
	public Long getSaid() {
		return said;
	}
	public void setSaid(Long said) {
		this.said = said;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Long getWiid() {
		return wiid;
	}
	public void setWiid(Long wiid) {
		this.wiid = wiid;
	}
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAppmid() {
		return appmid;
	}
	public void setAppmid(String appmid) {
		this.appmid = appmid;
	}
	@Override
	public String toString() {
		return "StorageApply [said=" + said + ", title=" + title + ", pid=" + pid + ", cid=" + cid + ", wiid=" + wiid
				+ ", wid=" + wid + ", note=" + note + ", status=" + status + ", appmid=" + appmid + "]";
	}
	
	
}
