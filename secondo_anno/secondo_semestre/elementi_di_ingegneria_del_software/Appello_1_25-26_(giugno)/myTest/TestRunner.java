package myTest;

import java.util.*;
import org.junit.runner.notification.Failure;
import org.junit.runner.*;
import org.junit.runners.Suite;
import myAdapter.*;

/**Classe per l'esecuzione delle classe di test del pacchetto {@code myTest}.
* Fornisce in standard output il numero di test eseguiti, numero di test falliti, tempo impiegato ad eseguire i test e le specifiche dei test falliti.
* <br>
* Per eseguire i test è usato il framework JUnit nella versione 4.13., incluso nella cartella {@code ./junit}
 * (notare che il nome è scritto tutto in minuscolo), con la versione di java {@code openjdk 21.0.11 2026-04-21}.
 * <br>
 * Per eseguire le classi di test è necessario includere i file JUnit (con estensione {@code .jar}) nel proprio classpath,
 * altrimenti si può usare la flag {@code -cp} per indicare il classpath (da linea di comando).
 * <ul>
 * <li><b>Windows:</b> Per compilare usare il comando {@code javac -cp "junit\*;." -d bin myTest\*.java myAdapter\*.java}, per eseguire il programma usare il comando {@code java -cp "junit\*;bin" myTest.TestRunner}.
 * <li><b>Linux/MacOS:</b> Per compilare il programma usare il comando {@code javac -d bin -cp "junit/*" myAdapter/*.java myTest/*.java}, per eseguire usare il comando {@code java -cp "bin:junit/*" myTest.TestRunner}.
 * </ul>
*/

public class TestRunner{
	/**Metodo chiamato per eseguire tutti i test del pacchetto {@code myTest}.
	 * Stampa in standard output i test eseguiti, test falliti, test andati a buon fine, il tempo totale di esecuzione e le specifiche dei test falliti.
	 * <br>
	 * I parametri forniti in input da riga di comando non sono utilizzati.
	 * @param args Parametri forniti da riga di comando, non utilizzati.
	 */
	public static void main(String[] args){
		int totalRunCount = 0;
		int totalFailureCount = 0;
		int totalSuccessCount = 0;
		long totalRunTime = 0;

		org.junit.runner.Result resultTestListAdapter = org.junit.runner.JUnitCore.runClasses(TestListAdapter.class);
		totalRunCount += resultTestListAdapter.getRunCount();
		totalFailureCount += resultTestListAdapter.getFailureCount();
		totalSuccessCount += resultTestListAdapter.getRunCount() - resultTestListAdapter.getFailureCount()  - resultTestListAdapter.getIgnoreCount() - resultTestListAdapter.getAssumptionFailureCount();
		totalRunTime += resultTestListAdapter.getRunTime();
		List<Failure> totalFailuresList = resultTestListAdapter.getFailures();

		org.junit.runner.Result resultTestSubListAdapter = org.junit.runner.JUnitCore.runClasses(TestSubListAdapter.class);
		totalRunCount += resultTestSubListAdapter.getRunCount();
		totalFailureCount += resultTestSubListAdapter.getFailureCount();
		totalSuccessCount += resultTestSubListAdapter.getRunCount() - resultTestSubListAdapter.getFailureCount()  - resultTestSubListAdapter.getIgnoreCount() - resultTestSubListAdapter.getAssumptionFailureCount();
		totalRunTime += resultTestSubListAdapter.getRunTime();
		totalFailuresList.addAll(resultTestSubListAdapter.getFailures());

		org.junit.runner.Result resultTestIterators = org.junit.runner.JUnitCore.runClasses(TestIterators.class);
		totalRunCount += resultTestIterators.getRunCount();
		totalFailureCount += resultTestIterators.getFailureCount();
		totalSuccessCount += resultTestIterators.getRunCount() - resultTestIterators.getFailureCount()  - resultTestIterators.getIgnoreCount() - resultTestIterators.getAssumptionFailureCount();
		totalRunTime += resultTestIterators.getRunTime();
		totalFailuresList.addAll(resultTestIterators.getFailures());

		System.out.println("***********************************************************");
		System.out.println("Sono stati eseguiti " + totalRunCount + " test");
		System.out.println("Sono falliti " + totalFailureCount + " test");
		System.out.println("Sono andati a buon fine " + totalSuccessCount + " test");
		System.out.println("Sono stati impegati " + totalRunTime + " millisecondi");
		
        	ListIterator<Failure> li = totalFailuresList.listIterator();
		if(!totalFailuresList.isEmpty()){
			System.out.println("\nTest falliti:");
		}
		while(li.hasNext()){
			System.out.println(li.next());
		}
	}

	//Scritto per rimozione warning di javadoc.
	/**Costruttore di default
	 */
	public TestRunner(){
	}
}
