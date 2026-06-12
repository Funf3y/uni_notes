# Appello 1 A.A. 25-26
Il progetto consiste in un adapter per l'interfaccia List utilizzando come adaptee la classe Vector di CLDC 1.1.
Si assume di trovarsi in ambiente J2SE 1.4.2.

---

Nella cartella `junit` si possono trovare i file `.jar` usati per il framework JUnit nella versione 4.13, nella cartella `doc` si trova la documentazione (compilata tramite `javadoc`), mentre nella cartella `bin` i binari ottenuti in compilazione (è stata utilizzata la versione di java `openjdk 21.0.11 2026-04-21`).
Si accede alla pagina principale della documentazione grazie al file `doc/index.html` (o `doc\index.html`).

---

# Compilazione ed esecuzione

Tutti i comandi fanno riferimento ai path relativi alla cartella principale del progetto.

## Linux/MacOs

- Per ricompilare la documentazione si può eseguire il comando `javadoc -cp "junit/*:." -d doc myAdapter/*.java myTest/*.java`
- Per ricompilare il progetto si può eseguire il comando `javac -cp "junit/*:." -d bin myAdapter/*.java myTest/*.java`
- Per eseguire il progetto si può eseguire il comando `java -cp "bin:junit/*" myTest.TestRunner`
- Per eseguire le singole classi di test si può eseguire il comando `java -cp "bin:junit/*" org.junit.runner.JUnitCore myTest.<classe da eseguire>` (ma non vengono visualizzati i test falliti)

## Windows

- Per ricompilare la documentazione si può eseguire il comando `javadoc -cp "junit\*:." -d doc myAdapter\*.java myTest\*.java`
- Per ricompilare il progetto si può eseguire il comando `javac -cp "junit\*;." -d bin myTest\*.java myAdapter\*.java`
- Per eseguire il progetto si può eseguire il comando `java -cp "junit\*;bin" myTest.TestRunner`
- Per eseguire le singole classi di test si può eseguire il comando `java -cp "bin;junit\*" org.junit.runner.JUnitCore myTest.<classe da eseguire>` (ma non vengono visualizzati i test falliti)

