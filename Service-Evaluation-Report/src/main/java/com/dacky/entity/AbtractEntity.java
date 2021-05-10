package com.dacky.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public abstract class AbtractEntity {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(name="create_date")
	private Instant createDate = Instant.now();
	
	
	@Column(name="update_date")
	private Instant updateDate = Instant.now();


	

	public Instant getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Instant createDate) {
		this.createDate = createDate;
	}


	public Instant getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Instant updateDate) {
		this.updateDate = updateDate;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
}
