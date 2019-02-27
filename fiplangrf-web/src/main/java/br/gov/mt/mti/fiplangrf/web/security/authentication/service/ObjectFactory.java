package br.gov.mt.mti.fiplangrf.web.security.authentication.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.gov.mt.cepromat.fiplangnf.web.security.authentication.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BeanWebServiceCdErro_QNAME = new QName("http://beans.fiplan.cepromat.mt.gov.br", "cdErro");
    private final static QName _BeanWebServiceErro_QNAME = new QName("http://beans.fiplan.cepromat.mt.gov.br", "erro");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.gov.mt.cepromat.fiplangnf.web.security.authentication.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Autenticar }
     * 
     */
    public Autenticar createAutenticar() {
        return new Autenticar();
    }

    /**
     * Create an instance of {@link AutenticarResponse }
     * 
     */
    public AutenticarResponse createAutenticarResponse() {
        return new AutenticarResponse();
    }

    /**
     * Create an instance of {@link BeanWebService }
     * 
     */
    public BeanWebService createBeanWebService() {
        return new BeanWebService();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beans.fiplan.cepromat.mt.gov.br", name = "cdErro", scope = BeanWebService.class)
    public JAXBElement<Integer> createBeanWebServiceCdErro(Integer value) {
        return new JAXBElement<Integer>(_BeanWebServiceCdErro_QNAME, Integer.class, BeanWebService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beans.fiplan.cepromat.mt.gov.br", name = "erro", scope = BeanWebService.class)
    public JAXBElement<String> createBeanWebServiceErro(String value) {
        return new JAXBElement<String>(_BeanWebServiceErro_QNAME, String.class, BeanWebService.class, value);
    }

}
