package magicroot.window;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class CardPackage {

	List<PlayCard> pack;
	int col;
	int row;
	int table[][];

	public CardPackage(int r, int c) {
		pack = new ArrayList<PlayCard>();
		row = r;
		col = c;
		table = new int[row][col];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				table[i][j] = 0;

	}

	public void addCard(PlayCard card, int r, int c) {
		if (row * col != pack.size() || row * col == 0) {
			card.setTableValues(r, c);
			table[r][c] = 1;
			pack.add(card);
		}
	}

	public void addCardinOrder(PlayCard card) {
		if (row * col != pack.size() || row * col == 0) {
			for (int i = 0; i < row; i++)
				for (int j = 0; j < col; j++) {
					if (table[i][j] == 0) {
						card.setTableValues(i, j);
						pack.add(card);
						table[i][j] = 1;
						i = row;
						j = col;
					}
				}
		}
	}

	public PlayCard dropCard(int r, int c) {
		PlayCard card;
		for (int i = 0; i < pack.size(); i++) {
			card = pack.get(i);
			if (card.getRow() == r && card.getCol() == c) {
				table[r][c] = 0;
				return pack.remove(i);
			}
		}
		return null;
	}
	public PlayCard dropLastCard() {
		if(pack!= null && pack.size()!=0)
			return pack.remove(pack.size()-1);
		
		return null;
		
	}

	public PlayCard getCard(int r, int c) {
		PlayCard card;
		for (int i = 0; i < pack.size(); i++) {
			card = pack.get(i);
			if (card.getRow() == r && card.getCol() == c)
				return card;
		}
		return null;
	}
	
	public PlayCard getCardbyId(int id) {
		PlayCard card;
		for (int i = 0; i < pack.size(); i++) {
			card = pack.get(i);
			if (Integer.parseInt(card.getId())==id)
				return card;
		}
		return null;
	}

	public PlayCard getCard(int pos) {
		
		if( pack.size() != 0 )
			return pack.get(pos);
			
		return null;
		
		

	}

	public PlayCard cardOnPoint(Point p) {
		PlayCard card;
		for (int i = 0; i < pack.size(); i++) {
			card = pack.get(i);
			if (card.contains(p.getX(), p.getY())) {
				return card;
			}
		}
		return null;
	}

	public boolean insidePack(Point p) {
		PlayCard card;
		for (int i = 0; i < pack.size(); i++) {
			card = pack.get(i);
			if (card.contains(p.getX(), p.getY())) {
				return true;
			}
		}
		return false;
	}

	void repaintPack() {
		PlayCard card;
		for (int i = 0; i < pack.size(); i++) {
			card = pack.get(i);
			card.changeImage(card.getImgName());
		}
	}

	public int getSize() {
		return pack.size();
	}

	public void setList(List<PlayCard> list) {
		pack = list;
	}
	
	public List<PlayCard> getList(){
		return pack;
	}

	public void dropCard(String id) {
		PlayCard card;
		for (int i = 0; i < pack.size(); i++) {
			card=pack.get(i);
			if(card.getId()==id){
				pack.remove(card);
				table[card.getRow()][card.getCol()]=0;
				
		
	}
	}
	}
	
}
