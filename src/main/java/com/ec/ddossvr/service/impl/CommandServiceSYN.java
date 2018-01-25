package com.ec.ddossvr.service.impl;

import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.common.model.BaseEntity;
import com.ec.common.utils.ObjectUtils;
import com.ec.ddossvr.dao.CommandSettingDao;
import com.ec.ddossvr.model.Command;
import com.ec.ddossvr.service.CommandService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
@Service("CommandServiceSYN")
public class CommandServiceSYN extends CommandService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandServiceSYN.class);

	@Autowired
	private CommandSettingDao commandSettingDao;
	
	@Override
	public void command(Command get,Writer writer) {
		
		String getStr = get.toString();
		
		SYN syn=new SYN();
		
		try {
			
			ObjectUtils.copyProperties(get, commandSettingDao.get());
			ObjectUtils.copyProperties(syn, commandSettingDao.get());
			get.setAttackInfo(syn);
			
			System.out.println("Reader:" + getStr);
			System.out.println("Writer:" + get.toString());
			writer.write(get.toString());
			
			
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();LOGGER.error(e.getMessage(),e);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();LOGGER.error(e.getMessage(),e);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();LOGGER.error(e.getMessage(),e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();LOGGER.error(e.getMessage(),e);
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();LOGGER.error(e.getMessage(),e);
			}
		}
		
		
		
	}
	

	public static class SYN extends BaseEntity {

		String 		attackHost = "192.168.1.50";
		Integer 	attackPort = 8081;
	
		public SYN() {
			
		}
	
		public SYN(String ip, Integer port) {
			super();
			this.attackHost = ip;
			this.attackPort = port;
		}
	
		public String getAttackHost() {
			return attackHost;
		}
	
		public void setAttackHost(String attackHost) {
			this.attackHost = attackHost;
		}
	
		public Integer getAttackPort() {
			return attackPort;
		}
	
		public void setAttackPort(Integer attackPort) {
			this.attackPort = attackPort;
		}
	
		 
	}

}

