package com.ec.ddossvr.model;

public enum CommandCode {

	START(0), STOP(1), RESTART(-1);

	private int value;

	CommandCode(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static CommandCode valueOf(int key) {
		switch (key) {
		case 0:
			return CommandCode.START;
		case 1:
			return CommandCode.STOP;
		case -1:
			return CommandCode.RESTART;
		default:
			return CommandCode.STOP;
		}
	}

}
