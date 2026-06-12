package myAdapter;

/**Un iteratore per liste che permette di scorrere la lista in avanti e indietro, modificare la lista durante l'iterazione, e ottenere la posizione corrente dell'iteratore nella lista. 
 * Un {@code HListIterator} non ha alcun elemento corrente, la posizione del suo cursore si trova tra l'elemento che verrebbe restituito chiamando {@code previous()} e l'elemento che verrebbe restituito chiamando {@code next()}. 
 * In una lista di {@code n} elementi, gli indici validi per un iteratore si trovano nell'intervallo {@code [0,n+1]} (gli estremi sono inclusi).
 * <br>
 * In quanto questa interfaccia è stata creata espressamente per non essere in conflitto con quella ufficiale di Java,
 * per l'implementazione della classe {@code HListIterator} di questo progetto,
 * e siccome è previsto di implementare tutte le optional operations,
 * nella documentazione non viene segnato che alcuna funzione possa lanciare l'eccezione {@code UnsupportedOperationException}.
 */
public interface HListIterator extends HIterator{
	/**Inserisce l'elemento specificato nella lista.
	 * L'elemento è inserito subito prima dell'elemento che sarebbe restituito invocando {@code next} (se presente) e subito dopo l'elemento che sarebbe restituito invocando {@code previous} (se presente). 
	 * (Se la lista non contiene elementi, l'elemento da inserire diventa l'unico elemento della lista.) 
	 * L'elemento viene inserito prima del cursore, quindi il risultato un'eventuale chiamata a {@code next} non varierebbe, mentre un'eventuale chiamata a {@code previous} (successiva all'inserimento) ritornerebbe il nuovo elemento. 
	 * (La chiamata aumenta di uno il valore che sarebbe restituito da {@code nextIndex} e {@code previousIndex}.)
	 *
	 * @param o l'elemento da inserire.
	 * @throws java.lang.IllegalArgumentException se l'elemento non può essere aggiunto alla lista.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato gli impedisce di essere aggiunto alla lista.
	 */
	void add(Object o);

	/**Restituisce {@code true} se esiste un elemento successivo al cursore nella lista.
	 *
	 * @return {@code true} se esiste un elemento successivo al cursore nella lista, altrimenti {@code false}.
	 */
	boolean hasNext();

	/**Restituisce {@code true} se esiste un elemento precedente al cursore nella lista.
	 *
	 * @return {@code true} se esiste un elemento precedente al cursore nella lista, altrimenti {@code false}.
	 */
	boolean hasPrevious();

	/**Restituisce l'elemento successivo al cursore nella lista.
	 * 
	 * @return l'elemento successivo al cursore nella lista.
	 * @throws java.util.NoSuchElementException se non ci sono elementi dopo il cursore.
	 */
	Object next();

	/**Restituisce l'indice dell'elemento che sarebbe restituito chiamando {@code next}.
	 * (Restituisce la dimensione della lista se l'iteratore è alla fine della stessa.)
	 *
	 * @return l'indice dell'elemento che sarebbe restituito chiamando {@code next}, altrimenti la dimensione della lista se l'iteratore è alla fine della stessa.
	 */
	int nextIndex();

	/**Restituisce l'elemento precedente al cursore nella lista.
	 * Il metodo può essere chiamato più volte per scorrere la lista al contrario, oppure può essere chiamato alternandolo a {@code next} per andare avanti e indietro.
	 *
	 * @return l'elemento precedente al cursore nella lista.
	 * @throws java.util.NoSuchElementException se non ci sono elementi prima del cursore.
	 */
	Object previous();

	/**Restituisce l'indice dell'elemento che sarebbe restituito chiamando {@code previous}.
	 * (Restituisce -1 se l'iteratore è all'inizio della stessa.)
	 *
	 * @return l'indice dell'elemento che sarebbe restituito chiamando {@code previous}, altrimenti -1 se l'iteratore è all'inizio della lista.
	 */
	int previousIndex();

	/**Rimuove dalla lista l'ultimo elemento che è stato restituito da {@code next} o {@code previous}. 
	 * Si può chiamare il metodo solo una volta per chiamata a {@code next} o {@code previous}. 
	 * Si può chiamare il metodo solo se {@code HListIterator.add} non è stato chiamato dall'ultima chiamata a {@code next} o {@code previous}.
	 *
	 * @throws java.lang.IllegalStateException se né {@code next} né {@code previous} sono stati chiamati, oppure se {@code remove} o {@code add} sono stati chiamati dall'ultima chiamata a {@code next} o {@code previous}.
	 */
	void remove();

	/**Rimpiazza l'ultimo elemento restituito da {@code next} o {@code previous} con l'elemento specificato.
	 * La chiamata può essere effettuata solo se né {@code HListIterator.remove} né {@code HListIterator.add} sono stati chiamati dall'ultima chiamata di {@code next} o {@code previous}.
	 *
	 * @param o l'elemento da inserire.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato gli impedisce di essere aggiunto alla lista.
	 * @throws java.lang.IllegalStateException se né {@code next} né {@code previous} sono stati chiamati, oppure se {@code remove} o {@code add} sono stati chiamati dall'ultima chiamata a {@code next} o {@code previous}.
	 * @throws java.lang.IllegalArgumentException se l'elemento non può essere aggiunto alla lista.
	 */
	void set(Object o);
}
