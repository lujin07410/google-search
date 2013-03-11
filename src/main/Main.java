package main;

import gui.GUI;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		if (args.length > 1) {
			System.out.println("The length of input keyword must be 1.");
			System.out
					.println("Or you can run this app without any keyword.\r\n");
			System.exit(0);
		}

		if (args.length == 1) {
			String keyword = args[0].trim();
			String results = null;
			try {
				results = utils.Utils.googleSearch(keyword);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (null == results) {
				System.out.println("Can't get the resources from google.");
				System.exit(0);
			}
			ArrayList<Element> all = new ArrayList<Element>();
			utils.Utils.jsonParsing(results, all);
			System.out.println(utils.Utils.outPut(all));
			System.exit(0);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GUI frame = new GUI();

				frame.setVisible(true);
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
			}
		});
	}
}
