package com.unity.util;

import java.util.List;

import com.unity.bean.Account;

public class DBUtil {
	private static List<Account> accountList;
	
	public DBUtil(){
		//添加一个默认的测试账号
		Account test = new Account();
		test.setUserid(1);
		test.setUserName("onechance");
		test.setPassword("123");
		accountList.add(test);
	}
	
	private static void checkAccount(Account account){
		for(Account acc:accountList){
			
		}
	}
}
