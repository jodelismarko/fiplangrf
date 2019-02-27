BEGIN

	FOR c IN (SELECT table_name FROM user_tables) 
	
		LOOP
	    	IF c.table_name like '%DHWTB%'
	        	THEN EXECUTE IMMEDIATE ('DROP TABLE "' || c.table_name || '" CASCADE CONSTRAINTS');
	    	END IF;
		END LOOP;
	
	FOR s IN (SELECT sequence_name FROM user_sequences) 
	
		LOOP
			EXECUTE IMMEDIATE ('DROP SEQUENCE ' || s.sequence_name);
		END LOOP;

END;