package com.ojas.ra.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.ojas.ra.MongoDBClient;
import com.ojas.ra.dao.RateTypeDAO;
import com.ojas.ra.domain.RateType;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.service.RateTypeService;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public class RateTypeServiceImpl implements RateTypeService {

	@Autowired
	RateTypeDAO rateTypeDAO;

	@Autowired
	private MongoDBClient mongoDBClient;

	Logger logger = Logger.getLogger(RateTypeServiceImpl.class);

	@Override
	public List<RateType> getAllObjects(MongoSortVO sort) {
		List<RateType> list;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			rateTypeDAO.getCollection("rateType", db);
			rateTypeDAO.setPojo(new RateType());
			try {
				list = rateTypeDAO.getAllObjects(sort);
				logger.debug("RateType List cteated..");
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
	public RateType findOneByCondition(Map<String, Object> condition) {
		RateType rateType = null;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			rateTypeDAO.setPojo(new RateType());
			rateTypeDAO.getCollection("companies", db);
			try {
				rateType = rateTypeDAO.findOneByCondition(condition);
				logger.debug("RateType found by condition..");
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
		return rateType;
	}

}
