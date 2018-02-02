package com.ec.ddossvr.dao;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ec.common.util.PropertiesUtil;
import com.ec.ddossvr.model.CommandSetting;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class CommandSettingDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandSettingDao.class);

	public static final String EXT_SETTING 		= "command-setting";

	private static  PropertiesUtil properties = null;
	
	private static  CommandSetting setting = new CommandSetting();

	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	@PostConstruct
	private void postConstruct() throws IOException {
		properties = new PropertiesUtil("config/command-setting.properties");
		if (StringUtils.isEmpty(properties.get(EXT_SETTING))) {
			try {
				upd(setting);
			} catch (JsonProcessingException e) {
				LOGGER.error(e.getMessage(),e);
			}
		}
	}


	public CommandSetting get() throws JsonParseException, JsonMappingException, IOException {
		if (setting == null) {
			String str = properties.get(EXT_SETTING);
			if (StringUtils.isEmpty(str))
				return null;
			setting = mapper.readValue(str,CommandSetting.class);
		}
		return setting;

	}


	
	public CommandSetting upd(CommandSetting setting) throws JsonProcessingException, IOException {
		String json = mapper.writeValueAsString(setting);
		properties.put(EXT_SETTING, json);
		this.setting = setting;
		return this.setting;
	}

}
