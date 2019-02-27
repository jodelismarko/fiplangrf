package br.gov.mt.mti.fiplangrf.service.security;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import br.gov.mt.cepromat.ceprofw.common.util.EnvUtil;
import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;
import br.gov.mt.mti.fiplangrf.common.util.Constantes;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GenericService implements Serializable {

	private static final long serialVersionUID = 7776280583545169415L;
	@PersistenceContext
	private EntityManager em;
	
	private Session getSession(){
		return em.unwrap(Session.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean isInFuncionalidade(Long codigo, String funcionalidade) {
		Integer count = getSession().doReturningWork(new ReturningWork<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				String sql = 
						"SELECT count(r.IDEN_FUNCIONALIDADE) qtde\n" + 
						"  FROM " + Constantes.TABELA_USUARIO + " u\n" + 
						"  JOIN DHWTB025_USUARIO_PERFIL upu on u.IDEN_USUARIO = upu.IDEN_USUARIO\n" + 
						"  JOIN DHWTB017_PERFIL_ACESSO pu on pu.IDEN_PERFIL_ACESSO = upu.IDEN_PERFIL_ACESSO\n" + 
						"  JOIN DHWTB024_PERFIL_FUNC pur on pur.IDEN_PERFIL_ACESSO = pu.IDEN_PERFIL_ACESSO\n" + 
						"  JOIN DHWTB027_FUNCIONALIDADE r on r.IDEN_FUNCIONALIDADE = pur.IDEN_FUNCIONALIDADE\n" +						 
						" WHERE u.IDEN_USUARIO = ?\n" + 
						"   AND r.flag_situacao = 'ATIVO'" +
						"   AND lower(r.nome) = lower(?)";
				try (PreparedStatement ps = conn.prepareStatement(sql)){
					ps.setLong(1, codigo);
					ps.setString(2, funcionalidade);
					
					try (ResultSet rs = ps.executeQuery()){
						
						if(rs.next()) {
							return rs.getInt("qtde");
						}
					}
					
					return 0;
				}
			}
		});
		
		return count.intValue() > 0;
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean isLoggedIn(final UsuarioLogado usuarioLogado) {
		Integer count = getSession().doReturningWork(new ReturningWork<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				
				if(EnvUtil.getAmbiente().equals("des") && usuarioLogado != null && usuarioLogado.getCpf().equals("99999999999")) {
					return 1;
				}
				
				String sql = 
						"SELECT count(1) qtde\n" + 
						"  FROM " + Constantes.TABELA_USUARIO + " u\n" +										 
						" WHERE u.IDEN_USUARIO = ?\n";
				try (PreparedStatement ps = conn.prepareStatement(sql)){
					ps.setLong(1, usuarioLogado.getCodigo());
					
					try (ResultSet rs = ps.executeQuery()){
						
						if(rs.next()) {
							return rs.getInt("qtde");
						}
					}
					
					return 0;
				}
			}
		});
		
		return count.intValue() > 0;
	}
	
	/**
	 * @param codigo
	 * @param itemMenuAcessado
	 * @param permissao
	 * @return boolean
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean isPermitido(Long codigo, String itemMenuAcessado, String permissao) {
		Integer count = getSession().doReturningWork(new ReturningWork<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				String sql = 
						"SELECT COUNT(DISTINCT FUNC.nome) qtde\n" + 
						"  FROM " + Constantes.TABELA_USUARIO + " USUARIO\n" + 
						"  JOIN DHWTB025_USUARIO_PERFIL upu on USUARIO.IDEN_USUARIO = upu.IDEN_USUARIO \n" + 
						"  JOIN DHWTB017_PERFIL_ACESSO PERFIL on PERFIL.IDEN_PERFIL_ACESSO = upu.IDEN_PERFIL_ACESSO \n" + 
						"  JOIN DHWTB024_PERFIL_FUNC pur on pur.IDEN_PERFIL_ACESSO = PERFIL.IDEN_PERFIL_ACESSO \n" + 
						"  JOIN DHWTB027_FUNCIONALIDADE FUNC on FUNC.IDEN_FUNCIONALIDADE = pur.IDEN_FUNCIONALIDADE \n" + 
						"  JOIN DHWTB026_FUNC_PERMISSAO rp on rp.IDEN_FUNCIONALIDADE = FUNC.IDEN_FUNCIONALIDADE \n" + 
						"  JOIN DHWTB028_PERMISSAO PERMISSAO on PERMISSAO.IDEN_PERMISSAO = rp.IDEN_PERMISSAO  \n" + 
						" WHERE USUARIO.IDEN_USUARIO = ?   \n" + 
						"   AND LOWER(PERMISSAO.ACTION) = ?\n" +
						"   AND FUNC.FLAG_SITUACAO = 'ATIVO' "+  
						"   AND EXISTS(SELECT 1 \n" + 
						"                FROM DHWTB026_FUNC_PERMISSAO B\n" + 
						"                JOIN DHWTB028_PERMISSAO G ON G.IDEN_PERMISSAO = B.IDEN_PERMISSAO\n" + 
						"               WHERE B.IDEN_FUNCIONALIDADE = FUNC.IDEN_FUNCIONALIDADE\n" +
						"				  AND G.TIPO_PERMISSAO= 'GATILHO' \n" +
						"                 AND LOWER(G.ACTION) = ? \n" + 
						"              ) ";
				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setLong(1, codigo);
					ps.setString(2, permissao.toLowerCase());
					ps.setString(3, itemMenuAcessado.toLowerCase());
					try (ResultSet rs = ps.executeQuery()){					
						if(rs.next()) {
							return rs.getInt("qtde");
						}
					}
					return 0;
				}
			}
		});
		
		return count.intValue() > 0;
	}
	
	public boolean isInPapel(Long codigo, String... permissoesPapel) {
		for(String permissao : permissoesPapel) {
			if(isInPapelPermissao(codigo, permissao)) {
				return true;
			}
		}
		return false;
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean isInPapelPermissao(Long codigo, String permissaoPapel) {
		Integer count = getSession().doReturningWork(new ReturningWork<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				String sql = "SELECT COUNT(DISTINCT FUNC.nome) qtde\n" + 
						"  FROM DHWTB018_USUARIO USUARIO\n" + 
						"  JOIN DHWTB025_USUARIO_PERFIL upu on USUARIO.IDEN_USUARIO = upu.IDEN_USUARIO \n" + 
						"  JOIN DHWTB017_PERFIL_ACESSO PERFIL on PERFIL.IDEN_PERFIL_ACESSO = upu.IDEN_PERFIL_ACESSO \n" + 
						"  JOIN DHWTB024_PERFIL_FUNC pur on pur.IDEN_PERFIL_ACESSO = PERFIL.IDEN_PERFIL_ACESSO \n" + 
						"  JOIN DHWTB027_FUNCIONALIDADE FUNC on FUNC.IDEN_FUNCIONALIDADE = pur.IDEN_FUNCIONALIDADE \n" + 
						"  JOIN DHWTB026_FUNC_PERMISSAO rp on rp.IDEN_FUNCIONALIDADE = FUNC.IDEN_FUNCIONALIDADE \n" + 
						"  JOIN DHWTB028_PERMISSAO PERMISSAO on PERMISSAO.IDEN_PERMISSAO = rp.IDEN_PERMISSAO  \n" + 
						" WHERE USUARIO.IDEN_USUARIO = ?   \n" + 
						"   AND LOWER(PERMISSAO.ACTION) = ?\n" +
						"   AND PERMISSAO.TIPO_PERMISSAO = 'PAPEL'\n" +
						"   AND FUNC.FLAG_SITUACAO = 'ATIVO'"   
						;
				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setLong(1, codigo);
					ps.setString(2, permissaoPapel.toLowerCase());					
					try (ResultSet rs = ps.executeQuery()){					
						if(rs.next()) {
							return rs.getInt("qtde");
						}
					}
					return 0;
				}
			}
		});
		
		return count.intValue() > 0;
	}
}
