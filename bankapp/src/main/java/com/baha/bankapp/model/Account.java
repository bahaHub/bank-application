package com.baha.bankapp.model;

import java.math.BigDecimal;

import com.baha.bankapp.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "taccount")
@Entity
public class Account extends BaseEntity {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.UUID)
	private String accNo;
	
	private String custNo;
	
	private BigDecimal accBal;
	
	private int accType;
	
	private int accStatus;

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public BigDecimal getAccBal() {
		return accBal;
	}

	public void setAccBal(BigDecimal accBal) {
		this.accBal = accBal;
	}

	public int getAccType() {
		return accType;
	}

	public void setAccType(int accType) {
		this.accType = accType;
	}

	public int getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(int accStatus) {
		this.accStatus = accStatus;
	}
	
}