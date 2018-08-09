package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.ojas.ra.domain.Menu;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface MenuService {
	/**
	 * 
	 * @param menu
	 * @return
	 */
	boolean createMenu(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	boolean updateMenu(Menu menu);

	/**
	 * 
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	List<Menu> listMenu(MongoSortVO sort, int page, int size);

	/**
	 * 
	 * @param condition
	 * @return
	 */
	Menu getOneByCondition(Map<String, Object> condition);

	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteMenuById(ObjectId id);

	/**
	 * 
	 * @param condition
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Menu> findAllByRoleId(Map<String, MongoAdvancedQuery> condition, MongoSortVO sort, int pageNo, int pageSize);

	/**
	 * 
	 * @param condition
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */

	List<Menu> findAllByConditon(Map<String, MongoAdvancedQuery> condition, MongoSortVO sort, int pageNo, int pageSize);

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
