package com.beyond.entity;

import java.util.List;

public class Page<T> {

	// 必有
	private int currentPageNum; // 需要查看页的页码
	private int totalRecords; // 共有多少条
	private int pageSize = 10; // 每页10条
	private List<T> contentList;

	// 需计算
	private int totalPageNum; // 总页数
	private int prePageNum; // 上一页
	private int nextPageNum; // 下一页
	private int startIndex; // 本页开始索引
	private int displayPageNum = 9; // 每次显示多少页的链接

	// 扩展
	private int displayStartPageNum; // 显示的开始页码
	private int displayEndPageNum;// 显示的结束页码

	// 通用属性
	private String url;

	public Page(int currentPageNum, int totalRecords) {
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;

		// 总页数
		totalPageNum = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;

		// 上一页
		if (currentPageNum - 1 < 1) {
			prePageNum = 1;
		} else {
			prePageNum = this.currentPageNum - 1;
		}

		// 下一页
		if (currentPageNum + 1 > totalPageNum) {
			nextPageNum = totalPageNum;
		} else {
			nextPageNum = this.currentPageNum + 1;
		}

		// 本页开始索引
		startIndex = (currentPageNum - 1) * pageSize;

		// 显示的开始和结束页码
		if (totalPageNum >= 9) {

			if (currentPageNum - 4 >= 1 && currentPageNum + 4 <= totalPageNum) {
				displayStartPageNum = currentPageNum - 4;
				displayEndPageNum = currentPageNum + 4;
			} else if (currentPageNum - 4 < 1) {
				displayStartPageNum = 1;
				displayEndPageNum = 9;
			} else {
				displayStartPageNum = totalPageNum - 8;
				displayEndPageNum = totalPageNum;
			}

		} else {
			displayStartPageNum = 1;
			displayEndPageNum = totalPageNum;
		}

	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getContentList() {
		return contentList;
	}

	public void setContentList(List<T> pageContentList) {
		this.contentList = pageContentList;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getPrePageNum() {
		return prePageNum;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getDisplayStartPageNum() {
		return displayStartPageNum;
	}

	public void setDisplayStartPageNum(int displayStartPageNum) {
		this.displayStartPageNum = displayStartPageNum;
	}

	public int getDisplayEndPageNum() {
		return displayEndPageNum;
	}

	public void setDisplayEndPageNum(int displayEndPageNum) {
		this.displayEndPageNum = displayEndPageNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
