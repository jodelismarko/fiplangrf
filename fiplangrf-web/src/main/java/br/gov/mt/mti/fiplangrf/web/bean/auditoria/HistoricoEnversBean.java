package br.gov.mt.mti.fiplangrf.web.bean.auditoria;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.hibernate.envers.exception.AuditException;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.model.auditoria.CustomRevisionEntity;
import br.gov.mt.mti.fiplangrf.service.auditoria.ConsultarHistoricoService;
import lombok.Getter;
import lombok.Setter;

public abstract class HistoricoEnversBean<T,PK> extends HistoricoBean<PK> {

	private static final long serialVersionUID = 2458003271468938122L;

	@Inject
    private ConsultarHistoricoService<T,PK> consultarHistoricoService;
	
	@Getter @Setter
	protected List<T> resultadoPesquisa; 
	
	@Getter @Setter
	private Class<CustomRevisionEntity> model;
	
	protected abstract Class<T> getType();
	
	@Getter @Setter
	private InputText usuarioNomeFilter;
	
	@Getter @Setter
	private Calendar dataInicialFilter;
	
	@Getter @Setter
	private Calendar dataFinalFilter;
	
	@Getter @Setter
	private SelectOneMenu tipoOperacaoFilter;
	

	public void limparForm(){
		resultadoPesquisa = new ArrayList<>();
		getCriteria().setDataFinal(null);
		getCriteria().setDataInicial(null);
		getCriteria().setNome(null);
		
	}

	
	@SuppressWarnings("unchecked")
	public void pesquisar(ActionEvent event) {

		try {
			resultadoPesquisa = consultarHistoricoService.consultarHistorico(getId(),getCriteria(),getType());
		} catch (AuditException | BusinessException e) {
			e.printStackTrace();
		}
	}
	
	
	public void pesquisarIgnoreLazy(ActionEvent event) throws AuditException, BusinessException {

		resultadoPesquisa = consultarHistoricoService.consultarHistoricoIgnoreLazy(getId(),getCriteria(),getType());

	}
	
	@SuppressWarnings("unchecked")
	public void pesquisar() {

		try {
			resultadoPesquisa = consultarHistoricoService.consultarHistorico(getId(),getCriteria(),getType());
		} catch (AuditException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
