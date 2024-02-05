package com.baha.bankapp.dto;

import java.util.List;

import com.baha.bankapp.common.BaseDto;

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
public class CustomerDto extends BaseDto {

	private String custNo;
	
	private String custName;
	  
	private List<AccountDto> accounts;
	
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
	
	public List<AccountDto> getAccounts() {
		return accounts;
	}
	
	public void setAccounts(List<AccountDto> accounts) {
		this.accounts = accounts;
	}
}