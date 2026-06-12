package myTest;

import org.junit.*;
import static org.junit.Assert.*;

/**<p>Test case per le implementazioni delle interfacce {@code HIterator} e {@code HListIterator}.
 * Verifica il corretto funzionamento delle implementazioni,
 * in linea con la documentazione ufficiale (J2SE 1.4.2).
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
 * <li>Verifica metodi {@code hasNext}, {@code next} e {@code remove} di {@code HIterator}.
 * <li>Verifica lancio delle eccezioni appropriate da parte di {@code next} e {@code remove} di {@code HIterator}.
 * <li>Verifica che {@code HListIterator} sia {@code HIterator}.
 * <li>Verifica che il metodo {@code listIterator(index int)} funzioni correttamente.
 * <li>Verifica metodi {@code hasNext}, {@code next}, {@code hasPrevious} e {@code previous} di {@code HListIterator}.
 * <li>Verifica metodi {@code nextIndex} e {@code previousIndex} di {@code HListIterator}.
 * <li>Verifica metodi {@code remove}, {@code add} e {@code set} di {@code HListIterator}.
 * <li>Verifica lancio delle eccezione appropriate da parte dei metodi di {@code HListIterator}.
 * </ul>
 *
 * <p>
 * Il test case si occupa di verificare che le implementazioni delle interfacce {@code HIterator} e {@code HListIterator}
 * funzionino correttamente nei casi standard, nei casi limite e nei casi in cui devono esser lanciate eccezioni.
 * <br>
 * Tramite una lista di appoggio ({@code la}, "ListAdapter") vengono svolti i metodi,
 * i quali sono un campione rappresentativo dei risultati possibili per ogni esecuzione.
 * Vengono eseguiti {@code 24} test.
 * </p>
 *
 * <p>
 * Nei vari test verranno usati gli attributi della classe: {@code la} ("ListAdapter"),
 * {@code it} ("ITerator") e {@code li} ("ListIterator").
 * <br>
 * Con il metodo {@code setup}, eseguito prima di ogni test,
 * viene inizializzata {@code la} a una lista di dieci interi (ognuno con il valore corrispondente alla propria posizione),
 * mentre {@code it} e {@code li} sono inizializzati rispettivamente come {@code HIterator} (tramite {@code la.iterator()}) e {@code HListIterator} (tramite {@code la.listIterator}); 
 * così entrambi puntano al primo elemento della lista.
 * </p>
 */
public class TestIterators{

	/**Lista di appoggio per gli iteratori.
	 * ("ListAdapter")
	 */
	private myAdapter.ListAdapter la;

	/**Iteratore HIterator.
	 */
	private myAdapter.HIterator it;

	/**Iteratore HListIterator.
	 */
	private myAdapter.HListIterator li;

	/**Costruttore di default.
	 * Inizializza a {@code null} gli attributi della classe.
	 */
	public TestIterators(){
		la = null;
		it = null;
		li = null;
	}

	/*---------------SETUP e TEARDOWN---------------*/

	/**Inizializzazione delle variabili, eseguita prima di ogni test method.
	 * Viene creata la lista {@code la} con il costruttore di default,
	 * quindi è inizialmente vuota, poi vengono inizializzati gli iteratori tramite i metodi
	 * {@code iterator()} e {@code listIterator}.
	 * <br>
	 * <b>Design:</b> Viene inizializzata la lista {@code la} con dieci elementi noti
	 * (interi corrispondenti alla posizione in cui si trovano),
	 * così da poter essere utilizzata come lista di appoggio per i metodi di test degli iteratori.
	 * <br>
	 * <b>Descrizione:</b> Viene inizializzata {@code la} con il costruttore di default,
	 * poi viene riempita con dieci interi, ognuno corrispondente alla posizione in cui si trova.
	 * Vengono poi inizializzati {@code it} e {@code li} tramite {@code la.iterator()} e {@code la.listIterator()},
	 * in questo modo punteranno al primo elemento di {@code la}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la}, {@code it} e {@code li} sono stati inizializzati.
	 * <br>
	 * <b>Risultati attesi:</b> {@code la} è una lista con dieci elementi (interi corrispondenti alle proprie posizioni), e {@code it} e {@code li} sono iteratori che puntano al primo elemento di {@code la}.
	 */
	@Before
	public void setup(){
		System.out.println("Setup");

		la = new myAdapter.ListAdapter();

		for(int i = 0; i < 10; i++){
			boolean res = la.add(i);
			assertEquals("La lista non ha dimensione " + i + 1 + ";", i + 1, la.size());
			assertTrue("La lista non è stata modificata;", res);
			assertEquals("L'elemento inserito è errato;", i, la.get(i));
		}

		it = la.iterator();
		li = la.listIterator();
	}

