package myTest;

import org.junit.*;
import static org.junit.Assert.*;

/**<p>Test case per la classe myAdapter.ListAdapter.
 * Verifica il corretto funzionamento della classe, 
 * in linea con la documentazione ufficiale dell'interfaccia List
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
 * <li>Verifica corretto funzionamento dei costruttori.
 * <li>Verifica di aggiunta elementi.
 * <li>Verifica presenza di elementi dopo la loro aggiunta.
 * <li>Verifica metodi {@code isEmpty} e {@code clear}
 * <li>Verifica rimozione elementi.
 * <li>Verifica mancanza di elementi dopo la loro rimozione.
 * <li>Verifica metodo {@code retainAll}.
 * <li>Verifica uguaglianza liste.
 * <li>Verifica corrispondenza hash code tra liste uguali.
 * <li>Verifica di funzionamento metodi {@code get} e {@code set}.
 * <li>Verifica metodi {@code indexOf} e {@code lastIndexOf}.
 * <li>Verifica metodi {@code toArray()} e {@code toArray(Object[] a)}
 * <li>Verifica lancio delle eccezioni appropriate da parte dei metodi.
 * </ul>
 * Sono presenti dei metodi aulisiari ai test.
 *
 * <p>
 * Il test case si occupa di verificare che i metodi della classe {@code myAdapter.ListAdapter} 
 * funzionino correttamente nei casi standard, nei casi limite e nei casi in cui devono lanciare varie eccezioni.
 * Per questo verifica inizialmente che gli oggetti creati dalla classe {@code myAdapter.ListAdapter} siano effettivamente implementazioni dell'interfaccia {@code HList}
 * (e di conseguenza di {@code HCollection}, siccome {@code HList} estende {@code HCollection}),
 * poi verifica il corretto funzionamento dei costruttori della classe,
 * infine verifica che ogni metodo risponda correttamente in base alle differenti precondizioni stabilite.
 * <br>
 * Le precondizioni sono scelte a campione, in modo che rappresentino il comportamento dei metodi in una
 * famiglia più grande di condizioni (non rappresentabili nella loro interezza),
 * per questo sono rappresentative del maggior numero di condizioni possibili.
 * <br>
 * I metodi sono stati scritti in modo tale che ogni risultato, per quanto possibile, sia rappresentato e testato almeno una volta, ma <i><b>non</b></i> viene testato il funzionamento del metodo {@code subList}, 
 * in quanto viene testato da {@code TestSubListAdapter}.
 * <br>
 * Vengono eseguiti {@code 67} test.
 * </p>
 *
 * <p>
 * Nei vari test verranno usati gli attributi della classe {@code la} ("ListAdapter") e {@code ausList} ("Auxiliary List"), 
 * due oggetti di tipo {@code myAdapter.ListAdapter} che vengono inizializzati a liste vuote
 * (tramite costruttore di default) prima dell'esecuzione di ogni test, 
 * mentre vengono poste uguali a {@code null} alla fine di ogni test.
 * <br>
 * I due metodi {@code addElementsToListLa} e {@code addTenElementsToListLa} sono ausiliari;
 * aggiungono in coda a {@code la} rispettivamente due elementi (entrambi -1) e dieci elementi
 * (interi corrispondenti alla posizione in cui vengono aggiunti).
 * Sono usati per aggiungere elementi alle liste in maniera nota e sistematica nei test.
 * </p>
 */
public class TestListAdapter{

	/**Lista che verrà inizializzata prima di ogni test. Viene usata per fare i test della classe myAdapter.ListAdapter.
	 */
	private myAdapter.ListAdapter la;

	/**Lista ausiliaria per alcuni metodi.
	 */
	private myAdapter.ListAdapter ausList;

	/**Costruttore di default.
	 * Inizializza a {@code null} gli attributi della classe.
	 */
	public TestListAdapter(){
		la = null;
		ausList = null;
	}

	/*---------------SETUP e TEARDOWN---------------*/

	/**Inizializzazione delle variabili {@code la} e {@code ausList}, eseguita prima di ogni test method.
	 * Le liste vengono create con il costruttore di default, perciò sono vuote.
	 * Se non sono vuote, il setup fallisce.
	 * <br>
	 * <b>Design:</b> Siccome il metodo viene eseguito prima di ogni metodo di test,
	 * ogni volta viene invocato il costruttore di default di {@code ListAdapter},
	 * che quindi viene testato nel setup stesso.
	 * Il test controlla che anche il metodo {@code size} funzioni correttamente (per questa esecuzione).
	 * <br>
	 * <b>Descrizione:</b> Il metodo inizializza le liste classe tramite il suo costruttore di default,
	 * che crea una lista vuota. Viene poi effettivamente verificato che tale lista sia vuota,
	 * se non dovesse esserlo, il test fallirebbe.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} e {@code ausList} sono state inizializzate.
	 * <br>
	 * <b>Risultati attesi:</b> {@code la} e {@code ausList} sono liste vuote.
	 */
	@Before
	public void setup(){
		System.out.println("Setup");
		la = new myAdapter.ListAdapter();
		assertEquals("La lista appena creata non è vuota;", 0, la.size());
		ausList = new myAdapter.ListAdapter();
		assertEquals("La lista ausiliaria appena creata non è vuota;", 0, ausList.size());
	}

	/**Assegnazione a {@code null} degli attributi, eseguita alla fine di ogni test method.
         * <br>
         * <b>Design:</b> Dopo ogni test le variabili vengono riportate allo stato in cui si trovavano subito dopo aver creato l'istanza della classe.
         * <br>
         * <b>Descrizione:</b> Vengono poste uguali a {@code null} sia {@code la} che {@code ausList}.
         * <br>
         * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
         * <br>
         * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
         * <br>
         * <b>Risultati attesi:</b> {@code la} e {@code ausList} sono {@code null}.
         */
	@After
	public void tearDown(){
		System.out.println("Teardown");
		la = null;
		ausList = null;
	}

	/*---------------TEST COSTRUTTORI---------------*/

	/**Test del costruttore {@code ListAdapter(int)}.
	 * Il costruttore crea una lista <i>vuota</i> di <i>capacità iniziale</i> specificata.
	 * <br>
	 * <b>Design:</b> Viene effettuato un test per il corretto funzionamento del costruttore {@code ListAdapter(int)}
	 * fornendo un intero {@code > 0}, che dovrebbe essere accettato senza problemi.
	 * Il test controlla che anche il metodo {@code size} funzioni correttamente (per questa esecuzione).
	 * <br>
	 * <b>Descrizione:</b> Viene inizializzata {@code la} usando il costruttore che permette di fornire una capacità iniziale,
	 * che creerà comunque una lista vuota; quindi verifica che la sua {@code size} sia {@code 0}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} è stata inizializzata.
	 * <br>
	 * <b>Risultati attesi:</b> {@code la} è una lista vuota (di capacità iniziale fornita).
	 */
	@Test
	public void testConstructorCapacity(){
		la = new myAdapter.ListAdapter(10);
		assertEquals("La lista appena creata non è vuota;", 0, la.size());
	}


	/**Test del costruttore {@code ListAdapter(int, int)}.
	 * Il costruttore crea una lista <i>vuota</i> di <i>capacità iniziale</i> e incremento della capacità specificate.
	 * <br>
	 * <b>Design:</b> Viene effettuato un test per il corretto funzionamento del costruttore {@code ListAdapter(int, int)}
	 * fornendo due interi {@code > 0}, che dovrebbero essere accettati senza problemi.
	 * Il test controlla che anche il metodo {@code size} funzioni correttamente (per questa esecuzione).
	 * <br>
	 * <b>Descrizione:</b> Viene inizializzata {@code la} usando il costruttore che permette di fornire una capacità iniziale e incremento di capacità,
	 * che creerà comunque una lista vuota; quindi verifica che la sua {@code size} sia {@code 0}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} è stata inizializzata.
	 * <br>
	 * <b>Risultati attesi:</b> {@code la} è una lista vuota (di capacità iniziale e incremento di capacità fornite).
	 */
	@Test
	public void testConstructorCapacityAndIncrement(){
		la = new myAdapter.ListAdapter(10, 5);
		assertEquals("La lista appena creata non è vuota;", 0, la.size());
	}


	/*---------------TEST PER ISTANZA DI HList E HCollection---------------*/

	/**Test per verificare che {@code la} e {@code ausList} siano istanze di {@code HList} e {@code HCollection}.
	 * <br>
	 * <b>Design:</b> {@code HList} estende, quindi è, {@code HCollection},
	 * quindi basterebbe verificare che siano istanze di {@code HList}.
	 * Per scrupolo, viene comunque controllato che siano istanze di entrambe le interfacce.
	 * <br>
	 * <b>Descrizione:</b> Tramite l'operatore {@code instanceof} viene verificato che {@code la} e {@code ausList}
	 * siano istanze di {@code Hlist} e {@code HCollection}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Viene confermato che {@code la}  e {@code ausList} sono istanze di {@code HList} e {@code HCollection}.
	 */
	@Test
	public void testIstanzaHList(){
		assertTrue("La lista la non è un'istanza di HCollection;", la instanceof myAdapter.HCollection);
		assertTrue("La lista la non è un'istanza di HList;", la instanceof myAdapter.HList);
		assertTrue("La lista ausList non è un'istanza di HCollection;", ausList instanceof myAdapter.HCollection);
		assertTrue("La lista ausList non è un'istanza di HList;", ausList instanceof myAdapter.HList);
	}

	/*---------------TEST SU AGGIUNTA DI ELEMENTI, LORO PRESENZA e DIMENSIONE LISTA---------------*/

	/**Test per verificare che si possa aggiungere un elemento.
	 * <br>
	 * <b>Design:</b> Partendo da una lista vuota, viene aggiunto un singolo elemento, 
	 * perciò la lista è stata modificata.
	 * <br>
	 * <b>Descrizione:</b> Viene aggiunto un elemento (in questo caso una stringa) tramite il metodo {@code add(Object o)},
	 * quindi viene verificato che venga correttamente restituito {@code true} siccome la lista è stata modificata.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> La lista contiene l'elemento specificato.
	 * <br>
	 * <b>Risultati attesi:</b> La lista avrà dimensione 1 e {@code add(Object o)} restituirà {@code true}.
	 */
	@Test
	public void testAddElement(){
		String s = "test";
		boolean res = la.add(s);
		assertEquals("La lista non ha dimensione 1;", 1, la.size());
		assertTrue("La lista non è stata modificata", res);
	}

