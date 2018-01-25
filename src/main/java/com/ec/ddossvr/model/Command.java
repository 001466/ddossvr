package com.ec.ddossvr.model;

import com.ec.common.model.BaseEntity;

public class Command extends BaseEntity {

	String 	tcpSvrHost = "127.0.0.1";
	Integer tcpSvrPort = 55;
	String 	svrRedirectURL = "https://github.com/001466/ecsharp/blob/win/ddossvrurl.txt;";
	
	Integer attackCommand = 1;
	String  attackType;
	String 	attackError;
	Long 	attackCount;
	Object 	attackInfo;
	public String getTcpSvrHost() {
		return tcpSvrHost;
	}
	public void setTcpSvrHost(String tcpSvrHost) {
		this.tcpSvrHost = tcpSvrHost;
	}
	public Integer getTcpSvrPort() {
		return tcpSvrPort;
	}
	public void setTcpSvrPort(Integer tcpSvrPort) {
		this.tcpSvrPort = tcpSvrPort;
	}
	public String getSvrRedirectURL() {
		return svrRedirectURL;
	}
	public void setSvrRedirectURL(String svrRedirectURL) {
		this.svrRedirectURL = svrRedirectURL;
	}
	public Integer getAttackCommand() {
		return attackCommand;
	}
	public void setAttackCommand(Integer attackCommand) {
		this.attackCommand = attackCommand;
	}
	public String getAttackType() {
		return attackType;
	}
	public void setAttackType(String attackType) {
		this.attackType = attackType;
	}
	public String getAttackError() {
		return attackError;
	}
	public void setAttackError(String attackError) {
		this.attackError = attackError;
	}
	public Long getAttackCount() {
		return attackCount;
	}
	public void setAttackCount(Long attackCount) {
		this.attackCount = attackCount;
	}
	public Object getAttackInfo() {
		return attackInfo;
	}
	public void setAttackInfo(Object attackInfo) {
		this.attackInfo = attackInfo;
	}

}
