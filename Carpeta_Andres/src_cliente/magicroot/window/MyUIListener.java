package magicroot.window;

import java.util.EventListener;

public interface MyUIListener extends EventListener {

	public void sendData(MyEvent evt);
	public void userChatUpdate(MyEvent evt);
}