	/**Assegnazione a {@code null} degli attrubuti, eseguita alla fine di ogni test method.
	 * <br>
	 * <b>Design:</b> Dopo ogni test le variabili vengono riportate allo stato in cui si trovavano subito dopo aver creato l'istanza della classe.
         * <br>
         * <b>Descrizione:</b> Vengono poste uguali a {@code null} la lista {@code la} e gli iteratori {@code it},{@code li}.
         * <br>
         * <b>Precondizioni:</b> Aver creato un'istanza della classe TestSubListAdapter.
         * <br>
         * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
         * <br>
         * <b>Risultati attesi:</b> {@code la}, {@code it} e {@code li} sono {@code null}.
	 */
	@After
	public void teardown(){
		System.out.println("Teardown");

		la = null;
		it = null;
		li = null;
	}

	/*---------------HITERATOR---------------*/

	/**Test per verificare che i metodi {@code hasNext} e {@code next} di {@code HIterator} funzionino correttamente.
	 * <br>
	 * <b>Design:</b> Per ogni elemento di {@code la} viene testato che i metodi {@code hasNext} e {@code next} funzionino correttamente, 
	 * quindi che {@code hasNext} restituisca {@code true} per tutti gli elementi tranne l'ultimo,
	 * e che {@code next} restituisca l'elemento corretto per ogni elemento presente
	 * (quindi solo finché {@code hasNext} restituisce {@code true}.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code hasNext}, se restituisce {@code true} viene seguito da {@code next},
	 * tante volte quanti sono gli elementi di {@code la}.
	 * Ad ogni iterazione viene controllato che {@code hasNext} restituisca {@code true} 
	 * e che {@code next} restituisca l'elemento corretto, tranne per l'ultima iterazione,
	 * dove si controlla che {@code hasNext} restituisca {@code false}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> {@code hasNext} restituirà {@code true} per tutte le iterazioni (tranne l'ultima) e {@code next} restituirà gli elementi corretti.
	 */
	@Test
	public void testHIteratorNext(){
		for(int i = 0; i < la.size(); i++){
			assertTrue("Non ci sono elementi dopo il numero " + i + ";", it.hasNext());
			assertEquals("L'elemento selezionato non è corretto;", i, it.next());
		}

		assertFalse("Ci sono elementi oltre quelli aggiunti originariamente;", it.hasNext());
	}

	/**Test per verificare che il metodo {@code remove} di {@code HIterator} funzioni correttamente.
	 * <br>
	 * <b>Design:</b> Per ogni elemento di {@code la} viene testato il metodo {@code remove},
	 * svuotando quindi la lista.
	 * <br>
	 * <b>Descrizione:</b> Per ogni elemento di {@code la} viene invocato prima il metodo {@code next},
	 * poi {@code remove} per rimuovere l'elemento.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> La lista sarà vuota.
	 * <br>
	 * <b>Risultati attesi:</b> {@code remove} eliminerò tutti gli elementi di {@code la}.
	 */
	@Test
	public void testHIteratorRemove(){
		int size = la.size();
		while(it.hasNext()){
			it.next();
			it.remove();
			size--;
			assertEquals("La dimensione della lista non è diminuita di uno;", size, la.size());
		}
	}

	/*---------------HLISTITERATOR---------------*/

