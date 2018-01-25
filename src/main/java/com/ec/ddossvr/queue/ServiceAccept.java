package com.ec.ddossvr.queue;

import org.springframework.stereotype.Component;

import com.ec.common.service.QueueExec;
import com.ec.common.service.QueueService;
@Component
public class ServiceAccept extends QueueService<QueueExec<?>>{


	
	@Override
	public String getNamespace() {
		return "svr-queue";
	}

	@Override
	protected int getMaxPriorityThreads() {
		return 2;
	}

	@Override
	protected int getPoolSize() {
		return 2;
	}
	
	@Override
	protected void dequeue(QueueExec<?> exec) throws Exception {
		exec.exec();
	}


}
