package br.gov.mt.mti.fiplangrf.listener;

import org.hibernate.envers.RevisionListener;

import br.gov.mt.mti.fiplangrf.common.util.Env;
import br.gov.mt.mti.fiplangrf.model.auditoria.CustomRevisionEntity;

public class CustomEnversListener implements RevisionListener {
	
	@Override
	public void newRevision(Object revisionEntity) {
		CustomRevisionEntity entity = (CustomRevisionEntity) revisionEntity;
		entity.setCodigoUsuarioCeproSecurity(Env.getUsuarioLogado().getCodigo());
		entity.setEnderecoIP(Env.getUsuarioLogado().getIp());
		entity.setNomeUsuario(Env.getUsuarioLogado().getNome());
		entity.setNumeroCPFUsuario(Env.getUsuarioLogado().getCpf());
	}
}
