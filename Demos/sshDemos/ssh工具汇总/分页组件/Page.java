package com.beyond.utils;

import java.util.List;

import com.beyond.f.F;

public class Page<T> {
	private Long totalRecords;
	private Integer recordsPerPage;
	private Integer currentPageNum;
	private List<T> contentList;

	private Integer nextPageNum;
	private Integer prePageNum;
	private Integer displayFirstPageNum;
	private Integer displayLastPageNum;
	private Integer maxDisplayPageCount;

	private Integer startRecordIndex;
	private Integer endRecordIndex;

	private Integer totalPageCount;

	private String uri;

	@Deprecated
	public Page(Integer currentPageNum) {
		this(currentPageNum, 0L, F.DEFAULT_RECORDS_PER_PAGE, F.DEFAULT_RECORDS_PER_PAGE);
	}

	public Page(Integer currentPageNum, Long totalRecords) {
		this(currentPageNum, totalRecords, F.DEFAULT_RECORDS_PER_PAGE, F.DEFAULT_RECORDS_PER_PAGE);
	}

	public Page(Integer currentPageNum, Long totalRecords, Integer recordsPerPage, Integer maxDisplayPageCount) {
		this.totalRecords = totalRecords;
		this.currentPageNum = currentPageNum;
		this.recordsPerPage = recordsPerPage;
		this.maxDisplayPageCount = maxDisplayPageCount;

		totalPageCount = (int) Math.ceil(totalRecords / recordsPerPage) + 1;

		if (currentPageNum == totalPageCount) {
			nextPageNum = currentPageNum;
		} else {
			nextPageNum = currentPageNum + 1;
		}

		if (currentPageNum == 1) {
			prePageNum = currentPageNum;
		} else {
			prePageNum = currentPageNum - 1;
		}

		// displayFirstPageNum displayLastPageNum
		if (totalPageCount <= maxDisplayPageCount) {
			displayFirstPageNum = 1;
			displayLastPageNum = totalPageCount;
		} else {

			if (currentPageNum - maxDisplayPageCount / 2 < 1) {
				displayFirstPageNum = 1;
				displayLastPageNum = maxDisplayPageCount;
			} else if (currentPageNum + maxDisplayPageCount / 2 > totalPageCount) {
				displayFirstPageNum = totalPageCount - maxDisplayPageCount + 1;
				displayLastPageNum = totalPageCount;
			} else {

				displayFirstPageNum = (int) Math.floor(currentPageNum - maxDisplayPageCount / 2);
				displayLastPageNum = (int) Math.ceil(currentPageNum + maxDisplayPageCount / 2);
			}

		}

		if (currentPageNum != totalPageCount) {
			startRecordIndex = (currentPageNum - 1) * recordsPerPage;
			endRecordIndex = startRecordIndex + recordsPerPage - 1;
		} else {
			startRecordIndex = (currentPageNum - 1) * recordsPerPage;
			endRecordIndex = (int) (totalRecords - 1);
		}

	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Integer getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(Integer recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public Integer getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public List<T> getContentList() {
		return contentList;
	}

	public void setContentList(List<T> contentList) {
		this.contentList = contentList;
	}

	public Integer getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(Integer nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public Integer getPrePageNum() {
		return prePageNum;
	}

	public void setPrePageNum(Integer prePageNum) {
		this.prePageNum = prePageNum;
	}

	public Integer getDisplayFirstPageNum() {
		return displayFirstPageNum;
	}

	public void setDisplayFirstPageNum(Integer displayFirstPageNum) {
		this.displayFirstPageNum = displayFirstPageNum;
	}

	public Integer getDisplayLastPageNum() {
		return displayLastPageNum;
	}

	public void setDisplayLastPageNum(Integer displayLastPageNum) {
		this.displayLastPageNum = displayLastPageNum;
	}

	public Integer getStartRecordIndex() {
		return startRecordIndex;
	}

	public void setStartRecordIndex(Integer startRecordIndex) {
		this.startRecordIndex = startRecordIndex;
	}

	public Integer getEndRecordIndex() {
		return endRecordIndex;
	}

	public void setEndRecordIndex(Integer endRecordIndex) {
		this.endRecordIndex = endRecordIndex;
	}

	public Integer getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getMaxDisplayPageCount() {
		return maxDisplayPageCount;
	}

	public void setMaxDisplayPageCount(Integer maxDisplayPageCount) {
		this.maxDisplayPageCount = maxDisplayPageCount;
	}

}
