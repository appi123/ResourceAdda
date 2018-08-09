package com.ojas.ra.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.ojas.ra.MongoDBClient;
import com.ojas.ra.dao.ProposedResourceDAO;
import com.ojas.ra.domain.ProposedResource;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.service.ProposedResourceService;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public class ProposedResourceServiceeImpl implements ProposedResourceService {

	@Autowired
	private ProposedResourceDAO proposedResourceDAO;
	@Autowired
	private MongoDBClient mongoDBClient;

	Logger logger = Logger.getLogger(ProposedResourceServiceeImpl.class);

	@Override
	public boolean create(ProposedResource proposedResource) {
		boolean b;
		try {
			DB db = initializeDB();
			logger.debug("Database initialized..");
			proposedResourceDAO.setPojo(new ProposedResource());
			proposedResourceDAO.getCollection("proposedResource", db);
			b = proposedResourceDAO.insert(proposedResource);
			logger.debug("ProposedResources created..");
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
	public List<ProposedResource> advancedFindByCondition(Map<String, MongoAdvancedQuery> condition, MongoSortVO sort,
			int pageNo, int pageSize) {
		List<ProposedResource> list = null;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			proposedResourceDAO.setPojo(new ProposedResource());
			proposedResourceDAO.getCollection("proposedResource", db);
			try {
				list = proposedResourceDAO.advancedFindByCondition(condition, sort, pageNo, pageSize);
				logger.debug("ProposedResources List created..");
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
		proposedResourceDAO.setPojo(new ProposedResource());
		return db;

	}

	@Override
	public int getPages(MongoSortVO sort, int pageSize) {
		int pages;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			proposedResourceDAO.setPojo(new ProposedResource());
			proposedResourceDAO.getCollection("proposedResource", db);
			try {
				pages = proposedResourceDAO.getPages(sort, pageSize);
				logger.debug("Pages get successfully..");
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
		return pages;
	}
}
