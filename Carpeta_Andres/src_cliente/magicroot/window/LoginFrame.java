package magicroot.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import constants.WebServiceFactory;

import cliente.ServiciosWeb;
import cliente.ServiciosWebService;
import cliente.JugadorBean;
import cliente.JugadorTO;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private Main main;
	private JTextField usrt;
	private JTextField nameTf;
	private JTextField addressTf;
	private JPasswordField pwdF;
	private JPasswordField passF;
	private ImagePanel loginPanel;
	private ImagePanel regPanel;
	private int wid=350;
	private int hei=600;

	public LoginFrame(Main m) {
		main = m;
		initGUI();
	}

	void initGUI() {

		setSize(wid, hei);
		setLayout(null);
		JLabel usrl = new JLabel("Usuario");
		JLabel pwdl = new JLabel("Pasword");

		usrt = new JTextField("jesus");
		pwdF = new JPasswordField("1234");

		usrl.setBounds((int)(wid*0.4), (int)(hei*0.35), 150, 20);
		pwdl.setBounds((int)(wid*0.4), (int)(hei*0.53), 150, 20);

		usrt.setBounds((int)(wid*0.3), (int)(hei*0.40), 150, 20);
		pwdF.setBounds((int)(wid*0.3), (int)(hei*0.57), 150, 20);

		MyButton loginButton = new MyButton("", 0, "buttons/entrar1.jpg",
				"buttons/entrar2.jpg", "buttons/entrar3.jpg", "buttons/entrar4.jpg");
		loginButton.setBounds((int)(wid*0.30), (int)(hei*0.8),(int)(wid*0.4), (int)(hei*0.07));
		loginButton.SetImages();
		loginButton.setMyBorder();
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JugadorTO jTO;
				ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
				ServiciosWeb base = servicio.getServiciosWebPort();

				JugadorBean j = new JugadorBean();
				String c = new String(pwdF.getPassword());

				j.setNombreUsr(usrt.getText());
				j.setClave(c);

				jTO = base.consultar(j);
				if (jTO.getIdjugador() == -1) {
					wrongData("Este Usuario ya esta Conectado");
				}
				else if (jTO.getIdjugador() == 0) {
					wrongData("Usuario o Contrasena invalida");

				} 
				else{
					ActivePlayer player = new ActivePlayer(jTO);
					main.changeFrame(player);
					
				}

			}

		});

		MyButton regButton = new MyButton("", 0, "buttons/registro1.jpg",
				"buttons/registro2.jpg", "buttons/registro3.jpg", "buttons/registro4.jpg");
		regButton.setBounds((int)(wid*0.30), (int)(hei*0.2),(int)(wid*0.4), (int)(hei*0.07));
		regButton.setMyBorder();
		regButton.SetImages();
		regButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerPanel();
			}

		});

		loginPanel = new ImagePanel("CardsJPG/card1-8.jpg");
		loginPanel.setLayout(null);
		loginPanel.setBounds(0, 0, wid, hei);
		add(loginPanel);
		loginPanel.add(pwdF);
		loginPanel.add(usrt);
		loginPanel.add(usrl);
		loginPanel.add(pwdl);
		loginPanel.add(loginButton);
		loginPanel.add(regButton);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}
		});

	}

	protected void registerPanel() {
		regPanel = new ImagePanel("CardsJPG/card1-8.jpg");
		regPanel.setLayout(null);
		regPanel.setBounds(0, 0, wid, hei);

		nameTf = new JTextField();
		nameTf.setBounds((int)(wid*0.30), (int)(hei*0.1),(int)(wid*0.5),20);

		JLabel nameLabel = new JLabel("Username");
		nameLabel.setBounds((int)(wid*0.04),(int)(hei*0.1),(int)(wid*0.26),20);

		addressTf = new JTextField();
		addressTf.setBounds((int)(wid*0.30), (int)(hei*0.20),(int)(wid*0.5),20);

		JLabel addressLabel = new JLabel("E-mail");
		addressLabel.setBounds((int)(wid*0.04),(int)(hei*0.20),(int)(wid*0.26),20);

		passF = new JPasswordField();
		passF.setBounds((int)(wid*0.30), (int)(hei*0.3),(int)(wid*0.5),20);

		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds((int)(wid*0.04),(int)(hei*0.3),(int)(wid*0.26),20);

		MyButton acept = new MyButton("", 0, "buttons/enviar1.jpg",
				"buttons/enviar2.jpg", "buttons/enviar3.jpg", "buttons/enviar4.jpg");
		acept.setBounds((int)(wid*0.10), (int)(hei*0.60),(int)(wid*0.4), (int)(hei*0.07));
		acept.SetImages();
		acept.setMyBorder();
		acept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
				ServiciosWeb base = servicio.getServiciosWebPort();

				JugadorTO j = new JugadorTO();
				String c = new String(passF.getPassword());

				j.setNombreusr(nameTf.getText());
				j.setCorreo(addressTf.getText());
				j.setClave(c);

				if (nameTf.getText().length() < 17
						&& nameTf.getText().length() > 3) {
					if (c.length() < 17 && c.length() > 3) {
						if (base.guardar(j) == true) {
							regPanel.setVisible(false);
							loginPanel.setVisible(true);
						} else {
							wrongData("el Pseudonimo o correo ya esta en uso ");

						}
					} else {
						wrongData("la contrase;a debe estar entre 8 y 16 caracteres");
					}
				} else {
					wrongData("The Username must be between 4 and 16 characters");
				}
			}
		});

		MyButton cancel = new MyButton("", 0, "buttons/cancelar1.jpg",
				"buttons/cancelar2.jpg", "buttons/cancelar3.jpg", "buttons/cancelar4.jpg");
		cancel.setBounds((int)(wid*0.55), (int)(hei*0.60),(int)(wid*0.4), (int)(hei*0.07));
		cancel.SetImages();
		cancel.setMyBorder();
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				regPanel.setVisible(false);
				loginPanel.setVisible(true);

			}
		});

		regPanel.add(nameTf);
		regPanel.add(addressTf);
		regPanel.add(passF);
		regPanel.add(nameLabel);
		regPanel.add(addressLabel);
		regPanel.add(passLabel);
		regPanel.add(acept);
		regPanel.add(cancel);
		loginPanel.setVisible(false);
		add(regPanel);
		repaint();
	}

	public void wrongData(String info) {
		JOptionPane.showConfirmDialog(this, info, "Error",
				JOptionPane.CLOSED_OPTION);
	}

}
