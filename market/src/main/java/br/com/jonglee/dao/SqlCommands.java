package br.com.jonglee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.jonglee.beans.ProductsData;

public class SqlCommands extends ConnectionToSQLite{

	public static SqlCommands instance;
	
	public static SqlCommands getInstance(){
		synchronized (SqlCommands.class) {
			if (instance == null){
				instance = new SqlCommands();
			}
		}
		return instance;	
	}
	
	/**
	 * This method list all products contain in data base
	 */
	public ArrayList<ProductsData> getAllContent(){
		
		ArrayList<ProductsData> listAll = new ArrayList<ProductsData>();
		
		Connection connect =null;
		PreparedStatement pstmt  = null;
		ResultSet rs = null;
		
		connect = getConnection();

		try {
			pstmt = connect.prepareStatement("SELECT * FROM PRODUCTS");
			rs = pstmt.executeQuery();
			  
			while(rs.next()){
				ProductsData dados = new ProductsData();
				dados.setId(rs.getString("products_id"));
				dados.setTitle(rs.getString("products_name"));
				dados.setDescription(rs.getString("products_description"));
				dados.setUnit_price(rs.getFloat("products_value"));
				listAll.add(dados);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao selecionar o BD"+e);
		}finally {
			closeConnection(connect, pstmt, rs);
		}
		
		return listAll;
	}
	
	/**
	 * This method add a product in data base
	 */
	public void addInDB(ProductsData product){
		PreparedStatement pstmt  = null;
		try (Connection connect = getConnection()){
			pstmt = connect.prepareStatement("INSERT INTO PRODUCTS (products_name, products_description, products_value) VALUES (?, ?, ?)");
			pstmt.setString(1, product.getTitle());
			pstmt.setString(2, product.getDescription());
			pstmt.setFloat(3, product.getUnit_price());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao adicionar no BD:" + e);
		}
	}
	
	public void removeInDB(ProductsData product){
		PreparedStatement pstmt  = null;
		try (Connection connect = getConnection()){
			pstmt = connect.prepareStatement("delete from products where products_id = ?");
			pstmt.setString(1, product.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao remover do BD:" + e);
		}
	}
}
