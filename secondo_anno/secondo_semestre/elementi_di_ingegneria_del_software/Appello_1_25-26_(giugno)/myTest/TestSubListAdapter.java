package myTest;

import org.junit.*;
import static org.junit.Assert.*;

/**<p>Test case per il metodo {@code subList} della classe myAdapter.ListAdapter.
 * Verifica il corretto funzionamento del metodo, 
 * in linea con la documentazione ufficiale del metodo {@code subList()}
 * (J2SE 1.4.2).
 * <br>
 * Per eseguire i test è usato il framework JUnit nella versione 4.13., incluso nella cartella {@code ./junit}
 * (notare che il nome è scritto tutto in minuscolo).
 * <br>
 * Per eseguire le classi di test è necessario includere i file JUnit (con estensione {@code .jar}) nel proprio classpath,
 * altrimenti si può usare la flag {@code -cp} per indicare il classpath (da linea di comando).
 * </p>
 *
 * <p>
 * <b>Macro-categorie di test:</b>
 * <ul>
 * <li>Verifica effettiva implementazione dell'interfaccia {@code HList}.
 * <li>Verifica restituzione di una lista vuota per il caso {@code fromIndex = toIndex}.
 * <li>Verifica della presenza di solo gli elementi appropriati nella sottolista.
 * <li>Verifica di riflessione delle modifiche non strutturali tra lista e sottolista associata.
 * <li>Verifica possibilità di esecuzione di operazioni <i>ranged</i> tramite sottolista.
 * <li>Verifica lancio delle eccezione appropriate.
 * </ul>
 *
 * <p> Il test case si occupa di verificare che il metodo {@code subList} della classe {@code myAdapter.ListAdapter}
 * funzioni correttamente nei casi standard, nei casi limite e nei casi in cui deve lanciare l'eccezione {@code IndexOutOfBoundsException}.
 * Per questo verifica inizialmente che l'oggetto restituito tramite {@code subList} sia un'istanza dell'interfaccia {@code HList}.
 * <br>
 * I metodi sono stati scritti per essere un campione rappresentativo dei risultati possibili per ogni esecuzione.
 * Per quanto riguarda le operazioni <i>ranged</i> vengono effettuati due test, 
 * uno che non effettua modifiche strutturali e uno che le effettua 
 * (in particolare {@code toArray()} e {@code clear}).
 * <br>
 * Vengono eseguiti {@code 11} test.
 * </p>
 *
 * <p>
 * Nei vari test verranno usati gli attributi della classe: {@code la} ("ListAdapter") e {@code sl} ("SubList"),
 * il primo un oggetto di tipo {@code myAdapter.ListAdapter} e il secondo {@code HList} 
 * (in realtà, siccome {@code subList()} è stato implementato tramite una clase interna che estende {@code myAdapter.ListAdapter},
 * anche {@code sl} è {@code myAdapter.ListAdapter}).
 * <br>
 * Con il metodo {@code setup}, che viene eseguito prima di ogni test, 
 * viene inizializzata {@code la} a una lista di dieci interi (ognuno con il valore corrispondente alla propria posizione),
 * mentre {@code sl} è inizializzata come una sottolista vuota (tramite {@code la.subList(0,0)}).
 * </p>
 */

public class TestSubListAdapter{

	/**Lista di appoggio per la sotto-lista.
	 */
	private myAdapter.ListAdapter la;

	/**{@code myAdapter.subList} utilizzata per i test.
	 */
	private myAdapter.HList sl;

	/**Costruttore di default.
	 * Inizializza a {@code null} gli attributi della classe.
	 */
	public TestSubListAdapter(){
		la = null;
		sl = null;
	}

	/*---------------SETUP e TEARDOWN---------------*/

	/**Inizializzazione delle variabili, eseguita prima di ogni test method.
	 * Viene creata la lista {@code la} con il costruttore di default,
	 * quindi è inizialmente vuota, mentre {@code sl} è una sottolista vuota associata a {@code la}.
	 * <br>
	 * <b>Design:</b> Viene inizializzata la lista {@code la} con dieci elementi noti
	 * (interi corrispondenti alla posizione in cui si trovano), 
	 * così da poter essere utilizzata come lista di appoggio per i metodi di test della sottolista {@code sl}.
	 * <br>
	 * <b>Descrizione:</b> Viene inizializzata {@code la} con il costruttore di default,
	 * poi viene riempita con dieci interi, ognuno corrispondente alla posizione in cui si trova.
	 * Viene poi inizializzata {@code sl} tramite {@code la.subList(0,0)},
	 * quindi rappresenta una lista vuota associata a {@code la}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListAdaper.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} e {@code sl} sono state inizializzate.
	 * <br>
	 * <b>Risultati attesi:</b> {@code la} è una lista con dieci elementi (interi corrispondenti alle proprie posizioni) e {@code sl} è una lista vuota.
	 */
	@Before
	public void setup(){
		System.out.println("Setup");

		la = new myAdapter.ListAdapter();
		assertTrue("La lista la appena creata non è vuota;", la.isEmpty());

		for(int i = 0; i < 10; i++){
                        boolean res = la.add(i);
                        assertEquals("La lista non ha dimensione " + i + 1 + ";", i + 1, la.size());
                        assertTrue("La lista non è stata modificata;", res);
                        assertEquals("L'elemento inserito è errato;", i, la.get(i));
                }

		sl = la.subList(0,0);
		assertTrue("La lista sl appena creata non è vuota;", sl.isEmpty());
	}

