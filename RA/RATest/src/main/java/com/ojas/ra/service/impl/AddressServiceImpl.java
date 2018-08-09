package com.ojas.ra.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.ojas.ra.MongoDBClient;
import com.ojas.ra.dao.AddressDAO;
import com.ojas.ra.domain.Address;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.service.AddressService;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoSortVO;
import com.ojas.ra.util.MongoUtil;

/**
 * 
 * @author skkhadar
 *
 */
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressDAO addressDAO;

	@Autowired
	private MongoDBClient mongoDBClient;
	Logger logger = Logger.getLogger(AddressServiceImpl.class);

	@Override
	public boolean createAddress(Address address) throws RAException {
		boolean b;
		try {
			DB db = initializeDB();
			logger.debug("Database initialized..");
			addressDAO.setPojo(new Address());
			logger.debug("Collection created..");
			addressDAO.getCollection("address", db);
			b = addressDAO.insert(address);
			logger.debug("address created..");
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
	public boolean saveAddress(Address address) throws RAException {
		boolean b;
		try {
			DB db = initializeDB();
			logger.debug("DataBase initialized..");
			addressDAO.getCollection("address", db);
			logger.debug("Collection Created..");
			addressDAO.setPojo(new Address());
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("_id", address.get_id());
			b = addressDAO.updateMapByCondition(condition, MongoUtil.getObjectByDBObject(address));
			logger.debug("address updated..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("Connection Closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return b;
	}

	@Override
	public boolean deleteAddress(ObjectId objId) throws RAException {
		boolean b;
		try {
			DB db = initializeDB();
			logger.debug("DataBase initialized..");
			addressDAO.getCollection("address", db);
			logger.debug("Collection Created..");
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("_id", objId);
			b = addressDAO.removeByCondition(condition);
			logger.debug("Address removed..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("Connection Closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return b;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Address> findAllByCondition(Map<String, Object> condition, MongoSortVO sort) throws RAException {
		List<Address> list;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initialized..");
			addressDAO.setPojo(new Address());
			addressDAO.getCollection("address", db);
			try {
				list = addressDAO.findAllByCondition(condition, sort);
				logger.debug("Getting Complete information by condition..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("Connection Closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
			return list;
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("Connection Closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	@Override
	public boolean removeByPrimaryId(String value) throws RAException {
		boolean b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initialized..");
			addressDAO.setPojo(new Address());
			addressDAO.getCollection("address", db);
			b = addressDAO.removeByPrimaryId(value);
			logger.debug("Removing address by primaryId..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("Conncetion Closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return b;
	}

	@Override
	public Address findOneByCondition(Map<String, Object> condition) throws RAException {
		Address address;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initialized..");
			addressDAO.setPojo(new Address());
			addressDAO.getCollection("address", db);
			try {
				address = addressDAO.findOneByCondition(condition);
				logger.debug("Finding Single Address by condition..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("Connection Closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Conncetion Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.debug("Conncetion Closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return address;
	}

	@Override
	public Address findOneByPrimaryId(String value) throws RAException {
		Address address;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initialized..");
			addressDAO.setPojo(new Address());
			addressDAO.getCollection("address", db);
			try {
				address = addressDAO.findOneByPrimaryId(value);
				logger.debug("Finding Single Address by primaryId..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("Connection Closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("Connection Closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return address;
	}

	@Override
	public List<Address> getAllObjects(MongoSortVO sort, int page, int size) throws RAException {
		List<Address> list = null;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initialized..");
			addressDAO.getCollection("address", db);
			addressDAO.setPojo(new Address());
			try {
				list = addressDAO.getAllObjects(sort, page, size);
				logger.debug("All Objects retrieved successfully..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("Connection Closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("Connection Closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return list;
	}

	private DB initializeDB() {
		DB db = mongoDBClient.getReadMongoDB();
		logger.debug("DataBase initialized..");
		addressDAO.setPojo(new Address());
		return db;

	}

	@Override
	public List<Address> advancedFindByCondition(Map<String, MongoAdvancedQuery> requementMappingcondition,
			MongoSortVO sort, int pageNo, int pageSize) {
		List<Address> list = null;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initialized..");
			addressDAO.setPojo(new Address());
			addressDAO.getCollection("address", db);
			try {
				list = addressDAO.advancedFindByCondition(requementMappingcondition, sort, pageNo, pageSize);
				logger.debug("Advanced find by condition successfully..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("Connection Closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("Connection Closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return list;
	}

	@Override
	public boolean updateMapByCondition(Map<String, Object> condition, Map<String, Object> target) {
		boolean b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("DataBase initialized..");
			addressDAO.setPojo(new Address());
			addressDAO.getCollection("address", db);
			b = addressDAO.updateMapByCondition(condition, target);
			logger.debug("Map updated by Condition Successfully..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("Connection Closed..");
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
			addressDAO.setPojo(new Address());
			addressDAO.getCollection("address", db);
			try {
				b = addressDAO.getPages(sort, pageSize);
				logger.debug("Pages get Successfully..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("Connection Closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("Connection Closed..");
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
			logger.debug("DataBase initialized");
			addressDAO.setPojo(new Address());
			addressDAO.getCollection("address", db);

			b = addressDAO.getCount(sort);
			logger.debug("Count get successfully..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection Closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("Connection Closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return b;
	}
}
