package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Audited
@Table(name = "DHUTB010_ANEXO")
@ToString(callSuper = false, exclude = {"usuario","arquivoAnexo"} )
@EqualsAndHashCode(callSuper = false, exclude = {"usuario","arquivoAnexo"} )
public class Anexo extends BaseEntity<Long> {

	private static final long serialVersionUID = 4299118091621558772L;

	@Id
	@Column(name = "IDEN_ANEXO", length = 8)
	@SequenceGenerator(name = "ANEXO_SEQ", sequenceName = "DHUSQ010_ANEXO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ANEXO_SEQ")
	private Long id;

	@GeneratorFieldOptions(defaultLabel = "Nome do Documento")
	@Column(name = "NOME_ANEXO", length = 50, nullable = false)
	private String nomeAnexo;

	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	@Column(name = "DESC_ARQ_ANEXADO", length = 500, nullable = false)
	private String descricao;

	@GeneratorFieldOptions(defaultLabel = "Conectividade")
	@Column(name = "DESC_CONECTIVIDADE", length = 100)
	private String conectividade;
	
	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "IDEN_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "DHUFK010_DHUTB007_USUARIO"))
	private Usuario usuario;
	
	@NotAudited
    @OneToOne( optional=false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="IDEN_ANEXO_ARQUIVO", nullable=false, 
		foreignKey=@ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT ,name="DHUFK010_DHUTB011_ANEXO_ARQUIV"))
    private ArquivoAnexo arquivoAnexo;
    
    @Transient
    private Integer tempId;
    
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}