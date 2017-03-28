package com.ikohoo.domain;

public class Page {
	private int curPage;
	private int rowPerPage;
	private int rowＣount;
	private int pageCount;
	private int firstpage;
	private int lastpage;
	private int prepage;
	private int nextpage;
	//private List<Cust>list;
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int thispage) {
		this.curPage = thispage;
	}
	public int getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(int rowperpage) {
		this.rowPerPage = rowperpage;
	}
	public int getRowCount() {
		return rowＣount;
	}
	public void setRowCount(int countrow) {
		this.rowＣount = countrow;
	}
	public int getPageCount() {
		return rowＣount/rowPerPage + (rowＣount % rowPerPage==0?0:1);
	}
//	public void setPageCount(int countpage) {
//		this.pageCount = countpage;
//	}
	public int getFirstpage() {
		return 1;
	}
//	public void setFirstpage(int firstpage) {
//		this.firstpage = firstpage;
//	}
	public int getLastpage() {
		return getPageCount();
	}
//	public void setLastpage(int lastpage) {
//		this.lastpage = lastpage;
//	}
	public int getPrepage() {
		return curPage==1?1:curPage-1;
	}
//	public void setPrepage(int prepage) {
//		this.prepage = prepage;
//	}
	public int getNextpage() {
		int pc = getPageCount();
		return curPage==pc?pc:curPage+1;
	}
//	public void setNextpage(int nextpage) {
//		this.nextpage = nextpage;
//	}
	@Override
	public String toString() {
		return "Page [thispage=" + curPage + ", rowPerPage=" + rowPerPage
				+ ", rowＣount=" + rowＣount + ", pageCount=" + pageCount
				+ ", firstpage=" + firstpage + ", lastpage=" + lastpage
				+ ", prepage=" + prepage + ", nextpage=" + nextpage + "]";
	}
	
}
