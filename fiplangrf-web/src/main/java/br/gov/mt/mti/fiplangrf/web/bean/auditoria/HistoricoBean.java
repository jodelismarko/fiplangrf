package br.gov.mt.mti.fiplangrf.web.bean.auditoria;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import br.gov.mt.cepromat.ceprofw.web.bean.BaseBean;
import br.gov.mt.mti.fiplangrf.model.auditoria.EnversCriteria;
import lombok.Getter;
import lombok.Setter;



@SuppressWarnings("rawtypes")
public abstract class HistoricoBean<PK> extends BaseBean {

    
	private static final long serialVersionUID = 6826352667647212085L;
	
	@Getter @Setter
	protected EnversCriteria criteria;

	@PostConstruct
	public void inicializar() {
		criteria = new EnversCriteria();
	}
	
	public void limpar() {
		criteria = new EnversCriteria();

	}
	
	public abstract List<?> getResultadoPesquisa();
	
	protected abstract void pesquisar(ActionEvent event);

	protected abstract PK getId();
	
	protected abstract void setId(PK id);
	
	public abstract String visualizar();

}
