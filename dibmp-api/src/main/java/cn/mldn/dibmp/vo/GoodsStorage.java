package cn.mldn.dibmp.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class GoodsStorage implements Serializable {

	private Long gid;
	private String name;
	private Double price;
	private Double weight;
	private String photo;
	private String note;
	private Date lastin; 
	private Integer stornum;
	private Integer num;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getLastin() {
		return lastin;
	}
	public void setLastin(Date lastin) {
		this.lastin = lastin;
	}
	public Integer getStornum() {
		return stornum;
	}
	public void setStornum(Integer stornum) {
		this.stornum = stornum;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "GoodsStorage [gid=" + gid + ", name=" + name + ", price=" + price + ", weight=" + weight + ", photo="
				+ photo + ", note=" + note + ", lastin=" + lastin + ", stornum=" + stornum + ", num=" + num + "]";
	}
	
}
