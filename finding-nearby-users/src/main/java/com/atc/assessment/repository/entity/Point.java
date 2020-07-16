package com.atc.assessment.repository.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Point {
	
	
	private Double x;
    private Double y;
    
	public Double getX() {
		return x;
	}
	
	public void setX(Double x) {
		this.x = x;
	}
	
	public Double getY() {
		return y;
	}
	
	public void setY(Double y) {
		this.y = y;
	}
    
    
}
