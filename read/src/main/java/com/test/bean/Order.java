package com.test.bean;
/**
 * ������Ϣ
 * @author Chenqi
 *
 */
public class Order extends Base{
	//����ID
	private String orderId;
	//�û�Id
	private String userID;
	//���
	private String amount;
	//����״̬: 1:���׳ɹ���2����֧����3��֧��ʧ��
	private int status;
	//�����������
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
