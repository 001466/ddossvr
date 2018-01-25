package com.ec.common.service;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class QueueExec<T> {

	public abstract void exec() throws IOException;

	public QueueExec(T param) {
		this.param=param;
		this.thread = Thread.currentThread();
	}

	private Thread thread;

	private T param;

	@JsonIgnore
	private Long enqTime;
	@JsonIgnore
	private Long deqTime;
	private Long durTime;

	public Long getEnqTime() {
		return enqTime;
	}

	public void setEnqTime(Long enqTime) {
		this.enqTime = enqTime;
	}

	public Long getDeqTime() {
		return deqTime;
	}

	public void setDeqTime(Long deqTime) {
		this.deqTime = deqTime;
	}

	public Long getDurTime() {
		if (durTime != null) {
			return durTime;
		} else if (deqTime != null && enqTime != null) {
			return deqTime - enqTime;
		} else {
			return null;
		}
	}

	public void setDurTime(Long durTime) {
		this.durTime = durTime;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public T getParam() {
		return param;
	}

	public void setParam(T param) {
		this.param = param;
	}

}
