package cn.mldn.dibmp.vo;

import java.io.Serializable;

/**
 * 商品信息表
 * @author yaojia
 *
 */
@SuppressWarnings("serial")
public class GoodsNum implements Serializable { 

	private Long gid;
	private String name;
	private Double price;
	private Double weight;
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
}
