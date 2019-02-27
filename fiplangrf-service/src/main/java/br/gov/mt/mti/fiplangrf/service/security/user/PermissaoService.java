package br.gov.mt.mti.fiplangrf.service.security.user;


import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.model.security.user.Permissao;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PermissaoService extends PaginableService<Permissao, Long> {

	protected PermissaoService(){
		super(Permissao.class);
	}
	
	@CheckBusinessAccess(permission="save")
	public void checkAndSave(Permissao permissao) throws BusinessException {
		Criteria criteria = getSession().createCriteria(Permissao.class);
		criteria.add(Restrictions.ilike("action", permissao.getAction()));
		Permissao consulta = (Permissao)criteria.uniqueResult();		
		
		boolean invalido = false;
		
		if(consulta != null) {
			if(permissao.getId() == null) {
				invalido = true;
			}
			
			getSession().evict(consulta);		
			
			if(!consulta.getId().equals(permissao.getId())) {
				invalido = true;
			}
		}
		
		if(invalido) {
			throw new BusinessException("Permissão já cadastrada.");
		}
		
		super.saveOrUpdate(permissao);
	}

}
