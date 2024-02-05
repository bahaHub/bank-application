package com.baha.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class AccountDto {
	private String custNo;
	private String accNo;
	private String accBal;
	private String depoAmt;
	private String wdAmt;
	private String accType;
	private String accStatus;
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
	public String getAccBal() {
		return accBal;
	}
	public void setAccBal(String accBal) {
		this.accBal = accBal;
	}
	public String getDepoAmt() {
		return depoAmt;
	}
	public void setDepoAmt(String depoAmt) {
		this.depoAmt = depoAmt;
	}
	public String getWdAmt() {
		return wdAmt;
	}
	public void setWdAmt(String wdAmt) {
		this.wdAmt = wdAmt;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
}