package magicroot.window;

import java.util.ArrayList;
import java.util.List;

import cliente.JugadorTO;

public class ActivePlayer {
	
	
	private int idPlayer;
	private int color;
	private String username;
	private List<PlayCard> cards;
	private List<PlayCard> playingCards;
	private int level;
	private int points;
	private String avatar;
	private String correo;
	private boolean playing = false;
	public ActivePlayer(JugadorTO j) {
	//1 azul(crea)  2 rojo(invitado)
		idPlayer = j.getIdjugador();
		username = j.getNombreusr();
		cards=null;
		color = 0;
		level = j.getNivel();
		avatar = j.getNombreAvatar();
		setCorreo(j.getCorreo());
		setPoints(j.getPuntos());
		
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public int getIdPlayer() {
		return idPlayer;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setCards(List<PlayCard> cards) {
		this.cards = cards;
	}

	public List<PlayCard> getCards() {
		return cards;
	}
	public void addCard(PlayCard card) {
		if(cards ==null)
			cards = new ArrayList<PlayCard>();
		cards.add(card);
	
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void setPlayingCards(List<PlayCard> playingCards) {
		this.playingCards = playingCards;
	}

	public List<PlayCard> getPlayingCards() {
		return playingCards;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public boolean isPlaying() {
		return playing;
	}

	
	 
}
