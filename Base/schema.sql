CREATE DATABASE "evaluation"
     WITH
     OWNER = postgres
     ENCODING = 'UTF8'
     LC_COLLATE = 'French_France.1252'
     LC_CTYPE = 'French_France.1252'
     TABLESPACE = pg_default
     CONNECTION LIMIT = -1;

     CREATE TABLE IF NOT EXISTS t_admin
     (
         id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
         username character varying COLLATE pg_catalog."default" NOT NULL,
         email character varying COLLATE pg_catalog."default" NOT NULL,
         password character varying COLLATE pg_catalog."default" NOT NULL,
         CONSTRAINT user_key PRIMARY KEY (id),
         CONSTRAINT uq_email UNIQUE (email),
         CONSTRAINT uq_username UNIQUE (username)
     );

     CREATE TABLE IF NOT EXISTS t_token_admin
     (
         id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
         idadmin smallint NOT NULL,
         value character varying COLLATE pg_catalog."default" NOT NULL,
         expiration timestamp without time zone NOT NULL,
         CONSTRAINT token_key PRIMARY KEY (id),
         CONSTRAINT "FK_admin" FOREIGN KEY (idadmin)
             REFERENCES public.t_admin (id) MATCH SIMPLE
             ON UPDATE NO ACTION
             ON DELETE NO ACTION
     );


     CREATE TABLE IF NOT EXISTS t_USER(
       id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
       username character varying COLLATE pg_catalog."default" NOT NULL,
       email character varying COLLATE pg_catalog."default" NOT NULL,
       password character varying COLLATE pg_catalog."default" NOT NULL,
       isValid smallint not null,
       dateInscription timestamp without time zone,
       CONSTRAINT user_key_1 PRIMARY KEY (id),
       CONSTRAINT uq_email_1 UNIQUE (email),
       CONSTRAINT uq_username_1 UNIQUE (username)
     );

    CREATE TABLE IF NOT EXISTS t_token_user
     (
         id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
         idUser smallint NOT NULL,
         value character varying COLLATE pg_catalog."default" NOT NULL,
         expiration timestamp without time zone NOT NULL,
         CONSTRAINT token_key_1 PRIMARY KEY (id),
         CONSTRAINT "FK_admin" FOREIGN KEY (idUser)
             REFERENCES public.t_USER (id) MATCH SIMPLE
             ON UPDATE NO ACTION
             ON DELETE NO ACTION
     );

      CREATE TABLE IF NOT EXISTS T_JOUR_SEMAINE(
        id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
        nom character varying COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT "KEY_JOUt_SEMAINE" PRIMARY KEY (id)
      );

     CREATE TABLE IF NOT EXISTS T_CATEGORIE(
       id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
       nom character varying COLLATE pg_catalog."default" NOT NULL,
       HN double precision not null, --- heure normale
       SB double precision not null, ---- salaire de base
       indemnite double precision not null , ----
       CONSTRAINT "KEY_CATEGORIE" PRIMARY KEY (id),
       CONSTRAINT "UNIQUE_CATEGORIE" UNIQUE (nom)
     );

     CREATE SEQUENCE serial START 101;

     CREATE TABLE IF NOT EXISTS T_EMPLOYEE(
        matricule character varying COLLATE pg_catalog."default" NOT NULL,
        idCategorie smallint NOT NULL,
        nom  character varying COLLATE pg_catalog."default" NOT NULL,
        prenom  character varying COLLATE pg_catalog."default" NOT NULL,
        dateNaissance date not null,
        dateEmbauche date not null,
        isDeleted smallint not null,
        CONSTRAINT "KEY_EMPLOYE" PRIMARY KEY (matricule),
        CONSTRAINT "FK_CATEGORIE_1" FOREIGN KEY (idCategorie)
        REFERENCES T_CATEGORIE(id) MATCH SIMPLE
     );

     CREATE TABLE IF NOT EXISTS T_CONTRAT(
       id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
       matricule character varying COLLATE pg_catalog."default" NOT NULL,
       dateDebut date not null,
       dateFin date not null check (dateDebut < dateFin),
       CONSTRAINT "KEY_CONTRAT" PRIMARY KEY (ID),
       CONSTRAINT "FK_EMPLOYEE_1" FOREIGN KEY (matricule)
       REFERENCES T_EMPLOYEE(matricule) MATCH SIMPLE
     );

     CREATE SEQUENCE pointage START 1;

     CREATE TABLE IF NOT EXISTS T_POINTAGE(
       id smallint NOT NULL,
       matricule character varying not null,
       commentaire character varying COLLATE pg_catalog."default" NOT NULL,
       CONSTRAINT "KEY_POINTAGE" PRIMARY KEY (ID),
       CONSTRAINT "FK_KEY_EMP" FOREIGN KEY (matricule)
       REFERENCES T_EMPLOYEE(matricule) match simple
     );

     CREATE TABLE IF NOT EXISTS T_DETAILS_POINTAGE(
          id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
          idPointage smallint not null,
          ferier double precision not null check(ferier>=0),
          jour smallint NOT NULL check(jour>0),
          heureJour double precision not NULL check(heureJour>=0 and heureJour<=17),
          heureNuit double precision not null check(heureNuit>=0 and heurenuit<=7),
          CONSTRAINT "KEY_DETAILS" PRIMARY KEY(id),
          CONSTRAINT "FK_KEY" FOREIGN KEY (idPointage)
          REFERENCES T_POINTAGE(id) match simple
     );


     CREATE TABLE IF NOT EXISTS  T_HEURE_MAJORE(
         id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
         type character varying not null,
         designation character varying not null,
         pourcentage double precision not null,
         CONSTRAINT "KEY_HEURE_MAJORE" PRIMARY KEY(id),
         CONSTRAINT "UNIQUE_KEY" UNIQUE (type),
         CONSTRAINT "DESIGNATION_UNIQUE" UNIQUE (designation)
     );

     CREATE TABLE IF NOT EXISTS   T_HEURE_SUP(
         id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
         designation character varying not null,
         heure double precision not null,
         pourcentage double precision not null,
         CONSTRAINT "T_HEURE_SUP" PRIMARY KEY(id),
         CONSTRAINT "UNIQUE_KEY_SUP" UNIQUE (designation)
     );
