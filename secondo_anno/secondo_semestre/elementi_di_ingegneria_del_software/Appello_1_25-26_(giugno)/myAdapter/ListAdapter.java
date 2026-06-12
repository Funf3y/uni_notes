package myAdapter;

import java.util.Vector;
import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;

/**Adattatore per la classe Vector in ambiente Java Micro Edition, CLDC 1.1.
 * La classe è un adattatore per la classe Vector e implementa le interfacce HList e HCollection 
 * (HList eredita da HCollection, quindi l'implementazione di quest'ultima è implicita).
 * <br>
 * Il comportamento è analogo a quello descritto dalla documentazione J2SE 1.4.2.
 */
public class ListAdapter implements HList{
	/**Adaptee.
	 */
	protected Vector vec;

	//Metodo non eliminato in caso possa servire.
	/*
	public String toString(){
		String ret = "";
		HIterator li = iterator();
		while(li.hasNext()){
			ret += li.next().toString();
		}
		return ret;
	}
	*/

	/**Costruisce una lista vuota di capacità iniziale e incremento di capacità specificati.
	 *
	 * @param initialCapacity capacità iniziale.
	 * @param capacityIncrement incremento della capacità quando la lista va in overflow.
	 * @throws java.lang.IllegalArgumentException se la capacità iniziale specificata è negativa.
	 */
	public ListAdapter(int initialCapacity, int capacityIncrement){
		vec = new Vector(initialCapacity, capacityIncrement);
	}

	/**Costruisce una lista vuota di capacità iniziale specificata.
	 *
	 * @param initialCapacity Capacità iniziale della lista.
	 * @throws java.lang.IllegalArgumentException se la capacità iniziale specificata è negativa.
	 */
	public ListAdapter(int initialCapacity){
		vec = new Vector(initialCapacity);
	}

	/**Costruisce una lista vuota.
	 */
	public ListAdapter(){
		vec = new Vector();
	}

	/**Inserisce l'elemento specificato alla posizione specificata nella lista. 
	 * Sposta a destra di uno tutti gli elementi a destra del cursore.
         *
         * @param index indice dove inserire l'elemento.
         * @param element l'elemento da inserire.
         * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index > size()}).
         */
	@SuppressWarnings("unchecked")
	public void add(int index, Object element){
		if(index < 0 || index > size()){
			throw new java.lang.IndexOutOfBoundsException();
		}

		vec.insertElementAt(element, index);
	}
	
	/**Aggiunge in coda alla lista l'elemento specificato.
         *
         * @param o l'elemento da aggiungere alla lista.
         * @return {@code true} se la lista è stata modificata in seguito alla chiamata del metodo, {@code false} altrimenti.
         */
	@SuppressWarnings("unchecked")
	public boolean add(Object o){
		int prevSize = size();
		vec.addElement(o);
		return size() != prevSize;
	}

	/**Aggiunge tutti gli elementi della collezione specificata a questa lista, nell'ordine in cui sono restituiti dall'iteratore della collezione.
         *
         * @param c gli elementi da inserire nella lista.
         * @return {@code true} se la lista è stata modificata in seguito alla chiamata di questo metodo.
	 * @throws java.lang.NullPointerException se la lista passata è {@code null}
         */
	@SuppressWarnings("unchecked")
	public boolean addAll(HCollection c){
		if(c == null){
			throw new java.lang.NullPointerException();
		}

		int prevSize = size();
		myAdapter.HIterator it = c.iterator();
		while(it.hasNext()){
			vec.addElement(it.next());
		}
		return size() != prevSize;
	}

	/**Inserisce tutti gli elementi della collezione specificata nella lista a partire dalla posizione specificata.
         * Sposta l'elemento nella posizione corrente (se presente) e tutti gli elementi alla sua destra.
         * Gli elementi saranno inseriti nell'ordine in cui sono restituiti dall'iteratore.
         *
         * @param index l'indice in cui inserire il primo elemento della collezione specificata.
         * @param c elementi da inserire nella lista.
         * @return {@code true} se la lista è stata modificata dalla chiamata del metodo.
         * @throws java.lang.NullPointerException se la collezione è {@code null}.
         * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range della lista ({@code index < 0 || index > size()}).
         */
	@SuppressWarnings("unchecked")
	public boolean addAll(int index, HCollection c){
		if(c == null){
			throw new java.lang.NullPointerException();
		}

		if(index < 0 || index > size()){
			throw new java.lang.IndexOutOfBoundsException();
		}

		int prevSize = size();
		int i = index;
		myAdapter.HIterator it = c.iterator();
		while(it.hasNext()){
			vec.insertElementAt(it.next(), i);
			i++;
		}
		return size() != prevSize;
	}

	/**Rimuove tutti gli elementi della lista.
         * La lista sarà vuota alla fine dell'esecuzione di questo metodo, a meno che non venga lanciata un'eccezione.
         */
	public void clear(){
		vec.removeAllElements();
	}

