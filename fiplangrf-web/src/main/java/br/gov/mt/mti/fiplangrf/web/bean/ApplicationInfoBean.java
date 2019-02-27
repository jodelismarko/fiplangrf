package br.gov.mt.mti.fiplangrf.web.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import javax.security.jacc.PolicyContextException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import br.gov.mt.cepromat.ceprofw.common.util.EnvUtil;
import br.gov.mt.cepromat.ceprofw.web.bean.ManterBaseBean;
import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;
import br.gov.mt.mti.fiplangrf.common.util.Env;
import br.gov.mt.mti.fiplangrf.web.security.authentication.FIPLANAuthenticationResponse;

@Named("appInfoBean")
@ApplicationScoped
public class ApplicationInfoBean extends ManterBaseBean {

	private static final long serialVersionUID = 8774604480444125982L;

	private static Properties prop;

	private InputStream inputStream;

	private static FIPLANAuthenticationResponse fiplanAuthenticationResponse;

	public ApplicationInfoBean() {

	}

	@PostConstruct
	public void init() {
		if (prop == null) {
			try {
				loadPropValues("application.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Long getId() {
		return null;
	}
	
	public UsuarioLogado getUsuarioLogado() {
		return Env.getUsuarioLogado();
	}

	public Object getVersion() throws PolicyContextException {

		return ApplicationInfoBean.getProperty("app.version");
	}

	public Object getBuildTime() throws PolicyContextException {

		return ApplicationInfoBean.getProperty("app.buildTime");
	}

	public String getTimestamp() {
		String env = EnvUtil.getAmbiente();
		if (StringUtils.isEmpty(env)) {
			env = "des";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return env + sdf.format(new Date());
	}

	private void loadPropValues(String propFile) throws IOException {

		try {
			if (prop == null)
				prop = new Properties();
			String propFileName = propFile;

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			setBuildTime(inputStream);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
	}

	private void setBuildTime(InputStream in) {
		try {
			File tempFile = File.createTempFile("ignore", "tmp");
			FileUtils.copyInputStreamToFile(in, tempFile);
			Long lastModified = tempFile.lastModified();
			Date buildTime = new Date(lastModified);
			SimpleDateFormat df = new SimpleDateFormat();
			df.applyPattern("dd/MM/yyyy HH:mm:ss");
			prop.setProperty("app.buildTime", df.format(buildTime));
		} catch (Exception e) {

		}

	}

	public static String getProperty(String key) {
		try {
			if (prop.keySet().contains(key))
				return prop.get(key).toString();
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public static void setAuthentication(FIPLANAuthenticationResponse resp) {

		ApplicationInfoBean.fiplanAuthenticationResponse = resp;
	}

	public static FIPLANAuthenticationResponse getAuthentication() {

		return fiplanAuthenticationResponse;
	}

	public FIPLANAuthenticationResponse getAuthenticationInfo() throws CloneNotSupportedException {
		FIPLANAuthenticationResponse resp = null;

		if (fiplanAuthenticationResponse != null) {
			resp = fiplanAuthenticationResponse.clone();
			fiplanAuthenticationResponse = null;
		}
		return resp;
	}

	public void setAuthenticationInfo(FIPLANAuthenticationResponse resp) {

		ApplicationInfoBean.fiplanAuthenticationResponse = resp;
	}

}
