package dropper.view;

import dropper.BEAM.*;
import dropper.DAO.DAOUsuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dropper.control.*;
import dropper.interfaces.BackgroundPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JTable;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;

@SuppressWarnings({"serial", "unused"})
public class Inicial extends JFrame implements ActionListener, MouseListener, DropTargetListener  {

	private JPanel contentPane, panel=null;
	
	JToolBar toolBar;
	JButton btnDownload, btnRemover, btnPrograma;
	
	
	JToggleButton btnImagem, btnDocumento, btnVideo, btnAudio;
	
	//JButton[] buttons=null;
	List<JButton> buttons = null;
	
	
	int botaoSel = 0; //Selecionador de botões
	String tipo = "arquivo";
	String login = null;
	int db = 0;
	
	//MyDragDropListener myDragDropListener;
	JFileChooser jc = null;
	private TrayIcon trayicon;
	
	Usuario usuarioMaster = null; 
	
	List<dropper.BEAM.Arquivo> arquivos = null;
	List<dropper.BEAM.Audio> audios = null;
	List<dropper.BEAM.Video> videos = null;
	List<dropper.BEAM.Imagem> imagens = null;
	
	dropper.DAO.DAOArquivo daoarquivo = null;
	dropper.DAO.DAOVideo daovideo = null;
	dropper.DAO.DAOAudio daoaudio = null;
	dropper.DAO.DAOImagem daoimagem = null;
	
	
	private JLabel lblVersoAlpha;
	private Component horizontalStrut;
	private JButton btnAmigos;
	private JSeparator separator;
	private JSeparator separator_1;
	private JButton btnShare;
	private JSeparator separator_2;
	private JLabel lblServidor;
		
	
	public Inicial(int db, String login) {
	
		
		setTitle("Dropper - Armazenamento e Compartilhamento de Arquivos Multimidia");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicial.class.getResource("/Icones/App-miscellaneous-icon.png")));
		
		try{
			//Inicializa o Usuario Logado
			

			DAOUsuario daousuario = new DAOUsuario();
			usuarioMaster = daousuario.consulta(login);
			
			
		}catch(Exception e){
			
			System.err.println("Erro ao consultar usuario master!");
			e.printStackTrace();
			
		}
		
		
		
		
		this.db = db;
		this.login = login;
		
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		toolBar = new JToolBar();
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toolBar.setBounds(0, 0, 644, 54);
		contentPane.add(toolBar);
		
		btnImagem = new JToggleButton("");
		btnImagem.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1393046713_diagram-23.png")));
		btnImagem.addActionListener(this);
		btnImagem.setSelected(false);
		btnImagem.setFocusable(false);
		
		btnDocumento = new JToggleButton("");
		btnDocumento.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1393048968_diagram-30.png")));
		btnDocumento.addActionListener(this);
		btnDocumento.setSelected(true);
		btnDocumento.setFocusable(false);
		
		
		toolBar.add(btnDocumento);
		toolBar.add(btnImagem);
		
		btnAudio = new JToggleButton("");
		btnAudio.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1393046727_diagram-25.png")));
		btnAudio.addActionListener(this);
		btnAudio.setFocusable(false);
		btnAudio.setSelected(false);
		toolBar.add(btnAudio);
		
		btnVideo = new JToggleButton("");
		btnVideo.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1393046739_diagram-26.png")));
		btnVideo.addActionListener(this);
		btnVideo.setFocusable(false);
		btnAudio.setSelected(false);
		toolBar.add(btnVideo);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);
		
		btnRemover = new JButton("");
		btnRemover.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1397530702_trash_can.png")));
		btnRemover.setFocusable(false);
		btnRemover.addActionListener(this);
		
		btnShare = new JButton("");
		btnShare.setEnabled(false);
		btnShare.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1397530380_sharemanager.png")));
		btnShare.setFocusable(false);
		btnShare.addActionListener(this);
		toolBar.add(btnShare);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_2);
		toolBar.add(btnRemover);
		
		btnDownload = new JButton("");
		btnDownload.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1394353513_download-alt.png")));
		btnDownload.setFocusable(false);
		btnDownload.setEnabled(false);
		toolBar.add(btnDownload);
		btnDownload.addActionListener(this);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_1);
		
		horizontalStrut = Box.createHorizontalStrut(200);
		toolBar.add(horizontalStrut);
		
		btnAmigos = new JButton("");
		//btnAmigos.setEnabled(false);
		btnAmigos.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/Untitled-1.fw.png")));
		btnAmigos.setFocusable(false);
		btnAmigos.addActionListener(this);
		toolBar.add(btnAmigos);
		
		inicializaDAO();
		atualizaPanel();
		
		inicializaTray();
		
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null); 
		setVisible(true);
	    setResizable(false);
		
	}//Fim Construtor
	
	
	public void inicializaDAO(){
		
		 daoarquivo = new dropper.DAO.DAOArquivo();
		 daovideo = new dropper.DAO.DAOVideo();
		 daoaudio = new dropper.DAO.DAOAudio();
		 daoimagem = new dropper.DAO.DAOImagem();
		
		
		 //Configuração de usuario
		 daoarquivo.setUsuario(usuarioMaster);
		 daovideo.setUsuario(usuarioMaster);
		 daoaudio.setUsuario(usuarioMaster);
		 daoimagem.setUsuario(usuarioMaster);
		 
		 
	}//Fim InicializaDAO
	
	
	public void inicializaTray(){
		
		ImageIcon icon = new ImageIcon("/Icones/App-miscellaneous-icon.png");
		trayicon = new TrayIcon(icon.getImage());
		trayicon.addMouseListener(new MouseAdapter() {
			
			
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 2) {
					SystemTray.getSystemTray().remove(trayicon);
					setVisible(true);
				}
				
			}
			
			
			
			
		});
		
		addWindowListener(new WindowAdapter() {
			
			public void windowIconified(WindowEvent e){
				try{
					
					dispose();
					SystemTray.getSystemTray().add(trayicon);
					
				}catch(Exception trayexception){
					
					
				}
				
			}
			
			
			
		});
		
		
	}
	
	
	public void actionPerformed (ActionEvent e){
		
		if(e.getSource() == btnAmigos){
			//System.out.println("Clicou em amigos: "+login);
			try {
				
				Amigos amigos = new Amigos(login);
				
			} catch (SQLException e1) {
				
				System.err.println("Erro ao iniciar a tela de amigos!");
				e1.printStackTrace();
			}
			
			
		}
		
		
		if(e.getSource() == btnPrograma){
			
		}
		
		if (e.getSource() == btnImagem){
			
			btnImagem.setSelected(true);
			btnDocumento.setSelected(false);
			btnVideo.setSelected(false);
			btnAudio.setSelected(false);
			btnDownload.setEnabled(false);
			this.tipo = "imagem";
			
			panel.setVisible(false);
			
			//Mudou de Panel, reseta o selecionador de botões
			botaoSel=0;
			
			atualizaPanel();
		}
		
		if (e.getSource() == btnDocumento){
			
			btnImagem.setSelected(false);
			btnDocumento.setSelected(true);
			btnVideo.setSelected(false);
			btnAudio.setSelected(false);
			btnDownload.setEnabled(false);
			this.tipo = "arquivo";
			
			
			panel.setVisible(false);
			
			//Mudou de Panel, reseta o selecionador de botões
			botaoSel=0;
			
			atualizaPanel();
		}
		
		if (e.getSource() == btnAudio){
			
			btnImagem.setSelected(false);
			btnDocumento.setSelected(false);
			btnVideo.setSelected(false);
			btnAudio.setSelected(true);
			btnDownload.setEnabled(false);
			this.tipo="audio";

			
			panel.setVisible(false);
			
			//Mudou de Panel, reseta o selecionador de botões
			botaoSel=0;
			
			atualizaPanel();
			
		}
		
		if (e.getSource() == btnVideo){
			
			btnImagem.setSelected(false);
			btnDocumento.setSelected(false);
			btnVideo.setSelected(true);
			btnAudio.setSelected(false);
			btnDownload.setEnabled(false);
			this.tipo = "video";
			
			panel.setVisible(false);
			
			//Mudou de Panel, reseta o selecionador de botões
			botaoSel=0;
			
			atualizaPanel();
		}
		
		
		if (e.getSource() == btnRemover){
			
			try{
				
				if (tipo.equals("arquivo")){
					
					daoarquivo.setArquivo(arquivos.get(botaoSel)); 
					daoarquivo.remove();
					arquivos.remove(botaoSel);
										
				}
				else if (tipo.equals("audio")){
					
					daoaudio.setAudio(audios.get(botaoSel)); 
					daoaudio.remove();
					audios.remove(botaoSel);
					
				}
				else if (tipo.equals("imagem")){
					
					daoimagem.setImagem(imagens.get(botaoSel)); 
					daoimagem.remove();
					imagens.remove(botaoSel);
					
				}
				else if (tipo.equals("video")){
					
					daovideo.setVideo(videos.get(botaoSel)); 
					daovideo.remove();
					videos.remove(botaoSel);
					
				}
				
				buttons.get(botaoSel).setVisible(false);
				buttons.remove(botaoSel);
				
				panel.removeAll();
				panel.validate();
				botaoSel = 0;                							   //Reseta o Contador de botoes
				
				
			}catch(Exception e2){
				
				System.err.println("Erro ao invocar metodo de Remover Arquivos!");
				
				e2.printStackTrace();
				
			} finally{
				
				String resposta = "Arquivo Removido com Sucesso!";
				JOptionPane.showMessageDialog(null, resposta);
				
				panel.setVisible(false);
				System.out.println("Entrou no finally");
				atualizaPanel();
				btnDownload.setEnabled(false);
				
            }
			
		}//Fim btnRemover
		
		if (e.getSource() == btnDownload){
			
			jc = new JFileChooser();
			jc.setFileSelectionMode(1);
			jc.showOpenDialog(this);
			File file = jc.getSelectedFile();
			String path=null;
			if(file!=null){
				path = file.getPath();
				System.out.println(path);
			}
			
			System.out.println(botaoSel);
			try {
				
				if (tipo.equals("arquivo")){
					
					daoarquivo.setArquivo(arquivos.get(botaoSel)); 
					daoarquivo.download(path);
					
										
				}
				else if (tipo.equals("audio")){
					
					daoaudio.setAudio(audios.get(botaoSel)); 
					daoaudio.download(path);
					
				}
				else if (tipo.equals("imagem")){
					
					daoimagem.setImagem(imagens.get(botaoSel)); 
					daoimagem.download(path);
									
				}
				else if (tipo.equals("video")){
					
					daovideo.setVideo(videos.get(botaoSel)); 
					daovideo.download(path);
					
				}
				
			} catch (Exception e1) {
				System.err.println("Erro ao invocar metodo de recuperar ");
				
				e1.printStackTrace();
				
			}finally{
				
				String resposta = "Arquivo baixado com Sucesso!";
				JOptionPane.showMessageDialog(null, resposta);
			}
		
		}
		
		if (e.getSource() == panel){
			System.out.println("Movido com sucesso!");
		}
		
		
		}//Fim Action

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Click!");
		System.out.println(botaoSel);
		btnDownload.setEnabled(true);
		
		buttons.get(botaoSel).setEnabled(true);
		int cont=0;
		for (JButton i : buttons){
			
			if (e.getSource() == i){
				System.out.println("Botão "+cont);
				i.setEnabled(false);
				botaoSel = cont;
			}
			
			cont++;
		}//Fim For
		
		
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

	
	
	//---------Drag 'n Drop
	
	
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(DropTargetDropEvent event) {
		 // Accept copy drops
        event.acceptDrop(DnDConstants.ACTION_COPY);

        // Get the transfer which can provide the dropped item data
        Transferable transferable = event.getTransferable();

        // Get the data formats of the dropped item
        DataFlavor[] flavors = transferable.getTransferDataFlavors();

        // Loop through the flavors
        for (DataFlavor flavor : flavors) {

            try {

                // If the drop items are files
                if (flavor.isFlavorJavaFileListType()) {

                    // Get all of the dropped files
                    @SuppressWarnings({ "unchecked", "rawtypes" })
					List<File> files = (List) transferable.getTransferData(flavor);

                    // Loop them through
                    for (File file : files) {

                        // Print out the file path
                        System.out.println("File path is '" + file.getPath() + "'.");
                       
                        Thread t1 = new Thread (new TelaProgresso(file, tipo));
                        t1.start();
                                                                      
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
            	
            }//Fim Finnaly
        }

        // Inform that the drop is complete
       event.dropComplete(true);
       
       
       
		
	}
		
	
	public void atualizaPanel(){
		
		//Se Já existir algum panel, ele é removido
		if (panel!=null){
			System.out.println("Entrou no if");
			contentPane.remove(panel);
		}
		
		panel = new BackgroundPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 52, 644, 420);
		
		System.out.println("Tipo selecionado: "+this.tipo);
		
		new DropTarget(panel, this);
	    panel.setLayout(new WrapLayout(FlowLayout.LEFT, 15, 15));
	   
	    btnDownload.setEnabled(false);
	    
		try{
			
			buttons = new ArrayList<JButton>();
			
			String icone = "";
			if (tipo.equals("arquivo")){
				icone = "/Icones/1397589565_New.png";
				
				daoarquivo.consulta();
				arquivos = daoarquivo.getArquivos();
				
				int cont=0;
				for (Arquivo i : arquivos){
					
					btnPrograma = new JButton(i.getNome());
					btnPrograma.setIcon(new ImageIcon(Inicial.class.getResource(icone)));
					btnPrograma.setVerticalTextPosition(SwingConstants.BOTTOM); 
					btnPrograma.setHorizontalTextPosition(SwingConstants.CENTER);
					btnPrograma.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
					btnPrograma.setBorderPainted(false);
					btnPrograma.setContentAreaFilled(false);
					btnPrograma.setBounds(60, 382, 62, 50);
					btnPrograma.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
					btnPrograma.setFocusable(false);
					btnPrograma.addMouseListener(this);
					
					GridBagConstraints gbc_labelnome = new GridBagConstraints();
					gbc_labelnome.insets = new Insets(0, 0, 0, 5);
					
					btnPrograma.setVisible(true);
					btnPrograma.setBorder(null);
					buttons.add(btnPrograma);
					
					panel.add(btnPrograma, gbc_labelnome);
					System.out.println("Adicionou "+i.getNome()+" ao panel");
					btnPrograma.validate();
						
					cont++;
					panel.validate();
						
				}//Fim for
				
								
			}else if (tipo.equals("imagem")){
				icone = "/Icones/1393046713_diagram-23.png";
				
				daoimagem.consulta();
				imagens = daoimagem.getImagens();
				
				int cont=0;
				for (Imagem i : imagens){
					
					btnPrograma = new JButton(i.getNome());
					btnPrograma.setIcon(new ImageIcon(Inicial.class.getResource(icone)));
					btnPrograma.setVerticalTextPosition(SwingConstants.BOTTOM); 
					btnPrograma.setHorizontalTextPosition(SwingConstants.CENTER);
					btnPrograma.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
					btnPrograma.setBorderPainted(false);
					btnPrograma.setContentAreaFilled(false);
					btnPrograma.setBounds(60, 382, 62, 50);
					btnPrograma.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
					btnPrograma.setFocusable(false);
					btnPrograma.addMouseListener(this);
					
					GridBagConstraints gbc_labelnome = new GridBagConstraints();
					gbc_labelnome.insets = new Insets(0, 0, 0, 5);
					
					btnPrograma.setVisible(true);
					btnPrograma.setBorder(null);
					buttons.add(btnPrograma);
					
					panel.add(btnPrograma, gbc_labelnome);
					System.out.println("Adicionou "+i.getNome()+" ao panel");
					btnPrograma.validate();
						
					cont++;
					panel.validate();
						
				}//Fim for
				
			}else if (tipo.equals("video")){
				icone = "/Icones/1393046739_diagram-26.png";
				
				daovideo.consulta();
				videos = daovideo.getVideos();
				
				int cont=0;
				for (Video i : videos){
					
					btnPrograma = new JButton(i.getNome());
					btnPrograma.setIcon(new ImageIcon(Inicial.class.getResource(icone)));
					btnPrograma.setVerticalTextPosition(SwingConstants.BOTTOM); 
					btnPrograma.setHorizontalTextPosition(SwingConstants.CENTER);
					btnPrograma.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
					btnPrograma.setBorderPainted(false);
					btnPrograma.setContentAreaFilled(false);
					btnPrograma.setBounds(60, 382, 62, 50);
					btnPrograma.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
					btnPrograma.setFocusable(false);
					btnPrograma.addMouseListener(this);
					
					GridBagConstraints gbc_labelnome = new GridBagConstraints();
					gbc_labelnome.insets = new Insets(0, 0, 0, 5);
					
					btnPrograma.setVisible(true);
					btnPrograma.setBorder(null);
					buttons.add(btnPrograma);
					
					panel.add(btnPrograma, gbc_labelnome);
					System.out.println("Adicionou "+i.getNome()+" ao panel");
					btnPrograma.validate();
						
					cont++;
					panel.validate();
						
				}//Fim for
				
			}else if (tipo.equals("audio")){				
				icone = "/Icones/1393046727_diagram-25.png";
				
				daoaudio.consulta();
				audios = daoaudio.getAudios();
				
				int cont=0;
				for (Audio i : audios){
					
					btnPrograma = new JButton(i.getNome());
					btnPrograma.setIcon(new ImageIcon(Inicial.class.getResource(icone)));
					btnPrograma.setVerticalTextPosition(SwingConstants.BOTTOM); 
					btnPrograma.setHorizontalTextPosition(SwingConstants.CENTER);
					btnPrograma.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
					btnPrograma.setBorderPainted(false);
					btnPrograma.setContentAreaFilled(false);
					btnPrograma.setBounds(60, 382, 62, 50);
					btnPrograma.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
					btnPrograma.setFocusable(false);
					btnPrograma.addMouseListener(this);
					
					GridBagConstraints gbc_labelnome = new GridBagConstraints();
					gbc_labelnome.insets = new Insets(0, 0, 0, 5);
					
					btnPrograma.setVisible(true);
					btnPrograma.setBorder(null);
					buttons.add(btnPrograma);
					
					panel.add(btnPrograma, gbc_labelnome);
					System.out.println("Adicionou "+i.getNome()+" ao panel");
					btnPrograma.validate();
						
					cont++;
					panel.validate();
						
				}//Fim for
			}
			
			lblVersoAlpha = new JLabel("Vers\u00E3o Alpha");
			lblVersoAlpha.setFont(new Font("Trajan Pro", Font.ITALIC, 12));
			lblVersoAlpha.setBounds(532, 430, 101, 30);
			contentPane.add(lblVersoAlpha);
			
			lblServidor = new JLabel("Servidor");
			lblServidor.setFont(new Font("Trajan Pro", Font.ITALIC, 11));
			lblServidor.setBounds(532, 410, 101, 24);
			contentPane.add(lblServidor);
			if(db == 0)lblServidor.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1386744919_Circle_Green.png")));
			else lblServidor.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1386744931_Circle_Red.png")));
			
			contentPane.add(panel);
			contentPane.validate();
				
			validate();
			
	    }catch(Exception ee){
	    
	    	System.out.println("Nem terminou o try");
	    	ee.printStackTrace();
	    }
		
		
	}//Fim Atualiza Panel
	
	//InnerClass TelaProgresso
	public class TelaProgresso extends JFrame implements Runnable, ActionListener{

		private JPanel contentPane;
		private JLabel lblNome, lblTamanho, lblTipo,
					   lblRNome, lblRTamanho, lblRTipo, lblStatus;
		private JButton btnAceitar, btnCancelar;
		JProgressBar progressBar;
				
		public File file;
		public String tipo;
		
		public TelaProgresso(File file, String tipo) {
		
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

		@Override
		public void run() {
			
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
				
			}
			
		}//Fim run
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == btnAceitar){
				
				atualizaPanel();				
				dispose();
			}
			
		}//Fim Eventos
		
		public void commit(){
			
			try{
				
				if (tipo.equals("arquivo")){
					dropper.DAO.DAOArquivo daoarquivo = new dropper.DAO.DAOArquivo(file);
					daoarquivo.setUsuario(usuarioMaster);
					daoarquivo.grava();					
				}else if (tipo.equals("imagem")){
					dropper.DAO.DAOImagem daoimagem = new dropper.DAO.DAOImagem(file);
					daoimagem.setUsuario(usuarioMaster);
					daoimagem.grava();					
				}else if (tipo.equals("video")){
					dropper.DAO.DAOVideo daovideo = new dropper.DAO.DAOVideo(file);
					daovideo.setUsuario(usuarioMaster);
					daovideo.grava();					
				}else if (tipo.equals("audio")){
					dropper.DAO.DAOAudio daoaudio = new dropper.DAO.DAOAudio(file);
					daoaudio.setUsuario(usuarioMaster);
					daoaudio.grava();					
				}
				
				btnAceitar.setEnabled(true);
				btnCancelar.setEnabled(false);
				lblStatus.setForeground(Color.GREEN);
				lblStatus.setText("Arquivo gravado com sucesso!");
				
				progressBar.setIndeterminate(false);
				
				
			}catch (Exception e){
				e.printStackTrace();
				System.err.println("Erro no método InsereBD!");
			}
			
		}
		
	}//Fim InnerClass TelaProgresso
	
	
}//Fim Classe
	


	