	/**Restituisce {@code true} se la lista contiene l'elemento specificato.
         * In particolare, restituisce {@code true} se e solo se la lista contiene almeno un elemento {@code e} tale che {@code (o == null ? e == null : o.equals(e))}.
         *
         * @param o l'elemento la cui presenza nella lista è da verificare.
         * @return {@code true} se la lista contiene l'elemento specificato.
         */
	@SuppressWarnings("unchecked")
	public boolean contains(Object o){
		return vec.contains(o);
	}
 
	/**Restituisce {@code true} se la lista contiene tutti gli elementi nella collezione specificata.
         *
         * @param c la collezione i cui elementi verranno cercati nella lista.
         * @return {@code true} se la lista contiene tutti gli elementi della collezione specificata.
         * @throws java.lang.NullPointerException se la collezione specificata è {@code null}.
         */
	@SuppressWarnings("unchecked")
	public boolean containsAll(HCollection c){
		if(c == null){
			throw new java.lang.NullPointerException();
		}

		myAdapter.HIterator it = c.iterator();

		while(it.hasNext()){
			if(!vec.contains(it.next())){
				return false;
			}
		}
		
		return true;
	}

	/**Paragona l'oggetto specificato con la lista e ne verifica l'uguaglianza.
         * Restituisce {@code true} se e solo se l'oggetto specificato è una lista, entrambe le liste hanno la stessa dimensione e tutti gli elementi corrispondenti delle due liste sono {@code equals} se ({@code (e1==null ? e2==null : e1.equals(e2))}).
         *
         * @param o {@code Object} da paragonare alla lista.
         */
	public boolean equals(Object o){
	if(!(o instanceof HList)){
			return false;
		}

		HList obj = (HList) o;

		if(obj.size() != size()){
			return false;
		}

		HListIterator thisLi = listIterator();
		HListIterator oLi = obj.listIterator();
		while(thisLi.hasNext()){
			if(thisLi.next() != oLi.next()){
				return false;
			}
		}
		return true;
	}

	/**Restituisce l'elemento alla posizione specificata nella lista.
         *
         * @param index l'indice dell'elemento da restituire.
         * @return l'elemento della lista alla posizione specificata.
         * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range della lista ({@code index < 0 || index >= size()}).
         */
	public Object get(int index){
		if(index < 0 || index >= size()){
			throw new java.lang.IndexOutOfBoundsException();
		}

		return vec.elementAt(index);
	}

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
	public int hashCode(){
		int hashC = 1;
        	HIterator i = this.iterator();
        	while (i.hasNext()) {
        		Object o = i.next();
        		hashC = 31 * hashC + (o==null ? 0 : o.hashCode());
        	}

		return hashC;
	}

	/**Restituisce l'indice della prima occorrenza dell'elemento specificato, in riferimento alla lista, altrimenti -1 se la lista non contiene l'elemento.
         * In particolare, restituisce l'indice più piccolo tale che {@code (o == null ? get(i) == null : o.equals(get(i)))}, altrimenti -1 se tale indice non esiste.
         *
         * @param o l'elemento da cercare.
         * @return l'indice corrispondente alla prima occorenza dell'elemento specificato, -1 se la lista non contiene l'elemento.
         */
	public int indexOf(Object o){
		return vec.indexOf(o);
	}

	/**Restituisce {@code true} se la lista non contiene elementi.
         *
         * @return {@code true} se la lista non contiene elementi.
         */
	public boolean isEmpty(){
		return vec.isEmpty();
	}

	/**Restituisce un iteratore degli elementi della lista, che li scorre in maniera ordinata.
         *
         * @return un {@code HIterator} degli elementi della lista, che li scorre in maniera ordinata.
         */
	public HIterator iterator(){
		return new Iterator();
	}

	/**Restituisce l'indice dell'ultima ocorrenza dell'elemento specificato, in riferimento alla lista, altrimenti -1 se la lista non contiene l'elemento.
         * In particolare, restituisce l'indice più grande tale che {@code (o == null ? get(i) == null : o.equals(get(i)))}, altrimenti -1 se tale indice non esiste.
         *
         * @param o l'elemento da cercare.
         * @return l'indice corrispondente all'ultima occorenza dell'elemento specificato, -1 se la lista non contiene l'elemento.
         */
	public int lastIndexOf(Object o){
		return vec.lastIndexOf(o);
	}

	/**Restituisce un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto).
         *
         * @return un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto).

         */
	public HListIterator listIterator(){
		return new ListIterator();
	}

