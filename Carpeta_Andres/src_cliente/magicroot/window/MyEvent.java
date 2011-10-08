package magicroot.window;

import java.util.EventObject;

@SuppressWarnings("serial")
public class MyEvent extends EventObject {
	private String data;
	
	MyEvent(Object source, String data) {
	    super(source);
	    this.data = data;
	   
	  }

	  public String getData() {
	    return data;
	  }
	  

}