	/**Test per verificare l'aggiunta di un numero arbitrariamente grande di elementi.
	 * <br>
	 * <b>Design:</b> Partendo da una lista vuota, viene aggiunto un grande numero di elementi,
	 * uno per volta, vertificando ogni volta che la lista venga modificata (e che la dimensione
	 * venga correttamente modificata di conseguenza).
	 * <br>
	 * <b>Descrizione:</b> Viene aggiunto un elemento (in questo caso una stringa) tramite il metodo {@code add(Object o)},
	 * quindi viene verificato che venga correttamente restituito {@code true} siccome la lista è stata modificata; questo per {@code Integer.MAX_VALUE/2048} volte (un numero arbitrariamente grande).

	 * <br>
	 * <b>Precondizioni:</b> Deve esistere un oggetto da inserire.
	 * <br>
	 * <b>Postcondizioni:</b> La lista conterrà {@code Integer.MAX_VALUE/2e15} volte l'elemento specificato.
	 * <br>
	 * <b>Risultati attesi:</b> La lista avrà dimensione {@code Integer.MAX_VALUE/2e15} e il metodo {@code add(HCollection c)} avrà restituito {@code true} a ogni iterazione.
	 */
	@Test
	public void testAddMultipleElements(){
		String s = "test";
		int div = (int) 2e15; //numero arbitrario
		for(int i = 0; i < Integer.MAX_VALUE/div; i++){
		boolean res = la.add(s);
		assertEquals("La lista non ha dimensione " + (i + 1) + ";", i + 1, la.size());
		assertTrue("La lista non è stata modificata", res);
		}
	}

	/**Test per verificare l'aggiunta di un oggetto HCollection tramite {@code addAll(HCollection c)}.
	 * <br>
	 * <b>Design:</b> Conoscendo a prescindere il contenuto iniziale delle due liste (sono vuote)
	 * e gli elementi da inserire (scelti manualmente), si può verificare che per quest'istanza vengano
	 * correttamente inseriti gli elementi nella lista.
	 * Siccome ListAdapter implementa HList, è anche un'istanza di HCollection.
	 * Viene anche verificato che le dimensioni delle liste siano corrette.
	 * <br>
	 * <b>Descrizione:</b> Viene usata la lista ausiliaria (che implementa HList, quindi HCollection) con un numero arbitrario di elementi,
	 * viene poi aggiunta a {@code la} tramite il metodo {@code addAll(HCollection c)}.
	 * Tramite metodo {@code containsAll(HCollection c)} viene verificato che siano contenuti effettivamente tutti gli elementi.

	 * <br>
	 * <b>Precondizioni:</b> Deve esistere la lista ausiliaria da aggiungere (e non essere {@code null}).
	 * <br>
	 * <b>Postcondizioni:</b> Le due liste contengono gli stessi elementi.
	 * <br>
	 * <b>Risultati attesi:</b> Vengono aggiunti tutti gli elementi della lista ausiliaria alla lista principale.
	 */
	@Test
	public void testAddCollection(){
		int dim = 10; //Numero (arbitrario) di elementi da inserire
		boolean res = false;
		ausList = new myAdapter.ListAdapter();
		for(int i = 0; i < dim; i++){
			res = ausList.add(i);
			assertTrue("La lista ausiliaria non è stata modificata", res);
			assertEquals("La lista ausiliaria non ha dimensione " + (i + 1) + ";" , i + 1, ausList.size());
		}

		res = la.addAll(ausList);
		assertEquals("La lista non ha dimensione " + dim, dim, la.size());
		assertTrue("La lista non è stata modificata", res);
		res = la.containsAll(ausList);
		assertTrue("La lista non contiene tutti gli elementi", res);
	}

	/**Test per verificare l'aggiunta di un oggetto nella lista <i>vuota</i> tramite il metodo {@code add(int index, Object element)}.
	 * <br>
	 * <b>Design:</b> Verifica del caso limite per cui un elemento viene inserito in una lista vuota fornendo come indice la sua dimensione (ovvero 0, l'unico valore accettabile).
	 * <br>
	 * <b>Descrizione:</b> Viene aggiunto un elemento tramite il metodo {@code add(int index, Object el)}, fornendo come indice la dimensione della lista.
	 * <br>
	 * <b>Precondizioni:</b> La lista a cui verrà aggiunto l'elemento deve essere vuota.
	 * <br>
	 * <b>Postcondizioni:</b> La lista conterrà l'elemento.
	 * <br>
	 * <b>Risultati attesi:</b> L'elemento verrà aggiunto nella lista senza lanciare eccezioni.
	 */
	 @Test
	 public void testAddIndexEmptyList(){
		 Object el = "Elemento";
		 la.add(la.size(), el);
		 assertEquals("La lista non ha dimensione 1;", 1, la.size());
		 assertEquals("L'elemento non si trova nella posizione specificata;", el, la.get(0));
	 }

	/**Test per verificare l'aggiunta di un oggetto all'inizio della lista tramite il metodo {@code add(int index, Object element)}.
	 * <br>
	 * <b>Design:</b> Aggiungendo un elemento in una posizione specifica della lista si può testare il corretto funzionamento del metodo {@code add(int index, Object element)}, sfruttando (e quindi testando) anche il metodo {@code get(int index)}.
	 * <br>
	 * <b>Descrizione:</b> Viene aggiunto un elemento in cima a una lista non vuota, verificando poi che sia stata modificata, che la dimensione sia corretta e che l'elemento sia nella posizione corretta.
	 * <br>
	 * <b>Precondizioni:</b> La lista a cui verrà aggiunto l'elemento deve essere non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> La lista avrà l'elemento in posizione corretta.
	 * <br>
	 * <b>Risultati attesi:</b> L'elemento verrà aggiunto nella lista nella posizione corretta.
	 */
	 @Test
	 public void testAddIndexBeginning(){
		addElementsToListLa();

		int previousSize = la.size();
		int index = 0;
		Object el = "Elemento";
		la.add(index, el);
		assertEquals("La lista non ha dimensione " + (previousSize + 1) + ";", (previousSize + 1), la.size());
		assertEquals("L'elemento non si trova nella posizione specificata;", el, la.get(index));
	 }

	/**Test per verificare l'aggiunta di un oggetto nel mezzo della lista tramite il metodo {@code add(int index, Object element)}.
	 * <br>
	 * <b>Design:</b> Aggiungendo un elemento in una posizione specifica della lista si può testare il corretto funzionamento del metodo {@code add(int index, Object element)}, sfruttando (e quindi testando) anche il metodo {@code get(int index)}.
	 * <br>
	 * <b>Descrizione:</b> Viene aggiunto un elemento in mezzo a una lista non vuota, verificando poi che sia stata modificata, che la dimensione sia corretta e che l'elemento sia nella posizione corretta.
	 * <br>
	 * <b>Precondizioni:</b> La lista a cui verrà aggiunto l'elemento deve essere non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> La lista avrà l'elemento in posizione corretta.
	 * <br>
	 * <b>Risultati attesi:</b> L'elemento verrà aggiunto nella lista nella posizione corretta.
	 */
	 @Test
	 public void testAddIndexMiddle(){
		addElementsToListLa();

		int previousSize = la.size();
		int index = 1; //indice dell'elemento di mezzo
		Object el = "Elemento";
		la.add(index, el);
		assertEquals("La lista non ha dimensione " + (previousSize + 1) + ";", (previousSize + 1), la.size());
		assertEquals("L'elemento non si trova nella posizione specificata;", el, la.get(index));
	 }

	/**Test per verificare l'aggiunta di un oggetto alla fine della lista tramite il metodo {@code add(int index, Object element)}.
	 * <br>
	 * <b>Design:</b> Aggiungendo un elemento in una posizione specifica della lista si può testare il corretto funzionamento del metodo {@code add(int index, Object element)}, sfruttando (e quindi testando) anche il metodo {@code get(int index)}.
	 * <br>
	 * <b>Descrizione:</b> Viene aggiunto un elemento alla fine della lista non vuota, verificando poi che sia stata modificata, che la dimensione sia corretta e che l'elemento sia nella posizione corretta.
	 * <br>
	 * <b>Precondizioni:</b> La lista a cui verrà aggiunto l'elemento deve essere non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> La lista avrà l'elemento in posizione corretta.
	 * <br>
	 * <b>Risultati attesi:</b> L'elemento verrà aggiunto nella lista nella posizione corretta.
	 */
	 @Test
	 public void testAddIndexEnd(){
		addElementsToListLa();

		int previousSize = la.size();
		int index = la.size();
		Object el = "Elemento";
		la.add(index, el);
		assertEquals("La lista non ha dimensione " + (previousSize + 1) + ";", (previousSize + 1), la.size());
		assertEquals("L'elemento non si trova nella posizione specificata;", el, la.get(index));
	 }

	/**Test per verificare l'aggiunta di un oggetto HCollection tramite {@code addAll(int index, HCollection c)}, inserendolo all'inizio.
	 * <br>
	 * <b>Design:</b> Conoscendo a prescindere il contenuto iniziale delle due liste (sono vuote)
	 * e gli elementi da inserire (scelti manualmente), si può verificare che per quest'istanza vengano
	 * correttamente inseriti gli elementi nella lista.
	 * Per controllare il corretto posizionamento degli elementi, si usa il metodo {@code containsAll} sulla sottolista corrispondente alla posizione fornita.
	 * Si usa un ListAdapter ausiliario in quanto implementa HList, che estende HCollection.
	 * <br>
	 * <b>Descrizione:</b> Viene usata la lista ausiliaria e riempita con un numero arbitrario di elementi,
	 * poi tramite il metodo {@code addAll(int index, HCollection c)} vengono aggiunti all'inizio della lista {@code la}.
	 * Viene creata una sottolista tramite il metodo {@code subList} corrispondente alla porzione della lista con i nuovi elementi, poi tramite il metodo {@code containsAll} si verifica che siano stati inseriti tutti gli elementi.
	 * <br>
	 * <b>Precondizioni:</b> La lista ausiliaria deve essere creata e contenere degli elementi.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} contiene gli elementi specificati a partire dalla posizione specificata.
	 * <br>
	 * <b>Risultati attesi:</b> Vengono aggiunti gli elementi della lista ausiliaria all'inizio di {@code la}.
	 */
	@Test
	public void testAddCollectionIndexBeginning(){
		boolean res = false;
		int dim = 5; //dimensione lista ausiliaria scelta in modo arbitrario

		ausList = new myAdapter.ListAdapter();
		for(int i = 0; i < dim; i++){ 
			res = ausList.add(i); //elemento arbitrario
			assertTrue("La lista ausiliaria non è stata modificata", res);
			assertEquals("La lista ausiliaria non ha dimensione " + (i + 1) + ";" , i + 1, ausList.size());
		}

		addElementsToListLa();

		int prevSize = la.size();
		int from = 0;
		res = la.addAll(from, ausList);
		assertEquals("La lista non ha dimensione " + (dim + prevSize) + ";", dim + prevSize, la.size());
		assertTrue("La lista non è stata modificata", res);

		myAdapter.ListAdapter sList = (myAdapter.ListAdapter) la.subList(from, from + dim);
		assertTrue("Gli elementi non sono stati inseriti nella posizione corretta;", sList.containsAll(ausList));
	}

