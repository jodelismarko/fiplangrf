package br.gov.mt.mti.fiplangrf.service.security.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUsuario;
import br.gov.mt.mti.fiplangrf.model.security.user.Perfil;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioService extends PaginableService<Usuario, Long> {

	public UsuarioService() {
		super(Usuario.class);
	}

	@CheckBusinessAccess(permission = "save")
	public Usuario incluir(Usuario usuario) throws BusinessException{
		return (Usuario) getSession().merge(usuario);
	}
	
	@CheckBusinessAccess(permission = "save")
	public Usuario updateUsuario(Usuario entity) {
		
		Usuario usuario = (Usuario) getSession().load(getType(), entity.getId());
		usuario.getPerfis().clear();
		getSession().flush();
		for (Perfil perf : entity.getPerfis()) {
			Perfil perfil = (Perfil) getSession().load(Perfil.class, perf.getId());
			usuario.getPerfis().add(perfil);
		}
		
		if (entity.getFoto() != null) {
			usuario.setFoto(entity.getFoto());
		}
		
		usuario.setSituacao(entity.getSituacao());

		return (Usuario) getSession().merge(usuario);
	}
	
	@CheckBusinessAccess(check = false)
	public Usuario findByLogin(String login) {
		Criteria criteria = getSession().createCriteria(getType());

		Usuario usuario = null;

		criteria.add(Restrictions.eq("login", login));

		usuario = (Usuario) criteria.uniqueResult();

		return usuario;
	}

	@CheckBusinessAccess(check = false)
	public FIPLANUsuario carregarUsuarioFIPLANByCPF(String cpf) throws BusinessException{
		String hql = " from FIPLANUsuario p where p.login = :login ";
		cpf = Long.valueOf(cpf).toString();
		Query q = getSession().createQuery(hql);
		q.setParameter("login", cpf);
		
		FIPLANUsuario usu = (FIPLANUsuario) q.uniqueResult();
		
		if (usu == null) {
			throw new BusinessException(DominioMensagem.MSG_CPF_NAO_CADASTRADO_NO_FIPLAN.getDesc());
		}
		
		return usu;
	}

	@Override
	@CheckBusinessAccess(permission = { "find" })
	public Usuario findByIdFetchAll(Long id) {
		Criteria criteria = getSession().createCriteria(getType());

		configCriteriaFind(criteria);

		Usuario usuario = null;

		criteria.add(Restrictions.eq("id", id));
		criteria.setCacheable(true);

		usuario = (Usuario) criteria.uniqueResult();

		return usuario;
	}

	@CheckBusinessAccess(permission = { "find" })
	public Usuario loadById(Long id) {
		Criteria criteria = getSession().createCriteria(getType());

		Usuario usuario = null;

		criteria.createAlias("perfis", "perfil", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("perfis", FetchMode.JOIN);

		criteria.add(Restrictions.eq("id", id));

		usuario = (Usuario) criteria.uniqueResult();

		Hibernate.initialize(usuario.getPerfis());

		return usuario;
	}

	@CheckBusinessAccess(permission = { "find" })
	public List<Usuario> listaUsuarioPorNomePerfil(String nome) {
		Criteria criteria = getSession().createCriteria(getType());

		criteria.createAlias("perfis", "perfil", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("perfis", FetchMode.JOIN);

		criteria.add(Restrictions.eq("perfil.nome", nome).ignoreCase());
		criteria.add(Restrictions.eq("situacao", DominioSituacao.ATIVO));

		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuario = criteria.list();

		return listaUsuario;
	}

	private void configCriteriaFind(Criteria criteria) {

		criteria.createAlias("perfis", "perfis", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("perfis", FetchMode.JOIN);
	}
}