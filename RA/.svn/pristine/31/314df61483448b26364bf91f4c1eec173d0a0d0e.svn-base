package com.ojas.ra.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.ojas.ra.MongoDBClient;
import com.ojas.ra.dao.PlansDAO;
import com.ojas.ra.domain.Plans;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.service.PlansService;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoSortVO;
import com.ojas.ra.util.MongoUtil;

/**
 * 
 * @author skkhadar
 *
 */
public class PlansServiceImpl implements PlansService {

	@Autowired
	PlansDAO plansDAO;

	@Autowired
	private MongoDBClient mongoDBClient;

	Logger logger = Logger.getLogger(PlansServiceImpl.class);

	@Override
	public boolean createPlans(Plans plans) throws RAException {
		boolean b;
		try {
			DB db = initializeDB();
			logger.debug("Database initialized");
			plansDAO.setPojo(new Plans());
			plansDAO.getCollection("plans", db);
			b = plansDAO.insert(plans);
			logger.debug("Plan created..");
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean updatePlans(Plans plans) throws RAException {
		boolean b;
		try {

			DB db = initializeDB();
			logger.debug("Database initialized..");
			plansDAO.getCollection("plans", db);
			plansDAO.setPojo(new Plans());
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("_id", plans.get_id());
			b = plansDAO.updateMapByCondition(condition, MongoUtil.getObjectByDBObject(plans));
			logger.debug("Plan updated..");
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

	@Override
	public boolean deletePlansById(ObjectId ObjId) throws RAException {
		boolean b;
		try {

			DB db = initializeDB();
			logger.debug("Database initialized..");
			plansDAO.getCollection("plans", db);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("_id", ObjId);
			b = plansDAO.removeByCondition(condition);
			logger.debug("Plan deleted by Id..");
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

	@Override
	public Plans getPlansByPrimaryId(Map<String, Object> condition) throws RAException {
		Plans plans;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			plansDAO.setPojo(new Plans());
			plansDAO.getCollection("plans", db);
			try {
				plans = plansDAO.findOneByCondition(condition);
				logger.debug("Plan get by PrimaryId..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("connection closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return plans;
	}

	@Override
	public List<Plans> advancedFind(Map<String, MongoAdvancedQuery> condition, MongoSortVO sort, int page, int size) {
		List<Plans> list;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			plansDAO.getCollection("plans", db);
			plansDAO.setPojo(new Plans());
			try {
				list = plansDAO.advancedFindByCondition(condition, sort, page, size);
				logger.debug("Plan List created inadvance..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("connection closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return list;
	}

	private DB initializeDB() {
		DB db = mongoDBClient.getReadMongoDB();
		logger.debug("Database initialized..");
		plansDAO.setPojo(new Plans());
		return db;

	}

	@Override
	public List<Plans> getAllPlans(MongoSortVO sort, int page, int size) {
		List<Plans> list;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			plansDAO.getCollection("plans", db);
			plansDAO.setPojo(new Plans());
			try {
				list = plansDAO.getAllObjects(sort, page, size);
				logger.debug("Plan List created..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("connection closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return list;
	}

	@Override
	public boolean updatePlans(Map<String, Object> condition, Map<String, Object> target) {
		boolean b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			plansDAO.setPojo(new Plans());
			plansDAO.getCollection("plans", db);
			b = plansDAO.updateMapByCondition(condition, target);
			logger.debug("Plans updated..");
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

	@Override
	public int getCount(MongoSortVO sort) {
		int b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			plansDAO.setPojo(new Plans());
			plansDAO.getCollection("plans", db);
			b = plansDAO.getCount(sort);
			logger.debug("Plans Counted successfully..");
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

	@Override
	public int getPages(MongoSortVO sort, int pageSize) {
		int b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			plansDAO.setPojo(new Plans());
			plansDAO.getCollection("plans", db);
			try {
				b = plansDAO.getPages(sort, pageSize);
				logger.debug("Plans Pages counted..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
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
}
