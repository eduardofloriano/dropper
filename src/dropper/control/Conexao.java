package dropper.control;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	Connection conn;
	
	
	public Conexao(){
		
		
		//Postgree
		try {
			        Class.forName("org.postgresql.Driver");
			        
			        //----- Localhost -----
			         //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dropper",
			         //   "postgres", "postgres");
			        
			        //----- Remote ------
			        //conn = DriverManager.getConnection("jdbc:postgresql://192.168.56.101:5432/dropper",
					//            "postgres", "postgres");
			        
			        
			       //----- Raspberry ------
				   //conn = DriverManager.getConnection("jdbc:postgresql://192.168.11.108:5432/dropper",
			       //            "postgres", "postgres");
				        
			         
			       //----- CubieTruck ------
					   conn = DriverManager.getConnection("jdbc:postgresql://192.168.1.15:5432/dropper",
				                   "postgres", "postgres");
			         
			         
			      } catch (Exception e) {
			         e.printStackTrace();
			         System.err.println(e.getClass().getName()+": "+e.getMessage());
			         System.exit(0);
			      }
			      System.out.println("Banco de Dados conectado com sucesso!");
			    
		
		
	}//Fim Construtor
	
	public Connection getConexao(){
		return conn;
	}
	

	
	
}//Fim Classe