	/**Test per verificare l'aggiunta di un oggetto HCollection tramite {@code addAll(int index, HCollection c)}, inserendolo nel mezzo.
	 * <br>
	 * <b>Design:</b> Conoscendo a prescindere il contenuto iniziale delle due liste (sono vuote)
	 * e gli elementi da inserire (scelti manualmente), si può verificare che per quest'istanza vengano
	 * correttamente inseriti gli elementi nella lista.
	 * Per controllare il corretto posizionamento degli elementi, si usa il metodo {@code containsAll} sulla sottolista corrispondente alla posizione fornita.
	 * Si usa un ListAdapter ausiliario in quanto implementa HList, che estende HCollection.
	 * <br>
	 * <b>Descrizione:</b> Viene usata la lista ausiliaria e riempita con un numero arbitrario di elementi,
	 * poi tramite il metodo {@code addAll(int index, HCollection c)} vengono aggiunti nel mezzo della lista {@code la}.
	 * Viene creata una sottolista tramite il metodo {@code subList} corrispondente alla porzione della lista con i nuovi elementi, poi tramite il metodo {@code containsAll} si verifica che siano stati inseriti tutti gli elementi.
	 * <br>
	 * <b>Precondizioni:</b> La lista ausiliaria deve essere creata e contenere degli elementi.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} contiene gli elementi specificati a partire dalla posizione specificata.
	 * <br>
	 * <b>Risultati attesi:</b> Vengono aggiunti gli elementi della lista ausiliaria nel mezzo di {@code la}.
	 */
	@Test
	public void testAddCollectionIndexMiddle(){
		boolean res = false;
		int dim = 5; //dimensione lista ausiliaria scelta in modo arbitrario

		ausList = new myAdapter.ListAdapter();
		for(int i = 0; i < dim; i++){ 
			res = ausList.add(1); //elemento arbitrario
			assertTrue("La lista ausiliaria non è stata modificata", res);
			assertEquals("La lista ausiliaria non ha dimensione " + (i + 1) + ";" , i + 1, ausList.size());
		}

		addElementsToListLa();

		int prevSize = la.size();
		int from = 1; //indice a metà della lista
		res = la.addAll(from, ausList);
		assertEquals("La lista non ha dimensione " + (dim + prevSize) + ";", dim + prevSize, la.size());
		assertTrue("La lista non è stata modificata", res);

		myAdapter.ListAdapter sList = (myAdapter.ListAdapter) la.subList(from, from + dim);
		assertTrue("Gli elementi non sono stati inseriti nella posizione corretta;", sList.containsAll(ausList));
	}

	/**Test per verificare l'aggiunta di un oggetto HCollection tramite {@code addAll(int index, HCollection c)}, inserendolo alla fine.
	 * <br>
	 * <b>Design:</b> Conoscendo a prescindere il contenuto iniziale delle due liste (sono vuote)
	 * e gli elementi da inserire (scelti manualmente), si può verificare che per quest'istanza vengano
	 * correttamente inseriti gli elementi nella lista.
	 * Per controllare il corretto posizionamento degli elementi, si usa il metodo {@code containsAll} sulla sottolista corrispondente alla posizione fornita.
	 * Si usa un ListAdapter ausiliario in quanto implementa HList, che estende HCollection.
	 * <br>
	 * <b>Descrizione:</b> Viene usata la lista ausiliaria e riempita con un numero arbitrario di elementi,
	 * poi tramite il metodo {@code addAll(int index, HCollection c)} vengono aggiunti alla fine della lista {@code la}.
	 * Viene creata una sottolista tramite il metodo {@code subList} corrispondente alla porzione della lista con i nuovi elementi, poi tramite il metodo {@code containsAll} si verifica che siano stati inseriti tutti gli elementi.
	 * <br>
	 * <b>Precondizioni:</b> La lista ausiliaria deve essere creata e contenere degli elementi.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} contiene gli elementi specificati a partire dalla posizione specificata.
	 * <br>
	 * <b>Risultati attesi:</b> Vengono aggiunti gli elementi della lista ausiliaria alla fine di {@code la}.
	 */
	@Test
	public void testAddCollectionIndexEnd(){
		boolean res = false;
		int dim = 5; //dimensione lista ausiliaria scelta in modo arbitrario

		ausList = new myAdapter.ListAdapter();
		for(int i = 0; i < dim; i++){ 
			res = ausList.add(1); //elemento arbitrario
			assertTrue("La lista ausiliaria non è stata modificata", res);
			assertEquals("La lista ausiliaria non ha dimensione " + (i + 1) + ";" , i + 1, ausList.size());
		}

		addElementsToListLa();
		
		int prevSize = la.size();
		int from = prevSize;
		res = la.addAll(from, ausList);
		assertEquals("La lista non ha dimensione " + (dim + prevSize) + ";", dim + prevSize, la.size());
		assertTrue("La lista non è stata modificata", res);

		myAdapter.ListAdapter sList = (myAdapter.ListAdapter) la.subList(from, from + dim);
		assertTrue("Gli elementi non sono stati inseriti nella posizione corretta;", sList.containsAll(ausList));
	}

	/**Test per verificare la presenza di un elemento in seguito al suo inserimento.
	 * <br>
	 * <b>Design:</b> Partendo dalla lista vuota, viene aggiunto un solo elemento, quindi deve essere contenuto.
	 * <br>
	 * <b>Descrizione:</b> Partendo dalla lista vuota, viene aggiunto un elemento, 
	 * poi ne viene verificata la presenza tramite il metodo {@code contains(Object o)}.
	 * <br>
	 * <b>Precondizioni:</b> La lista deve contenere l'elemento.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono cambiamenti alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> L'elemento verrà trovato correttamente da {@code contains}.
	 */
	@Test
	public void testContainsElement(){
		String s = "Contenuto";
		la.add(s);
		assertEquals("La lista non ha dimensione 1", 1, la.size());
		assertTrue("La lista non contiene l'elemento aggiunto", la.contains(s));
	}

	/*---------------TEST SU RIMOZIONE DI ELEMENTI, LORO ASSENZA e LISTE VUOTE---------------*/

	/**Test per verificare che {@code isEmpty} restituisca {@code true} per una lista vuota.
	 * <br>
	 * <b>Design:</b> Viene testato che il metodo funzioni correttamente con la lista {@code la}, inizializzata a vuota da {@code setup}.
	 * <br>
	 * <b>Descrizione:</b> Viene chiamato il metodo {@code isEmpty} su {@code la}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code isEmpty} restituirà {@code true}.
	 */
	@Test
	public void testIsEmptyTrue(){
		assertTrue("La lista non è vuota;", la.isEmpty());
	}

	/**Test per verificare che {@code isEmpty} restituisca {@code false} per una lista non vuota.
	 * <br>
	 * <b>Design:</b> Viene testato che il metodo funzioni correttamente con la lista {@code la}, riempita tramite entrambi i metodi ausiliari.
	 * <br>
	 * <b>Descrizione:</b> Viene chiamato il metodo {@code isEmpty} su {@code la}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code isEmpty} restituirà {@code false}.
	 */
	@Test
	public void testIsEmptyFalse(){
		addElementsToListLa();
		assertFalse("La lista è vuota;", la.isEmpty());
		addTenElementsToListLa();
		assertFalse("La lista è vuota;", la.isEmpty());
	}

	/**Test per verificare che {@code isEmpty} funzioni correttamente dopo aver svuotato la lista tramite il metodo {@code clear}.
	 * <br>
	 * <b>Design:</b> Viene verificato il corretto funzionamento del metodo {@code isEmpty} in seguito alla manipolazione della lista.
	 * Usare il metodo {@code clear} per svuotarla, permette di verificare anche il suo funzionamento.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista tramite il metodo ausiliario {@code addTenElementsToListLa()}, svuotata tramite il metodo {@code clear}, per poi verificare che sia vuota.
	 * <br>
	 * <b>Precondizioni:</b> La lista deve essere riempita e svuotata.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code isEmpty} verificherà correttamente che la lista sia stata svuotata da {@code clear}.
	 */
	@Test
	public void testIsEmptyCleared(){
		addElementsToListLa();
		la.clear();
		assertEquals("La lista non ha dimensione 0;", 0, la.size());
		assertTrue("La lista non è vuota;", la.isEmpty());
	}

	/**Test per verificare che {@code isEmpty} funzioni dopo aver svuotato la lista con {@code remove(int index)} ripetuti.
	 * <br>
	 * <b>Design:</b> Manipolando la lista con {@code remove(int index)} e verificando il risultato con {@code isEmpty},
	 * si possono verificare entrambi i metodi con un solo test.
	 * <br>
	 * <b>Descrizione:</b> Vengono aggiunti degli elementi alla lista tramite metodi ausiliari,
	 * poi viene iterata la rimozione degli elementi (un elemento alla volta).
	 * (Il metodo funzionerebbe anche partendo da una lista vuota,
	 * ma non è quello che si testa qui.)
	 * <br>
	 * <b>Precondizioni:</b> La lista deve essere non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> La lista deve essere vuota.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code isEmpty} riconoscerà correttamente che {@code la} è vuota.
	 */
	@Test
	public void testIsEmptyAllRemoved(){
		addTenElementsToListLa();
		myAdapter.HListIterator li = la.listIterator();
		while(li.hasNext()){
			la.remove(0);
		}
		assertTrue("La lista non è vuota;", la.isEmpty());
	}

	/**Test per verificare che {@code clear} funzioni correttamente con una lista già vuota.
	 * <br>
	 * <b>Design:</b> Usando {@code la} come lista, è assicurato che sia vuota, come da metodo {@code setup},
	 * si può quindi verificare che il metodo non lanci eccezioni indebitamente.
	 * <br>
	 * <b>Descrizione:</b> Viene chiamato il metodo {@code clear} su una lista vuota.
	 * <br>
	 * <b>Precondizioni:</b> {@code la} deve essere vuota.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo riconoscerà correttamente che la lista è vuota.
	 */
	@Test
	public void testClearEmptyList(){
		la.clear();
		assertEquals("La lista non ha dimensione 0;", 0, la.size());
		assertTrue("La lista non è vuota;", la.isEmpty());
	}

