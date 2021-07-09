----- get day of week
 SELECT EXTRACT(ISODOW FROM dateinscription) from t_user;
----- week of year
 SELECT EXTRACT(WEEK FROM dateinscription) from t_user;
----- Month
 SELECT EXTRACT(MONTH FROM dateinscription) from t_user;
 ----- year
SELECT EXTRACT(YEAR FROM dateinscription) from t_user;
------ DATE DIFF AGE
SELECT DATE_PART('year', '2000-01-01'::date) - DATE_PART('year', CURRENT_DATE) as age;

---- UPDATE
SELECT * FROM updateEmploye('105',CAST(2 AS smallint),'RAZANADRAKOTO','Koto','2000-04-22','2021-07-06','2022-07-06',CAST(0 AS smallint));
