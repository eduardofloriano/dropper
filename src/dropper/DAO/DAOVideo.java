package dropper.DAO;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



@SuppressWarnings("unused")
public class DAOVideo extends DAO {

	private dropper.BEAM.Video video;
	private dropper.BEAM.Usuario usuario;
	private List<dropper.BEAM.Video> videos = null;
	private int tamanhoTotal=0;
	private File file;
	
	static Properties parametrosConexao;
	static Connection con;
	static ResultSet rs;
	
	public DAOVideo(){
		
	}
	
	public DAOVideo(dropper.BEAM.Video video){
		
	
		this.video = video;
		
		
	}//Fim Construtor
	
	public DAOVideo(File file){
		
		this.file = file;
		
	}//Fim Construtor
	
	public void grava(){
		
		super.grava(file, "video", usuario.getId());
		
	}//Fim grava
		
	public void remove(){
		
		try{
			
			//obtem parâmetros de conexão via arquivo de propriedades
			Properties parametrosConexao = new Properties();
			parametrosConexao.load(DAOVideo.class.getResourceAsStream("banco.properties"));
			
			
			String driver = parametrosConexao.getProperty("driver");
			String url = parametrosConexao.getProperty("url");
			String login = parametrosConexao.getProperty("login");
			String senha = parametrosConexao.getProperty("senha");
			
			//Conecta ao banco e insere a imagem
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, login, senha);
			
			PreparedStatement stmt = con.prepareStatement(
					"DELETE FROM video WHERE id = (?)");
			
			stmt.setInt(1, (int)video.getId());
			
			stmt.executeUpdate();
			
			
			//Finaliza o Programa
			stmt.close();
			con.close();
			
			System.out.println("Arquivo "+video.getNome()+" Removido do banco!");
			
			
		}catch (Exception e){
			
			System.err.println("Erro ao remover arquivo!");
			e.printStackTrace();
		}
		
	}//Fim remove
	
	public void setVideo(dropper.BEAM.Video video){
		this.video = video;
	}
	
	public void setUsuario(dropper.BEAM.Usuario usuario){
		this.usuario = usuario;
	}

	public dropper.BEAM.Video getVideo(){
		
		return this.video;
	}
	
	public void download(String path) throws Exception{
		
		
		System.out.println(video.getDescricao());
		System.out.println(video.getNome());
		System.out.println(video.getTamanho());
		//System.out.println(programa.getDescricao());
		
		System.out.println(path);
		String newFile = path+"\\"+video.getNome();
		System.out.println(newFile);
			
		
		try{
			conecta();
			
			//PreparedStatement stmt = con.prepareStatement("SELECT * FROM video WHERE id = "+video.getId());
			PreparedStatement stmt = con.prepareStatement("SELECT video_bytes.byte FROM video_bytes"
					+ " WHERE video_bytes.id = (SELECT video.byte FROM video WHERE video.id = "+video.getId()+")");
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
								
				byte[] bytes = rs.getBytes("byte");
				
				video.setBytes(bytes);
				
			}
			
		}catch(Exception gbyte){
			System.err.println("Erro ao recuperar bytes!");
			gbyte.printStackTrace();
		}
		
		
		byte[] bytes = this.video.getBytes();
		
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
			
			//PreparedStatement stmt = con.prepareStatement("SELECT (id, nome, descricao, tamanho) FROM video WHERE usuario = "+usuario.getId());
			PreparedStatement stmt = con.prepareStatement("SELECT (video.id, video.nome, video.descricao, video.tamanho)"
					+ " FROM video JOIN videos ON videos.video = video.id WHERE usuario = "+usuario.getId());
			
			rs = stmt.executeQuery();
			
			videos = new ArrayList<dropper.BEAM.Video>();
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
				
				
				dropper.BEAM.Video video = new dropper.BEAM.Video();
				video.setNome(result[1]);
				video.setDescricao(result[2]);
				video.setTamanho(Integer.parseInt(result[3]));
				//programa.setBytes(bytes);
				video.setId(Integer.parseInt(result[0]));
					
				videos.add(video);
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
	
	public List<dropper.BEAM.Video> getVideos(){
		return videos;
	} 
	
	public int getTamanho(){
		return tamanhoTotal;
	}
	
	public static void conecta(){
		
		try{
			
			System.out.println("Iniciando Conexao com o banco");
			
			//obtem parâmetros de conexão via arquivo de propriedades
			parametrosConexao = new Properties();
			parametrosConexao.load(DAOVideo.class.getResourceAsStream("banco.properties"));
							
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
