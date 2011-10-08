
package cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for jugadorTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="jugadorTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cartas" type="{http://servidor/}cartasDisponiblesTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idjugador" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="imagenes" type="{http://jaxb.dev.java.net/array}byteArray" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nivel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreAvatar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreusr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partidasEmpatas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partidasGanadas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partidasPerdidas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="puntos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "jugadorTO", propOrder = {
    "cartas",
    "clave",
    "correo",
    "idjugador",
    "imagenes",
    "nivel",
    "nombreAvatar",
    "nombreusr",
    "partidasEmpatas",
    "partidasGanadas",
    "partidasPerdidas",
    "puntos"
})
public class JugadorTO {

    @XmlElement(nillable = true)
    protected List<CartasDisponiblesTO> cartas;
    protected String clave;
    protected String correo;
    protected int idjugador;
    @XmlElement(nillable = true)
    protected List<ByteArray> imagenes;
    protected int nivel;
    protected String nombreAvatar;
    protected String nombreusr;
    protected int partidasEmpatas;
    protected int partidasGanadas;
    protected int partidasPerdidas;
    protected int puntos;

    /**
     * Gets the value of the cartas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cartas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCartas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CartasDisponiblesTO }
     * 
     * 
     */
    public List<CartasDisponiblesTO> getCartas() {
        if (cartas == null) {
            cartas = new ArrayList<CartasDisponiblesTO>();
        }
        return this.cartas;
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
     * Gets the value of the idjugador property.
     * 
     */
    public int getIdjugador() {
        return idjugador;
    }

    /**
     * Sets the value of the idjugador property.
     * 
     */
    public void setIdjugador(int value) {
        this.idjugador = value;
    }

    /**
     * Gets the value of the imagenes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imagenes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImagenes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ByteArray }
     * 
     * 
     */
    public List<ByteArray> getImagenes() {
        if (imagenes == null) {
            imagenes = new ArrayList<ByteArray>();
        }
        return this.imagenes;
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
     * Gets the value of the nombreusr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreusr() {
        return nombreusr;
    }

    /**
     * Sets the value of the nombreusr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreusr(String value) {
        this.nombreusr = value;
    }

    /**
     * Gets the value of the partidasEmpatas property.
     * 
     */
    public int getPartidasEmpatas() {
        return partidasEmpatas;
    }

    /**
     * Sets the value of the partidasEmpatas property.
     * 
     */
    public void setPartidasEmpatas(int value) {
        this.partidasEmpatas = value;
    }

    /**
     * Gets the value of the partidasGanadas property.
     * 
     */
    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    /**
     * Sets the value of the partidasGanadas property.
     * 
     */
    public void setPartidasGanadas(int value) {
        this.partidasGanadas = value;
    }

    /**
     * Gets the value of the partidasPerdidas property.
     * 
     */
    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    /**
     * Sets the value of the partidasPerdidas property.
     * 
     */
    public void setPartidasPerdidas(int value) {
        this.partidasPerdidas = value;
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

}
