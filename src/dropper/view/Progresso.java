package dropper.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;



//import dropper.model.InsereBD;
@SuppressWarnings({"serial", "unused"})
public class Progresso extends JFrame implements Runnable, ActionListener{

	private JPanel contentPane;

	private JLabel lblNome, lblTamanho, lblTipo,
				   lblRNome, lblRTamanho, lblRTipo, lblStatus;

	private JButton btnAceitar, btnCancelar;
	
	JProgressBar progressBar;
	
	
	public File file;
	public String tipo;
	
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public Progresso(File file, String tipo) {
	
		try {
		
			
			
			this.file = file;
			this.tipo = tipo;
			
			setTitle("Progresso");
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 399, 283);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			progressBar = new JProgressBar();
			progressBar.setBounds(10, 11, 373, 34);
			progressBar.setIndeterminate(true);
			contentPane.add(progressBar);
			
			btnAceitar = new JButton("");
			btnAceitar.setEnabled(false);
			btnAceitar.setIcon(new ImageIcon(Progresso.class.getResource("/Icones/Gravar.png")));
			btnAceitar.setBounds(315, 221, 29, 23);
			btnAceitar.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			btnAceitar.setBorderPainted(false);
			btnAceitar.setContentAreaFilled(false);
			btnAceitar.setFocusable(false);
			btnAceitar.addActionListener(this);
			contentPane.add(btnAceitar);
			
			btnCancelar = new JButton("");
			btnCancelar.setIcon(new ImageIcon(Progresso.class.getResource("/Icones/Cancelar.png")));
			btnCancelar.setBounds(354, 221, 29, 23);
			btnCancelar.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			btnCancelar.setBorderPainted(false);
			btnCancelar.setContentAreaFilled(false);
			btnCancelar.setFocusable(false);
			btnCancelar.addActionListener(this);
			contentPane.add(btnCancelar);
			
			lblNome = new JLabel("Nome:");
			lblNome.setBounds(27, 80, 46, 14);
			contentPane.add(lblNome);
			
			lblTamanho = new JLabel("Tamanho:");
			lblTamanho.setBounds(11, 105, 56, 14);
			contentPane.add(lblTamanho);
			
			lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(35, 130, 46, 14);
			contentPane.add(lblTipo);
			
			lblRNome = new JLabel("New label");
			lblRNome.setBounds(83, 80, 300, 14);
			contentPane.add(lblRNome);
			
			lblRTamanho = new JLabel("New label");
			lblRTamanho.setBounds(83, 105, 300, 14);
			contentPane.add(lblRTamanho);
			
			lblRTipo = new JLabel("New label");
			lblRTipo.setBounds(83, 130, 300, 14);
			contentPane.add(lblRTipo);
			
			lblStatus = new JLabel("Gravando...");
			lblStatus.setBounds(10, 230, 295, 14);
			contentPane.add(lblStatus);
			
			
			
			this.setLocationRelativeTo(null); 
			setVisible(true);
		    setResizable(false);
			
			
			
		
		} catch (Exception e) {
			
		
		}finally{
			
			
			
		}
		
	
	}//Construtor


	public Progresso(String title){
	
		criaLayout();
		setTitle(title);
		
	}


	@Override
	public void run() {
		
		//criaLayout();
		try {
			
			
			lblRNome.setText(file.getName());
			lblRTamanho.setText(Integer.toString((int) file.length())+" Bytes");
			lblRTipo.setText(tipo);
			Thread.sleep(3*1000);
			commit();
			
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		finally{
			
			//notify();
			
		}
		
		
		
		//commit();
		
		
	}//Fim run
	
	public void criaLayout(){
		
		
		setTitle("Progresso");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 11, 373, 34);
		progressBar.setIndeterminate(true);
		contentPane.add(progressBar);
		
		btnAceitar = new JButton("");
		btnAceitar.setEnabled(false);
		btnAceitar.setIcon(new ImageIcon(Progresso.class.getResource("/Icones/Gravar.png")));
		btnAceitar.setBounds(315, 221, 29, 23);
		btnAceitar.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		btnAceitar.setBorderPainted(false);
		btnAceitar.setContentAreaFilled(false);
		btnAceitar.setFocusable(false);
		btnAceitar.addActionListener(this);
		contentPane.add(btnAceitar);
		
		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Progresso.class.getResource("/Icones/Cancelar.png")));
		btnCancelar.setBounds(354, 221, 29, 23);
		btnCancelar.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		btnCancelar.setBorderPainted(false);
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setFocusable(false);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(27, 80, 46, 14);
		contentPane.add(lblNome);
		
		lblTamanho = new JLabel("Tamanho:");
		lblTamanho.setBounds(11, 105, 56, 14);
		contentPane.add(lblTamanho);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(35, 130, 46, 14);
		contentPane.add(lblTipo);
		
		lblRNome = new JLabel("New label");
		lblRNome.setBounds(83, 80, 300, 14);
		contentPane.add(lblRNome);
		
		lblRTamanho = new JLabel("New label");
		lblRTamanho.setBounds(83, 105, 300, 14);
		contentPane.add(lblRTamanho);
		
		lblRTipo = new JLabel("New label");
		lblRTipo.setBounds(83, 130, 300, 14);
		contentPane.add(lblRTipo);
		
		lblStatus = new JLabel("Gravando...");
		lblStatus.setBounds(10, 230, 295, 14);
		contentPane.add(lblStatus);
		
		
		
		this.setLocationRelativeTo(null); 
		setVisible(true);
	    setResizable(false);
	   
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnAceitar){
			
			dispose();
		}
		
		
	}//Fim Eventos
	
	
	public void commit(){
		
		try{
			
			//Thread t1 = new Thread (new InsereBD(file, tipo));
		//	t1.start();
			//InsereBD ibd = new InsereBD(file, tipo);
			//ibd.Insere();
			
			
			//while (t1.isAlive()){}//Pause
			
			
			//Thread.sleep(5000);
			
			
			btnAceitar.setEnabled(true);
			btnCancelar.setEnabled(false);
			lblStatus.setForeground(Color.GREEN);
			lblStatus.setText("Arquivo gravado com sucesso!");
			
			progressBar.setIndeterminate(false);
			//progressBar.validate();
			//validate();
			//contentPane.validate();
			
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println("Erro no método InsereBD!");
		}
		

	}
	
}//Fim Classe
