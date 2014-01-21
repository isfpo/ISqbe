--
-- Script de controle de version a integrer dans les autres scripts
--
-- auteur : IS/GF
-- Date   : 11.12.02
--
-- Parametre
-- 	&1 			: numero de la version precedente a controler
--
-- Resultat 
--	Version correcte 	: poursuite du traitement
--	Version incorrecte 	: interruption du traitement
--

set termout off
set echo off 
set heading off  feedback off  verify off  
--
-- Set the input parameter default values 
-----------------------------------------
column p1  noprint new_value p1a
column p1b noprint new_value Version_precedente

select '&1' p1 from dual;
select decode('&&p1a', null, '0',  '&&p1a'  ) p1b from dual;
--
spool controle_version.&Version_precedente
--
prompt set term on
prompt prompt 
prompt prompt 
--
select distinct 'prompt Controle version CORRECT, taper une touche pour continuer' from dual where exists  (select 1 from (select max(ver_n_version) v_max from DSC_T_VERSION_DB) a where a.v_max=&Version_precedente);
select distinct 'pause' from dual where exists  (select 1 from (select max(ver_n_version) v_max from DSC_T_VERSION_DB) a where a.v_max=&Version_precedente);
--
select distinct 'prompt Controle version INCORRECT, taper une touche pour terminer' from dual where not exists  (select 1 from (select max(ver_n_version) v_max from DSC_T_VERSION_DB) a where a.v_max=&Version_precedente);
select distinct 'pause' from dual where not exists  (select 1 from (select max(ver_n_version) v_max from DSC_T_VERSION_DB) a where a.v_max=&Version_precedente);
select distinct 'exit' from dual where not exists  (select 1 from (select max(ver_n_version) v_max from DSC_T_VERSION_DB) a where a.v_max=&Version_precedente);
--
spool off
--
start  controle_version.&Version_precedente

set termout on
-- set echo on 
set heading on  feedback on  verify on  


