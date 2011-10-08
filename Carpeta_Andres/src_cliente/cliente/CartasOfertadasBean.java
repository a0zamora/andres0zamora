
package cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cartasOfertadasBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cartasOfertadasBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cartaRef" type="{http://servidor/}cartaBean" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jugadorRef" type="{http://servidor/}jugadorBean" minOccurs="0"/>
 *         &lt;element name="respuestaOferta" type="{http://servidor/}respuestaBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="respuestas" type="{http://servidor/}respuestaBean" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cartasOfertadasBean", propOrder = {
    "cantidad",
    "cartaRef",
    "id",
    "jugadorRef",
    "respuestaOferta",
    "respuestas"
})
public class CartasOfertadasBean {

    protected int cantidad;
    protected CartaBean cartaRef;
    protected int id;
    protected JugadorBean jugadorRef;
    @XmlElement(nillable = true)
    protected List<RespuestaBean> respuestaOferta;
    @XmlElement(nillable = true)
    protected List<RespuestaBean> respuestas;

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

    /**
     * Gets the value of the respuestaOferta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the respuestaOferta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRespuestaOferta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RespuestaBean }
     * 
     * 
     */
    public List<RespuestaBean> getRespuestaOferta() {
        if (respuestaOferta == null) {
            respuestaOferta = new ArrayList<RespuestaBean>();
        }
        return this.respuestaOferta;
    }

    /**
     * Gets the value of the respuestas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the respuestas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRespuestas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RespuestaBean }
     * 
     * 
     */
    public List<RespuestaBean> getRespuestas() {
        if (respuestas == null) {
            respuestas = new ArrayList<RespuestaBean>();
        }
        return this.respuestas;
    }

}
