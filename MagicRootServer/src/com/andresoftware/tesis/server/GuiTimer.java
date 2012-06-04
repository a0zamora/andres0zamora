package com.andresoftware.tesis.server;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GuiTimer extends JFrame {

	private JButton buttonArmed;
	private JButton buttonDesArmed;
	private GuiTestTimer hilo;
	public JTextArea textArea;

	public GuiTimer() {

		super("Timer");
		Container contenedor;
		contenedor = getContentPane();
		contenedor.setLayout(new FlowLayout());
		// crear botones
		buttonArmed = new JButton("Activar timer");
		contenedor.add(buttonArmed);

		buttonDesArmed = new JButton("Desactivar timer");
		contenedor.add(buttonDesArmed);
		buttonDesArmed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (hilo != null) {
					textArea.setText(textArea.getText() + "\nAntes de detener");
					hilo.stopTimer();
					hilo = null;
					textArea.setText(textArea.getText()
							+ "\nDespues de detener");
				}
			}
		});

		textArea = new JTextArea();
		textArea.setMinimumSize(new Dimension(200, 100));
		contenedor.add(textArea);

		buttonArmed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("timer Activado");
				//hilo = new GuiTestTimer(GuiTimer.this);
			}
		});

		setVisible(true);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setText(String str) {
		textArea.setText(str);

	}

	public static void main(String[] args) {
		new GuiTimer();
	}
}