	/**Test per verificare la rimozione di un elemento tramite {@code remove(Object o)}.
	 * <br>
	 * <b>Design:</b> Viene verificato che per quest'istanza venga rimosso correttamente un elemento presente nella lista.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista con un metodo ausiliario, poi tramite {@code remove(Object o)} viene rimosso uno degli elementi, infine viene controllato che non sia più contenuto tramite {@code contains}.
	 * <br>
	 * <b>Precondizioni:</b> La lista è non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> Gli elementi della lista sono gli stessi, tranne che per quello rimosso.
	 * <br>
	 * <b>Risultati attesi:</b> Viene eliminato correttamente l'elemento.
	 */
	 @Test
	 public void testRemoveObject(){
		 Integer removeObj = 2;
		 addTenElementsToListLa();
		 int prevSize = la.size();

		 assertTrue("La lista non contiene l'elemento da rimuovere;", la.contains(removeObj));
		 boolean res = la.remove(removeObj);
		 assertTrue("La lista non è stata modificata;", res);
		 assertEquals("La dimensione non è cambiata", prevSize - 1, la.size());
		 assertFalse("La lista contiene ancora l'elemento da rimuovere;", la.contains(removeObj));
	 }

	/**Test per verificare il comportamento di {@code remove(Object o)} con un oggetto non contenuto nella lista.
	 * <br>
	 * <b>Design:</b> La rimozione di un oggetto non presente non dovrebbe modificare la lista,
	 * quindi viene verificato che sia quello il risultato per quest'istanza.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista con un metodo ausiliario, poi viene invocato il metodo {@code remove(Object o)}, con {@code o} un oggetto non presente nella lista.
	 * <br>
	 * <b>Precondizioni:</b> L'oggetto da rimuovere non deve essere contenuto nella lista.
	 * <br>
	 * <b>Postcondizioni:</b> La lista non viene modificata.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code remove} restituisce {@code false}.
	 */
	@Test
	public void testRemoveObjectNotPresent(){
		String notPres = "Non presente";
		addTenElementsToListLa();
		int prevSize = la.size();

		assertFalse("La lista contiene l'elemento;", la.contains(notPres));
		boolean res = la.remove(notPres);
		assertFalse("La lista è stata modificata;", res);
		assertEquals("La dimensione è cambiata;", prevSize, la.size());
	}

	/**Test per verificare il comportamento di {@code remove(Object o)} con un oggetto {@code null} contenuto nella lista.
	 * <br>
	 * <b>Design:</b> La rimozione di un oggetto null deve funzionare come per un qualsiasi altro oggetto,
	 * quindi viene verificato che sia quello il risultato per quest'istanza.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista con un metodo ausiliario, aggiungendo anche elementi {@code null}, poi viene invocato il metodo {@code remove(Object o)}, con {@code o} di valore {@code null}.
	 * <br>
	 * <b>Precondizioni:</b> La lista deve contenere almeno un oggetto {@code null}.
	 * <br>
	 * <b>Postcondizioni:</b> Gli elementi della lista sono gli stessi, tranne che per quello rimosso.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code remove} rimuove correttamente l'oggetto {@code null}.
	 */
	@Test
	public void testRemoveObjectNull(){
		addTenElementsToListLa();
		la.add(null);
		int prevSize = la.size();

		assertTrue("La lista non contiene un elemento null;", la.contains(null));
		boolean res = la.remove(null);
		assertTrue("La lista non è stata modificata;", res);
		assertEquals("La dimensione non è cambiata;", prevSize - 1, la.size());
	}

	/**Test per verificare che {@code removeAll}, ricevendo come parametro una lista vuota, non modifichi la lista (verificato con {@code equals}.
	 * <br>
	 * <b>Design:</b> Rimuovendo "tutti" gli elementi di una lista vuota, 
	 * alla lista da modificare non dovrebbero essere apportati cambiamenti.
	 * Il metodo permette di testare, {@code removeAll}, {@code equals} e il funzionamento di iteratori su liste.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita {@code la} tramite metodo ausiliario, copiata in un altra lista ({@code listBefore}).
	 * poi viene usata la lista ausiliaria, vuota per {@code setup},
	 * come parametro per l'invocazione di {@code removeAll}.
	 * Viene controllato che la lista non abbia subito modifiche con il metodo {@code equals}.
	 * <br>
	 * <b>Precondizioni:</b> {@code ausList} deve esser vuota.
	 * <br>
	 * <b>Postcondizioni:</b> {@code ausList} conterrà tutti gli elementi che {@code la} conteneva in precedenza.
	 * <br>
	 * <b>Risultati attesi:</b> La lista rimarrà uguale tra l'inizio e la fine dell'esecuzione.
	 */
	@Test
	public void testRemoveAllEmpty(){
		addTenElementsToListLa();

		myAdapter.ListAdapter listBefore = new myAdapter.ListAdapter();
		myAdapter.HListIterator li = la.listIterator();

		while(li.hasNext()){
			listBefore.add(li.next());
		}

		boolean res = la.removeAll(ausList);

		assertFalse("La lista è stata cambiata;", res);
		assertTrue("Le liste non sono uguali", la.equals(listBefore));
	}

	/**Test per verificare che {@code removeAll}, ricevendo come parametro una lista con elementi non contenuti in {@code la}, non modifichi {@code la} (verificato con {@code equals}).
	 * <br>
	 * <b>Design:</b> Rimuovendo tutti gli elementi in comune con una lista che non ha nessun elemento comune,
	 * non dovrebbero essere apportati cambiamenti.
	 * Il metodo permette di testare {@code removeAll}, {@code equals} e il funzionamento di iteratori su liste.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita {@code la} tramite metodo ausiliario, copiata in un altra lista ({@code listBefore}),

	 * poi viene usata la lista ausiliaria, riempita con elementi non in comune,
	 * come parametro per l'invocazione di {@code removeAll}.
	 * Viene controllato che la lista non abbia subito modifiche con il metodo {@code equals}.
	 * <br>
	 * <b>Precondizioni:</b> {@code ausList} non deve avere elementi in comune con {@code la}.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> La lista rimarrà uguale tra l'inizio e la fine dell'esecuzione.
	 */
	@Test
	public void testRemoveAllDifferentList(){
		addTenElementsToListLa();

		myAdapter.ListAdapter listBefore = new myAdapter.ListAdapter();
		myAdapter.HListIterator li = la.listIterator();

		while(li.hasNext()){
			listBefore.add(li.next());
		}

		ausList.add("Non in comune");

		boolean res = la.removeAll(ausList);

		assertFalse("La lista è stata cambiata;", res);
		assertTrue("Le liste non sono uguali;", la.equals(listBefore));
	}

	/**Test per verificare che {@code removeAll}, ricevendo la lista stessa come parametro, svuoti completamente la lista.
	 * <br>
	 * <b>Design:</b> Usando la lista da cui si vogliono eliminare gli elementi come parametro dovrebbe esserer svuotata,
	 * infatti la lista ha per forza tutti gli elementi in comune con sé stessa.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista tramite un metodo ausiliario,
	 * la lista ausiliaria viene riempita allo stesso modo,
	 * poi viene usata come parametro per invocare {@code removeAll}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> La lista {@code la} verrà svuotata.
	 */
	@Test
	public void testRemoveAllSameList(){
		addTenElementsToListLa();

		for(int i = 0; i < la.size(); i++){
			ausList.add(i);
		}

		assertTrue("Le liste non sono uguali;", la.equals(ausList));

		boolean res = la.removeAll(ausList);

		assertTrue("La lista non è stata modificata;", res);
		assertTrue("La lista non è vuota;", la.isEmpty());
	}

	/**Test per verificare che {@code removeAll} rimuova correttamente parte degli elementi di una lista.
	 * <br>
	 * <b>Design:</b> Usando la lista ausiliaria, si possono eliminare parte degli elementi di una lista.
	 * Non viene usata una lista restituita dal metodo {@code subList} siccome il suo contenuto è indefinito
	 * quando vengono effettuati cambiamenti strutturali
	 * (come la rimozione di oggetti).
	 * Viene usato il metodo {@code contains} per verificare che gli elementi non siano più presenti.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista {@code la} tramite un metodo ausiliario, 
	 * vengono aggiunti parte dei suoi elementi (non contigui) alla lista ausiliaria,
	 * che viene poi usata come parametro per il metodo {@code removeAll}.
	 * <br>
	 * <b>Precondizioni:</b> Gli elementi di {@code ausList} devono essere un sottoinsieme di quelli di {@code la}, {@code la} deve avere elementi tutti distinti (altrimenti il controllo con {@code contains} non garantisce di essere corretto).
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verranno eliminati correttamente tutti e soli gli elementi specificati da {@code ausList}.
	 */
	@Test
	public void testRemoveAllSubList(){
		addTenElementsToListLa();
		int prevSize = la.size();

		//mantiene solo gli elementi agli indici pari
		for(int i = 0; i < la.size(); i+=2){
			ausList.add(la.get(i));
		}

		boolean res = la.removeAll(ausList);
		assertTrue("La lista non è stata modificata;", res);
		assertEquals("La lista non è di dimensione corretta;", prevSize - ausList.size(), la.size());

		myAdapter.HListIterator li = ausList.listIterator();
		while(li.hasNext()){
			assertFalse("La lista contiene uno degli elementi da eliminare;", la.contains(li.next()));
		}
	}

	/**Test per verificare che {@code retainAll}, ricevendo come parametro una lista vuota, svuoti completamente la lista.
	 * <br>
	 * <b>Design:</b> Mantenendo "tutti" gli elementi di una lista vuota, 
	 * la lista da modificare dovrebbe essere svuotata.
	 * Il metodo permette di testare sia {@code retainAll} che {@code isEmpty}.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita {@code la} tramite metodo ausiliario,
	 * poi viene usata la lista ausiliaria, vuota per {@code setup},
	 * come parametro per l'invocazione di {@code retainAll}.
	 * Viene controllato che la lista sia stata svuotata con il metodo {@code isEmpty}.
	 * <br>
	 * <b>Precondizioni:</b> {@code ausList} deve esser vuota.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> La lista verrà svuotata.
	 */
	@Test
	public void testRetainAllEmpty(){
		addTenElementsToListLa();
		boolean res = la.retainAll(ausList);

		assertTrue("La lista non è stata cambiata;", res);
		assertTrue("La lista non è stata svuotata;", la.isEmpty());
	}

