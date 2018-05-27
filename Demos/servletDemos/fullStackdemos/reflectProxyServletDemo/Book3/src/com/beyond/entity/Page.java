package com.beyond.entity;

import java.util.List;

public class Page<T> {

	// ����
	private int currentPageNum; // ��Ҫ�鿴ҳ��ҳ��
	private int totalRecords; // ���ж�����
	private int pageSize = 10; // ÿҳ10��
	private List<T> contentList;

	// �����
	private int totalPageNum; // ��ҳ��
	private int prePageNum; // ��һҳ
	private int nextPageNum; // ��һҳ
	private int startIndex; // ��ҳ��ʼ����
	private int displayPageNum = 9; // ÿ����ʾ����ҳ������

	// ��չ
	private int displayStartPageNum; // ��ʾ�Ŀ�ʼҳ��
	private int displayEndPageNum;// ��ʾ�Ľ���ҳ��

	// ͨ������
	private String url;

	public Page(int currentPageNum, int totalRecords) {
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;

		// ��ҳ��
		totalPageNum = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;

		// ��һҳ
		if (currentPageNum - 1 < 1) {
			prePageNum = 1;
		} else {
			prePageNum = this.currentPageNum - 1;
		}

		// ��һҳ
		if (currentPageNum + 1 > totalPageNum) {
			nextPageNum = totalPageNum;
		} else {
			nextPageNum = this.currentPageNum + 1;
		}

		// ��ҳ��ʼ����
		startIndex = (currentPageNum - 1) * pageSize;

		// ��ʾ�Ŀ�ʼ�ͽ���ҳ��
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
