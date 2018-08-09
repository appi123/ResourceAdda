package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.ojas.ra.domain.Requirement;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface RequirementService {
	/**
	 * 
	 * @param requirement
	 * @return
	 * @throws RAException
	 */
	boolean saveRequirement(Requirement requirement) throws RAException;

	/**
	 * 
	 * @param requirement
	 * @return
	 * @throws RAException
	 */
	boolean deleteRequirement(ObjectId requirement) throws RAException;

	/**
	 * 
	 * @param value
	 * @return
	 * @throws RAException
	 */
	boolean removeByPrimaryId(String value) throws RAException;

	/**
	 * 
	 * @param condition
	 * @param sort
	 * @return
	 * @throws RAException
	 */
	List<Requirement> findAllByCondition(Map<String, Object> condition, MongoSortVO sort) throws RAException;

	/**
	 * 
	 * @param condition
	 * @return
	 * @throws RAException
	 */
	Requirement findOneByCondition(Map<String, Object> condition) throws RAException;

	/**
	 * 
	 * @param value
	 * @return
	 * @throws RAException
	 */
	Requirement findOneByPrimaryId(String value) throws RAException;

	/**
	 * 
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 * @throws RAException
	 */
	List<Requirement> getAllObjects(MongoSortVO sort, int page, int size) throws RAException;

	/**
	 * 
	 * @param condition
	 * @param target
	 * @return
	 */
	boolean updateMapByCondition(Map<String, Object> condition, Map<String, Object> target);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	List<Requirement> getAllObjects(MongoSortVO sort);

	/**
	 * 
	 * @param requirement
	 * @return
	 */
	boolean createRequirement(Requirement requirement);

	/**
	 * 
	 * @param nameOftheProperty
	 * @param valueOftheProperty
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Requirement> advancedFindByCondition(String nameOftheProperty, String valueOftheProperty, MongoSortVO sort,
			int pageNo, int pageSize);

	/**
	 * 
	 * @param requementMappingcondition
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Requirement> advancedFindByCondition(Map<String, MongoAdvancedQuery> requementMappingcondition,
			MongoSortVO sort, int pageNo, int pageSize);

	/**
	 * 
	 * @param requirement
	 * @return
	 * @throws RAException
	 */
	boolean post(Requirement requirement) throws RAException;

	/**
	 * 
	 * @param requirementMappingcondition
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Requirement> findByCondition(Map<String, Object> requirementMappingcondition, MongoSortVO sort, int pageNo,
			int pageSize);

	/**
	 * 
	 * @param primarySkills
	 * @param jobCategory
	 * @param location
	 * @param experience
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Requirement> advancedFindByConditions(String primarySkills, String jobCategory, String location,
			String experience, MongoSortVO sort, int pageNo, int pageSize);

	/**
	 * 
	 * @param string
	 * @param jobCategory
	 * @param location
	 * @param experience
	 * @param vendors
	 * @param budget
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Requirement> advancedFindByConditions(String string, String jobCategory, String location, String experience,
			String vendors, String budget, MongoSortVO sort, int pageNo, int pageSize);

	/**
	 * 
	 * @param sort
	 * @param pageSize
	 * @return
	 */
	int getPages(MongoSortVO sort, int pageSize);

	/**
	 * 
	 * @param nameOftheProperty
	 * @param string
	 * @param sort
	 * @return
	 */
	List<Requirement> advancedFindByCondition(String nameOftheProperty, String string, MongoSortVO sort);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	int getCount(MongoSortVO sort);

	/**
	 * 
	 * @param andQuery
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Requirement> findAllByCondition(BasicDBObject andQuery, MongoSortVO sort, int pageNo, int pageSize);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	Map<String, Integer> findTopCustomers(MongoSortVO sort);

}
