package myAdapter;

/**Una collezione rappresenta un gruppo di oggetti, anche detti <i>elementi</i>.
 * <br>
 * In quanto questa interfaccia è stata creata espressamente per non essere in conflitto con quella ufficiale di Java,
 * per l'implementazione della classe {@code ListAdapter} di questo progetto,
 * e siccome è previsto di implementare tutte le optional operations,
 * nella documentazione non viene segnato che alcuna funzione possa lanciare l'eccezione {@code UnsupportedOperationException}.
 */
public interface HCollection{
	/**Fa in modo che la collezione contenga l'elemento specificato. 
	 * Restituisce {@code true} se la collezione viene modificata in seguito alla chiamata di questo metodo, altrimenti {@code false}.
	 * <br>
	 * Se una collezione non permette di aggiungere un elemento, a meno che non sia perché lo contiene già, allora <i>deve</i> lanciare un'eccezione (invece di ritornare {@code false}). 
	 * Questo permette di mantenere l'inavriante per cui una collezione contiene semprre l'elemento specificato alla fine dell'esecuzione del metodo.
	 *
	 * @param o l'elemento che deve essere presente nella collezione.
	 * @return {@code true} se la collezione è stata modificata dopo la chiamata, {@code false} altrimenti.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la collezione.
         * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la lista non supporta elementi nulli.
	 * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la collezione non supporta elementi nulli.
	 * @throws java.lang.IllegalArgumentException se l'elemento non può essere aggiunto alla collezione.
	 */
	boolean add(Object o);

	/**Aggiunge tutti gli elementi della collezione specificata a questa collezione. 
	 *
	 * @param c gli elementi da inserire nella collezione.
	 * @return {@code true} se la collezione è stata modificata in seguito alla chiamata di questo metodo.
	 * @throws java.lang.ClassCastException se la classe della collezione specificata è incompatibile con la collezione.
	 * @throws java.lang.NullPointerException se la collezione è {@code null} o se contiene elementi nulli e la collezione non supporta elementi nulli.
	 * @throws java.lang.IllegalArgumentException se l'elemento non può essere aggiunto alla collezione.
	 */
	boolean addAll(HCollection c);

	/**Rimuove tutti gli elementi della collezione. 
	 * La collezione sarà vuota alla fine dell'esecuzione di questo metodo, a meno che non venga lanciata un'eccezione.
	 */
	void clear();

	/**Restituisce {@code true} se la collezione contiene l'elemento specificato. 
	 * In particolare, restituisce {@code true} se e solo se la collezione contiene almeno un elemento {@code e} tale che {@code (o == null ? e == null : o.equals(e))}.
	 *
	 * @param o l'elemento la cui presenza nella collezione è da verificare.
	 * @return {@code true} se la collezione contiene l'elemento specificato.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la collezione.
	 * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la collezione non supporta elementi nulli.
	 */
	boolean contains(Object o);

	/**Restituisce {@code true} se la collezione contiene tutti gli elementi nella collezione specificata.
	 *
	 * @param c la collezione i cui elementi verranno cercati nella collezione implicita.
	 * @return {@code true} se la collezione implicita contiene tutti gli elementi della collezione specificata.
	 * @throws java.lang.ClassCastException se la classe della collezione specificata è incompatibile con la collezione.
	 * @throws java.lang.NullPointerException se la collezione specificata è {@code null} o se la collezione non supporta elementi nulli.
	 */
	boolean containsAll(HCollection c);

	/**Paragona l'oggetto specificato con la collezione e ne verifica l'uguaglianza.
	 *
	 * @param o {@code Object} da paragonare alla collezione.
	 * @return {@code true} se l'oggetto specificato è uguale alla collezione.
	 */
	boolean equals(Object o);

	/**Restituisce l'hash code della collezione.
	 *
	 * @return l'hash code della collezione.
	 */
	int hashCode();

	/**Restituisce {@code true} se la collezione non contiene elementi.
	 *
	 * @return {@code true} se la collezione non contiene elementi.
	 */
	boolean isEmpty();

	/**Restituisce un iteratore degli elementi della collezione. 
	 * Non viene garantito l'ordine in cui gli elementi sono restituiti gli elementi (a meno che la collezione non sia un'istanza di una classe che garantisca l'ordine).
	 *
	 * @return un {@code HIterator} degli elementi della collezione.
	 */
	HIterator iterator();

