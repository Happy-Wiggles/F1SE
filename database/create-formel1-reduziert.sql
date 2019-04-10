--
-- Skript zum Anlegen einer reduzierten Formel 1 Datenbank
-- Susanne Busse, März 2019
--
-- Achtung: Tabellennamen auf MySQL-Server der TH case-sensitive!
-- 

-- für MySQL Administrator oder Workbench
-- bei phpMyAdmin auf der Webseite auf ;; stellen
-- wird nur wegen der Trigger benoetigt, die auch das ; benutzen
DELIMITER ;;  

-- ggf. vorher die Tabellen und Trigger löschen
DROP TRIGGER IF EXISTS AdaptSiege;;
DROP TRIGGER IF EXISTS DeleteSieg;;
DROP TRIGGER IF EXISTS AddSieg;;

DROP TRIGGER IF EXISTS AdaptSaisonEinsatz;;
DROP TRIGGER IF EXISTS DeleteSaisonEinsatz;;
DROP TRIGGER IF EXISTS AddSaisonEinsatz;;

DROP TRIGGER IF EXISTS AdaptPolesAndSchnellsteRunden;;
DROP TRIGGER IF EXISTS DeletePolesAndSchnellsteRunden;;
DROP TRIGGER IF EXISTS AddPolesAndSchnellsteRunden;;

DROP TABLE IF EXISTS gefahrenvon;;
DROP TABLE IF EXISTS setztein;;
DROP TABLE IF EXISTS rennen;;
DROP TABLE IF EXISTS rennstall;;
DROP TABLE IF EXISTS rennfahrer;;
DROP TABLE IF EXISTS saison;;
DROP TABLE IF EXISTS rennstrecke;;
DROP TABLE IF EXISTS land;;


/* ======================================================== */
/* Table: Land                                              */
/* ======================================================== */

CREATE TABLE land 
(	
    LandID		INTEGER       NOT NULL    auto_increment, 
    Name    	VARCHAR(40)   NOT NULL, 
    Flagge		BLOB		  NULL,
    PRIMARY KEY (LandID) 
);;


/*==========================================================*/
/* Table: Rennstrecke                                       */
/*==========================================================*/

CREATE TABLE rennstrecke 
(	
    StreckenID  INTEGER     NOT NULL    auto_increment, 
    Name        VARCHAR(40) NOT NULL, 
    LandID      INTEGER	    NOT NULL, 
    PRIMARY KEY (StreckenID),
    FOREIGN KEY (LandID) REFERENCES land (LandID) on update cascade
);;


/* ======================================================== */
/* Table: Saison                                            */
/*==========================================================*/

CREATE TABLE saison 
(	
    Jahr        NUMERIC(4,0)      NOT NULL, 
    PRIMARY KEY (Jahr)
);;
 
 
/*==========================================================*/
/* Table: Rennstall                                         */
/*==========================================================*/
 
CREATE TABLE rennstall 
(	
    RennstallID INTEGER	        NOT NULL    auto_increment, 
    Name        VARCHAR(40)     NOT NULL, 
    Punkte      NUMERIC(7,2)    NOT NULL, 
    Jahr        NUMERIC(4,0)    NOT NULL, 
    PRIMARY KEY (RennstallID),
    FOREIGN KEY (Jahr) REFERENCES saison (Jahr)
);;


/*==========================================================*/
/* Table: Rennfahrer                                        */
/*==========================================================*/

CREATE TABLE rennfahrer 
(	
    RennfahrerID      INTEGER       NOT NULL    auto_increment, 
    Name              VARCHAR(30)   NOT NULL, 
    Vorname           VARCHAR(30)   NOT NULL, 
    Geburtstag        DATE          NOT NULL, 
    Nationalitaet     INTEGER       NOT NULL, 
    Siege             NUMERIC(4,0)  DEFAULT 0 NOT NULL, 
    PolePositions     NUMERIC(4,0)  DEFAULT 0 NOT NULL, 
    SchnellsteRunden  NUMERIC(4,0)  DEFAULT 0 NOT NULL, 
    Punkte            NUMERIC(7,2)  DEFAULT 0.00 NOT NULL, 
    PRIMARY KEY (RennfahrerID),
    FOREIGN KEY (Nationalitaet) REFERENCES land (LandID) on update cascade
);;


/*==========================================================*/
/* Table: Rennen                                            */
/*==========================================================*/

CREATE TABLE rennen
(	
    Datum             DATE             NOT NULL, 
    StreckenID        INTEGER          NOT NULL, 
    Jahr              NUMERIC(4,0)     NOT NULL, 
    PoleFahrerID      INTEGER          NULL, 
    SchnellsteRundeID INTEGER          NULL, 
    CHECK(year(Datum) = Jahr),   -- wird ignoriert!
    PRIMARY KEY (Datum),
    FOREIGN KEY (StreckenID) REFERENCES rennstrecke (StreckenID) on update cascade, 
    FOREIGN KEY (Jahr) REFERENCES saison (Jahr), 
    FOREIGN KEY (PoleFahrerID) REFERENCES rennfahrer (RennfahrerID) on update cascade, 
    FOREIGN KEY (SchnellsteRundeID) REFERENCES rennfahrer (RennfahrerID) on update cascade
);; 


/*==========================================================*/
/* Table: setztein                                         */
/*==========================================================*/
 
 CREATE TABLE setztein 
 (	
    RennstallID       INTEGER        NOT NULL, 
    RennfahrerID      INTEGER        NOT NULL, 
    Punkte            NUMERIC(6,2)   UNSIGNED   DEFAULT 0.00 NOT NULL, 
    PRIMARY KEY (RennstallID, RennfahrerID), 
    FOREIGN KEY (RennstallID) REFERENCES rennstall(RennstallID) on delete cascade on update cascade, 
    FOREIGN KEY (RennfahrerID) REFERENCES rennfahrer (RennfahrerID) on delete cascade on update cascade
);;


