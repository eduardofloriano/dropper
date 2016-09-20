package dropper.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;

import java.awt.Label;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.JButton;

import dropper.BEAM.Usuario;
import dropper.DAO.DAOAmigos;
import dropper.DAO.DAOUsuario;

import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.SwingConstants;

import java.awt.Toolkit;

@SuppressWarnings({ "serial", "unused" })
public class Amigos extends JFrame implements ActionListener,MouseListener{

	private JPanel contentPane;
	private JButton btnAdicionar, btnProcurar, btnVoltar, btnAvancar;
	JLabel lblPagina;
	
	private dropper.BEAM.Usuario usuarioLogado = null;
	private int numAmigos = 0; //Numero de amigos
	private dropper.BEAM.Usuario usuario;
	private dropper.BEAM.Usuario amigo;
	private List<dropper.BEAM.Usuario> usuarios = null;
	private String login;
	private int pagina = 1;
	private int iamigo = 0; //Iterator da lista de amigos
	private int numpaginas = 1;
	private int reset = 0; //Reseta o array de usuarios
	
	private List<JPanel> panels = null;
	private List<JButton> buttons = null; //Botoes Avancar e Voltar
	private List<JButton> buttonsShare = null; //Botoes de Compartilhar

	public Amigos(String login) throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Amigos.class.getResource("/Icones/Untitled-1.fw.png")));
		
		this.login = login;
		
		setBounds(100, 100, 300, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panels = new ArrayList<JPanel>();
		buttons = new ArrayList<JButton>();
		buttonsShare = new ArrayList<JButton>();
		
		make();
		
		//Adiciona Amigos;
		//Máximo de 4 amigos por página
		DAOUsuario dao2 = new DAOUsuario();
		DAOAmigos dao3 = new DAOAmigos(dao2.consulta(login));
		usuarios = dao3.consulta();
		
	
		if ( usuarios.size() % 4 == 0 ) numpaginas = (( (int)usuarios.size()/4));
		else numpaginas = (( (int)usuarios.size()/4)+1);
		if (numpaginas > 1) btnAvancar.setEnabled(true);
		
		
		addAmigos();
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); 
		setVisible(true);
		//setResizable(false);
		
	}//Fim Construtor

	
	public void addAmigos(){
		
		numAmigos=0;
		//Recria o array de buttons
		buttonsShare = new ArrayList<JButton>();
				
		int cont = 0;
		while (cont < 4 && iamigo <= usuarios.size()-1){
			
			usuario = usuarios.get(iamigo);
			addAmigo(usuario.getNome());
			
			iamigo++;
			cont++;
		}
		
	}//Fim addAmigos
	
	
	public void addAmigo(String nome){
				
		int distancia = 60;
		
		for (int i=0; i<numAmigos; i++){
			distancia += 60;
			
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(0, distancia, 284, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		panels.add(panel);

		JLabel lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Amigos.class.getResource("/Amigos/user.png")));
		lblStatus.setBounds(10, 18, 24, 24);
		panel.add(lblStatus);
		
		JLabel label = new JLabel(nome);
		label.setFont(new Font("Trebuchet MS", Font.ITALIC, 12));
		label.setBounds(44, 18, 230, 24);
		panel.add(label);
		
		JButton btnRemover = new JButton("");
		btnRemover.setIcon(new ImageIcon(Amigos.class.getResource("/Amigos/1384569256_Delete.jpg")));
		btnRemover.setBounds(245, 18, 24, 24);
		btnRemover.setFocusable(false);
		panel.add(btnRemover);
		
		JButton btnShare = new JButton("");
		btnShare.setIcon(new ImageIcon(Amigos.class.getResource("/Amigos/sharefolder.png")));
		btnShare.setBounds(205, 18, 24, 24);
		btnShare.setBorderPainted(false);
		btnShare.setContentAreaFilled(false);
		btnShare.setFocusable(false);
		btnShare.addMouseListener(this);
		buttonsShare.add(btnShare);
		panel.add(btnShare);
		
		numAmigos ++;
		
		
	}
	
	
	public void make(){
		
		setTitle("Amigos");
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, Color.BLACK));
		panel.setBounds(0, 0, 284, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Amigos.class.getResource("/Amigos/user.png")));
		lblStatus.setBounds(10, 18, 24, 24);
		panel.add(lblStatus);
		
		JLabel label = new JLabel("<Usu\u00E1rio>");
		label.setFont(new Font("Trebuchet MS", Font.ITALIC, 12));
		label.setBounds(44, 18, 230, 24);
		panel.add(label);
		
		lblPagina = new JLabel("1");
		lblPagina.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
		lblPagina.setBounds(131, 309, 26, 36);
		contentPane.add(lblPagina);
		
		btnAdicionar = new JButton("");
		btnAdicionar.setIcon(new ImageIcon(Amigos.class.getResource("/Amigos/1384569158_Add.png")));
		btnAdicionar.setBounds(245, 18, 24, 24);
		btnAdicionar.setFocusable(false);
		btnAdicionar.addActionListener(this);
		panel.add(btnAdicionar);
		
		btnProcurar = new JButton("");
		btnProcurar.setIcon(new ImageIcon(Amigos.class.getResource("/Amigos/1384569181_Search.jpg")));
		btnProcurar.setBounds(205, 18, 24, 24);
		btnProcurar.setFocusable(false);
		panel.add(btnProcurar);
		
		
		btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(Amigos.class.getResource("/Setas/Voltar.fw.png")));
		btnVoltar.setBounds(10, 309, 36, 36);
		btnVoltar.setFocusable(false);	
		btnVoltar.setBorderPainted(false);
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setEnabled(false);
		btnVoltar.addActionListener(this);
		contentPane.add(btnVoltar);
		
		
		btnAvancar = new JButton("");
		btnAvancar.setIcon(new ImageIcon(Amigos.class.getResource("/Setas/Avancar.fw.png")));
		btnAvancar.setBounds(238, 309, 36, 36);
		btnAvancar.setFocusable(false);	
		btnAvancar.setBorderPainted(false);
		btnAvancar.setContentAreaFilled(false);
		btnAvancar.setEnabled(false);
		btnAvancar.addActionListener(this);
		contentPane.add(btnAvancar);
		
		buttons.add(btnAvancar);
		buttons.add(btnVoltar);
		//Monta o Cabeçalho
		try{
			
			DAOUsuario dao = new DAOUsuario();
			usuarioLogado = dao.consulta(login);
			
			label.setText(usuarioLogado.getNome());
			
		}catch (Exception e){
			System.err.println("Erro ao consultar usuario!");
			e.printStackTrace();
		}
		
		
	}//Fim Make
	
	public void clear(){
		
		for (JPanel i : panels){
			i.setVisible(false);
		}
		
		for (JButton i : buttons){
			i.setVisible(false);
		}
		
		contentPane.removeAll();
		contentPane.validate();
		validate();
		
	}//Fim Clear
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnAdicionar){
			CadastroAmigo ca = new CadastroAmigo();
			
				System.out.println("Cadastrou o Amigo");
				dispose();
				//Amigos amigos = new Amigos(login);
				
				System.out.println("Deu o Dispose!");
					
		}
				
		if (e.getSource() == btnAvancar){
			
			clear(); 
			make();  
			pagina++;
			reset = reset + 4;
			
			btnVoltar.setEnabled(true);
			if (pagina==numpaginas) btnAvancar.setEnabled(false);
			else btnAvancar.setEnabled(true);
			lblPagina.setText(""+pagina);
			
			addAmigos();
			
		}
		
		if (e.getSource() == btnVoltar){
			
			clear();
			make();
			pagina--;
			reset = reset - 4;
			
			btnAvancar.setEnabled(true);
			if (pagina==1) btnVoltar.setEnabled(false);
			else btnVoltar.setEnabled(true);
			lblPagina.setText(""+pagina);
						
			iamigo = iamigo - (numAmigos+4);
			
			addAmigos();
			
		}
		
		
		
	}//Fim ActionsEvents
	
	
	@Override	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//Botao Share
			int cont = reset;
			for (JButton i : buttonsShare ){
				if (e.getSource() == i){
						
					amigo = usuarios.get(cont);
					//Compartilha compartilha = new Compartilha(amigo.getNome());
					CompartilhaTree compartilha = new CompartilhaTree(amigo.getNome());
						
				}
				cont++;
					
			}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	//InnerClass
	public class CadastroAmigo extends JFrame implements ActionListener{

		private JPanel contentPane;
		private JTextField textField;
		private JButton btnGravar, btnCancelar; 
		
		public CadastroAmigo() {
			
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 400, 240);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setBounds(10, 11, 46, 14);
			contentPane.add(lblEmail);
			
			textField = new JTextField();
			textField.setBounds(47, 8, 327, 20);
			contentPane.add(textField);
			textField.setColumns(10);
			
			btnGravar = new JButton("");
			btnGravar.setIcon(new ImageIcon(CadastroAmigo.class.getResource("/Amigos/1384569162_Check.jpg")));
			btnGravar.setBounds(292, 166, 36, 36);
			btnGravar.addActionListener(this);
			contentPane.add(btnGravar);
			
			btnCancelar = new JButton("");
			btnCancelar.setIcon(new ImageIcon(CadastroAmigo.class.getResource("/Amigos/1384569248_Cancel.jpg")));
			btnCancelar.setBounds(338, 166, 36, 36);
			btnCancelar.addActionListener(this);
			contentPane.add(btnCancelar);
			
			this.setLocationRelativeTo(null); 
			setVisible(true);
			
			
		}//Fim Construtor
		
		public void actionPerformed(ActionEvent e){
			
			if (e.getSource() == btnGravar){
				
				try {
					
					DAOAmigos daoamigos = new DAOAmigos(usuarioLogado);
					daoamigos.cadastra(textField.getText());
					
					System.out.println("Gravado COm sucesso!");
					dispose();
				
					Amigos amigos = new Amigos(login);
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}//Fim try/catch
			}//Fim if
			
		}//Fim Actions
		
		
	}//Fim Classe
	
	
	
	
}//Fim Classe
