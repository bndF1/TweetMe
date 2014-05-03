package Presentation;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Font;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class AcercaDeJDialog extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JLabel lblTweetmeAplhaVersion;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				AcercaDeJDialog inst = new AcercaDeJDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public AcercaDeJDialog(JFrame frame) {
		super(frame);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AcercaDeJDialog.class.getResource("/Img/049.png")));
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("About");
				this.setResizable(false);
				getContentPane().setLayout(null);
				{
					jPanel2 = new JPanel();
					jPanel2.setBounds(0, 0, 225, 94);
					getContentPane().add(jPanel2);
					jPanel2.setPreferredSize(new java.awt.Dimension(294, 49));
					jPanel2.setBorder(BorderFactory.createTitledBorder(""));
					jPanel2.setLayout(null);
					{
						lblTweetmeAplhaVersion = new JLabel("TweetMe ");
						lblTweetmeAplhaVersion.setBounds(121, 17, 87, 17);
						lblTweetmeAplhaVersion.setFont(new Font("Tahoma", Font.BOLD, 16));
						jPanel2.add(lblTweetmeAplhaVersion);
					}
					
					JLabel lblAplhaVersion = new JLabel("Aplha Version");
					lblAplhaVersion.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblAplhaVersion.setBounds(121, 45, 87, 14);
					jPanel2.add(lblAplhaVersion);
					
					JLabel lblNewLabel = new JLabel("New label");
					lblNewLabel.setIcon(new ImageIcon(AcercaDeJDialog.class.getResource("/Img/tweet.png")));
					lblNewLabel.setBounds(10, 6, 100, 80);
					jPanel2.add(lblNewLabel);
					
					JLabel lbltwmme = new JLabel("@tw4mme");
					lbltwmme.setBounds(118, 72, 92, 14);
					jPanel2.add(lbltwmme);
					lbltwmme.setFont(new Font("Tahoma", Font.BOLD, 15));
				}
				{
					jPanel1 = new JPanel();
					jPanel1.setBounds(0, 97, 225, 94);
					getContentPane().add(jPanel1);
					jPanel1.setPreferredSize(new java.awt.Dimension(180, 106));
					jPanel1.setBorder(BorderFactory.createTitledBorder("Creado por"));
					jPanel1.setLayout(null);
					
					JLabel lblborras = new JLabel("@borras118");
					lblborras.setFont(new Font("Tahoma", Font.BOLD, 13));
					lblborras.setBounds(62, 26, 80, 14);
					jPanel1.add(lblborras);
					
					JLabel lblbndf = new JLabel("@bndF1");
					lblbndf.setFont(new Font("Tahoma", Font.BOLD, 13));
					lblbndf.setBounds(62, 58, 80, 14);
					jPanel1.add(lblbndf);
				}
			}
			this.setSize(231, 219);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
