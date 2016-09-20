package dropper.DAO;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;



public class DAO {

	
	public void grava(File file, String tipo, int usuario){
		
					
			try {
				
				//Pausa a frame por 3 segundos
				Thread.sleep(3*1000);
				
				
				//L� os Bytes do arquivo
				String descricao = "Arquivo Binario";
				
				byte[] arquivo = new byte[(int)file.length()];
				System.out.println("Lendo "+arquivo.length+" Bytes...");
				
				DataInputStream is = new DataInputStream(
						new FileInputStream(file.getPath()));
				
				is.readFully(arquivo);
				is.close();
				
				
				//obtem parâmetros de conexão via arquivo de propriedades
				Properties parametrosConexao = new Properties();
				parametrosConexao.load(DAO.class.getResourceAsStream("banco.properties"));
				
				
				String driver = parametrosConexao.getProperty("driver");
				String url = parametrosConexao.getProperty("url");
				String login = parametrosConexao.getProperty("login");
				String senha = parametrosConexao.getProperty("senha");
				
				//Conecta ao banco e insere
				Class.forName(driver);				
				Connection con = DriverManager.getConnection(url, login, senha);
				
				//Insere os Bytes no BD
				PreparedStatement stmt = con.prepareStatement("INSERT INTO "+tipo+"_bytes(byte) VALUES (?)");
				System.out.println("INSERT INTO "+tipo+"_bytes(byte) VALUES (?)");
				stmt.setObject(1, arquivo);
				stmt.executeUpdate();
				
				//Seleciona o ID do arquivo em bytes armazenado
				//stmt = con.prepareStatement("SELECT (id) FROM "+tipo+"_bytes" );
				//ResultSet rs = stmt.executeQuery();
				//rs.last();
				//int row_id = rs.getInt("id");
				
				stmt = con.prepareStatement("SELECT (id) FROM "+tipo+"_bytes WHERE id = (SELECT MAX(id) FROM "+tipo+"_bytes)");
				ResultSet rs = stmt.executeQuery();
				int row_id = 0;
				while(rs.next()){
					row_id = rs.getInt("id");
					System.out.println(row_id);
									
				}
				
				//Insere os Metadados do Arquivo
				stmt = con.prepareStatement(
						"INSERT INTO "+tipo+"(nome, descricao, tamanho, byte) "
					+ "VALUES (?,?,?,?)");
				
				stmt.setString(1, file.getName());
				stmt.setString(2, descricao);
				stmt.setInt(3, (int)file.length());
				stmt.setInt(4, row_id);
				stmt.executeUpdate();
				
				//Seleciona o ID do arquivo armazenado
				//stmt = con.prepareStatement("SELECT (id) FROM "+tipo);
				//rs = stmt.executeQuery();
				//rs.last();
				//row_id = rs.getInt("id");
				
				stmt = con.prepareStatement("SELECT (id) FROM "+tipo);
				rs = stmt.executeQuery();
				while(rs.next()){
					row_id = rs.getInt("id");
					System.out.println(row_id);
									
				}
					
				//Altera o nome da tabela caso for imagem
				String tipo1 = tipo;
				String tipo2 = tipo;
				
				if (tipo.equals("imagem")){
					tipo1 = "imagen";					
				}
				
				stmt = con.prepareStatement(
						"INSERT INTO "+tipo1+"s(usuario,"+tipo2+")"
					+ "VALUES (?,?)");
								
				stmt.setInt(1, usuario);
				stmt.setInt(2, row_id);
				stmt.executeUpdate();
				
				//Finaliza o Programa
				stmt.close();
				con.close();
				
				System.out.println("Arquivo "+file.getName()+" Acrescentada ao banco");
				
				
				
				
			}catch (Exception e){
				System.out.println("Erro ao acrescentar arquivo no banco");
				e.printStackTrace();
			}
			
		
		
	}//Fim grava
	
	public void remove(){
		
	}//Fim Remove
	
	public void get(){
		
	}//Fim Get

		
}//Fim Classe

