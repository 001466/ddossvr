package com.ec.ddossvr.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ec.common.controller.BaseController;
import com.ec.common.model.Response;
import com.ec.ddossvr.dao.CommandSettingDao;
import com.ec.ddossvr.model.CommandSetting;
@RestController
@RequestMapping("/admin/command/setting")
public class CommandSettingController  extends BaseController{

	
	@Autowired
	CommandSettingDao commandSettingDao;
	
	@RequestMapping(path="/get", produces = { "application/json" }, consumes = { "application/json" })
	public Response<CommandSetting> get1( HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return get2( request, response);
	}

	@RequestMapping(path="/get")
	public Response<CommandSetting> get2( HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Response<CommandSetting> res= new Response<CommandSetting>(Response.Code.SUCCESS.getValue());
		CommandSetting data=commandSettingDao.get(); 
		res.setData(data);
		return res;
	}
	
	
	
	@RequestMapping(path="/upd",produces = { "application/json" }, consumes = { "application/json" })
	public Response<CommandSetting> post1(@RequestBody CommandSetting extEntity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return post2(extEntity, request, response);
	}
	

	@RequestMapping(path="/upd")
	public Response<CommandSetting> post2(@ModelAttribute CommandSetting commandSetting, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		commandSettingDao.upd(commandSetting);
		return new Response<CommandSetting>(Response.Code.SUCCESS.getValue());
	}
	
	 
	
}
