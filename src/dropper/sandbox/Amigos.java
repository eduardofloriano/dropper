package dropper.sandbox;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;

import java.awt.Label;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.JButton;

import dropper.BEAM.Usuario;
import dropper.DAO.DAOUsuario;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;

@SuppressWarnings({ "unused", "serial" })
public class Amigos extends JFrame implements ActionListener{

	private JPanel contentPane;
	private int numAmigos = 0; //Numero de amigos
	private dropper.BEAM.Usuario usuario;

	public Amigos(String login) {
		setTitle("Amigos");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, Color.BLACK));
		panel.setBounds(0, 0, 284, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Amigos.class.getResource("/Icones/1386744919_Circle_Green.png")));
		lblStatus.setBounds(10, 18, 24, 24);
		panel.add(lblStatus);
		
		JLabel label = new JLabel("<Usu\u00E1rio>");
		label.setFont(new Font("Trebuchet MS", Font.ITALIC, 12));
		label.setBounds(44, 18, 230, 24);
		panel.add(label);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(Amigos.class.getResource("/Setas/Voltar.fw.png")));
		btnVoltar.setBounds(10, 309, 36, 36);
		btnVoltar.setFocusable(false);	
		btnVoltar.setBorderPainted(false);
		btnVoltar.setContentAreaFilled(false);
		contentPane.add(btnVoltar);
		
		
		JButton btnAvancar = new JButton("");
		btnAvancar.setIcon(new ImageIcon(Amigos.class.getResource("/Setas/Avancar.fw.png")));
		btnAvancar.setBounds(238, 309, 36, 36);
		btnAvancar.setFocusable(false);	
		btnAvancar.setBorderPainted(false);
		btnAvancar.setContentAreaFilled(false);
		contentPane.add(btnAvancar);
		
		
		//Monta o Cabeçalho
		try{
			
			DAOUsuario dao = new DAOUsuario();
			usuario = dao.consulta(login);
			
			label.setText(usuario.getNome());
			
			JButton btnRemover = new JButton("");
			btnRemover.setIcon(new ImageIcon(Amigos.class.getResource("/Amigos/1384569256_Delete.jpg")));
			btnRemover.setBounds(245, 18, 24, 24);
			panel.add(btnRemover);
			
		}catch (Exception e){
			System.err.println("Erro ao consultar usuario!");
			e.printStackTrace();
		}
		
		
		//Adiciona Amigos;
		addAmigo();
		addAmigo();
		//addAmigo();
		//addAmigo();
		//addAmigo();
		
		
		setVisible(true);
	}//Fim Construtor


	public void addAmigo(){
		
		
		int distancia = 60;
		
		for (int i=0; i<numAmigos; i++){
			distancia += 60;
			
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(0, distancia, 284, 60);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Amigos.class.getResource("/Icones/1386744919_Circle_Green.png")));
		lblStatus.setBounds(10, 18, 24, 24);
		panel.add(lblStatus);
		
		JLabel label = new JLabel("<Usu\u00E1rio>");
		label.setFont(new Font("Trebuchet MS", Font.ITALIC, 12));
		label.setBounds(44, 18, 230, 24);
		panel.add(label);
		
		
		
		
		numAmigos ++;
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
	}
}//Fim Classe
