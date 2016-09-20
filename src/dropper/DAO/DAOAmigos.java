package dropper.DAO;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SuppressWarnings("unused")
public class DAOAmigos {

	private dropper.BEAM.Usuario usuario;
	private List<dropper.BEAM.Usuario> usuarios = null;
	private int[] amigos;
	private int tamanhoTotal = 0;
	
	
	static Properties parametrosConexao;
	static Connection con;
	static ResultSet rs;
	
	
	public DAOAmigos(dropper.BEAM.Usuario usuario){
		
		this.usuario = usuario;
		
		
	}//FIm Construtor!
	
	
	public List<dropper.BEAM.Usuario> consulta() throws SQLException{
		
		try{
			
			conecta();
			
			
			PreparedStatement stmt = con.prepareStatement("SELECT nome,email,login,senha,id FROM usuario"
					+ " JOIN amigos ON usuario.id = amigos.usuario2 WHERE usuario1 = ?");
			stmt.setInt(1, usuario.getId());
			
			rs = stmt.executeQuery();
			
			usuarios = new ArrayList<dropper.BEAM.Usuario>();
			while(rs.next()){
				
				dropper.BEAM.Usuario usuario = new dropper.BEAM.Usuario();
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setId(rs.getInt("id"));
					
				usuarios.add(usuario);
				tamanhoTotal++;
				
			}
			
			
			
			
			
			
			
			
			
			
		}catch(Exception e){
			System.err.println("Erro na consulta de amigos!");
			e.printStackTrace();
			
			
		}finally{
			
			con.close();
			rs.close();
		}
		
		return usuarios;
		
	}//Fim Consulta
	
	public void cadastra(String email){
		
		try {
			
			conecta();
			
			//INSERT INTO amigos SELECT 4,id FROM usuario WHERE email = 'zezinho@gmail.com';
			PreparedStatement stmt = con.prepareStatement( "INSERT INTO amigos SELECT ?,id FROM usuario WHERE email = ?");
			
			stmt.setInt(1, usuario.getId());
			stmt.setString(2, email);
			
			stmt.executeUpdate();
			
			
			//Finaliza o Programa
			stmt.close();
			con.close();
			
			
			
			
			
			
		}catch (Exception e){
			
			System.out.println("Erro ao acrescentar arquivo no banco");
			e.printStackTrace();
			
		}
		
		
		
	}//Fim Cadastra
	
	
	public static void conecta(){
		
		try{
			
			System.out.println("Iniciando Conexao com o banco");
			
			//obtem parâmetros de conexão via arquivo de propriedades
			parametrosConexao = new Properties();
			parametrosConexao.load(DAOArquivo.class.getResourceAsStream("banco.properties"));
							
			System.out.println("Leu Parametros");		
			
			String driver = parametrosConexao.getProperty("driver");
			String url = parametrosConexao.getProperty("url");
			String login = parametrosConexao.getProperty("login");
			String senha = parametrosConexao.getProperty("senha");
							
			//Conecta ao banco e insere a imagem
			Class.forName(driver);
				
			
			con = DriverManager.getConnection(url, login, senha);
			
			System.out.println(url+" "+login+" "+senha);
			
		}
		catch (Exception e){
			System.err.println("Erro ao conectar Banco de Dados!");
			e.printStackTrace();
			
		}
		finally{
			System.out.println("Conectou ao banco");
		}
		
	}//Fim Metodo Conecta
	
	
}//Fim Classe
