package com.test.bean;

/**
 * ��Ʒ��Ϣ
 * @author Chenqi
 *
 */
public class Product extends Base{

	//��Ʒid
	private String productId;
	//���	
	private Integer productStock;
	//����
	private String productName;
	//����
	private double price;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getProductStock() {
		return productStock;
	}
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