	/**Restituisce un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto), che parte dalla posizione specificata.
         * L'indice specificato indica il primo elemento che verrebbe restituito da una chiamata a {@code next}.
         * Una chiamata a {@code previous} restituirebbe l'elemento all'indice meno uno.
         *
         * @param index l'indice del primo elemento che l'iteratore dovrà restituire (secondo il metodo {@code next}).
         * @return un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto), che inizia dalla posizione specificata
         * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index > size()}).

         */
	public HListIterator listIterator(int index){
		if(index < 0 || index > size()){
			throw new java.lang.IndexOutOfBoundsException();
		}
		return new ListIterator(index);
	}

	/**Rimuove l'elemento inserito alla posizione nella lista.
         * Sposta tutti gli elementi a destra del cursore verso sinistra.
         * Restituisce l'elemento rimosso dalla lista.
         *
         * @param index l'indice dell'elemento da rimuovere
         * @return l'elemento precedentemente presente alla posizione specificata.
         * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range della lista ({@code index < 0 || index >= size()}).
         */
	public Object remove(int index){
		if(index < 0 || index >= size()){
			throw new java.lang.IndexOutOfBoundsException();
		}
		HListIterator li = listIterator(index);
		Object ret = li.next();
		li.remove();
		return ret;
	}

	/**Rimuove la prima istanza dell'elemento specificato dalla lista. Se la lista non contiene l'elemento, non viene modificata.
         * In particolare, rimuove elemento {@code e} con indice più piccolo tale che {@code (o == null ? e == null : o.equals(e))}.
         *
         * @param o l'elemento da rimuovere dalla lista, se presente.
         * @return {@code true} se la lista conteneva l'elemento, {@code false} altrimenti.
         */
	public boolean remove(Object o){
		return vec.removeElement(o);
	}

	/**Rimuove tutti gli elementi della lista che sono inclusi anche nella collezione specificata.
         *
         * @param c gli elementi da rimuovere dalla lista.
         * @return {@code true} se la lista è stata modificata dalla chiamata di questo metodo.
         * @throws java.lang.NullPointerException se la collezione specificata è {@code null}.
         */
	public boolean removeAll(HCollection c){
		if(c == null){
			throw new java.lang.NullPointerException();
		}

		int prevSize = size();
		HIterator it = c.iterator();
		while(it.hasNext()){
			Object rem = it.next();
			remove(rem);
		}

		return prevSize!=size();
	}

	/**Mantiene solo gli elementi nella collezione specificata.
         * Ovvero elimina dalla lista tutti gli elementi che non ha in comune con la collezione specificata.
         *
         * @param c gli elementi della lista da mantenere.
         * @return {@code true} se la lista è stata modificata dalla chiamata di questo metodo.
         * @throws java.lang.NullPointerException se la collezione specificata è {@code null}.
         */
	public boolean retainAll(HCollection c){
		if(c == null){
			throw new java.lang.NullPointerException();
		}

		int prevSize = size();
		HListIterator li = this.listIterator();

		HCollection removeCollection = new ListAdapter();
		HIterator rmcit = removeCollection.iterator();

		while(li.hasNext()){
			Object rem = li.next();
			if(!c.contains(rem)){
				removeCollection.add(rem);
			}
		}

		this.removeAll(removeCollection);

		return prevSize!=size();
	}

	/**Rimpiazza l'elemento della lista alla posizione specificata con l'elemento specificato.
         *
         * @param index l'indice dell'elemento da rimpiazzare.
         * @param element l'elemento da memorizzare alla posizione specificata.
         * @return l'elemento precedentemente nella posizione specificata.
         * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index >= size()}).
         */
	@SuppressWarnings("unchecked")
	public Object set(int index, Object element){
		if(index < 0 || index >= size()){
			throw new java.lang.IndexOutOfBoundsException();
		}

		Object old = vec.elementAt(index);
		vec.setElementAt(element, index);
		return old;
	}

	/**Restituisce il numero di elementi della lista.
	 * Se la collezione contiene più elementi di {@code Integer.MAX_VALUE} elementi, restituisce {@code Integer.MAX_VALUE}.
	 *
	 * @return il numero di componenti della lista.
	 */
	public int size(){
		return (vec.size() > Integer.MAX_VALUE ? Integer.MAX_VALUE : vec.size());
	}

	/**Restituisce una porzione della lista tra {@code fromIndex} (incluso) e {@code toIndex} (escluso).
         * La lista restituita è collegata alla lista principale, quindi tutti i cambi non strutturali devono essere riflessi nulla lista originale e viceversa. 
	 * Se {@code fromIndex} e {@code toIndex} sono uguali, la lista restituita è vuota.
         *
         * @param fromIndex indice di partenza della {@code subList}.
         * @param toIndex indice finale della {@code subList}.
         * @return una porzione dell'intervallo specificato della lista.
         * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code fromIndex < 0 || toIndex > size() || fromIndex > toIndex}).
         */
	public HList subList(int fromIndex, int toIndex){
		if(fromIndex < 0 || toIndex > size() || fromIndex > toIndex){
			throw new IndexOutOfBoundsException();
		}

		return new SubListAdapter(this, fromIndex, toIndex);
	}

