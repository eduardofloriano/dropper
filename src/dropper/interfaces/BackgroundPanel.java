package dropper.interfaces;

import javax.swing.*;

import java.awt.*;


@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel {

	
	Image image;
	
	public BackgroundPanel(){
		
		
		try{
			image = (new ImageIcon(getClass().getResource("/Backgrounds/bluebackground.fw.png"))).getImage();
			//setIconImage(Toolkit.getDefaultToolkit().getImage(Inicial3.class.getResource("/Icones/1393049236_apps_music_box.png")));
			
			
		}catch(Exception e){
			
			
		}
		
	}//Fim BackgroundPanel
	
public BackgroundPanel(int x){
		
		
		try{
			
			if (x != 0){
				image = (new ImageIcon(getClass().getResource("/Backgrounds/bluebackground2.fw.png"))).getImage();
				//setIconImage(Toolkit.getDefaultToolkit().getImage(Inicial3.class.getResource("/Icones/1393049236_apps_music_box.png")));
			}else{
				image = (new ImageIcon(getClass().getResource("/Backgrounds/bluebackground.fw.png"))).getImage();
				//setIconImage(Toolkit.getDefaultToolkit().getImage(Inicial3.class.getResource("/Icones/1393049236_apps_music_box.png")));
			}
			
			
			
		}catch(Exception e){
			
			
		}
		
	}//Fim BackgroundPanel
	
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		if(image != null) g.drawImage(image, (this.getWidth()/2) - (image.getWidth(this) / 2),
		(this.getHeight()/2) - (image.getHeight(this)/2), image.getWidth(this), image.getHeight(this),this); //Image, locationx, location y, sizex, sizey
		
		
		
	}//Fim paintComponent
	
}//Fim Classe
