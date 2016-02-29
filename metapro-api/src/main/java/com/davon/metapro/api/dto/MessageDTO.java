package com.davon.metapro.api.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class MessageDTO {

	private Long id;
	@NotEmpty
	@Length(max = 100)
	private String message;
	private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
