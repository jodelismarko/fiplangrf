package br.gov.mt.mti.fiplangrf.web.bean.base;

import javax.inject.Inject;

import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.cepromat.ceprofw.web.bean.BasePesquisaBean;
import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;
import br.gov.mt.mti.fiplangrf.service.security.GenericService;
import br.gov.mt.mti.fiplangrf.service.security.exception.PermissaoNegadaException;

public abstract class AbstractPesquisaBean<T extends DynamicSearchCriteria<?>> extends BasePesquisaBean<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5517241281151888101L;
	@Inject
	private UsuarioLogado usuarioLogado;
	@Inject
	protected GenericService genericService;

	public void setAction(String action) {
		usuarioLogado.setAction(action);
	}
	
	public void validarHasFuncionalidade(String ... funcionalidades) throws PermissaoNegadaException {    	
		if(funcionalidades == null || funcionalidades.length == 0) {
			throw new IllegalArgumentException(this.getClass().getName() + ".validarHasFuncionalidade [" +  "O parâmetro roles não deve ser vazio].");
		}
		if(!hasAtLeastOneFuncionalidade(funcionalidades)) {			
			throw new PermissaoNegadaException("O usuário não possui a devida Role para esta funcionalidade.");
		}
	}
    
	public boolean hasPermissao(String permissao){
		if(usuarioLogado != null){			
			return genericService.isPermitido(usuarioLogado.getCodigo(), usuarioLogado.getAction(), permissao);						
		}
		return false;
	}
    
	public boolean hasFuncionalidade(String funcionalidade) {
		if(usuarioLogado != null){			
			return (genericService.isInFuncionalidade(usuarioLogado.getCodigo(), funcionalidade));			
		}
		return false;
	}
	
	public boolean hasPapel(String... funcionalidades) {
		if(usuarioLogado != null){			
			return (genericService.isInPapel(usuarioLogado.getCodigo(), funcionalidades));			
		}
		return false;
	}
    
	public boolean hasAtLeastOneFuncionalidade(String ... funcionalidades) {
		for(String funcionalidade : funcionalidades) {
			if(hasFuncionalidade(funcionalidade)) {
				return true;
			}
		}
		return false;
	}

	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
