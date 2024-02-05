package com.baha.bankapp.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baha.bankapp.common.CommonConstant;
import com.baha.bankapp.common.CommonUtils;
import com.baha.bankapp.common.StringUtil;
import com.baha.bankapp.dto.AccountDto;
import com.baha.bankapp.exception.AccountInsufficientBalanceException;
import com.baha.bankapp.exception.AccountNotActiveException;
import com.baha.bankapp.exception.AccountNotFoundException;
import com.baha.bankapp.model.Account;
import com.baha.bankapp.repository.AccountRepository;
import com.baha.bankapp.service.BaseService;
import com.baha.bankapp.service.AccountServiceManager;

@Service
public class AccountServiceManagerImpl extends BaseService  implements AccountServiceManager {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Map<String, Object> createAccount(Account newAccount, String custNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (StringUtil.isNullorBlank(custNo)) {
			return setReturnMap(CommonConstant.RETURN_CODE_10002, CommonConstant.STR_RETURN_MESSAGE_10002);
		} else if (newAccount.getAccType() != 1 && newAccount.getAccType() != 2) {
			return setReturnMap(CommonConstant.RETURN_CODE_10004, CommonConstant.STR_RETURN_MESSAGE_10004);
		}
		
		newAccount.setCustNo(custNo);
		newAccount.setAccNo(CommonUtils.generateAccountNumber());
		newAccount.setAccBal(BigDecimal.ZERO);
		newAccount.setAccStatus(CommonUtils.ACCOUNT_ACTIVE);
		newAccount.setCreatedBy(CommonUtils.STR_SYSTEM);
		newAccount.setCreatedDate(LocalDateTime.now());
		newAccount.setUpdatedBy(CommonUtils.STR_SYSTEM);
		newAccount.setUpdatedDate(LocalDateTime.now());
		
		newAccount = accountRepository.save(newAccount);
		
		returnMap = setReturnMap(CommonConstant.RETURN_CODE_SUCCESS, CommonConstant.RETURN_MESSAGE_SUCCESS);
		returnMap.put(CommonConstant.STR_OBJECT, newAccount);
		
		return returnMap;
	}

	@Override
	public Map<String, Object> inquireCustomerAccountList(String custNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (StringUtil.isNullorBlank(custNo)) {
			return setReturnMap(CommonConstant.RETURN_CODE_10002, CommonConstant.STR_RETURN_MESSAGE_10002);
		}
		
		List<Account> accList = accountRepository.findByCustNo(custNo);
		
		returnMap = setReturnMap(CommonConstant.RETURN_CODE_SUCCESS, CommonConstant.RETURN_MESSAGE_SUCCESS);
		returnMap.put(CommonConstant.STR_OBJECT, accList);
		
		return returnMap;
	}

	@Override
	public Map<String, Object> depositAmount(AccountDto accountDto, String accNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BigDecimal minDepoAmt = BigDecimal.valueOf(1);
		BigDecimal depoAmt;
		try {
			depoAmt = new BigDecimal(accountDto.getDepoAmt());
		} catch (Exception e) {
			return setReturnMap(CommonConstant.RETURN_CODE_20005, CommonConstant.STR_RETURN_MESSAGE_20005);
		}

		Optional<Account> result = accountRepository.findById(accNo);
		if (result.isEmpty()) {
			throw new AccountNotFoundException(accNo);
		} else if (result.get().getAccStatus() == 0) {
			throw new AccountNotActiveException(accNo);
		} else if (depoAmt.compareTo(minDepoAmt) < 0) {
			return setReturnMap(CommonConstant.RETURN_CODE_20004, CommonConstant.STR_RETURN_MESSAGE_20004);
		}

	    Account account = result.get();
	    BigDecimal newAccBall = account.getAccBal().add(depoAmt);
	    account.setAccBal(newAccBall);
	    account.setUpdatedBy(CommonUtils.STR_SYSTEM);
	    account.setUpdatedDate(LocalDateTime.now());
	    account = accountRepository.saveAndFlush(account);
	    
	    returnMap = setReturnMap(CommonConstant.RETURN_CODE_SUCCESS, CommonConstant.RETURN_MESSAGE_SUCCESS);
		returnMap.put(CommonConstant.STR_OBJECT, account);
	    
		return returnMap;
	}

	@Override
	public Map<String, Object> withdrawAmount(AccountDto accountDto, String accNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BigDecimal wdAmt;
		try {
			wdAmt = new BigDecimal(accountDto.getWdAmt());
		} catch (Exception e) {
			return setReturnMap(CommonConstant.RETURN_CODE_20005, CommonConstant.STR_RETURN_MESSAGE_20005);
		}

		Optional<Account> result = accountRepository.findById(accNo);
		if (result.isEmpty()) {
			throw new AccountNotFoundException(accNo);
		} else if (result.get().getAccStatus() == 0) {
			throw new AccountNotActiveException(accNo);
		}
	    Account account = result.get();
	    
	    if (wdAmt.compareTo(account.getAccBal()) > 0) {
//	    	return setReturnMap(CommonConstant.RETURN_CODE_20003, CommonConstant.STR_RETURN_MESSAGE_20003);
	    	throw new AccountInsufficientBalanceException(accNo);
	    }
	    
	    BigDecimal newAccBall = account.getAccBal().subtract(wdAmt);
	    account.setAccBal(newAccBall);
	    account.setUpdatedBy(CommonUtils.STR_SYSTEM);
	    account.setUpdatedDate(LocalDateTime.now());
	    account = accountRepository.saveAndFlush(account);

	    returnMap = setReturnMap(CommonConstant.RETURN_CODE_SUCCESS, CommonConstant.RETURN_MESSAGE_SUCCESS);
		returnMap.put(CommonConstant.STR_OBJECT, account);
	    
		return returnMap;
	}

	@Override
	public Map<String, Object> closeAccount(String accNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (!accountRepository.existsById(accNo)) {
			throw new AccountNotFoundException(accNo);
		}
		
		Optional<Account> result = accountRepository.findById(accNo);
		
		Account account = result.get();
		account.setAccStatus(0);
	    account.setUpdatedBy(CommonUtils.STR_SYSTEM);
	    account.setUpdatedDate(LocalDateTime.now());
	    account = accountRepository.saveAndFlush(account);
		
//	    accountRepository.deleteById(accNo);
	    
	    returnMap = setReturnMap(CommonConstant.RETURN_CODE_SUCCESS, CommonConstant.RETURN_MESSAGE_SUCCESS);
		
		return returnMap;
	}
}
