package com.unity.util;

import java.util.ArrayList;
import java.util.List;

import com.unity.bean.Account;

public class DBUtil {
	private static List<Account> accountList;
	
	public DBUtil(){
		
		accountList = new ArrayList<Account>();
		
		//添加一个默认的测试账号
		Account test = new Account();
		test.setUserid(1);
		test.setUserName("onechance");
		test.setPassword("123");
		accountList.add(test);
	}
	
	public Account checkAccount(Account account){
		for(Account acc:accountList){
			if(acc.getUserName().equals(account.getUserName())&&acc.getPassword().equals(account.getPassword())){
				return acc;
			}
		}
		return account;
	}
}
