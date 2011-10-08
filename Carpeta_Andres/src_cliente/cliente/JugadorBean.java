
package cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for jugadorBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="jugadorBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cartasDisponiblesRef" type="{http://servidor/}cartasDisponiblesBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cartasOfertadasRef" type="{http://servidor/}cartasOfertadasBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mensajes" type="{http://servidor/}mensajesBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nivel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreAvatar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreUsr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partidaRef" type="{http://servidor/}partidaBean" minOccurs="0"/>
 *         &lt;element name="partidasEmpatadasParticipante1" type="{http://servidor/}partidasJugadas" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partidasEmpatadasParticipante2" type="{http://servidor/}partidasJugadas" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partidasGanadas" type="{http://servidor/}partidasJugadas" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partidasPerdidas" type="{http://servidor/}partidasJugadas" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="puntos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="respuesta" type="{http://servidor/}respuestaBean" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "jugadorBean", propOrder = {
    "cartasDisponiblesRef",
    "cartasOfertadasRef",
    "clave",
    "correo",
    "id",
    "mensajes",
    "nivel",
    "nombreAvatar",
    "nombreUsr",
    "partidaRef",
    "partidasEmpatadasParticipante1",
    "partidasEmpatadasParticipante2",
    "partidasGanadas",
    "partidasPerdidas",
    "puntos",
    "respuesta"
})
public class JugadorBean {

    @XmlElement(nillable = true)
    protected List<CartasDisponiblesBean> cartasDisponiblesRef;
    @XmlElement(nillable = true)
    protected List<CartasOfertadasBean> cartasOfertadasRef;
    protected String clave;
    protected String correo;
    protected int id;
    @XmlElement(nillable = true)
    protected List<MensajesBean> mensajes;
    protected int nivel;
    protected String nombreAvatar;
    protected String nombreUsr;
    protected PartidaBean partidaRef;
    @XmlElement(nillable = true)
    protected List<PartidasJugadas> partidasEmpatadasParticipante1;
    @XmlElement(nillable = true)
    protected List<PartidasJugadas> partidasEmpatadasParticipante2;
    @XmlElement(nillable = true)
    protected List<PartidasJugadas> partidasGanadas;
    @XmlElement(nillable = true)
    protected List<PartidasJugadas> partidasPerdidas;
    protected int puntos;
    @XmlElement(nillable = true)
    protected List<RespuestaBean> respuesta;

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
     * Gets the value of the clave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClave() {
        return clave;
    }

    /**
     * Sets the value of the clave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClave(String value) {
        this.clave = value;
    }

    /**
     * Gets the value of the correo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Sets the value of the correo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreo(String value) {
        this.correo = value;
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
     * Gets the value of the mensajes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MensajesBean }
     * 
     * 
     */
    public List<MensajesBean> getMensajes() {
        if (mensajes == null) {
            mensajes = new ArrayList<MensajesBean>();
        }
        return this.mensajes;
    }

    /**
     * Gets the value of the nivel property.
     * 
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Sets the value of the nivel property.
     * 
     */
    public void setNivel(int value) {
        this.nivel = value;
    }

    /**
     * Gets the value of the nombreAvatar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreAvatar() {
        return nombreAvatar;
    }

    /**
     * Sets the value of the nombreAvatar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreAvatar(String value) {
        this.nombreAvatar = value;
    }

    /**
     * Gets the value of the nombreUsr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreUsr() {
        return nombreUsr;
    }

    /**
     * Sets the value of the nombreUsr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreUsr(String value) {
        this.nombreUsr = value;
    }

    /**
     * Gets the value of the partidaRef property.
     * 
     * @return
     *     possible object is
     *     {@link PartidaBean }
     *     
     */
    public PartidaBean getPartidaRef() {
        return partidaRef;
    }

    /**
     * Sets the value of the partidaRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartidaBean }
     *     
     */
    public void setPartidaRef(PartidaBean value) {
        this.partidaRef = value;
    }

    /**
     * Gets the value of the partidasEmpatadasParticipante1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partidasEmpatadasParticipante1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartidasEmpatadasParticipante1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartidasJugadas }
     * 
     * 
     */
    public List<PartidasJugadas> getPartidasEmpatadasParticipante1() {
        if (partidasEmpatadasParticipante1 == null) {
            partidasEmpatadasParticipante1 = new ArrayList<PartidasJugadas>();
        }
        return this.partidasEmpatadasParticipante1;
    }

    /**
     * Gets the value of the partidasEmpatadasParticipante2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partidasEmpatadasParticipante2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartidasEmpatadasParticipante2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartidasJugadas }
     * 
     * 
     */
    public List<PartidasJugadas> getPartidasEmpatadasParticipante2() {
        if (partidasEmpatadasParticipante2 == null) {
            partidasEmpatadasParticipante2 = new ArrayList<PartidasJugadas>();
        }
        return this.partidasEmpatadasParticipante2;
    }

    /**
     * Gets the value of the partidasGanadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partidasGanadas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartidasGanadas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartidasJugadas }
     * 
     * 
     */
    public List<PartidasJugadas> getPartidasGanadas() {
        if (partidasGanadas == null) {
            partidasGanadas = new ArrayList<PartidasJugadas>();
        }
        return this.partidasGanadas;
    }

    /**
     * Gets the value of the partidasPerdidas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partidasPerdidas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartidasPerdidas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartidasJugadas }
     * 
     * 
     */
    public List<PartidasJugadas> getPartidasPerdidas() {
        if (partidasPerdidas == null) {
            partidasPerdidas = new ArrayList<PartidasJugadas>();
        }
        return this.partidasPerdidas;
    }

    /**
     * Gets the value of the puntos property.
     * 
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Sets the value of the puntos property.
     * 
     */
    public void setPuntos(int value) {
        this.puntos = value;
    }

    /**
     * Gets the value of the respuesta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the respuesta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRespuesta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RespuestaBean }
     * 
     * 
     */
    public List<RespuestaBean> getRespuesta() {
        if (respuesta == null) {
            respuesta = new ArrayList<RespuestaBean>();
        }
        return this.respuesta;
    }

}