	/*-----ISTANZA di HITERATOR e INIZIALIZZAZIONE CON INDICE-----*/
	/**Test per verificare che l'oggetto {@code HListIterator} sia un'istanza di {@code HIterator}.
	 * <br>
	 * <b>Design:</b> L'interfaccia {@code HListIterator} deve estendere l'interfaccia {@code HIterator},
	 * quindi si verifica ciò tramite l'oggetto di tipo {@code HListIterator}.
	 * <br>
	 * <b>Descrizione:</b> Si controlla che {@code li} sia un'istanza di {@code HIterator} (con {@code instanceof}.
	 * <br>
	 * <b>Precondizioni:</b> Aver crato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Viene correttamente verificato che {@code li} è un'istanza di {@code HIterator}.
	 */
	@Test
	public void testIsHListIteratorInstanceOfHIterator(){
		assertTrue("L'oggetto di tipo myAdapter.HListIterator non è un'istanza di myAdapter.HIterator;", li instanceof myAdapter.HIterator);
	}

	/**Test per verificare che il metodo {@code listIterator(int index)} restituisca l'iteratore in posizione corretta.
	 * <br>
	 * <b>Design:</b> Il metodo {@code listIterator(int index)} deve creare un iteratore
	 * il cui cursore deve trovarsi tra gli elementi in posizione {@code index - 1} e {@code index},
	 * quindi viene verificato tramite i metodi {@code previousIndex} e {@code nextIndex}.
	 * <br>
	 * <b>Descrizione:</b> Per ogni elemento di {@code la}, viene assegnato a {@code li} il risultato di {@code la.listIterator(index)}, 
	 * poi viene verificato che gli indici precedente e successivo al cursore siano quelli corretti 
	 * tramite i metodi {@code hasPrevious} e {@code hasNext}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Per ogni iterazione, gli indici restituiti da {@code previousIndex} e {@code nextIndex} saranno quelli previsti.
	 */
	@Test
	public void testHListIteratorWithIndex(){
		for(int i = 0; i < la.size(); i++){
			li = la.listIterator(i);
			assertEquals("L'indice precedente non è quello previsto;", i - 1, li.previousIndex());
			assertEquals("L'indice successivo non è quello previsto;", i, li.nextIndex());
		}
	}

	/*-----HASNEXT, NEXT, HASPREVIOUS, PREVIOUS, NEXTINDEX e PREVIOUSINDEX-----*/

	/**Test per verificare che i metodi {@code hasNext}, {@code next}, {@code hasPrevious} e {@code previous} di {@code HListIterator} funzionino correttamente.
	 * <br>
	 * <b>Design:</b> Per ogni elemento di {@code la} viene testato che i metodi {@code hasNext}, {@code next}, {@code hasPrevious} e {@code previous} funzionino correttamente, 
	 * quindi che {@code hasNext} restituisca {@code true} per tutti gli elementi tranne l'ultimo,
	 * che {@code hasPrevious} restituisca {@code true} per tutti gli elementi tranne il primo,
	 * che {@code next} restituisca l'elemento corretto per ogni elemento presente
	 * (quindi solo finché {@code hasNext} restituisce {@code true})
	 *  e che {@code previous} restituisca l'elemento corretto per ogni elemento presente
	 * (quindi solo finché {@code hasPrevious} restituisce {@code true}).
	 * <br>
	 * <b>Descrizione:</b> Siccome {@code li} punta all'elemento zero (per {@code setup}),
	 * viene controllato prima che {@code hasPrevious} restituisca {@code false},
	 * poi viene invocato il metodo {@code hasNext}, se restituisce {@code true} viene seguito da {@code next},
	 * tante volte quanti sono gli elementi di {@code la}.
	 * Arrivato alla fine della lista, viene verificato che {@code hasNext} restituisca {@code false},
	 * poi viene invocato il metodo {@code hasPrevioud}, se restituisce {@code true} viene seguito da {@code previous},
	 * tante volte quanti sono gli elementi di {@code la}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> {@code hasNext} restituirà {@code true} per tutte le iterazioni (tranne l'ultima) e {@code next} restituirà gli elementi corretti.
	 */
	@Test
	public void testHListIteratorNextAndPrevious(){
		assertFalse("Ci sono elementi prima di quello puntato in zero;", li.hasPrevious());

		for(int i = 0; i < la.size(); i++){
			assertTrue("Non ci sono elementi dopo il numero " + i + ";", li.hasNext());
			assertEquals("L'elemento selezionato non è corretto;", i, li.next());
		}

		assertFalse("Ci sono elementi oltre quelli aggiunti originariamente;", li.hasNext());

		for(int i = 0; i < la.size(); i++){
			assertTrue("Non ci sono elementi prima del numero " + (la.size() - i - 1) + ";", li.hasPrevious());
			assertEquals("L'elemento selezionato non è corretto;", la.size() - i - 1, li.previous());
		}
	}