	/**Assegnazione a {@code null} degli attributi, eseguita alla fine di ogni test method.
	 * <br>
	 * <b>Design:</b> Dopo ogni test le variabili vengono riportate allo stato in cui si trovavano subito dopo aver creato l'istanza della classe.
	 * <br>
	 * <b>Descrizione:</b> Vengono poste uguali a {@code null} sia {@code la} che {@code sl}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> {@code la} e {@code sl} sono {@code null}.
	 */
	@After
	public void teardown(){
		la = null;
		sl = null;
		System.out.println("Teardown");
	}

	/*---------------TEST PER ISTANZA DI HList e HCollection---------------*/

	/**Test per verificare che {@code la} e {@code sl} siano istanze di {@code HList} e {@code HCollection}.
         * <br>
         * <b>Design:</b> {@code HList} estende, quindi è, {@code HCollection},
         * quindi basterebbe verificare che siano istanze di {@code HList}.
         * Per scrupolo, viene comunque controllato che siano istanze di entrambe le interfacce.
         * <br>
         * <b>Descrizione:</b> Tramite l'operatore {@code instanceof} viene verificato che {@code la} e {@code sl}
         * siano istanze di {@code Hlist} e {@code HCollection}.
         * <br>
         * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListAdapter.
         * <br>
         * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
         * <br>
         * <b>Risultati attesi:</b> Viene confermato che {@code la}  e {@code sl} sono istanze di {@code HList} e {@code HCollection}.
         */
	@Test
        public void testIstanzaHList(){
                assertTrue("La lista la non è un'istanza di HCollection;", la instanceof myAdapter.HCollection);
                assertTrue("La lista la non è un'istanza di HList;", la instanceof myAdapter.HList);
                assertTrue("La lista sl non è un'istanza di HCollection;", sl instanceof myAdapter.HCollection);
                assertTrue("La lista sl non è un'istanza di HList;", sl instanceof myAdapter.HList);
        }

	/*---------------CORRETTEZZA INDICI E PRESENZA ELEMENTI---------------*/

	/**Test per verificare che {@code sl} sia la lista vuota se {@code fromIndex = toIndex}.
	 * <br>
	 * <b>Design:</b> Da documentazione, se i due indici forniti sono gli stessi,
	 * la lista restituita deve esser vuota.
	 * Il metodo testa quindi questo caso per tutti gli indici della lista {@code la}.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code subList(int fromIndex, int toIndex} 
	 * per ogni indice di {@code la}, verificando ogni volta che {@code sl} sia vuota.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza di TestSubListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Per ogni iterazione, {@code sl} sarà una lista vuota.
	 */
	@Test
	public void testSameIndex(){
		for(int i = 0; i < la.size(); i++){
			sl = la.subList(i, i);
			assertTrue("La lista non è vuota per l'iterazione " + i + ";", sl.isEmpty());
		}
	}