	/**Test per verificare che {@code retainAll}, ricevendo come parametro una lista con elementi non contenuti in {@code la}, svuoti completamente la lista.
	 * <br>
	 * <b>Design:</b> Mantenendo tutti gli elementi in comune con una lista che non ha nessun elemento comune,
	 * la lista originale dovrebbe risultare vuota alla fine.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita {@code la} tramite metodo ausiliario,
	 * poi viene usata la lista ausiliaria, riempita con elementi non in comune,
	 * come parametro per l'invocazione di {@code retainAll}.
	 * Viene controllato che {@code la} sia vuota con il metodo {@code isEmpty}.
	 * <br>
	 * <b>Precondizioni:</b> {@code ausList} non deve avere elementi in comune con {@code la}.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> La lista {@code la} verrà svuotata.
	 */
	@Test
	public void testRetainAllDifferentList(){
		addTenElementsToListLa();

		ausList.add("Non in comune");

		boolean res = la.retainAll(ausList);

		assertTrue("La lista non è stata cambiata;", res);
		assertTrue("La lista non è vuota;", la.isEmpty());
	}

	/**Test per verificare che {@code retainAll}, ricevendo la lista stessa come parametro, non effettui modifiche alla lista.
	 * <br>
	 * <b>Design:</b> Usando una lista con tutti gli stessi elementi di quella da modificare,
	 * dovrebbero essere mantenuti tutti (la lista ha per forza tutti gli elementi in comune con sé stessa).
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista tramite un metodo ausiliario,
	 * la lista ausiliaria viene riempita allo stesso modo,
	 * poi viene usata come parametro per invocare {@code retainAll}.
	 * <br>
	 * <b>Precondizioni:</b> La lista ausiliaria deve contenere gli stessi elementi di {@code la}.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code retainAll} non modificherà {@code la}.
	 */
	@Test
	public void testRetainAllSameList(){
		addTenElementsToListLa();

		for(int i = 0; i < la.size(); i++){
			ausList.add(i);
		}

		myAdapter.ListAdapter listBefore = la;
		boolean res = la.retainAll(ausList);

		assertFalse("La lista è stata modificata;", res);
		assertTrue("La lista non è uguale dopo l'esecuzione del metodo;", la.equals(listBefore));
	}

	/**Test per verificare che {@code retainAll} mantenga correttamente parte degli elementi di una lista.
	 * <br>
	 * <b>Design:</b> Usando la lista ausiliaria, si possono mantenere parte degli elementi di una lista.
	 * Non viene usata una lista restituita dal metodo {@code subList} siccome il suo contenuto è indefinito
	 * quando vengono effettuati cambiamenti strutturali
	 * (come la rimozione di oggetti).
	 * Viene usato il metodo {@code contains} per verificare che gli elementi corretti siano presenti.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista {@code la} tramite un metodo ausiliario, 
	 * vengono aggiunti parte dei suoi elementi (non contigui) alla lista ausiliaria,
	 * che viene poi usata come parametro per il metodo {@code retainAll}.
	 * <br>
	 * <b>Precondizioni:</b> Gli elementi di {@code ausList} devono essere un sottoinsieme di quelli di {@code la}, {@code la} deve avere elementi tutti distinti (altrimenti il controllo con {@code contains} non garantisce di essere corretto).
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verranno mantenuti correttamente tutti e soli gli elementi specificati da {@code ausList}.
	 */
	@Test
	public void testRetainAllSubList(){
		addTenElementsToListLa();
		int prevSize = la.size();

		//mantiene solo gli elementi agli indici pari
		for(int i = 0; i < la.size(); i+=2){
			ausList.add(la.get(i));
		}

		boolean res = la.retainAll(ausList);
		assertTrue("La lista non è stata modificata;", res);
		assertEquals("La lista non è della dimensione corretta;", ausList.size(), la.size());

		myAdapter.HListIterator li = ausList.listIterator();
		while(li.hasNext()){
			assertTrue("La lista non contiene uno degli elementi da mantenere;", la.contains(li.next()));
		}
	}

	/*---------------UGUAGLIANZA LISTE, HASH CODE---------------*/

	/**
	 * <br> Test per verificare che il metodo {@code hashCode} restituisca correttamente l'hash code per una lista con elementi di diverso tipo.
	 * <b>Design:</b> Viene dichiarato nella documentazione come viene calcolato l'hash code,
	 * quindi calcolandolo allo stesso modo nel metodo dovrebbe risultare uguale.
	 * <br>
	 * <b>Descrizione:</b> Vengono aggiunti numeri interi, oggetti {@code null} e stringhe alla lista,
	 * poi ne viene calcolato l'hashcode tramite metodo {@code hashCode} e tramite l'algoritmo dichiarato nella documentazione, per verificare che siano uguali.
	 * <br>
	 * <b>Precondizioni:</b> La lista deve contenere diversi tipi di oggetti, tra cui oggetti {@code null}.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Gli hash code calcolati saranno uguali.
	 */
	@Test
	public void testHashCode(){
		addTenElementsToListLa();
		la.add(null);
		la.add("Elemento");
		int hashMethod = la.hashCode();

		int hashAlgorithm = 1;
		myAdapter.HIterator it = la.iterator();
		while(it.hasNext()){
			Object o = it.next();
			hashAlgorithm = 31 * hashAlgorithm + (o == null ? 0 : o.hashCode());
		}

		assertEquals("Gli hash code calcolati sono diversi;", hashAlgorithm, hashMethod);
	}

	/**Test per verificare che il meotodo {@code hashCode} calcoli lo stesso hash code per liste contenenti gli stessi elementi.
	 * <br>
	 * <b>Design:</b> Due liste uguali dovranno calcolare lo stesso hash code,
	 * quindi questo viene testato.
	 * Non è garantito che due liste differenti non calcolino lo stesso hash code,
	 * quindi ciò non viene testato.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista {@code la} con un metodo ausiliario,
	 * poi ne viene copiato il contenuto sulla lista ausiliaria, così da avere due liste identiche.
	 * Viene calcolato l'hash code di entrambe le liste e si verifica se il risultato è lo stesso.
	 * <br>
	 * <b>Precondizioni:</b> {@code la} e {@code ausList} devono equivalersi.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Gli hash code calcolati saranno uguali.
	 */
	@Test
	public void testHashCodeSameList(){
		addTenElementsToListLa();
		la.add("Elemento");
		la.add(null);

		myAdapter.HListIterator li = la.listIterator();
		while(li.hasNext()){
			ausList.add(li.next());
		}

		assertTrue("Le liste non sono uguali;", la.equals(ausList));
		assertEquals("Gli hash code non sono uguali;", la.hashCode(), ausList.hashCode());
	}

	/**Test per verificare che il metodo {@code equals} funzioni correttamente per liste uguali.
	 * <br>
	 * <b>Design:</b> Il funzionamento del metodo {@code equals} è già stato testato su liste contenenti interi
	 * nei test di {@code removeAll}, {@code retainAll}, quindi adesso viene testato con diverse tipologie di oggetti.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista {@code la} con degli elementi, 
	 * che vengono poi inserriti nello stesso ordine in {@code ausList} tramite iteratore.
	 * Viene infine testato che le liste risultanti siano uguali.

	 * <br>
	 * <b>Precondizioni:</b> {@code la} e {@code ausList} devono contenere gli stessi elementi nello stesso ordine.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Le liste verranno riconosciute come uguali da {@code equals}.
	 */
	@Test
	public void testEqualsSameList(){
		la.add("Elemento");
		la.add(null);
		la.add(new Object());
		la.add(123);

		myAdapter.HListIterator li = la.listIterator();
		while(li.hasNext()){
			ausList.add(li.next());
		}

		assertTrue("Le liste non sono uguali;", la.equals(ausList));
	}

	/**Test per verificare che il metodo {@code equals} funzioni correttamente per liste diverse e di diversa dimensione.
	 * <br>
	 * <b>Design:</b> Avendo testato il metodo con liste uguali nei test dei metodi {@code removeAll}, {@code retainAll} e {@code hashCode},
	 * ora viene testato che funzioni correttamente anche per delle istanze di liste differenti.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista {@code la} con un metodo ausiliario,
	 * poi vengono aggiunti degli elementi ad {@code ausList} in modo che sia diversa da {@code la},
	 * poi le si paragonano con {@code equals}.
	 * <br>
	 * <b>Precondizioni:</b> {@code la} e {@code ausList} devono contenere elementi differenti.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Le liste verranno riconosciute come differenti da {@code equals}.
	 */
	@Test 
	public void testEqualsDifferentList(){
		addTenElementsToListLa();

		ausList.add(1);
		ausList.add(5);
		ausList.add(null);
		ausList.add("Elemento");

		assertFalse("Le liste sono uguali;", la.equals(ausList));
	}

	/**Test per verificare che il metodo {@code equals} funzioni correttamente per liste della stessa lunghezza, con gli stessi elementi in ordine differente.
	 * <br>
	 * <b>Design:</b> Due liste con la stessa lunghezza e stessi elementi in ordine differente sono diverse,
	 * quindi viene verificato che {@code equals} sia in grado di verificarlo.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita {@code la} con un metodo ausiliario,
	 * poi viene riempita {@code ausList} con gli elementi di {@code la} in ordine inverso.
	 * <br>
	 * <b>Precondizioni:</b> {@code ausList} contiene gli stessi elementi di {@code la} in ordine differente.
	 * {@code la} non ha tutti gli elementi uguali.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Le liste verranno riconosciute come differenti da {@code equals}.
	 */
	@Test
	public void testEqualsScrambledList(){
		addTenElementsToListLa();

		myAdapter.HListIterator it = la.listIterator(la.size());

		while(it.hasPrevious()){
			ausList.add(it.previous());
		}

		assertFalse("Le liste sono uguali;", la.equals(ausList));
	}

	/**Test per verificare che il metodo {@code equals} funzioni correttamente per liste della stessa lunghezza, con elementi diversi.
	 * <br>
	 * <b>Design:</b> Due liste della stessa lunghezza con elementi diversi sono diverse,
	 * quindi viene verificato che {@code equals} sia in grado di verificarlo.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita {@code la} con un metodo ausiliario,
	 * poi viene riempita {@code ausList} con lo stesso numero di elementi differenti.
	 * <br>
	 * <b>Precondizioni:</b> {@code la} e {@code ausList} hanno la stessa dimensione.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Le liste verranno riconosciute come differenti da {@code equals}.
	 */
	@Test
	public void testEqualsSameLength(){
		addElementsToListLa();

		myAdapter.HListIterator it = la.listIterator();

		while(it.hasNext()){
			ausList.add("Elemento");
			it.next();
		}

		assertFalse("Le liste sono uguali;", la.equals(ausList));
	}

