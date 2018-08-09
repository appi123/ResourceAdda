package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.ojas.ra.domain.Address;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface AddressService {

	/**
	 * 
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Address> getAllObjects(MongoSortVO sort, int pageNo, int pageSize);

	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean removeByPrimaryId(String id);

	/**
	 * 
	 * @param condition
	 * @return
	 */
	Address findOneByCondition(Map<String, Object> condition);

	/**
	 * 
	 * @param addressMappingcondition
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Address> advancedFindByCondition(Map<String, MongoAdvancedQuery> addressMappingcondition, MongoSortVO sort,
			int pageNo, int pageSize);

	/**
	 * 
	 * @param value
	 * @return
	 * @throws RAException
	 */
	Address findOneByPrimaryId(String value) throws RAException;

	/**
	 * 
	 * @param condition
	 * @param sort
	 * @return
	 * @throws RAException
	 */
	List<Address> findAllByCondition(Map<String, Object> condition, MongoSortVO sort) throws RAException;

	/**
	 * 
	 * @param objId
	 * @return
	 * @throws RAException
	 */
	boolean deleteAddress(ObjectId objId) throws RAException;

	/**
	 * 
	 * @param address
	 * @return
	 * @throws RAException
	 */
	boolean saveAddress(Address address) throws RAException;

	/**
	 * 
	 * @param address
	 * @return
	 * @throws RAException
	 */
	boolean createAddress(Address address) throws RAException;

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