	/**Test per verificare che i metodi {@code nextIndex} e {@code previousIndex} funzionino correttamente.
	 * <br>
	 * <b>Design:</b> Viene testato che i metodi {@code nextIndex} e {@code previousIndex}
	 * funzionino correttamente per ogni indice di {@code la}
	 * <br>
	 * <b>Descrizione:</b> Per ogni indice di {@code la} vengono invocati i metodi {@code nextIndex} e {@code previousIndex},
	 * verificando che i risultati resituiti siano corretti.
	 * Poi viene chiamato {@code next} per far avanzare l'iteratore.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> I metodi {@code nextIndex} e {@code previousIndex} restituiranno correttamente gli indici corrispondenti.
	 */
	@Test
	public void testHListIteratorPreviousIndexAndNextIndex(){
		int i = 0;
		while(li.hasNext()){
			assertEquals("L'indice precedente è scorretto;", i - 1, li.previousIndex());
			assertEquals("L'indice successivo è scorretto;", i, li.nextIndex());
			li.next();
			i++;
		}
	}

	/*-----REMOVE, ADD e SET-----*/

	/**Test per verificare che il metodo {@code remove} di {@code HListIterator} funzioni correttamente dopo chiamate a {@code next}.
	 * <br>
	 * <b>Design:</b> Finché {@code li.hasNext} viene testato il metodo {@code remove},
	 * partendo da {@code li = 0} (per {@code setup}) e chiamando {@code next}
	 * (dimezzando quindi la lista).
	 * <br>
	 * <b>Descrizione:</b> Finché {@code li.hasNext} viene invocato prima il metodo {@code next},
	 * poi {@code remove} per rimuovere l'elemento.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> La lista sarà vuota.
	 * <br>
	 * <b>Risultati attesi:</b> {@code remove} elimina metà gli elementi di {@code la}.
	 */
	@Test
	public void testHListIteratorRemoveAfterNext(){
		int size = la.size();
		while(li.hasNext()){
			li.next();
			li.remove();
			size--;
			assertEquals("La dimensione della lista non è diminuita di uno;", size, la.size());
		}
	}

	/**Test per verificare che il metodo {@code remove} di {@code HListIterator} funzioni correttamente dopo chiamate a {@code previous}.
	 * <br>
	 * <b>Design:</b> Finché {@code li.hasPrevious} viene testato il metodo {@code remove},
	 * partendo da {@code li = la.listIterator(la.size())} e chiamando {@code previous}
	 * (dimezzando quindi la lista).
	 * <br>
	 * <b>Descrizione:</b> Finché {@code li.hasPrevious} viene invocato prima il metodo {@code previous},
	 * poi {@code remove} per rimuovere l'elemento.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> La lista sarà vuota.
	 * <br>
	 * <b>Risultati attesi:</b> {@code remove} elimina tutti gli elementi di {@code la} correttamente.
	 */
	@Test
	public void testHListIteratorRemoveAfterPrevious(){
		int size = la.size();
		li = la.listIterator(size - 1);
		while(li.hasPrevious()){
			li.previous();
			li.remove();
			size--;
			assertEquals("La dimensione della lista non è diminuita di uno;", size, la.size());
		}
	}

