package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import com.ojas.ra.domain.JobType;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface JobTypeSevice {
	/**
	 * 
	 * @param jobType
	 * @return
	 */
	boolean createJobType(JobType jobType);

	/**
	 * 
	 * @param jobtype
	 * @return
	 */
	boolean updateJobType(JobType jobtype);

	/**
	 * 
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	List<JobType> getAllJobType(MongoSortVO sort, int page, int size);

	/**
	 * 
	 * @param condition
	 * @return
	 */
	JobType getOneByCondition(Map<String, Object> condition);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	List<JobType> getAllJobType(MongoSortVO sort);

}
