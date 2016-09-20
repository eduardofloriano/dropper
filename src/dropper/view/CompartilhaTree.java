package dropper.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import dropper.BEAM.Arquivo;
import dropper.BEAM.Usuario;
import dropper.DAO.DAOArquivo;
import dropper.DAO.DAOUsuario;
import dropper.interfaces.BackgroundPanel;
import dropper.interfaces.MyNode;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Font;
import java.io.File;
import java.util.List;

@SuppressWarnings({"serial", "unused"})
public class CompartilhaTree extends JFrame implements ActionListener, TreeSelectionListener {

	private JPanel contentPane, panel=null;
	private JToolBar toolBar;
	private JButton btnDownload, btnUsuario, btnSair;
	
	
	private Usuario usuario;
	private Component horizontalStrut;
	private String folha;
	private String parent;
	private int arraycount = 0;
	
	List<dropper.BEAM.Arquivo> arquivos = null;
	List<dropper.BEAM.Audio> audios = null;
	List<dropper.BEAM.Video> videos = null;
	List<dropper.BEAM.Imagem> imagens = null;
	
	dropper.DAO.DAOArquivo daoarquivo = null;
	dropper.DAO.DAOVideo daovideo = null;
	dropper.DAO.DAOAudio daoaudio = null;
	dropper.DAO.DAOImagem daoimagem = null;
	
