package com.test.bean;

/**
 * 货架信息
 * @author Chenqi
 *
 */
public class Huojia extends Base{
	//货架Id
	private String huojiaId;
	//货架名称
	private String huojiaName;
	//货架地址
	private String huojiaAddress;
	//货架二维码字节数据
	private String huojiaqRcode;
	//货架的层数
	private String huojiaFloor;
	public String getHuojiaId() {
		return huojiaId;
	}
	public void setHuojiaId(String huojiaId) {
		this.huojiaId = huojiaId;
	}
	public String getHuojiaName() {
		return huojiaName;
	}
	public void setHuojiaName(String huojiaName) {
		this.huojiaName = huojiaName;
	}
	public String getHuojiaAddress() {
		return huojiaAddress;
	}
	public void setHuojiaAddress(String huojiaAddress) {
		this.huojiaAddress = huojiaAddress;
	}
	public String getHuojiaqRcode() {
		return huojiaqRcode;
	}
	public void setHuojiaqRcode(String huojiaqRcode) {
		this.huojiaqRcode = huojiaqRcode;
	}
	public String getHuojiaFloor() {
		return huojiaFloor;
	}
	public void setHuojiaFloor(String huojiaFloor) {
		this.huojiaFloor = huojiaFloor;
	}

}
