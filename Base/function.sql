-------------- gerer token admin  ----------

CREATE OR REPLACE FUNCTION public.token(
 parametre smallint)
   RETURNS character varying
   LANGUAGE 'plpgsql'
   COST 100
   VOLATILE PARALLEL UNSAFE
AS $BODY$
declare
   tokenValue character varying;
   validation smallint;
BEGIN
   select value INTO tokenValue FROM t_token_admin WHERE idAdmin=parametre AND expiration>current_timestamp;
   IF tokenValue IS NULL OR tokenValue = '' THEN
       tokenValue := MD5(CONCAT(now(),parametre));
       insert into t_token_admin (idAdmin,value,expiration) values(parametre,tokenValue,CURRENT_TIMESTAMP + '1 day');
   END IF;
   return tokenValue;
END;
$BODY$;

-------------- gerer token utulisateur  ----------

CREATE OR REPLACE FUNCTION public.tokenUser(
parametre smallint)
  RETURNS character varying
  LANGUAGE 'plpgsql'
  COST 100
  VOLATILE PARALLEL UNSAFE
AS $BODY$
declare
  tokenValue character varying;
  validation smallint;
BEGIN
  select isValid into validation from t_user where id=parametre;
  select value INTO tokenValue FROM t_token_user WHERE idUser=parametre AND expiration>current_timestamp;
  IF validation=1 THEN
    IF tokenValue IS NULL OR tokenValue = '' THEN
        tokenValue := MD5(CONCAT(now(),parametre));
        insert into t_token_user (idUser,value,expiration) values(parametre,tokenValue,CURRENT_TIMESTAMP + '1 day');
    END IF;
  END IF;
  return tokenValue;
END;
$BODY$;

------------- check token admin------------

CREATE OR REPLACE FUNCTION checktoken(
 parametre character varying)
   RETURNS boolean
   LANGUAGE 'plpgsql'
   COST 100
   VOLATILE PARALLEL UNSAFE
AS $BODY$
declare
   tokenValue character varying;
   result boolean;
BEGIN
   select value INTO tokenValue FROM t_token_admin WHERE value=parametre AND expiration>current_timestamp;
   IF tokenValue IS NULL OR tokenValue = '' THEN
       result := FALSE;
   ELSE
       result := TRUE;
   END IF;
   return result;
END;
$BODY$;


------------- check token user------------

CREATE OR REPLACE FUNCTION checktokenUser(
 parametre character varying)
   RETURNS boolean
   LANGUAGE 'plpgsql'
   COST 100
   VOLATILE PARALLEL UNSAFE
AS $BODY$
declare
   tokenValue character varying;
   result boolean;
BEGIN
   select value INTO tokenValue FROM t_token_user WHERE value=parametre AND expiration>current_timestamp;
   IF tokenValue IS NULL OR tokenValue = '' THEN
       result := FALSE;
   ELSE
       result := TRUE;
   END IF;
   return result;
END;
$BODY$;


------------- email check in save ----------------

