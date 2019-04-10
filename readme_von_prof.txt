/**
 * Grobstruktur des Projekts:
 * - src/		Quellcode (inkl. GUI, Persistenzschicht und deren Konfiguration)
 * - database/	Skripte zum Anlegen, Füllen und Löschen der MySQL(!)-Datenbank
 * - lib/ 		Benötigte Libraries für EclipseLink und JDBC (mysql-... für MySQL)
 *
 * Anleitung:
 * - Anlegen der MySQL-Datenbank
 *   Zugang zur MySQL-DB über Workbench oder phpMyAdmin (Browser) - vgl. Infoseite
 *   in Moodle.
 *   Skripte create-....sql und insert-....sql im eigenen MySQL-Bereich ausführen. 
 *   Hinweis zum create-Skript: das erste Kommando 'DELIMITER ...' ist NUR in der
 *    Workbench auszuführen, in phpMyAdmin diese Zeile weglassen und im Formular
 *    als Begrenzer das ;; eintragen!
 *   Sollte noch kein Bereich existieren, da Datenbanken1 nicht im WS18/19 besucht 
 *   wurde, bitte bei Frau Busse melden.
 *   Hinweis an diejenigen, die einen lokal installierten MySQL-Server unter
 *    Windows nutzen: der Server muss die Tabellen case-sensitive anlegen, d.h.
 *    nicht alles in Großbuchstaben umwandeln. Bei Problemen die Servervariable
 *    lower_case_table_names im Options File umsetzen.
 *
 * - Konfiguration der Persistenzschicht
 *   Die Konfigurationsdatei src/META-INF/persistence.xml muss angepasst werden. 
 *   Es sind (weiter unten) die Properties user und password für die von
 *   Ihnen gewählten DB anzupassen. Bei MySQL muss darüber hinaus auch die
 *   Property url angepasst werden: ganz hinten steht der Datenbankname - bei
 *   den DBs an der TH ist dies immer USER_db
 *
 * - Classpath um die passenden libs erweitern
 *   Falls noch nicht drin, hinzufügen:
 *    - die jars unter lib/eclipselink... und
 *    - MySQL: lib/jdbc/mysql-...jar 
  *
 * - Ausfuehren des Prototyps
 *   Das Hauptprogramm der Schichtenarchitektur ist zu finden in de.fhb.formel1.gui.controller:
 *   Start.java
 *   Ausführen als Java Application...
 *   Es kann beim ersten Starten etwas länger dauern! Es geht dann aber 
 *   ein Fenster auf, in dem eine Saison ausgesucht werden kann, zu der die 
 *   bereits geplanten Rennen angezeigt werden sollen.
 *   Etwaige Fehler, die mit der Datenbankanbindung zu tun haben, treten gleich
 *   beim Start auf, wenn die Persistenzschicht initialisiert wird.
 * 
 * - Die Persistenzschicht wurde generiert, wobei MySQL als Basis verwendet wurde, und
 *   weitestgehend so belassen. Daher bitte nicht über manch seltsame Methodennamen
 *   wundern. Es wurde bisher auch nur die Beispielapplikation getestet. Daher kann es sein,
 *   dass es bei Aufrufen anderer Methoden der Persistenzschichten zu Fehlern in EclipseLink kommt.
 *   In diesem Fall hilft Frau Busse gern weiter.
 *   Die Beispielapplikation sollte aber, wie gesagt, in jedem Fall laufen.
 *   
 * Gutes Gelingen!
  
 * @author Susanne Busse, Gabriele Schmidt
 */