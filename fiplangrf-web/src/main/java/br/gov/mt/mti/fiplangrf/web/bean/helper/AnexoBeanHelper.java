package br.gov.mt.mti.fiplangrf.web.bean.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.gov.mt.mti.fiplangrf.common.util.EncodingUtil;
import br.gov.mt.mti.fiplangrf.common.util.Env;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.model.tabelas.Anexo;
import br.gov.mt.mti.fiplangrf.model.tabelas.ArquivoAnexo;
import br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService;
import br.gov.mt.mti.fiplangrf.service.tabelas.AnexoService;
import lombok.Getter;

@Stateless
public class AnexoBeanHelper {

	@Inject
	private AnexoService anexoService;

	@Inject
	private UsuarioService usuarioService;

	@Getter
	private StreamedContent file;

	/* ANEXOS */
	public Anexo uploadAnexo(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		byte[] contents = uploadedFile.getContents(); // Or getInputStream()
		Anexo anex = new Anexo();
		ArquivoAnexo arq = new ArquivoAnexo();
		arq.setArquivo(contents);
		anex.setArquivoAnexo(arq);
		anex.setDataCriacao(new Date());

		try {
			String descricao = EncodingUtil.decodeString(uploadedFile.getFileName(), "ISO-8859-1", "UTF-8");
			String extensao = FilenameUtils.getExtension(descricao);
			if (descricao.length() > 200) {
				descricao = descricao.substring(0, 195) + "." + extensao;
			}
			anex.setDescricao(descricao);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

		anex.setContentType(uploadedFile.getContentType());
		anex.setTamanho(uploadedFile.getSize());
		Usuario usuarioLogadoEntity = usuarioService.findById(Env.getUsuarioLogado().getCodigo());
		anex.setUsuario(usuarioLogadoEntity);
		return anex;
	}

	public void downloadAnexo(Anexo anexo) throws IOException, NoSuchAlgorithmException {
		byte[] arquivoAnexado = null;

		if (anexo.getId() == null) {
			arquivoAnexado = anexo.getArquivoAnexo().getArquivo();
		} else {
			arquivoAnexado = anexoService.hibernateInitializeArquivoAnexo(anexo);
		}

		InputStream inputStream = new ByteArrayInputStream(arquivoAnexado);
		file = new DefaultStreamedContent(inputStream, anexo.getContentType(), anexo.getDescricao());
	}

}