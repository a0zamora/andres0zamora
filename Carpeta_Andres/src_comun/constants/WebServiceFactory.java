package constants;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import cliente.ServiciosWebService;

public class WebServiceFactory {

	//public static final String SERVER_IP = "150.185.180.170";
    public static final String SERVER_IP = "localhost";

	public static final ServiciosWebService createBasesDatosService() {

		URL url = null;

		try {
			url = new URL("http://" + SERVER_IP + ":8080/WS/ServiciosWeb?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return new ServiciosWebService(url, new QName("http://servidor/",
				"ServiciosWebService"));
	}
}
