package org.shovelgame.common.data.filter.store;

import java.util.List;

public interface StoredFilterService {

	/**
	 * Nacte filtr dle ID
	 * 
	 * @param filterId
	 * @return
	 */
	List<StoredFilter> findAll();

	/**
	 * Nacte filtr dle ID
	 * 
	 * @param filterId
	 * @return
	 */
	StoredFilter findById(Long filterId);

	/**
	 * Ulozi filtr.
	 * 
	 * @param storedFilter
	 * @return ID ulozeneho filtru
	 */
	Long save(StoredFilter storedFilter);

	void remove(StoredFilter filter);

}
