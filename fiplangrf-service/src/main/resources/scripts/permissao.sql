DROP SEQUENCE DHWSQ028_PERMISSAO;
CREATE SEQUENCE DHWSQ028_PERMISSAO NOCACHE;
--USUARIO
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.usuario','Acao Incluir Usuario','incluir.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.usuario','Acao Alterar Usuario','alterar.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.usuario','Acao Excluir Usuario','excluir.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.usuario','Acao Pesquisar Usuario','pesquisar.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'usuario.vincular.perfil','Acao Vincular Perfil','usuario.vincular.perfil','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.save','Incluir Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.delete','Excluir Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.find','Pesquisar Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.find','ACAO');
--FIM_USUARIO

--PERFIL
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.perfil','Acao Incluir Perfil','incluir.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.perfil','Acao Alterar Perfil','alterar.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.perfil','Acao Excluir Perfil','excluir.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.perfil','Acao Pesquisar Perfil','pesquisar.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'perfil.vincular.funcionalidade','Acao Vincular Perfil','perfil.vincular.funcionalidade','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.PerfilService.save','Incluir Perfil','br.gov.mt.mti.fiplangma.service.security.user.PerfilService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.PerfilService.delete','Excluir Perfil','br.gov.mt.mti.fiplangma.service.security.user.PerfilService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.PerfilService.find','Pesquisar Perfil','br.gov.mt.mti.fiplangma.service.security.user.PerfilService.find','ACAO');
--FIM_PERFIL

--FUNCIONALIDADE
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.funcionalidade','Acao Incluir Funcionalidade','incluir.funcionalidade','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.funcionalidade','Acao Alterar Funcionalidade','alterar.funcionalidade','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.funcionalidade','Acao Excluir Funcionalidade','excluir.funcionalidade','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.funcionalidade','Acao Pesquisar Funcionalidade','pesquisar.funcionalidade','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'funcionalidade.vincular.permissao','Acao Vincular Funcionalidade','funcionalidade.vincular.permissao','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.FuncionalidadeService.save','Incluir Funcionalidade','br.gov.mt.mti.fiplangma.service.security.user.FuncionalidadeService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.FuncionalidadeService.delete','Excluir Funcionalidade','br.gov.mt.mti.fiplangma.service.security.user.FuncionalidadeService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.FuncionalidadeService.find','Pesquisar Funcionalidade','br.gov.mt.mti.fiplangma.service.security.user.FuncionalidadeService.find','ACAO');
--FIM_FUNCIONALIDADE

--CNAE
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.cnae','Acao Incluir CNAE','incluir.cnae','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.cnae','Acao Alterar CNAE','alterar.cnae','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.cnae','Acao Excluir CNAE','excluir.cnae','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.cnae','Acao Pesquisar CNAE','pesquisar.cnae','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.CNAEService.save','Incluir CNAE','br.gov.mt.mti.fiplangma.service.tabelas.CNAEService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.CNAEService.delete','Excluir CNAE','br.gov.mt.mti.fiplangma.service.tabelas.CNAEService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.CNAEService.find','Pesquisar CNAE','br.gov.mt.mti.fiplangma.service.tabelas.CNAEService.find','ACAO');
--FIM_CNAE

--NATUREZA_JURIDICA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.naturezaJuridica','Acao Incluir Natureza Juridica','incluir.naturezaJuridica','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.naturezaJuridica','Acao Alterar Natureza Juridica','alterar.naturezaJuridica','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.naturezaJuridica','Acao Excluir Natureza Juridica','excluir.naturezaJuridica','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.naturezaJuridica','Acao Pesquisar Natureza Juridica','pesquisar.naturezaJuridica','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.NaturezaJuridicaService.save','Incluir Natureza Juridica','br.gov.mt.mti.fiplangma.service.tabelas.NaturezaJuridicaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.NaturezaJuridicaService.delete','Excluir Natureza Juridica','br.gov.mt.mti.fiplangma.service.tabelas.NaturezaJuridicaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.NaturezaJuridicaService.find','Pesquisar Natureza Juridica','br.gov.mt.mti.fiplangma.service.tabelas.NaturezaJuridicaService.find','ACAO');
--FIM_NATUREZA_JURIDICA

--TIPO_OCORRENCIA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.tipoOcorrencia','Acao Incluir Tipo Ocorrencia','incluir.tipoOcorrencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.tipoOcorrencia','Acao Alterar Tipo Ocorrencia','alterar.tipoOcorrencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.tipoOcorrencia','Acao Excluir Tipo Ocorrencia','excluir.tipoOcorrencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.tipoOcorrencia','Acao Pesquisar Tipo Ocorrencia','pesquisar.tipoOcorrencia','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.TipoOcorrenciaService.save','Incluir Tipo Ocorrencia','br.gov.mt.mti.fiplangma.service.tabelas.TipoOcorrenciaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.TipoOcorrenciaService.delete','Excluir Tipo Ocorrencia','br.gov.mt.mti.fiplangma.service.tabelas.TipoOcorrenciaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.TipoOcorrenciaService.find','Pesquisar Tipo Ocorrencia','br.gov.mt.mti.fiplangma.service.tabelas.TipoOcorrenciaService.find','ACAO');
--FIM_TIPO_OCORRENCIA

