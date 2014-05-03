package Presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import twitter4j.TwitterException;
import Exceptions.DAOExcepcion;

public class IntroducirPIN extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private String PIN;
	private ControladorP control;


	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			IntroducirPIN dialog = new IntroducirPIN();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws DAOExcepcion 
	 */
	public IntroducirPIN() throws DAOExcepcion {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IntroducirPIN.class.getResource("/Img/086.png")));
		setTitle("PIN");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(contentPanel, e1.getMessage(),
					e1.getMessage(), JOptionPane.ERROR_MESSAGE);
		} catch (InstantiationException e1) {
			JOptionPane.showMessageDialog(contentPanel, e1.getMessage(),
					e1.getMessage(), JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			JOptionPane.showMessageDialog(contentPanel, e1.getMessage(),
					e1.getMessage(), JOptionPane.ERROR_MESSAGE);
		} catch (UnsupportedLookAndFeelException e1) {
			JOptionPane.showMessageDialog(contentPanel, e1.getMessage(),
					e1.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

		setBounds(100, 100, 199, 153);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		control = ControladorP.dameControlador();
		JLabel lblIntroduzcaElPin = new JLabel("Introduzca el PIN ");
		lblIntroduzcaElPin.setBounds(21, 11, 141, 14);
		lblIntroduzcaElPin.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPanel.add(lblIntroduzcaElPin);
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(42, 36, 102, 20);
			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						PIN=passwordField.getText();
						try {
							control.setPin(PIN);
							control.continuar(PIN);
						} catch (TwitterException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(contentPanel, e1.getMessage(),
									e1.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
							//e1.printStackTrace();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(contentPanel, e1.getMessage(),
									e1.getMessage(), JOptionPane.ERROR_MESSAGE);
						//	e1.printStackTrace();
						}
						getRootPane().getParent().setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
