package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.Element;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	public static int screenWidth = 0;
	public static int screenHeight = 0;

	private final static String LABEL_STR = " Input your keyword here:  ";
	private final static String BUTTON_STR = " Search! ";
	private final static String ERROR_STR = " Some errors exist! ";
	private static ResultPanel contentPanel = new ResultPanel();
	private static JButton button = new JButton(BUTTON_STR);
	private static JTextField jt = new JTextField();
	private static JLabel label = new JLabel(LABEL_STR);
	private ArrayList<Element> all = new ArrayList<Element>();

	public GUI() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		screenHeight = (int) (screenSize.height / 2);
		screenWidth = (int) (screenSize.width / 2);
		this.setSize(screenWidth, screenHeight);

		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.setLayout(null);

		JScrollPane pane = new JScrollPane(contentPanel);

		this.add(pane);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		pane.setLocation(5, 2);
		pane.setSize(screenWidth - 10, screenHeight - 4 - 70);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());

		inputPanel.add(label, BorderLayout.WEST);
		inputPanel.add(jt, BorderLayout.CENTER);
		inputPanel.add(button, BorderLayout.EAST);
		this.add(inputPanel, BorderLayout.SOUTH);
		inputPanel.setLocation(5, screenHeight - 4 - 70 + 5);
		inputPanel.setSize(screenWidth - 10, 40);

		addActions();
	}

	private void addActions() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				String keyword = jt.getText();
				keyword = keyword.replaceAll(" ", "");
				String results = null;
				try {
					results = utils.Utils.googleSearch(keyword);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (null == results) {
					label.setText(ERROR_STR);
					return;
				}
				all.clear();
				utils.Utils.jsonParsing(results, all);
				if (all.size() != 0) {
					contentPanel.setAll(all);
				}
			}
		});
	}

}
