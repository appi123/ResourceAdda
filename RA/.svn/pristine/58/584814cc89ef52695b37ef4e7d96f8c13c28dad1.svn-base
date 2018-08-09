package com.ojas.ra.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.ojas.ra.MongoDBClient;
import com.ojas.ra.dao.JobCategoryDAO;
import com.ojas.ra.domain.JobCategory;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.service.JobCategoryService;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public class JobCategoryServiceImpl implements JobCategoryService {
	@Autowired
	JobCategoryDAO jobCategoryDAO;
	@Autowired
	MongoDBClient mongoDBClient;
	Logger logger = Logger.getLogger(JobCategoryServiceImpl.class);

	@Override
	public boolean createJobCategory(JobCategory jobCategory) {
		boolean b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initialized..");
			jobCategoryDAO.setPojo(new JobCategory());
			jobCategoryDAO.getCollection("jobCategory", db);
			b = jobCategoryDAO.insert(jobCategory);
			logger.debug("Job category created..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return b;
	}

	@Override
	public boolean updateJobCategory(JobCategory jobCategory) {
		boolean b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			jobCategoryDAO.setPojo(new JobCategory());
			jobCategoryDAO.getCollection("jobCategory", db);
			b = jobCategoryDAO.save(jobCategory);
			logger.debug("Job category updated succesfully..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return b;
	}

	@Override
	public List<JobCategory> getAllJobCategory(MongoSortVO sort, int page, int size) {
		List<JobCategory> list;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase Initialized..");
			jobCategoryDAO.getCollection("jobCategory", db);
			jobCategoryDAO.setPojo(new JobCategory());
			try {
				list = jobCategoryDAO.getAllObjects(sort, page, size);
				logger.debug("All job categories get successfully..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return list;
	}

	@Override
	public JobCategory getOneByCondition(Map<String, Object> condition) {
		JobCategory jobCategory;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initilized..");
			jobCategoryDAO.setPojo(new JobCategory());
			jobCategoryDAO.getCollection("jobCategory", db);
			try {
				jobCategory = jobCategoryDAO.findOneByCondition(condition);
				logger.debug("Get job Category by condition..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return jobCategory;
	}

	public List<JobCategory> getAllJobCategory(MongoSortVO sort) {
		List<JobCategory> list = null;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initialized..");
			jobCategoryDAO.getCollection("jobCategory", db);
			jobCategoryDAO.setPojo(new JobCategory());
			try {
				list = jobCategoryDAO.getAllObjects(sort);
				logger.debug("All job categories get successfully..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return list;
	}

	@Override
	public int getCount(MongoSortVO sort) {
		int b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initialized..");
			jobCategoryDAO.setPojo(new JobCategory());
			jobCategoryDAO.getCollection("jobCategory", db);
			b = jobCategoryDAO.getCount(sort);
			logger.debug("count get successfully..");
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
			logger.debug("DataBase initialized..");
			jobCategoryDAO.setPojo(new JobCategory());
			jobCategoryDAO.getCollection("jobCategory", db);
			try {
				b = jobCategoryDAO.getPages(sort, pageSize);
				logger.debug("pages get successfully..");
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