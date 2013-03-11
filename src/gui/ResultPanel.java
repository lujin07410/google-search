package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextArea;

import main.Element;

public class ResultPanel extends JTextArea {

	private static final long serialVersionUID = 1L;
	private ArrayList<Element> all = null;

	public void setAll(ArrayList<Element> all) {
		this.all = all;
		this.setText(utils.Utils.outPut(this.all));
		this.repaint();
	}

	public ResultPanel() {
		this.setBackground(Color.white);
		this.setEditable(false);
	}

}
