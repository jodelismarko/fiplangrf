package br.gov.mt.mti.fiplangrf.dominio;

import java.util.Properties;

import org.hibernate.HibernateException;

import br.gov.mt.cepromat.ceprofw.core.dominio.GenericEnumUserType;
import br.gov.mt.mti.fiplangrf.common.util.Constantes;

public class MTIFWGenericEnumUserType  extends GenericEnumUserType {

    public static final String PACOTE_DOMINIOS = Constantes.BASE_PACKAGE + ".dominio";
    
    public static final String ENUM_CLASS_NAME_PARAM = "enumClass";
    public static final String ENUM_CLASS_VALUE_METHOD = "getValueMethod";
    

    protected void getEnumClassName(Properties properties) {

        StringBuilder sbPacote = new StringBuilder(PACOTE_DOMINIOS);
        
        String enumClassName = properties.getProperty(ENUM_CLASS_NAME_PARAM);
        
        try {
            enumClass = Class.forName(sbPacote.append(".").append(enumClassName).toString()).asSubclass(Enum.class);
        } catch (ClassNotFoundException cnfe) {
            throw new HibernateException("Domínio não encontrado", cnfe);
        }
    }

}
