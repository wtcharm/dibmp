package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GoodsStorageAndApply implements Serializable {

	private Long pid;
	private Long cid;
	private Long wid;
	private Integer num;
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
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "GoodsStorageAndApply [pid=" + pid + ", cid=" + cid + ", wid=" + wid + ", num=" + num + "]";
	}
	
}
