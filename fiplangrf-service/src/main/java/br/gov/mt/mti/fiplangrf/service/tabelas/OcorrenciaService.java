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
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoPendencia;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Ocorrencia;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;
import br.gov.mt.mti.fiplangrf.util.converter.FiplanConverter;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OcorrenciaService extends PaginableService<Ocorrencia, Long> {

	protected OcorrenciaService() {
		super(Ocorrencia.class);
	}

	@CheckBusinessAccess(permission = "save")
	public Ocorrencia checkAndSave(Ocorrencia ocorrencia) throws BusinessException {

		validate(ocorrencia);

		Criteria criteria = createCriteria();

		criteria.createAlias("unidadeAdministrativa", "unidadeAdministrativa");

		criteria.add(
				Restrictions.eq("unidadeAdministrativa.numrCNPJ", ocorrencia.getUnidadeAdministrativa().getNumrCNPJ()));
		criteria.add(Restrictions.eq("tipoOcorrencia", ocorrencia.getTipoOcorrencia()));
		criteria.add(Restrictions.ne("situacaoPendencia", DominioSituacaoPendencia.REGULARIZADA));

		if (ocorrencia.getGrupoPendencia() != null) {
			criteria.add(Restrictions.eq("grupoPendencia", ocorrencia.getGrupoPendencia()));
		}

		if (ocorrencia.getSubGrupoPendencia() != null) {
			criteria.add(Restrictions.eq("subGrupoPendencia", ocorrencia.getSubGrupoPendencia()));
		}

		if (ocorrencia.getTipoPendencia() != null) {
			criteria.add(Restrictions.eq("tipoPendencia", ocorrencia.getTipoPendencia()));
		}

		Ocorrencia consulta = (Ocorrencia) criteria.uniqueResult();

		boolean invalido = false;

		if (consulta != null) {
			if (ocorrencia.getId() == null) {
				invalido = true;
			}

			getSession().evict(consulta);

			if (!consulta.getId().equals(ocorrencia.getId())) {
				invalido = true;
			}
		}

		if (invalido) {
			throw new BusinessException(DominioMensagem.MSG_REGISTRO_JA_CADASTRADO.getDesc());
		}

		return (Ocorrencia) getSession().merge(ocorrencia);

	}

	@Override
	@CheckBusinessAccess(check = false)
	public Ocorrencia findByIdFetchAll(Long id) {
		Criteria criteria = getSession().createCriteria(getType());

		configCriteriaFind(criteria);

		criteria.add(Restrictions.eq("id", id));

		return (Ocorrencia) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public void validate(Ocorrencia ocorrencia) throws BusinessException {

		Criteria criteriaUO = getSession().createCriteria(FIPLANUnidadeOrcamentaria.class);
		List<FIPLANUnidadeOrcamentaria> listaUnidadeOrcamentaria = new ArrayList<>();
		listaUnidadeOrcamentaria = (List<FIPLANUnidadeOrcamentaria>) criteriaUO.list();
		if (ocorrencia.getUnidadeOrcamentaria() != null) {
			FIPLANUnidadeOrcamentaria fiplanUnidadeOrcamentaria = FiplanConverter
					.getFiplanFromUnidadeOrcamentaria(ocorrencia.getUnidadeOrcamentaria());
			if (!listaUnidadeOrcamentaria.contains(fiplanUnidadeOrcamentaria)
					&& ocorrencia.getSituacaoPendencia().equals(DominioSituacaoPendencia.PENDENTE)) {
				throw new BusinessException(DominioMensagem.MSG_UO_INVALIDA.getDesc());
			}
		}

		if (ocorrencia.getDataInscricao() != null && ocorrencia.getDataInscricao().after(Date.from(Instant.now()))) {
			throw new BusinessException(DominioMensagem.MSG_DATA_MAIOR_QUE_ATUAL.getDesc());
		}

		if (ocorrencia.getDataRegularizacao() != null
				&& ocorrencia.getDataRegularizacao().after(Date.from(Instant.now()))) {
			throw new BusinessException(DominioMensagem.MSG_DATA_MAIOR_QUE_ATUAL.getDesc());
		}

		if (ocorrencia.getListaAcaoCoordenadoria().isEmpty()) {
			throw new BusinessException(DominioMensagem.MSG_INFORMAR_ACAO_COORDENADORIA.getDesc());
		}
	}

	private void configCriteriaFind(Criteria criteria) {

		criteria.createAlias("listaAcaoCoordenadoria", "listaAcaoCoordenadoria", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("listaAcaoCoordenadoria", FetchMode.JOIN);

		criteria.createAlias("listaAcaoCoordenadoria.anexos", "anexosAcaoCoordenadoria", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("anexosAcaoCoordenadoria", FetchMode.JOIN);

		criteria.createAlias("listaMedidaIntermediaria", "listaMedidaIntermediaria", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("listaMedidaIntermediarias", FetchMode.JOIN);

		criteria.createAlias("listaMedidaIntermediaria.anexos", "anexosMedidaIntermediaria", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("anexosMedidaIntermediaria", FetchMode.JOIN);

		criteria.createAlias("listaEncerramentoPendencia", "listaEncerramentoPendencia", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("listaEncerramentoPendencia", FetchMode.JOIN);

		criteria.createAlias("listaEncerramentoPendencia.anexos", "anexosEncerramentoPendencia", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("anexosEncerramentoPendencia", FetchMode.JOIN);

		/*criteria.createAlias("tramites", "tramites", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("tramites", FetchMode.JOIN);
		
		criteria.createAlias("tramites.usuarioAcao", "usuarioAcao", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("usuarioAcao", FetchMode.JOIN);*/
	}
}
