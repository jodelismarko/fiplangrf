INSERT INTO DHWTB018_USUARIO (IDEN_USUARIO,DATA_ATUALIZACAO,DATA_CRIACAO,VERSAO,DATA_ULTIMO_ACESSO,NUMR_CPF,FLAG_SITUACAO) VALUES (1,null, sysdate,0,null,'36329059918','ATIVO');
INSERT INTO DHWTB017_PERFIL_ACESSO (IDEN_PERFIL_ACESSO,DATA_ATUALIZACAO,DATA_CRIACAO,VERSAO,DESC_PERFIL_ACESSO,FLAG_SITUACAO) values (1,null,sysdate,0,'Master','ATIVO'); 

INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (1,null,sysdate,0,'Funcionalidade Incluir Usuario','ROLE_INCLUIR_USUARIO','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (2,null,sysdate,0,'Funcionalidade Consultar Usuario','ROLE_CONSULTAR_USUARIO','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (3,null,sysdate,0,'Funcionalidade Alterar Usuario','ROLE_ALTERAR_USUARIO','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (4,null,sysdate,0,'Funcionalidade Excluir Usuario','ROLE_EXCLUIR_USUARIO','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (5,null,sysdate,0,'Funcionalidade Alterar Senha Usuario','ROLE_ALTERAR_USUARIO_SENHA','ATIVO');

INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (6,null,sysdate,0,'Funcionalidade Incluir Perfil','ROLE_INCLUIR_PERFIL','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (7,null,sysdate,0,'Funcionalidade Consultar Perfil','ROLE_CONSULTAR_PERFIL','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (8,null,sysdate,0,'Funcionalidade Alterar Perfil','ROLE_ALTERAR_PERFIL','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (9,null,sysdate,0,'Funcionalidade Excluir Perfil','ROLE_EXCLUIR_PERFIL','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (10,null,sysdate,0,'Funcionalidade Vincular Perfil','ROLE_VINCULAR_PERFIL_FUNCIONALIDADE','ATIVO');

INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (11,null,sysdate,0,'Funcionalidade Incluir Funcionalidade','ROLE_INCLUIR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (12,null,sysdate,0,'Funcionalidade Consultar Funcionalidade','ROLE_CONSULTAR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (13,null,sysdate,0,'Funcionalidade Alterar Funcionalidade','ROLE_ALTERAR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (14,null,sysdate,0,'Funcionalidade Excluir Funcionalidade','ROLE_EXCLUIR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (15,null,sysdate,0,'Funcionalidade Vincular Funcionalidade','ROLE_VINCULAR_FUNCIONALIDADE','ATIVO');

INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (16,null,sysdate,0,'Relatorio Exemplo','ROLE_RELATORIO_EXEMPLO','ATIVO');
INSERT INTO DHWTB024_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (17,null,sysdate,0,'Relatorio Exemplo','ROLE_AUDIT_LOG','ATIVO');

INSERT INTO DHWTB018_DHWTB017_USR_PERFIL ( IDEN_USUARIO,  IDEN_PERFIL_ACESSO) VALUES (1, 1);

INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,1);
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,2);
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,3);
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,4);
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,5);
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,6)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,7)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,8)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,9)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,10)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,11)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,12)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,13)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,14)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,15)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,16)
INSERT INTO DHWTB017_DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,17)

INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (1,null,sysdate,0,'incluir.usuario','Acao Incluir Usuario','incluir.usuario','GATILHO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (2,null,sysdate,0,'alterar.usuario','Acao Alterar Usuario','alterar.usuario','GATILHO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (3,null,sysdate,0,'excluir.usuario','Acao Excluir Usuario','excluir.usuario','GATILHO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (4,null,sysdate,0,'pesquisar.usuario','Acao Pesquisar Usuario','pesquisar.usuario','GATILHO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (5,null,sysdate,0,'resetar.senha.usuario','Acao Resetar Senha','resetar.senha.usuario','GATILHO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (6,null,sysdate,0,'usuario.vincular.perfil','Acao Vincular Perfil','usuario.vincular.perfil','GATILHO');

INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (7,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.save','Incluir Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.save','ACAO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (8,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.merge','Alterar Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.merge','ACAO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (9,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.delete','Excluir Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.delete','ACAO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (10,null,sysdate,0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.find','Pesquispar Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.find','ACAO');


INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (11,null,sysdate,0,'incluir.perfil','Acao Incluir Perfil','incluir.perfil','GATILHO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (12,null,sysdate,0,'alterar.perfil','Acao Alterar Perfil','alterar.perfil','GATILHO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (13,null,sysdate,0,'excluir.perfil','Acao Excluir Perfil','excluir.perfil','GATILHO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (14,null,sysdate,0,'pesquisar.perfil','Acao Pesquisar Perfil','pesquisar.perfil','GATILHO');
INSERT INTO DHWTB025_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (15,null,sysdate,0,'vincular.funcionalidade','Acao Vincular Funcionalidade','vincular.funcionalidade','GATILHO');

--Incluir usuário
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (1,1)
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (1,7)
--Alterar usuário
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (3,2)
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (3,7)
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (3,8)
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (3,10)
--Excluir Usuario
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (4,3)
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (4,7)
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (4,8)
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (4,10)
--Pesquisar usuário
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (2,4)
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (2,7)
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (2,8)
INSERT INTO DHWTB024_DHWTB025_FUN_PERM (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (2,10)

