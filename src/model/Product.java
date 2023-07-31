package model;

public class Product {

	private int id;

	private String productCode;

	private String productName;

	private String brand;

	private String status;

	private String quantify;

	private String price;

	private String date;

	public Product() {
		super();
	}

	public Product(int id, String productCode, String productName, String brand, String status, String quantify,
			String price, String date) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.brand = brand;
		this.status = status;
		this.quantify = quantify;
		this.price = price;
		this.date = date;
	}
	
	public Product(String productCode, String productName, String brand, String status, String quantify, String price) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.brand = brand;
		this.status = status;
		this.quantify = quantify;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQuantify() {
		return quantify;
	}

	public void setQuantify(String quantify) {
		this.quantify = quantify;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
