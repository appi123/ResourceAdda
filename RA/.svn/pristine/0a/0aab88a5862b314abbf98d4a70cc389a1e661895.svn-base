package com.ojas.ra.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.ojas.ra.MongoDBClient;
import com.ojas.ra.dao.MenuDAO;
import com.ojas.ra.domain.Menu;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.service.MenuService;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuDAO menuDAO;
	@Autowired
	MongoDBClient mongoDBClient;

	Logger logger = Logger.getLogger(MenuServiceImpl.class);

	@Override
	public boolean createMenu(Menu menu) {
		boolean b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			menuDAO.setPojo(new Menu());
			menuDAO.getCollection("menu", db);
			b = menuDAO.insert(menu);
			logger.debug("Menu created..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return b;
	}

	@Override
	public boolean updateMenu(Menu menu) {
		boolean b;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			menuDAO.setPojo(new Menu());
			menuDAO.getCollection("menu", db);
			b = menuDAO.save(menu);
			logger.debug("Menu updated..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return b;
	}

	@Override
	public List<Menu> listMenu(MongoSortVO sort, int page, int size) {
		List<Menu> list;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			menuDAO.setPojo(new Menu());
			menuDAO.getCollection("menu", db);
			try {
				list = menuDAO.getAllObjects(sort, page, size);
				logger.debug("ListMent created..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return list;
	}

	@Override
	public Menu getOneByCondition(Map<String, Object> condition) {
		Menu menu;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			menuDAO.setPojo(new Menu());
			menuDAO.getCollection("menu", db);
			try {
				menu = menuDAO.findOneByCondition(condition);
				logger.debug("Get One MenuById..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return menu;
	}

	@Override
	public boolean deleteMenuById(ObjectId id) {
		boolean b;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("_id", id);
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			menuDAO.setPojo(new Menu());
			menuDAO.getCollection("menu", db);
			b = menuDAO.removeByCondition(condition);
			logger.debug("Menu deleted by Id..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection closed..");
		} catch (RAException e) {
			mongoDBClient.closeMongoClient();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		return b;
	}

	@Override
	public List<Menu> findAllByConditon(Map<String, MongoAdvancedQuery> condition, MongoSortVO sort, int pageNo,
			int pageSize) {
		List<Menu> list = null;
		{
			try {
				DB db = mongoDBClient.getReadMongoDB();
				logger.debug("Database initialized..");
				menuDAO.setPojo(new Menu());
				menuDAO.getCollection("menu", db);
				try {
					list = menuDAO.advancedFindByCondition(condition, sort, pageNo, pageSize);
					logger.debug("All menu FindByCondition..");
				} catch (RAException e) {
					mongoDBClient.closeMongoClient();
					logger.error("connection closed..");
					logger.error(e.getMessage());
					throw new RAException("Data Not Found !!");
				}
				mongoDBClient.closeMongoClient();
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException(e.getMessage());
			}
		}
		return list;
	}

	@Override
	public List<Menu> findAllByRoleId(Map<String, MongoAdvancedQuery> condition, MongoSortVO sort, int pageNo,
			int pageSize) {
		List<Menu> list = null;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			menuDAO.setPojo(new Menu());
			menuDAO.getCollection("menu", db);
			try {
				list = menuDAO.advancedFindByCondition(condition, sort, pageNo, pageSize);
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection closed..");
		} catch (RAException e) {
			e.getMessage();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			mongoDBClient.closeMongoClient();
		}
		return list;
	}

	@Override
	public int getPages(MongoSortVO sort, int pageSize) {
		int pages = 0;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			menuDAO.setPojo(new Menu());
			menuDAO.getCollection("menu", db);
			try {
				pages = menuDAO.getPages(sort, pageSize);
				logger.debug("pages get successfully..");
			} catch (RAException e) {
				mongoDBClient.closeMongoClient();
				logger.error("connection closed..");
				logger.error(e.getMessage());
				throw new RAException("Data Not Found !!");
			}
			mongoDBClient.closeMongoClient();
			logger.debug("Connection closed..");
		} catch (RAException e) {
			e.getMessage();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			mongoDBClient.closeMongoClient();
		}
		return pages;
	}

	@Override
	public int getCount(MongoSortVO sort) {
		int count = 0;
		try {
			DB db = mongoDBClient.getReadMongoDB();
			logger.debug("Database initialized..");
			menuDAO.setPojo(new Menu());
			menuDAO.getCollection("menu", db);
			count = menuDAO.getCount(sort);
			logger.debug("count get successfully..");
			mongoDBClient.closeMongoClient();
			logger.debug("Connection closed..");
		} catch (RAException e) {
			e.getMessage();
			logger.error("connection closed..");
			logger.error(e.getMessage());
			mongoDBClient.closeMongoClient();
		}
		return count;
	}
}
