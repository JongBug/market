package br.com.jonglee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionToSQLite{
	
	public Connection getConnection(){	
		// conexao com BD
		Connection connect = null;
		try 
		{  
			String sqlBD = "jdbc:sqlite:C://temp/jonglee_market.db"; 
			Class.forName("org.sqlite.JDBC");  
			connect = DriverManager.getConnection(sqlBD);  
		} 
		catch (Exception e) 
		{  
			System.out.println("Erro na conexão: " + e);
		}
		return connect;
	}
	
	public void closeConnection(Connection connect, PreparedStatement pstmt, ResultSet rs){
		try {
			if(connect!=null){
				connect.close();
			}
		}catch (Exception e) {
			System.out.println("Erro ao fechar conexão"+e);
		}
		try{
			if (pstmt!=null) {
				pstmt.close();
			}
		}catch (Exception e) {
			System.out.println("Erro ao fechar conexão"+e);
		}
		try{
			if(rs!=null){
				rs.close();
			}
		}catch (Exception e) {
			System.out.println("Erro ao fechar conexão"+e);
		}
	}
}