	/**Test per verificare che il metodo {@code equals} funzioni correttamente fornendo come parametro un oggetto non corrispondente a una lista.
	 * <br>
	 * <b>Design:</b> Un oggetto che non è un'istanza di {@code HList} non può essere uguale a una lista,
	 * quindi viene verificato che {@code equals} sia in grado di verificarlo.
	 * <br>
	 * <b>Descrizione:</b> Viene creato un oggetto Object e viene paragonato alla lista vuota {@code la}.
	 * <br>
	 * <b>Precondizioni:</b> L'oggetto creato non può essere un'istanza di {@code HList}.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Gli oggetti verranno riconosciuti come differenti da {@code equals}.
	 */
	@Test
	public void testEqualsNonHList(){
		Object o = new String();

		assertFalse("Gli oggetti sono uguali;", la.equals(o));
	}

	/*---------------GET, SET e INDICI---------------*/

	/**Test per verificare che il metodo {@code get} funzioni correttamente per vari indici.
	 * <br>
	 * <b>Design:</b> Il metodo get accetta qualsiasi indice tale che {@code index >= 0 || index < size()},
	 * quindi viene testato su tutti gli indici della lista {@code la}, 
	 * riempita per dieci volte con il metodo ausiliario {@code addTenElementsToListLa}
	 * (per il quale ogni elemento corrisponde al numero del suo indice).
	 * <br>
	 * <b>Descrizione:</b> Viene riempita dieci volte la lista {@code la} con il metodo {@code addTenElementsToListLa},
	 * poi, uno per volta, vengono esaminati gli elementi tramite metodo {@code get}.
	 * <br>
	 * <b>Precondizioni:</b> Gli elementi della lista devono essere interi corrispondenti alle loro posizioni.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Ogni elemento verrà restituito da {@code get} correttamente.
	 */
	@Test
	public void testGet(){
		int times = 10;
		for(int i = 0; i < times; i++){
			addTenElementsToListLa();
		}

		for(int i = 0; i < la.size(); i++){
			assertEquals("L'elemento " + i + " restituito non è corretto;", i, la.get(i));
		}
	}

	/**Test per verificare che il metodo {@code set} funzioni correttamente per vari indici.
	 * <br>
	 * <b>Design:</b> Il metodo set accetta qualsiasi indice tale che {@code index >= 0 || index < size()},
	 * quindi viene testato su tutti gli indici della lista {@code la}, 
	 * riempita per dieci volte con il metodo ausiliario {@code addTenElementsToListLa}, 
	 * tale che ogni elemento modificato con {@code set} di indice {@code i}
	 * abbia come elemento corrispondente l'intero {@code la.size() - i + 500} 
	 * (in modo che nussuno degli elementi precedentemente inseriti sia uguale a uno degli elementi modificati).
	 * <br>
	 * <b>Descrizione:</b> Viene riempita dieci volte la lista {@code la} con il metodo {@code addTenElementsToListLa},
	 * poi, uno per volta, vengono modificati gli elementi tramite {@code set} 
	 * in modo che per ogni indice {@code i} l'elemento corrispondente sia {@code la.size() - i + 500}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} conterrà gli interi da 501 a 600 in ordine inverso.
	 * <br>
	 * <b>Risultati attesi:</b> Ogni elemento verrà rimpiazzato da {@code set} correttamente.
	 */
	@Test
	public void testSet(){
		int times = 10;
		for(int i = 0; i < times; i++){
			addTenElementsToListLa();
		}

		for(int i = 0; i < la.size(); i++){
			int newEl = la.size() - i + 500;
			assertEquals("L'elemento " + i + " restituito non è corretto;", i, la.set(i, newEl));
			assertEquals("L'elemento " + i + " inserito non è corretto;", newEl, la.get(i));
		}
	}

	/**Test per verificare che {@code indexOf} restituisca la posizione corretta di un elemento in una lista con tutti elementi differenti.
	 * <br>
	 * <b>Design:</b> Usando il metodo {@code addTenElementsToListLa} ogni elemento è un intero corrispondente al suo indice,
	 * quindi si testa il metodo {@code indexOf} per ognuno di questi elementi.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita dieci volte la lista {@code la} con il metodo {@code addTenElementsToListLa},
	 * poi per ogni elemento viene verificato che l'indice restituito sia corretto
	 * (ovvero che indice e elemento siano uguali).
	 * <br>
	 * <b>Precondizioni:</b> Gli elementi della lista devono essere interi corrispondenti alle loro posizioni.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code indexOf} restituirà correttamente gli indici di ogni elementi.
	 */
	@Test
	public void testIndexOf(){
		int times = 10;
		for(int i = 0; i < times; i++){
			addTenElementsToListLa();
		}

		for(int i = 0; i < la.size(); i++){
			assertEquals("L'indice è errato;", i, la.indexOf(i));
		}
	}

	/**Test per verificare che {@code indexOf} restituisca l'indice della prima occorrenza dell'elemento specificato.
	 * <br>
	 * <b>Design:</b> Dopo aver riempito {@code la} con il metodo ausiliario {@code addTenElementsToListLa},
	 * viene inserito nella lista un elemento differente dagli altri per tre volte 
	 * (numero scelto arbitrariamente tra {@code [2, la.size()]}), 
	 * così da poter verificare che venga individuato correttamente l'indice della prima occorrenza.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita dieci volte la lista {@code la} con il metodo {@code addTenElementsToListLa},
	 * viene inserito un elemento differente in tre posizioni (scelte arbitrariamente),
	 * poi viene verificato che {@code indexOf} restituisca l'indice corretto.
	 * <br>
	 * <b>Precondizioni:</b> Devono essere presenti più istanze dell'elemento da cercare, la variabile {@code index} è l'indice della prima occorrenza.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code indexOf} restituirà correttamente l'indice della prima occorrenza dell'elemento da cercare.
	 */
	@Test
	public void testIndexOfManyInstances(){
		addTenElementsToListLa();

		int index = 3;
		String el = "Trovami";
		la.add(index, el);
		la.add(index + 1, el);
		la.add(la.size() - index, el); //posizioni scelte in modo che index sia l'indice della prima occorrenza

		assertEquals("L'indice non corrisponde alla prima istanza;", index, la.indexOf(el));
	}

	/**Test per verificare che {@code indexOf} funzioni correttamente cercando un elemento non presente.
	 * <br>
	 * <b>Design:</b> Riempiendo {@code la} con il metodo ausiliario {@code addTenElementsToListLa} essa conterrà solo interi,
	 * quindi sicuramente cercando l'indice di una stringa questa non verrà trovata, 
	 * quindi {@code indexOf} dovrà restituire -1.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita {@code la} con {@code addTenElementsToListLa}, 
	 * quindi viene cercato l'indice di una stringa con {@code indexOf}.
	 * <br>
	 * <b>Precondizioni:</b> L'elemento da cercare non deve essere presente in {@code la}.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code indexOf} restituirà -1.
	 */
	@Test
	public void testIndexOfNotPresent(){
		addTenElementsToListLa();
		String el = "Non presente";

		assertEquals("L'elemento è stato trovato;", -1, la.indexOf(el));
	}
	
	/**Test per verificare che {@code lastIndexOf} restituisca la posizione corretta di un elemento in una lista con tutti elementi differenti.
	 * <br>
	 * <b>Design:</b> Usando il metodo {@code addTenElementsToListLa} ogni elemento è un intero corrispondente al suo indice,
	 * quindi si testa il metodo {@code lastIndexOf} per ognuno di questi elementi.
	 * Il comportamento di {@code lastIndexOf} quando tutti gli elementi sono differenti è lo stesso di {@code indexOf}.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita dieci volte la lista {@code la} con il metodo {@code addTenElementsToListLa},
	 * poi per ogni elemento viene verificato che l'indice restituito sia corretto
	 * (ovvero che indice e elemento siano uguali).
	 * <br>
	 * <b>Precondizioni:</b> Gli elementi della lista devono essere interi corrispondenti alle loro posizioni; in particolare devono essere tutti diversi.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code lastIndexOf} restituirà correttamente gli indici di ogni elementi.
	 */
	@Test
	public void testLastIndexOf(){
		int times = 10;
		for(int i = 0; i < times; i++){
			addTenElementsToListLa();
		}

		for(int i = 0; i < la.size(); i++){
			assertEquals("L'indice è errato;", i, la.lastIndexOf(i));
		}
	}

	/**Test per verificare che {@code lastIndexOf} restituisca l'indice dell'ultima  occorrenza dell'elemento specificato.
	 * <br>
	 * <b>Design:</b> Dopo aver riempito {@code la} con il metodo ausiliario {@code addTenElementsToListLa},
	 * viene inserito nella lista un elemento differente dagli altri per tre volte 
	 * (numero scelto arbitrariamente tra {@code [2, la.size()]}), 
	 * così da poter verificare che venga individuato correttamente l'indice della prima occorrenza.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita dieci volte la lista {@code la} con il metodo {@code addTenElementsToListLa},
	 * viene inserito un elemento differente in tre posizioni (scelte arbitrariamente),
	 * poi viene verificato che {@code lastIndexOf} restituisca l'indice corretto.
	 * <br>
	 * <b>Precondizioni:</b> Devono essere presenti più istanze dell'elemento da cercare, la variabile {@code index + 2} è l'indice dell'ultima occorrenza (tiene conto delle due occorrenze inserite prima dell'ultima).
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code lastIndexOf} restituirà correttamente l'indice dell'ultima occorrenza dell'elemento da cercare.
	 */
	@Test
	public void testLastIndexOfManyInstances(){
		addTenElementsToListLa();

		int index = 9;
		String el = "Trovami";
		la.add(index, el);
		la.add(index - 1, el);
		la.add(la.size() - index, el); //posizioni scelte in modo che index sia l'indice dell'ultima occorrenza

		assertEquals("L'indice non corrisponde all'ultima istanza;", index + 2, la.lastIndexOf(el));
	}

	/**Test per verificare che {@code lastIndexOf} funzioni correttamente cercando un elemento non presente.
	 * <br>
	 * <b>Design:</b> Riempiendo {@code la} con il metodo ausiliario {@code addTenElementsToListLa} essa conterrà solo interi,
	 * quindi sicuramente cercando l'indice di una stringa questa non verrà trovata, 
	 * quindi {@code lastIndexOf} dovrà restituire -1.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita {@code la} con {@code addTenElementsToListLa}, 
	 * quindi viene cercato l'indice di una stringa con {@code lastIndexOf}.
	 * <br>
	 * <b>Precondizioni:</b> L'elemento da cercare non deve essere presente in {@code la}.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code indexOf} restituirà -1.
	 */
	@Test
	public void testLastIndexOfNotPresent(){
		addTenElementsToListLa();
		String el = "Non presente";

		assertEquals("L'elemento è stato trovato;", -1, la.lastIndexOf(el));
	}

	/*---------------ARRAY---------------*/

