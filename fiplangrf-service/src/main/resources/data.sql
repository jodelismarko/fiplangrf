-- USUÁRO FIPLAN
INSERT INTO ACWVW0464 (nr_cpf, dt_expiracao, ds_email,flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '36329059918', null, 'admin@master.com.br','N', 'Usuario Master Dev', '65981330233', '6536537852');
INSERT INTO ACWVW0464 (nr_cpf, dt_expiracao, ds_email,flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) VALUES ( '539211192', null, 'jodelismarko@gmail.com','N', 'Jodelismarko Mamoré de Melo', '65992898361', '65992898361');

-- USUÁRIO SISTEMA
INSERT INTO DHWTB018_USUARIO ( iden_usuario, data_atualizacao, data_criacao, versao, data_ultimo_acesso, numr_cpf, flag_situacao) VALUES (DHWSQ018_USUARIO.nextval, NULL, SYSDATE, 0, NULL, '36329059918', 'ATIVO');
INSERT INTO DHWTB018_USUARIO ( iden_usuario, data_atualizacao, data_criacao, versao, data_ultimo_acesso, numr_cpf, flag_situacao) VALUES (DHWSQ018_USUARIO.nextval, NULL, SYSDATE, 0, NULL, '539211192', 'ATIVO');

-- PERFIL
INSERT INTO DHWTB017_PERFIL_ACESSO (IDEN_PERFIL_ACESSO,DATA_ATUALIZACAO,DATA_CRIACAO,VERSAO, DESC_PERFIL_ACESSO,FLAG_SITUACAO) VALUES (DHWSQ017_PERFIL_ACESSO.nextval,null,SYSDATE,0,'Perfil de Acesso Master','ATIVO')

-- VINCULAR PERFIL AOS USUÁRIOS
INSERT INTO DHWTB025_USUARIO_PERFIL(IDEN_USUARIO, IDEN_PERFIL_ACESSO) VALUES (1,1);
INSERT INTO DHWTB025_USUARIO_PERFIL(IDEN_USUARIO, IDEN_PERFIL_ACESSO) VALUES (2,1);


-- Funcionalidade Usuário
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Usuario','ROLE_INCLUIR_USUARIO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Usuario','ROLE_CONSULTAR_USUARIO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Usuario','ROLE_ALTERAR_USUARIO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Usuario','ROLE_EXCLUIR_USUARIO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Senha Usuario','ROLE_ALTERAR_USUARIO_SENHA','ATIVO');

--Funcionalidade Perfil
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Perfil','ROLE_INCLUIR_PERFIL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Perfil','ROLE_CONSULTAR_PERFIL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Perfil','ROLE_ALTERAR_PERFIL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Perfil','ROLE_EXCLUIR_PERFIL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Vincular Perfil','ROLE_VINCULAR_PERFIL_FUNCIONALIDADE','ATIVO');

--Funcionalidade Funcionalidade
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Funcionalidade','ROLE_INCLUIR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Funcionalidade','ROLE_CONSULTAR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Funcionalidade','ROLE_ALTERAR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Funcionalidade','ROLE_EXCLUIR_FUNCIONALIDADE','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Vincular Funcionalidade','ROLE_VINCULAR_FUNCIONALIDADE','ATIVO');

--Funcionalidade Despesa
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Despesa','ROLE_INCLUIR_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Despesa','ROLE_CONSULTAR_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Despesa','ROLE_ALTERAR_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Despesa','ROLE_EXCLUIR_DESPESA','ATIVO');

--Funcionalidade Detalhamento de Despesa
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Detalhamento de Despesa','ROLE_INCLUIR_DETALHAMENTO_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Detalhamento de Despesa','ROLE_CONSULTAR_DETALHAMENTO_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Detalhamento de Despesa','ROLE_ALTERAR_DETALHAMENTO_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Detalhamento de Despesa','ROLE_EXCLUIR_DETALHAMENTO_DESPESA','ATIVO');

--Funcionalidade Detalhamento da Provisão de Despesa
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Detalhamento da Provisão de Despesa','ROLE_INCLUIR_DETALHAMENTO_PROVISAO_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Detalhamento da Provisão de Despesa','ROLE_CONSULTAR_DETALHAMENTO_PROVISAO_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Detalhamento da Provisão de Despesa','ROLE_ALTERAR_DETALHAMENTO_PROVISAO_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Detalhamento da Provisão de Despesa','ROLE_EXCLUIR_DETALHAMENTO_PROVISAO_DESPESA','ATIVO');

