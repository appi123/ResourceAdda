package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import com.ojas.ra.domain.JoinWithIn;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface JoinWithInService {
	/**
	 * 
	 * @param joinWithIn
	 * @return
	 */
	boolean createJoinWithIn(JoinWithIn joinWithIn);

	/**
	 * 
	 * @param joinWithIn
	 * @return
	 */
	boolean updateJoinWithIn(JoinWithIn joinWithIn);

	/**
	 * 
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	List<JoinWithIn> getAllJoinWithIn(MongoSortVO sort, int page, int size);

	/**
	 * 
	 * @param condition
	 * @return
	 */
	JoinWithIn getOneByCondition(Map<String, Object> condition);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	List<JoinWithIn> getAllJoinWithIn(MongoSortVO sort);

	/**
	 * 
	 * @param sort
	 * @param pageSize
	 * @return
	 */
	int getPages(MongoSortVO sort, int pageSize);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	int getCount(MongoSortVO sort);

}