	/**Test per verificare che {@code toArray()} contenga correttamente gli elementi di una lista con varie tipologie di oggetti.
	 * <br>
	 * <b>Design:</b> Il metodo {@code toArray()} deve restituire un array contenente tutti gli oggetti della lista utilizzata,
	 * quindi viene verificato che funzioni correttamente usando più tipologie di oggetti,
	 * inclusi oggetti {@code null}.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista {@code la} con i metodi {@code addElementsToListLa} e {@code addTenElementsToListLa},
	 * poi vengono aggiunte delle stringhe, oggetti {@code null} e oggetti {@code Object}.
	 * Infine viene verificato che ogni oggetto della lista e dell'array siano uguali.
	 * <br>
	 * <b>Precondizioni:</b> La lista {@code la} deve contenere vari tipi di oggetti.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Ogni oggetto dell'array sarà uguale al corrispondente oggetto della lista.
	 */
	@Test
	public void testToArray(){
		addTenElementsToListLa();
		addElementsToListLa();
		la.add(null);
		la.add("Elemento");
		la.add(new Object());
		la.add(null);
		la.add("Contenuto");

		Object[] array = la.toArray();

		assertEquals("L'array e la lista non hanno stessa dimensione;", la.size(), array.length);
		for(int i = 0; i < la.size(); i++){
			assertEquals("Gli elementi in posizione " + i + " non sono uguali;", la.get(i), array[i]);
		}
	}

	/**Test per verificare che {@code toArray(Object[] a)} contenga correttamente gli elementi di una lista con varie tipologie di oggetti.
	 * <br>
	 * <b>Design:</b> Il metodo {@code toArray(Object[] a)} deve inserire nell'array {@code a}  gli oggetti della lista utilizzata,
	 * quindi viene verificato che funzioni correttamente usando più tipologie di oggetti,
	 * inclusi oggetti {@code null}.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista {@code la} con i metodi {@code addElementsToListLa} e {@code addTenElementsToListLa},
	 * poi vengono aggiunte delle stringhe, oggetti {@code null} e oggetti {@code Object}.
	 * Infine viene verificato che ogni oggetto della lista e dell'array siano uguali.
	 * <br>
	 * <b>Precondizioni:</b> La lista {@code la} deve contenere vari tipi di oggetti.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Ogni oggetto dell'array sarà uguale al corrispondente oggetto della lista.
	 */
	@Test
	public void testToArrayGiven(){
		addTenElementsToListLa();
		addElementsToListLa();
		la.add(null);
		la.add("Elemento");
		la.add(new Object());
		la.add(null);
		la.add("Contenuto");

		Object[] array = new Object[la.size()];
		assertEquals("L'array e la lista non hanno stessa dimensione;", la.size(), array.length);

		Class classArray = array.getClass();

		array = la.toArray(array);

		assertEquals("Le classi prima e dopo l'esecuzione del metodo non sono uguali;", classArray, array.getClass());
		for(int i = 0; i < la.size(); i++){
			assertEquals("Gli elementi in posizione " + i + " non sono uguali;", la.get(i), array[i]);
		}
	}

	/**Test per verificare che {@code toArray(Object[] a)} non dia errori partendo da un array troppo piccolo.
	 * <br>
	 * <b>Design:</b> Il metodo {@code toArray(Object[] a)} deve inserire nell'array {@code a}  gli oggetti della lista utilizzata,
	 * anche se l'array di partenza non è sufficientemente capiente,
	 * quindi viene verificato che il metodo funzioni correttamente anche per questo caso.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista {@code la} con il metodo {@code addTenElementsToListLa}.
	 * Viene poi verificato che ogni oggetto della lista e dell'array siano uguali.
	 * <br>
	 * <b>Precondizioni:</b> L'array di partenza non può avere dimensione maggiore o uguale della dimensione di {@code la}.
	 * <br>
	 * <b>Postcondizioni:</b> L'array sarà abbastanza capiente.
	 * <br>
	 * <b>Risultati attesi:</b> Ogni oggetto dell'array sarà uguale al corrispondente oggetto della lista.
	 */
	@Test
	public void testToArraySmallerThanSize(){
		addTenElementsToListLa();

		Object[] array = new Object[0];
		assertTrue("L'array non ha dimensione iniziale minore della lista;", array.length < la.size());

		Class classArray = array.getClass();

		array = la.toArray(array);

		assertEquals("Le classi prima e dopo l'esecuzione del metodo non sono uguali;", classArray, array.getClass());

		for(int i = 0; i < la.size(); i++){
			assertEquals("Gli elementi in posizione " + i + " non sono uguali;", la.get(i), array[i]);
		}
	}

	/*---------------ECCEZIONI---------------*/


	/**Test costruttore {@code ListAdapter(int)}, effettuato fornendo un numero negativo.
	 * Il costruttore fallisce, lanciando un'eccezione.
	 * <br>
	 * <b>Design:</b> Il costruttore {@code ListAdapter(int)} non accetta numeri negativi,
	 * viene perciò testato che lanci correttamente l'eccezione {@code java.lang.IllegalArgumentException} quando non vengono rispettati i vincoli.
	 * Il test controlla che anche il metodo {@code size} funzioni correttamente (per questa esecuzione).
	 * <br>
	 * <b>Descrizione:</b> Viene chiamato il costruttore {@code ListAdapter(int)} fornendo {@code -1} come capacità iniziale.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non è stata inizializzata.
	 * <br>
	 * <b>Risultati attesi:</b> La chiamata a {@code ListAdapter(int)} non è andata a buon fine e ha lanciato l'eccezione {@code java.lang.IllegalArgumentException}.
	 */
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testExceptionConstructorNegativeCapacity(){
		la = new myAdapter.ListAdapter(-1);
	}

	/**Test costruttore {@code ListAdapter(int, int)}, effettuato fornendo un numero negativo per la capacità.
	 * Il costruttore fallisce, lanciando un'eccezione.
	 * <br>
	 * <b>Design:</b> Il costruttore {@code ListAdapter(int, int)} richiede un intero per la capacità iniziale della lista
	 * (che non può essere negativo) e uno per l'incremento della capacità (che non ha vincoli specifici).
	 * Viene perciò testato che, fornito un numero negativo per la capacità, lanci correttamente l'eccezione {@code java.lang.IllegalArgumentException}.
	 * Il test controlla che anche il metodo {@code size} funzioni correttamente (per questa esecuzione).
	 * <br>
	 * <b>Descrizione:</b> Viene chiamato il costruttore {@code ListAdapter(int, int)} fornendo {@code -1} come capacità iniziale
	 * (e {@code 5} come incremento di capacità, un numero scelto arbitrariamente perché sia valido per il costruttore).
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non è stata inizializzata.
	 * <br>
	 * <b>Risultati attesi:</b> La chiamata a {@code ListAdapter(int,int)} non è andata a buon fine e ha lanciato l'eccezione {@code java.lang.IllegalArgumentException}.
	 */
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testExceptionNegativeCapacityAndIncrement(){
		la = new myAdapter.ListAdapter(-1, 5);
	}

