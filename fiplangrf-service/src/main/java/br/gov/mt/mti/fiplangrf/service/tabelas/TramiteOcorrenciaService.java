package br.gov.mt.mti.fiplangrf.service.tabelas;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Query;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoPendencia;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.model.tabelas.Ocorrencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TramiteOcorrencia;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TramiteOcorrenciaService extends PaginableService<TramiteOcorrencia, Long> {

	protected TramiteOcorrenciaService() {
		super(TramiteOcorrencia.class);
	}
	
	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<TramiteOcorrencia> findAllByIdOcorrencia(Long idOcorrencia) {
		Query query = getSession().createQuery("from TramiteOcorrencia t where t.ocorrencia.id = :id order by t.dataTramiteOcorrencia desc");
		query.setParameter("id", idOcorrencia);
		return (List<TramiteOcorrencia>) query.list();
	}

	@CheckBusinessAccess(check = false)
	public void registrar(Ocorrencia ocorrencia, Usuario usuarioAcao) throws BusinessException {
		TramiteOcorrencia registrado = TramiteOcorrencia.registrado(ocorrencia, usuarioAcao);
		super.merge(registrado);
	}

	@CheckBusinessAccess(check = false)
	public void enviarParaRegularizacao(Ocorrencia ocorrencia, Usuario usuarioAcao) throws BusinessException {
		TramiteOcorrencia enviarParaAnalise = new TramiteOcorrencia(ocorrencia, usuarioAcao, DominioSituacaoPendencia.EM_REGULARIZACAO);
		super.merge(enviarParaAnalise);
	}

	@CheckBusinessAccess(check = false)
	public void devolverParaRegularizacao(Ocorrencia ocorrencia, Usuario usuarioAcao) throws BusinessException {
		TramiteOcorrencia devolverParaRegularizacao = new TramiteOcorrencia(ocorrencia, usuarioAcao, DominioSituacaoPendencia.EM_REGULARIZACAO);
		
		devolverParaRegularizacao.setJustificativa(ocorrencia.getJustificativaTramite());
		super.merge(devolverParaRegularizacao);
	}

	@CheckBusinessAccess(check = false)
	public void enviarParaAnalise(Ocorrencia ocorrencia, Usuario usuarioAcao) throws BusinessException {
		TramiteOcorrencia enviarParaAnalise = new TramiteOcorrencia(ocorrencia, usuarioAcao, DominioSituacaoPendencia.EM_ANALISE);
		super.merge(enviarParaAnalise);
	}

	@CheckBusinessAccess(check = false)
	public void devolverParaElaboracao(Ocorrencia ocorrencia, Usuario usuarioAcao) throws BusinessException {
		TramiteOcorrencia devolverParaElaboracao = new TramiteOcorrencia(ocorrencia, usuarioAcao, DominioSituacaoPendencia.PENDENTE);
		devolverParaElaboracao.setJustificativa(ocorrencia.getJustificativaTramite());
		super.merge(devolverParaElaboracao);
	}

	@CheckBusinessAccess(check = false)
	public void suspender(Ocorrencia ocorrencia, Usuario usuarioAcao) throws BusinessException {
		TramiteOcorrencia suspender = new TramiteOcorrencia(ocorrencia, usuarioAcao, DominioSituacaoPendencia.SUSPENSA);
		
		suspender.setJustificativa(ocorrencia.getJustificativaTramite());
		super.merge(suspender);
	}

	@CheckBusinessAccess(check = false)
	public void reativar(Ocorrencia ocorrencia, Usuario usuarioAcao) throws BusinessException {
		TramiteOcorrencia reativar = new TramiteOcorrencia(ocorrencia, usuarioAcao, DominioSituacaoPendencia.PENDENTE);
		reativar.setJustificativa(ocorrencia.getJustificativaTramite());
		super.merge(reativar);
	}

	@CheckBusinessAccess(check = false)
	public void finalizar(Ocorrencia ocorrencia, Usuario usuarioAcao) throws BusinessException {
		TramiteOcorrencia enviarParaAnalise = new TramiteOcorrencia(ocorrencia, usuarioAcao, DominioSituacaoPendencia.REGULARIZADA);
		super.merge(enviarParaAnalise);
	}
}