--Funcionalidade Fonte de recurso
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Fonte de recurso','ROLE_INCLUIR_FONTE_RECURSO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Fonte de recurso','ROLE_CONSULTAR_FONTE_RECURSO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Fonte de recurso','ROLE_ALTERAR_FONTE_RECURSO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Fonte de recurso','ROLE_EXCLUIR_FONTE_RECURSO','ATIVO');

--Funcionalidade Grupo de Controle de Despesa
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Grupo de Controle de Despesa','ROLE_INCLUIR_GRUPO_CONTROLE_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Grupo de Controle de Despesa','ROLE_CONSULTAR_GRUPO_CONTROLE_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Grupo de Controle de Despesa','ROLE_ALTERAR_GRUPO_CONTROLE_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Grupo de Controle de Despesa','ROLE_EXCLUIR_GRUPO_CONTROLE_DESPESA','ATIVO');

--Funcionalidade Item de Despesa
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Item de Despesa','ROLE_INCLUIR_ITEM_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Item de Despesa','ROLE_CONSULTAR_ITEM_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Item de Despesa','ROLE_ALTERAR_ITEM_DESPESA','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Item de Despesa','ROLE_EXCLUIR_ITEM_DESPESA','ATIVO');

--Funcionalidade Planejamento Anual de Prazos
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Planejamento Anual de Prazos','ROLE_INCLUIR_PLANEJ_ANUAL_PRAZOS','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Planejamento Anual de Prazos','ROLE_CONSULTAR_PLANEJ_ANUAL_PRAZOS','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Planejamento Anual de Prazos','ROLE_ALTERAR_PLANEJ_ANUAL_PRAZOS','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Planejamento Anual de Prazos','ROLE_EXCLUIR_PLANEJ_ANUAL_PRAZOS','ATIVO');

--Funcionalidade Prazo de Solicitação Mensal
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Incluir Prazo de Solicitação Mensal','ROLE_INCLUIR_PRAZO_SOLICITACAO_MENSAL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Consultar Prazo de Solicitação Mensal','ROLE_CONSULTAR_PRAZO_SOLICITACAO_MENSAL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Alterar Prazo de Solicitação Mensal','ROLE_ALTERAR_PRAZO_SOLICITACAO_MENSAL','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Funcionalidade Excluir Prazo de Solicitação Mensal','ROLE_EXCLUIR_PRAZO_SOLICITACAO_MENSAL','ATIVO');

-- Funcionalidade Relatório
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Relatorio Exemplo','ROLE_RELATORIO_EXEMPLO','ATIVO');
INSERT INTO DHWTB027_FUNCIONALIDADE (IDEN_FUNCIONALIDADE,data_atualizacao,data_criacao,versao,descricao,nome,FLAG_SITUACAO) VALUES (DHWSQ027_FUNCIONALIDADE.NEXTVAL,null,sysdate,0,'Relatorio Exemplo','ROLE_AUDIT_LOG','ATIVO');

-- Vincular Todas funcionalidades ao Perfil Administrador
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,1);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,2);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,3);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,4);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,5);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,6);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,7);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,8);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,9);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,10);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,11);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,12);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,13);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,14);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,15);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,16);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,17);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,18);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,19);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,20);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,21);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,22);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,23);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,24);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,25);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,26);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,27);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,28);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,29);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,30);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,31);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,32);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,33);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,34);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,35);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,36);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,37);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,38);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,39);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,40);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,41);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,42);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,43);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,44);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,45);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,46);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,47);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,48);
INSERT INTO DHWTB024_PERFIL_FUNC (IDEN_PERFIL_ACESSO,IDEN_FUNCIONALIDADE) VALUES (1,49);

-- USUARIO
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.usuario','Acao Incluir Usuario','incluir.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.usuario','Acao Alterar Usuario','alterar.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.usuario','Acao Excluir Usuario','excluir.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.usuario','Acao Pesquisar Usuario','pesquisar.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.NEXTVAL,null,sysdate,0,'resetar.senha.usuario','Acao Resetar Senha','resetar.senha.usuario','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'usuario.vincular.perfil','Acao Vincular Perfil','usuario.vincular.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService.save','Incluir Usuario','br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService.delete','Excluir Usuario','br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService.find','Pesquisar Usuario','br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService.find','ACAO');

