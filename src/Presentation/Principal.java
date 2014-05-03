package Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.fieldexpert.fbapi4j.Case;
import com.fieldexpert.fbapi4j.Configuration;
import com.fieldexpert.fbapi4j.DefaultCaseBuilder;
import com.fieldexpert.fbapi4j.Session;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.media.ImageUpload;
import twitter4j.media.ImageUploadFactory;
import twitter4j.media.MediaProvider;

public class Principal {

	private File FileName;
	private JFrame frmTweetme;
	private int clicks = 0;
	private static ControladorP control;
	private static Comunicador com;
	private static Twitter twitter;
	private JTextArea textAreaTweet;
	private JLabel twitsCount, lblCharactersToEnd;
	private Paging p;
	private JList textArea;
	private DefaultListModel listModel;
	private ImageUpload upload;
	private boolean tweetWithImage = false;
	private final Action PublicarTweet = new PublicarTweet();
	private final Action ElegirImagen = new ElegirImagen();
	private JTextField textFieldBuscar;
	private final Action salir = new salir();
	private final Action NuevoTweet = new NuevoTweet();
	private final Action About = new About();
	private final Action Cuenta = new Cuenta();
	private final Action Incio = new Inicio();
	private final Action Conecta = new Conecta();
	private static Configuration conf;
	private static Session session;

	/**
	 * Launch the application.
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

	
		com = new Comunicador();
		twitter = com.getTwitter();
		control = com.getControlP();
		System.out.println(com);

		/*Configurem per a poder enviar automaticament els bugs que trobem, el usuari, etc*/
		conf = new Configuration();
		conf.setProperty("endpoint", "https://mesupv.fogbugz.com");
		conf.setProperty("email", "usuarivirtual@gmail.com");
		conf.setProperty("password", "usuarivirtual23");

		session = conf.buildSession();
		if (control.isOAuth()) {
			avanzar();
		}
		
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void avanzar() throws Exception {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Principal window = new Principal();
					window.frmTweetme.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public Principal() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	private void initialize() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, Exception {

		frmTweetme = new JFrame();
		Image img;
		URL url = new URL(twitter.showUser(twitter.getId())
				.getBiggerProfileImageURL());
		img = ImageIO.read(url);

		frmTweetme.setIconImage(Toolkit.getDefaultToolkit().getImage(url));

		frmTweetme.setTitle("TweetMe");
		frmTweetme.setBounds(100, 100, 1089, 785);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frmTweetme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new CompoundBorder(new BevelBorder(BevelBorder.RAISED,
						new Color(240, 240, 240), new Color(255, 255, 255),
						new Color(105, 105, 105), new Color(160, 160, 160)),
						new LineBorder(new Color(180, 180, 180), 5)),
				"Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new CompoundBorder(new BevelBorder(BevelBorder.RAISED,
						new Color(240, 240, 240), new Color(255, 255, 255),
						new Color(105, 105, 105), new Color(160, 160, 160)),
						new LineBorder(new Color(180, 180, 180), 5)), "Tweets",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setAutoscrolls(true);

