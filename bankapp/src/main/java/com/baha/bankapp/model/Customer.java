package com.baha.bankapp.model;

import com.baha.bankapp.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tcustomer")
@Entity
public class Customer extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String custNo;

	private String custName;
	
	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
}
