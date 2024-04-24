package ule.ed.recursivelist;


public interface EDList<T> {
	/**
	 * TAD 'EDList'
	 * 
	 * Almacena una colección de objetos de tipo T, permitiendo
	 * elementos repetidos.
	 * 
	 * Ejemplo: (A B C A B D )
	 *
	 * 
	 * Excepciones
	 * 
	 * No se permiten elementos null. Si a cualquier método que recibe
	 * un elemento se le pasa el valor null, lanzará una excepción
	 * NullPointerException.
	 * 
	 * Los valores de parámetros position deben ser mayores que cero y
	 * nunca negativos. Si se recibe un valor negativo o cero se lanzará
	 * IllegalArgumentException.
	 * 
	 * 
	 * Constructores
	 * 
	 * Se definirá un constructor por defecto que inicialice la instancia como lista
	 * vacía.
	 * 
	 * 
	 * Método  Object#toString()
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
	 * Indica si esta lista está vacía
	 * 
	 * @return <code>true</code> si no contiene elementos
	 */
	public boolean isEmpty();

	/**
	 * TODO RECURSIVO: Devuelve el número total de elementos en esta lista. <br>
	 * 
	 * Ejemplo:<br>
	 * Si una lista l contiene (A B C B D A B ): <br>
	 * l.size() -> 7
	 * 
	 * @return número total de elementos en esta lista
	 */
	public int size();

	
	
	/**
	 * TODO RECURSIVO: Añade un elemento como último elemento de la lista
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
	 * TODO RECURSIVO: Añade un elemento en la posición pasada como parámetro desplazando los
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
	 * TODO RECURSIVO: Devuelve el elemento que está en position.
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
	 * 
	 */
	public T getElemPos(int position);

	
	/**
	 * TODO RECURSIVO: Devuelve la posición de la primera aparición del elemento.
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
	 * 
	 */
	public int getPosFirst(T elem);

	
	/**
	 * TODO RECURSIVO: Devuelve la posición de la última aparición del elemento.
	 * <p>
	 * Si una lista l contiene (A B C B D A ): <br>
	 * l.getPosLast("A") -> 6 <br>
	 * l.getPosLast("B") -> 4 <br>
	 * l.getPosLast("Z") -> NoSuchElementException
	 * 
	 * @param elem elemento a encontrar.
	 * 
	 * @throws NullPointerException   si elem es <code>null</code>
	 * @throws NoSuchElementException si elem no está en la lista.
	 * 
	 */
	public int getPosLast(T elem);


	/**
	 * TODO RECURSIVO:  Elimina y devuelve el último elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.removeLast() la lista quedará (A
	 * B ) y devolverá C
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * 
	 */
	public T removelast() throws EmptyCollectionException;

	
	/**
	 * TODO RECURSIVO:
	 * Elimina los elementos que ocupan posición impar en la lista
	 * Ejemplo: Si la lista es [1,2,3,4,5,6], devuelve 3 y la lista queda [2,4,6]
     * Si la lista es vacía devuelve 0
     * 	 
     *  @return número de elementos eliminados.
	 */
	public int removeOddElements();
	
	/**
	 * TODO RECURSIVO:
	 * Elimina los elementos que ocupan posición par en la lista
	 * Ejemplo: Si la lista es [1,2,3,4,5,6], devuelve 3 y la lista queda [1,3,5]
     * 
     * Si la lista es vacía devuelve 0
     * 	 
     *  @return número de elementos eliminados.
	 */
	public int removeEvenElements();
	
	
	/**
	 * TODO RECURSIVO:   Elimina duplicados consecutivos sobre la propia lista.
	 * 
	 * Ejemplo: si lista=[A A B A A A B] >> [A B A B]
	 * Si la lista es vacía devuelve 0
	 * 
	 * @return número de elementos eliminados.
	 * 
	 */
	public int removeConsecDuplicates();

