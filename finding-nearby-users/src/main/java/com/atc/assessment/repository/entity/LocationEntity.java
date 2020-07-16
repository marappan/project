package com.atc.assessment.repository.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LOCATION_DATA")
public class LocationEntity {
	
	@Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "latitude")),
            @AttributeOverride(name = "y", column = @Column(name = "longitude"))
    })
    private Point point;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="USER_ID", nullable=false)
	private Integer userId;
	
	@ManyToOne
	//@JoinColumn(name="USER_ID", insertable=true, updatable=true)
	private UserEntity userData;
	
	public UserEntity getUserData() {
		return userData;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setUserData(UserEntity userData) {
		this.userData = userData;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

}