		GroupLayout groupLayout = new GroupLayout(frmTweetme.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 210,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 541,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																690,
																Short.MAX_VALUE)
														.addComponent(
																panel_1,
																GroupLayout.DEFAULT_SIZE,
																690,
																Short.MAX_VALUE))
										.addGap(25)));
		listModel = new DefaultListModel();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				gl_panel_1
						.createSequentialGroup()
						.addGap(4)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								505, Short.MAX_VALUE).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
				Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				gl_panel_1
						.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								652, Short.MAX_VALUE).addContainerGap()));
		textArea = new JList();
		textArea.setBorder(null);
		textArea.setCellRenderer(new AreaTweet(com));
		textArea.setEnabled(true);
		textArea.setModel(listModel);

		scrollPane.setViewportView(textArea);

		panel_1.setLayout(gl_panel_1);
		cargarTimeLine();
		panel.setLayout(null);

		JButton btnConecta = new JButton("");
		btnConecta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarConecta();
			}
		});

		// Controlar que el server ens envie una resposta.
		// Controlar que la imatgne no és null.

		JLabel lblFotoPerfil = new JLabel(new ImageIcon(img));
		lblFotoPerfil.setBounds(20, 30, 80, 80);
		lblFotoPerfil.setBorder(BorderFactory.createLineBorder(new Color(0)));
		lblFotoPerfil.setLayout(new BorderLayout());
		panel.add(lblFotoPerfil);

		JLabel lblusurio = new JLabel("@"
				+ twitter.showUser(twitter.getId()).getScreenName());// +control.getUser().getScreenName());
		lblusurio.setBounds(20, 14, 169, 14);
		lblusurio.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblusurio);

		final JButton btnTweet = new JButton("Tweet");
		btnTweet.setBounds(115, 185, 74, 23);
		btnTweet.setAction(PublicarTweet);
		btnTweet.setToolTipText("Publicar");
		panel.add(btnTweet);

		lblCharactersToEnd = new JLabel();
		lblCharactersToEnd.setText("0");
		lblCharactersToEnd.setBounds(91, 189, 22, 14);
		panel.add(lblCharactersToEnd);

		textAreaTweet = new JTextArea();
		textAreaTweet.setWrapStyleWord(true);
		textAreaTweet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				int i = textAreaTweet.getText().length();
				// System.out.println(i);
				if (i >= 141) {
					btnTweet.setEnabled(false);
					textAreaTweet.setBackground(new Color(16711680));
				} else {
					btnTweet.setEnabled(true);
					textAreaTweet.setBackground(new Color(16777215));
				}
				lblCharactersToEnd.setText(textAreaTweet.getText().length()
						+ "");

			}

		});

		textAreaTweet.setFont(new Font("Tahoma", Font.ITALIC, 12));
		textAreaTweet.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clicks++;
				if (clicks == 1)
					textAreaTweet.setText("");
				textAreaTweet.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
		});
		JScrollPane scroll = new JScrollPane(textAreaTweet);
		scroll.setBounds(21, 115, 168, 66);
		textAreaTweet.setBounds(21, 109, 142, 62);
		textAreaTweet.setText("Publica un nuevo tweet...");
		textAreaTweet.setLineWrap(true);
		textAreaTweet.setToolTipText("Publica un nuevo tweet...");
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scroll);
		twitsCount = new JLabel(String.valueOf(twitter
				.showUser(twitter.getId()).getStatusesCount()) + " Tweets");
		twitsCount.setBounds(104, 30, 85, 14);
		twitsCount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(twitsCount);

		JButton btnCamera = new JButton("");
		btnCamera.setAction(ElegirImagen);
		btnCamera.setBounds(20, 185, 29, 23);
		btnCamera.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/noun_project_766.png")));
		btnCamera.setBorder(BorderFactory.createEmptyBorder());
		btnCamera.setContentAreaFilled(false);
		panel.add(btnCamera);

		JButton btnDescubre = new JButton("");
		btnDescubre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarDescubre();
			}
		});
		btnDescubre.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/hashtag.png")));
		btnDescubre.setBorder(BorderFactory.createEmptyBorder());
		btnDescubre.setContentAreaFilled(false);
		btnDescubre.setBounds(52, 367, 100, 106);
		panel.add(btnDescubre);

		JButton btnCuenta = new JButton("");
		btnCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cargarMiPagina();
			}
		});
		btnCuenta.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/cuenta.png")));
		btnCuenta.setBorder(BorderFactory.createEmptyBorder());
		btnCuenta.setContentAreaFilled(false);
		btnCuenta.setBounds(52, 499, 100, 86);
		panel.add(btnCuenta);

		JButton btnBuscar = new JButton();
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarEnTwitter();
			}
		});
		btnBuscar.setMargin(new Insets(4, 17, 4, 17));
		btnBuscar.setBorder(BorderFactory.createEmptyBorder());
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/buscar.png")));
		btnBuscar.setBounds(160, 638, 25, 28);
		panel.add(btnBuscar);

		JButton btnReloadtl = new JButton("");
		btnReloadtl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cargarTimeLine();
			}
		});
		btnReloadtl.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/reload.png")));
		btnReloadtl.setToolTipText("Recargar timeline");
		btnReloadtl.setBorder(BorderFactory.createEmptyBorder());
		btnReloadtl.setContentAreaFilled(false);
		btnReloadtl.setBounds(25, 638, 28, 28);
		panel.add(btnReloadtl);

		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(69, 643, 86, 20);
		panel.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		btnConecta.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/arroba.png")));
		btnConecta.setBorder(BorderFactory.createEmptyBorder());
		btnConecta.setContentAreaFilled(false);
		btnConecta.setBounds(52, 234, 100, 106);
		panel.add(btnConecta);

		frmTweetme.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frmTweetme.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmPreferencias = new JMenuItem("Preferencias");
		mnArchivo.add(mntmPreferencias);
		mntmPreferencias.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_MASK));
		mntmPreferencias.setEnabled(false);
		mntmPreferencias.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/19-gear.png")));
		JSeparator jSeparator1 = new JSeparator();
		mnArchivo.add(jSeparator1);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAction(salir);
		mntmSalir.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/63-runner.png")));
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mnArchivo.add(mntmSalir);

		JMenu mnNewMenu = new JMenu("Tweet");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNuevoTweet = new JMenuItem("Nuevo tweet");
		mntmNuevoTweet.setAction(NuevoTweet);
		mntmNuevoTweet.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/399.png")));
		mntmNuevoTweet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmNuevoTweet);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mntmInicio = new JMenuItem("Inicio");
		mntmInicio.setAction(Incio);
		mntmInicio.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK));
		mntmInicio.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/161-2.png")));
		mnNewMenu.add(mntmInicio);

		JMenuItem mntmConecta = new JMenuItem("Conecta");
		mntmConecta.setAction(Conecta);
		mntmConecta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmConecta.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/042.png")));
		mnNewMenu.add(mntmConecta);

		JMenuItem mntmDescubre = new JMenuItem("Descubre");
		mntmDescubre.setEnabled(false);
		mntmDescubre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
				InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmDescubre.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/045.png")));
		mnNewMenu.add(mntmDescubre);

		JMenuItem mntmCuenta = new JMenuItem("Cuenta");
		mntmCuenta.setAction(Cuenta);
		mntmCuenta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				InputEvent.CTRL_MASK));
		mntmCuenta.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/337.png")));
		mnNewMenu.add(mntmCuenta);

		JMenuItem mntmMensajeDirecto = new JMenuItem("Mensaje Directo");
		mntmMensajeDirecto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				InputEvent.CTRL_MASK));
		mntmMensajeDirecto.setEnabled(false);
		mntmMensajeDirecto.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/079.png")));
		mnNewMenu.add(mntmMensajeDirecto);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmNecesitoAyuda = new JMenuItem("Necesito ayuda");
		mntmNecesitoAyuda.setEnabled(false);
		mntmNecesitoAyuda.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/046.png")));
		mnAyuda.add(mntmNecesitoAyuda);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setAction(About);
		mntmAbout.setIcon(new ImageIcon(Principal.class
				.getResource("/Img/049.png")));
		mnAbout.add(mntmAbout);
	}

	private void buscarEnTwitter() {

		try {
			Query query = new Query(textFieldBuscar.getText().trim());
			QueryResult result;
			do {
				result = twitter.search(query);
				List<Status> timeline = result.getTweets();
				listModel.clear();
				for (int i = 0; i < timeline.size(); i++) {
					listModel.addElement(timeline.get(i));
				}
			} while ((query = result.nextQuery()) != null);
		} catch (TwitterException e1) {
			JOptionPane.showMessageDialog(frmTweetme, e1.getMessage(),
					e1.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarTimeLine() {
		p = new Paging();
		p.setCount(150);
		ResponseList<Status> timeline;
		try {
			timeline = twitter.getHomeTimeline(p);
			listModel.clear();
			for (int i = 0; i < timeline.size(); i++) {
				listModel.addElement(timeline.get(i));
			}
		} catch (TwitterException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frmTweetme, e1.getMessage(),
					e1.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

	private void cargarConecta() {
		p = new Paging();
		p.setCount(150);
		ResponseList<Status> timeline;
		try {
			timeline = twitter.getMentionsTimeline(p);
			listModel.clear();
			for (int i = 0; i < timeline.size(); i++) {
				listModel.addElement(timeline.get(i));
			}
		} catch (TwitterException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frmTweetme, e1.getMessage(),
					e1.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

	private void cargarDescubre() {
		// to do
	}

	private void cargarMiPagina() {
		p = new Paging();
		p.setCount(150);
		ResponseList<Status> timeline;
		try {
			timeline = twitter.getUserTimeline(p);
			listModel.clear();
			for (int i = 0; i < timeline.size(); i++) {
				listModel.addElement(timeline.get(i));
			}
		} catch (TwitterException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frmTweetme, e1.getMessage(),
					e1.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

	public JList getTextArea() {
		return textArea;
	}

	public void setTextArea(JList textArea) {
		this.textArea = textArea;
	}

	private class PublicarTweet extends AbstractAction {
		public PublicarTweet() {
			putValue(NAME, "Tweet");
			putValue(SHORT_DESCRIPTION, "Tweet something :)");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			publicarTweet();
		}
	}

	private void publicarTweet() {
		// Agafem el text que anem a publicar.
		String status = textAreaTweet.getText().trim();
		//Indiquem al projecte que enviem el bug
		String project = "TweetMe";
		//L'ˆrea en la que s'envia
		String area = "BugsReport";
		DefaultCaseBuilder caseBuilder = new DefaultCaseBuilder(project, area);

		if (!tweetWithImage) {
			try {
				new PublicarTweetSinImagen(twitter, status);
			} catch (TwitterException e2) {

				/**
				 * Ac’ Žs on s'envia en questi— el bug a la plataforma.
				 */
				Case bug=caseBuilder.build(e2);
				e2.printStackTrace();

				session.scout(bug);
				session.close();
				
				JOptionPane.showMessageDialog(frmTweetme, e2.getMessage(),
						e2.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
				e2.getErrorMessage();

			}
		} else {
			try {
				new PublicarTweetConImagen(twitter, status, upload, FileName);
			} catch (TwitterException e1) {
				/**
				 * Per a enviar bug en cas que tambŽ ac’.
				 */
				Case bug=caseBuilder.build(e1);
				e1.printStackTrace();

				session.scout(bug);
				session.close();

				JOptionPane.showMessageDialog(frmTweetme, e1.getMessage(),
						e1.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		// ...
		tweetWithImage = false;
		textAreaTweet.setText("Publica un nuevo tweet...");
		textAreaTweet.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblCharactersToEnd.setText("0");
		cargarTimeLine();
		clicks = 0;
		try {
			actualizarNumTweets();
		} catch (IllegalStateException e1) {
			JOptionPane.showMessageDialog(frmTweetme, e1.getMessage(),
					e1.getMessage(), JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (TwitterException e1) {
			JOptionPane.showMessageDialog(frmTweetme, e1.getMessage(),
					e1.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

	}

	private void actualizarNumTweets() throws IllegalStateException,
			TwitterException {
		// TODO Auto-generated method stub
		twitsCount.setText(String.valueOf(twitter.showUser(twitter.getId())
				.getStatusesCount() + " Tweets"));
	}

	private class ElegirImagen extends AbstractAction {
		public ElegirImagen() {
			putValue(NAME, "");
			putValue(SHORT_DESCRIPTION, "Upload a photo :)");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showDialog(fc, "Añadir");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				FileName = fc.getSelectedFile();
				ImageUploadFactory factory = new ImageUploadFactory(
						twitter.getConfiguration());
				upload = factory.getInstance(MediaProvider.TWITTER,
						twitter.getAuthorization());
				tweetWithImage = true;
			}
			textAreaTweet.setText(textAreaTweet.getText() + " [IMAGEN]");
			fc.setSelectedFile(null);
		}
	}

	private class salir extends AbstractAction {
		public salir() {
			putValue(NAME, "Salir");
			putValue(SHORT_DESCRIPTION, "Salir de la aplicación");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			frmTweetme.dispose();
			System.exit(0);
		}
	}

	private class NuevoTweet extends AbstractAction {
		public NuevoTweet() {
			putValue(NAME, "Nuevo tweet");
			putValue(SHORT_DESCRIPTION, "Publica un nuevo tweet");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			textAreaTweet.setText("");
			textAreaTweet.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
	}

	private class About extends AbstractAction {
		public About() {
			putValue(NAME, "About");
			putValue(SHORT_DESCRIPTION, "Acerca de la aplicación");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			AcercaDeJDialog a = new AcercaDeJDialog(null);
			a.setVisible(true);
		}
	}

	private class Cuenta extends AbstractAction {
		public Cuenta() {
			putValue(NAME, "Cuenta");
			putValue(SHORT_DESCRIPTION, "Ver mi perfil");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			cargarMiPagina();
		}
	}

	private class Inicio extends AbstractAction {
		public Inicio() {
			putValue(NAME, "Inicio");
			putValue(SHORT_DESCRIPTION, "Cargar timeline");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			cargarTimeLine();
		}
	}

	private class Conecta extends AbstractAction {
		public Conecta() {
			putValue(NAME, "Conecta");
			putValue(SHORT_DESCRIPTION, "Cargar menciones");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			cargarConecta();
		}
	}
}