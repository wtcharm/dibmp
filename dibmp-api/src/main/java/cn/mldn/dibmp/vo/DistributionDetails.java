package cn.mldn.dibmp.vo;

import java.io.Serializable;

/**
 * -- 定义出库申请详情
 * @author yaojia
 *
 */
public class DistributionDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7171107477681043697L;
	private Long dsdid;
	private Long gid;
	private String name;
	private int num;
	private Double price;
	private int status;
	private Long wid;
	private String outmid;
	public Long getDsdid() {
		return dsdid;
	}
	public void setDsdid(Long dsdid) {
		this.dsdid = dsdid;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public String getOutmid() {
		return outmid;
	}
	public void setOutmid(String outmid) {
		this.outmid = outmid;
	}
	@Override
	public String toString() {
		return "DistributionDetails [dsdid=" + dsdid + ", gid=" + gid + ", name=" + name + ", num=" + num + ", price="
				+ price + ", status=" + status + ", wid=" + wid + ", outmid=" + outmid + "]";
	}
	
}
