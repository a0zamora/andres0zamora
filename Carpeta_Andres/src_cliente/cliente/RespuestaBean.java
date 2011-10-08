
package cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for respuestaBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="respuestaBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cartasOfertada" type="{http://servidor/}cartasOfertadasBean" minOccurs="0"/>
 *         &lt;element name="cartasPropuesta" type="{http://servidor/}cartasOfertadasBean" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jugadorRef" type="{http://servidor/}jugadorBean" minOccurs="0"/>
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
@XmlType(name = "respuestaBean", propOrder = {
    "cartasOfertada",
    "cartasPropuesta",
    "descripcion",
    "id",
    "jugadorRef",
    "remitente"
})
public class RespuestaBean {

    protected CartasOfertadasBean cartasOfertada;
    protected CartasOfertadasBean cartasPropuesta;
    protected String descripcion;
    protected int id;
    protected JugadorBean jugadorRef;
    protected String remitente;

    /**
     * Gets the value of the cartasOfertada property.
     * 
     * @return
     *     possible object is
     *     {@link CartasOfertadasBean }
     *     
     */
    public CartasOfertadasBean getCartasOfertada() {
        return cartasOfertada;
    }

    /**
     * Sets the value of the cartasOfertada property.
     * 
     * @param value
     *     allowed object is
     *     {@link CartasOfertadasBean }
     *     
     */
    public void setCartasOfertada(CartasOfertadasBean value) {
        this.cartasOfertada = value;
    }

    /**
     * Gets the value of the cartasPropuesta property.
     * 
     * @return
     *     possible object is
     *     {@link CartasOfertadasBean }
     *     
     */
    public CartasOfertadasBean getCartasPropuesta() {
        return cartasPropuesta;
    }

    /**
     * Sets the value of the cartasPropuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link CartasOfertadasBean }
     *     
     */
    public void setCartasPropuesta(CartasOfertadasBean value) {
        this.cartasPropuesta = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
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
