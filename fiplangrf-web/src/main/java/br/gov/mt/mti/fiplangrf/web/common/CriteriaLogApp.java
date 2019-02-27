package br.gov.mt.mti.fiplangrf.web.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import lombok.Data;

@Data
@SuppressWarnings("rawtypes")
public class CriteriaLogApp<T extends DynamicSearchCriteria> implements Serializable, Comparable<CriteriaLogApp> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3053961225969466646L;
	
	private T criteria;
	
	private String label;
	
	private String value;

	private CriteriaLogApp(T criteria, String label, String value) {
		this.criteria = criteria;
		this.label = label;
		this.value = value;		
	}
	
	@SuppressWarnings("unchecked")
	public static CriteriaLogApp getInstance(final Map<String, CriteriaLogApp> map, DynamicSearchCriteria criteria) {
		
		Class<?> entityMetaData = (Class<?>)((ParameterizedType)criteria.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		CriteriaLogApp criteriaLogApp = new CriteriaLogApp(criteria, entityMetaData.getSimpleName(), entityMetaData.getName());
		
		map.put(entityMetaData.getName(), criteriaLogApp);
		
		return criteriaLogApp;
	}

	@Override
	public int compareTo(CriteriaLogApp o) {
		return getLabel().compareTo(o.getLabel());
	}

}
