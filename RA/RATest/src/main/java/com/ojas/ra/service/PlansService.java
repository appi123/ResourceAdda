package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.ojas.ra.domain.Plans;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface PlansService {
	/**
	 * 
	 * @param plans
	 * @return
	 * @throws RAException
	 */
	boolean createPlans(Plans plans) throws RAException;

	/**
	 * 
	 * @param plans
	 * @return
	 * @throws RAException
	 */
	boolean updatePlans(Plans plans) throws RAException;

	/**
	 * 
	 * @param ObjId
	 * @return
	 * @throws RAException
	 */
	boolean deletePlansById(ObjectId ObjId) throws RAException;

	/**
	 * 
	 * @param condition
	 * @return
	 * @throws RAException
	 */
	Plans getPlansByPrimaryId(Map<String, Object> condition) throws RAException;

	/**
	 * 
	 * @param condition
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	List<Plans> advancedFind(Map<String, MongoAdvancedQuery> condition, MongoSortVO sort, int page, int size);

	/**
	 * 
	 * @param condition
	 * @param target
	 * @return
	 */
	boolean updatePlans(Map<String, Object> condition, Map<String, Object> target);

	/**
	 * 
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	List<Plans> getAllPlans(MongoSortVO sort, int page, int size);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	int getCount(MongoSortVO sort);

	/**
	 * 
	 * @param sort
	 * @param pageSize
	 * @return
	 */
	int getPages(MongoSortVO sort, int pageSize);

}
