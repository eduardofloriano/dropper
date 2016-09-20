package dropper.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dropper.control.Conexao;

@SuppressWarnings("unused")
public class ValidaLogin_model {

	
	private String login;
	private String senha;
	boolean valida = false;
	
	Connection conn;
	ResultSet rs;
	PreparedStatement stmt;
	
	public ValidaLogin_model(String login, String senha){
		
		System.out.println("Entrou no Model");
		
		this.login = login;
		this.senha = senha;
		
		Conexao cn = new Conexao();
		 conn = cn.getConexao();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");
			stmt.setString(1, login);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			while (rs.next()){
				
				if (login.equals(rs.getString("login")) && senha.equals(rs.getString("senha"))){
					valida = true;
				}
				
			}
			
			
			
			
			
			
		} catch (SQLException e) {
			
			System.err.println("Erro na Query de Validar Login!");
			e.printStackTrace();
		}
		 
		 
	}//Fim ValidaLogin
	
	public boolean valida(){
		return valida;
	}
	
}//Fim Classe
