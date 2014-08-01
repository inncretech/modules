package com.inncretech.merchant.ui.bean;

import java.io.Serializable;

public class DimensionsAndWeight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double weight;
	private Double width;
	private Double length;
	private Double height;

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "DimensionsAndWeight [weight=" + weight + ", width=" + width + ", length=" + length + ", height="
				+ height + "]";
	}

}
