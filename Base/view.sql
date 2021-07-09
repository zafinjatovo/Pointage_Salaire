-------------- VIEW
CREATE VIEW ADMIN AS
select *,token(id) from t_admin;

CREATE VIEW USERS AS
select *,coalesce(tokenuser(id),'not valid') as token from t_user;

CREATE VIEW V_USER_NOVALID
AS
 select * from t_USER
 where isValid=0;

CREATE VIEW EMPLOYEE AS
select * from t_employee where isdeleted=0;

CREATE VIEW EMPLOYEE_D AS
select matricule,t_categorie.id,T_categorie.nom as categorie,employee.nom,prenom,datenaissance,dateembauche,isdeleted,t_categorie.HN,t_categorie.SB,T_CATEGORIE.indemnite from employee left join t_categorie on t_categorie.id=employee.idcategorie;

select *,
  getHeureFerier(id) as ferier,
  getHeureFerierTravailer(id),
  getHeureJour(id),
  getHeureNuit(id,CAST(7 AS smallint)),
  getHeureJourSpecifier(id,CAST(7 AS smallint)),
  getsommeHT(id)
 from t_pointage;