-- PERFIL
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.perfil','Acao Incluir Perfil','incluir.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.perfil','Acao Alterar Perfil','alterar.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.perfil','Acao Excluir Perfil','excluir.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.perfil','Acao Pesquisar Perfil','pesquisar.perfil','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'perfil.vincular.funcionalidade','Acao Vincular Perfil','perfil.vincular.funcionalidade','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.security.user.PerfilService.save','Incluir Perfil','br.gov.mt.mti.fiplangrf.service.security.user.PerfilService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.security.user.PerfilService.delete','Excluir Perfil','br.gov.mt.mti.fiplangrf.service.security.user.PerfilService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.security.user.PerfilService.find','Pesquisar Perfil','br.gov.mt.mti.fiplangrf.service.security.user.PerfilService.find','ACAO');

-- FUNCIONALIDADE
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.funcionalidade','Acao Incluir Funcionalidade','incluir.funcionalidade','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.funcionalidade','Acao Alterar Funcionalidade','alterar.funcionalidade','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.funcionalidade','Acao Excluir Funcionalidade','excluir.funcionalidade','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.funcionalidade','Acao Pesquisar Funcionalidade','pesquisar.funcionalidade','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'funcionalidade.vincular.permissao','Acao Vincular Funcionalidade','funcionalidade.vincular.permissao','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService.save','Incluir Funcionalidade','br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService.delete','Excluir Funcionalidade','br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService.find','Pesquisar Funcionalidade','br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService.find','ACAO');

-- DESPESA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.despesa','Acao Incluir Despesa','incluir.despesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.despesa','Acao Alterar Despesa','alterar.despesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.despesa','Acao Excluir Despesa','excluir.despesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.despesa','Acao Pesquisar Despesa','pesquisar.despesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.DespesaService.save','Incluir Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.DespesaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.DespesaService.delete','Excluir Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.DespesaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.DespesaService.find','Pesquisar Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.DespesaService.find','ACAO');

-- DETALHAMENTO DESPESA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.detalhamentoDespesa','Acao Incluir Detalhamento de Despesa','incluir.detalhamentoDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.detalhamentoDespesa','Acao Alterar Detalhamento de Despesa','alterar.detalhamentoDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.detalhamentoDespesa','Acao Excluir Detalhamento de Despesa','excluir.detalhamentoDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.detalhamentoDespesa','Acao Pesquisar Detalhamento de Despesa','pesquisar.detalhamentoDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoDespesaService.save','Incluir Detalhamento de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoDespesaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoDespesaService.delete','Excluir Detalhamento de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoDespesaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoDespesaService.find','Pesquisar Detalhamento de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoDespesaService.find','ACAO');

-- DETALHAMENTO DA PROVISÃO DE  DESPESA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.detalhamentoProvisaoDespesa','Acao Incluir Detalhamento da Provisão de Despesa','incluir.detalhamentoProvisaoDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.detalhamentoProvisaoDespesa','Acao Alterar Detalhamento da Provisão de Despesa','alterar.detalhamentoProvisaoDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.detalhamentoProvisaoDespesa','Acao Excluir Detalhamento da Provisão de Despesa','excluir.detalhamentoProvisaoDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.detalhamentoProvisaoDespesa','Acao Pesquisar Detalhamento da Provisão de Despesa','pesquisar.detalhamentoProvisaoDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoProvisaoDespesaService.save','Incluir Detalhamento da Provisão de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoProvisaoDespesaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoProvisaoDespesaService.delete','Excluir Detalhamento da Provisão de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoProvisaoDespesaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoProvisaoDespesaService.find','Pesquisar Detalhamento da Provisão de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoProvisaoDespesaService.find','ACAO');

-- FONTE DE RECURSO
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.fonteRecurso','Acao Incluir Fonte de Recurso','incluir.fonteRecurso','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.fonteRecurso','Acao Alterar Fonte de Recurso','alterar.fonteRecurso','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.fonteRecurso','Acao Excluir Fonte de Recurso','excluir.fonteRecurso','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.fonteRecurso','Acao Pesquisar Fonte de Recurso','pesquisar.fonteRecurso','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.FonteRecursoService.save','Incluir Fonte de Recurso','br.gov.mt.mti.fiplangrf.service.tabelas.FonteRecursoService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.FonteRecursoService.delete','Excluir Fonte de Recurso','br.gov.mt.mti.fiplangrf.service.tabelas.FonteRecursoService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.FonteRecursoService.find','Pesquisar Fonte de Recurso','br.gov.mt.mti.fiplangrf.service.tabelas.FonteRecursoService.find','ACAO');

