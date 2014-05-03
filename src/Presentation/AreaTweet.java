package Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import twitter4j.Status;

public class AreaTweet extends JPanel implements ListCellRenderer {
	private JTextField textField;
	private JLabel lblusuario, lblUserIcon, lblDate;
	private Comunicador com;
	private JLabel lblVia;

	/**
	 * Create the panel.
	 */
	public AreaTweet(Comunicador com) {
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		this.com = com;
		lblusuario = new JLabel("@usuario");
		lblusuario.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblUserIcon = new JLabel("");
		lblUserIcon.setBorder(BorderFactory.createLineBorder(new Color(0)));
		lblUserIcon.setLayout(new BorderLayout());

		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);

		lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.ITALIC, 10));

		lblVia = new JLabel("via");

		JButton btnRetweet = new JButton("Retweet");
		btnRetweet.setEnabled(false);

		JButton btnFavorito = new JButton("Favorito");
		btnFavorito.setEnabled(false);
		JButton btnResponder = new JButton("Responder");
		btnResponder.setEnabled(false);
		btnResponder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("hola");
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(10)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(0)
																		.addComponent(
																				lblusuario,
																				GroupLayout.DEFAULT_SIZE,
																				96,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblDate,
																				GroupLayout.DEFAULT_SIZE,
																				66,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblVia)
																		.addGap(306))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblUserIcon,
																				GroupLayout.PREFERRED_SIZE,
																				48,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(10)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								textField,
																								GroupLayout.DEFAULT_SIZE,
																								430,
																								Short.MAX_VALUE)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												btnResponder)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnRetweet)
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												btnFavorito)))
																		.addContainerGap()))));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(11)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblusuario)
														.addComponent(
																lblDate,
																GroupLayout.PREFERRED_SIZE,
																14,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblVia))
										.addGap(11)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				textField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnRetweet)
																						.addComponent(
																								btnFavorito)
																						.addComponent(
																								btnResponder)))
														.addComponent(
																lblUserIcon,
																GroupLayout.PREFERRED_SIZE,
																48,
																GroupLayout.PREFERRED_SIZE))
										.addGap(30)));
		setLayout(groupLayout);
	}

	@Override
	/**Per a fer: canviar status */
	public Component getListCellRendererComponent(JList arg0, Object value,
			int index, boolean isSelected, boolean isFocused) {
		Status stat = (Status) value;
		lblusuario.setText("@" + stat.getUser().getScreenName());

		String string = stat.getSource();
		if (!string.equals("web")) {
			int begin = string.indexOf(">");
			int end = string.lastIndexOf("<");
			string = string.substring(begin + 1, end);
		}
		lblVia.setText("Vía " + string);
		lblDate.setText("on " + stat.getCreatedAt().toString());
		textField.setText(stat.getText());
		URL url;
		try {
			url = new URL(stat.getUser().getProfileImageURL());
			lblUserIcon.setIcon(new ImageIcon(url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}
}