	/**Restituisce un array contenente tutti gli elementi della lista, nella sequenza corretta.
         *
         * @return un array contenente tutti gli elementi della lista, nella sequenza corretta.
         */
	public Object[] toArray(){
		Object[] array = new Object[size()];
		vec.copyInto(array);
		return array;
	}

	/**Restituisce un array contenente tutti gli elementi della lista; il tipo dell'array restituito è quello dell'array specificato.
         *
         * @param a l'array in cui verranno copiati gli elementi della lista, se abbastanza capiente; altrimenti, viene allocato un array dello stesso tipo e utilizzato in maniera analoga.
         * @return un array contenente gli elementi della lista.
         * @throws java.lang.ArrayStoreException il tipo dell'array specificato non è un supertipo del tipo di ogni elemento della lista.
         * @throws java.lang.NullPointerException se il tipo dell'array specificato è {@code null}.
         */
	public Object[] toArray(Object[] a){
		if(a == null){
			throw new java.lang.NullPointerException();
		}

		if(a.length < size()){
			a = new Object[size()];
		}
		
		vec.copyInto(a); //L'eccezione java.lang.ArrayStoreException viene lanciata da questo metodo se necessario
		return a;
	}

	/*---------------SUBLISTADAPTER---------------*/

	//Estendendo ListAdapter, la classe subListAdapter è un ListAdapter e implementa HList (quindi HCollection)
	//Attributi che mi vengono in mente:
	//- genitore (ListAdapter da cui prende una porzione)
	//- indice di inizio da considerare
	//- indice di fine da considerare
	//Bisogna fare un check sugli indici
	//Dovrebbe essere passato un riferimento, quindi l'oggetto è sempre quello. Rimbalzare i cambiamenti dovrebbe essere semplice
	//(modificando l'oggetto, visto che il riferimento è lo stesso, viene modificata sia la lista che la sublist)
	private class SubListAdapter extends ListAdapter{
		/**Lista da cui si analizza una sezione
		 */
		protected ListAdapter parent;

		/**Indice di partenza.
		 */
		protected int beginning;

		/**Indice finale (non incluso).
		 */
		protected int end;

		/**Costruttore.
		 */
		public SubListAdapter(ListAdapter parent, int fromIndex, int toIndex){
			this.parent = parent;
			beginning = fromIndex;
			end = toIndex;
		}

		/**Dimensione della sublist.
		 */
		public int size(){
			return end - beginning;
		}

		/*
		public String toString(){
			return parent.toString();
		}
		*/

		/**Inserisce l'elemento specificato alla posizione specificata nella lista. 
		 * Sposta a destra di uno tutti gli elementi a destra del cursore.
		 *
		 * @param index indice dove inserire l'elemento.
		 * @param element l'elemento da inserire.
		 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index > size()}).
		 */
			@Override
		@SuppressWarnings("unchecked")
		public void add(int index, Object element){
			if(index < 0 || index > size()){
				throw new java.lang.IndexOutOfBoundsException();
			}

			parent.add(beginning + index, element);
		}

		/**Inserisce tutti gli elementi della collezione specificata nella lista a partire dalla posizione specificata.
		 * Sposta l'elemento nella posizione corrente (se presente) e tutti gli elementi alla sua destra.
		 * Gli elementi saranno inseriti nell'ordine in cui sono restituiti dall'iteratore.
		 *
		 * @param index l'indice in cui inserire il primo elemento della collezione specificata.
		 * @param c elementi da inserire nella lista.
		 * @return {@code true} se la lista è stata modificata dalla chiamata del metodo.
		 * @throws java.lang.NullPointerException se la collezione è {@code null}.
		 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range della lista ({@code index < 0 || index > size()}).
		 */
		@SuppressWarnings("unchecked")
		public boolean addAll(int index, HCollection c){
			if(c == null){
				throw new java.lang.NullPointerException();
			}

			if(index < 0 || index > size()){
				throw new java.lang.IndexOutOfBoundsException();
			}

			int prevSize = size();
			int i = 0;
			myAdapter.HIterator it = c.iterator();
			while(it.hasNext()){
				parent.add(beginning + index, it.next());
				i++;
			}
			return size() != prevSize;
		}

		/**Rimuove tutti gli elementi della lista.
		 * La lista sarà vuota alla fine dell'esecuzione di questo metodo, a meno che non venga lanciata un'eccezione.
		 */
		public void clear(){
			for(int i = 0; i < end; i++){
				parent.remove(beginning);
			}
		}

