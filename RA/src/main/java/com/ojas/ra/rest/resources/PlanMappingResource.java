package com.ojas.ra.rest.resources;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
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

import com.ojas.ra.domain.PlanMapping;
import com.ojas.ra.domain.Plans;
import com.ojas.ra.domain.Registration;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.mapper.PlanMappingMapper;
import com.ojas.ra.mapper.PlansMapper;
import com.ojas.ra.response.Response;
import com.ojas.ra.service.PlanMappingService;
import com.ojas.ra.service.PlansService;
import com.ojas.ra.service.RegistrationService;

/**
 * 
 * @author skkhadar
 *
 */
@Component
@Path("/planMapping")
public class PlanMappingResource {

	@Autowired
	private PlanMappingService planMappingService;
	@Autowired
	private PlansService planService;
	@Autowired
	private RegistrationService registrationService;

	Logger logger = Logger.getLogger(PlanMappingResource.class);

	/**
	 * 
	 * @param planMappingMapper
	 * @return
	 */
	@POST
	@Path("/createPlanMapping")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRoleMapping(@RequestBody PlanMappingMapper planMappingMapper) {
		try {
			PlanMapping planMapping = new PlanMapping();
			BeanUtils.copyProperties(planMappingMapper, planMapping);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("registrationId", planMapping.getRegistrationId());
			PlanMapping pm;
			try {
				pm = planMappingService.findOneByCondition(condition);
			} catch (RAException e) {
				logger.error("Plan not created " + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "Plan not created");
			}
			if (null == pm) {
				planMapping.setStatus("Active");
				logger.debug("creating Plan");
				planMappingService.create(planMapping);
			} else {
				pm.setStatus("InActive");
				planMappingService.updatePlanMapping(pm);
				logger.debug("creating Plan");
				planMappingService.create(planMapping);
			}
			Boolean result = planMappingService.create(planMapping);
			if (result == false) {
				logger.debug("Plan not created");
				return new Response("Failed", result, HttpStatus.CONFLICT, "plan not created");
			}
			logger.debug("Plan created successfully");
			return new Response("Success", result, HttpStatus.OK, "plan created");

		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param plansMapper
	 * @param id
	 * @return
	 */
	@Path("/updatePlanMapping/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePlans(@RequestBody PlanMappingMapper plansMapper, @PathParam("id") ObjectId id) {
		try {
			PlanMapping planMapping = new PlanMapping();
			BeanUtils.copyProperties(plansMapper, planMapping);
			planMapping.set_id(id);
			Boolean result = planMappingService.updatePlanMapping(planMapping);
			if (result == false) {
				logger.debug("Plan not updated for id " + id);
				return new Response("Failed", result, HttpStatus.CONFLICT, "plan not saved");
			}
			logger.debug("Plan updated for id " + id);
			return new Response("Success", result, HttpStatus.OK, "plan saved");

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
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Path("/changePlan/{id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changePlan(@Context ServletContext context, @PathParam("id") ObjectId id)
			throws JsonGenerationException, JsonMappingException, IOException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("planId", id);
			PlanMapping pm;
			try {
				pm = planMappingService.findOneByCondition(condition);
			} catch (RAException e) {
				logger.error("plan not changed " + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "plan not changed");
			}
			pm.setStatus("InActive");
			Boolean result = planMappingService.updatePlanMapping(pm);
			if (result == false) {
				logger.debug("plan not changed for id " + id);
				return new Response("Failed", result, HttpStatus.CONFLICT, "plan not changed");
			}
			logger.debug("plan changed successfully for id " + id);
			return new Response("Success", result, HttpStatus.OK, "plan changed");

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
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Path("/findPlanMapping/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findOneByPrimaryId(@Context ServletContext context, @PathParam("id") String id) {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("_id", id);
			PlanMapping planMapping;
			PlanMappingMapper mapperList;
			try {
				planMapping = planMappingService.findOneByCondition(condition);
				mapperList = convertDomainToMappar(planMapping);
			} catch (RAException e) {
				logger.error("No records found " + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			if (mapperList == null) {
				logger.debug("No records found for id " + id);
				return new Response("Failed", mapperList, HttpStatus.CONFLICT, "No record found");
			}
			logger.debug("Records found for id " + id);
			return new Response("Success", mapperList, HttpStatus.OK, "record found");
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
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Path("/findPlanMappingByRegId/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPlanMappingByRegId(@Context ServletContext context, @PathParam("id") String id) {
		Plans plan = null;
		PlansMapper mapperList = null;
		Long noOfDays = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("registrationId", new ObjectId(id));
		try {
			PlanMapping planMapping;
			try {
				planMapping = planMappingService.findOneByCondition(condition);
			} catch (RAException e) {
				logger.error("No records found " + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			ObjectId planId = null;
			if (null != planMapping) {
				planId = planMapping.getPlan_id();
				Map<String, Object> condition1 = new HashMap<String, Object>();
				condition1.put("_id", planId);
				try {
					plan = planService.getPlansByPrimaryId(condition1);
				} catch (RAException e) {
					logger.error("No records found for id " + id);
					return new Response(HttpStatus.CONFLICT, "No records found");
				}
				// finding no of days remaining
				Map<String, Object> condition2 = new HashMap<String, Object>();
				condition2.put("_id", new ObjectId(id));
				Registration registration;
				try {
					registration = registrationService.findOneByCondition(condition2);
				} catch (RAException e) {
					logger.error("No records found for id " + id);
					return new Response(HttpStatus.CONFLICT, "No records found");
				}
				Calendar calender = Calendar.getInstance();
				Date date1 = calender.getTime();
				// Date date1 = registration.getRegisteredDate();
				Date date2 = registration.getExpiryDate();
				registration.getExpiryDate();
				noOfDays = date2.getTime() - date1.getTime();
				noOfDays = TimeUnit.DAYS.convert(noOfDays, TimeUnit.MILLISECONDS);
				if (null != plan) {
					mapperList = convertDomainToMapparPlan(plan);
				}
				if (mapperList == null) {
					logger.debug("No records found for id " + id);
					return new Response("Failed", mapperList, HttpStatus.CONFLICT, "No record found", noOfDays);
				}
			}
		} catch (RAException Exception) {
			logger.debug("No records found for id " + id);
			return new Response("Failed", mapperList, HttpStatus.CONFLICT, "No record found", noOfDays);
		}
		logger.debug("Records found for id " + id);
		return new Response("Success", mapperList, HttpStatus.OK, "Found", noOfDays);
	}

	/**
	 * 
	 * @param plans
	 * @return
	 */
	private PlansMapper convertDomainToMapparPlan(Plans plans) {
		try {
			PlansMapper plansMapper = new PlansMapper();
			BeanUtils.copyProperties(plans, plansMapper);
			plansMapper.set_id(plans.get_id().toString());
			logger.debug("Converting Plans to PlansMapper");
			return plansMapper;
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param planMapping
	 * @return
	 */
	private PlanMappingMapper convertDomainToMappar(PlanMapping planMapping) {
		try {
			PlanMappingMapper planMappingMapper = new PlanMappingMapper();
			BeanUtils.copyProperties(planMapping, planMappingMapper);
			planMappingMapper.set_id(planMapping.get_id().toString());
			logger.debug("Converting PlanMapping to PlanMappingMapper");
			return planMappingMapper;
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}
}
