package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.ojas.ra.domain.ResourceMapping;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface ResourceMappingService {
	/**
	 * 
	 * @param rm
	 * @return
	 */
	boolean createResourceMapping(ResourceMapping rm);

	/**
	 * 
	 * @param objId
	 * @return
	 */
	boolean deleteResourceMapping(ObjectId objId);

	/**
	 * 
	 * @param rm
	 * @return
	 */
	boolean updateResourceMapping(ResourceMapping rm);

	/**
	 * 
	 * @param condition
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	List<ResourceMapping> advancedFind(Map<String, MongoAdvancedQuery> condition, MongoSortVO sort, int page, int size);

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