	/**Test per verificare che {@code add} aggiunga un elemento per ogni elemento originale di {@code la}.
	 * <br>
	 * <b>Design:</b> Deve essere possibile invocare {@code add} da ogni posizione della lista,
	 * quindi viene testato iterando con {@code next} e {@code hasNext},
	 * effettivamente interponendo un nuovo elemento tra ogni elemento della lista originale.
	 * <br>
	 * <b>Descrizione:</b> Finché {@code li.hasNext},
	 * vengono chiamati {@code add} e {@code next} alternandosi,
	 * poi viene verificato che l'elemento restituito a {@code nextIndex} non sia stato modificato,
	 * mentre quello restituito a {@code previousIndex} sia quello appena inserito.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} avrà dimensione doppia rispetto a quella di partenza.
	 * <br>
	 * <b>Risultati attesi:</b> {@code add} sarà in grado di inserire un elemento aggiuntivo per ogni elemento originale di {@code la}.
	 */
	@Test
	public void testHListIteratorAddManyElements(){
		String el = "Nuovo elemento";
		int i = 0;
		while(li.hasNext()){
			li.add(el);
			assertEquals("L'elemento precedente non è quello inserito;", el, la.get(li.previousIndex()));
			assertEquals("L'elemento successivo è variato;", i, la.get(li.nextIndex()));
			li.next();
			i++;
		}
	}

	/**Test per verificare che si possano chiamare più {@code add} consecutivi.
	 * <br>
	 * <b>Design:</b> Al contrario di {@code remove}, si può chiamare {@code add} più volte di seguito,
	 * quindi viene verificato che questo caso funzioni correttamente.
	 * Gli elementi verranno aggiunti in testa.
	 * <br>
	 * <b>Descrizione:</b> Viene chiamato {@code add} 
	 * (senza chiamare prima o dopo {@code next} o {@code previous}) per dieci volte,
	 * verificando che la dimensione della lista sia cambiata correttamente.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Alla fine saranno stati aggiunti dieci elementi a {@code la}.
	 */
	@Test
	public void testHListIteratorAddInSequence(){
		int initSize = la.size(), size = la.size();
		int old = (int) la.get(0);
		String el = "Elemento";
		for(int i = 0; i < initSize; i++){
			li.add(el);
			assertEquals("L'elemento precedente non è quello inserito;", el, la.get(li.previousIndex()));
			assertEquals("L'elemento successivo è variato;", old, la.get(li.nextIndex()));
			assertEquals("La dimensione non è aumentata di uno;", size + 1, la.size());
			size = la.size();
		}
	}

	/**Test per verificare che aggiungendo con {@code add} un elemento a una lista vuota, questo sia l'unico elemento.
	 * <br>
	 * <b>Design:</b> Come specificato dalla documentazione,
	 * aggiungengo un elemento tramite {@code li.add} a una lista vuota, l'elemento dovrà essere l'unico presente in essa.
	 * Viene quindi verificato questo caso.
	 * <br>
	 * <b>Descrizione:</b> Viene inizializzata {@code la} con il costruttore di default,
	 * così che sia una lista vuota, poi viene aggiunto un elemento tramite {@code li.add},
	 * infine viene verificato che sia l'unico presente.
	 * <br>
	 * <b>Precondizioni:</b> {@code la} deve essere una lista vuota.
	 * <br>
	 * <b>Postcondizioni:</b> {@code la} sarà una lista con un solo elemento.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà aggiunto un elemento a {@code la}.
	 */
	@Test
	public void testHListIteratorAddToEmptyList(){
		la = new myAdapter.ListAdapter();
		li = la.listIterator();

		String el = "Elemento";
		li.add(el);

		assertEquals("La lista non ha dimensione uno;", 1, la.size());
		assertEquals("L'elemento inserito non corrisponde;", el, la.get(0));
	}

	/**Test per verificare che {@code set} sia in grado di modificare tutti gli elementi di una lista.
	 * <br>
	 * <b>Design:</b> Il metodo {@code set} deve essere in grado di rimpiazzare gli elementi di una lista,
	 * a prescindere dalla loro posizione, quindi viene testato che funzioni secondo le specifiche.
	 * <br>
	 * <b>Descrizione:</b> Iterando con {@code hasNext} e {@code next},
	 * viene chiamato {@code li.set(Object o)} per ogni elemento di {@code la}.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza di TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Ogni elemento di {@code la} è diverso rispetto a prima della chiamata del metodo.
	 * <br>
	 * <b>Risultati attesi:</b> {@code set} sarà in grado di modificare ogni elemento della lista.
	 */
	@Test
	public void testHListIteratorSet(){
		String el = "Elemento";
		int i = 0;
		while(li.hasNext()){
			li.next();
			li.set(el);
			assertEquals("L'elemento non è stato modificato;", el, la.get(i));
			i++;
		}
	}

