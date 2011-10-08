
package cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cartaBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cartaBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cartasDisponiblesRef" type="{http://servidor/}cartasDisponiblesBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cartasOfertadasRef" type="{http://servidor/}cartasOfertadasBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="elemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fuerzaeste" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fuerzanorte" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fuerzaoeste" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fuerzasur" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mensajesOferta" type="{http://servidor/}mensajesBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mensajesPropuesta" type="{http://servidor/}mensajesBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sumafuerzas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cartaBean", propOrder = {
    "cartasDisponiblesRef",
    "cartasOfertadasRef",
    "costo",
    "elemento",
    "fuerzaeste",
    "fuerzanorte",
    "fuerzaoeste",
    "fuerzasur",
    "id",
    "mensajesOferta",
    "mensajesPropuesta",
    "sumafuerzas"
})
public class CartaBean {

    @XmlElement(nillable = true)
    protected List<CartasDisponiblesBean> cartasDisponiblesRef;
    @XmlElement(nillable = true)
    protected List<CartasOfertadasBean> cartasOfertadasRef;
    protected int costo;
    protected String elemento;
    protected int fuerzaeste;
    protected int fuerzanorte;
    protected int fuerzaoeste;
    protected int fuerzasur;
    protected int id;
    @XmlElement(nillable = true)
    protected List<MensajesBean> mensajesOferta;
    @XmlElement(nillable = true)
    protected List<MensajesBean> mensajesPropuesta;
    protected int sumafuerzas;

    /**
     * Gets the value of the cartasDisponiblesRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cartasDisponiblesRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCartasDisponiblesRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CartasDisponiblesBean }
     * 
     * 
     */
    public List<CartasDisponiblesBean> getCartasDisponiblesRef() {
        if (cartasDisponiblesRef == null) {
            cartasDisponiblesRef = new ArrayList<CartasDisponiblesBean>();
        }
        return this.cartasDisponiblesRef;
    }

    /**
     * Gets the value of the cartasOfertadasRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cartasOfertadasRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCartasOfertadasRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CartasOfertadasBean }
     * 
     * 
     */
    public List<CartasOfertadasBean> getCartasOfertadasRef() {
        if (cartasOfertadasRef == null) {
            cartasOfertadasRef = new ArrayList<CartasOfertadasBean>();
        }
        return this.cartasOfertadasRef;
    }

    /**
     * Gets the value of the costo property.
     * 
     */
    public int getCosto() {
        return costo;
    }

    /**
     * Sets the value of the costo property.
     * 
     */
    public void setCosto(int value) {
        this.costo = value;
    }

    /**
     * Gets the value of the elemento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElemento() {
        return elemento;
    }

    /**
     * Sets the value of the elemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElemento(String value) {
        this.elemento = value;
    }

    /**
     * Gets the value of the fuerzaeste property.
     * 
     */
    public int getFuerzaeste() {
        return fuerzaeste;
    }

    /**
     * Sets the value of the fuerzaeste property.
     * 
     */
    public void setFuerzaeste(int value) {
        this.fuerzaeste = value;
    }

    /**
     * Gets the value of the fuerzanorte property.
     * 
     */
    public int getFuerzanorte() {
        return fuerzanorte;
    }

    /**
     * Sets the value of the fuerzanorte property.
     * 
     */
    public void setFuerzanorte(int value) {
        this.fuerzanorte = value;
    }

    /**
     * Gets the value of the fuerzaoeste property.
     * 
     */
    public int getFuerzaoeste() {
        return fuerzaoeste;
    }

    /**
     * Sets the value of the fuerzaoeste property.
     * 
     */
    public void setFuerzaoeste(int value) {
        this.fuerzaoeste = value;
    }

    /**
     * Gets the value of the fuerzasur property.
     * 
     */
    public int getFuerzasur() {
        return fuerzasur;
    }

    /**
     * Sets the value of the fuerzasur property.
     * 
     */
    public void setFuerzasur(int value) {
        this.fuerzasur = value;
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
     * Gets the value of the mensajesOferta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajesOferta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajesOferta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MensajesBean }
     * 
     * 
     */
    public List<MensajesBean> getMensajesOferta() {
        if (mensajesOferta == null) {
            mensajesOferta = new ArrayList<MensajesBean>();
        }
        return this.mensajesOferta;
    }

    /**
     * Gets the value of the mensajesPropuesta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajesPropuesta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajesPropuesta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MensajesBean }
     * 
     * 
     */
    public List<MensajesBean> getMensajesPropuesta() {
        if (mensajesPropuesta == null) {
            mensajesPropuesta = new ArrayList<MensajesBean>();
        }
        return this.mensajesPropuesta;
    }

    /**
     * Gets the value of the sumafuerzas property.
     * 
     */
    public int getSumafuerzas() {
        return sumafuerzas;
    }

    /**
     * Sets the value of the sumafuerzas property.
     * 
     */
    public void setSumafuerzas(int value) {
        this.sumafuerzas = value;
    }

}
