package br.gov.mt.mti.fiplangrf.web.bean.relatorios;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterField;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.service.relatorio.RelatorioGenericoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

public abstract class AbstractRelatorioBean<T extends DynamicSearchCriteria<?>> extends AbstractPesquisaBean<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4627720277328292911L;
	@Inject
	protected RelatorioGenericoService relatorioGenericoService;
	
	protected void executarRelatorio(String reportPath) throws BusinessException {
		relatorioGenericoService.executarRelatorio(reportPath, getCriteria());
	}	

	protected void showMessage(String message) {	
		RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));		
	}

	public abstract String getKey();
	
	public abstract String getReportPath();
	
	public abstract String getPopUpPath();
	
	public void putCriteriaBuffer() {
		getSessionBuffer().put(getKey(), getCriteria());
	}
	
	@SuppressWarnings("unchecked")
	public T getCriteriaBuffer() {
		return (T)getSessionBuffer().get(getKey());
	}
	
	public List<FilterField> getSelectedFields(){
		return getCriteria().getModel().getSelectedFields();
	}
	
	public void resetarParametros() {
		getSelectedFields().clear();
		relatorioGenericoService.resetarParametros();
	}
	
	public void carregarRelatorio() throws BusinessException {
		if (getCriteriaBuffer() != null) {
			setCriteria(getCriteriaBuffer());
		}
		
		executarRelatorio(getReportPath());
	}
	
	public void executarRelatorio() throws BusinessException, IOException {
		for (final FilterField filterField : getSelectedFields()) {

			if (filterField.isRequired() && filterField.getValue() == null) {
				showMessage("Por favor informe o filtro " + filterField.getLabel() + ".");
				return;
			}
			
		}
		putCriteriaBuffer();
		abrirPopUp(getPopUpPath());
	}
	
	/**
	 * Este método deverá retornar um mapeamento do campo FieldFilter.property
	 * para a respectiva página de popup do tipo LUPA.
	 * @return
	 */
	public Map<String, String> getPageMappingLupa(){
		return new HashMap<String, String>();
	};
	
	public boolean isFilterFieldEmpty(final FilterField field) {
		Object value = field.getValue();
		if(value == null) {
			return true;
		} else if((value instanceof String) && ((String) value).isEmpty()) {
			return true;		
		}
		return false;
	}
}
