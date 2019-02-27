package br.gov.mt.mti.fiplangrf.service.tabelas;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoAdministracao;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TipoAdministracaoService extends PaginableService<TipoAdministracao, Long> {

	protected TipoAdministracaoService() {
		super(TipoAdministracao.class);
	}
	
	@CheckBusinessAccess(permission = "save")
	public TipoAdministracao checkAndSave(TipoAdministracao tipoAdministracao) throws BusinessException{
		Criteria criteria = getSession().createCriteria(TipoAdministracao.class);

		criteria.add(
				Restrictions.and(
						Restrictions.ilike("descricao", tipoAdministracao.getDescricao()),
						Restrictions.eq("tipoPoder", tipoAdministracao.getTipoPoder())));

		TipoAdministracao consulta = (TipoAdministracao) criteria.uniqueResult();

		boolean invalido = false;

		if (consulta != null) {
			if (tipoAdministracao.getId() == null) {
				invalido = true;
			}

			getSession().evict(consulta);

			if (!consulta.getId().equals(tipoAdministracao.getId())) {
				invalido = true;
			}
		}

		if (invalido) {
			throw new BusinessException(DominioMensagem.MSG_REGISTRO_JA_CADASTRADO.getDesc());
		}

		return (TipoAdministracao) getSession().merge(tipoAdministracao);
	}
	
	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<TipoAdministracao> findByName(String findIt){
		
		Criteria criteria = getSession().createCriteria(TipoAdministracao.class);

		criteria.add(
				Restrictions.and(
						Restrictions.like("descricao", findIt, MatchMode.ANYWHERE).ignoreCase(), 
						Restrictions.eq("situacao", DominioSituacao.ATIVO)) );

		return (List<TipoAdministracao>)criteria.list();
	}
}
