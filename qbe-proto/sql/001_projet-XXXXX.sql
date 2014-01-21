--
-- Description...
--
-- Auteur : is/sg
-- Date   : 12.01.2011
--
-- pré-process
-- ***********

@@IS_CONTROLE_VERSION.SQL 2

--Fichier de log
spool 001_legal-XXXXX.log;
set echo on;

-- SQL....

-- Modification de la version de la bd
INSERT INTO IS_VERSION_DB (LABEL, VERSION, COMPATIBILITE, DESCRIPTION)  
                   VALUES ('LEGAL', '3',     '1',   'XXXXX');

SHOW ERRORS;
spool off;
