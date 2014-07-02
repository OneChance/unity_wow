package com.unity.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.unity.bean.Account;

public class DBUtil {

	private Connection con = null;
	private Statement stmt = null;
	private String url = "jdbc:mysql://localhost/unity_wow";
	private String user = "root";
	private String pwd = "123";

	private void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, pwd);
			stmt = con.createStatement();
		} catch (Exception e) {
			// your installation of JDBC Driver Failed
			e.printStackTrace();
		}
	}

	public DBUtil() {
		init();
	}

	public Account checkAccount(Account account) {
		String sql = "select * from account where name= '" + account.getUserName()
				+ "' and password='" + account.getPassword() + "'";
		
		System.out.println(sql);
		
		try {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {	
				account.setUserid(rs.getInt("id"));		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

	public Account checkReg(Account account) {
		String sql = "select * from account where name= '"+account.getUserName()+"'";
        try {
            ResultSet rs = stmt .executeQuery(sql);          
            if(rs.next()){  
                return account;
            }else{
            	String sql2 = "insert into account (name,password) value ('"
                    +account.getUserName()+"','"+account.getPassword()+"'); ";
            	stmt.execute(sql2);
            	ResultSet key = stmt.getGeneratedKeys();
            	key.next();
            	account.setUserid((int)key.getLong(1));
            }	
        }catch (Exception e){
           e.printStackTrace();
        }
        
		return account;
	}

	public static void main(String[] args) {

	}
}
