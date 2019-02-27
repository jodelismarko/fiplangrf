package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.Basic;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Audited
@Table(name = "DHWTB019_ANEXO")
@EqualsAndHashCode(callSuper = false, of = {"id", "descricao", "tamanho", "contentType"})
@GeneratorEntityOptions(defaultLabel = "Anexo", descriptionProperty = "descricao")
public class Anexo extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = -2529342557965337086L;

	@Id
	@Column(name = "IDEN_ANEXO", length = 8)
	@SequenceGenerator(name = "ANEXO_SEQ", sequenceName = "DHWSQ019_ANEXO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANEXO_SEQ")
	private Long id;

	@GeneratorFieldOptions(defaultLabel = "Anexo", filterable = true)
	@Column(name = "DESC_ARQUIVO_ANEXO", nullable = false, length = 2000)
	private String descricao;

	@GeneratorFieldOptions(defaultLabel = "Tamanho do arquivo", filterable = true)
	@Column(name = "QTDE_BYTES", nullable = false, length = 13)
	private Long tamanho;

	@GeneratorFieldOptions(defaultLabel = "Descrição do tipo do arquivo", filterable = true)
	@Column(name = "DESC_CONTENT_TYPE", nullable = false, length = 100)
	private String contentType;

	@Basic(fetch = FetchType.LAZY)
	@NotAudited
	@OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "IDEN_ARQUIVO_ANEXO", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "DHWFK019_DHWTB020_ARQUIVO_ANEX"))
	private ArquivoAnexo arquivoAnexo;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "IDEN_USUARIO", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "DHWFK019_DHWTB018_USUARIO"))
	private Usuario usuario;

	public Anexo() {

	}

	public void finalize() throws Throwable {

	}
}
