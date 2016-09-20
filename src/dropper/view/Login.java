package dropper.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dropper.control.Conexao;
import dropper.control.ValidaLogin_controle;
import dropper.interfaces.BackgroundPanel;

import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JRadioButton;

@SuppressWarnings({"serial", "unused"})
public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPanel panel = null;
	private JTextField tfUsuario;
	private JTextField tfSenha;
	
	private JButton btnLogin;
	
	private JRadioButton rdbtnLocal, rdbtnRemoto; 

	private TrayIcon trayicon;
	
	public Login() {
		
		setTitle("Dropper");
		ImageIcon icon = new ImageIcon("/Icones/App-miscellaneous-icon.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Icones/App-miscellaneous-icon.png")));
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new BackgroundPanel(5);
		panel.setBounds(0, 0, 344, 362);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		tfUsuario.setForeground(Color.LIGHT_GRAY);
		tfUsuario.setText("Usu\u00E1rio");
		tfUsuario.setBounds(10, 180, 324, 47);
		tfUsuario.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 1) {
					tfUsuario.setText("");
				}
				
			}
			
		});
		panel.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfSenha = new JTextField();
		tfSenha.setForeground(Color.LIGHT_GRAY);
		tfSenha.setText("Senha");
		tfSenha.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		tfSenha.setColumns(10);
		tfSenha.setBounds(10, 238, 324, 47);
		tfSenha.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 1) {
					tfSenha.setText("");
				}
				
			}
			
		});
		panel.add(tfSenha);
		
		btnLogin = new JButton("Login >>>");
		btnLogin.setFont(new Font("Trajan Pro", Font.BOLD, 12));
		btnLogin.setBounds(229, 321, 105, 34);
		btnLogin.addActionListener(this);
		panel.add(btnLogin);
		
		rdbtnLocal = new JRadioButton("Local");
		rdbtnLocal.setBounds(10, 306, 109, 23);
		rdbtnLocal.addActionListener(this);
		panel.add(rdbtnLocal);
		
		rdbtnRemoto = new JRadioButton("Remoto");
		rdbtnRemoto.setBounds(10, 332, 109, 23);
		rdbtnRemoto.addActionListener(this);
		panel.add(rdbtnRemoto);
		
		
		rdbtnLocal.setSelected(true);
		
		JLabel lblIcone = new JLabel("");
		lblIcone.setIcon(new ImageIcon(Login.class.getResource("/Icones/logo.fw.png")));
		lblIcone.setBounds(55, 11, 235, 140);
		panel.add(lblIcone);
		
		
		//System Tray
		//Cria a ST
		trayicon = new TrayIcon(icon.getImage());
		trayicon.addMouseListener(new MouseAdapter() {
			
			
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 2) {
					SystemTray.getSystemTray().remove(trayicon);
					setVisible(true);
				}
				
			}
			
			
		});
		//Fim Cria a ST
		
		addWindowListener(new WindowAdapter() {
			
			public void windowIconified(WindowEvent e){
				adicionarTray();
				
			}
			
			
			
		});
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); 
		setVisible(true);
		setResizable(false);
			
		
		
	}//Fim Construtor
	
	
	public void actionPerformed (ActionEvent e){
		
		if (e.getSource() == btnLogin){
			
			ValidaLogin_controle vlc = new ValidaLogin_controle(tfUsuario.getText(), tfSenha.getText());
			System.out.println("Usuário: "+tfUsuario.getText());
			System.out.println("Senha: "+tfSenha.getText());
			
			if (vlc.valida()){
				
				if (rdbtnLocal.isSelected()){
					
					Inicial ob = new Inicial(1,tfUsuario.getText() );
					dispose();
					
				}else{
					
					Inicial ob = new Inicial(0,tfUsuario.getText());
					dispose();
				}
				
			}else{
				System.out.println("Usuário Errado");
			}
				
				
				
				
			
			
			
		}//Fim btnLogin
		
		if (e.getSource() == rdbtnLocal){
			rdbtnLocal.setSelected(true);
			rdbtnRemoto.setSelected(false);
		}
		if (e.getSource() == rdbtnRemoto){
			rdbtnLocal.setSelected(false);
			rdbtnRemoto.setSelected(true);
		}
		
		
	}//Fim Action
	
	
	private void adicionarTray(){
		
		try{
			
			dispose();
			SystemTray.getSystemTray().add(trayicon);
			
		}catch(Exception e){
			
			
		}
		
		
	}
}//Fim Classe
