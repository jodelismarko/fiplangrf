package br.gov.mt.mti.fiplangrf.dominio;


import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;

import br.gov.mt.cepromat.ceprofw.common.model.DominioInterface;

public enum DominioSituacaoRegistro implements DominioInterface<DominioSituacaoRegistro> {

	// @formatter:off
	ATIVO("ATIVO","Ativo"),
	INATIVO("INATIVO","Inativo");
	// @formatter:on

    public static final String NOME = "DominioSituacaoRegistro";

    public static final String METHOD = "getCharCod";

    private int cod;
    private String desc;
    private String longDesc;
    private String charCod;


    private DominioSituacaoRegistro(int cod, String desc) {
        this.cod = cod;
        this.desc = desc;
        this.longDesc = desc;
    }
    

    private DominioSituacaoRegistro(String charCod, String desc) {
    	this.charCod = charCod;
        this.desc = desc;
        this.longDesc = desc;
    }

    /**
     * Retorna a instância do domínio a partir de seu código
     * 
     * @param codigo
     * @return
     */
    public static DominioSituacaoRegistro valueOf(int codigo) {
        for (DominioSituacaoRegistro valor : values()) {
            if (valor.getCod() == codigo) {
                return valor;
            }
        }
        return null;
    }
    
    public static DominioSituacaoRegistro valueOf(Character charCod) {
        for (DominioSituacaoRegistro valor : values()) {
            if (valor.getCharCod().equals(charCod)) {
                return valor;
            }
        }
        return null;
    }
       

    
    /**
     * Retorna a instância do domínio a partir de seu código
     * 
     * @param codigo
     * @return
     */
    public static DominioSituacaoRegistro valueOf(boolean active) {
    	if (active){
    		return DominioSituacaoRegistro.ATIVO;
    	}else{
    		return DominioSituacaoRegistro.INATIVO;
    	}

    }
    
    public boolean getBooleanValue() {
    	if (this==DominioSituacaoRegistro.ATIVO){
    		return true;
    	}else{
    		return false;
    	}

    }
    /**
     * Verifica se o código informado existe no domínio
     * 
     * @param codigo
     * @return
     */
    public static boolean isValid(Integer codigo) {
        return valueOf(codigo) != null;
    }
    
    

    public String getCharCod() {
		return charCod;
	}


	public void setCharCod(String charCod) {
		this.charCod = charCod;
	}


	/**
     * Número de elementos do dominio
     * 
     * @return
     */
    public static Integer getSize() {
        return values().length;
    }

    public String getName() {
        return this.name();
    }

    public int getCod() {
        return this.cod;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getLongDesc() {
        return (StringUtils.isBlank(this.longDesc)) ? this.desc : this.longDesc;
    }

    public String toString() {
        return this.getDesc();
    }

    public static List<SelectItem> getItems() {
        List<SelectItem> itens = new ArrayList<SelectItem>();
        for (DominioSituacaoRegistro dominio : values()) {
            SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
            itens.add(item);
        }
        return itens;
    }

}