		/**Restituisce {@code true} se la lista contiene l'elemento specificato.
		 * In particolare, restituisce {@code true} se e solo se la lista contiene almeno un elemento {@code e} tale che {@code (o == null ? e == null : o.equals(e))}.
		 *
		 * @param o l'elemento la cui presenza nella lista è da verificare.
		 * @return {@code true} se la lista contiene l'elemento specificato.
		 */
		@SuppressWarnings("unchecked")
		public boolean contains(Object o){
			return (beginning <= parent.indexOf(o) && parent.indexOf(o) < end);
		}
	 
		/**Restituisce {@code true} se la lista contiene tutti gli elementi nella collezione specificata.
		 *
		 * @param c la collezione i cui elementi verranno cercati nella lista.
		 * @return {@code true} se la lista contiene tutti gli elementi della collezione specificata.
		 * @throws java.lang.NullPointerException se la collezione specificata è {@code null}.
		 */
		@SuppressWarnings("unchecked")
		public boolean containsAll(HCollection c){
			if(c == null){
				throw new java.lang.NullPointerException();
			}

			myAdapter.HIterator it = c.iterator();

			while(it.hasNext()){
				int index = parent.indexOf(it.next());
				if((index == -1) || (beginning > index || index >= end)){
					return false;
				}
			}
			
			return true;
		}

		/**Paragona l'oggetto specificato con la lista e ne verifica l'uguaglianza.
		 * Restituisce {@code true} se e solo se l'oggetto specificato è una lista, entrambe le liste hanno la stessa dimensione e tutti gli elementi corrispondenti delle due liste sono {@code equals} se ({@code (e1==null ? e2==null : e1.equals(e2))}).
		 *
		 * @param o {@code Object} da paragonare alla lista.
		 */
		public boolean equals(Object o){
		if(!(o instanceof HList)){
				return false;
			}

			HList obj = (HList) o;

			if(obj.size() != size()){
				return false;
			}

			HListIterator thisLi = listIterator(beginning);
			HListIterator oLi = obj.listIterator();
			while(thisLi.hasNext() && thisLi.nextIndex() < end){
				if(thisLi.next() != oLi.next()){
					return false;
				}
			}
			return true;
		}

		/**Restituisce l'elemento alla posizione specificata nella lista.
		 *
		 * @param index l'indice dell'elemento da restituire.
		 * @return l'elemento della lista alla posizione specificata.
		 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range della lista ({@code index < 0 || index >= size()}).
		 */
		public Object get(int index){
			if(index < 0 || index >= size()){
				throw new java.lang.IndexOutOfBoundsException();
			}
			return parent.get(beginning + index);
		}

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
		public int hashCode(){
			int hashC = 1;
			HListIterator i = parent.listIterator(beginning);
			while (i.hasNext() && i.nextIndex() < end) {
				Object o = i.next();
				hashC = 31 * hashC + (o==null ? 0 : o.hashCode());
			}

			return hashC;
		}

		/**Restituisce l'indice della prima occorrenza dell'elemento specificato, in riferimento alla lista, altrimenti -1 se la lista non contiene l'elemento.
		 * In particolare, restituisce l'indice più piccolo tale che {@code (o == null ? get(i) == null : o.equals(get(i)))}, altrimenti -1 se tale indice non esiste.
		 *
		 * @param o l'elemento da cercare.
		 * @return l'indice corrispondente alla prima occorenza dell'elemento specificato, -1 se la lista non contiene l'elemento.
		 */
		public int indexOf(Object o){
			return (parent.indexOf(o) < beginning || parent.indexOf(o) >= end) ? -1 : parent.indexOf(o);
		}

		/**Restituisce {@code true} se la lista non contiene elementi.
		 *
		 * @return {@code true} se la lista non contiene elementi.
		 */
		public boolean isEmpty(){
			return size() == 0;
		}

		/**Restituisce un iteratore degli elementi della lista, che li scorre in maniera ordinata.
		 *
		 * @return un {@code HIterator} degli elementi della lista, che li scorre in maniera ordinata.
		 */
		public HIterator iterator(){
			HIterator it = parent.iterator();
			int i = 0;
			while(i < beginning){
				it.next();
			}

			return it;
		}

		/**Restituisce l'indice dell'ultima ocorrenza dell'elemento specificato, in riferimento alla lista, altrimenti -1 se la lista non contiene l'elemento.
		 * In particolare, restituisce l'indice più grande tale che {@code (o == null ? get(i) == null : o.equals(get(i)))}, altrimenti -1 se tale indice non esiste.
		 *
		 * @param o l'elemento da cercare.
		 * @return l'indice corrispondente all'ultima occorenza dell'elemento specificato, -1 se la lista non contiene l'elemento.
		 */
		public int lastIndexOf(Object o){
			int index = -1;
			int i = beginning;
			while(i < end){
				if(parent.get(index).equals(o)){
					index = i;
				}
				i++;
			}

			return index;
		}

		/**Restituisce un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto).
		 *
		 * @return un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto).

		 */
		public HListIterator listIterator(){
			return parent.listIterator(beginning);
		}