	DefaultMutableTreeNode item = null;
	
	
	private JTree tree;
	private JFileChooser jc = null;
	private String tipo = null;
	
	
	public CompartilhaTree(String nome) {
		setTitle("Compartilhamento");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CompartilhaTree.class.getResource("/Amigos/sharefolder.png")));
		
		try{
			
			DAOUsuario daousuario = new DAOUsuario();
			usuario = daousuario.consultaNome(nome);
			
			
			
		}catch (Exception e){
			
			System.err.println("Erro ao consultar usuario!");
			e.printStackTrace();
		}
		
		
		setBounds(100, 100, 330, 500);
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
		btnUsuario.setIcon(new ImageIcon(CompartilhaTree.class.getResource("/Compartilha/usuario.png")));
		btnUsuario.setFocusable(false);
		toolBar.add(btnUsuario);
		btnUsuario.addActionListener(this);
		btnUsuario.setContentAreaFilled(false);
		btnUsuario.setFocusable(false);
		
		
		btnDownload = new JButton("");
		btnDownload.setIcon(new ImageIcon(Inicial.class.getResource("/Icones/1394353513_download-alt.png")));
		btnDownload.setFocusable(false);
		btnDownload.setEnabled(false);
		toolBar.add(btnDownload);
		btnDownload.addActionListener(this);
		
		btnSair= new JButton("");
		btnSair.setIcon(new ImageIcon(Inicial.class.getResource("/Compartilha/sair.png")));
		btnSair.setFocusable(false);
		btnSair.addActionListener(this);
		
		horizontalStrut = Box.createHorizontalStrut(90);
		toolBar.add(horizontalStrut);
		toolBar.add(btnSair);
				
		
		panel = new BackgroundPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 52, 324, 420);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		//------ Tree ------//
		
			//Criação da Tree
			DefaultMutableTreeNode top =
					new DefaultMutableTreeNode("Compartilhamento");
			createNodes(top);
			tree = new JTree(top);
			//JScrollPane treeView = new JScrollPane(tree);
			tree.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
			tree.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			tree.setBounds(10, 11, 304, 398);
			tree.setRootVisible(false);
			tree.setShowsRootHandles(true);
			tree.putClientProperty("JTree.lineStyle", "None");
			
			
			
			//Icones
			ImageIcon leafIcon = new ImageIcon("/Icones/1397589565_New.png");
			if (leafIcon != null) {
			    DefaultTreeCellRenderer renderer = 
			        new DefaultTreeCellRenderer();
			    renderer.setLeafIcon(leafIcon);
			    tree.setCellRenderer(renderer);
			}
			//Fim Icones
		
			//Ações & Listeners
			
			//Inicializa a Tree
			 tree.getSelectionModel().setSelectionMode
	            (TreeSelectionModel.SINGLE_TREE_SELECTION);
			 
			//Listener
			tree.addTreeSelectionListener(this);
			
			
			
			
		panel.add(tree);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null); 
		setVisible(true);
	    setResizable(false);
	    
	}//Fim Construtor
	
	public void actionPerformed (ActionEvent e){
		
		if (e.getSource() == btnSair){
			
			dispose();
			
		}
		
		if (e.getSource() == btnDownload){
			
			if(!folha.equals("Vazio")){
				
								
				jc = new JFileChooser();
				jc.setFileSelectionMode(1);
				jc.showOpenDialog(this);
				File file = jc.getSelectedFile();
				String path=null;
				if(file!=null){
					path = file.getPath();
					System.out.println(path);
				}
				
				//System.out.println(botaoSel);
				try {
					
					if (parent.equals("arquivo")){
						//System.out.println(locateArquivo(folha).getNome()+" Tamanho: "+locateArquivo(folha).getTamanho());
						//System.out.println(locateArquivo(folha).getId());
						//System.out.println(locateArquivo(folha).getNome());
						//System.out.println(locateArquivo(folha).getTamanho());
						//System.out.println(locateArquivo(folha).getDescricao());
						//System.out.println(locateArquivo(folha).getBytes());
						//System.out.println();
						//System.out.println(usuario.getNome());
						
						
						daoarquivo.setArquivo(locateArquivo(folha)); 
						daoarquivo.download(path);
						
											
					}
					else if (parent.equals("audio")){
						
						daoaudio.setAudio(locateAudio(folha)); 
						daoaudio.download(path);
						
					}
					else if (parent.equals("imagem")){
						
						daoimagem.setImagem(locateImagem(folha)); 
						daoimagem.download(path);
										
					}
					else if (parent.equals("video")){
					
						daovideo.setVideo(locateVideo(folha)); 
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
			
			
		}
		
	}//Fim Action
	
	
	public void valueChanged(TreeSelectionEvent e) {
		//Returns the last path element of the selection.
		//This method is useful only when the selection model allows a single selection.
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		                      tree.getLastSelectedPathComponent();
		    DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode)
                    tree.getLastSelectedPathComponent();

		   // MyNode node = (MyNode)
           //         tree.getLastSelectedPathComponent();

		    
		    if (node == null)
		    //Nothing is selected.     
		    return;

		    Object nodeInfo = node.getUserObject();
		    if (node.isLeaf()) {
		    	
		    	//btnDownload.setEnabled(true);
		    	arraycount = 0;
		    	//nodeParent = (DefaultMutableTreeNode)node.getParent();	 
		    	//parent = nodeParent
		    	
		    	//System.out.println(node.getRoot());
		    	//System.out.println(node.getParent());
		    	//System.out.println();
		    	
		    	
		    	folha = node.toString();
		    	parent = node.getParent().toString();
		    	parent = formataTipo(parent);
		    	
		    	//System.out.println(node.toString());
		    	//System.out.println("Tamanho do array: "+arquivos.size());
		    
		    	
		    	if (folha.equals("Vazio")){
		    		btnDownload.setEnabled(false);
		    	}else{
		    		btnDownload.setEnabled(true);
		    	}
		    	
		    	
		    } else {
		    	btnDownload.setEnabled(false);
		    	System.out.println(node.getChildCount());
		    	
		    	
		    }
		}
	
	
	//Nodes
	private void createNodes(DefaultMutableTreeNode top){
		
		System.out.println("Entrou no CreateNodes");
		DefaultMutableTreeNode category_arquivos = null;
		DefaultMutableTreeNode category_audios = null;
		DefaultMutableTreeNode category_imagens = null;
		DefaultMutableTreeNode category_videos = null;
		DefaultMutableTreeNode book = null;
		
		//MyNode category_arquivos = null;
		//MyNode category_audios = null;
		//MyNode category_imagens = null;
		//MyNode category_videos = null;
		//MyNode book = null;
		
		category_arquivos = new DefaultMutableTreeNode("Arquivos");
		category_audios = new DefaultMutableTreeNode("Audios");
		category_imagens = new DefaultMutableTreeNode("Imagens");
		category_videos = new DefaultMutableTreeNode("Videos");
		
		//category_arquivos = new MyNode("Arquivos");
		//category_audios = new MyNode("Audios");
		//category_imagens = new MyNode("Imagens");
		//category_videos = new MyNode("Videos");

		
		top.add(category_arquivos);
		top.add(category_audios);
		top.add(category_imagens);
		top.add(category_videos);
		
		
		boolean arvoreVazia = false; //0 para preenchida, 1 para vazia 
		//Consulta os Arquivos
		try{
			
			System.out.println("Entrou no Try");
			daoarquivo = new dropper.DAO.DAOArquivo();
			daoarquivo.setUsuario(usuario);
			daoarquivo.consulta();
			System.out.println("Consultou");
			if (daoarquivo.getTamanho() == 0){
				book = new DefaultMutableTreeNode("Vazio");
				category_arquivos.add(book);
				arvoreVazia = true;
			}else {
				arquivos = daoarquivo.getArquivos();
				System.out.println(arquivos.size());
				arvoreVazia = false;
			}
			
					

		}catch (Exception e){
			System.err.println("Erro ao consultar os arquivos");
			e.printStackTrace();
		}
		finally{
			
			//Preenche os nodes
			if (!arvoreVazia){
				System.out.println("Vai começar o for!");
				for (dropper.BEAM.Arquivo i : arquivos){
					
					System.out.println("Entrou no for!");
					book = new DefaultMutableTreeNode(i.getNome());
	             	//book = new MyNode(i.getNome());
					category_arquivos.add(book);
					
				}
			}
			

		} //Fim Arquivos
		
		//Consulta os Audios
		try{
			
			System.out.println("Entrou no Try");
			daoaudio = new dropper.DAO.DAOAudio();
			daoaudio.setUsuario(usuario);
			daoaudio.consulta();
			System.out.println("Consultou");
			if (daoaudio.getTamanho() == 0){
				book = new DefaultMutableTreeNode("Vazio");
				category_audios.add(book);
				arvoreVazia = true;
			}else {
				audios = daoaudio.getAudios();
				System.out.println(audios.size());
				arvoreVazia = false;
			}
			
					

		}catch (Exception e){
			System.err.println("Erro ao consultar os audios");
			e.printStackTrace();
		}
		finally{
			
			//Preenche os nodes
			if (!arvoreVazia){
				System.out.println("Vai começar o for!");
				for (dropper.BEAM.Audio i : audios){
					
					System.out.println("Entrou no for!");
					book = new DefaultMutableTreeNode(i.getNome());
	             	//book = new MyNode(i.getNome());
					category_audios.add(book);
					
				}
			}
			

		} //Fim Audios
		
		//Consulta as Imagens
		try{
			
			System.out.println("Entrou no Try");
			daoimagem = new dropper.DAO.DAOImagem();
			daoimagem.setUsuario(usuario);
			daoimagem.consulta();
			System.out.println("Consultou");
			if (daoimagem.getTamanho() == 0){
				book = new DefaultMutableTreeNode("Vazio");
				category_imagens.add(book);
				arvoreVazia = true;
			}else {
				imagens = daoimagem.getImagens();
				System.out.println(imagens.size());
				arvoreVazia = false;
			}
			
					

		}catch (Exception e){
			System.err.println("Erro ao consultar as Imagens");
			e.printStackTrace();
		}
		finally{
			
			//Preenche os nodes
			if (!arvoreVazia){
				System.out.println("Vai começar o for!");
				for (dropper.BEAM.Imagem i : imagens){
					
					System.out.println("Entrou no for!");
					book = new DefaultMutableTreeNode(i.getNome());
	             	//book = new MyNode(i.getNome());
					category_imagens.add(book);
					
				}
			}
			

		} //Fim Imagens
			
		
		//Consulta os Videos
		try{
			
			System.out.println("Entrou no Try");
			daovideo = new dropper.DAO.DAOVideo();
			daovideo.setUsuario(usuario);
			daovideo.consulta();
			System.out.println("Consultou");
			if (daovideo.getTamanho() == 0){
				book = new DefaultMutableTreeNode("Vazio");
				category_videos.add(book);
				arvoreVazia = true;
			}else {
				videos = daovideo.getVideos();
				System.out.println(videos.size());
				arvoreVazia = false;
			}
			
					

		}catch (Exception e){
			System.err.println("Erro ao consultar os videos");
			e.printStackTrace();
		}
		finally{
			
			//Preenche os nodes
			if (!arvoreVazia){
				System.out.println("Vai começar o for!");
				for (dropper.BEAM.Video i : videos){
					
					System.out.println("Entrou no for!");
					book = new DefaultMutableTreeNode(i.getNome());
	             	//book = new MyNode(i.getNome());
					category_videos.add(book);
					
				}
			}
			

		} //Fim Videos	
		
		
		
	}//Fim createNodes
	
	public String formataTipo(String nome){
		
		if(nome.equals("Arquivos")){
			return "arquivo";
		}else if(nome.equals("Audios")){
			return "audio";
		}else if(nome.equals("Imagens")){
			return "imagem";
		}else return "video";
		
		
	}
	
	
	public dropper.BEAM.Arquivo locateArquivo( String nome ){
		
		if(arquivos.get(arraycount).getNome().equals(nome)){
						
			return arquivos.get(arraycount);
			
		}else{
			
			arraycount ++;
			return locateArquivo(nome);
			
		}
	
	}//Fim LocateArquivo
	
	public dropper.BEAM.Audio locateAudio( String nome ){
		
		if(audios.get(arraycount).getNome().equals(nome)){
						
			return audios.get(arraycount);
			
		}else{
			
			arraycount ++;
			return locateAudio(nome);
			
		}
	
	}//Fim LocateAudio
	
	public dropper.BEAM.Imagem locateImagem( String nome ){
		
		if(imagens.get(arraycount).getNome().equals(nome)){
						
			return imagens.get(arraycount);
			
		}else{
			
			arraycount ++;
			return locateImagem(nome);
			
		}
	
	}//Fim LocateImagem
	
	public dropper.BEAM.Video locateVideo( String nome ){
		
		if(audios.get(arraycount).getNome().equals(nome)){
						
			return videos.get(arraycount);
			
		}else{
			
			arraycount ++;
			return locateVideo(nome);
			
		}
	
	}//Fim LocateVideo
	
	
}//Fim Classe
