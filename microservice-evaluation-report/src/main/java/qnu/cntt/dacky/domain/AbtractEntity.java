package qnu.cntt.dacky.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class AbtractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@CreatedDate
	@Column(name = "create_date")
	private Instant createDate = Instant.now();

	@CreatedBy
	@Column(name = "created_by", nullable = false, length = 50, updatable = false)
	@JsonIgnore
	private String createdBy = "Client";
	
	@LastModifiedDate
	@Column(name = "update_date")
	private Instant updateDate = Instant.now();

	@LastModifiedBy
	@Column(name = "update_by", length = 50)
	@JsonIgnore
	private String updateBy = "Client";

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
