package dropper.BEAM;

public class Video {

	public String nome;
	public String descricao;
	public int tamanho;
	public byte[] bytes;
	
	public int id;
	
	
	public Video(){
		
		
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getTamanho() {
		return tamanho;
	}


	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}


	public byte[] getBytes() {
		return bytes;
	}


	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}


	public void setId( int id) {
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	
	
}//Fim Classe