	/**Rimuove una singola istanza dell'elemento specificato dalla collezione, se è presente. 
	 * In particolare, rimuove un elemento {@code e} tale che {@code (o == null ? e == null : o.equals(e))}, se la collezione contiene più di un elemento {@code e}.
	 * Restituisce {@code true} se la collezione conteneva l'elemento specificato.
	 *
	 * @param o l'elemento da rimuovere dalla collezione, se presente.
	 * @return {@code true} se la collezione è stata modificata in seguito alla chiamta, {@code false} altrimenti.
	 * @throws java.lang.ClassCastException se la classe dell'elemento specificato è incompatibile con la collezione.
	 * @throws java.lang.NullPointerException se l'elemento specificato è {@code null} e la collezione non supporta elementi nulli.
	 */
	boolean remove(Object o);

	/**Rimuove tutti gli elementi della collezione che sono inclusi anche nella collezione specificata. 
	 * Alla fine della chiamata, la collezione non avrà elementi in comune con quelli della collezione specificata.
	 *
	 * @param c gli elementi da rimuovere dalla collezione.
	 * @return {@code true} se la collezione è stata modificata dalla chiamata di questo metodo.
	 * @throws java.lang.ClassCastException se la classe della collezione specificata è incompatibile con la collezione.
	 * @throws java.lang.NullPointerException se la collezione contiene elementi null e la collezione specificata non li supporta, oppure se la collezione specificata è {@code null}.
	 */
	boolean removeAll(HCollection c);

	/**Mantiene solo gli elementi nella collezione specificata. 
	 * Ovvero elimina dalla collezione tutti gli elementi che non ha in comunq con la collezione specificata.
	 *
	 * @param c gli elementi della collezione da mantenere.
	 * @return {@code true} se la collezione è stata modificata dalla chiamata di questo metodo.
	 * @throws java.lang.ClassCastException se la classe della collezione specificata è incompatibile con la collezione.
	 * @throws java.lang.NullPointerException se la collezione contiene elementi null e la collezione specificata non li supporta, oppure se la collezione specificata è {@code null}.
	 */
	boolean retainAll(HCollection c);

	/**Restituisce il numero di elementi nella collezione.
	 * Se la collezione contiene più elementi di {@code Integer.MAX_VALUE} elementi, restituisce {@code Integer.MAX_VALUE}.
	 *
	 * @return il numero di elementi della collezione.
	 */
	int size();

	/**Restituisce un array contenente gli elementi della collezione. 
	 * Se la collezione garantisce un ordine di iterazione degli elementi, il metodo deve restituire gli elementi nello stesso ordine.
	 * <br>
	 * L'array restituito sarà completamente scollegato dalla collezione di partenza, quindi il chimante è libero di modificare l'array restituito.
	 *
	 * @return un array contenente tutti gli elementi della collezione.
	 */
	Object[] toArray();

	/**Restituisce un array contenente tutti gli elementi della collezione; il tipo dell'array restituito è quello dell'array specificato. 
	 * Se la collezione ha dimensione tale da poter essere contenuta dall'array, allora è restituita su tale array. 
	 * Altrimenti, viene allocato un nuovo array del tipo dell'array specificato e la dimensione della collezione.
	 * <br>
	 * Se la collezione ha dimensione minore dell'array specificato, allora l'elemento successivo alla fine della collezione è impostato a {@code null}.
	 * <br>
	 * Se la collezione garantisce un ordine di iterazione degli elementi, il metodo deve restituire gli elementi nello stesso ordine.
	 *
	 * @param a l'array in cui verranno copiati gli elementi della collezione, se abbastanza capiente; altrimenti, viene allocato un array dello stesso tipo e utilizzato in maniera analoga.
	 * @return un array contenente gli elementi della collezione.
	 * @throws java.lang.ArrayStoreException il tipo dell'array specificato non è un supertipo del tipo di ogni elemento della collezione.
	 * @throws java.lang.NullPointerException se il tipo dell'array specificato è {@code null}.
	 */
	Object[] toArray(Object[] a);
}
