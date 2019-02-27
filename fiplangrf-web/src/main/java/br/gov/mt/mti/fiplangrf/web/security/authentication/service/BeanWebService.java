package br.gov.mt.mti.fiplangrf.web.security.authentication.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de BeanWebService complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="BeanWebService"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cdErro" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="erro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BeanWebService", namespace = "http://beans.fiplan.cepromat.mt.gov.br", propOrder = {
    "cdErro",
    "erro"
})
public class BeanWebService {

    @XmlElementRef(name = "cdErro", namespace = "http://beans.fiplan.cepromat.mt.gov.br", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> cdErro;
    @XmlElementRef(name = "erro", namespace = "http://beans.fiplan.cepromat.mt.gov.br", type = JAXBElement.class, required = false)
    protected JAXBElement<String> erro;

    /**
     * Obt�m o valor da propriedade cdErro.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCdErro() {
        return cdErro;
    }

    /**
     * Define o valor da propriedade cdErro.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCdErro(JAXBElement<Integer> value) {
        this.cdErro = value;
    }

    /**
     * Obt�m o valor da propriedade erro.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErro() {
        return erro;
    }

    /**
     * Define o valor da propriedade erro.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErro(JAXBElement<String> value) {
        this.erro = value;
    }

}
