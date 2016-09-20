package dropper.sandbox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

@SuppressWarnings({"serial", "unused"})
public class CadastroAmigo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	
	public CadastroAmigo() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(CadastroAmigo.class.getResource("/Amigos/1384569162_Check.jpg")));
		btnNewButton.setBounds(292, 166, 36, 36);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(CadastroAmigo.class.getResource("/Amigos/1384569248_Cancel.jpg")));
		button.setBounds(338, 166, 36, 36);
		contentPane.add(button);
		
		setVisible(true);
		
	}//Fim Construtor
}//Fim Classe
