package br.gov.mt.mti.fiplangrf.web.bean.log;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.envers.RevisionType;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.Expression;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterField;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.service.log.LogService;

@SuppressWarnings({"unchecked","rawtypes"})
public class LazyAuditDataModel extends LazyDataModel<Object[]> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7584448610822290371L;
	
	protected Class<?> clazz;
	protected DynamicSearchCriteria<?> criteria;
	protected Integer revisaoInicial;
	protected Integer revisaoFinal;
	protected Date dataInicial;
	protected Date dataFinal;
	protected List<RevisionType> operacoesSelecionadas;
	protected Long idRegistro;
	protected String nomeUsuario;
	protected String cpf;
	
	private List<Object[]> datasource;
	
	private LogService logService;
	
	public LazyAuditDataModel(  final LogService logService,
								Class<?> clazz, DynamicSearchCriteria<?> criteria,								
								Integer revisaoInicial, Integer revisaoFinal,
								Date dataInicial, Date dataFinal,
								List<RevisionType> operacoesSelecionadas,
								Long idRegistro,
								String nomeUsuario,
								String cpf) {
		this.logService = logService;
		setClazz(clazz);
		setCriteria(criteria);
		setRevisaoInicial(revisaoInicial);
		setRevisaoFinal(revisaoFinal);
		setDataInicial(dataInicial);
		setDataFinal(dataFinal);
		setOperacoesSelecionadas(operacoesSelecionadas);
		setIdRegistro(idRegistro);
		setNomeUsuario(nomeUsuario);
		setCpf(cpf);
	}
	
	@Override
    public Object[] getRowData(String rowKey) {
        for(Object[] array : datasource) {
            if(((Map<String, String>)array[0]).get("id").equals(rowKey))
                return array;
        }
 
        return null;
    }
	
	@Override
    public Object getRowKey(Object[] array) {
		Map<String, String> map = (Map<String, String>) array[0];
        return map.get("id");
    }
	
	class LazySorter implements Comparator<Object[]> {
		 
	    private String sortField;
	     
	    private SortOrder sortOrder;
	     
	    public LazySorter(String sortField, SortOrder sortOrder) {
	        this.sortField = sortField;
	        this.sortOrder = sortOrder;
	    }
	 
	    public int compare(Object[] arr1, Object[] arr2) {
	        try {
	        	Map<String, String> map1 = (Map<String, String>)arr1[0];
	        	Map<String, String> map2 = (Map<String, String>)arr2[0];
	        	
	            Object value1 = map1.get(this.sortField) == null ? "" : map1.get(this.sortField);
	            Object value2 = map2.get(this.sortField) == null ? "" : map2.get(this.sortField);
	 
	            int value = ((Comparable)value1).compareTo(value2);
	             
	            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
	        }
	        catch(Exception e) {
	            throw new RuntimeException();
	        }
	    }
	}
	
	protected void configFilterByColumn(Map<String, Object> filters) throws IllegalArgumentException{
		if(!criteria.isFilterByColumn()) return;
		if(filters.isEmpty()){
			Set<String> clear = new HashSet<String>();
			for(Field field : criteria.getClass().getDeclaredFields()){
				if(field.isAnnotationPresent(FilterFieldOption.class)){
					FilterFieldOption filter = field.getAnnotation(FilterFieldOption.class);
					try {
						Method method = criteria.getClass().getMethod("get" + 
								Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1));
						Object object = method.invoke(criteria);
						if(object == null){
							clear.add(filter.property());
						}
					} catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			Iterator<FilterField> ite = criteria.getModel().getSelectedFields().iterator();
			while(ite.hasNext()){
				FilterField f = ite.next();
				if(clear.contains(f.getField())){
					ite.remove();
				}
			}
			return;
		}
		if(criteria.getMapSearchInColumn().isEmpty()){
			throw new IllegalArgumentException("É preciso configurar o Map<String, Expression> da classe criteria: " + 
					criteria.getClass().getName());
		}
		for(Map.Entry<String, Object> entry : filters.entrySet()){
			String atributo = entry.getKey();
			String valor = (String)entry.getValue();
			for(Field field : criteria.getClass().getDeclaredFields()){
				if(field.isAnnotationPresent(FilterFieldOption.class)){
					FilterFieldOption filter = field.getAnnotation(FilterFieldOption.class);
					if(filter.property().equals(atributo)){
						try {
							FilterField filterField = criteria.getModel().getFieldByName(atributo);
							filterField.setValue(valor);
							
							if(!criteria.getMapSearchInColumn().containsKey(atributo) ||
								criteria.getMapSearchInColumn().get(atributo) == null){
								throw new IllegalArgumentException("Nenhuma configuração foi encontrada para : \"" + atributo + 
										"\" " + criteria.getClass().getName() + ".configSearchInColumn(Map)");
							}
							
							Expression expression = criteria.getMapSearchInColumn().get(atributo);
							
							filterField.setExpression(expression);
							criteria.getModel().getSelectedFields().add(filterField);
						} catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	@Override
    public List<Object[]> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
    	configFilterByColumn(filters);
    	List<Object[]> data = new ArrayList<Object[]>();
    	
    	Long dataSize = logService.count(clazz, criteria, 
				 revisaoInicial, revisaoFinal, 
				 dataInicial, dataFinal, 
				 operacoesSelecionadas, 
				 idRegistro,
				 nomeUsuario,
				 cpf);    	

    	datasource = logService.search(clazz, criteria, 
				revisaoInicial, revisaoFinal, 
				dataInicial, dataFinal, 
				operacoesSelecionadas, 
				idRegistro,
				nomeUsuario,
				cpf,
				first, pageSize);    	

        //rowCount        
        this.setRowCount(dataSize.intValue());
        
        //filter
        for(Object[] arr : datasource) {
            boolean match = true;
            Map<String, String> map = (Map<String, String>)arr[0];
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = map.get(filterProperty);
 
                        if(filterValue == null || fieldValue.toLowerCase().startsWith(filterValue.toString().toLowerCase())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(arr);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize.intValue() % pageSize));
            }
        }
        else {
            return data;
        }
    }

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public DynamicSearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(DynamicSearchCriteria criteria) {
		this.criteria = criteria;
	}

	public Integer getRevisaoInicial() {
		return revisaoInicial;
	}

	public void setRevisaoInicial(Integer revisaoInicial) {
		this.revisaoInicial = revisaoInicial;
	}

	public Integer getRevisaoFinal() {
		return revisaoFinal;
	}

	public void setRevisaoFinal(Integer revisaoFinal) {
		this.revisaoFinal = revisaoFinal;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<RevisionType> getOperacoesSelecionadas() {
		return operacoesSelecionadas;
	}

	public void setOperacoesSelecionadas(List<RevisionType> operacoesSelecionadas) {
		this.operacoesSelecionadas = operacoesSelecionadas;
	}

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
