package magicroot.window;

import java.util.EventListener;

public interface MyListener extends EventListener {

	public static final int SHOW_PROFILE = 0;
	public static final int SHOW_ROOM = 1;
	public static final int SHOW_MARKET = 2;
	public static final int SHOW_LOBBY = 3;
	public static final int SHOW_STORE = 4;
	public static final int SHOW_MAKESHOP = 5;
	public static final int SHOW_VIEWSHOPS = 6;
	

	public void showProfile(MyEvent evt);

	public void showRoom(MyEvent evt);

	public void showMarket(MyEvent evt);

	public void showLobby(MyEvent evt);

	public void showStore(MyEvent evt);

	public void showMakeShop(MyEvent evt);

	public void showViewShops(MyEvent evt);

}
