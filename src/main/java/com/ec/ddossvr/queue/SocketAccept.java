package com.ec.ddossvr.queue;

import org.springframework.stereotype.Component;

import com.ec.common.service.QueueExec;
import com.ec.common.service.QueueService;
@Component
public class SocketAccept extends QueueService<QueueExec<?>>{


	
	@Override
	public String getNamespace() {
		return "cli-queue";
	}

	@Override
	protected int getMaxPriorityThreads() {
		return 1;
	}

	@Override
	protected int getPoolSize() {
		return 256;
	}
	
	@Override
	protected void dequeue(QueueExec<?> exec) throws Exception {
		exec.exec();
	}


}
