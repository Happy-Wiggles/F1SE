--
-- Skript zum Loeschen einer reduzierten Formel 1 Datenbank
--
-- Loescht Trigger, Sequenzen und Tabellen
--

DROP TRIGGER IF EXISTS AdaptSiege;
DROP TRIGGER IF EXISTS DeleteSieg;
DROP TRIGGER IF EXISTS AddSieg;

DROP TRIGGER IF EXISTS AdaptSaisonEinsatz;
DROP TRIGGER IF EXISTS DeleteSaisonEinsatz;
DROP TRIGGER IF EXISTS AddSaisonEinsatz;

DROP TRIGGER IF EXISTS AdaptPolesAndSchnellsteRunden;
DROP TRIGGER IF EXISTS DeletePolesAndSchnellsteRunden;
DROP TRIGGER IF EXISTS AddPolesAndSchnellsteRunden;

DROP TABLE IF EXISTS gefahrenvon;
DROP TABLE IF EXISTS setztein;
DROP TABLE IF EXISTS rennen;
DROP TABLE IF EXISTS rennstall;
DROP TABLE IF EXISTS rennfahrer;
DROP TABLE IF EXISTS saison;
DROP TABLE IF EXISTS rennstrecke;
DROP TABLE IF EXISTS land;

