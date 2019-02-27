package br.gov.mt.mti.fiplangrf.service.security.user;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.security.user.Funcionalidade;
import br.gov.mt.mti.fiplangrf.model.security.user.Perfil;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PerfilService extends PaginableService<Perfil, Long> {

	protected PerfilService() {
		super(Perfil.class);
	}

	@Override
	@CheckBusinessAccess(permission = "save")
	public void saveOrUpdate(Perfil entity) {
		Perfil perfil = (Perfil) getSession().load(getType(), entity.getId());
		perfil.getFuncionalidades().clear();
		getSession().flush();
		for (Funcionalidade func : entity.getFuncionalidades()) {
			Funcionalidade funcionalidade = (Funcionalidade) getSession().load(Funcionalidade.class, func.getId());
			perfil.getFuncionalidades().add(funcionalidade);
		}

		super.saveOrUpdate(perfil);
	}

	@CheckBusinessAccess(check = false)
	public List<Perfil> findAllNotInList(List<Perfil> perfisUsuario) {
		List<Perfil> perfis = findAllActive();
		List<Perfil> perfisNotInUser = new ArrayList<Perfil>();

		for (Perfil p : perfis) {
			if (!perfisUsuario.contains(p)) {
				perfisNotInUser.add(p);
			}
		}
		return perfisNotInUser;
	}

	@CheckBusinessAccess(permission = "save")
	public Perfil checkAndSave(Perfil entity) throws BusinessException {
		String msgError = "Já existe perfil de acesso com o nome informado.";

		Criteria c = getSession().createCriteria(getType());

		c.add(Restrictions.eq("descricao", entity.getDescricao()).ignoreCase());

		Perfil perfil = (Perfil) c.uniqueResult();

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

		return (Perfil) getSession().merge(entity);
	}

	@CheckBusinessAccess(permission = "find")
	public Perfil loadById(Long id) {
		Criteria criteria = getSession().createCriteria(getType());
		Perfil perfil = null;

		criteria.createAlias("funcionalidades", "funcionalidade", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("funcionalidades", FetchMode.JOIN);

		criteria.add(Restrictions.eq("id", id));

		perfil = (Perfil) criteria.uniqueResult();

		return perfil;
	}

	@CheckBusinessAccess(check = false)
	public List<Perfil> findAllActive() {

		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("situacao", DominioSituacao.ATIVO));
		criteria.addOrder(Order.desc("descricao"));
		
		@SuppressWarnings("unchecked")
		List<Perfil> resultado = (List<Perfil>) criteria.list();

		if (resultado == null)
			return new ArrayList<Perfil>();
		return resultado;
	}
}