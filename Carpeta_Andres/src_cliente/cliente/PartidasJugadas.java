
package cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for partidasJugadas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partidasJugadas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jugadorEmpate1" type="{http://servidor/}jugadorBean" minOccurs="0"/>
 *         &lt;element name="jugadorEmpate2" type="{http://servidor/}jugadorBean" minOccurs="0"/>
 *         &lt;element name="jugadorGanador" type="{http://servidor/}jugadorBean" minOccurs="0"/>
 *         &lt;element name="jugadorPerdedor" type="{http://servidor/}jugadorBean" minOccurs="0"/>
 *         &lt;element name="puntosGanador" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="puntosPerdedor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partidasJugadas", propOrder = {
    "id",
    "jugadorEmpate1",
    "jugadorEmpate2",
    "jugadorGanador",
    "jugadorPerdedor",
    "puntosGanador",
    "puntosPerdedor"
})
public class PartidasJugadas {

    protected int id;
    protected JugadorBean jugadorEmpate1;
    protected JugadorBean jugadorEmpate2;
    protected JugadorBean jugadorGanador;
    protected JugadorBean jugadorPerdedor;
    protected int puntosGanador;
    protected int puntosPerdedor;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the jugadorEmpate1 property.
     * 
     * @return
     *     possible object is
     *     {@link JugadorBean }
     *     
     */
    public JugadorBean getJugadorEmpate1() {
        return jugadorEmpate1;
    }

    /**
     * Sets the value of the jugadorEmpate1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorBean }
     *     
     */
    public void setJugadorEmpate1(JugadorBean value) {
        this.jugadorEmpate1 = value;
    }

    /**
     * Gets the value of the jugadorEmpate2 property.
     * 
     * @return
     *     possible object is
     *     {@link JugadorBean }
     *     
     */
    public JugadorBean getJugadorEmpate2() {
        return jugadorEmpate2;
    }

    /**
     * Sets the value of the jugadorEmpate2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorBean }
     *     
     */
    public void setJugadorEmpate2(JugadorBean value) {
        this.jugadorEmpate2 = value;
    }

    /**
     * Gets the value of the jugadorGanador property.
     * 
     * @return
     *     possible object is
     *     {@link JugadorBean }
     *     
     */
    public JugadorBean getJugadorGanador() {
        return jugadorGanador;
    }

    /**
     * Sets the value of the jugadorGanador property.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorBean }
     *     
     */
    public void setJugadorGanador(JugadorBean value) {
        this.jugadorGanador = value;
    }

    /**
     * Gets the value of the jugadorPerdedor property.
     * 
     * @return
     *     possible object is
     *     {@link JugadorBean }
     *     
     */
    public JugadorBean getJugadorPerdedor() {
        return jugadorPerdedor;
    }

    /**
     * Sets the value of the jugadorPerdedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorBean }
     *     
     */
    public void setJugadorPerdedor(JugadorBean value) {
        this.jugadorPerdedor = value;
    }

    /**
     * Gets the value of the puntosGanador property.
     * 
     */
    public int getPuntosGanador() {
        return puntosGanador;
    }

    /**
     * Sets the value of the puntosGanador property.
     * 
     */
    public void setPuntosGanador(int value) {
        this.puntosGanador = value;
    }

    /**
     * Gets the value of the puntosPerdedor property.
     * 
     */
    public int getPuntosPerdedor() {
        return puntosPerdedor;
    }

    /**
     * Sets the value of the puntosPerdedor property.
     * 
     */
    public void setPuntosPerdedor(int value) {
        this.puntosPerdedor = value;
    }

}
