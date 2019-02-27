package br.gov.mt.mti.fiplangrf.util.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.hibernate.jdbc.Work;

import br.gov.mt.cepromat.ceprofw.core.dominio.DominioFormatoSaidaRelatorio;
import lombok.Data;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.DocxExporterConfiguration;
import net.sf.jasperreports.export.DocxReportConfiguration;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.XlsxExporterConfiguration;
import net.sf.jasperreports.export.XlsxReportConfiguration;

@Data
public class ReportExporter implements Work {

	public ReportExporter(String caminhoRelatorio, Map<String, Object> parametros, DominioFormatoSaidaRelatorio dominioFormatoSaidaRelatorio) {
		super();
		this.caminhoRelatorio = caminhoRelatorio;
		this.parametros = parametros;
		this.dominioFormatoSaidaRelatorio = dominioFormatoSaidaRelatorio;

	}

	private String caminhoRelatorio;
	private DominioFormatoSaidaRelatorio dominioFormatoSaidaRelatorio;
	private Map<String, Object> parametros;
	private ByteArrayOutputStream relatorio = new ByteArrayOutputStream();
	private boolean relatorioGerado;

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			//if (EnvUtil.getAmbiente().equals("des")) {
			//	mostraParametros();	
			//}
			
			String pathReport = FacesContext.getCurrentInstance().getExternalContext().getRealPath(this.caminhoRelatorio);
			JasperPrint print = JasperFillManager.fillReport(new FileInputStream(new File(pathReport)), this.parametros, connection);
			this.relatorioGerado = print.getPages().size() > 0;

			if (this.relatorioGerado) {

				if (dominioFormatoSaidaRelatorio.equals(DominioFormatoSaidaRelatorio.XLS)) {
					Exporter<ExporterInput, XlsxReportConfiguration, XlsxExporterConfiguration, OutputStreamExporterOutput> exportador = new JRXlsxExporter();
					exportador.setExporterInput(new SimpleExporterInput(print));
					exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(relatorio));
					exportador.exportReport();

				} else if (dominioFormatoSaidaRelatorio.equals(DominioFormatoSaidaRelatorio.DOC)) {
					Exporter<ExporterInput, DocxReportConfiguration, DocxExporterConfiguration, OutputStreamExporterOutput> exportador = new JRDocxExporter();
					exportador.setExporterInput(new SimpleExporterInput(print));
					exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(relatorio));
					exportador.exportReport();
				} else {
					Exporter<ExporterInput, PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput> exportador = new JRPdfExporter();
					exportador.setExporterInput(new SimpleExporterInput(print));
					exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(relatorio));
					exportador.exportReport();
				}

			}
		} catch (Exception e) {
			e.getMessage();
			throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
		}

	}

//	private void mostraParametros() {
//
//		if (this.getParametros().size() > 0) {
//
//			System.out.println("Parametros Informados ");
//			Set<Entry<String, Object>> lista = this.getParametros().entrySet();
//
//			for (Entry<String, Object> e : lista) {
//				System.out.println("--------------------");
//				System.out.println("Chave...:: " + e.getKey());
//				System.out.println("Valor...:: " + e.getValue());
//			}
//			System.out.println("--------------------");
//		}
//	}

}
