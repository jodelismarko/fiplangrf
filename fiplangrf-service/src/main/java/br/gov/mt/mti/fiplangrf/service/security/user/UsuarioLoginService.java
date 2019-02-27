package br.gov.mt.mti.fiplangrf.service.security.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import br.gov.mt.mti.fiplangrf.common.util.Constantes;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUsuario;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.service.security.interfaces.UsuarioLoginSvcLocal;
import br.gov.mt.mti.fiplangrf.service.security.interfaces.UsuarioLoginSvcRemote;

@Stateless
@LocalBean
@Local(value=UsuarioLoginSvcLocal.class)
@Remote(value=UsuarioLoginSvcRemote.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioLoginService implements UsuarioLoginSvcLocal, UsuarioLoginSvcRemote, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5271067167705809910L;
	
	@PersistenceContext
	private EntityManager em;
	
	private Session getSession(){
		return em.unwrap(Session.class);
	}

	public void updateDataLoginNULL(Usuario entity) {
		getSession().evict(entity);
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection conn) throws SQLException {
			}
			
		});
	}
	
	public void updateDataLogin(Usuario entity, String ip) {
		getSession().evict(entity);
		getSession().doWork(new Work() {

			@Override
			public void execute(Connection conn) throws SQLException {
				//Obs: esta conn não precisa ser fechada explicitamente, pois é gerenciada pelo Hibernate,
				//apesar do Warning que aparece na console				
				StringBuilder sql = new StringBuilder();
				sql.append("update ")
				   .append(Constantes.TABELA_USUARIO)
				   .append(" set data_ultimo_acesso=? ")
				   .append(" where IDEN_USUARIO=? ")
				;
				try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {					
					ps.setTimestamp(1,  new Timestamp(new Date().getTime()));
					ps.setLong(2, entity.getCodigo());
					ps.executeUpdate();
				}
			}
		});
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario findByEmail(String email){
		
		StringBuilder hql = new StringBuilder();
		hql.append("select vo from " + Usuario.class.getSimpleName()).append(" vo ")
		   .append(" where vo.email = :email ");
		
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("email", email);
		
		Usuario usuario = (Usuario)query.uniqueResult();
		
		em.detach(usuario);
		
		return usuario;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario findByLogin(String login){
		
		StringBuilder hql = new StringBuilder();
		
		/* Neste caso o login é sempre por CPF.
		 * Se o CPF for passado com a máscara, o método irá remover tudo o que NÃO é número.
		 */
		login = login.replaceAll("\\D+", "");
		
		hql.append("select vo from " + Usuario.class.getSimpleName())
		   .append(" vo ")
		   .append(" left join fetch vo.perfis perfil ")
		   .append(" left join fetch perfil.funcionalidades funcionalidade ")
		   .append(" left join fetch funcionalidade.permissoes permissao ")
		   .append(" where vo.login = :login and vo.situacao = :flagSituacao ");
		
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("login", login);
		query.setParameter("flagSituacao", DominioSituacao.ATIVO);
		
		return (Usuario)query.uniqueResult();
	}
	
	/**
	 * Método utilizado para buscar o usuario atraves do servico do FIPLAN.
	 * @param login
	 * @return
	 */
	@Override
	public FIPLANUsuario findFIPLANUsuarioByLogin(String cpf) {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("select vo from " + FIPLANUsuario.class.getSimpleName())
		   .append(" vo ")
		   .append(" where vo.login = :login ");
		
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("login", cpf.replaceAll("\\D+",""));
		
		return (FIPLANUsuario)query.uniqueResult();
	}
	
	
	@Override
	public Usuario loadByCodigo(Long codigo){
		Session session = getSession();
		Query query = session.createQuery("from Usuario u where u.codigo = :codigo");
		query.setParameter("codigo", codigo);
		return (Usuario)query.uniqueResult();
	}
}
