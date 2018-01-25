package com.ec.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.ec.common.service.QueueService;

@Component
@ConditionalOnClass({ Servlet.class, Tomcat.class })
@ConditionalOnWebApplication
public class ApplicationMetrics implements PublicMetrics, ApplicationContextAware {

	private ApplicationContext applicationContext;


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public Collection<Metric<?>> metrics() {
		
		List<Metric<?>> metrics = new ArrayList<Metric<?>>();
		Map<String, QueueService> queueServiceMap=applicationContext.getBeansOfType(QueueService.class, false, true);
		for(QueueService qs:queueServiceMap.values()){
			metrics.add(new Metric<Integer>("queue_size."+qs.getNamespace().toLowerCase(),qs.getQueueSize()));
		}  
		return metrics;
		
	}


}
