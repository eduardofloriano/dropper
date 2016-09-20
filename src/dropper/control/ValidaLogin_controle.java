package dropper.control;

import dropper.model.ValidaLogin_model;

public class ValidaLogin_controle {

	
	boolean valida = false;
	
	public ValidaLogin_controle(String login, String senha){
		System.out.println("Entrou no controle");
		
		ValidaLogin_model vlm = new ValidaLogin_model(login, senha);
		valida = vlm.valida();
		
		
	}//Fim Construtor
	
	public boolean valida(){
		return valida;
	}
	
}//Fim Classe