	/*---------------ECCEZIONI---------------*/

	/**Test per verificare che {@code next} di {@code HIterator} lanci correttamente l'eccezione {@code NoSuchElementException}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di accedere a un elemento
	 * quando non ce ne sono più presenti di successivi.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code next} un numero di volte pari alla dimensione di {@code la},
	 * poi una volta in più perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.util.NoSuchElementException}.
	 */
	@Test(expected=java.util.NoSuchElementException.class)
	public void testExceptionHIteratorNoSuchElement(){
		for(int i = 0; i < la.size() + 1; i++){
			it.next();
		}
	}

	/**Test per verificare che {@code remove} di {@code HIterator} lanci correttamente l'eccezione {@code IllegalStateException} senza aver chiamato {@code next} prima.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di rimuovere un elemento
	 * quando non è stato chiamato in precedenza il metodo {@code next}.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code remove} senza chiamare prima {@code next}
	 * perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IllegalStateException}.
	 */
	@Test(expected=java.lang.IllegalStateException.class)
	public void testExceptionHIteratorRemoveNoNext(){
		it.remove();
	}

	/**Test per verificare che {@code remove} di {@code HIterator} lanci correttamente l'eccezione {@code IllegalStateException} invocandolo due volte di seguito.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di rimuovere un elemento due volte di seguito
	 * quando è stato chiamato il metodo {@code next} solo una volta.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code remove} chiamando solo una volta {@code next}
	 * perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IllegalStateException}.
	 */
	@Test(expected=java.lang.IllegalStateException.class)
	public void testExceptionHIteratorRemoveOneNext(){
		it.next();
		it.remove();
		it.remove();
	}

	/*-----HListIterator-----*/

	/**Test per verificare che {@code next} di {@code HListIterator} lanci correttamente l'eccezione {@code NoSuchElementException}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di accedere a un elemento
	 * quando non ce ne sono più presenti di successivi.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene inizializzato {@code li} con {@code la.listIterator(la.size())},
	 * poi viene chiamato {@code next} perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.util.NoSuchElementException}.
	 */
	@Test(expected=java.util.NoSuchElementException.class)
	public void testExceptionHListIteratorNextNoSuchElement(){
		li = la.listIterator(la.size());
		li.next();
	}

	/**Test per verificare che {@code previous} di {@code HListIterator} lanci correttamente l'eccezione {@code NoSuchElementException}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di accedere a un elemento
	 * quando non ce ne sono più presenti di precedenti.
	 * Tramite {@code setup}, {@code li} si trova sul primo elemento,
	 * quindi non ha elementi precedenti.
	 * Viene verificato che il metodo funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code previous} perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.util.NoSuchElementException}.
	 */
	@Test(expected=java.util.NoSuchElementException.class)
	public void testExceptionHListIteratorPreviousNoSuchElement(){
		li.previous();
	}

	/*---remove---*/
	
	/**Test per verificare che {@code remove} di {@code HListIterator} lanci correttamente l'eccezione {@code IllegalStateException} senza aver chiamato {@code next} o {@code previous} prima.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di rimuovere un elemento
	 * quando non è stato chiamato in precedenza il metodo {@code next} o {@code previous}.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code remove} senza chiamare prima {@code next} o {@code previous}
	 * perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IllegalStateException}.
	 */
	@Test(expected=java.lang.IllegalStateException.class)
	public void testExceptionHListIteratorRemoveNoNextNoPrevious(){
		li.remove();
	}

	/**Test per verificare che {@code remove} di {@code HListIterator} lanci correttamente l'eccezione {@code IllegalStateException} invocandolo due volte di seguito dopo {@code next}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di rimuovere un elemento due volte di seguito
	 * quando è stato chiamato il metodo {@code next} solo una volta.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code remove} chiamando solo una volta {@code next}
	 * perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IllegalStateException}.
	 */
	@Test(expected=java.lang.IllegalStateException.class)
	public void testExceptionHListIteratorRemoveOneNext(){
		li.next();
		li.remove();
		li.remove();
	}

