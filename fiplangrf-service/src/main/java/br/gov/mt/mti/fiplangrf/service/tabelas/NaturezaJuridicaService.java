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
import br.gov.mt.mti.fiplangrf.model.tabelas.NaturezaJuridica;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NaturezaJuridicaService extends PaginableService<NaturezaJuridica, Long> {

	protected NaturezaJuridicaService() {
		super(NaturezaJuridica.class);
	}

	@CheckBusinessAccess(permission = "save")
	public NaturezaJuridica checkAndSave(NaturezaJuridica naturezaJuridica) throws BusinessException {
		Criteria criteria = getSession().createCriteria(NaturezaJuridica.class);

		criteria.add(Restrictions.eq("numrNaturezaJuridica", naturezaJuridica.getNumrNaturezaJuridica()));

		NaturezaJuridica consulta = (NaturezaJuridica) criteria.uniqueResult();

		boolean invalido = false;

		if (consulta != null) {
			if (naturezaJuridica.getId() == null) {
				invalido = true;
			}

			getSession().evict(consulta);

			if (!consulta.getId().equals(naturezaJuridica.getId())) {
				invalido = true;
			}
		}

		if (invalido) {
			throw new BusinessException(DominioMensagem.MSG_REGISTRO_JA_CADASTRADO.getDesc());
		}

		return (NaturezaJuridica)getSession().merge(naturezaJuridica);
	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<NaturezaJuridica> findByName(String findIt) {

		Criteria criteria = getSession().createCriteria(NaturezaJuridica.class);

		criteria.add(
				Restrictions.and(
						Restrictions.like("descricao", findIt, MatchMode.ANYWHERE).ignoreCase(), 
						Restrictions.eq("situacao", DominioSituacao.ATIVO)) );

		return (List<NaturezaJuridica>) criteria.list();
	}
	
}
