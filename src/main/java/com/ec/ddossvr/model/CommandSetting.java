package com.ec.ddossvr.model;

public class CommandSetting extends Command{

	
	String 		attackHost = "192.168.1.50";
	Integer 	attackPort = 8081;
	String 		attackURL = "http://192.168.1.50:8081/dispatcher/";
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
	public String getAttackURL() {
		return attackURL;
	}
	public void setAttackURL(String attackURL) {
		this.attackURL = attackURL;
	}

}
