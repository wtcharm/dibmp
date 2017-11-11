package cn.mldn.dibmp.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class GoodsStorageDetails implements Serializable {

	private Long wid;
	private String inmid;
	private Date lastin;
	private Long stornum;
	private String appmid;
	private String address;
	private String admin;
	
	
	private String cityName;
	private String proviceName;
	
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProviceName() {
		return proviceName;
	}
	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public String getInmid() {
		return inmid;
	}
	public void setInmid(String inmid) {
		this.inmid = inmid;
	}
	public Date getLastin() {
		return lastin;
	}
	public void setLastin(Date lastin) {
		this.lastin = lastin;
	}
	public Long getStornum() {
		return stornum;
	}
	public void setStornum(Long stornum) {
		this.stornum = stornum;
	}
	public String getAppmid() {
		return appmid;
	}
	public void setAppmid(String appmid) {
		this.appmid = appmid;
	}
	@Override
	public String toString() {
		return "GoodsStorageDetails [wid=" + wid + ", inmid=" + inmid + ", lastin=" + lastin + ", stornum=" + stornum
				+ ", appmid=" + appmid + ", address=" + address + ", admin=" + admin + ", cityName=" + cityName
				+ ", proviceName=" + proviceName + "]";
	}
	
}
