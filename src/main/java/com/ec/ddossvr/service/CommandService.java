package com.ec.ddossvr.service;

import java.io.Writer;

import com.ec.ddossvr.model.Command;

public abstract class CommandService {
	public abstract void command(Command get, Writer writer);
}
