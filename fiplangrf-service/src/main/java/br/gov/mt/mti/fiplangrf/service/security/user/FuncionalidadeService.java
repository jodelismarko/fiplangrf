package br.gov.mt.mti.fiplangrf.service.security.user;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.security.user.Funcionalidade;
import br.gov.mt.mti.fiplangrf.model.security.user.Permissao;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FuncionalidadeService extends PaginableService<Funcionalidade, Long> {

	protected FuncionalidadeService(){
		super(Funcionalidade.class);
	}
	
	
	@Override
	public void saveOrUpdate(Funcionalidade entity) {
		if(entity.getId() == null) {
			getSession().persist(entity);
		} else {
			Funcionalidade func = (Funcionalidade)getSession().load(getType(), entity.getId());
			func.getPermissoes().clear();
			getSession().flush();
			for(Permissao permissao : entity.getPermissoes()) {
				permissao = (Permissao) getSession().load(Permissao.class, permissao.getId());
				func.getPermissoes().add(permissao);
			}
			
			getSession().saveOrUpdate(func);
		}
	}
	
	@CheckBusinessAccess(permission = "save")
	public void checkAndSave(Funcionalidade entity) throws BusinessException {
		String msgError = "Já existe funcionalidade de acesso com o nome informado.";

		Criteria c = getSession().createCriteria(getType());

		c.add(Restrictions.eq("descricao", entity.getDescricao()).ignoreCase());

		Funcionalidade perfil = (Funcionalidade) c.uniqueResult();

		if (perfil != null) {
			getSession().evict(perfil);
			boolean valido = true;
			if (entity.getId() == null) {
				valido = false;
			} else {
				valido = perfil.getId().equals(entity.getId());
			}
			if (!valido)
				throw new BusinessException(msgError);
		}

		super.saveOrUpdate(entity);
	}

	public Funcionalidade loadById(Long id) {
		Criteria criteria = getSession().createCriteria(getType());
		
		criteria.createAlias("permissoes", "permissao", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("permissoes", FetchMode.JOIN);
		
		criteria.add(Restrictions.eq("id", id));
		
		Funcionalidade funcionalidade = (Funcionalidade)criteria.uniqueResult();
		
		Hibernate.initialize(funcionalidade.getPermissoes());
		
		return funcionalidade;
	}
	
	@CheckBusinessAccess(check = false)
	public List<Funcionalidade> findAllNotInList(List<Funcionalidade> funcionalidadesPerfil) {
		List<Funcionalidade> funcionalidade = findAllActive();
		List<Funcionalidade> funcionalidadesNotInPerfil = new ArrayList<Funcionalidade>();

		for (Funcionalidade p : funcionalidade) {
			if (!funcionalidadesPerfil.contains(p)) {
				funcionalidadesNotInPerfil.add(p);
			}
		}
		
		return funcionalidadesNotInPerfil;
	}
	
	@CheckBusinessAccess(check = false)
	public List<Funcionalidade> findAllActive() {

		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("situacao", DominioSituacao.ATIVO));
		criteria.addOrder(Order.desc("nome"));
		
		@SuppressWarnings("unchecked")
		List<Funcionalidade> resultado = (List<Funcionalidade>) criteria.list();

		if (resultado == null)
			return new ArrayList<Funcionalidade>();
		return resultado;
	}
}
