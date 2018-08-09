package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import com.ojas.ra.domain.SecondarySkills;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface SecondatySkillsService {
	/**
	 * 
	 * @param secondarySkills
	 * @return
	 */
	boolean create(SecondarySkills secondarySkills);

	/**
	 * 
	 * @param secondarySkills
	 * @return
	 */
	boolean update(SecondarySkills secondarySkills);

	/**
	 * 
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	List<SecondarySkills> getAll(MongoSortVO sort, int page, int size);

	/**
	 * 
	 * @param condition
	 * @return
	 */
	SecondarySkills getOneByCondition(Map<String, Object> condition);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	List<SecondarySkills> getAll(MongoSortVO sort);

}
