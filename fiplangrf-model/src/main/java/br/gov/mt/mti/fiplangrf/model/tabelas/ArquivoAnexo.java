package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name="DHUTB011_ANEXO_ARQUIVO")
@ToString(callSuper = false, of = "id")
@EqualsAndHashCode(callSuper = false,  of = "id")
public class ArquivoAnexo   extends BaseEntity<Long> {
	
	private static final long serialVersionUID = 1091553803745187076L;

	@Id
	@Column(name="IDEN_ANEXO_ARQUIVO", length=8)
    @SequenceGenerator(name = "ARQUIVO_ANEXO_SEQ", sequenceName = "DHUSQ011_ANEXO_ARQUIVO", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ARQUIVO_ANEXO_SEQ")
	private Long id;

	@Lob	
	@GeneratorFieldOptions(defaultLabel = "Conteúdo")	
	@Column(name="ARQV_ANEXADO", columnDefinition="BLOB NOT NULL")
	private byte[] arquivo;

}


