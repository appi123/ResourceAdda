package com.ojas.ra.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ojas.ra.domain.JobType;
import com.ojas.ra.exception.RAException;
import com.ojas.ra.mapper.JobTypeMapper;
import com.ojas.ra.response.Response;
import com.ojas.ra.service.JobTypeSevice;
import com.ojas.ra.util.MongoOrderByEnum;
import com.ojas.ra.util.MongoSortVO;

/**
 * 
 * @author skkhadar
 *
 */
@Component
@Path("/jobType")
public class JobTypeResource {

	@Autowired
	private JobTypeSevice jobTypeService;

	Logger logger = Logger.getLogger(JobTypeResource.class);

	/**
	 * 
	 * @param jobTypeMapper
	 * @return
	 */
	@Path("/createJobType")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createJobType(@RequestBody JobTypeMapper jobTypeMapper) {
		int count = 0;
		try {
			MongoSortVO sort = new MongoSortVO();
			sort.setPrimaryKey("_id");
			sort.setPrimaryOrderBy(MongoOrderByEnum.DESC);

			for (JobType jobType : jobTypeService.getAllJobType(sort)) {
				if (jobType.getJobType().equalsIgnoreCase(jobTypeMapper.getJobType()) == false) {
					count++;
				}
			}
			if (count == jobTypeService.getAllJobType(sort).size()) {
				JobType jobType = new JobType();
				BeanUtils.copyProperties(jobTypeMapper, jobType);
				Boolean result = jobTypeService.createJobType(jobType);
				if (result == true) {
					logger.debug("Job type created successfully");
					return new Response("success", result, HttpStatus.OK, "Job type created successfully");
				} else {
					logger.debug("Job type not created");
					return new Response("Failed", result, HttpStatus.CONFLICT, "Job type not created ");
				}
			}
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
		logger.debug("Job type not created");
		return new Response("Failed", HttpStatus.CONFLICT, "Job type not created");
	}

	/**
	 * 
	 * @param jobTypeMapper
	 * @param id
	 * @return
	 */
	@Path("/updateJobType/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateJobType(@RequestBody JobTypeMapper jobTypeMapper, @PathParam("id") ObjectId id) {
		try {
			JobType jobType = new JobType();
			BeanUtils.copyProperties(jobTypeMapper, jobType);
			jobType.set_id(id);
			Boolean result = jobTypeService.updateJobType(jobType);
			if (result == true) {
				logger.debug("JobType updated successfully for id " + id);
				return new Response("Success", result, HttpStatus.OK, "Success");
			} else {
				logger.debug("No records found for id " + id);
				return new Response("Failed ", result, HttpStatus.CONFLICT, "Failed ");
			}
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
	@Path("/listJobType/{pageNo}/{pageSize}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllJobType(@Context ServletContext context, @PathParam("pageNo") int pageNo,
			@PathParam("pageSize") int pageSize) {
		try {
			MongoSortVO sort = new MongoSortVO();
			sort.setPrimaryKey("_id");
			sort.setPrimaryOrderBy(MongoOrderByEnum.DESC);
			List<JobType> list;
			List<JobTypeMapper> result;
			try {
				list = jobTypeService.getAllJobType(sort, pageNo, pageSize);
				result = convertDomainToMapperList(list);
			} catch (RAException e) {
				logger.error(e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			if (result == null) {
				logger.debug("No records found");
				return new Response("Failed ", result, HttpStatus.CONFLICT, "No records found");
			} else {
				logger.debug("Records found successfully");
				return new Response("Success", result, HttpStatus.OK, "Records found");
			}
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param jobTypes
	 * @return
	 */
	public List<JobTypeMapper> convertDomainToMapperList(List<JobType> jobTypes) {
		try {
			List<JobTypeMapper> list = new ArrayList<JobTypeMapper>();
			for (JobType jobType : jobTypes) {
				JobTypeMapper jobTypeMapper = new JobTypeMapper();
				BeanUtils.copyProperties(jobType, jobTypeMapper);
				jobTypeMapper.set_id(jobType.get_id().toString());
				list.add(jobTypeMapper);
			}
			logger.debug("Converting jobTypeMapper to jobTypeMapperList");
			return list;
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
	@Path("/findOneByPrimaryId/{value}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findOneByPrimaryId(@Context ServletContext context, @PathParam("value") ObjectId value)
			throws JsonGenerationException, JsonMappingException, IOException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("_id", value);
			JobType jobType;
			JobTypeMapper result;
			try {
				jobType = jobTypeService.getOneByCondition(condition);
				result = convertDomainToMappar(jobType);
			} catch (RAException e) {
				logger.error("No records found " + e.getMessage());
				return new Response(HttpStatus.CONFLICT, "No records found");
			}
			if (result == null) {
				logger.debug("No records found");
				return new Response("Failed ", result, HttpStatus.CONFLICT, "No records found");
			}
			logger.debug("Records found successfully");
			return new Response("Success", result, HttpStatus.OK, "Records found");
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param jobType
	 * @return
	 */
	private JobTypeMapper convertDomainToMappar(JobType jobType) {
		try {
			JobTypeMapper jobTypeMapper = new JobTypeMapper();
			BeanUtils.copyProperties(jobType, jobTypeMapper);
			jobTypeMapper.set_id(jobType.get_id().toString());
			logger.debug("Converting jobType to jobTypeMapper");
			return jobTypeMapper;
		} catch (RAException e) {
			logger.error(e.getMessage());
			throw new RAException(e.getMessage());
		}
	}
}
