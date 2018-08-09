package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import com.ojas.ra.domain.JobCategory;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface JobCategoryService {
	/**
	 * 
	 * @param jobCategory
	 * @return
	 */
	boolean createJobCategory(JobCategory jobCategory);

	/**
	 * 
	 * @param jobCategory
	 * @return
	 */
	boolean updateJobCategory(JobCategory jobCategory);

	/**
	 * 
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	List<JobCategory> getAllJobCategory(MongoSortVO sort, int page, int size);

	/**
	 * 
	 * @param condition
	 * @return
	 */
	JobCategory getOneByCondition(Map<String, Object> condition);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	List<JobCategory> getAllJobCategory(MongoSortVO sort);

	/**
	 * 
	 * @param sort
	 * @param pageSize
	 * @return
	 */
	int getPages(MongoSortVO sort, int pageSize);

	int getCount(MongoSortVO sort);

}
