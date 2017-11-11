package cn.mldn.dibmp.vo;

import java.io.Serializable;

/**
 * 客户与客户级别的关系
 * @author yaojia
 *
 */
public class CustomerCitem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -322406162782503908L;
	private String name;
	private String phone;
	private String levelTitle;
	private String note;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLevelTitle() {
		return levelTitle;
	}
	public void setLevelTitle(String levelTitle) {
		this.levelTitle = levelTitle;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "CustomerCitem [name=" + name + ", phone=" + phone + ", levelTitle=" + levelTitle + ", note=" + note
				+ "]";
	}
	

}