-- GRUPO DE CONTROLE DE DESPESA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.grupoControleDespesa','Acao Incluir Grupo de Controle de Despesa','incluir.grupoControleDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.grupoControleDespesa','Acao Alterar Grupo de Controle de Despesa','alterar.grupoControleDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.grupoControleDespesa','Acao Excluir Grupo de Controle de Despesa','excluir.grupoControleDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.grupoControleDespesa','Acao Pesquisar Grupo de Controle de Despesa','pesquisar.grupoControleDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.GrupoControleDespesaService.save','Incluir Grupo de Controle de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.GrupoControleDespesaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.GrupoControleDespesaService.delete','Excluir Grupo de Controle de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.GrupoControleDespesaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.GrupoControleDespesaService.find','Pesquisar Grupo de Controle de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.GrupoControleDespesaService.find','ACAO');

-- ITEM DESPESA
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.itemDespesa','Acao Incluir Item de Despesa','incluir.itemDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.itemDespesa','Acao Alterar Item de Despesa','alterar.itemDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.itemDespesa','Acao Excluir Item de Despesa','excluir.itemDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.itemDespesa','Acao Pesquisar Item de Despesa','pesquisar.itemDespesa','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.ItemDespesaService.save','Incluir Item de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.ItemDespesaService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.ItemDespesaService.delete','Excluir Item de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.ItemDespesaService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.ItemDespesaService.find','Pesquisar Item de Despesa','br.gov.mt.mti.fiplangrf.service.tabelas.ItemDespesaService.find','ACAO');

-- PLANEJAMENTO ANUAL DE PRAZOS
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.planejamentoAnualPrazos','Acao Incluir Planejamento Anual de Prazos','incluir.planejamentoAnualPrazos','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.planejamentoAnualPrazos','Acao Alterar Planejamento Anual de Prazos','alterar.planejamentoAnualPrazos','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.planejamentoAnualPrazos','Acao Excluir Planejamento Anual de Prazos','excluir.planejamentoAnualPrazos','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.planejamentoAnualPrazos','Acao Pesquisar Planejamento Anual de Prazos','pesquisar.planejamentoAnualPrazos','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.PlanejamentoAnualPrazosService.save','Incluir Planejamento Anual de Prazos','br.gov.mt.mti.fiplangrf.service.tabelas.PlanejamentoAnualPrazosService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.PlanejamentoAnualPrazosService.delete','Excluir Planejamento Anual de Prazos','br.gov.mt.mti.fiplangrf.service.tabelas.PlanejamentoAnualPrazosService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.PlanejamentoAnualPrazosService.find','Pesquisar Planejamento Anual de Prazos','br.gov.mt.mti.fiplangrf.service.tabelas.PlanejamentoAnualPrazosService.find','ACAO');

-- PRAZO DE SOLICITAÇÃO MENSAL
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'incluir.prazoSolicitacaoMensal','Acao Incluir Prazo de Solicitação Mensal','incluir.prazoSolicitacaoMensal','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'alterar.prazoSolicitacaoMensal','Acao Alterar Prazo de Solicitação Mensal','alterar.prazoSolicitacaoMensal','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'excluir.prazoSolicitacaoMensal','Acao Excluir Prazo de Solicitação Mensal','excluir.prazoSolicitacaoMensal','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'pesquisar.prazoSolicitacaoMensal','Acao Pesquisar Prazo de Solicitação Mensal','pesquisar.prazoSolicitacaoMensal','GATILHO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.PrazoSolicitacaoMensalService.save','Incluir Prazo de Solicitação Mensal','br.gov.mt.mti.fiplangrf.service.tabelas.PrazoSolicitacaoMensalService.save','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.PrazoSolicitacaoMensalService.delete','Excluir Prazo de Solicitação Mensal','br.gov.mt.mti.fiplangrf.service.tabelas.PrazoSolicitacaoMensalService.delete','ACAO');
INSERT INTO DHWTB028_PERMISSAO (IDEN_PERMISSAO,data_atualizacao,data_criacao,versao,action,descricao,nome,tipo_permissao) VALUES (DHWSQ028_PERMISSAO.nextval,null,sysdate,0,'br.gov.mt.mti.fiplangrf.service.tabelas.PrazoSolicitacaoMensalService.find','Pesquisar Prazo de Solicitação Mensal','br.gov.mt.mti.fiplangrf.service.tabelas.PrazoSolicitacaoMensalService.find','ACAO');


--Incluir usuário
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (1,1);
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (1,7);
--Alterar usuário
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (3,2);
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (3,7);
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (3,8);
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (3,10);
--Excluir Usuario
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (4,3);
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (4,7);
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (4,8);
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (4,10);
--Pesquisar usuário
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (2,4);
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (2,7);
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (2,8);
INSERT INTO DHWTB026_FUNC_PERMISSAO (IDEN_FUNCIONALIDADE,IDEN_PERMISSAO) VALUES (2,10);

