package com.test.bean;

/**
 * 商品信息
 * @author Chenqi
 *
 */
public class Product extends Base{

	//商品id
	private String productId;
	//库存	
	private Integer productStock;
	//名称
	private String productName;
	//单价
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
