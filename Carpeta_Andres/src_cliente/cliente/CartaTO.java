
package cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cartaTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cartaTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="elemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fuerzaeste" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fuerzanorte" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fuerzaoeste" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fuerzasur" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idcarta" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "cartaTO", propOrder = {
    "costo",
    "elemento",
    "fuerzaeste",
    "fuerzanorte",
    "fuerzaoeste",
    "fuerzasur",
    "idcarta",
    "sumafuerzas"
})
public class CartaTO {

    protected int costo;
    protected String elemento;
    protected int fuerzaeste;
    protected int fuerzanorte;
    protected int fuerzaoeste;
    protected int fuerzasur;
    protected int idcarta;
    protected int sumafuerzas;

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
     * Gets the value of the idcarta property.
     * 
     */
    public int getIdcarta() {
        return idcarta;
    }

    /**
     * Sets the value of the idcarta property.
     * 
     */
    public void setIdcarta(int value) {
        this.idcarta = value;
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
