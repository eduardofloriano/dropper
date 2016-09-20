package dropper.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dropper.BEAM.Usuario;
import dropper.DAO.DAOUsuario;
import dropper.interfaces.BackgroundPanel;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Font;

@SuppressWarnings({"serial", "unused"})
public class Compartilha extends JFrame implements ActionListener {

	private JPanel contentPane, panel=null;
	private JToolBar toolBar;
	private JButton btnDownload, btnUsuario, btnSair;
	private Component horizontalStrut;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	
	
	private Usuario usuario;
	
	
	public Compartilha(String nome) {
		setTitle("Compartilhamento");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Compartilha.class.getResource("/Amigos/sharefolder.png")));
		
		try{
			
			DAOUsuario daousuario = new DAOUsuario();
			usuario = daousuario.consultaNome(nome);
			
			
			
		}catch (Exception e){
			
			System.err.println("Erro ao consultar usuario!");
			e.printStackTrace();
		}
		
		
		
		
		
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		toolBar = new JToolBar();
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toolBar.setBounds(0, 0, 644, 54);
		contentPane.add(toolBar);
		
		
		btnUsuario = new JButton(usuario.getNome());
		btnUsuario.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		btnUsuario.setIcon(new ImageIcon(Compartilha.class.getResource("/Compartilha/usuario.png")));
		btnUsuario.setFocusable(false);
		toolBar.add(btnUsuario);
		btnUsuario.addActionListener(this);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);
		
		
		btnDownload = new JButton("");
		btnDownload.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1394353513_download-alt.png")));
		btnDownload.setFocusable(false);
		toolBar.add(btnDownload);
		btnDownload.addActionListener(this);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_1);
		
		horizontalStrut = Box.createHorizontalStrut(410);
		toolBar.add(horizontalStrut);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_2);
		
		btnSair= new JButton("");
		btnSair.setIcon(new ImageIcon(Inicial.class.getResource("/Compartilha/sair.png")));
		btnSair.setFocusable(false);
		btnSair.addActionListener(this);
		toolBar.add(btnSair);
		
		
		
		
		panel = new BackgroundPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 52, 644, 420);
		panel.setLayout(new WrapLayout(FlowLayout.LEFT, 15, 15));
		contentPane.add(panel);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null); 
		setVisible(true);
	    setResizable(false);
	    
	}//Fim Construtor
	
	public void actionPerformed (ActionEvent e){
		
		if (e.getSource() == btnSair){
			
			dispose();
			
		}
		
		
	}//Fim Action

}//Fim Classe
