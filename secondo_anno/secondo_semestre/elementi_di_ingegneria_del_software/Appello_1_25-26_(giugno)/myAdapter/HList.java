package myAdapter;

/**Una collezione ordinata (anche detta <i>sequenza</i>). 
 * L'utente ha controllo preciso nell'inserimento di ogni elemento. 
 * L'utente può accedere agli elementi tramite la posizione nella lista, e cercare elementi nella lista.
 * <br>
 * Sono permessi elementi duplicati, ed elementi {@code null} multipli (se permessi).
 * <br>
 * In quanto questa interfaccia è stata creata espressamente per non essere in conflitto con quella ufficiale di Java,
 * per l'implementazione della classe {@code ListAdapter} di questo progetto,
 * e siccome è previsto di implementare tutte le optional operations,
 * nella documentazione non viene segnato che alcuna funzione possa lanciare l'eccezione {@code UnsupportedOperationException}.
 */
public interface HList extends HCollection{
	/**Inserisce l'elemento specificato alla posizione specificata nella lista. 
	 * Sposta a destra di uno tutti gli elementi a destra del cursore.
	 *
	 * @param index indice dove inserire l'elemento.
	 * @param element l'elemento da inserire.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato gli impedisce di essere aggiunto alla lista.
	 * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la lista non supporta elementi nulli.
	 * @throws java.lang.IllegalArgumentException se degli aspetti dell'elemento gli impediscono di essere aggiunto alla lista.
	 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index > size()}).
	 */
	void add(int index, Object element);

	/**Aggiunge in coda alla lista l'elemento specificato.
	 *
	 * @param o l'elemento da aggiungere alla lista.
	 * @return {@code true} se la lista è stata modificata in seguito alla chiamata del metodo, {@code false} altrimenti.
	 *
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato gli impedisce di essere aggiunto alla lista.
	 * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la lista non supporta elementi nulli.
	 * @throws java.lang.IllegalArgumentException se degli aspetti dell'elemento gli impediscono di essere aggiunto alla lista.
	 */
	boolean add(Object o);

	/**Aggiunge tutti gli elementi della collezione specificata a questa lista, nell'ordine in cui sono restituiti dall'iteratore della collezione.
	 *
	 * @param c gli elementi da inserire nella lista.
	 * @return {@code true} se la lista è stata modificata in seguito alla chiamata di questo metodo.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato gli impedisce di essere aggiunto alla lista.
	 * @throws java.lang.NullPointerException se la collezione è {@code null} o se contiene elementi nulli e la lista non supporta elementi nulli.
	 * @throws java.lang.IllegalArgumentException se degli aspetti dell'elemento gli impediscono di essere aggiunto alla lista.
	 */
	boolean addAll(HCollection c);

	/**Inserisce tutti gli elementi della collezione specificata nella lista a partire dalla posizione specificata. 
	 * Sposta l'elemento nella posizione corrente (se presente) e tutti gli elementi alla sua destra. 
	 * Gli elementi saranno inseriti nell'ordine in cui sono restituiti dall'iteratore.
	 *
	 * @param index l'indice in cui inserire il primo elemento della collezione specificata.
	 * @param c elementi da inserire nella lista.
	 * @return {@code true} se la lista è stata modificata dalla chiamata del metodo.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato gli impedisce di essere aggiunto alla lista.
	 * @throws java.lang.NullPointerException se la collezione specificata contiene elementi {@code null} e la lista non li supporta, oppure se la collezione è {@code null}.
	 * @throws java.lang.IllegalArgumentException se degli aspetti di uno degli elementi della collezione specificata gli impediscono di essere aggiunto alla lista.
	 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index > size()}).
	 */
	boolean addAll(int index, HCollection c);

	/**Rimuove tutti gli elementi della lista.
	 * La lista sarà vuota alla fine dell'esecuzione di questo metodo, a meno che non venga lanciata un'eccezione.
	 */
	void clear();

	/**Restituisce {@code true} se la lista contiene l'elemento specificato.
	 * In particolare, restituisce {@code true} se e solo se la lista contiene almeno un elemento {@code e} tale che {@code (o == null ? e == null : o.equals(e))}.
	 *
	 * @param o l'elemento la cui presenza nella lista è da verificare.
	 * @return {@code true} se la lista contiene l'elemento specificato.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la lista.
	 * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la lista non supporta elementi nulli.
	 */
	boolean contains(Object o);

