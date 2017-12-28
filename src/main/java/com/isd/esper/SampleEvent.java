package com.isd.esper;

public class SampleEvent {
	private String item = null;
	private Double price = null;

	public SampleEvent(String item, Double price) {
		super();
		this.item = item;
		this.price = price;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}