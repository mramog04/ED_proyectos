package ule.ed.list;
import java.util.Iterator;

public interface IEDList<T> {
	/**
	 * TAD 'EDList'
	 * 
	 * Almacena una colección de objetos de tipo <code>T</code>, permitiendo
	 * elementos repetidos.
	 * 
	 * Ejemplo: (A B C A B D )
	 *
	 * 
	 * Excepciones
	 * 
	 * No se permiten elementos <code>null</code>. Si a cualquier método que recibe
	 * un elemento se le pasa el valor <code>null</code>, lanzará una excepción
	 * {@link NullPointerException}.
	 * 
	 * Los valores de parámetros <code>position</code> deben ser mayores que cero y
	 * nunca negativos. Si se recibe un valor negativo o cero se lanzará
	 * {@link IllegalArgumentException}.
	 * 
	 * 
	 * Constructores
	 * 
	 * Se definirá un constructor por defecto que inicialice la instancia como lista
	 * vacía.
	 * 
	 * 
	 * Método {@link Object#toString()}
	 * 
	 * El formato será mostrar el toString de los elementos separados por espacios
	 * (A B C D D D B ) el toString
	 *
	 * 
	 * @author profesor
	 *
	 * @param <T> tipo de elementos en la lista
	 */

	/**
	 * Devuelve el número total de elementos en esta lista. <br>
	 * 
	 * Ejemplo:<br>
	 * Si una lista l contiene (A B C B D A B ): <br>
	 * l.size() -> 7
	 * 
	 * @return número total de elementos en esta lista
	 */
	public int size();

	/**
	 * Indica si esta lista está vacía
	 * 
	 * @return <code>true</code> si no contiene elementos
	 */
	public boolean isEmpty();

	/**
	 * Elimina todo el contenido de esta lista.
	 * 
	 * Deja la lista vacía
	 * 
	 */
	public void clear();

	/**
	 * Añade un elemento como primer elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addFirst("C") la lista quedará
	 * (C A B C )
	 * 
	 * @param elem el elemento a añadir
	 * 
	 * @throws NullPointerException si elem es <code>null</code>
	 */
	public void addFirst(T elem);

	/**
	 * Añade un elemento como último elemento de la lista
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addLast("C") la lista quedará (A
	 * B C C )
	 * 
	 * @param elem el elemento a añadir
	 * 
	 * @throws NullPointerException si elem es <code>null</code>
	 */
	public void addLast(T elem);

	/**
	 * Añade un elemento como penúltimo elemento de la lista
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addPenult("D") la lista quedará
	 * (A B D C )
	 * 
	 * Si la lista está vacía lo inserta normal
	 * 
	 * @param elem el elemento a añadir
	 * 
	 * @throws NullPointerException si elem es <code>null</code>
	 */
	public void addPenult(T elem);

	/**
	 * Añade un elemento en la posición pasada como parámetro desplazando los
	 * elementos que estén a partir de esa posición.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addPos("Z", 2) la lista quedará
	 * (A Z B C ).
	 * <p>
	 * Si position>size() se insertará como último elemento.
	 * 
	 * @param elem     el elemento a añadir
	 * @param position la posición en la que añadirá el elemento
	 * 
	 * @throws NullPointerException     si elem es <code>null</code>
	 * @throws IllegalArgumentException si position <= 0
	 * 
	 */
	public void addPos(T elem, int position);

	/**
	 * Elimina y devuelve el primer elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.removeFirst() la lista quedará
	 * (B C ) y devolverá A
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * @return el elemento que era el primero de esta lista y se ha eliminado
	 *
	 */
	public T removeFirst() throws EmptyCollectionException;

	/**
	 * Elimina y devuelve el último elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.removeLast() la lista quedará (A
	 * B ) y devolverá C
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * @return el elemento que era el ultimo de esta lista y se ha eliminado
	 * 
	 */
	public T removelast() throws EmptyCollectionException;;

	/**
	 * Elimina y devuelve el penúltimo elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.removePenult() la lista quedará
	 * (A C ) y devolverá B
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * @throws NoSuchElementException   si la lista solo tiene un elemento
	 * @return el elemento que era el penultimo de esta lista y se ha eliminado
	 *
	 */
	public T removePenult() throws EmptyCollectionException;;

	/**
	 * Elimina la primera aparición del elemento y devuelve la posición en la que estaba.
	 * <p>
	 * Si una lista l contiene (A B C B ) y hacemos l.removeElem("B") la lista
	 * quedará (A C B ) y devolverá 2
	 * 
	 * @param elem el elemento a eliminar
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * @throws NoSuchElementException   si la lista no contiene el elemento
	 * @return la posición en la que estaba la primera aparición del elemento
	 * 
	 */
	public int removeElem(T elem) throws EmptyCollectionException;;

	/**
	 * Devuelve el elemento que está en position.
	 * <p>
	 * Si una lista l contiene (A B C D E ): <br>
	 * l.getElemPos(1) -> A <br>
	 * l.getElemPos(3) -> C <br>
	 * l.getElemPos(10) -> IllegalArgumentException
	 * 
	 * 
	 * @param position posición a comprobar para devolver el elemento
	 * 
	 * @throws IllegalArgumentException si position no está entre 1 y size()
	 * @return el elemento que esta en position
	 *
	 */
	public T getElemPos(int position);

