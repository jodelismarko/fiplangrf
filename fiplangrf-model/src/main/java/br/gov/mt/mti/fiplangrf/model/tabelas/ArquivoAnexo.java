package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "DHWTB020_ARQUIVO_ANEXO")
@EqualsAndHashCode(callSuper = false, of = {"id", "arquivo"})
@GeneratorEntityOptions(defaultLabel = "ANEXO")
public class ArquivoAnexo extends BaseVersionedEntity<Long>{
	
	private static final long serialVersionUID = 2971863409596074127L;

	@Id
	@Column(name = "IDEN_ARQUIVO_ANEXO")
	@SequenceGenerator(name = "ARQUIVO_ANEXO_SEQ", sequenceName = "DHWSQ020_ARQUIVO_ANEXO", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARQUIVO_ANEXO_SEQ")
	private Long id;
	
	@OneToOne(mappedBy = "arquivoAnexo")
	private Anexo anexo;
	
	@Column(name="BLOB_ARQUIVO_ANEXO")
	@GeneratorFieldOptions(defaultLabel = "Número CNAE", filterable = true)
	private byte[] arquivo;

}
