package br.gov.mt.mti.fiplangrf.web.security.authentication.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="out" type="{http://beans.fiplan.cepromat.mt.gov.br}BeanWebService"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "out"
})
@XmlRootElement(name = "autenticarResponse")
public class AutenticarResponse {

    @XmlElement(required = true, nillable = true)
    protected BeanWebService out;

    /**
     * Obt�m o valor da propriedade out.
     * 
     * @return
     *     possible object is
     *     {@link BeanWebService }
     *     
     */
    public BeanWebService getOut() {
        return out;
    }

    /**
     * Define o valor da propriedade out.
     * 
     * @param value
     *     allowed object is
     *     {@link BeanWebService }
     *     
     */
    public void setOut(BeanWebService value) {
        this.out = value;
    }

}
