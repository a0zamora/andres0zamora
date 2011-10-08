
package cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for partidaBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partidaBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="empate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ganador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jugadores" type="{http://servidor/}jugadorBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="perdedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partidaBean", propOrder = {
    "empate",
    "ganador",
    "id",
    "jugadores",
    "perdedor"
})
public class PartidaBean {

    protected int empate;
    protected String ganador;
    protected int id;
    @XmlElement(nillable = true)
    protected List<JugadorBean> jugadores;
    protected String perdedor;

    /**
     * Gets the value of the empate property.
     * 
     */
    public int getEmpate() {
        return empate;
    }

    /**
     * Sets the value of the empate property.
     * 
     */
    public void setEmpate(int value) {
        this.empate = value;
    }

    /**
     * Gets the value of the ganador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGanador() {
        return ganador;
    }

    /**
     * Sets the value of the ganador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGanador(String value) {
        this.ganador = value;
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
     * Gets the value of the jugadores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jugadores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJugadores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JugadorBean }
     * 
     * 
     */
    public List<JugadorBean> getJugadores() {
        if (jugadores == null) {
            jugadores = new ArrayList<JugadorBean>();
        }
        return this.jugadores;
    }

    /**
     * Gets the value of the perdedor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerdedor() {
        return perdedor;
    }

    /**
     * Sets the value of the perdedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerdedor(String value) {
        this.perdedor = value;
    }

}