	/**Test per verificare che la lista {@code sl} contenga tutti gli elementi di {@code la} nell'intervallo {@code [fromIndex, toIndex)}.
	 * <br>
	 * <b>Design:</b> La lista restituita dal metodo {@code subList} deve contenere tutti e soli gli elementi
	 * nell'intervallo indicato dagli indici, nell'ordine originale,
	 * quindi prendendo tre casi campione viene verificato che gli elementi delle due liste corrispondano.
	 * Nel primo caso vale {@code fromIndex = 0} e {@code toIndex = la.size()/2}, 
	 * nel secondo caso vale {@code fromIndex = la.size()/2} e {@code toIndex = la.size()},
	 * nel terzo caso vale {@code fromIndex = la.size/3} e {@code toIndex = la.size()*2/3}.
	 * <br>
	 * <b>Descrizione:</b> Per ogni caso, viene effettuata la chiamata {@code la.subList(fromIndex, toIndex)},
	 * poi viene verificato che gli elementi siano gli stessi in {@code sl} e in {@code la} nelle posizioni corrette.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Per ogni iterazione, {@code sl} conterrà gli stessi elementi di {@code la} nell'intervallo {@code [fromIndex, toIndex)}.
	 */
	@Test
	public void testCheckSameElements(){
		//Primo caso
		int fromIndex = 0;
		int toIndex = la.size()/2;
		sl = la.subList(fromIndex, toIndex);
		for(int i = 0; i < toIndex - fromIndex; i++){
			assertEquals("Primo caso: Gli elementi in posizione " + i + " (con riferimento a sl) non sono uguali;", la.get(fromIndex + i), sl.get(i));
		}

		//Secondo caso
		fromIndex = toIndex;
		toIndex = la.size();
		sl = la.subList(fromIndex, toIndex);
		for(int i = 0; i < toIndex - fromIndex; i++){
			assertEquals("Secondo caso: Gli elementi in posizione " + i + " (con riferimento a sl) non sono uguali;", la.get(fromIndex + i), sl.get(i));
		}

		//Terzo caso
		fromIndex = toIndex/3;
		toIndex = fromIndex*2;
		sl = la.subList(fromIndex, toIndex);
		for(int i = 0; i < toIndex - fromIndex; i++){
			assertEquals("Terzo caso: Gli elementi in posizione " + i + " (con riferimento a sl) non sono uguali;", la.get(fromIndex + i), sl.get(i));
		}
	}

	/**Test per verificare che la lista {@code sl} contenga tutti gli elementi di {@code la} nell'intervallo {@code [0, la.size())}.
	 * <br>
	 * <b>Design:</b> La lista restituita dal metodo {@code subList} deve contenere tutti e soli gli elementi
	 * nell'intervallo indicato dagli indici, nell'ordine originale,
	 * quindi viene verificato che prendendo tutti gli elementi della lista, siano effettivamente tutti presenti.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code la.subList(0, la.size())}, 
	 * poi viene verificato che le due liste siano equivalenti
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Le due liste saranno equivalenti.
	 */
	@Test
	public void testCheckSameList(){
		sl = la.subList(0, la.size());
		assertEquals("Le due liste non sono uguali;", la, sl);
	}

	/*---------------CORRISPONDENZA MODIFICHE e UTILIZZO METODI CON SUBLIST---------------*/

	/**Test per verificare che le modifiche (non strutturali) a {@code la} si riflettano su {@code sl}.
	 * <br>
	 * <b>Design:</b> {@code sl} è una porzione di {@code la} e tutti i cambiamenti non strutturali si devono riflettere (in maniera reciproca),
	 * quindi ora viene modificata {@code la} e si verifica che le modifiche siano avvenute anche su {@code sl}.
	 * <br>
	 * <b>Descrizione:</b> Viene assegnato a {@code sl} il risultato della chiamata {@code la.subList(0, la.size())},
	 * viene sostituito ogni elemento di {@code la} con la stringa {@code "Nuovo elemento"},
	 * poi si verifica che la modifica sia avvenuta anche su {@code sl}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della clase TestSubListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> I cambiamenti su {@code la} saranno stati riflessi su {@code sl}.
	 */
	@Test
	public void testReflectChangesFromLaToSl(){
		sl = la.subList(0, la.size());
		String el = "Nuovo elemento";
		for(int i = 0; i < la.size(); i++){
			la.set(i, el);
			assertEquals("L'elemento in posizione " + i + " non è stato modificato correttamente;", la.get(i), sl.get(i));
		}
	}

	/**Test per verificare che le modifiche (non strutturali) a {@code sl} si riflettano su {@code la}.
	 * <br>
	 * <b>Design:</b> {@code sl} è una porzione di {@code la} e tutti i cambiamenti non strutturali si devono riflettere (in maniera reciproca),
	 * quindi ora viene modificata {@code sl} e viene verificato che le modifiche siano avvenute anche su {@code la}.
	 * <br>
	 * <b>Descrizione:</b> Viene assegnato a {@code sl} il risultato della chiamata {@code la.subList(0, la.size()/2)},
	 * viene sostituito ogni elemento di {@code la} con la stringa {@code "Nuovo elemento"},
	 * poi si verifica che la modifica sia avvenuta anche su {@code la}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della clase TestSubListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> I cambiamenti su {@code sl} saranno stati riflessi su {@code la}.
	 */
	@Test
	public void testReflectChangesFromSlToLa(){
		sl = la.subList(0, la.size());
		String el = "Nuovo elemento";
		for(int i = 0; i < sl.size(); i++){
			sl.set(i, el);
			assertEquals("L'elemento in posizione " + i + " non è stato modificato correttamente;", la.get(i), sl.get(i));
		}
	}

