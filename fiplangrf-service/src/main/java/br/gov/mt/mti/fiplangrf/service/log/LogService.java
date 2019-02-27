package br.gov.mt.mti.fiplangrf.service.log;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.criteria.AuditDisjunction;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.metadata.ClassMetadata;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.ColumnModel;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LogService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7776280583545169415L;
	@PersistenceContext
	private EntityManager em;	
	
	private Session getSession(){
		return em.unwrap(Session.class);
	}
	
	@CheckBusinessAccess(permission="find")
	public List<String> listarTabelasPorRev(Long codgAuditoria) {
		List<String> listaTabelasPorRev = getSession().doReturningWork(new ReturningWork<List<String>>() {
			@Override
			public List<String> execute(Connection conn) throws SQLException {
				List<String> listaTabelasPorRev = new ArrayList<String>();
				String sql = "SELECT ENTITY_NAME      \n" + 
							"  FROM DHYTB999_AUDITORIA_TABELAS\n" +
							" WHERE IDEN_AUDITORIA=? \n" +
							" ORDER BY ENTITY_NAME";
				
				try (PreparedStatement ps = conn.prepareStatement(sql)){
					
					ps.setLong(1, codgAuditoria);
					
					try (ResultSet rs = ps.executeQuery()){
						
						while(rs.next()) {
							listaTabelasPorRev.add(rs.getString("ENTITY_NAME"));
						}
					} 					
				}
				return listaTabelasPorRev;
			}
		});
		
		return listaTabelasPorRev;
	}
	
	@CheckBusinessAccess(permission="find")
	public List<Integer> listarRevisoes() {
		List<Integer> listaRevisoes = getSession().doReturningWork(new ReturningWork<List<Integer>>() {
			@Override
			public List<Integer> execute(Connection conn) throws SQLException {
				List<Integer> listaRevisoes = new ArrayList<Integer>();
				String sql = "SELECT IDEN_AUDITORIA      \n" + 
							"  FROM DHYTB999_AUDITORIA\n" + 
							" ORDER BY IDEN_AUDITORIA DESC";
				
				try (PreparedStatement ps = conn.prepareStatement(sql)){
					
					try (ResultSet rs = ps.executeQuery()){
						
						while(rs.next()) {
							listaRevisoes.add(rs.getInt("IDEN_AUDITORIA"));
						}
					} 					
				}
				return listaRevisoes;
			}
		});
		
		return listaRevisoes;
	}
	
	private AuditReader getAuditReader() {
		return AuditReaderFactory.get(em);
	}
	
	@SuppressWarnings("rawtypes")
	private AuditQuery getQuery(Class<?> clazz, DynamicSearchCriteria criteria, 
								Integer revisaoInicial, Integer revisaoFinal,
								Date dataInicial, Date dataFinal,
								List<RevisionType> operacoesSelecionadas,
								Long idRegistro,
								String nomeUsuario,
								String cpf) {		
		
		AuditQuery query = getAuditReader().createQuery().forRevisionsOfEntity(clazz, false, true)
				.addOrder(AuditEntity.revisionNumber().desc());	
		
		if(StringUtils.isNotBlank(nomeUsuario)) {
			query.add(AuditEntity.revisionProperty("nomeUsuario").ilike(nomeUsuario, MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(cpf)) {			
			query.add(AuditEntity.revisionProperty("numeroCPFUsuario").ilike(cpf.replaceAll("\\D", ""), MatchMode.ANYWHERE));
		}
		
		if(idRegistro != null) {
			
			ClassMetadata classMetadata = getSession().getSessionFactory().getClassMetadata(clazz);
			
			String idProperty = classMetadata.getIdentifierPropertyName();
			
			query.add(AuditEntity.property(idProperty).eq(idRegistro));
		}
		
		if(revisaoInicial != null && revisaoFinal == null) {
			query.add(AuditEntity.revisionNumber().eq(revisaoInicial));
		}
		
		if(revisaoInicial == null && revisaoFinal != null) {
			query.add(AuditEntity.revisionNumber().eq(revisaoFinal));
		}
		
		if(revisaoInicial != null && revisaoFinal != null) {
			query.add(AuditEntity.revisionNumber().between(revisaoInicial,revisaoFinal));
		}		
		
		if(dataInicial != null && dataFinal == null) {			
			query.add(AuditEntity.revisionProperty("dataOperacao").between(dataInicial, dataInicial));		
		}
		
		if(dataInicial == null && dataFinal != null) {			
			query.add(AuditEntity.revisionProperty("dataOperacao").between(dataFinal, dataFinal));
		}
		
		if(dataInicial != null && dataFinal != null) {
			query.add(AuditEntity.revisionProperty("dataOperacao").between(dataInicial, dataFinal));			
		}		
		
		if(operacoesSelecionadas != null && !operacoesSelecionadas.isEmpty()) {
			AuditDisjunction auditDisjunction = AuditEntity.disjunction();
			for(int i=0; i<operacoesSelecionadas.size(); i++) {
				RevisionType operacao = operacoesSelecionadas.get(i);
				auditDisjunction.add(AuditEntity.revisionType().eq(operacao));
			}
			query.add(auditDisjunction);
		}
		return query;
	}
	
	@SuppressWarnings("rawtypes")
	@CheckBusinessAccess(permission="find")
	public Long count(Class<?> clazz, DynamicSearchCriteria criteria, 
						Integer revisaoInicial, Integer revisaoFinal,
						Date dataInicial, Date dataFinal,
						List<RevisionType> operacoesSelecionadas,
						Long idRegistro,
						String nomeUsuario,
						String cpf) {
		AuditQuery query = getQuery(clazz, 
						criteria, 
						revisaoInicial, revisaoFinal, 
						dataInicial, dataFinal, 
						operacoesSelecionadas, 
						idRegistro,
						nomeUsuario,
						cpf);
		
	    Long count = (Long)query.addProjection(AuditEntity.id().count()).getSingleResult();
	    
	    return count;
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CheckBusinessAccess(permission="find")	
	public List<Object[]> search(Class<?> clazz, DynamicSearchCriteria criteria,								
								Integer revisaoInicial, Integer revisaoFinal,
								Date dataInicial, Date dataFinal,
								List<RevisionType> operacoesSelecionadas,
								Long idRegistro,
								String nomeUsuario,
								String cpf,
								int first, int pageSize) {
		
		
		AuditQuery query = getQuery(clazz, 
									criteria, 
									revisaoInicial, revisaoFinal, 
									dataInicial, dataFinal, 
									operacoesSelecionadas, 
									idRegistro,
									nomeUsuario,
									cpf);
		
		List<Object[]> resultList = query.getResultList();
		
		for(int i=0; i<resultList.size(); i++) {
			Object[] result = resultList.get(i);
			Object objRefresh = result[0];
			Long id = null;
			boolean entityRemovido = false;
			try {
				Method getId = objRefresh.getClass().getMethod("getId");
				id = (Long) getId.invoke(objRefresh);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			try {
				getSession().load(objRefresh.getClass(), id);
			} catch(EntityNotFoundException e) {
				  //Objeto foi removido (deletado) do banco de dados
				 entityRemovido = true;
			}
			
			Map<String, String> record = new HashMap<String, String>();
			for(ColumnModel column : criteria.getModel().getColumns()) {
				try {					
					record.put(column.getProperty(), BeanUtils.getProperty(objRefresh, column.getProperty()));					
				} catch (Exception e) {			
					if(entityRemovido) {
						record.put(column.getProperty(), "Entity Excluído");
					}
				} 
			}
			record.put("id", id.toString());
			resultList.get(i)[0] = record;			
		}
		
		return  resultList;
	}
}
