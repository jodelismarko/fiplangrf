INSERT INTO dhwtb018_usuario ( iden_usuario, data_atualizacao, data_criacao, versao, data_ultimo_acesso, numr_cpf, flag_situacao) 
    VALUES (dhwsq018_usuario.nextval, NULL, SYSDATE, 0, NULL, '36329059918', 'ATIVO');
INSERT INTO dhwtb018_usuario ( iden_usuario, data_atualizacao, data_criacao, versao, data_ultimo_acesso, numr_cpf, flag_situacao) 
    VALUES (dhwsq018_usuario.nextval, NULL, SYSDATE, 0, NULL, '04267231125', 'ATIVO');
   
INSERT INTO DHWTB017_PERFIL_ACESSO (IDEN_PERFIL_ACESSO,DATA_ATUALIZACAO,DATA_CRIACAO,VERSAO,NOME, DESCRICAO,FLAG_SITUACAO) 
    VALUES (DHWSQ017_PERFIL_ACESSO.nextval,null,sysdate,0,'Master', 'Perfil de Acesso Master','ATIVO');
    
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) 
    VALUES ( '36329059918', null, 'admin@master.com.br', 'N', 'Usuario Master Dev', '65981330233', '6536537852');
    
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) 
    VALUES ( '04267231125', null, 'jilds@outlook.com', 'N', 'Jonathan Inacio Lima da Silva', '65981330233', '6536537852');

INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) 
    VALUES ( '47870477010', null, 'usuario1@email.com', 'N', 'Usuario 1', '65981330233', '6536537852');
    
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) 
    VALUES ( '28868756005', null, 'usuario2@email.com', 'N', 'Usuario 2', '65981330233', '6536537852');
    
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) 
    VALUES ( '98044538003', null, 'usuario3@email.com', 'N', 'Usuario 3', '65981330233', '6536537852');
    
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) 
    VALUES ( '29025094058', null, 'usuario4@email.com', 'N', 'Usuario 4', '65981330233', '6536537852');
    
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) 
    VALUES ( '00573046026', null, 'usuario5@email.com', 'N', 'Usuario 5', '65981330233', '6536537852');
    
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) 
    VALUES ( '08773720046', null, 'usuario6@email.com', 'N', 'Usuario 6', '65981330233', '6536537852');
    
INSERT INTO acwvw0464 (nr_cpf, dt_expiracao, ds_email, flg_usr_bloqueado, ds_nome, nr_tel_cel, nr_tel_com ) 
    VALUES ( '36932220024', null, 'usuario7@email.com', 'N', 'Usuario 7', '65981330233', '6536537852');