		/**Restituisce un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto), che parte dalla posizione specificata.
		 * L'indice specificato indica il primo elemento che verrebbe restituito da una chiamata a {@code next}.
		 * Una chiamata a {@code previous} restituirebbe l'elemento all'indice meno uno.
		 *
		 * @param index l'indice del primo elemento che l'iteratore dovrà restituire (secondo il metodo {@code next}).
		 * @return un {@code HListIterator} per gli elementi della lista (che li scorre nell'ordine corretto), che inizia dalla posizione specificata
		 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index > size()}).

		 */
		public HListIterator listIterator(int index){
			if(index < 0 || index > size()){
				throw new java.lang.IndexOutOfBoundsException();
			}
			return parent.listIterator(beginning + index);
		}

		/**Rimuove l'elemento inserito alla posizione nella lista.
		 * Sposta tutti gli elementi a destra del cursore verso sinistra.
		 * Restituisce l'elemento rimosso dalla lista.
		 *
		 * @param index l'indice dell'elemento da rimuovere
		 * @return l'elemento precedentemente presente alla posizione specificata.
		 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range della lista ({@code index < 0 || index >= size()}).
		 */
		public Object remove(int index){
			if(index < 0 || index >= size()){
				throw new java.lang.IndexOutOfBoundsException();
			}

			HListIterator li = parent.listIterator(beginning + index);
			Object ret = li.next();
			li.remove();
			return ret;
		}

		/**Rimuove la prima istanza dell'elemento specificato dalla lista. Se la lista non contiene l'elemento, non viene modificata.
		 * In particolare, rimuove elemento {@code e} con indice più piccolo tale che {@code (o == null ? e == null : o.equals(e))}.
		 *
		 * @param o l'elemento da rimuovere dalla lista, se presente.
		 * @return {@code true} se la lista conteneva l'elemento, {@code false} altrimenti.
		 */
		public boolean remove(Object o){
			int index = indexOf(o);
			if(index < beginning || index >= size()){
				return false;
			}

			return (parent.remove(index) == null ? false : true);
		}

		/**Rimuove tutti gli elementi della lista che sono inclusi anche nella collezione specificata.
		 *
		 * @param c gli elementi da rimuovere dalla lista.
		 * @return {@code true} se la lista è stata modificata dalla chiamata di questo metodo.
		 * @throws java.lang.NullPointerException se la collezione specificata è {@code null}.
		 */
		public boolean removeAll(HCollection c){
			if(c == null){
				throw new java.lang.NullPointerException();
			}

			int prevSize = size();
			HIterator it = c.iterator();
			while(it.hasNext()){
				remove(it.next());
			}

			return prevSize!=size();
		}

		/**Mantiene solo gli elementi nella collezione specificata.
		 * Ovvero elimina dalla lista tutti gli elementi che non ha in comune con la collezione specificata.
		 *
		 * @param c gli elementi della lista da mantenere.
		 * @return {@code true} se la lista è stata modificata dalla chiamata di questo metodo.
		 * @throws java.lang.NullPointerException se la collezione specificata è {@code null}.
		 */
		public boolean retainAll(HCollection c){
			if(c == null){
				throw new java.lang.NullPointerException();
			}

			int prevSize = size();
			HListIterator li = parent.listIterator(beginning);

			HCollection removeCollection = new ListAdapter();
			HIterator rmcit = removeCollection.iterator();

			while(li.hasNext() && li.nextIndex() < end){
				Object rem = li.next();
				if(!c.contains(rem)){
					removeCollection.add(rem);
				}
			}

			this.removeAll(removeCollection);

			return prevSize!=size();
		}

		/**Rimpiazza l'elemento della lista alla posizione specificata con l'elemento specificato.
		 *
		 * @param index l'indice dell'elemento da rimpiazzare.
		 * @param element l'elemento da memorizzare alla posizione specificata.
		 * @return l'elemento precedentemente nella posizione specificata.
		 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index >= size()}).
		 */
		@SuppressWarnings("unchecked")
		public Object set(int index, Object element){
			if(index < 0 || index >= size()){
				throw new java.lang.IndexOutOfBoundsException();
			}

			Object old = parent.get(beginning + index);
			parent.set(beginning + index, element);
			return old;
		}

		/**Restituisce una porzione della lista tra {@code fromIndex} (incluso) e {@code toIndex} (escluso).
		 * La lista restituita è collegata alla lista principale, quindi tutti i cambi non strutturali devono essere riflessi nulla lista originale e viceversa. 
		 * Se {@code fromIndex} e {@code toIndex} sono uguali, la lista restituita è vuota.
		 *
		 * @param fromIndex indice di partenza della {@code subList}.
		 * @param toIndex indice finale della {@code subList}.
		 * @return una porzione dell'intervallo specificato della lista.
		 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code fromIndex < 0 || toIndex > size() || fromIndex > toIndex}).
		 */
		public HList subList(int fromIndex, int toIndex){
			if(fromIndex < 0 || toIndex > size() || fromIndex > toIndex){
				throw new IndexOutOfBoundsException();
			}

			return new SubListAdapter(this, fromIndex, toIndex);
		}

