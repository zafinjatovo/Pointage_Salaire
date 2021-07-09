insert into t_admin (username,email,password) values('admin','admin@gmail.com','admin');

insert into t_user (username,email,password,isValid,dateInscription) values('tovo','tovo@tovogmail.com','tovo',0,CURRENT_TIMESTAMP);
insert into t_user (username,email,password,isValid,dateInscription) values('ando','ando@tovogmail.com','ando',0,CURRENT_TIMESTAMP);
insert into t_user (username,email,password,isValid,dateInscription) values('toky','toky@tovogmail.com','toky',0,CURRENT_TIMESTAMP);

insert into T_JOUR_SEMAINE (nom) values('lundi');
insert into T_JOUR_SEMAINE (nom) values('mardi');
insert into T_JOUR_SEMAINE (nom) values('mercredi');
insert into T_JOUR_SEMAINE (nom) values('jeudi');
insert into T_JOUR_SEMAINE (nom) values('vendredi');
insert into T_JOUR_SEMAINE (nom) values('samedi');
insert into T_JOUR_SEMAINE (nom) values('dimanche');

---- Cadre,Normal, Gardien, Chauffeurs
insert into T_CATEGORIE (nom,HN,SB,indemnite) values('Cadre',36,100000,10);
insert into T_CATEGORIE (nom,HN,SB,indemnite) values('Normal',40,200000,10);
insert into T_CATEGORIE (nom,HN,SB,indemnite) values('Gardien',56,300000,10);
insert into T_CATEGORIE (nom,HN,SB,indemnite) values('Chauffeurs',42,150000,10);


----- INSERT parametre MAJORE ------
insert INTO T_HEURE_MAJORE (type,designation,pourcentage) values('nuit','HM30',130);
insert INTO T_HEURE_MAJORE (type,designation,pourcentage) values('ferier','HM40',140);
insert INTO T_HEURE_MAJORE (type,designation,pourcentage) values('dimanche','HM50',150);

insert into T_HEURE_SUP (designation,heure,pourcentage) values('HS30',8,30);
insert into T_HEURE_SUP (designation,heure,pourcentage) values('HS50',12,50);