	/**
	 * TODO RECURSIVO: Elimina la primera aparición del elemento y devuelve la posición en la que estaba.
	 * <p>
	 * Si una lista l contiene (A B C B ) y hacemos l.removeFirstElem("B") la lista
	 * quedará (A C B ) y devolverá 2
	 * 
	 * @param elem el elemento a eliminar
	 * @return pos del elemento eliminado
	 * 
	 * @throws NoSuchElementException   si la lista no contiene el elemento
	 * 
	 */
	public int removeFirstElem(T elem);

	/**
primera	 * <p>
	 * TODO RECURSIVO: Elimina la última aparición del elemento y devuelve la posición en la que estaba.
     * Si l contiene (A B C B ) y hacemos l.removeLastElem("B") la lista
	 * quedará (A C B ) y devolverá 4
	 * 
	 * @param elem el elemento a eliminar
	 * @return pos del elemento eliminado
	 * 
	 * @throws NoSuchElementException   si la lista no contiene el elemento
	 * 
	 */
	public T removeLastElem(T elem);


	/**
	 * TODO RECURSIVO: Crea una nueva lista inversa de esta lista. <br>
	 * Si esta lista es vacía devuelve la lista vacía. <br>
	 * 
	 * Ejemplo:<br>
	 * Si una lista l contiene (A B C ): <br>
	 * l.reverse().toString() -> (C B A )
	 * 
	 * @return lista inversa de esta lista
	 */
	public EDList<T> reverse();

	
	
	/**
	 * TODO RECURSIVO:
	 * Devuelve el toString de la sublista formada por los elementos NO situados entre las posiciones from hasta until incluidas en el sentido inverso. 
	 * 
	 *  Si from > size() se muestra desde el último de la lista
	 * 
	 *   Ejemplos: 
	 * l1=(A B C D E ) ; l1.toSringExceptFromUntilReverse(3,1)  -> (E D )  
	 * l1=(A B C D E ) ; l1.toSringExceptFromUntilReverse(10,3)  -> (A B  )  
	 * l1=(A B C D E ) ; l1.toSringExceptFromUntilReverse(20,5) -> (D C B A )  
	 * 
     * @param from posición (incluida) desde la que se empieza a considerar los elementos a no incluir en el resultado 
	 * @param until posición (incluida) hasta la que se consideran los elementos a no incluir en el resultado
	 *  
	 * @return String de la sublista formada por los elementos que no están en el rango establecido por los dos parámetros.
	 *
	 * @throws IllegalArgumentException si from o until son <=0 ; o si from < until
	 *
	 */
	public String toSringExceptFromUntilReverse(int from, int until);

	
	/**
	 * TODO RECURSIVO:
	 * Comprueba si la longitud de la lista es igual al entero pasado como parámetro.
	 *  
	 * Ejemplos:<br>
	 * l1=(A B C D E F G )  l1.lengthEqualsTo(3) devolverá false <br>
     * l1=(A B C D E F G )  l1.lengthEqualsTo(7) devolverá true <br>
	 *
	 * @param n longitud a comparar
	 * @return true si la longitud de la lista es igual a n, false en caso contrario
	 */
	public boolean lengthEqualsTo(int n);
			
	// añadido
	/**
	 * TODO RECURSIVO: Añade un elemento delante del elemento indicado como segundo parámetro.
	 * Si el elemento target no está en la lista lo inserta como primer elemento de la lista
	 * Devuelve True si el target estaba en la lista, False en caso contrario
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addBefore("Z", "B") la lista quedará
	 * (A Z B C ) y devolverá true.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addBefore("Z", "K") la lista quedará
	 * (Z A B C ) y devolverá false.
	 * <p>
	 * 
	 * @param elem     el elemento a añadir
	 * @param target   el elemento a buscar, si se encuentra se inserta elem delante de este.
	 * 
	 * @throws NullPointerException     si elem o target son <code>null</code>
	 * @return true si el target estaba en la lista, false en caso contrario
	 * 
	 */
	public boolean addBefore(T elem, T target);
	
	

	
}

