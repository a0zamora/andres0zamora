
package cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mensajesBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mensajesBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cartaOfertada" type="{http://servidor/}cartaBean" minOccurs="0"/>
 *         &lt;element name="cartaPropuesta" type="{http://servidor/}cartaBean" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jugadorRef" type="{http://servidor/}jugadorBean" minOccurs="0"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remitente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mensajesBean", propOrder = {
    "cartaOfertada",
    "cartaPropuesta",
    "id",
    "jugadorRef",
    "mensaje",
    "remitente"
})
public class MensajesBean {

    protected CartaBean cartaOfertada;
    protected CartaBean cartaPropuesta;
    protected int id;
    protected JugadorBean jugadorRef;
    protected String mensaje;
    protected String remitente;

    /**
     * Gets the value of the cartaOfertada property.
     * 
     * @return
     *     possible object is
     *     {@link CartaBean }
     *     
     */
    public CartaBean getCartaOfertada() {
        return cartaOfertada;
    }

    /**
     * Sets the value of the cartaOfertada property.
     * 
     * @param value
     *     allowed object is
     *     {@link CartaBean }
     *     
     */
    public void setCartaOfertada(CartaBean value) {
        this.cartaOfertada = value;
    }

    /**
     * Gets the value of the cartaPropuesta property.
     * 
     * @return
     *     possible object is
     *     {@link CartaBean }
     *     
     */
    public CartaBean getCartaPropuesta() {
        return cartaPropuesta;
    }

    /**
     * Sets the value of the cartaPropuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link CartaBean }
     *     
     */
    public void setCartaPropuesta(CartaBean value) {
        this.cartaPropuesta = value;
    }

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
     * Gets the value of the jugadorRef property.
     * 
     * @return
     *     possible object is
     *     {@link JugadorBean }
     *     
     */
    public JugadorBean getJugadorRef() {
        return jugadorRef;
    }

    /**
     * Sets the value of the jugadorRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorBean }
     *     
     */
    public void setJugadorRef(JugadorBean value) {
        this.jugadorRef = value;
    }

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Gets the value of the remitente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemitente() {
        return remitente;
    }

    /**
     * Sets the value of the remitente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemitente(String value) {
        this.remitente = value;
    }

}
