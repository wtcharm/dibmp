package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StorageApplyStatus implements Serializable {
	private Integer sasid ;
	private String sastitle ;
	public Integer getSasid() {
		return sasid;
	}
	public void setSasid(Integer sasid) {
		this.sasid = sasid;
	}
	public String getSastitle() {
		return sastitle;
	}
	public void setSastitle(String sastitle) {
		this.sastitle = sastitle;
	}
	@Override
	public String toString() {
		return "StorageApplyStatus [sasid=" + sasid + ", sastitle=" + sastitle + "]";
	}
	
	
	
}
