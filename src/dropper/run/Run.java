package dropper.run;

import javax.swing.UIManager;

import dropper.view.Amigos;
import dropper.view.Inicial;
import dropper.view.Login;
import dropper.view.Progresso;

@SuppressWarnings("unused")
public class Run {

	
	public static void main(String[] args) {
		
		
		setlookAndFeel();
		
		Login ob = new Login();
		//Inicial ob = new Inicial(1);
		//Amigos ob = new Amigos("Eduardo");
		
		
	} //Fim Main

	
public static void setlookAndFeel(){
		
		try {
			
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			
		} catch( Exception e ){
			
			e.printStackTrace();
		}
		
		
		
	}
} //Fim Classe
