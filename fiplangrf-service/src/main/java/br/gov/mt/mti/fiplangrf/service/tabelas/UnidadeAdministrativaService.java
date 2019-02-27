package br.gov.mt.mti.fiplangrf.service.tabelas;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.model.tabelas.UnidadeAdministrativa;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UnidadeAdministrativaService extends PaginableService<UnidadeAdministrativa, Long> {

	protected UnidadeAdministrativaService() {
		super(UnidadeAdministrativa.class);
	}

	@CheckBusinessAccess(permission = "save")
	public UnidadeAdministrativa incluir(UnidadeAdministrativa unidadeAdministrativa) throws BusinessException {
		validate(unidadeAdministrativa);
		
		Criteria criteria = getSession().createCriteria(UnidadeAdministrativa.class);

		criteria.add(Restrictions.eq("numrCNPJ", unidadeAdministrativa.getNumrCNPJ()));

		UnidadeAdministrativa consulta = (UnidadeAdministrativa) criteria.uniqueResult();

		boolean invalido = false;

		if (consulta != null) {
			if (unidadeAdministrativa.getId() == null) {
				invalido = true;
			}

			getSession().evict(consulta);

			if (!consulta.getId().equals(unidadeAdministrativa.getId())) {
				invalido = true;
			}
		}

		if (invalido) {
			throw new BusinessException(DominioMensagem.MSG_REGISTRO_JA_CADASTRADO.getDesc());
		}

		return (UnidadeAdministrativa) getSession().merge(unidadeAdministrativa);
	}

	@CheckBusinessAccess(check = false)
	public UnidadeAdministrativa findByCNPJ(Long numrCNPJ) throws BusinessException {

		Criteria criteria = getSession().createCriteria(UnidadeAdministrativa.class);

		criteria.add(Restrictions.and(Restrictions.eq("numrCNPJ", numrCNPJ),
				Restrictions.eq("situacao", DominioSituacao.ATIVO)));

		UnidadeAdministrativa consulta = (UnidadeAdministrativa) criteria.uniqueResult();

		if (consulta == null) {
			throw new BusinessException(DominioMensagem.MSG_NENHUM_REGISTRO_ENCONTRADO.getDesc());
		}

		return consulta;
	}

	@Override
	@CheckBusinessAccess(permission = "find")
	public UnidadeAdministrativa findByIdFetchAll(Long id) {
		Criteria criteria = getSession().createCriteria(getType());

		configCriteriaFind(criteria);

		criteria.add(Restrictions.eq("id", id));

		return (UnidadeAdministrativa) criteria.uniqueResult();
	}

	private void configCriteriaFind(Criteria criteria) {

		criteria.createAlias("cnaes", "cnaes", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("cnaes", FetchMode.JOIN);

		criteria.createAlias("cnaes.cnae", "cnae", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("cnae", FetchMode.JOIN);

		criteria.createAlias("legislacaoUAs", "legislacaoUAs", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("legislacaoUAs", FetchMode.JOIN);

	}
	
	@SuppressWarnings("unchecked")
	public void validate(UnidadeAdministrativa unidadeAdministrativa) throws BusinessException {
		
		Criteria criteriaUO = getSession().createCriteria(FIPLANUnidadeOrcamentaria.class);
		List<FIPLANUnidadeOrcamentaria> listaUnidadeOrcamentaria = new ArrayList<>(); 
		listaUnidadeOrcamentaria = (List<FIPLANUnidadeOrcamentaria>)criteriaUO.list();
		
		if (unidadeAdministrativa.getFiplanUnidadeOrcamentaria() != null && unidadeAdministrativa.getFiplanUnidadeOrcamentaria().getCodigoUnidadeOrcamentaria() != null && !listaUnidadeOrcamentaria.contains(unidadeAdministrativa.getFiplanUnidadeOrcamentaria())) {
			throw new BusinessException(DominioMensagem.MSG_UO_INVALIDA.getDesc());
		}
		
		if (unidadeAdministrativa.getDataAbertura().after(Date.from(Instant.now()))) {
			throw new BusinessException(DominioMensagem.MSG_DATA_MAIOR_QUE_ATUAL.getDesc());
		}

		if (unidadeAdministrativa.getDataTermino() != null && unidadeAdministrativa.getDataTermino().after(Date.from(Instant.now()))) {
			throw new BusinessException(DominioMensagem.MSG_DATA_MAIOR_QUE_ATUAL.getDesc());
		}

		if ((unidadeAdministrativa.getDataTermino() != null && unidadeAdministrativa.getDataAbertura() != null) && unidadeAdministrativa.getDataTermino().before(unidadeAdministrativa.getDataAbertura())) {
			throw new BusinessException(DominioMensagem.MSG_DATA_MENOR_QUE_ABERTURA.getDesc());
		}

		if (unidadeAdministrativa.getCnaes().isEmpty() || !unidadeAdministrativa.existeCnaePrincipal()) {
			throw new BusinessException(DominioMensagem.MSG_INFORMAR_CNAE_UNIDADE_ADMINISTRATIVA.getDesc());
		}

	}

}
