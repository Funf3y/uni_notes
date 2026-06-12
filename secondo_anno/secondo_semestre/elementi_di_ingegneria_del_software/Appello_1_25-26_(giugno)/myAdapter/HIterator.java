package myAdapter;

/** Un iteratore su una collezione.
*/
public interface HIterator{
	/**Resituisce {@code true} se esiste un altro elemento nell'iterazione.
	 * @return {@code true} se esiste un altro elemento nell'iterazione, altrimenti {@code false}
	 */
	boolean hasNext();

	/**Resituisce l'elemento successivo nell'iterazione.
	 * @return l'elemento successivo nell'iterazione.
	 * @throws java.util.NoSuchElementException l'iterazione non ha altri elementi.
	 */
	Object next();

	/**Rimuove dalla collezione sottostante l'ultimo elemento resituito dall'iteratore. 
	 * Il metodo può essere chiamato solo una volta per ogni chiamata a {@code next}. <br>
	 * Il comportamento dell'iteratore non è specificato se la collezione sottostante viene modificata da metodi diversi da questo mentre sta avvenendo l'iterazione.
	 * @throws java.lang.IllegalStateException se il metodo {@code next} non è già stato chiamato, o se il metodo {@code remove} è già stato chiamato dall'ultima chiamata a {@code next}.
	 */
	void remove();
}