/*==========================================================*/
/* Table: gefahrenvon                                       */
/*==========================================================*/
 
CREATE TABLE gefahrenvon 
(	
    RennfahrerID      INTEGER         NOT NULL, 
    Datum             DATE            NOT NULL, 
    Platz             NUMERIC(2,0)    UNSIGNED   NULL, 
    PRIMARY KEY (RennfahrerID, Datum), 
    FOREIGN KEY (Datum) REFERENCES rennen (Datum) on delete cascade on update cascade, 
    FOREIGN KEY (RennfahrerID) REFERENCES rennfahrer (RennfahrerID) on delete cascade on update cascade
);;



/*==========================================================*/
/* Trigger: Integritaet Poles und schnellste Runden         */
/*==========================================================*/

CREATE TRIGGER AddPolesAndSchnellsteRunden 
after insert on rennen             
for each row  
begin
  if (@DISABLE_TRIGGERS is null) then
    update rennfahrer
     set PolePositions = PolePositions + 1
     where RennfahrerID = new.PoleFahrerID;

    update rennfahrer
     set SchnellsteRunden = SchnellsteRunden + 1
     where RennfahrerID = new.SchnellsteRundeID;
  end if;
end;
;;


CREATE TRIGGER DeletePolesAndSchnellsteRunden 
after delete on rennen 
for each row
begin
  if (@DISABLE_TRIGGERS is null) then
    update rennfahrer
     set PolePositions = PolePositions - 1
     where RennfahrerID = old.PoleFahrerID;

    update rennfahrer
     set SchnellsteRunden = SchnellsteRunden - 1
     where RennfahrerID = old.SchnellsteRundeID;
  end if;
end;
;;


CREATE TRIGGER AdaptPolesAndSchnellsteRunden 
before update on rennen 
for each row
begin
  if (@DISABLE_TRIGGERS is null) then
    if (new.PoleFahrerID <> old.PoleFahrerID)
    then
      update rennfahrer
       set PolePositions = PolePositions - 1
       where RennfahrerID = old.PoleFahrerID;
      update rennfahrer
       set PolePositions = PolePositions + 1
       where RennfahrerID = new.PoleFahrerID;
    end if;

    if (new.SchnellsteRundeID <> old.SchnellsteRundeID)
    then
      update rennfahrer
       set SchnellsteRunden = SchnellsteRunden - 1
       where RennfahrerID = old.SchnellsteRundeID;
      update rennfahrer
       set SchnellsteRunden = SchnellsteRunden + 1
       where RennfahrerID = new.SchnellsteRundeID;
    end if;
  end if;
end;
;;


/*==========================================================*/
/* Trigger: Integritaet Punkte Rennfahrer und Team          */
/*==========================================================*/

CREATE TRIGGER AddSaisonEinsatz 
after insert on setztein 
for each row
begin
  if (@DISABLE_TRIGGERS is null) then
    update rennfahrer
     set Punkte = Punkte + new.Punkte
     where RennfahrerID = new.RennfahrerID;

    update rennstall
     set Punkte = Punkte + new.Punkte
     where RennstallID = new.RennstallID;
  end if;
end;
;;
 

CREATE TRIGGER DeleteSaisonEinsatz 
before delete on setztein 
for each row
begin
  if (@DISABLE_TRIGGERS is null) then
    update rennfahrer
     set Punkte = Punkte - old.Punkte
     where RennfahrerID = old.RennfahrerID;

    update rennstall
     set Punkte = Punkte - old.Punkte
     where RennstallID = old.RennstallID;
  end if;
end;
;;


CREATE TRIGGER AdaptSaisonEinsatz 
before update on setztein 
for each row
begin
  if (@DISABLE_TRIGGERS is null) then
    update rennfahrer
     set Punkte = Punkte - old.Punkte
     where RennfahrerID = old.RennfahrerID;

    update rennstall
     set Punkte = Punkte - old.Punkte
     where RennstallID = old.RennstallID;

    update rennfahrer
     set Punkte = Punkte + new.Punkte
     where RennfahrerID = new.RennfahrerID;

    update rennstall
     set Punkte = Punkte + new.Punkte
     where RennstallID = new.RennstallID;
  end if;
end;
;;


/*==========================================================*/
/* Trigger: Integritaet Siege eines Rennfahrers             */
/*==========================================================*/

CREATE TRIGGER AddSieg 
after insert on gefahrenvon 
for each row
begin
  if (@DISABLE_TRIGGERS is null) then
    if new.Platz = 1
    then
      update rennfahrer
       set Siege = Siege + 1
       where RennfahrerID = new.RennfahrerID;
    end if;
  end if;
end;
;;


CREATE TRIGGER DeleteSieg 
before delete on gefahrenvon 
for each row
begin
  if (@DISABLE_TRIGGERS is null) then
    if old.Platz = 1 then
       update rennfahrer
       set Siege = Siege - 1
       where RennfahrerID = old.RennfahrerID;
    end if;
  end if;
end;;
 

CREATE TRIGGER AdaptSiege 
before update on gefahrenvon 
for each row
begin
  if (@DISABLE_TRIGGERS is null) then
    if old.Platz = 1 then
       update rennfahrer
       set Siege = Siege - 1
       where RennfahrerID = old.RennfahrerID;
    end if;

    if new.Platz = 1 then
       update rennfahrer
       set Siege = Siege + 1
       where RennfahrerID = new.RennfahrerID;
    end if;
  end if;
end;;