		/**Restituisce un array contenente tutti gli elementi della lista, nella sequenza corretta.
		 *
		 * @return un array contenente tutti gli elementi della lista, nella sequenza corretta.
		 */
		public Object[] toArray(){
			Object[] array = parent.toArray();


			Object[] ausArray = new Object[size()];
			for(int i = 0; i < size(); i++){
				ausArray[i] = array[beginning + i];
			}
			return ausArray;
		}

		/**Restituisce un array contenente tutti gli elementi della lista; il tipo dell'array restituito è quello dell'array specificato.
		 *
		 * @param a l'array in cui verranno copiati gli elementi della lista, se abbastanza capiente; altrimenti, viene allocato un array dello stesso tipo e utilizzato in maniera analoga.
		 * @return un array contenente gli elementi della lista.
		 * @throws java.lang.ArrayStoreException il tipo dell'array specificato non è un supertipo del tipo di ogni elemento della lista.
		 * @throws java.lang.NullPointerException se il tipo dell'array specificato è {@code null}.
		 */
		public Object[] toArray(Object[] a){
			if(a == null){
				throw new java.lang.NullPointerException();
			}

			if(a.length < size()){
				a = new Object[size()];
			}
			
			Object[] ausArray = parent.toArray(); //L'eccezione java.lang.ArrayStoreException viene lanciata da questo metodo se necessario
			for(int i = 0; i < size(); i++){
				a[i] = ausArray[beginning + i];
			}
			return a;
	}
	}

	/*---------------HITERATOR---------------*/

	/**Implementazione dell'iteratore (HIterator) per ListAdapter.
	*/
	private class Iterator implements HIterator{
		/**Posizione del cursore.
		 */
		protected int cursor;

		/**Indice dell'ultimo elemento analizzato con {@code next}.
		 */
		protected int lastSeen;

		/**Costruttore di default.
		 */
		public Iterator(){
			cursor = 0;
			lastSeen = -1;
		}

        	/**Resituisce {@code true} se esiste un altro elemento nell'iterazione.
         	* @return {@code true} se esiste un altro elemento nell'iterazione, altrimenti {@code false}
         	*/
        	public boolean hasNext(){
			return (0 <= cursor && cursor < size());
		}
	
        	/**Resituisce l'elemento successivo nell'iterazione.
         	* @return l'elemento successivo nell'iterazione.
         	* @throws java.util.NoSuchElementException l'iterazione non ha altri elementi.
         	*/
        	public Object next(){
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}

			lastSeen = cursor;
			Object ret = vec.elementAt(cursor);
			cursor++;
			return ret;
		}
	
