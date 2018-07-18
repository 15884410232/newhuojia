package com.test.bean;
/**
 * 订单信息
 * @author Chenqi
 *
 */
public class Order extends Base{
	//订单ID
	private String orderId;
	//用户Id
	private String userID;
	//金额
	private String amount;
	//定单状态: 1:交易成功，2：待支付，3：支付失败
	private int status;
	//订单结果描述
	private String desc;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
