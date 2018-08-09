package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.ojas.ra.domain.AccessToken;
import com.ojas.ra.domain.Role;
import com.ojas.ra.domain.UserAccount;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface UserService {
	/**
	 * 
	 * @param role
	 * @return
	 * @throws RAException
	 */
	Role getRole(String role) throws RAException;

	/**
	 * 
	 * @param user
	 * @return
	 * @throws RAException
	 */
	boolean create(UserAccount user) throws RAException;

	/**
	 * 
	 * @param user
	 * @throws RAException
	 */
	void save(UserAccount user) throws RAException;

	/**
	 * 
	 * @param username
	 * @return
	 * @throws RAException
	 */
	UserAccount getByUsername(String username) throws RAException;

	/**
	 * 
	 * @param user
	 * @return
	 * @throws RAException
	 */
	AccessToken createAccessToken(UserAccount user) throws RAException;

	/**
	 * 
	 * @param arg0
	 * @return
	 * @throws RAException
	 */
	UserAccount loadUserByUsername(String arg0) throws RAException;

	/**
	 * 
	 * @param arg0
	 * @return
	 * @throws RAException
	 */
	UserAccount getUserByToken(String arg0) throws RAException;

	/**
	 * 
	 * @param condition
	 * @return
	 */
	UserAccount findOneByCondition(Map<String, Object> condition);

	/**
	 * 
	 * @param condition
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws RAException
	 */
	List<UserAccount> findByRegistrationId(Map<String, MongoAdvancedQuery> condition, MongoSortVO sort, int pageNo,
			int pageSize) throws RAException;

	/**
	 * 
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<UserAccount> getAllObjects(MongoSortVO sort, int pageNo, int pageSize);

	/**
	 * 
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Role> getAllRoles(MongoSortVO sort, int pageNo, int pageSize);

	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(ObjectId id);

	/**
	 * 
	 * @param condition1
	 * @param target1
	 * @return
	 */
	boolean updateMapByCondition(Map<String, Object> condition1, Map<String, Object> target1);

	/**
	 * 
	 * @param requirementMappingcondition
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<UserAccount> advancedFindByCondition(Map<String, MongoAdvancedQuery> requirementMappingcondition,
			MongoSortVO sort, int pageNo, int pageSize);

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