CREATE OR REPLACE FUNCTION checkemail(
	parametre character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
declare
    result character varying;
BEGIN
    select email INTO result FROM T_user WHERE email=parametre;
    IF result IS NULL OR result='' THEN
       result := 'valid';
    ELSE
       result := 'not valid';
    END IF;
    return result;
END;
$BODY$;


---------- username check in save -------------------

CREATE OR REPLACE FUNCTION checkusername(
	parametre character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
declare
    result character varying;
BEGIN
    select username INTO result FROM T_user WHERE username=parametre;
    IF result IS NULL OR result='' THEN
       result := 'valid';
    ELSE
       result := 'not valid';
    END IF;
    return result;
END;
$BODY$;



-------- save admin ------------

CREATE OR REPLACE FUNCTION saveuser(
	thisusername character varying,
	thisemail character varying,
	thispassword character,
	thisvalidation smallint)
    RETURNS TABLE(validusername character varying, validemail character varying)
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
declare
    usernameValid character varying;
    emailValid character varying;
BEGIN
    ----- check username and email -----
    select into usernameValid checkUsername(thisUsername);
    select into emailValid checkEmail(thisEmail);
    IF usernameValid='valid' and emailValid='valid' THEN
        -----INSERT ADMIN----
        insert into T_user (username,email,password,isValid,dateInscription)
        values(thisUsername,thisEmail,thisPassword,thisValidation,CURRENT_TIMESTAMP);
    END IF;
    return query select usernameValid,emailValid;

END;
$BODY$;


---------- AGE check in save -------------------

CREATE OR REPLACE FUNCTION checkage(
	parametre character varying,
  dateEmbauche character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
declare
    result character varying;
    temp smallint;
BEGIN
    select DATE_PART('year', dateEmbauche::date) - DATE_PART('year', parametre::date) as age INTO temp;
    IF temp >= 18 THEN
       result := 'valid';
    ELSE
       result := 'not valid';
    END IF;
    return result;
END;
$BODY$;

---------- get Fincontrat -------------------

CREATE OR REPLACE FUNCTION getFinContrat(
	parametre character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
declare
    result character varying;
BEGIN
    select into result dateFin from T_CONTRAT WHERE matricule=parametre;
    return result;
END;
$BODY$;


-------- save EMPLOYEE ------------

CREATE OR REPLACE FUNCTION saveEmploye(
  thisCategorie smallint,
  thisnom character varying,
	thisprenom character varying,
	thisdateNaissance character varying,
	thisdateEmbauche character varying,
  thisdateFinContrat character varying
)
    RETURNS TABLE(matricule character varying)
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
declare
    validationAge character varying;
    nummatricule character varying;
BEGIN
    ----- check age -----
    select into validationAge checkage(thisdateNaissance,thisdateEmbauche);
    IF validationAge='valid' THEN
        -----INSERT EMPLOYEE----
        -- matricule | idcategorie | nom | prenom | datenaissance | dateembauche | isdeleted
        select into nummatricule nextval('serial');
        insert into T_EMPLOYEE (matricule,idcategorie,nom,prenom,datenaissance,dateembauche,isDeleted)
        values(nummatricule,thisCategorie,thisnom,thisprenom,thisdateNaissance::DATE,thisdateEmbauche::DATE,0);
        ----- isert contrat
        insert into T_CONTRAT (matricule,dateDebut,dateFin) values (nummatricule,thisdateEmbauche::DATE,thisdateFinContrat::DATE);
    ELSE
        nummatricule:='';
    END IF;
    return query select nummatricule;

END;
$BODY$;
 ----- update

CREATE OR REPLACE FUNCTION updateEmploye(
  thismatricule character varying,
  thisCategorie smallint,
  thisnom character varying,
	thisprenom character varying,
	thisdateNaissance character varying,
	thisdateEmbauche character varying,
  thisdateFinContrat character varying,
  delete smallint
)
  RETURNS boolean
  LANGUAGE 'plpgsql'
  COST 100
  VOLATILE PARALLEL UNSAFE
  AS $BODY$
declare
    validationAge character varying;
    result boolean;
BEGIN
    ----- check age -----
    select into validationAge checkage(thisdateNaissance,thisdateEmbauche);
    IF validationAge='valid' THEN
        -----INSERT EMPLOYEE----
        -- matricule | idcategorie | nom | prenom | datenaissance | dateembauche | isdeleted
        update T_EMPLOYEE set idCategorie=thisCategorie,nom=thisnom,prenom=thisprenom,dateNaissance=thisdateNaissance::DATE,dateEmbauche=thisdateEmbauche::DATE  where matricule=thismatricule;
        ----- isert contrat
        UPDATE T_CONTRAT set dateDebut=thisdateEmbauche::DATE,dateFin=thisdateFinContrat::DATE where matricule=thismatricule;
        result:=TRUE;
    ELSE
        result:=FALSE;
    END IF;
    return result;

END;
$BODY$;

CREATE OR REPLACE FUNCTION getPointage(
  thismatricule character
)
    RETURNS TABLE(id smallint,matricule character varying)
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
declare
    idvalue smallint;
    nummatricule character varying;
BEGIN
    ----- check age -----
    select into idvalue  max(t_pointage.id) from t_pointage where t_pointage.matricule=thismatricule;
    return query select t_pointage.id,t_pointage.matricule from t_pointage where t_pointage.id=idvalue;

END;
$BODY$;

--select sum(heurejour) from t_details_pointage where idpointage=19;


CREATE OR REPLACE FUNCTION getHeureJour(
	parametre smallint)
    RETURNS double precision
    LANGUAGE 'plpgsql'
AS $BODY$
declare
    value double precision;
BEGIN
    select into value sum(heurejour) from t_details_pointage where idpointage=parametre and ferier=0;
    return value;
END;
$BODY$;


CREATE OR REPLACE FUNCTION getHeureFerier(
	parametre smallint)
    RETURNS double precision
    LANGUAGE 'plpgsql'
AS $BODY$
declare
    value double precision;
BEGIN
    select into value sum(ferier) from t_details_pointage where idpointage=parametre;
    return value;
END;
$BODY$;

CREATE OR REPLACE FUNCTION getHeureFerierTravailer(
	parametre smallint)
    RETURNS double precision
    LANGUAGE 'plpgsql'
AS $BODY$
declare
    value double precision;
BEGIN
    select into value sum(heurejour) + sum(heurenuit)  from t_details_pointage where idpointage=parametre and ferier>0;
    return value;
END;
$BODY$;


CREATE OR REPLACE FUNCTION getHeureJourSpecifier(
	parametre smallint,
  parametre2 smallint)
    RETURNS double precision
    LANGUAGE 'plpgsql'
AS $BODY$
declare
    value double precision;
BEGIN
    select into value sum(heurejour) + sum(heurenuit)  from t_details_pointage where idpointage=parametre and ferier=0 and jour=parametre2;
    return value;
END;
$BODY$;

CREATE OR REPLACE FUNCTION getHeureNuit(
	parametre smallint,
  parametre2 smallint)
    RETURNS double precision
    LANGUAGE 'plpgsql'
AS $BODY$
declare
    value double precision;
BEGIN
    select into value sum(heurenuit)  from t_details_pointage where idpointage=parametre and ferier=0 and jour<>parametre2;
    return value;
END;
$BODY$;


CREATE OR REPLACE FUNCTION getsommeHT(
	parametre smallint)
    RETURNS double precision
    LANGUAGE 'plpgsql'
AS $BODY$
declare
    value double precision;
BEGIN
    select into value sum(t_details_pointage.heurejour) + sum(t_details_pointage.heurenuit) + sum(t_details_pointage.ferier) from t_details_pointage where t_details_pointage.idPointage=parametre;
    return value;
END;
$BODY$;



---- getHeure jour pointage --- ferier | jour | heurejour | heurenuit

CREATE OR REPLACE FUNCTION getHeure(
  thispointage smallint,
  jourspecifie smallint
)
    RETURNS TABLE(idPointage smallint,ferier double precision,heuretravailFerier double precision,heurejour double precision,heurenuit double precision,heureDimance double precision,sommeHT double precision)
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
declare
    feriervalue double precision;
    heuretravailFeriervalue double precision;
    heurejourvalue double precision;
    heurenuitvalue double precision;
    heureDimancevalue  double precision;
    sommevalue double precision;
BEGIN

    select into feriervalue getHeureFerier(id) from t_pointage;
    select into heuretravailFeriervalue getHeureFerierTravailer(id) from t_pointage;
    select into heurejourvalue getHeureJour(id) from t_pointage;
    select into heurenuitvalue getHeureNuit(id,jourspecifie) from t_pointage;
    select into heureDimancevalue getHeureJourSpecifier(id,jourspecifie) from t_pointage;
    select into sommevalue sum(t_details_pointage.heurejour) + sum(t_details_pointage.heurenuit) + sum(t_details_pointage.ferier) from t_details_pointage where t_details_pointage.idPointage=thispointage;

    return query select thispointage,feriervalue,heuretravailFeriervalue,heurejourvalue,heurenuitvalue,heureDimancevalue,sommevalue;
END;
$BODY$;
