package cn.mldn.dibmp.vo;

import java.io.Serializable;
/**
 * 审核过的清单存放
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class StorageRecordStatus implements Serializable {
	private Integer srsid ;
	private String srstitle ;
	public Integer getSrsid() {
		return srsid;
	}
	public void setSrsid(Integer srsid) {
		this.srsid = srsid;
	}
	public String getSrstitle() {
		return srstitle;
	}
	public void setSrstitle(String srstitle) {
		this.srstitle = srstitle;
	}
	
	
	
}
