package magicroot.window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChatPanel extends ImagePanel {

	// elementos del Panel de chat
	private MyUIListener listener;
	private JTextArea chatTextA;
	private JTextField userTextF;
	private JButton sendButton;
	private JScrollPane chatScrollP;
	public ChatPanel(int w, int h) {
		wid=w;
		hei=h;
		setSize(wid, hei);
		initGUI();
		setMyBorder();
		changeImage("CardsJPG/Aqua.jpg");
	}

	private void initGUI() {// inicializa y agrega cada componente del panel

		setLayout(null);
		setBackground(Color.BLACK);
		chatTextA = new JTextArea();
		chatTextA.setEditable(false);
		chatTextA.setBackground(Color.LIGHT_GRAY);
		chatTextA.setForeground(Color.DARK_GRAY);
		chatScrollP = new JScrollPane(chatTextA,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		chatScrollP.setBounds((int)(wid*0.12), (int)(hei*0.10),(int)(wid*0.8), (int)(hei*0.35));
		add(chatScrollP);

		userTextF = new JTextField();
		userTextF.setBounds((int)(wid*0.12), (int)(hei*0.50), (int)(wid*0.8), 20);
		userTextF.addKeyListener(new KeyListener() { 
					@Override
					public void keyTyped(KeyEvent arg0) {
					}

					@Override
					public void keyReleased(KeyEvent arg0) {
						if (arg0.getKeyChar() == '\n') {
							listener.userChatUpdate(new MyEvent(
									ChatPanel.this, userTextF.getText()));
									userTextF.setText("");
						}
					}

					@Override
					public void keyPressed(KeyEvent arg0) {
					}
				});
		add(userTextF);

		sendButton = new JButton("Send");
		sendButton.setBounds((int)(wid*0.01), (int)(hei*0.15), (int)(wid*0.10), (int)(hei*0.3));
		sendButton.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent arg0) {
				listener.userChatUpdate(new MyEvent(
						ChatPanel.this, userTextF.getText()));
						userTextF.setText("");

			}
		});

		add(sendButton);
	}

	public void putText(String text) {
		if ("clear".equals(userTextF.getText()))
			chatTextA.setText("");
		else {
			chatTextA.append(text);
			chatTextA.append("\n");
			chatTextA.setCaretPosition(chatTextA.getText().length() - 1);

		}
		userTextF.setText("");

	}

	public void listenersConnections(MyUIListener i) {
		listener = i;

	}

	public void myRepaint(){
		removeAll();
		add(chatScrollP);
		add(userTextF);
		add(sendButton);
		super.repaint();
	}
}

