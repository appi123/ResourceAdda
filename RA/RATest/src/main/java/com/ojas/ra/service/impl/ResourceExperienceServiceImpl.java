package com.ojas.ra.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.ojas.ra.MongoDBClient;
import com.ojas.ra.dao.ResourceExperienceDAO;
import com.ojas.ra.domain.ResourceExperience;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.service.ResourceExperienceService;

/**
 * 
 * @author skkhadar
 *
 */
public class ResourceExperienceServiceImpl implements ResourceExperienceService {

	@Autowired
	private ResourceExperienceDAO resourceExperienceDAO;

	@Autowired
	private MongoDBClient mongoDBClient;

	Logger logger = Logger.getLogger(ResourceExperienceServiceImpl.class);

	@Override
	public boolean create(ResourceExperience res) {
		boolean b;
		try {
			DB db = initializeDB();
			logger.debug("Database initialized..");
			resourceExperienceDAO.setPojo(new ResourceExperience());
			resourceExperienceDAO.getCollection("resourceExperience", db);
			b = resourceExperienceDAO.insert(res);
			logger.debug("ResourceExperience created..");
			mongoDBClient.closeMongoClient();
			logger.debug("connection closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return b;
	}

	private DB initializeDB() {
		DB db = mongoDBClient.getReadMongoDB();
		logger.debug("Database initialized..");
		resourceExperienceDAO.setPojo(new ResourceExperience());
		return db;
	}
}