	/**Restituisce {@code true} se la lista contiene tutti gli elementi nella collezione specificata.
	 *
	 * @param c la collezione i cui elementi verranno cercati nella lista.
	 * @return {@code true} se la lista contiene tutti gli elementi della collezione specificata.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la lista.
	 * @throws java.lang.NullPointerException se la collezione specificata è {@code null} o se la lista non supporta elementi nulli.
	 */
	boolean containsAll(HCollection c);

	/**Paragona l'oggetto specificato con la lista e ne verifica l'uguaglianza.
	 * Restituisce {@code true} se e solo se l'oggetto specificato è una lista, entrambe le liste hanno la stessa dimensione e tutti gli elementi corrispondenti delle due liste sono {@code equals} se ({@code (e1==null ? e2==null : e1.equals(e2))}).
	 *
	 * @param o {@code Object} da paragonare alla lista.
	 */
	boolean equals(Object o);

	/**Restituisce l'elemento alla posizione specificata nella lista.
	 *
	 * @param index l'indice dell'elemento da restituire.
	 * @return l'elemento della lista alla posizione specificata.
	 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index >= size()}).
	 */
	Object get(int index);

	/**Restituisce l'hash code della lista. L'hash code della lista è il risultato del seguente calcolo:
	 * <pre>
	 * <code>hashCode = 1;
  	 * Iterator i = list.iterator();
  	 * while (i.hasNext()) {
      	 * Object obj = i.next();
      	 * hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
  	 * }
  	 * </code>
	 * </pre>
	 * 
	 * @return l'hash code della lista.
	 */
	int hashCode();

	/**Restituisce l'indice della prima occorrenza dell'elemento specificato, in riferimento alla lista, altrimenti -1 se la lista non contiene l'elemento.
	 * In particolare, restituisce l'indice più piccolo tale che {@code (o == null ? get(i) == null : o.equals(get(i)))}, altrimenti -1 se tale indice non esiste.
	 *
	 * @param o l'elemento da cercare.
	 * @return l'indice corrispondente alla prima occorenza dell'elemento specificato, -1 se la lista non contiene l'elemento.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la lista.
	 * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la lista non supporta elementi nulli.
	 */
	int indexOf(Object o);

	/**Restituisce {@code true} se la lista non contiene elementi.
	 *
	 * @return {@code true} se la lista non contiene elementi.
	 */
	boolean isEmpty();

	/**Restituisce un iteratore degli elementi della lista, che li scorre in maniera ordinata.
	 *
	 * @return un {@code HIterator} degli elementi della lista, che li scorre in maniera ordinata.
	 */
	HIterator iterator();

	/**Restituisce l'indice dell'ultima ocorrenza dell'elemento specificato, in riferimento alla lista, altrimenti -1 se la lista non contiene l'elemento.
	 * In particolare, restituisce l'indice più grande tale che {@code (o == null ? get(i) == null : o.equals(get(i)))}, altrimenti -1 se tale indice non esiste.
	 *
	 * @param o l'elemento da cercare.
	 * @return l'indice corrispondente all'ultima occorenza dell'elemento specificato, -1 se la lista non contiene l'elemento.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la lista.
	 * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la lista non supporta elementi nulli.
	 */
	int lastIndexOf(Object o);

	/**Restituisce un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto).
	 *
	 * @return un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto).

	 */
	HListIterator listIterator();

	/**Restituisce un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto), che parte dalla posizione specificata. 
	 * L'indice specificato indica il primo elemento che verrebbe restituito da una chiamata a {@code next}. 
	 * Una chiamata a {@code previous} restituirebbe l'elemento all'indice meno uno.
	 *
	 * @param index l'indice del primo elemento che l'iteratore dovrà restituire (secondo il metodo {@code next}).
	 * @return un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto), che inizia dalla posizione specificata
	 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index > size()}).

	 */
	HListIterator listIterator(int index);