	/**Viene testata l'operazione con range {@code toArray} su {@code sl} perché si rifletta su {@code la}.
	 * <br>
	 * <b>Design:</b> Siccome {@code sl} è una porzione della lista {@code la},
	 * invocando i metodi di {@code myAdapter.ListAdapter} si possono effettuare operazioni su intervalli. 
	 * Per questo motivo vengono testati alcuni metodi scelti a campione, in questo caso {@code toArray}.
	 * <br>
	 * <b>Descrizione:</b> Viene assegnato a {@code sl} il risultato della chiamata a {@code la.subList(la.size()/3, la.size()*2/3)},
	 * poi viene invocato il metodo {@code toArray} su {@code sl} 
	 * e viene verificato che l'array restituito contenga tutti gli elementi di {@code sl}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListIterator.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> L'array creato conterrà tutti gli elementi di {@code sl}.
	 */
	@Test
	public void testRangedToArray(){
		sl = la.subList(la.size() / 3, la.size() * 2 / 3);
		Object[] array = sl.toArray();
		
		for(int i = 0; i < sl.size(); i++){
			assertEquals("Gli elementi in posizione " + i + " non sono uguali;", sl.get(i), array[i]);
		}
	}

	/**Viene testata l'operazione con range {@code clear} su {@code sl} perché si rifletta su {@code la}.
	 * <br>
	 * <b>Design:</b> Siccome {@code sl} è una porzione della lista {@code la},
	 * invocando i metodi di {@code myAdapter.ListAdapter} si possono effettuare operazioni su intervalli. 
	 * Per questo motivo vengono testati alcuni metodi scelti a campione, in questo caso {@code clear};
	 * è un metodo che modifica strutturalmente {@code la}, 
	 * quindi il comportamento di {@code sl} in seguito non è determinato.
	 * <br>
	 * <b>Descrizione:</b> Viene assegnato a {@code sl} il risultato della chiamata a {@code la.subList(la.size()/3, la.size()*2/3)},
	 * poi viene invocato il metodo {@code clear} su {@code sl} 
	 * infine viene verificato che gli elementi presenti in precedenza non ci siano più (usando una lista ausiliaria).
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListIterator.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Gli elementi di {@code la} nell'intervallo {@code [fromIndex, toIndex)} non saranno più presenti.
	 */
	@Test
	public void testRangedClear(){
		sl = la.subList(la.size() / 3, la.size() * 2 / 3);
		myAdapter.ListAdapter ausList = new myAdapter.ListAdapter();
		for(int i = 0; i < sl.size(); i++){
			ausList.add(sl.get(i));
		}

		sl.clear();
		
		for(int i = 0; i < ausList.size(); i++){
			assertFalse("La lista la contiene un elemento che sarebbe dovuto essere eliminato;", la.contains(ausList.get(i)));
		}
	}

	/*---------------ECCEZIONI---------------*/

	/**Test per verificare che il metodo lanci correttamente l'eccezione {@code java.lang.IndexOutOfBounds} per {@code fromIndex < 0}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione per {@code fromIndex < 0 || toIndex > size() || fromIndex > toIndex};
	 * viene testato il primo caso.
	 * <br>
	 * <b>Descrizione:</b> Effettuata la chiamata {@code la.subList(-1, la.size())}, quindi con un numero negativo.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionNegativeFromIndex(){
		la.subList(-1, la.size());
	}

	/**Test per verificare che il metodo lanci correttamente l'eccezione {@code java.lang.IndexOutOfBounds} per {@code toIndex > size()}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione per {@code fromIndex < 0 || toIndex > size() || fromIndex > toIndex};
	 * viene testato il secondo caso.
	 * <br>
	 * <b>Descrizione:</b> Effettuata la chiamata {@code la.subList(0, la.size() + 1)},
	 * quindi con un numero maggiore della dimensione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionGreaterThanSizeToIndex(){
		la.subList(0, la.size() + 1);
	}

	/**Test per verificare che il metodo lanci correttamente l'eccezione {@code java.lang.IndexOutOfBounds} per {@code fromIndex > toIndex}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione per {@code fromIndex < 0 || toIndex > size() || fromIndex > toIndex};
	 * viene testato il terzo caso.
	 * <br>
	 * <b>Descrizione:</b> Effettuata la chiamata {@code la.subList(la.size(), 0)},
	 * quindi con {@code fromIndex > toIndex}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionGreaterFromIndexGreaterThanToIndex(){
		la.subList(la.size(), 0);
	}
}
