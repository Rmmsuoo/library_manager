package com.example.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LOGS")
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOG_ID_SEQ")
	@SequenceGenerator(name = "LOG_ID_SEQ", sequenceName = "LOG_ID_SEQ", allocationSize = 1)
	private Integer id;

	@Column(name = "LIBRARY_ID")
	private Integer libraryId;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "RENT_DATE")
	private LocalDateTime rentDate;

	@Column(name = "RETURN_DATE")
	private Date returnDate;

	@Column(name = "RETURN_DUE_DATE")
	private LocalDateTime returnDueDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public LocalDateTime getRentDate() {
		return rentDate;
	}

	public void setRentDate(LocalDateTime localDateTime) {
		this.rentDate = localDateTime;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDateTime getReturnDueDate() {
		return returnDueDate;
	}

	public void setReturnDueDate(LocalDateTime returnDueDate) {
		this.returnDueDate = returnDueDate;
	}
}