	/**Rimuove l'elemento inserito alla posizione nella lista. 
	 * Sposta tutti gli elementi a destra del cursore verso sinistra. 
	 * Restituisce l'elemento rimosso dalla lista.
	 *
	 * @param index l'indice dell'elemento da rimuovere
	 * @return l'elemento precedentemente presente alla posizione specificata.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la lista.
	 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index >= size()}).
	 */
	Object remove(int index);

	/**Rimuove la prima istanza dell'elemento specificato dalla lista. Se la lista non contiene l'elemento, non viene modificata.
	 * In particolare, rimuove elemento {@code e} con indice più piccolo tale che {@code (o == null ? e == null : o.equals(e))}.
	 *
	 * @param o l'elemento da rimuovere dalla lista, se presente.
	 * @return {@code true} se la lista conteneva l'elemento, {@code false} altrimenti.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la lista.
	 * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la lista non supporta elementi nulli.
	 */
	boolean remove(Object o);

	/**Rimuove tutti gli elementi della lista che sono inclusi anche nella collezione specificata.
	 *
	 * @param c gli elementi da rimuovere dalla lista.
	 * @return {@code true} se la lista è stata modificata dalla chiamata di questo metodo.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la lista.
	 * @throws java.lang.NullPointerException se la lista contiene elementi nulli e la collezione specificata non supporta elementi nulli, oppure se la collezione specificata è {@code null}.
	 */
	boolean removeAll(HCollection c);

	/**Mantiene solo gli elementi nella collezione specificata.
	 * Ovvero elimina dalla lista tutti gli elementi che non ha in comune con la collezione specificata.
	 *
	 * @param c gli elementi della lista da mantenere.
	 * @return {@code true} se la lista è stata modificata dalla chiamata di questo metodo.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la lista.
	 * @throws java.lang.NullPointerException se la lista contiene elementi nulli e la collezione specificata non supporta elementi nulli, oppure se la collezione specificata è {@code null}.
	 */
	boolean retainAll(HCollection c);

	/**Rimpiazza l'elemento della lista alla posizione specificata con l'elemento specificato.
	 *
	 * @param index l'indice dell'elemento da rimpiazzare.
	 * @param element l'elemento da memorizzare alla posizione specificata.
	 * @return l'elemento precedentemente nella posizione specificata.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato gli impedisce di essere aggiunto alla lista.
	 * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la lista non supporta elementi nulli.
	 * @throws java.lang.IllegalArgumentException se degli aspetti dell'elemento gli impediscono di essere aggiunto alla lista.
	 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index >= size()}).
	 */
	Object set(int index, Object element);

	/**Restituisce il numero di elementi nella lista.
	 * Se la lista contiene più elementi di {@code Integer.MAX_VALUE} elementi, restituisce {@code Integer.MAX_VALUE}.
	 *
	 * @return il numero di elementi della lista.
	 */
	int size();

	/**Restituisce una porzione della lista tra {@code fromIndex} (incluso) e {@code toIndex} (escluso). 
	 * La lista restituita è collegata alla lista principale, quindi tutti i cambi non strutturali devono essere riflessi nulla lista originale e viceversa.
	 *
	 * @param fromIndex indice di partenza della {@code subList}.
	 * @param toIndex indice finale della {@code subList}.
	 * @return una porzione dell'intervallo specificato della lista.
	 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code fromIndex < 0 || toIndex > size() || fromIndex > toIndex}).
	 */
	HList subList(int fromIndex, int toIndex);

	/**Restituisce un array contenente tutti gli elementi della lista, nella sequenza corretta.
	 *
	 * @return un array contenente tutti gli elementi della lista, nella sequenza corretta.
	 */
	Object[] toArray();

	/**Restituisce un array contenente tutti gli elementi della lista; il tipo dell'array restituito è quello dell'array specificato.
	 *
	 * @param a l'array in cui verranno copiati gli elementi della lista, se abbastanza capiente; altrimenti, viene allocato un array dello stesso tipo e utilizzato in maniera analoga.
	 * @return un array contenente gli elementi della lista.
	 * @throws java.lang.ArrayStoreException il tipo dell'array specificato non è un supertipo del tipo di ogni elemento della lista.
	 * @throws java.lang.NullPointerException se il tipo dell'array specificato è {@code null}.
	 */
	Object[] toArray(Object[] a);
}
