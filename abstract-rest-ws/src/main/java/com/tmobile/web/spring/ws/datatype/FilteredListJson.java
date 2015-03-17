package com.tmobile.web.spring.ws.datatype;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmobile.domain.common.JsonResponse;

import cz.tsystems.common.data.filter.FilteredList;

public class FilteredListJson<T extends JsonResponse> implements JsonResponse {

	public class ListMetadata {

		private long fullSize;
		private long pageSize;
		private long pageCount = 1;
		private long page;

		public ListMetadata(FilteredList<T> filter) {
			this.fullSize = filter.getFullDataSize();
			List<T> data = filter.getData();
			if (data != null) {
				this.pageSize = data.size();
			}
			if(filter.isPagingEnabled()) {
				this.pageCount = filter.getPageCount();
			}
			this.page = filter.getCurrentPage();
		}

		public long getFullSize() {
			return fullSize;
		}

		public void setFullSize(long fullSize) {
			this.fullSize = fullSize;
		}

		public long getPageSize() {
			return pageSize;
		}

		public void setPageSize(long pageSize) {
			this.pageSize = pageSize;
		}

		public long getPageCount() {
			return pageCount;
		}

		public void setPageCount(long pageCount) {
			this.pageCount = pageCount;
		}

		public void setPage(long page) {
			this.page = page;
		}

		public long getPage() {
			return page;
		}

	}

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private FilteredList<T> filter;

	public FilteredListJson(FilteredList<T> filter) {
		super();
		this.filter = filter;
	}

	@JsonProperty("data")
	public List<T> getData() {
		return this.filter.getData();
	}

	@JsonProperty("metadata")
	public ListMetadata getMetadata() {
		return new ListMetadata(this.filter);
	}
}
