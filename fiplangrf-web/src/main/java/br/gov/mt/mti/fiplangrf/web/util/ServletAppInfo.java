package br.gov.mt.mti.fiplangrf.web.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import br.gov.mt.cepromat.ceprofw.common.util.EnvUtil;

@WebServlet(value = "/init", loadOnStartup = 1)
public class ServletAppInfo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7588450111457012894L;

	private static Properties prop;

	public void init() {
		if (prop == null) {
			try {
				loadPropValues("application.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		SimpleDateFormat sdfLocalTimeZone = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		sdfLocalTimeZone.setTimeZone(TimeZone.getTimeZone("America/Cuiaba"));
		
		getServletContext().setAttribute("version", getProperty("app.version"));
		
		String utcDateTime = getProperty("app.build");
		
		if(utcDateTime != null && !utcDateTime.isEmpty()) {
			try  {
				
				Date dataUTC = sdf.parse(utcDateTime);
				getServletContext().setAttribute("buildTime", sdfLocalTimeZone.format(dataUTC));
			} catch(Exception e) {}			
		}
		
		getServletContext().setAttribute("ambiente", EnvUtil.getAmbiente());

	}

	private void loadPropValues(String propFile) throws IOException {

		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFile)){
			if (prop == null)
				prop = new Properties();

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFile + "' not found in the classpath");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public String getProperty(String key) {
		try {
			if (prop.keySet().contains(key))
				return prop.get(key).toString();
		} catch (Exception e) {
			return null;
		}
		return null;
	}

}
