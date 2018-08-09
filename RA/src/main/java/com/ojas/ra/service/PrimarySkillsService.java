package com.ojas.ra.service;

import java.util.List;
import java.util.Map;

import com.ojas.ra.domain.PrimarySkills;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public interface PrimarySkillsService {
	/**
	 * 
	 * @param primarySkills
	 * @return
	 */
	boolean create(PrimarySkills primarySkills);

	/**
	 * 
	 * @param primarySkills
	 * @return
	 */
	boolean update(PrimarySkills primarySkills);

	/**
	 * 
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	List<PrimarySkills> getAll(MongoSortVO sort, int page, int size);

	/**
	 * 
	 * @param condition
	 * @return
	 */
	PrimarySkills getOneByCondition(Map<String, Object> condition);

	/**
	 * 
	 * @param sort
	 * @return
	 */
	List<PrimarySkills> getAll(MongoSortVO sort);

}
