package dropper.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SuppressWarnings("unused")
public class DAOUsuario extends DAO{

	private dropper.BEAM.Usuario usuario;
	private List<dropper.BEAM.Usuario> usuarios = null;
	private int tamanhoTotal = 0;
	
	
	static Properties parametrosConexao;
	static Connection con;
	static ResultSet rs;
	
	public DAOUsuario (){
		
	}
	
	public DAOUsuario (dropper.BEAM.Usuario usuario ){
		this.usuario = usuario;
		
	}
	
	public void consulta() throws SQLException{
		
		try{
			conecta();
			
			tamanhoTotal = 0;
			
			PreparedStatement stmt = con.prepareStatement("SELECT (id, nome, email, login, senha) FROM usuario");
			rs = stmt.executeQuery();
			
			usuarios = new ArrayList<dropper.BEAM.Usuario>();
			System.out.println("Executou a Query select!");
		
			
			while(rs.next()){
				
				System.out.println(rs.getString(1));
				String aux = rs.getString(1);
				//break;

				int tam = aux.length();
				aux = aux.substring(1, tam-1);
				aux = aux.replace('\"', ' ');
				//aux = aux.
				System.out.println(aux);
				//System.exit(1);
				
				String[] result = aux.split(",");
				for ( String i : result){
					System.out.println(i);
				}
				
				
				dropper.BEAM.Usuario usuario = new dropper.BEAM.Usuario();
				usuario.setNome(result[1]);
				usuario.setEmail(result[2]);
				usuario.setLogin(result[3]);
				usuario.setSenha(result[4]);
				usuario.setId(Integer.parseInt(result[0]));
					
				usuarios.add(usuario);
				tamanhoTotal++;
				//System.out.println(rs.getString("row record"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro Na Query de Consultar");
		}finally{
			
			con.close();
			rs.close();
			
		}
		
	}//Fim Consulta

	public dropper.BEAM.Usuario consulta(String login) throws SQLException{
		
		try{
			conecta();
			
			tamanhoTotal = 0;
			
			PreparedStatement stmt = con.prepareStatement("SELECT (id, nome, email, login, senha) FROM usuario WHERE login = ?");
			stmt.setString(1, login);
			
			rs = stmt.executeQuery();
			
			//usuarios = new ArrayList<dropper.BEAM.Usuario>();
			System.out.println("Executou a Query select!");
		
			
			while(rs.next()){
				
				System.out.println(rs.getString(1));
				String aux = rs.getString(1);
				//break;

				int tam = aux.length();
				aux = aux.substring(1, tam-1);
				aux = aux.replace('\"', ' ');
				//aux = aux.
				System.out.println(aux);
				//System.exit(1);
				
				String[] result = aux.split(",");
				for ( String i : result){
					System.out.println(i);
				}
				
				
				dropper.BEAM.Usuario usuario = new dropper.BEAM.Usuario();
				usuario.setNome(result[1]);
				usuario.setEmail(result[2]);
				usuario.setLogin(result[3]);
				usuario.setSenha(result[4]);
				usuario.setId(Integer.parseInt(result[0]));
					
				//usuarios.add(usuario);
				this.usuario = usuario;
				tamanhoTotal++;
				//System.out.println(rs.getString("row record"));
				
				
				
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro Na Query de Consultar");
		}finally{
			
			con.close();
			rs.close();
			
		}
		
		return usuario;
		
	}//Fim Consulta
	

	public dropper.BEAM.Usuario consultaNome(String nome) throws SQLException{
		
		try{
			conecta();
			
			tamanhoTotal = 0;
			
			PreparedStatement stmt = con.prepareStatement("SELECT (id, nome, email, login, senha) FROM usuario WHERE nome = ?");
			stmt.setString(1, nome);
			
			rs = stmt.executeQuery();
			
			//usuarios = new ArrayList<dropper.BEAM.Usuario>();
			System.out.println("Executou a Query select!");
		
			
			while(rs.next()){
				
				System.out.println(rs.getString(1));
				String aux = rs.getString(1);
				//break;

				int tam = aux.length();
				aux = aux.substring(1, tam-1);
				aux = aux.replace('\"', ' ');
				//aux = aux.
				System.out.println(aux);
				//System.exit(1);
				
				String[] result = aux.split(",");
				for ( String i : result){
					System.out.println(i);
				}
				
				
				dropper.BEAM.Usuario usuario = new dropper.BEAM.Usuario();
				usuario.setNome(result[1]);
				usuario.setEmail(result[2]);
				usuario.setLogin(result[3]);
				usuario.setSenha(result[4]);
				usuario.setId(Integer.parseInt(result[0]));
					
				//usuarios.add(usuario);
				this.usuario = usuario;
				tamanhoTotal++;
				//System.out.println(rs.getString("row record"));
				
				
				
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro Na Query de Consultar");
		}finally{
			
			con.close();
			rs.close();
			
		}
		
		return usuario;
		
	}//Fim Consulta
	
	
	public List<dropper.BEAM.Usuario> getUsuarios(){
		return usuarios;
	} 
	
	public int getTamanho(){
		return tamanhoTotal;
	}
	
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