	/**Test per verificare il corretto lancio dell'eccezione di {@code java.lang.NullPointerException} del metodo {@code addAll(HCollection c)}.
	 * <br>
	 * <b>Design:</b> Passare un HCollection nulla dovrebbe lanciare l'eccezione, quindi viene testato la lista ausiliaria (che implementa HList, quindi è un HCollection).
	 * <br>
	 * <b>Descrizione:</b> Viene usata la lista ausiliaria tale che sia nulla, per passarla come parametro
	 * alla funzione {@code addAll(HCollection c)}.
	 * <br>
	 * <b>Precondizioni:</b> La lista ausiliaria deve essere nulla.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non subisce modifiche.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.NullPointerException}.
	 */
	@Test(expected=java.lang.NullPointerException.class)
	public void testExceptionAddNullCollection(){
		ausList = null;
		boolean res = la.addAll(ausList);
		assertFalse("La lista è stata modificata;", res);
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.NullPointerException} del metodo {@code addAll(int index, HCollection c)}.
	 * <br>
	 * <b>Design:</b> Passare un HCollection nulla dovrebbe lanciare l'eccezione, quindi viene testato usando la ausiliaria (che implementa HList, quindi è un HCollection).
	 * Viene aggiunto un elemento a {@code la} per evitare che venga lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 * <br>
	 * <b>Descrizione:</b> Viene usata la lista ausiliaria tale che sia nulla, per passarla come parametro
	 * alla funzione {@code addAll(int index, HCollection c)}.
	 * <br>
	 * <b>Precondizioni:</b> La lista ausiliaria deve essere nulla.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non subisce modifiche.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.NullPointerException}.
	 */
	@Test(expected=java.lang.NullPointerException.class)
	public void testExceptionAddNullCollectionIndex(){
		la.add(0);
		ausList = null;

		boolean res = la.addAll(0, ausList);
		assertFalse("La lista è stata modificata;", res);
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.IndexOutOfBoundsException} del metodo {@code addAll(int index, HCollection c)}.
	 * <br>
	 * <b>Design:</b> Fornire un indice negativo deve sicuramente lanciare l'eccezione {@code java.lang.IndexOutOfBoundsException}, quindi viene verificato.
	 * <br>
	 * <b>Descrizione:</b> Viene usata la lista ausiliaria non nulla (così che non venga lanciata l'ecceazione {@code java.lang.NullPointerException}) da usare come parametro per il metodo, mentre viene fornito un indice negativo per generare l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> La lista ausiliaria deve non essere {@code null}.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non subisce modifiche.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionAddCollectionIndexNegative(){
		la.addAll(-1, ausList);
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.IndexOutOfBoundsException} del metodo {@code add(int index, Object o)}.
	 * <br>
	 * <b>Design:</b> Fornire un indice negatico, quindi sicuramente non nel range della lista, farà in modo che l'eccezione {@code java.lang.IndexOutOfBoundsException} verrà lanciata.
	 * <br>
	 * <b>Descrizione:</b> Viene fornito come parametro il numero -1 al metodo {@code add(int index, Object o)}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestListAdapter.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non subisce modifiche.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionAddIndexNegative(){
		la.add(-1, "Elemento");
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.IndexOutOfBoundsException} del metodo {@code get(int index)} con un numero negativo per indice.
	 * <br>
	 * <b>Design:</b> Il metodo get deve lanciare l'eccezione per valori negativi oppure maggiori o uguali alla dimensione, quindi viene testato il primo caso.
	 * <br>
	 * <b>Descrizione:</b> Vengono aggiunti degli elementi alla lista, poi viene usato -1 come parametro del metodo {@code get(int index)}.
	 * <br>
	 * <b>Precondizioni:</b> La lista è non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non subisce modifiche.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionGetNegative(){
		addElementsToListLa();
		la.get(-1);
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.IndexOutOfBoundsException} del metodo {@code get(int index)} con un numero maggiore della dimensione per indice.
	 * <br>
	 * <b>Design:</b> Il metodo get deve lanciare l'eccezione per valori negativi oppure maggiori o uguali alla dimensione, quindi viene testato il secondo caso.
	 * <br>
	 * <b>Descrizione:</b> Vengono aggiunti degli elementi alla lista, poi viene usato {@code la.size() + 1} come parametro del metodo {@code get(int index)}.
	 * <br>
	 * <b>Precondizioni:</b> La lista è non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non subisce modifiche.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionGetGreaterThanSize(){
		addElementsToListLa();
		la.get(la.size() + 1);
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.IndexOutOfBoundsException} del metodo {@code get(int index)} con la dimensione per indice.
	 * <br>
	 * <b>Design:</b> Il metodo get deve lanciare l'eccezione per valori negativi oppure maggiori o uguali alla dimensione, quindi viene testato il terzo caso.
	 * <br>
	 * <b>Descrizione:</b> Vengono aggiunti degli elementi alla lista, poi viene usato {@code la.size()} come parametro del metodo {@code get(int index)}.
	 * <br>
	 * <b>Precondizioni:</b> La lista è non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non subisce modifiche.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionGetSize(){
		addElementsToListLa();
		la.get(la.size());
	}

	/**Test per verificare che {@code remove(int index)} lanci l'eccezione {@code java.lang.IndexOutOfBoundsException} dato un indice per una lista vuota.
	 * <br>
	 * <b>Design:</b> Una lista vuota ha dimensione zero, quindi nessun indice è accettabile per il metodo {@code remove}. 
	 * Viene testato questo caso particolare usando la dimensione come indice.
	 * <br>
	 * <b>Descrizione:</b> Viene usata {@code la} (vuota per {@code setup}) per invocare {@code remove(int index)}.
	 * <br>
	 * <b>Precondizioni:</b> {@code la} deve essere vuota.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code remove(int index)} lancia l'eccezione {@code java.lang.IndexOutOfBounds}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionRemoveEmptyList(){
		la.remove(la.size());
	}

	/**Test per verificare che {@code remove(int index)} lanci l'eccezione {@code java.lang.IndexOutOfBoundsException} dato un indice negativo.
	 * <br>
	 * <b>Design:</b> Un indice negativo è sicuramente fuori dal range accettabile per qualsiasi lista.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista tramite un metodo ausiliario, poi viene invocato {@code remove(int index)} con -1 come indice.
	 * <br>
	 * <b>Precondizioni:</b> {@code la} deve essere non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code remove(int index)} lancia l'eccezione {@code java.lang.IndexOutOfBounds}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionRemoveNegativeIndex(){
		addTenElementsToListLa();
		la.remove(-1);
	}

	/**Test per verificare che {@code remove(int index)} lanci l'eccezione {@code java.lang.IndexOutOfBoundsException} dato un indice maggiore della sua dimensione.
	 * <br>
	 * <b>Design:</b> Un indice maggiore della dimensione della lista non è accettato, 
	 * quindi viene verificato che venga lanciata correttamente l'eccezione.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista tramite un metodo ausiliario, poi viene invocato {@code remove(int index)} con {@code la.size() + 1} come indice.
	 * <br>
	 * <b>Precondizioni:</b> {@code la} deve essere non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code remove(int index)} lancia l'eccezione {@code java.lang.IndexOutOfBounds}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionRemoveGreaterThanSizeIndex(){
		addTenElementsToListLa();
		la.remove(la.size() + 1);
	}

	/**Test per verificare che {@code removeAll} lanci l'eccezione {@code java.lang.NullPointerException}, data una collezione nulla.
	 * <br>
	 * <b>Design:</b> Secondo le specifiche, il metodo {@code removeAll} non accetta liste nulle,
	 * quindi viene testato che lanci correttamente l'eccezione.
	 * <br>
	 * <b>Descrizione:</b> Viene usata la lista ausiliaria, assegnata a {@code null}, come parametro per il metodo {@code removeAll}.
	 * <br>
	 * <b>Precondizioni:</b> La lista ausiliaria deve essere nulla.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code removeAll} lancia l'eccezione {@code java.lang.NullPointerException}.  
	 */
	@Test(expected=java.lang.NullPointerException.class)
	public void testExceptionRemoveAllNull(){
		ausList = null;
		la.removeAll(ausList);
	}

	/**Test per verificare che {@code retainAll} lanci l'eccezione {@code java.lang.NullPointerException}, data una collezione nulla.
	 * <br>
	 * <b>Design:</b> Secondo le specifiche, il metodo {@code retainAll} non accetta liste nulle,
	 * quindi viene testato che lanci correttamente l'eccezione.
	 * <br>
	 * <b>Descrizione:</b> Viene usata la lista ausiliaria, assegnata a {@code null}, come parametro per il metodo {@code retainAll}.
	 * <br>
	 * <b>Precondizioni:</b> La lista ausiliaria deve essere nulla.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Il metodo {@code retainAll} lancia l'eccezione {@code java.lang.NullPointerException}.  
	 */
	@Test(expected=java.lang.NullPointerException.class)
	public void testExceptionRetainAllNull(){
		ausList = null;
		la.retainAll(ausList);
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.IndexOutOfBoundsException} del metodo {@code set(int index, Object element)} con un numero negativo per indice.
	 * <br>
	 * <b>Design:</b> Il metodo set deve lanciare l'eccezione per valori negativi oppure maggiori o uguali alla dimensione, quindi viene testato il primo caso.
	 * <br>
	 * <b>Descrizione:</b> Vengono aggiunti degli elementi alla lista, poi viene usato -1 come parametro del metodo {@code set(int index, Object element)}.
	 * <br>
	 * <b>Precondizioni:</b> La lista è non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non subisce modifiche.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionSetNegative(){
		addElementsToListLa();
		la.set(-1, "Elemento");
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.IndexOutOfBoundsException} del metodo {@code set(int index, Object element)} con un numero maggiore della dimensione per indice.
	 * <br>
	 * <b>Design:</b> Il metodo set deve lanciare l'eccezione per valori negativi oppure maggiori o uguali alla dimensione, quindi viene testato il secondo caso.
	 * <br>
	 * <b>Descrizione:</b> Vengono aggiunti degli elementi alla lista, poi viene usato {@code la.size() + 1} come parametro del metodo {@code set(int index, Object element)}.
	 * <br>
	 * <b>Precondizioni:</b> La lista è non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non subisce modifiche.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionSetGreaterThanSize(){
		addElementsToListLa();
		la.set(la.size() + 1, "Elemento");
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.IndexOutOfBoundsException} del metodo {@code set(int index, Object element)} con la dimensione per indice.
	 * <br>
	 * <b>Design:</b> Il metodo set deve lanciare l'eccezione per valori negativi oppure maggiori o uguali alla dimensione, quindi viene testato il terzo caso.
	 * <br>
	 * <b>Descrizione:</b> Vengono aggiunti degli elementi alla lista, poi viene usato {@code la.size()} come parametro del metodo {@code set(int index, Object element)}.
	 * <br>
	 * <b>Precondizioni:</b> La lista è non vuota.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} non subisce modifiche.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.IndexOutOfBoundsException}.
	 */
	@Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void testExceptionSetSize(){
		addElementsToListLa();
		la.set(la.size(), "Elemento");
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.NullPointerException} del metodo {@code toArray(Object[] a)}, passando un array {@code null}.
	 * <br>
	 * <b>Design:</b> Il metodo {@code toArray(Object[] a} deve lanciare l'eccezione per parametri passati {@code null},
	 * quindi viene verificato che ciò succeda.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code toArray(Object[] a)} con parametro {@code null} sulla lista {@code la}.
	 * <br>
	 * <b>Precondizioni:</b> Il parametro passato deve essere nullo.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.NullPointerException}.
	 */
	@Test(expected=java.lang.NullPointerException.class)
	public void testExceptionToArrayNullPtr(){
		la.toArray(null);
	}

	/**Test per verificare il corretto lancio dell'eccezione {@code java.lang.ArrayStoreException} del metodo {@code toArray(Object[] a)}.
	 * <br>
	 * <b>Design:</b> Il metodo {@code toArray(Object[] a} deve lanciare l'eccezione {@code ArrayStoreException}
	 * passando come parametro un array il cui tipo non è un supertipo della classe di ogni oggetto della lista,
	 * quindi viene verificato che ciò succeda.
	 * <br>
	 * <b>Descrizione:</b> Viene riempita la lista {@code la} con degli oggetti di tipo String,
	 * poi viene creato un array di {@code Integer}, che viene usato come parametro nell'invocazione di
	 * {@code toArray(Object[] a)}.
	 * <br>
	 * <b>Precondizioni:</b> L'array passato non può essere di un supertipo di tutti gli oggetti di {@code la}.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Viene lanciata l'eccezione {@code java.lang.ArrayStorerException}.
	 */
	@Test(expected=java.lang.ArrayStoreException.class)
	public void testExceptionToArrayArrayStore(){
		la.add("Lorem");
		la.add("Ipsum");
		la.add("Dolor");
		la.add("Sit");
		
		Integer[] array = new Integer[la.size()];
		la.toArray(array);
	}

	/*---------------METODI AUSILIARI---------------*/

	/**Metodo ausiliario ai test che aggiunge due elementi (entrambi -1) alla lista {@code la}.
	 */
	public void addElementsToListLa(){
		int prevSize = la.size();
		boolean res = la.add("Lorem ipsum");
		assertEquals("La lista non ha dimensione" + (prevSize + 1) + ";", prevSize + 1, la.size());
		assertTrue("La lista non è stata modificata", res);
		res = la.add("dolor sit amet");
		assertEquals("La lista non ha dimensione 2;", prevSize + 2, la.size());
		assertTrue("La lista non è stata modificata", res);
	}

	/**Metodo ausiliario ai test che aggiunge gli elementi {@code [la.size(), la.size() + 9]} alla lista {@code la}.
	 * Gli elementi sono interi corrispondenti alla loro posizione (in posizione 0 si trova il numero 0,
	 * in posizione 1 il numero 1, ...).
	 */
	public void addTenElementsToListLa(){
		int prevSize = la.size();
		int dim = 10;
		for(int i = 0; i < dim; i++){
			boolean res = la.add(prevSize + i);
			assertEquals("La lista non ha dimensione " + (prevSize + i+1) + ";", prevSize + i + 1, la.size());
			assertTrue("La lista non è stata modificata", res);
			assertEquals("L'elemento inserito è errato;", prevSize + i, la.get(prevSize + i));
		}
	}	
}
