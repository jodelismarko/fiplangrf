INSERT INTO dhwtb018_usuario ( iden_usuario, data_atualizacao, data_criacao, versao, data_ultimo_acesso, numr_cpf, flag_situacao) VALUES (1, NULL, SYSDATE(), 0, NULL, '36329059918', 'ATIVO');
INSERT INTO dhwtb018_usuario ( iden_usuario, data_atualizacao, data_criacao, versao, data_ultimo_acesso, numr_cpf, flag_situacao) VALUES (2, NULL, SYSDATE(), 0, NULL, '04267231125', 'ATIVO');
INSERT INTO dhwtb018_usuario ( iden_usuario, data_atualizacao, data_criacao, versao, data_ultimo_acesso, numr_cpf, flag_situacao) VALUES (2, NULL, SYSDATE(), 0, NULL, '00539211192', 'ATIVO');

   
INSERT INTO DHWTB017_PERFIL_ACESSO (IDEN_PERFIL_ACESSO,DATA_ATUALIZACAO,DATA_CRIACAO,VERSAO, DESC_PERFIL_ACESSO,FLAG_SITUACAO) VALUES (1,null,sysdate(),0,'Perfil de Acesso Master','ATIVO');
    
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '36329059918', null, 'admin@master.com.br', 'Usuario Master Dev', '65981330233', '6536537852');
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '04267231125', null, 'jilds@outlook.com', 'Jonathan Inacio Lima da Silva', '65981330233', '6536537852');
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '47870477010', null, 'usuario1@email.com', 'Usuario 1', '65981330233', '6536537852');
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '28868756005', null, 'usuario2@email.com', 'Usuario 2', '65981330233', '6536537852');
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '98044538003', null, 'usuario3@email.com', 'Usuario 3', '65981330233', '6536537852');
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '29025094058', null, 'usuario4@email.com', 'Usuario 4', '65981330233', '6536537852');
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '00573046026', null, 'usuario5@email.com', 'Usuario 5', '65981330233', '6536537852');
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '08773720046', null, 'usuario6@email.com', 'Usuario 6', '65981330233', '6536537852');
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '36932220024', null, 'usuario7@email.com', 'Usuario 7', '65981330233', '6536537852');
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '00539211192', null, 'jodelismarko@gmail.com', 'Jodelismarko Mamoré de Melo', '00539211192', '00539211192');

INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (1,null,sysdate(),0,'Funcionalidade Incluir Usuario','ROLE_INCLUIR_USUARIO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (2,null,sysdate(),0,'Funcionalidade Consultar Usuario','ROLE_CONSULTAR_USUARIO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (3,null,sysdate(),0,'Funcionalidade Alterar Usuario','ROLE_ALTERAR_USUARIO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (4,null,sysdate(),0,'Funcionalidade Excluir Usuario','ROLE_EXCLUIR_USUARIO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (5,null,sysdate(),0,'Funcionalidade Alterar Senha Usuario','ROLE_ALTERAR_USUARIO_SENHA','ATIVO');

INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (6,null,sysdate(),0,'Funcionalidade Incluir Perfil','ROLE_INCLUIR_PERFIL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (7,null,sysdate(),0,'Funcionalidade Consultar Perfil','ROLE_CONSULTAR_PERFIL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (8,null,sysdate(),0,'Funcionalidade Alterar Perfil','ROLE_ALTERAR_PERFIL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (9,null,sysdate(),0,'Funcionalidade Excluir Perfil','ROLE_EXCLUIR_PERFIL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (10,null,sysdate(),0,'Funcionalidade Vincular Perfil','ROLE_VINCULAR_PERFIL_FUNCIONALIDADE','ATIVO');

INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (11,null,sysdate(),0,'Funcionalidade Incluir Funcionalidade','ROLE_INCLUIR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (12,null,sysdate(),0,'Funcionalidade Consultar Funcionalidade','ROLE_CONSULTAR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (13,null,sysdate(),0,'Funcionalidade Alterar Funcionalidade','ROLE_ALTERAR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (14,null,sysdate(),0,'Funcionalidade Excluir Funcionalidade','ROLE_EXCLUIR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (15,null,sysdate(),0,'Funcionalidade Vincular Funcionalidade','ROLE_VINCULAR_FUNCIONALIDADE','ATIVO');

INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,1);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,2);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,3);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,4);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,5);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,6);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,7);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,8);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,9);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,10);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,11);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,12);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,13);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,14);
INSERT INTO dhwtb024_perfil_func (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,15);


INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (1,null,sysdate(),0,'incluir.usuario','Acao Incluir Usuario','incluir.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (2,null,sysdate(),0,'alterar.usuario','Acao Alterar Usuario','alterar.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (3,null,sysdate(),0,'excluir.usuario','Acao Excluir Usuario','excluir.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (4,null,sysdate(),0,'pesquisar.usuario','Acao Pesquisar Usuario','pesquisar.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (5,null,sysdate(),0,'resetar.senha.usuario','Acao Resetar Senha','resetar.senha.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (6,null,sysdate(),0,'usuario.vincular.perfil','Acao Vincular Perfil','usuario.vincular.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (7,null,sysdate(),0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.save','Incluir Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (8,null,sysdate(),0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.merge','Alterar Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.merge','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (9,null,sysdate(),0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.delete','Excluir Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (10,null,sysdate(),0,'br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.find','Pesquispar Usuario','br.gov.mt.mti.fiplangma.service.security.user.UsuarioService.find','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (11,null,sysdate(),0,'incluir.perfil','Acao Incluir Perfil','incluir.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (12,null,sysdate(),0,'alterar.perfil','Acao Alterar Perfil','alterar.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (13,null,sysdate(),0,'excluir.perfil','Acao Excluir Perfil','excluir.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (14,null,sysdate(),0,'pesquisar.perfil','Acao Pesquisar Perfil','pesquisar.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (15,null,sysdate(),0,'vincular.funcionalidade','Acao Vincular Funcionalidade','vincular.funcionalidade','GATILHO');


