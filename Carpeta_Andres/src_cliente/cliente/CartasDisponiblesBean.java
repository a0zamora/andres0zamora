
package cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cartasDisponiblesBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cartasDisponiblesBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cartaRef" type="{http://servidor/}cartaBean" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jugadorRef" type="{http://servidor/}jugadorBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cartasDisponiblesBean", propOrder = {
    "cantidad",
    "cartaRef",
    "id",
    "jugadorRef"
})
public class CartasDisponiblesBean {

    protected int cantidad;
    protected CartaBean cartaRef;
    protected int id;
    protected JugadorBean jugadorRef;

    /**
     * Gets the value of the cantidad property.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

    /**
     * Gets the value of the cartaRef property.
     * 
     * @return
     *     possible object is
     *     {@link CartaBean }
     *     
     */
    public CartaBean getCartaRef() {
        return cartaRef;
    }

    /**
     * Sets the value of the cartaRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link CartaBean }
     *     
     */
    public void setCartaRef(CartaBean value) {
        this.cartaRef = value;
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

}
