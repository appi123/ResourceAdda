package com.ojas.ra.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.ojas.ra.MongoDBClient;
import com.ojas.ra.dao.JoinWithInDAO;
import com.ojas.ra.domain.JoinWithIn;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.service.JoinWithInService;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public class JoinWithInServiceImpl implements JoinWithInService {
	@Autowired
	JoinWithInDAO joinWithInDAO;
	@Autowired
	MongoDBClient mongoDBClient;

	Logger logger = Logger.getLogger(JoinWithInServiceImpl.class);

	@Override
	public boolean createJoinWithIn(JoinWithIn joinWithIn) {
		boolean b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			joinWithInDAO.setPojo(new JoinWithIn());
			joinWithInDAO.getCollection("joinWithIn", db);
			b = joinWithInDAO.insert(joinWithIn);
			logger.debug("joinWithIn created..");
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
	public boolean updateJoinWithIn(JoinWithIn joinWithIn) {
		boolean b;
		try {

			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			joinWithInDAO.setPojo(new JoinWithIn());
			joinWithInDAO.getCollection("joinWithIn", db);
			b = joinWithInDAO.save(joinWithIn);
			logger.debug("JoinWithIn updated..");
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
	public List<JoinWithIn> getAllJoinWithIn(MongoSortVO sort, int page, int size) {
		List<JoinWithIn> list;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			joinWithInDAO.getCollection("joinWithIn", db);
			joinWithInDAO.setPojo(new JoinWithIn());
			try {
				list = joinWithInDAO.getAllObjects(sort, page, size);
				logger.debug("All JoinWithIn get successfully..");
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
	public JoinWithIn getOneByCondition(Map<String, Object> condition) {
		JoinWithIn joinWithIn;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			joinWithInDAO.setPojo(new JoinWithIn());
			joinWithInDAO.getCollection("joinWithIn", db);
			try {
				joinWithIn = joinWithInDAO.findOneByCondition(condition);
				logger.debug("OneJoinWithIn get by condition..");
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
		return joinWithIn;
	}

	public List<JoinWithIn> getAllJoinWithIn(MongoSortVO sort) {
		List<JoinWithIn> list;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			joinWithInDAO.getCollection("joinWithIn", db);
			joinWithInDAO.setPojo(new JoinWithIn());
			try {
				list = joinWithInDAO.getAllObjects(sort);
				logger.debug("All JoinWithIn get successfully..");
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
	public int getCount(MongoSortVO sort) {
		int b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			joinWithInDAO.setPojo(new JoinWithIn());
			joinWithInDAO.getCollection("joinWithIn", db);
			b = joinWithInDAO.getCount(sort);
			logger.debug("JoinWithIn Count get successfully..");
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
			joinWithInDAO.setPojo(new JoinWithIn());
			joinWithInDAO.getCollection("joinWithIn", db);
			try {
				b = joinWithInDAO.getPages(sort, pageSize);
				logger.debug("JoinWithIn Pages get successfully..");
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
