package dropper.DAO;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@SuppressWarnings("unused")
public class DAOArquivo extends DAO {

	private dropper.BEAM.Arquivo arquivo;
	private dropper.BEAM.Usuario usuario;
	private List<dropper.BEAM.Arquivo> arquivos = null;
	private int tamanhoTotal=0;
	private File file;
	
	static Properties parametrosConexao;
	static Connection con;
	static ResultSet rs;
	
	
	public DAOArquivo(){
		
	}
	
	public DAOArquivo(dropper.BEAM.Arquivo arquivo){
		
	
		this.arquivo = arquivo;
		
		
	}//Fim Construtor
	
	public DAOArquivo(File file){
		
		this.file = file;
		
	}//Fim Construtor
	
	public void grava(){
		
		super.grava(file, "arquivo", usuario.getId());
		
	}//Fim grava
			
	public void remove(){
		
		try{
			
			//obtem parâmetros de conexão via arquivo de propriedades
			Properties parametrosConexao = new Properties();
			parametrosConexao.load(DAOArquivo.class.getResourceAsStream("banco.properties"));
			
			
			String driver = parametrosConexao.getProperty("driver");
			String url = parametrosConexao.getProperty("url");
			String login = parametrosConexao.getProperty("login");
			String senha = parametrosConexao.getProperty("senha");
			
			//Conecta ao banco e insere a imagem
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, login, senha);
			
			PreparedStatement stmt = con.prepareStatement(
					"DELETE FROM arquivo WHERE id = (?)");
			
			stmt.setInt(1, (int)arquivo.getId());
			
			stmt.executeUpdate();
			
			
			//Finaliza o Programa
			stmt.close();
			con.close();
			
			System.out.println("Arquivo "+arquivo.getNome()+" Removido do banco!");
			
			
		}catch (Exception e){
			
			System.err.println("Erro ao remover arquivo!");
			e.printStackTrace();
		}
		
	}//Fim remove
		
	public void setArquivo(dropper.BEAM.Arquivo arquivo){
		this.arquivo = arquivo;
	}
		
	public void setUsuario(dropper.BEAM.Usuario usuario){
		this.usuario = usuario;
	}
	
	public dropper.BEAM.Arquivo getArquivo(){
		
		return this.arquivo;
	}
		
	public void download(String path) throws Exception{
		
		
		System.out.println(arquivo.getDescricao());
		System.out.println(arquivo.getNome());
		System.out.println(arquivo.getTamanho());
		//System.out.println(programa.getDescricao());
		
		System.out.println(path);
		String newFile = path+"\\"+arquivo.getNome();
		System.out.println(newFile);
			
		
		try{
			
			conecta();
			
			//PreparedStatement stmt = con.prepareStatement("SELECT * FROM arquivo WHERE id = "+arquivo.getId());
			//PreparedStatement stmt = con.prepareStatement("SELECT arquivos_bytes.byte FROM arquivo_bytes JOIN arquivo"
			//		+ " ON arquivo.byte = arquivos_bytes.id WHERE arquivo.id = "+arquivo.getId());
			PreparedStatement stmt = con.prepareStatement("SELECT arquivo_bytes.byte FROM arquivo_bytes"
					+ " WHERE arquivo_bytes.id = (SELECT arquivo.byte FROM arquivo WHERE arquivo.id = "+arquivo.getId()+")");
			
			
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
								
				byte[] bytes = rs.getBytes("byte");
				
				arquivo.setBytes(bytes);
				
			}
			
		}catch(Exception gbyte){
			System.err.println("Erro ao recuperar bytes!");
			gbyte.printStackTrace();
		}
		
		
		byte[] bytes = this.arquivo.getBytes();
		
		//Converte
		File file = new File(newFile);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		fos.close();
		
		System.out.println("Arquivo recuperado com sucesso!");
		
	}//Fim Download
	
	public void consulta() throws SQLException{
		
		try{
			conecta();
			
			tamanhoTotal = 0;
			
			//PreparedStatement stmt = con.prepareStatement("SELECT (id, nome, descricao, tamanho) FROM arquivo WHERE usuario = "+usuario.getId());
			PreparedStatement stmt = con.prepareStatement("SELECT (arquivo.id, arquivo.nome, arquivo.descricao, arquivo.tamanho)"
					+ " FROM arquivo JOIN arquivos ON arquivos.arquivo = arquivo.id WHERE usuario = "+usuario.getId());
			
			rs = stmt.executeQuery();
			
			arquivos = new ArrayList<dropper.BEAM.Arquivo>();
			System.out.println("Executou a Query select!");
		
			
			while(rs.next()){
				
				System.out.println("Entrou no WhileDAO");

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
				
				
				dropper.BEAM.Arquivo arquivo = new dropper.BEAM.Arquivo();
				arquivo.setNome(result[1]);
				arquivo.setDescricao(result[2]);
				arquivo.setTamanho(Integer.parseInt(result[3]));
				//programa.setBytes(bytes);
				arquivo.setId(Integer.parseInt(result[0]));
					
				arquivos.add(arquivo);
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
	
	public List<dropper.BEAM.Arquivo> getArquivos(){
		return arquivos;
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
