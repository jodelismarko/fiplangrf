package br.gov.mt.mti.fiplangrf.service.tabelas;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoPendencia;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TipoPendenciaService extends PaginableService<TipoPendencia, Long> {

	protected TipoPendenciaService() {
		super(TipoPendencia.class);
	}

	@CheckBusinessAccess(permission = "save")
	public TipoPendencia checkAndSave(TipoPendencia tipoPendencia) throws BusinessException {

		Criteria criteria = getSession().createCriteria(getType());

		criteria.add(
				Restrictions.and(
						Restrictions.or(
								Restrictions.like("sigla", tipoPendencia.getSigla().toLowerCase(), MatchMode.EXACT).ignoreCase(),
								Restrictions.like("descricao", tipoPendencia.getDescricao().toLowerCase(), MatchMode.EXACT).ignoreCase()), 
						Restrictions.eq("tipoOcorrencia", tipoPendencia.getTipoOcorrencia())));
		
		if (tipoPendencia.getGrupoPendencia() != null) {
			criteria.add(Restrictions.eq("grupoPendencia", tipoPendencia.getGrupoPendencia()));
		}
		
		if (tipoPendencia.getSubGrupoPendencia() != null) {
			criteria.add(Restrictions.eq("subGrupoPendencia", tipoPendencia.getSubGrupoPendencia()));
		}

		TipoPendencia consulta = (TipoPendencia) criteria.uniqueResult();

		boolean invalido = false;

		if (consulta != null) {
			if (tipoPendencia.getId() == null) {
				invalido = true;
			}

			getSession().evict(consulta);

			if (!consulta.getId().equals(tipoPendencia.getId())) {
				invalido = true;
			}
		}

		if (invalido) {
			throw new BusinessException(DominioMensagem.MSG_REGISTRO_JA_CADASTRADO.getDesc());
		}

		return (TipoPendencia) getSession().merge(tipoPendencia);
	}

	@Override
	@CheckBusinessAccess(permission = "find")
	public TipoPendencia findByIdFetchAll(Long id) {
		Criteria criteria = getSession().createCriteria(getType());

		configCriteriaFind(criteria);

		criteria.add(Restrictions.eq("id", id));

		return (TipoPendencia) criteria.uniqueResult();
	}

	private void configCriteriaFind(Criteria criteria) {
		
		criteria.createAlias("tipoOcorrencia","tipoOcorrencia", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("tipoOcorrencia", FetchMode.JOIN);
		
		criteria.createAlias("grupoPendencia","grupoPendencia", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("grupoPendencia", FetchMode.JOIN);
		
		criteria.createAlias("subGrupoPendencia","subGrupoPendencia", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("subGrupoPendencia", FetchMode.JOIN);
	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<TipoPendencia> findByName(String findIt) {
		Criteria criteria = getSession().createCriteria(TipoPendencia.class);
		
		configCriteriaFind(criteria);

		criteria.add(Restrictions.and(Restrictions.like("descricao", findIt, MatchMode.ANYWHERE).ignoreCase(),
				Restrictions.eq("situacao", DominioSituacao.ATIVO)));

		return (List<TipoPendencia>) criteria.list();
	}
}
