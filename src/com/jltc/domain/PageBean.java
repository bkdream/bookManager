package com.jltc.domain;

import java.util.List;

public class PageBean<T> {
	private Integer curpage;//	当前页数-curpage
	private Integer pagesize;//	每页显示数据条数-pagesize
	private Integer totaldata;//总记录数totaldata
	private Integer totalpage;//	总页数-totalpage->totalRecord/pagesize
	private List<T> list; //	当前页数据集合-list(ArrayList)
	private String url;
	public PageBean() {

	}

	public PageBean(Integer curpage, Integer pagesize, Integer totaldata, Integer totalpage, List<T> list,
					String url) {
		this.curpage = curpage;
		this.pagesize = pagesize;
		this.totaldata = totaldata;
		this.totalpage = totalpage;
		this.list = list;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCurpage() {
		return curpage;
	}

	public void setCurpage(Integer curpage) {
		this.curpage = curpage;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getTotaldata() {
		return totaldata;
	}

	public void setTotaldata(Integer totaldata) {
		this.totaldata = totaldata;
	}

	public Integer getTotalpage() {
		return totalpage;
	}

	public void setTotalpage() {
		if(totaldata%pagesize==0){
			this.totalpage=totaldata/pagesize;
		}else{
			this.totalpage=totaldata/pagesize+1;
		} 
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "PageBean{" +
				"curpage=" + curpage +
				", pagesize=" + pagesize +
				", totaldata=" + totaldata +
				", totalpage=" + totalpage +
				", list=" + list +
				", url='" + url + '\'' +
				'}';
	}
}
