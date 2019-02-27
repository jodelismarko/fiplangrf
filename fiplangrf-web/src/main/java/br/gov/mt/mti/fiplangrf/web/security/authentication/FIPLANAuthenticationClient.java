package br.gov.mt.mti.fiplangrf.web.security.authentication;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import br.gov.mt.cepromat.ceprofw.common.util.EnvUtil;
import br.gov.mt.mti.fiplangrf.web.security.authentication.service.BeanWebService;
import br.gov.mt.mti.fiplangrf.web.security.authentication.service.FiplanGrp;
import br.gov.mt.mti.fiplangrf.web.security.authentication.service.FiplanGrpPortType;

public class FIPLANAuthenticationClient {

	private static final String FIPLAN_PROPERTIES_FILE = "fiplan.properties";
	private static final String FIPLAN_SOAP_AUTHENTICATION_WSDL_PROPERTY = "fiplan.soap.authentication.wsdl";

	public static FIPLANAuthenticationResponse authenticate(String cpf, String senha) throws IOException {

		String cpfSanitized = cpf.replaceAll("[^0-9]", "");
		cpfSanitized = Long.valueOf(cpfSanitized).toString();

		Properties prop = new Properties();
		InputStream inputStream = FIPLANAuthenticationClient.class.getClassLoader()
				.getResourceAsStream(FIPLAN_PROPERTIES_FILE);
		prop.load(inputStream);

		String wsdlUrl = prop.getProperty(EnvUtil.getAmbiente() + "." + FIPLAN_SOAP_AUTHENTICATION_WSDL_PROPERTY);

		System.out.println("***********************");
		System.out.println("Create Web Service Client...");
		FiplanGrp service1 = new FiplanGrp(new URL(wsdlUrl));
		System.out.println("Create Web Service...");
		FiplanGrpPortType port1 = service1.getFiplanGrpHttpPort();

		BeanWebService result = port1.autenticar(cpfSanitized, senha);
		FIPLANAuthenticationResponse resp = new FIPLANAuthenticationResponse();
		resp.setCodigoResponse(result.getCdErro().getValue());

		if (resp.getCodigoResponse() == 2) {
			resp.setMsg("Acesso negado pelo FIPLAN. Motivo: " + result.getErro().getValue());
		}
		return resp;
	}

}