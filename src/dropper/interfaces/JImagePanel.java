package dropper.interfaces;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class JImagePanel extends JPanel {

	
	
	BufferedImage background = null;
	
	
	public JImagePanel(BufferedImage img)
	{
		
		if (img == null)
			throw new NullPointerException("Buffered image não pode ser null");
		this.background = img;
		
		
	}
	
	
	public JImagePanel(File imgSrc) throws IOException{
		
		this(ImageIO.read(imgSrc));
	}
	
	
	public JImagePanel(String fileName) throws IOException{
		this(new File(fileName));
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		g2d.dispose();
		
	}
	
}//Fim Classe