--TIPO_ADMINISTRACAO
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.tipoAdministracao','Acao Incluir Tipo Administração','incluir.tipoAdministracao','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.tipoAdministracao','Acao Alterar Tipo Administração','alterar.tipoAdministracao','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.tipoAdministracao','Acao Excluir Tipo Administração','excluir.tipoAdministracao','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.tipoAdministracao','Acao Pesquisar Tipo Administração','pesquisar.tipoAdministracao','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.TipoAdministracaoService.save','Incluir Tipo Administração','br.gov.mt.mti.fiplangma.service.tabelas.TipoAdministracaoService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.TipoAdministracaoService.delete','Excluir Tipo Administração','br.gov.mt.mti.fiplangma.service.tabelas.TipoAdministracaoService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.TipoAdministracaoService.find','Pesquisar Tipo Administração','br.gov.mt.mti.fiplangma.service.tabelas.TipoAdministracaoService.find','ACAO');
--FIM_TIPO_ADMINISTRACAO

--GRUPO_PENDENCIA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.grupoPendencia','Acao Incluir Grupo','incluir.grupoPendencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.grupoPendencia','Acao Alterar Grupo','alterar.grupoPendencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.grupoPendencia','Acao Excluir Grupo','excluir.grupoPendencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.grupoPendencia','Acao Pesquisar Grupo','pesquisar.grupoPendencia','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.GrupoPendenciaService.save','Incluir Grupo','br.gov.mt.mti.fiplangma.service.tabelas.GrupoPendenciaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.GrupoPendenciaService.delete','Excluir Grupo','br.gov.mt.mti.fiplangma.service.tabelas.GrupoPendenciaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.GrupoPendenciaService.find','Pesquisar Grupo','br.gov.mt.mti.fiplangma.service.tabelas.GrupoPendenciaService.find','ACAO');
--FIM_GRUPO_PENDENCIA

--SUBGRUPO_PENDENCIA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.subGrupoPendencia','Acao Incluir Subgrupo','incluir.subGrupoPendencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.subGrupoPendencia','Acao Alterar Subgrupo','alterar.subGrupoPendencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.subGrupoPendencia','Acao Excluir Subgrupo','excluir.subGrupoPendencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.subGrupoPendencia','Acao Pesquisar Subgrupo','pesquisar.subGrupoPendencia','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.SubGrupoPendenciaService.save','Incluir Subgrupo','br.gov.mt.mti.fiplangma.service.tabelas.SubGrupoPendenciaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.SubGrupoPendenciaService.delete','Excluir Subgrupo','br.gov.mt.mti.fiplangma.service.tabelas.SubGrupoPendenciaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.SubGrupoPendenciaService.find','Pesquisar Subgrupo','br.gov.mt.mti.fiplangma.service.tabelas.SubGrupoPendenciaService.find','ACAO');
--FIM_SUBGRUPO_PENDENCIA

--TIPO_PENDENCIA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.tipoPendencia','Acao Incluir Tipo Pendencia','incluir.tipoPendencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.tipoPendencia','Acao Alterar Tipo Pendencia','alterar.tipoPendencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.tipoPendencia','Acao Excluir Tipo Pendencia','excluir.tipoPendencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.tipoPendencia','Acao Pesquisar Tipo Pendencia','pesquisar.tipoPendencia','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.TipoPendenciaService.save','Incluir Tipo Pendencia','br.gov.mt.mti.fiplangma.service.tabelas.TipoPendenciaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.TipoPendenciaService.delete','Excluir Tipo Pendencia','br.gov.mt.mti.fiplangma.service.tabelas.TipoPendenciaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.TipoPendenciaService.find','Pesquisar Tipo Pendencia','br.gov.mt.mti.fiplangma.service.tabelas.TipoPendenciaService.find','ACAO');
--FIM_TIPO_PENDENCIA

--UNIDADE_ADMINISTRATIVA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.unidadeAdministrativa','Acao Incluir Unidade Administrativa','incluir.unidadeAdministrativa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.unidadeAdministrativa','Acao Alterar Unidade Administrativa','alterar.unidadeAdministrativa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.unidadeAdministrativa','Acao Excluir Unidade Administrativa','excluir.unidadeAdministrativa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.unidadeAdministrativa','Acao Pesquisar Unidade Administrativa','pesquisar.unidadeAdministrativa','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.UnidadeAdministrativaService.save','Incluir Unidade Administrativa','br.gov.mt.mti.fiplangma.service.tabelas.UnidadeAdministrativaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.UnidadeAdministrativaService.delete','Excluir Unidade Administrativa','br.gov.mt.mti.fiplangma.service.tabelas.UnidadeAdministrativaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.UnidadeAdministrativaService.find','Pesquisar Unidade Administrativa','br.gov.mt.mti.fiplangma.service.tabelas.UnidadeAdministrativaService.find','ACAO');
--FIM_UNIDADE_ADMINISTRATIVA
    
--TIPO_OCORRENCIA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.ocorrencia','Acao Incluir Ocorrencia','incluir.ocorrencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.ocorrencia','Acao Alterar Ocorrencia','alterar.ocorrencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.ocorrencia','Acao Excluir Ocorrencia','excluir.ocorrencia','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.ocorrencia','Acao Pesquisar Ocorrencia','pesquisar.ocorrencia','GATILHO');

INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.OcorrenciaService.save','Incluir Ocorrencia','br.gov.mt.mti.fiplangma.service.tabelas.OcorrenciaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.OcorrenciaService.delete','Excluir Ocorrencia','br.gov.mt.mti.fiplangma.service.tabelas.OcorrenciaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) 
    VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.tabelas.OcorrenciaService.find','Pesquisar Ocorrencia','br.gov.mt.mti.fiplangma.service.tabelas.OcorrenciaService.find','ACAO');
--FIM_TIPO_OCORRENCIA