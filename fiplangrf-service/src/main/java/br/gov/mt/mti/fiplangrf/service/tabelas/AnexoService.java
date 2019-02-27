package br.gov.mt.mti.fiplangrf.service.tabelas;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.mti.fiplangrf.model.tabelas.Anexo;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AnexoService extends PaginableService<Anexo, Long> {

	protected AnexoService() {
		super(Anexo.class);
	}

	@CheckBusinessAccess(check = false)
	public byte[] hibernateInitializeArquivoAnexo(Anexo anexo) {

		Criteria criteria = getSession().createCriteria(getType());
		criteria.createAlias("arquivoAnexo", "arquivoAnexo", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("arquivoAnexo", FetchMode.JOIN);

		criteria.add(Restrictions.eq("id", anexo.getId()));

		anexo = (Anexo) criteria.uniqueResult();

		return anexo.getArquivoAnexo().getArquivo();
	}

}