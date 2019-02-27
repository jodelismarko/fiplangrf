ALTER TABLE DHWTB017_PERFIL_ACESSO
DROP COLUMN DESCRICAO;

ALTER TABLE DHWTB017_PERFIL_ACESSO_AUD
DROP COLUMN DESCRICAO;
commit;

ALTER TABLE DHWTB017_PERFIL_ACESSO
RENAME COLUMN NOME TO  DESC_PERFIL_ACESSO;

ALTER TABLE DHWTB017_PERFIL_ACESSO_AUD
RENAME COLUMN NOME TO  DESC_PERFIL_ACESSO;
commit;

--------------------------------------------------------
--DDL FOR ADD COLUMN VERSAO
--------------------------------------------------------
BEGIN
	FOR c IN (SELECT table_name FROM user_tables) 
		LOOP
	    	IF c.table_name like '%DHWTB%' 
                and c.table_name not like '%_AUD%' 
                and c.table_name not like '%DHWTB018_USUARIO%' 
                and c.table_name not like '%DHWTB017_PERFIL_ACESSO%'  
                and c.table_name not like '%DHWTB027_FUNCIONALIDADE%'
                and c.table_name not like '%DHWTB028_PERMISSAO%'
	        	THEN EXECUTE IMMEDIATE ('ALTER TABLE "' || c.table_name || '" ADD VERSAO NUMBER(19)');
	    	END IF;
		END LOOP;
END;