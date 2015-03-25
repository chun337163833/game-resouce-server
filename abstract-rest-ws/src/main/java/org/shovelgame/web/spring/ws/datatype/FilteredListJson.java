package org.shovelgame.web.spring.ws.datatype;

import java.util.List;

import org.shovelgame.common.data.filter.FilteredList;
import org.shovelgame.domain.common.JsonObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FilteredListJson implements JsonObject {

	public class ListMetadata {

		private long fullSize;
		private long pageSize;
		private long pageCount = 1;
		private long page;
		public ListMetadata(FilteredList<?> filter, List<?> data) {
			this.fullSize = filter.getFullDataSize();
			if (data != null) {
				this.pageSize = data.size();
			}
			if(filter.isPagingEnabled()) {
				this.pageCount = filter.getPageCount();
			}
			this.page = filter.getCurrentPage();
		}
		
		public ListMetadata(FilteredList<?> filter) {
			this(filter, filter.getData());
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
	private FilteredList<?> filter;
	
	@JsonIgnore
	private List<?> data;
	
	public FilteredListJson(FilteredList<?> filter) {
		this(filter, filter.getData());
	}
	public FilteredListJson(FilteredList<?> filter, List<?> data) {
		super();
		this.filter = filter;
		this.data = data;
	}

	@JsonProperty("data")
	public List<?> getData() {
		return this.data;
	}

	@JsonProperty("metadata")
	public ListMetadata getMetadata() {
		return new ListMetadata(this.filter);
	}
}