	/**
	 * Devuelve la posición de la primera aparición del elemento.
	 * <p>
	 * Si una lista l contiene (A B C B D A ): <br>
	 * l.getPosFirst("A") -> 1 <br>
	 * l.getPosFirst("B") -> 2 <br>
	 * l.getPosFirst("Z") -> NoSuchElementException
	 * 
	 * @param elem elemento a encontrar.
	 *
	 * @throws NullPointerException   si elem es <code>null</code>
	 * @throws NoSuchElementException si elem no está en la lista.
	 * @return la posición en la que estaba la primera aparición del elemento
     * 
	 */
	public int getPosFirst(T elem);

	/**
	 * Devuelve la posición de la última aparición del elemento.
	 * <p>
	 * Si una lista l contiene (A B C B D A ): <br>
	 * l.getPosFirst("A") -> 6 <br>
	 * l.getPosFirst("B") -> 4 <br>
	 * l.getPosFirst("Z") -> NoSuchElementException
	 * 
	 * @param elem elemento a encontrar.
	 * 
	 * @throws NullPointerException   si elem es <code>null</code>
	 * @throws NoSuchElementException si elem no está en la lista.
	 * @return la posición en la que estaba la ultima aparición del elemento
     * 
	 */
	public int getPosLast(T elem);

	/**
	 * Elimina todas las apariciones del elemento y devuelve el número de instancias
	 * eliminadas.
	 * <p>
	 * Si una lista l contiene (A B C B D A B ): <br>
	 * l.removeAll("A") -> 2, dejando la lista (B C B D B ): <br>
	 * l.removeAll("B") -> 3, dejando la lista (A C D A ): <br>
	 * l.removeAll("Z") -> NoSuchElementException
	 * 
	 * @param elem elemento a eliminar.
	 * 
	 * @throws NullPointerException   si elem es <code>null</code>
	 * @throws NoSuchElementException si elem no está en la lista.
	 * @throws EmptyCollectionException  si la lista está vacia
	 * @return el numero de instancias eliminadas 
     * 
	 */
	public int removeAll(T elem) throws EmptyCollectionException;

	/**
	 * Crea una nueva lista con los elementos que tienen repetidos en esta lista. <br>
	 * Si esta lista es vacía devuelve la lista vacía. <br>
	 * 
	 * Ejemplo:<br>
	 * Si una lista l contiene (A X B C B D B C X): <br>
	 * l.listOfRepeatedElems().toString() -> (X B C )
	 * 
	 * @return lista de los elementos que tienen repetidos de esta lista
	 */
	public IEDList<T> listOfRepeatedElems();

	/**
	 * Devuelve el numero de veces que aparece el elemento en la lista
	 * 
	 * <br>
	 * Ejemplos:<br>
	 * l1=(A B C D E ) ; l1.countElem(B) -> 1 <br>
	 * l1=(A B C B E B ) ; l1.countElem(B) -> 3 <br>
	 * l1=(A E C D E ) ;l1.countElem(B) -> 0 <br>
	 * 
	 * @param elem  elemento a buscar 
	 * @throws NullPointerException   si elem es <code>null</code>
	 * @return el numero de veces que aparece el elemento en la lista
     *
	 */
	public int countElem(T elem);

	/**
	 * Devuelve un iterador que recorre la lista desde el primero hasta el último
	 * elemento. <br>
	 *
	 * Por ejemplo, para una lista x con elementos (A B C D E)
	 *
	 * el iterador creado con x.iterator() devuelve en sucesivas llamadas a next():
	 * A, B, C, D y E.
	 *
	 * @return iterador para recorrer todos los elementos de la lista.
	 */
	public Iterator<T> iterator();

	/**
	 * Devuelve un iterador que recorre los elementos con posición par de la lista.
	 * <br>
	 *
	 * Por ejemplo, para una lista x con elementos (A B C D E )
	 *
	 * el iterador creado con x.evenPositionsIterator() devuelve en sucesivas
	 * llamadas a next(): B y D.
	 *
	 * @return iterador para recorrer elementos en posiciones pares.
	 */
	public Iterator<T> evenPositionsIterator();

	/**
	 * Devuelve un iterador que recorre los elementos con posición impar de la
	 * lista. <br>
	 *
	 * Por ejemplo, para una lista x con elementos (A B C D E )
	 *
	 * el iterador creado con x.oddPositionsIterator() devuelve en sucesivas llamadas
	 * a next(): A, C y E.
	 *
	 * @return iterador para recorrer elementos en posiciones impares.
	 */
	public Iterator<T> oddPositionsIterator();

	/**
	 * Devuelve un iterador que recorre los elementos que ocupan posición par y
	 * posteriormente los que ocupan posición impar en la lista.
	 *
	 * Por ejemplo, para una lista x con elementos (1 2 3 4 5 6 7 8 9 )
	 * 
     * el iterador creado con x.OddEvenIterator() devuelve en sucesivas llamadas a next(): 1, 3, 5, 7, 9, 
     *  2, 4, 6 y 8 .
	 *
	 * @return iterador para recorrer los elementos de la lista 
     *        (primero que ocupan posición impar y luego los que ocupan posición par).
	
	 */
	public Iterator<T> OddEvenIterator();
}
