CREATE TABLESPACE fiplan_gma_des DATAFILE 'fiplan_gma_des.dbf' SIZE 100m AUTOEXTEND ON NEXT 100m EXTENT MANAGEMENT LOCAL;

CREATE USER fiplan_gma_des IDENTIFIED BY timao DEFAULT TABLESPACE fiplan_gma_des QUOTA UNLIMITED ON  fiplan_gma_des;

GRANT CREATE SESSION TO fiplan_gma_des;

GRANT create session, 
      alter session, 
      create table, 
      create procedure, 
      create view, 
      create materialized view, 
      create trigger, 
      create sequence, 
      create any directory, 
      create type, 
      create synonym TO fiplan_gma_des;