	/**Test per verificare che {@code remove} di {@code HListIterator} lanci correttamente l'eccezione {@code IllegalStateException} invocandolo due volte di seguito dopo {@code previous}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di rimuovere un elemento due volte di seguito
	 * quando è stato chiamato il metodo {@code previous} solo una volta.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code remove} chiamando solo una volta {@code previous}
	 * perché lanci l'eccezione. Viene inizializzato {@code li} a {@code la.listIterator(la.size() - 1)},
	 * così che esista un elemento precedente.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IllegalStateException}.
	 */
	@Test(expected=java.lang.IllegalStateException.class)
	public void testExceptionHListIteratorRemoveOnePrevious(){
		li = la.listIterator(la.size() - 1);
		li.previous();
		li.remove();
		li.remove();
	}

	/**Test per verificare che {@code remove} di {@code HListIterator} lanci correttamente l'eccezione {@code IllegalStateException} invocandolo in seguito ad {@code add}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di rimuovere un elemento 
	 * quando è stato chiamato il metodo {@code add} prima di sé, 
	 * senza aver poi chiamato {@code next} o {@code previous}.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code next} seguito da {@code add},
	 * poi viene chiamato {@code remove} perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IllegalStateException}.
	 */
	@Test(expected=java.lang.IllegalStateException.class)
	public void testExceptionHListIteratorRemoveAfterAdd(){
		li.next();
		li.add(null);
		li.remove();
	}

	/*-----set-----*/

	/**Test per verificare che {@code set} di {@code HListIterator} lanci correttamente l'eccezione {@code IllegalStateException} senza aver chiamato {@code next} o {@code previous} prima.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di rimuovere un elemento
	 * quando non è stato chiamato in precedenza il metodo {@code next} o {@code previous}.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code set} senza chiamare prima {@code next} o {@code previous}
	 * perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IllegalStateException}.
	 */
	@Test(expected=java.lang.IllegalStateException.class)
	public void testExceptionHListIteratorSetNoNextNoPrevious(){
		li.set(null);
	}

	/**Test per verificare che {@code set} di {@code HListIterator} lanci correttamente l'eccezione {@code IllegalStateException} invocandolo in seguito ad {@code add}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di rimuovere un elemento 
	 * quando è stato chiamato il metodo {@code add} prima di sé, 
	 * senza aver poi chiamato {@code next} o {@code previous}.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code next} seguito da {@code add},
	 * poi viene chiamato {@code set} perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IllegalStateException}.
	 */
	@Test(expected=java.lang.IllegalStateException.class)
	public void testExceptionHListIteratorSetAfterAdd(){
		li.next();
		li.add(null);
		li.set("Elemento");
	}

	/**Test per verificare che {@code remove} di {@code HListIterator} lanci correttamente l'eccezione {@code IllegalStateException} invocandolo in seguito ad {@code add}.
	 * <br>
	 * <b>Design:</b> Il metodo deve lanciare l'eccezione quando si tenta di rimuovere un elemento 
	 * quando è stato chiamato il metodo {@code remove} prima di sé, 
	 * senza aver poi chiamato {@code next} o {@code previous}.
	 * Quindi viene verificato che funzioni correttamente.
	 * <br>
	 * <b>Descrizione:</b> Viene invocato il metodo {@code next} seguito da {@code remove},
	 * poi viene chiamato {@code set} perché lanci l'eccezione.
	 * <br>
	 * <b>Precondizioni:</b> Aver creato un'istanza della classe TestIterators.
	 * <br>
	 * <b>Postcondizioni:</b> Non vi sono modifiche alle condizioni.
	 * <br>
	 * <b>Risultati attesi:</b> Verrà lanciata l'eccezione {@code java.lang.IllegalStateException}.
	 */
	@Test(expected=java.lang.IllegalStateException.class)
	public void testExceptionHListIteratorSetAfterRemove(){
		li.next();
		li.remove();
		li.set(null);
	}
}