        	/**Rimuove dalla collezione sottostante l'ultimo elemento resituito dall'iteratore.
         	* Il metodo può essere chiamato solo una volta per ogni chiamata a {@code next}. 
		* <br>
         	* Il comportamento dell'iteratore non è specificato se la collezione sottostante viene modificata da metodi diversi da questo mentre sta avvenendo l'iterazione.
         	* @throws java.lang.IllegalStateException se il metodo {@code next} non è già stato chiamato, o se il metodo {@code remove} è già stato chiamato dall'ultima chiamata a {@code next}.
         	*/
        	public void remove(){
			if(lastSeen == -1){
				throw new java.lang.IllegalStateException();
			}

			vec.removeElementAt(lastSeen);
			lastSeen = -1;
		}
	}

	/*---------------HListIterator---------------*/

	/**Implementazione dell'iteratore per liste (HListIterator) per ListAdapter. 
	 * Implementando HListIterator, che estende HIterator, si sta implementando effettivamente anche quest'ultima interfaccia.
	 */
	private class ListIterator extends Iterator implements HListIterator{

		/**Indice dell'ultimo elemento analizzato con {@code previous} o {@code next}.
		 */
		protected int lastSeen;

		/**Costruttore di default.
		 */
		public ListIterator(){
			lastSeen = -1;
			cursor = 0;
		}

		/**Il costruttore inizializza il cursore all'indice fornito.
         	 * @throws java.lang.IndexOutOfBoundsException se l'indice non è compreso nel range ({@code index < 0 || index > size()}).
		 */
		public ListIterator(int index){
			super();
			if(index < 0 || index > size()){
				throw new java.lang.IndexOutOfBoundsException();
			}
			cursor = index;
		}

		/**Inserisce l'elemento specificato nella lista.
         	* L'elemento è inserito subito prima dell'elemento che sarebbe restituito invocando {@code next} (se presente) e subito dopo l'elemento che sarebbe restituito invocando {@code previous} (se presente).
         	* (Se la lista non contiene elementi, l'elemento da inserire diventa l'unico elemento della lista.)
         	* L'elemento viene inserito prima del cursore, quindi il risultato un'eventuale chiamata a {@code next} non varierebbe, mentre un'eventuale chiamata a {@code previous} (successiva all'inserimento) ritornerebbe il nuovo elemento.
         	* (La chiamata aumenta di uno il valore che sarebbe restituito da {@code nextIndex} e {@code previousIndex}.)
         	*
         	* @param o l'elemento da inserire.
         	*/
		@SuppressWarnings("unchecked")
        	public void add(Object o){
			vec.insertElementAt(o, cursor); 
			cursor++;
			lastSeen = -1;
		}
	
        	/**Restituisce {@code true} se esiste un elemento precedente al cursore nella lista.
         	*
         	* @return {@code true} se esiste un elemento precedente al cursore nella lista, altrimenti {@code false}.
         	*/
        	public boolean hasPrevious(){
			return (0 < cursor && cursor <= size());
		}

        	/**Resituisce {@code true} se esiste un altro elemento nell'iterazione.
         	* @return {@code true} se esiste un altro elemento nell'iterazione, altrimenti {@code false}
         	*/
        	public boolean hasNext(){
			return (0 <= cursor && cursor < size());
		}
	
        	/**Restituisce l'elemento successivo al cursore nella lista.
         	*
         	* @return l'elemento successivo al cursore nella lista.
         	* @throws java.util.NoSuchElementException se non ci sono elementi dopo il cursore.
         	*/
        	public Object next(){
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}

			Object ret = vec.elementAt(cursor);
			lastSeen = cursor;
			cursor++;
			return ret;
		}
	
        	/**Restituisce l'indice dell'elemento che sarebbe restituito chiamando {@code next}.
         	* (Restituisce la dimensione della lista se l'iteratore è alla fine della stessa.)
         	*
         	* @return l'indice dell'elemento che sarebbe restituito chiamando {@code next}, altrimenti la dimensione della lista se l'iteratore è alla fine della stessa.
         	*/
        	public int nextIndex(){
			return cursor;
		}
	
        	/**Restituisce l'elemento precedente al cursore nella lista.
         	* Il metodo può essere chiamato più volte per scorrere la lista al contrario, oppure può essere chiamato alternandolo a {@code next} per andare avanti e indietro.
         	*
         	* @return l'elemento precedente al cursore nella lista.
         	* @throws java.util.NoSuchElementException se non ci sono elementi prima del cursore.
         	*/
        	public Object previous(){
			if(cursor == 0){
				throw new java.util.NoSuchElementException();
			}

			Object ret = vec.elementAt(cursor-1);
			lastSeen = cursor;
			cursor--;
			return ret;
		}
	
        	/**Restituisce l'indice dell'elemento che sarebbe restituito chiamando {@code previous}.
         	* (Restituisce -1 se l'iteratore è all'inizio della stessa.)
         	*
         	* @return l'indice dell'elemento che sarebbe restituito chiamando {@code previous}, altrimenti -1 se l'iteratore è all'inizio della lista.
         	*/
        	public int previousIndex(){
			return cursor - 1;
		}
	
        	/**Rimuove dalla lista l'ultimo elemento che è stato restituito da {@code next} o {@code previous}.
         	* Si può chiamare il metodo solo una volta per chiamata a {@code next} o {@code previous}.
         	* Si può chiamare il metodo solo se {@code HListIterator.add} non è stato chiamato dall'ultima chiamata a {@code next} o {@code previous}.
         	*
         	* @throws java.lang.IllegalStateException se né {@code next} né {@code previous} sono stati chiamati, oppure se {@code remove} o {@code add} sono stati chiamati dall'ultima chiamata a {@code next} o {@code previous}.
         	*/
        	public void remove(){
			if(lastSeen == -1){
				throw new java.lang.IllegalStateException();
			}

			vec.removeElementAt(lastSeen);
			lastSeen = -1;
		}
	
        	/**Rimpiazza l'ultimo elemento restituito da {@code next} o {@code previous} con l'elemento specificato.
         	* La chiamata può essere effettuata solo se né {@code remove} né {@code add} sono stati chiamati dall'ultima chiamata di {@code next} o {@code previous}.
         	*
         	* @param o l'elemento da inserire.
         	* @throws java.lang.IllegalStateException se né {@code next} né {@code previous} sono stati chiamati, oppure se {@code remove} o {@code add} sono stati chiamati dall'ultima chiamata a {@code next} o {@code previous}.
         	*/
		@SuppressWarnings("unchecked")
        	public void set(Object o){
			if(lastSeen == -1){
				throw new java.lang.IllegalStateException();
			}

			vec.setElementAt(o, lastSeen);
		}
	}
}

