package br.gov.mt.mti.fiplangrf.service.auditoria;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.exception.AuditException;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.model.auditoria.EnversCriteria;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
public class ConsultarHistoricoService<T, PK> {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CheckBusinessAccess(check = false)
	public List consultarHistorico(PK id, EnversCriteria criteria, Class<T> type)
			throws AuditException, BusinessException {

		if (id == null) {
			// throw new IllegalArgumentException("id==null");
			return new ArrayList();
		}

		if (criteria.isValid()) {
			Session session = (Session) entityManager.getDelegate();
			AuditReader reader = AuditReaderFactory.get(session);

			AuditQuery query = reader.createQuery().forRevisionsOfEntity(type, false, false)
					.add(AuditEntity.id().eq(id));
			if (criteria.getDataInicial() != null) {
				query.add(AuditEntity.revisionProperty("dataOperacao").ge(criteria.getDataInicial()));
			}
			if (criteria.getDataFinal() != null) {

				Calendar dataProximoDiaMenos1Segundo = new GregorianCalendar();

				dataProximoDiaMenos1Segundo.setTime(criteria.getDataFinal());
				dataProximoDiaMenos1Segundo.set(Calendar.HOUR_OF_DAY, 0);
				dataProximoDiaMenos1Segundo.set(Calendar.MINUTE, 0);
				dataProximoDiaMenos1Segundo.set(Calendar.SECOND, 0);
				dataProximoDiaMenos1Segundo.set(Calendar.MILLISECOND, 0);
				dataProximoDiaMenos1Segundo.add(Calendar.DAY_OF_YEAR, 1);

				query.add(AuditEntity.revisionProperty("dataOperacao")
						.lt(dataProximoDiaMenos1Segundo.getTime()));
			}
			if (StringUtils.isNotEmpty(criteria.getNome())) {
				query.add(AuditEntity.revisionProperty("nomeUsuario")
						.ilike("%" + criteria.getNome().toUpperCase() + "%"));
			}

			List<T> result = query.addOrder(AuditEntity.revisionNumber().desc()).getResultList();
			for (Object o : result) {
				Object entity = ((Object[]) o)[0];
				inicializaEntity(entity);

			}

			return result;
		} else {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> consultarHistoricoIgnoreLazy(PK id, EnversCriteria criteria, Class<T> type)
			throws AuditException, BusinessException {

		if (id == null) {
			throw new IllegalArgumentException("id==null");
		}

		if (criteria.isValid()) {
			Session session = (Session) entityManager.getDelegate();
			AuditReader reader = AuditReaderFactory.get(session);

			AuditQuery query = reader.createQuery().forRevisionsOfEntity(type, false, false)
					.add(AuditEntity.id().eq(id));
			if (criteria.getDataInicial() != null) {
				query.add(AuditEntity.revisionProperty("dataOperacao").ge(criteria.getDataInicial()));
			}
			if (criteria.getDataFinal() != null) {

				Calendar dataProximoDiaMenos1Segundo = new GregorianCalendar();

				dataProximoDiaMenos1Segundo.setTime(criteria.getDataFinal());
				dataProximoDiaMenos1Segundo.set(Calendar.HOUR_OF_DAY, 0);
				dataProximoDiaMenos1Segundo.set(Calendar.MINUTE, 0);
				dataProximoDiaMenos1Segundo.set(Calendar.SECOND, 0);
				dataProximoDiaMenos1Segundo.set(Calendar.MILLISECOND, 0);
				dataProximoDiaMenos1Segundo.add(Calendar.DAY_OF_YEAR, 1);

				query.add(AuditEntity.revisionProperty("dataOperacao").lt(dataProximoDiaMenos1Segundo.getTime()));
			}
			if (StringUtils.isNotEmpty(criteria.getNome())) {
				query.add(AuditEntity.revisionProperty("nomeUsuario").like("%" + criteria.getNome() + "%"));
			}


			List<T> result = query.addOrder(AuditEntity.revisionNumber().desc()).getResultList();

			return result;
		} else {
			return null;
		}

	}

	private void inicializaEntity(Object entity) {
		Class<?> clazz = entity.getClass();
		while (clazz != null && !clazz.getName().equals("Object")) {
			for (Field f : clazz.getDeclaredFields()) {

				if (f.isAnnotationPresent(ManyToOne.class) || f.isAnnotationPresent(OneToOne.class)
						|| f.isAnnotationPresent(ManyToMany.class) || f.isAnnotationPresent(OneToMany.class)) {
					try {
						f.getName();
						// System.out.println(f.getName());
						Method m = entity.getClass().getMethod("get" + WordUtils.capitalize(f.getName()));
						Object consultado = m.invoke(entity);
						if (consultado != null)
							consultado.toString();
					} catch (Exception e) {
						// e.printStackTrace();
					}
				}
			}
			clazz = clazz.getSuperclass();
		}
	}

	@SuppressWarnings("unchecked")
	public T getHistoricoEntity(PK id, Integer revisionId, Class<T> type) {

		if (id == null) {
			throw new IllegalArgumentException("id==null");
		}

		Session session = (Session) entityManager.getDelegate();
		AuditReader reader = AuditReaderFactory.get(session);

		AuditQuery query = reader.createQuery().forEntitiesAtRevision(type, revisionId).add(AuditEntity.id().eq(id));

		T result = (T) query.addOrder(AuditEntity.revisionNumber().desc()).getSingleResult();

		return result;

	}
}
