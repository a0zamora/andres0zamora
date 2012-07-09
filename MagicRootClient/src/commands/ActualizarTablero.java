package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import constants.TipoDeEvento;

public class ActualizarTablero implements Command {

	public static final String CADENA_COMANDO = "posicion";
	private String str = "";

	private int id;
	private int y;
	private int x;
	private String Element;
	private List<PosicionBean> listaPosicion;

	public ActualizarTablero(String str) {
		listaPosicion = new ArrayList<PosicionBean>();
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(str);

		if (partes.length != 65) {
			throw new IllegalArgumentException();
		}
		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		try {
			int k = 1;
			for (int i = 0; i < 16; i++) {
				id = Integer.parseInt(partes[k++]);
				x = Integer.parseInt(partes[k++]);
				y = Integer.parseInt(partes[k++]);
				Element = partes[k++];
				if (Element.startsWith("true")) {
					boolean sameElement = true;
					PosicionBean pos = new PosicionBean(id, x, y, sameElement);
					listaPosicion.add(pos);
				} else if (Element.startsWith("false")) {
					boolean sameElement = false;
					PosicionBean pos = new PosicionBean(id, x, y, sameElement);
					listaPosicion.add(pos);
				}
			}

		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public ActualizarTablero(List<PosicionBean> listaPosicion) {

		int tam = listaPosicion.size();
		if (tam != 16) {
			throw new IllegalArgumentException();
		}

		this.listaPosicion = listaPosicion;

	}

	@SuppressWarnings("unchecked")
	public List getList() {
		return listaPosicion;
	}

	// --------------------------------------------------------------------------------
	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

	// --------------------------------------------------------------------------------
	@Override
	public String convertirAString() {
		str = "";
		int tam = this.listaPosicion.size();
		for (int k = 0; k < tam; k++) {
			PosicionBean aux = listaPosicion.get(k);
			str = str + " " + aux.convertirAString();
		}

		return CADENA_COMANDO + str;
	}

	// --------------------------------------------------------------------------------
	@Override
	public int getId() {
		return id;
	}


}