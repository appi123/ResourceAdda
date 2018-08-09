package com.ojas.ra.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.ojas.ra.MongoDBClient;
import com.ojas.ra.dao.JobTypeDAO;
import com.ojas.ra.domain.JobType;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.service.JobTypeSevice;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public class JobTypeServiceImpl implements JobTypeSevice {

	@Autowired
	JobTypeDAO jobTypeDAO;
	@Autowired
	MongoDBClient mongoDBClient;

	Logger logger = Logger.getLogger(JobTypeServiceImpl.class);

	@Override
	public boolean createJobType(JobType jobType) {
		boolean b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			jobTypeDAO.setPojo(new JobType());
			jobTypeDAO.getCollection("jobType", db);
			b = jobTypeDAO.insert(jobType);
			logger.debug("jobType created..");
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
	public boolean updateJobType(JobType jobType) {
		boolean b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			jobTypeDAO.setPojo(new JobType());
			jobTypeDAO.getCollection("jobType", db);
			b = jobTypeDAO.save(jobType);
			logger.debug("JobType updated successfully..");
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
	public List<JobType> getAllJobType(MongoSortVO sort, int page, int size) {
		List<JobType> list;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			jobTypeDAO.getCollection("jobType", db);
			jobTypeDAO.setPojo(new JobType());
			try {
				list = jobTypeDAO.getAllObjects(sort, page, size);
				logger.debug("All Jobtypes get successfully..");
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
	public JobType getOneByCondition(Map<String, Object> condition) {
		JobType jobType;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			jobTypeDAO.setPojo(new JobType());
			jobTypeDAO.getCollection("jobType", db);
			try {
				jobType = jobTypeDAO.findOneByCondition(condition);
				logger.debug("one job get by condition..");
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
		return jobType;
	}

	public List<JobType> getAllJobType(MongoSortVO sort) {
		List<JobType> list;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			jobTypeDAO.getCollection("jobType", db);
			jobTypeDAO.setPojo(new JobType());
			try {
				list = jobTypeDAO.getAllObjects(sort);
				logger.debug("All jobTypes get successfully..");
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

}
