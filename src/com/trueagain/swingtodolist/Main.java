package com.trueagain.swingtodolist;

import javax.swing.JFrame;

public class Main extends JFrame{
	public Main() {
		setTitle("Todo");
		MainPanel mainPanel = new MainPanel();
		getContentPane().add(mainPanel);
		setSize(WIDTH, HEIGHT);
	}
	
	public static void main(String[] args) {
		Main mainFrame = new Main();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

	public static final int WIDTH = 250;
	public static final int HEIGHT = 400;
}

