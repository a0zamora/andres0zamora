
package cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mensajeTrueque complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mensajeTrueque">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idRespuesta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="laCarta" type="{http://servidor/}cartasDisponiblesTO" minOccurs="0"/>
 *         &lt;element name="miCarta" type="{http://servidor/}cartasDisponiblesTO" minOccurs="0"/>
 *         &lt;element name="nombreUsr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mensajeTrueque", propOrder = {
    "idRespuesta",
    "laCarta",
    "miCarta",
    "nombreUsr",
    "tipo"
})
public class MensajeTrueque {

    protected int idRespuesta;
    protected CartasDisponiblesTO laCarta;
    protected CartasDisponiblesTO miCarta;
    protected String nombreUsr;
    protected String tipo;

    /**
     * Gets the value of the idRespuesta property.
     * 
     */
    public int getIdRespuesta() {
        return idRespuesta;
    }

    /**
     * Sets the value of the idRespuesta property.
     * 
     */
    public void setIdRespuesta(int value) {
        this.idRespuesta = value;
    }

    /**
     * Gets the value of the laCarta property.
     * 
     * @return
     *     possible object is
     *     {@link CartasDisponiblesTO }
     *     
     */
    public CartasDisponiblesTO getLaCarta() {
        return laCarta;
    }

    /**
     * Sets the value of the laCarta property.
     * 
     * @param value
     *     allowed object is
     *     {@link CartasDisponiblesTO }
     *     
     */
    public void setLaCarta(CartasDisponiblesTO value) {
        this.laCarta = value;
    }

    /**
     * Gets the value of the miCarta property.
     * 
     * @return
     *     possible object is
     *     {@link CartasDisponiblesTO }
     *     
     */
    public CartasDisponiblesTO getMiCarta() {
        return miCarta;
    }

    /**
     * Sets the value of the miCarta property.
     * 
     * @param value
     *     allowed object is
     *     {@link CartasDisponiblesTO }
     *     
     */
    public void setMiCarta(CartasDisponiblesTO value) {
        this.miCarta = value;
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
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

}
