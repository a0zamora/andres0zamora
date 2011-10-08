package magicroot.window;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import commands.BorrarConexionCommand;
import commands.ConexionNuevaCommand;

@SuppressWarnings("serial")
public class PlayersPanel extends ImagePanel {

	private MyListener listener;
	private DefaultTableModel defTableModel;
	private JScrollPane playersScrollP;
	private JTable playersTable;
	private JPopupMenu popMenu;
	private Point evtPoint;
	private ActivePlayer player;

	public PlayersPanel(int w, int h) {
		wid = w;
		hei = h;
		setSize(wid, hei);
		setLayout(null);
		changeImage("CardsPNG/Fondonegro.png");
		setMyBorder();
		Object[][] tableData = {};

		String[] columnNames = { "Nombre", "ID" };

		defTableModel = new DefaultTableModel(tableData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		playersTable = new JTable(defTableModel);
		playersTable.setFont(getMyFont((float) (hei * 0.03)));
		playersTable.setRowHeight(40);
		playersTable.setBackground(Color.BLACK);
		playersTable.setForeground(Color.WHITE);
		playersTable.setGridColor(Color.GRAY);
		playersTable.setFont(getMyFont((float) (hei * 0.03)));
		playersScrollP = new JScrollPane(playersTable);
		playersScrollP.setBounds((int) (wid * 0.1), (int) (hei * 0.12),
				(int) (wid * 0.8), (int) (hei * 0.65));
		add(playersScrollP);
		playersScrollP.getViewport().setBackground(Color.BLACK);
		setColumnsWidth();
		popMenu = new JPopupMenu();

		AbstractAction abs = new AbstractAction("Ver Perfil") {

			public void actionPerformed(ActionEvent arg0) {
				String data;
				int row = playersTable.rowAtPoint(evtPoint);
				int col = playersTable.columnAtPoint(evtPoint);
				if (row >= 0 && col >= 0) {
					data = (String) defTableModel.getValueAt(row, 0);
					listener.showProfile(new MyEvent(PlayersPanel.this, data));
				}
			}
		};
		popMenu.add(abs);
		playersTable.add(popMenu);

		playersTable.addMouseListener(new MouseAdapter() {
			public void checkForPopMenu(MouseEvent e) {
				if(!player.isPlaying()){
				evtPoint = e.getPoint();
				popMenu.show(playersTable, evtPoint.x, evtPoint.y);
				}
			}
			public void mouseClicked(MouseEvent e) {
				checkForPopMenu(e);
			}

		});

		JLabel label = new JLabel("Personas en Linea");
		label.setForeground(Color.WHITE);
		label.setBounds((int)(wid*0.25),(int)(hei*0.04), 190, 30);
		label.setFont(getMyFont((float) (hei * 0.03)));
		add(label);
	}

	public void addPlayerOnline(MyEvent evt) {
		ConexionNuevaCommand c = new ConexionNuevaCommand(evt.getData());
		int count;
		String data;
		count = defTableModel.getRowCount();
		for(int i =0;i<count;i++){
			data= (String)defTableModel.getValueAt(i, 0);
			if(data.equals(c.getNombreUsr())){
				return;		
			}
		}
		defTableModel.addRow(new String[] { c.getNombreUsr() , c.getNivel() });
	}

	public void removePlayerOnline(MyEvent evt) {
		int count;
		String data;
		BorrarConexionCommand c = new BorrarConexionCommand(evt.getData());
		count = defTableModel.getRowCount();
		for(int i =0;i<count;i++){
			data= (String)defTableModel.getValueAt(i, 0);
			if(data.equals(c.getNombreUsr())){
				defTableModel.removeRow(i);
				break;
			}
		}
	}
		

	public void setColumnsWidth() {

		TableColumn column = null;
		column = playersTable.getColumnModel().getColumn(0);
		column.setPreferredWidth(110);
		column = playersTable.getColumnModel().getColumn(1);
		column.setPreferredWidth(20);

	}

	public void listenersConnections(MyListener i) {
		listener = i;
	}

	public String getPlayerOnline(int id) {
		int count;
		String data;
		count = defTableModel.getRowCount();
		for(int i =0;i<count;i++){
			data = (String)defTableModel.getValueAt(i, 1);
			if(data.equals(id+"")){
				return (String)defTableModel.getValueAt(i, 0);
			}
		}
		return "";
	}

	public void setPlayer(ActivePlayer p){
		player = p;
	}
}
