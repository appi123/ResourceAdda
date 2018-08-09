package com.ojas.ra.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.ojas.ra.domain.AccessToken;
import com.ojas.ra.domain.Role;
import com.ojas.ra.domain.RoleMapping;
import com.ojas.ra.domain.UserAccount;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.mapper.RoleMapper;
import com.ojas.ra.response.Response;
import com.ojas.ra.service.RegistrationService;
import com.ojas.ra.service.RoleMappingService;
import com.ojas.ra.service.RoleService;
import com.ojas.ra.service.UserService;
import com.ojas.ra.transfer.UserTransfer;
import com.ojas.ra.util.MongoAdvancedQuery;
import com.ojas.ra.util.MongoEqualQuery;
import com.ojas.ra.util.MongoOrderByEnum;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author msunil
 *
 */
@Component
@Path("/user")
public class UserResource {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleMappingService roleMappingService;

	@Autowired
	private RoleService roleService;
	@Autowired
	RegistrationService registrationService;
	Logger logger = Logger.getLogger(UserResource.class);

	/**
	 * Retrieves the currently logged in user.
	 *
	 * @return A transfer containing the username and the roles.
	 */
	@Path("/userDetails/{userName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@Context ServletContext context, @PathParam("userName") String userName) {
		UserTransfer userTransfer = null;
		try {
			UserAccount userDetails;
			List<RoleMapping> mapings;
			try {
				logger.debug("getting the user details by username " + userName);
				userDetails = userService.loadUserByUsername(userName.trim());
				mapings = roleMappingService.findOneRoleMappingByUserId(userDetails.get_id());
			} catch (RAException e) {
				logger.error("No records found " + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			List<Role> roles = new ArrayList<Role>();
			userTransfer = new UserTransfer();
			BeanUtils.copyProperties(userDetails, userTransfer);

			for (RoleMapping map : mapings) {
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("_id", map.getRole_id());
				Role role;
				try {
					role = roleService.findOneByCondition(condition);
				} catch (RAException e) {
					logger.error("No records found for userName " + userName);
					return new Response(HttpStatus.CONFLICT, "No records found");
				}
				roles.add(role);
			}
			userTransfer.setRoles(this.createRoleMap(roles));
			if (null != userDetails.getRegistrationId()) {
				userTransfer.setRegistrationId(userDetails.getRegistrationId().toString());
			}
			userTransfer.setRegistrationType(userDetails.getRegistrationType());
			logger.debug("records found successfully " + userDetails);
			return new Response(userTransfer, HttpStatus.OK, "records found");
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * Authenticates a user and creates an access token.
	 *
	 * @param username
	 *            The name of the user.
	 * @param password
	 *            The password of the user.
	 * @return The generated access token.
	 */
	@Path("/authenticate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(@FormParam("username") String username, @FormParam("password") String password) {
		try {
			UserAccount acc;
			AccessToken accessToken;
			try {
				logger.debug("authenticating the user by username " + username);
				acc = userService.loadUserByUsername(username);
				logger.debug("creating the access token for the " + username);
				accessToken = this.userService.createAccessToken(acc);
			} catch (RAException e) {
				logger.error("No records found " + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			if (accessToken == null) {
				logger.debug("authentication failed");
				return new Response(accessToken, HttpStatus.CONFLICT, "failed");
			}
			logger.debug("authentication success");
			return new Response(accessToken, HttpStatus.OK, "success");
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param userRole
	 * @return
	 */
	private Map<String, String> createRoleMap(List<Role> userRole) {
		try {
			Map<String, String> roles = new HashMap<String, String>();

			for (Role authority : userRole) {
				roles.put(authority.getRoleName(), authority.getRoleName());
			}
			return roles;
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param userTransfer
	 * @return
	 */
	@POST
	@Path("/saveUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(@RequestBody UserTransfer userTransfer) {
		boolean b = true;
		try {
			if (null == userService.loadUserByUsername(userTransfer.getUsername())) {
				int pageNo = 1;
				int pageSize = 10;
				MongoEqualQuery equal = new MongoEqualQuery();
				equal.setEqualto(new ObjectId(userTransfer.getRegistrationId()));
				Map<String, MongoAdvancedQuery> resourceMappingcondition = new HashMap<String, MongoAdvancedQuery>();
				resourceMappingcondition.put("registrationId", equal);

				MongoSortVO sort = new MongoSortVO();
				sort.setPrimaryKey("registrationId");
				sort.setPrimaryOrderBy(MongoOrderByEnum.DESC);
				try {
					userService.advancedFindByCondition(resourceMappingcondition, sort, pageNo, pageSize);
				} catch (RAException e) {
					logger.error("No records found" + e.getMessage());
					return new Response(HttpStatus.CONFLICT, "No records found");
				}
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("_id", new ObjectId(userTransfer.getRegistrationId()));
				try {
					registrationService.findOneByCondition(condition);
				} catch (RAException e) {
					logger.error("No records found" + e.getMessage());
					return new Response(HttpStatus.CONFLICT, "No records found");
				}

				try {
					UserAccount user = new UserAccount();
					BeanUtils.copyProperties(userTransfer, user);
					user.setRegistrationId(new ObjectId(userTransfer.getRegistrationId()));
					user.setStatus("Active");
					userService.create(user);
					if (null != userTransfer.getRoleMapper() && !userTransfer.getRoleMapper().isEmpty()) {
						UserAccount userAccount;
						try {
							userAccount = userService.loadUserByUsername(user.getUsername());
						} catch (RAException e) {
							logger.error("No records found" + e.getMessage());
							return new Response(HttpStatus.CONFLICT, "No records found");
						}
						for (RoleMapper map : userTransfer.getRoleMapper()) {
							RoleMapping mapping = new RoleMapping();
							mapping.setUser_id(userAccount.get_id());
							mapping.setRole_id(new ObjectId(map.get_id()));
							roleMappingService.createRoleMapping(mapping);
						}
					}

				} catch (RAException e) {
					logger.error("No records found" + e.getMessage());
					return new Response(HttpStatus.CONFLICT, "No records found");
				}

				/*
				 * } else { return new Response(HttpStatus.CONFLICT,
				 * "Your Licences are completed..."); }
				 */
			} else {
				logger.debug("UserName already exists...");
				return new Response(HttpStatus.CONFLICT, "UserName already exists...");
			}
			if (b == false) {
				logger.debug("user not saved");
				return new Response(b, HttpStatus.CONFLICT, "failure");
			}
			logger.debug("user successsfully inserted");
			return new Response(b, HttpStatus.OK, "sucess");
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param userTransfer
	 * @param id
	 * @return
	 */
	@PUT
	@Path("/updateUser/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@RequestBody UserTransfer userTransfer, @PathParam("id") ObjectId id) {
		try {

			UserAccount userAccount = new UserAccount();
			BeanUtils.copyProperties(userTransfer, userAccount);
			userAccount.set_id(id);
			userAccount.setRegistrationId(new ObjectId(userTransfer.getRegistrationId()));
			userService.save(userAccount);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("user_id", id);
			roleMappingService.removeByCondition(condition);
			for (RoleMapper map : userTransfer.getRoleMapper()) {
				RoleMapping mapping = new RoleMapping();
				mapping.setUser_id(userAccount.get_id());
				mapping.setRole_id(new ObjectId(map.get_id()));
				roleMappingService.createRoleMapping(mapping);
			}
			logger.debug("user updated successfully for ID " + id);
			return new Response(true, HttpStatus.OK, "sucess");

		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param context
	 * @param value
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	@GET
	@Path("/getOneByPrimaryKey/{value}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOneByPrimaryId(@Context ServletContext context, @PathParam("value") ObjectId value)
			throws JsonGenerationException, JsonMappingException, IOException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("_id", value);
			UserAccount user;
			List<RoleMapping> mapping;
			UserTransfer userTransfer;
			try {
				user = userService.findOneByCondition(condition);
				mapping = roleMappingService.findOneRoleMappingByUserId(user.get_id());
				userTransfer = convertDomainToMapper(user);
			} catch (RAException e) {
				logger.error("No records found" + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			List<RoleMapper> list = new ArrayList<RoleMapper>();
			for (RoleMapping mpps : mapping) {
				RoleMapper mapper = new RoleMapper();
				Map<String, Object> rolecondition = new HashMap<String, Object>();
				rolecondition.put("_id", mpps.getRole_id());
				Role role;
				try {
					role = roleService.findOneByCondition(rolecondition);
				} catch (RAException e) {
					logger.error("No records found" + e.getMessage());
					return new Response(HttpStatus.CONFLICT, "No records found");
				}
				mapper.set_id(role.get_id().toString());
				mapper.setRoleName(role.getRoleName());
				list.add(mapper);
			}
			userTransfer.setRoleMapper(list);
			if (userTransfer == null) {
				logger.debug("No records found");
				new Response(userTransfer, HttpStatus.NOT_FOUND, "not found");
			}
			logger.debug("records found successfully");
			return new Response(userTransfer, HttpStatus.OK, "sucess");
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param context
	 * @param objectId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path("/findAllByRegistrationId/{regId}/{pageNo}/{pageSize}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllByRegistrationId(@Context ServletContext context, @PathParam("regId") String objectId,
			@PathParam("pageNo") int pageNo, @PathParam("pageSize") int pageSize) {
		try {
			MongoEqualQuery equal = new MongoEqualQuery();
			equal.setEqualto(new ObjectId(objectId));
			Map<String, MongoAdvancedQuery> userMappingcondition = new HashMap<String, MongoAdvancedQuery>();
			userMappingcondition.put("registrationId", equal);
			MongoSortVO sort = new MongoSortVO();
			sort.setPrimaryKey("registrationId");
			sort.setPrimaryOrderBy(MongoOrderByEnum.DESC);
			List<UserTransfer> userTransfer;
			List<UserAccount> listUser;
			try {
				listUser = userService.findByRegistrationId(userMappingcondition, sort, pageNo, pageSize);
				userTransfer = convertDomainToMapperList(listUser);
			} catch (RAException e) {
				logger.error("No records found" + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			int count = userService.getCount(sort);
			int pages = userService.getPages(sort, pageSize);
			if (userTransfer == null || userTransfer.size() == 0) {
				logger.debug("No records found");
				return new Response(userTransfer, pages, count, HttpStatus.NOT_FOUND, "No records found");
			}
			logger.debug("Records found successfully");
			return new Response(userTransfer, pages, count, HttpStatus.OK, "records found");
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param context
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Path("/listUsers/{pageNo}/{pageSize}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAllByCondition(@Context ServletContext context, @PathParam("pageNo") int pageNo,
			@PathParam("pageSize") int pageSize) {
		try {
			MongoSortVO sort = new MongoSortVO();
			sort.setPrimaryKey("_id");
			sort.setPrimaryOrderBy(MongoOrderByEnum.DESC);
			List<UserAccount> list;
			List<UserTransfer> userTransfer;
			try {
				list = userService.getAllObjects(sort, pageNo, pageSize);
				userTransfer = convertDomainToMapperList(list);
			} catch (RAException e) {
				logger.error("No records found " + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			int count = userService.getCount(sort);
			int pages = userService.getPages(sort, pageSize);
			if (userTransfer == null || userTransfer.size() == 0) {
				logger.debug("No records found");
				return new Response(userTransfer, pages, count, HttpStatus.NOT_FOUND, "No records found");
			}
			logger.debug("Records found successfully");
			return new Response(userTransfer, pages, count, HttpStatus.OK, "records found");
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param context
	 * @param id
	 * @return
	 */
	@Path("/deleteUserById/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserById(@Context ServletContext context, @PathParam("id") ObjectId id) {
		try {
			boolean result = userService.delete(id);
			if (result == false) {
				logger.debug("No records found for id " + id);
				return new Response(result, HttpStatus.CONFLICT, "failed");
			}
			logger.debug("Records deleted for id " + id);
			return new Response(result, HttpStatus.OK, "success");
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param context
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Path("/getAllRoles/{pageNo}/{pageSize}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRoles(@Context ServletContext context, @PathParam("pageNo") int pageNo,
			@PathParam("pageSize") int pageSize) {

		try {
			MongoSortVO sort = new MongoSortVO();
			sort.setPrimaryKey("_id");
			sort.setPrimaryOrderBy(MongoOrderByEnum.DESC);
			List<Role> list;
			List<RoleMapper> roleMapper;
			try {
				list = userService.getAllRoles(sort, pageNo, pageSize);
				roleMapper = convertToListRoleMapper(list);
			} catch (RAException e) {
				logger.error("No records found " + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			int count = userService.getCount(sort);
			int pages = userService.getPages(sort, pageSize);
			if (roleMapper == null || roleMapper.size() == 0) {
				logger.debug("No records found");
				return new Response(roleMapper, pages, count, HttpStatus.NOT_FOUND, "No records found");
			}
			logger.debug("Records found successfully");
			return new Response(roleMapper, pages, count, HttpStatus.OK, "records found");
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param context
	 * @param nameOftheProperty
	 * @param valueOftheProperty
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Path("/findOneAllByCondition/{nameOftheProperty}/{valueOftheProperty}/{pageNo}/{pageSize}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findOneAllByCondition(@Context ServletContext context,
			@PathParam("nameOftheProperty") String nameOftheProperty,
			@PathParam("valueOftheProperty") String valueOftheProperty, @PathParam("pageNo") int pageNo,
			@PathParam("pageSize") int pageSize) {
		try {
			MongoEqualQuery equal = new MongoEqualQuery();

			equal.setEqualto(valueOftheProperty);
			Map<String, MongoAdvancedQuery> requirementMappingcondition = new HashMap<String, MongoAdvancedQuery>();
			requirementMappingcondition.put(nameOftheProperty, equal);
			MongoSortVO sort = new MongoSortVO();
			sort.setPrimaryKey("_id");
			sort.setPrimaryOrderBy(MongoOrderByEnum.DESC);
			List<UserTransfer> lists;
			List<UserAccount> requirementlist;
			try {
				requirementlist = userService.advancedFindByCondition(requirementMappingcondition, sort, pageNo,
						pageSize);
				lists = convertDomainToMapperList(requirementlist);
				logger.debug("Records found..");
			} catch (RAException e) {
				logger.error("No records found " + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			int count = userService.getCount(sort);
			int pages = userService.getPages(sort, pageSize);
			if (lists == null || lists.size() == 0) {
				logger.debug("No records found");
				return new Response(lists, pages, count, HttpStatus.NOT_FOUND, "No records found");
			}
			logger.debug("Records found successfully");
			return new Response(lists, pages, count, HttpStatus.OK, "records found");
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param userList
	 * @return
	 */
	private List<UserTransfer> convertDomainToMapperList(List<UserAccount> userList) {
		try {
			List<UserTransfer> transferList = new ArrayList<UserTransfer>();
			if (null != userList) {
				for (UserAccount user : userList) {
					UserTransfer userTransfer = new UserTransfer();
					BeanUtils.copyProperties(user, userTransfer);
					userTransfer.set_id(user.get_id().toString());
					if (null != user.getRegistrationId()) {
						userTransfer.setRegistrationId(user.getRegistrationId().toString());
					}
					transferList.add(userTransfer);
				}
			}
			logger.debug("Converting UserAccountList to UserTransferList");
			return transferList;
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	private UserTransfer convertDomainToMapper(UserAccount user) {
		try {
			UserTransfer userTransfer = new UserTransfer();
			BeanUtils.copyProperties(user, userTransfer);
			userTransfer.setRegistrationId(user.getRegistrationId().toString());
			userTransfer.set_id(user.get_id().toString());
			logger.debug("Converting UserAccount to UserTransfer");
			return userTransfer;
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param roles
	 * @return
	 */
	private List<RoleMapper> convertToListRoleMapper(List<Role> roles) {
		try {
			List<RoleMapper> listMapper = new ArrayList<RoleMapper>();
			for (Role role : roles) {
				RoleMapper map = new RoleMapper();
				BeanUtils.copyProperties(role, map);
				map.set_id(role.get_id().toString());
				listMapper.add(map);
			}
			logger.debug("Converting RoleList to RoleMapperList");
			return listMapper;
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}
}
