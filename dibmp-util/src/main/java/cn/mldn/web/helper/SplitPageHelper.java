package cn.mldn.web.helper;

import javax.servlet.http.HttpServletRequest;

import cn.mldn.web.util.CastUtil;

/**
 * 分页工具类
 * @author yaojia
 *
 */
public class SplitPageHelper {

	private long currentPage ;
	private int lineSize ;
	private String column ;
	private String keyWord ;
	private HttpServletRequest request ;
	
	/**
	 * 设置分页参数
	 * @param request 请求对象
	 * @param actionUrl action跳转的路径
	 * @param defaultColumn 分页搜索的下拉列表数据
	 */
	public SplitPageHelper(HttpServletRequest request, String actionUrl, String defaultColumn) {
		this.request = request;
		this.request.setAttribute("columnData", defaultColumn);
		this.request.setAttribute("handleUrl", actionUrl); 
		this.currentPage = CastUtil.castLong(this.request.getParameter("cp"));
		if(this.currentPage == 0) {
			this.currentPage =1 ;
		}
		this.request.setAttribute("currentPage", this.currentPage);
		this.lineSize = CastUtil.castInt(this.request.getParameter("ls"));
		if(this.lineSize == 0) {
			this.lineSize = 5 ;
		}
		this.request.setAttribute("lineSize", this.lineSize);
		this.column = this.request.getParameter("col");
		if (this.column == null) {
			this.column = "" ;
		}
		this.request.setAttribute("column", this.column);
		this.keyWord = this.request.getParameter("kw");
		if (this.keyWord == null) {
			this.keyWord = "" ;
		}
		this.request.setAttribute("keyWord", this.keyWord);
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public int getLineSize() {
		return lineSize;
	}

	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "SplitPageHelper [currentPage=" + currentPage + ", lineSize=" + lineSize + ", column=" + column
				+ ", keyWord=" + keyWord + ", request=" + request + "]";
	}
	
}