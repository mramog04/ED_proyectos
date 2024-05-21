package ule.edi.tree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;


/**
 * arbol binario de busqueda (binary search tree, BST).
 * 
 * El codigo fuente esta en UTF-8, y la constante EMPTY_TREE_MARK definida en
 * AbstractTreeADT del proyecto API deberia ser el simbolo de conjunto vacio:
 * ∅
 * 
 * Si aparecen caracteres "raros", es porque el proyecto no esta bien
 * configurado en Eclipse para usar esa codificacion de caracteres.
 *
 * En el toString() que esta ya implementado en AbstractTreeADT se usa el
 * formato:
 * 
 * Un arbol vaci­o se representa como "∅". Un Ã¡rbol no vacio como
 * "{(informacion rai­z), sub-arbol 1, sub-arbol 2, ...}".
 * 
 * Por ejemplo, {A, {B, ∅, ∅}, ∅} es un arbol binario con rai­z "A" y un
 * unico sub-arbol, a su izquierda, con rai­z "B".
 * 
 * El metodo render() tambien representa un arbol, pero con otro formato; por
 * ejemplo, un arbol {M, {E, ∅, ∅}, {S, ∅, ∅}} se muestra como:
 * 
 * M 
 * | E
 * | | ∅
 * | | ∅ 
 * | S 
 * | | ∅
 * | | ∅
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para adjuntar
 * informacion extra. Si es el caso, tanto toString() como render() mostraran
 * los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor) y con
 * {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en los
 * elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> seria muy restrictivo; en su lugar se
 * permiten tipos que sean comparables no solo con exactamente T sino tambien
 * con tipos por encima de T en la herencia.
 * 
 * @param <T> tipo de la informacion en cada nodo, comparable.
 */
public class BinarySearchTreeImpl<T extends Comparable<? super T>> extends AbstractBinaryTreeADT<T> {

	BinarySearchTreeImpl<T> father; // referencia a su nodo padre)
	int count;  // contador de instancias 

	
	/**
	 * Devuelve el arbol binario de busqueda izquierdo.
	 */
	protected BinarySearchTreeImpl<T> getLeftBST() {
		// El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		// aqui­ se sabe que es ademas BST (binario de busqueda)
		//
		return (BinarySearchTreeImpl<T>) left;
	}

	protected void setLeftBST(BinarySearchTreeImpl<T> left) {
		this.left = left;
	}

	/**
	 * Devuelve el arbol binario de busqueda derecho.
     */
	protected BinarySearchTreeImpl<T> getRightBST() {
		return (BinarySearchTreeImpl<T>) right;
	}

	protected void setRightBST(BinarySearchTreeImpl<T> right) {
		this.right = right;
	}

	/**
	 * arbol BST vaci­o
	 */
	public BinarySearchTreeImpl() {
		// TODO HACER QUE THIS SEA EL NODO VACIO
		this.left=null;
		this.right=null;
		this.father=null;
		this.count=0;
	}

	public BinarySearchTreeImpl(BinarySearchTreeImpl<T> father) {
		// TODO HACER QUE THIS SEA EL NODO VACIO, asignando como padre el parametro
		// recibido
		//aqui he añadido lo del elemento ns si esta bien
		this();
		this.father= father;
		this.count=0;
	}

	private BinarySearchTreeImpl<T> emptyBST(BinarySearchTreeImpl<T> father) {
		//Devuelve un nodo vacío
		return new BinarySearchTreeImpl<T>(father);//y aqui añadi un null
	}

	private void elemNull(T elem){
		if(elem==null){
			throw new IllegalArgumentException();
		}
	}
	
	
	/**
	 * Inserta los elementos que no sean null, de una coleccion en el arbol. 
	 * (si alguno es 'null', no lo inserta)
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements valores a insertar.
	 * @return numero de elementos insertados en el arbol (elementos diferentes de null)
	 */
	public int insert(Collection<T> elements) {
		// si alguno es 'null', no se inserta
		// TODO Implementar el metodo
		int result=0;
		for(T element : elements){
			if(element!=null){
				if(insert(element)){//a lo mejor se puede usar insertRec
					result++;
				}
			}
		}
		return result;
	}

	/**
	 * Inserta los elementos que no sean null, de un array en el arbol. 
	 * (si alguno es 'null', no lo inserta)
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 * @return  	numero de elementos insertados en el arbol (elementos diferentes de null)
	 */
	public int insert(T... elements) {
		// si alguno es 'null', no inserta ese elemento
		// TODO Implementar el metodo
		//a lo mejor tambien hay que sumar cuando no lo inserta pero si añade en el count
		int result=0;
		for(T element : elements){
			if(element!=null){
				if(insert(element)){//a lo mejor se puede usar insertRec
					result++;
				}
			}
		}
		return result;
	}

	/**
	 * Inserta (como hoja) un nuevo elemento en el arbol de busqueda.
	 * 
	 * Debe asignarse valor a su atributo father (referencia a su nodo padre o null
	 * si es la rai­z)
	 * 
	 * No se permiten elementos null. Si element es null dispara excepcion:IllegalArgumentException 
	 * Si el elemento ya existe en el arbol
	 *  no inserta un nodo nuevo, sino que incrementa el atributo count del nodo que tiene igual contenido.
	 * 
	 * @param element valor a insertar.
	 * @return true si se insertó en un nuevo nodo (no existia ese elemento en el arbol),
	 *         false en caso contrario
	 * @throws IllegalArgumentException si element es null
	 */
	public boolean insert(T element) {
        // TODO Implementar el metodo
        elemNull(element);
        if(isEmpty()){
            this.setContent(element);
			this.setLeftBST(emptyBST(father));
			this.setRightBST(emptyBST(father));
            this.count++;
			return true;
        }
        return insertRec(element,this);
    }

    private boolean insertRec(T elem,BinarySearchTreeImpl<T> current){
        int cmp = elem.compareTo(current.content);

        if(cmp < 0){
            if(current.left.content==null){
				current=current.getLeftBST();
				current.setContent(elem);
				current.count++;
				current.setLeftBST(emptyBST(current));
				current.setRightBST(emptyBST(current));
                return true;
            }else{
                return insertRec(elem,current.getLeftBST());
            }
        }else if(cmp > 0){
            if(current.right.content==null){
				current=current.getRightBST();
				current.setContent(elem);
                current.count++;
				current.setLeftBST(emptyBST(current));
				current.setRightBST(emptyBST(current));
                return true;
            }else{
                return insertRec(elem,current.getRightBST());
            }
        }else{
            current.count++;
            return false;
        }
    }

	/**
	 * Busca el elemento en el arbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param element valor a buscar.
	 * @return true si el elemento esta en el arbol, false en caso contrario
	 * @throws IllegalArgumentException si element es null
	 *
	 */
	public boolean contains(T element) {
		// TODO Implementar el metodo
		elemNull(element);
		return containsRec(element, father);
	}

	private boolean containsRec(T elem,BinarySearchTreeImpl<T> current){
		int cmp = elem.compareTo(current.content);
        if(cmp < 0){
            if(current.left.content.equals(elem)){
                return true;
            }else if(current.left.content.equals(null)){
                return false;
            }else{
                return containsRec(elem,(BinarySearchTreeImpl<T>)current.left);
            }
        }else if(cmp > 0){
            if(current.right.content.equals(elem)){
                return true;
            }else if(current.right.content.equals(null)){
                return false;
            }else{
                return containsRec(elem,(BinarySearchTreeImpl<T>)current.right);
            }
        }else{
            return true;
        }  
	}
	
	/**
	 *  devuelve la cadena formada por el contenido del árbol teniendo en cuenta que 
	 *  si un nodo tiene su atributo count>1 pone entre paréntesis su valor justo detrás del atributo elem
	 *  También debe mostrar las etiquetas que tenga el nodo (si las tiene)
	 *  
	 *  CONSEJO: REVISAR LA IMPLEMENTACIÓN DE TOSTRING DE LA CLASE AbstractTreeADT 
	 * 
	 * Por ejemplo: {M, {E(2), ∅, ∅}, {K(5), ∅, ∅}}
	 * 
	 * @return cadena con el contenido del árbol incluyendo su atributo count entre paréntesis si elemento tiene más de 1 instancia
	 */
	public String toString() {//NO ESTA ACABADO
		// TODO implementar este metodo
		// CONSEJO: Hay que reciclar la implementación del método de la clase AbstractTreeADT, 
		// añadiendo el código necesario para poner detrás del content entre paréntesis el count
		// solo si éste es mayor que 1.
		// Código que añade el content: result.append("{" + content.toString());
		
		if (!isEmpty()) {
			//	Construye el resultado de forma eficiente
			StringBuffer result = new StringBuffer();
				
			//	Raíz
			result.append("{" + content.toString());

			if(this.count>1){
				result.append("("+count+")");
			}
			
			if (!tags.isEmpty()) {
				result.append(" [");
				
				List<String> sk = new LinkedList<String>(tags.keySet());
				
				Collections.sort(sk);
				for (String k : sk) {
					result.append("(" + k + ", " + tags.get(k) + "), ");
				}
				result.delete(result.length() - 2, result.length());
				result.append("]");
			}
			
			//	Y cada sub-árbol
			for (int i = 0; i < getMaxDegree(); i++) {
				result.append(", " + getSubtree(i).toString());
			}
			//	Cierra la "}" de este árbol
			result.append("}");
			
			return result.toString();
		} else {
			return AbstractTreeADT.EMPTY_TREE_MARK;
		}
		
	}



	
	
	/**
	 * Devuelve un iterador que recorre los elementos (sin tener en cuenta el número de instancias)del arbol por niveles segun
	 * el recorrido en anchura
	 * 
	 * Por ejemplo, con el arbol
	 * 
	 * {50, {30(2), {10, ∅, ∅}, {40, ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
	 * 
	 * y devolvera el iterador que recorrera los nodos en el orden: 50, 30, 80, 10, 40, 60
	 * 
	 * 
	 * 
	 * @return iterador para el recorrido en anchura
	 */
    public Iterator<T> iteratorWidth() {
	 	// TODO Implementar metodo
		// puede implementarse creando una lista con el recorrido en anchura de los
		// elementos del arbol y devolver el iterador de dicha lista
		List<T> resultList = new ArrayList<>();
		if (!isEmpty()) {
			Queue<BinarySearchTreeImpl<T>> queue = new LinkedList<>();
			queue.add(this);

			while (!queue.isEmpty()) {
				BinarySearchTreeImpl<T> current = queue.poll();

				if (current.content != null) {
					resultList.add(current.content);
				}

				if (current.getLeftBST() != null) {
					queue.add(current.getLeftBST());
				}
				if (current.getRightBST() != null) {
					queue.add(current.getRightBST());
				}
			}
		}
		return resultList.iterator();
	}

	/**
	 * Devuelve un iterador que recorre los elementos (teniendo en cuenta el número de instancias)del arbol por niveles segun
	 * el recorrido en anchura
	 * 
	 * Por ejemplo, con el arbol
	 * 
	 * {50, {30(2), {10, ∅, ∅}, {40, ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
	 * 
	 * y devolvera el iterador que recorrera los nodos en el orden: 50, 30, 30, 80, 80, 10, 40, 60
	 *  
	 * @return iterador para el recorrido en anchura
	 */
     public Iterator<T> iteratorWidthInstances() {// No se si funciona bien
		// TODO Implementar metodo
		// puede implementarse creando una lista con el recorrido en anchura de los
		// elementos del arbol (teniendo el número de instancias que tiene el elemento)
		//y devolver el iterador de dicha lista
		List<T> resultList = new ArrayList<>();
		if (!isEmpty()) {
			Queue<BinarySearchTreeImpl<T>> queue = new LinkedList<>();
			queue.add(this);

			while (!queue.isEmpty()) {
				BinarySearchTreeImpl<T> current = queue.poll();

				// Add current.content count times
				for (int i = 0; i < current.count; i++) {
					resultList.add(current.content);
				}

				if (current.getLeftBST() != null) {
					queue.add(current.getLeftBST());
				}
				if (current.getRightBST() != null) {
					queue.add(current.getRightBST());
				}
			}
		}
		return resultList.iterator();
	 }
	
		
	/**
	 * Cuenta el número de elementos diferentes del arbol (no tiene en cuenta las instancias)
	 * 
	 * Por ejemplo, con el arbol
	 * 
	 * {50, {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
	 * 
	 * la llamada a ejemplo.instancesCount() devolvera 6
	 * 
	 * @return el numero de elementos diferentes del arbol 
	 */
    public int size() {
		// TODO implementar este metodo		
		return sizeRec(this);
	}

	private int sizeRec(BinarySearchTreeImpl<T> current){
		if(current.isEmpty()){
			return 0;
		}
		int count = 1;
		count+=sizeRec(current.getLeftBST());
		count+=sizeRec(current.getRightBST());

		return count;
	}
	
    /**
	 * Cuenta el número de instancias de elementos diferentes del arbol 
	 * 
	 * Por ejemplo, con el arbol ejemplo=
	 * 
	 * {50, {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
	 * 
	 * la llamada a ejemplo.instancesCount() devolvera 11
	 * 
	 * @return el número de instancias de elementos del arbol 
	 */
	public int instancesCount() {
		// TODO implementar este metodo
		return instancesCountRec(this);
	}
	
	private int instancesCountRec(BinarySearchTreeImpl<T> current){
		if(current.isEmpty()){
			return 0;
		}

		int count = current.count;
		count+=instancesCountRec(current.getLeftBST());
		count+=instancesCountRec(current.getRightBST());

		return count;
	}

	
	/**
	 * Elimina los valores en un array del Arbol.
	 * Devuelve el número de elementos que pudo eliminar del árbol
	 *  (no podrá eliminar los elemenots 'null' o que no los contiene el arbol)
	 * 
	 * return numero de elementos eliminados del arbol
	 */
	public int  remove(T... elements) {
		// TODO Implementar el metodo
		int result = 0;
		for(T element : elements){
			if(element!=null){
				remove(element);
				result++;
			}
		}
		return result;
	}

	/**
	 * Elimina un elemento del arbol. Si el atributo count del nodo que contiene el elemento es >1, simplemente se decrementará este valor en una unidad
	 * 
	 * Si hay que eliminar el nodo, y tiene dos hijos, se tomara el criterio de sustituir el
	 * elemento por el menor de sus mayores y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no esta en el arbol
	 * @throws IllegalArgumentException si element es null
     *
	 */
	public void remove(T element) {
		// TODO Implementar el metodo
		elemNull(element);
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}
		removeRec(element, this);
	}

	private void removeRec(T elem,BinarySearchTreeImpl<T> current){
		int cmp = elem.compareTo(current.content);
        if(cmp < 0){
    		if(current.getLeftBST().isEmpty()){
                throw new NoSuchElementException();
            }else{
                removeRec(elem, current.getLeftBST());
            }
        }else if(cmp > 0){
            if(current.getRightBST().isEmpty()){
                throw new NoSuchElementException();
            }else{
                removeRec(elem, current.getRightBST());
            }
        }else{
            if(current.count > 1){
				current.count--;
				return;
			}else{
				//Caso 1: El nodo no tiene hijos
				if (isLeaf()) {
					// Verifica si el nodo es el hijo izquierdo o derecho de su padre y lo elimina
					current.setContent(null);
					current.count=0;
					current.setRightBST(null);
					current.setLeftBST(null);
					return;
				}
			
				// Caso 2.1: El nodo tiene un solo hijo y es izquierdo
				if (!current.getLeftBST().isEmpty() && current.getRightBST().isEmpty()) {
					// Encuentra el hijo del nodo
					BinarySearchTreeImpl<T> child = current.getLeftBST();
			
					// Reemplaza el nodo actual por su hijo
					current.setContent(child.content);
					current.count=child.count;
					current.setRightBST(child.getRightBST());
					current.setLeftBST(child.getLeftBST());
					child.father=current.father; // Actualiza el padre del hijo
					return;	

				}

				// Caso 2.2: El nodo tiene un solo hijo y es derecho
				if (current.getLeftBST().isEmpty() && !current.getRightBST().isEmpty()) {
					// Encuentra el hijo del nodo
					BinarySearchTreeImpl<T> child = current.getRightBST();

					current.setContent(child.content);
					current.count=child.count;
					current.setRightBST(child.getRightBST());
					current.setLeftBST(child.getLeftBST());
					child.father=current.father; // Actualiza el padre del hijo
					return;	

				}

				//Caso 3: El nodo tiene 2 hijos(repasar antes del examen)
				if (!current.getLeftBST().isEmpty() && !current.getRightBST().isEmpty()) {
					// Encuentra el sucesor inmediato del nodo actual
					BinarySearchTreeImpl<T> sucessor = current.getMin(current.getRightBST());
					
					// Sustituye el valor del nodo actual por el valor del sucesor inmediato
					current.setContent(sucessor.getContent());
					current.count = sucessor.count;
				
					current.getRightBST().remove(sucessor.content,sucessor.count);
					return;
				}
      		}  
		}
	}

	private BinarySearchTreeImpl<T> getMin(BinarySearchTreeImpl<T> current){
		if(current.getLeftBST()!=null){
			current.getMin(current.getLeftBST());
		}
		return current;
	} 
	
	/**
	 * Decrementa el número de instancias del elemento en num unidades.
	 * Si count queda en cero o negativo, se elimina el elemento del arbol. 
	 * Devuelve el número de instancias que pudo eliminar
	 * 
	 * 
	 * Si hay que eliminar el nodo, y tiene dos hijos, se tomara el criterio de sustituir el
	 * elemento por el menor de sus mayores y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no esta en el arbol	
	 * @throws IllegalArgumentException si element es null
	 * @return numero de instancias eliminadas
	 * 
	 */
	public int remove(T element, int num) {// no se si coutInstancesElem() funnciona bien
		// TODO Implementar el metodo
		elemNull(element);
		int numElements = countInstancesElemRec(element, this);
		int result = numElements-num;
		if(result>=0){
			for(int i = 0;i < num;i++){
				remove(element);
			}
			return num;
		}
		else{
			for(int i = 0;i<numElements;i++){
				remove(element);
			}
			return numElements;
		}

	}

	private int countInstancesElemRec(T elem,BinarySearchTreeImpl<T> current){
		int cmp = elem.compareTo(current.content);
        if(cmp < 0){
            if(current.left==null){
                return 0;
            }else{
                return countInstancesElemRec(elem,current.getLeftBST());
            }
        }else if(cmp > 0){
			if(current.right==null){
                return 0;
            }else{
                return countInstancesElemRec(elem,current.getRightBST());
            }
        }else{
            return current.count;
        }
	}
	/**
	 * Elimina todas las instancias del elemento en el árbol 
	 * eliminando del arbol el nodo que contiene el elemento .
	 * 
	 * 
	 * Se tomara el criterio de sustituir el elemento por el menor de sus mayores 
	 * y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no esta en el arbol	
	 * @throws IllegalArgumentException si element es null
	 */
	public int removeAll(T element) {
		// TODO Implementar el metodo
		elemNull(element);
		int numElements = countInstancesElemRec(element, this);
		for(int i = 0;i<numElements;i++){
			remove(element);
		}
		return numElements;
	}

	/**
	* Devuelve el sub-árbol indicado. (para tests)
	* path será el camino para obtener el sub-arbol. Está formado por 0 y 1.
	* Si se codifica "bajar por la izquierda" como "0" y
	* "bajar por la derecha" como "1", el camino desde un 
	* nodo N hasta un nodo M (en uno de sus sub-árboles) será          * la cadena de 0s y 1s que indica cómo llegar desde N hasta M.
	*
	* Se define también el camino vacío desde un nodo N hasta
	* él mismo, como cadena vacía.
	* 
	* Si el subarbol no existe lanzará la excepción NoSuchElementException.
	* 
	* @param path
	* @return el nodo no vacío que se alcanza con ese camino
	* @throws NoSuchElementException si el camino no alcanza un nodo no vacío en el árbol
	* @throws IllegalArgumentException si el camino no contiene sólamente 0s y 1s
*/
	public BinarySearchTreeImpl<T> getSubtreeWithPath(String path) {//repasar si fuciona bien
	//TODO
	if (path == null) {
        throw new IllegalArgumentException("Path cannot be null");
    }

    BinarySearchTreeImpl<T> current = this;
    for (char direction : path.toCharArray()) {
        if (direction == '0') {
            current = current.getLeftBST();
        } else if (direction == '1') {
            current = current.getRightBST();
        } else {
            throw new IllegalArgumentException("Path contains invalid characters");
        }

        if (current == null) {
            throw new NoSuchElementException("Subtree does not exist");
        }
    }

    return current;
	}




/**
 * Devuelve el contenido del nodo alcanzado desde la raíz
 * de éste árbol, con el camino dado.
 * 
 * Por ejemplo, sea un árbol "A" {10, {5, ∅, ∅}, {20, ∅, {30, ∅, ∅}}}:
 * 
 * 10
 * |  5
 * |  |  ∅
 * |  |  ∅
 * |  20
 * |  |  ∅ 
 * |  |  30
 * |  |  |  ∅
 * |  |  |  ∅
 * 
 * Entonces se tiene que A.getContentWithPath("1") es 20 y 
 * que A.getContentWithPath("") es 10.
 * 
 * @param path camino a seguir desde la raíz.
 * @return contenido del nodo alcanzado.
 * @throws NoSuchElementException si el camino no alcanza un nodo no vacío en el árbol
 * @throws IllegalArgumentException si el camino no contiene sólamente 0s y 1s
*/
	public T getContentWithPath(String path) {
	//TODO
	if (path == null) {
        throw new IllegalArgumentException("Path cannot be null");
    }

    BinarySearchTreeImpl<T> current = this;
    for (char direction : path.toCharArray()) {
        if (direction == '0') {
            current = current.getLeftBST();
        } else if (direction == '1') {
            current = current.getRightBST();
        } else {
            throw new IllegalArgumentException("Path contains invalid characters");
        }

        if (current == null) {
            throw new NoSuchElementException("Subtree does not exist");
        }
    }

    return current.getContent();
	}


	/**
	* Etiqueta los nodos con su posición en el recorrido descendente.
	* Por ejemplo, sea un árbol "A":
	* 
	* {10, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}
	* 
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  ∅
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
	* 
	* y el árbol quedaría etiquetado como:
	* 
	*  {10 [(descend, 3)], 
	*       {5 [(descend, 4)], {2 [(descend, 5)], ∅, ∅}, ∅}, 
	*       {20 [(descend, 2)], ∅, {30 [(descend, 1)], ∅, ∅}}}
	* 
   */
	public void tagDescendent() {//no se si funcionara bien
		// TODO
		BinarySearchTreeImpl<T> current = this;
		int instances = this.instancesCount();

		tagDescendenRec(current, instances);		
	}

	private void tagDescendenRec(TreeADT<T> current,int degree){
		if (current.isEmpty()) {
			return;
		}
	
		current.setTag("descend", degree--);
	
		// Recursivamente etiqueta cada sub-árbol
		for (int i = 0; i < current.getMaxDegree(); i++) {
			tagDescendenRec(current.getSubtree(i), degree);
		}
		return;
	}


	/**
	 * Acumula en orden preorden, una lista con los pares 'padre-hijo' en este árbol.
	 * 
	 * Por ejemplo, sea un árbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}
	 * 
	 * 10
	 * |  5
	 * |  |  2
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * |  |  ∅
	 * |  20
	 * |  |  ∅
	 * |  |  30
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * 
	 * el resultado sería una lista de cadenas:
	 * 
	 *         [(10,5), (5,2), (10,20), (20,30), ]
	 * 
	 * y además quedaría etiquetado como:
	 * 
	 *  {10 [(preorder, 1)], 
	 *       {5 [(preorder, 2)], {2 [(preorder, 3)], ∅, ∅}, ∅}, 
	 *       {20 [(preorder, 4)], ∅, {30 [(preorder, 5)], ∅, ∅}}}
	 * 
	 * @return lista con el resultado.
	 */
	public List<String> parentChildPairsTagPreorder() {//comprobar si funciona bien
	//TODO
		List<String> pairs = new ArrayList<>();
        int[] preorderIndex = {1}; // To keep track of the preorder position
        parentChildPairsTagPreorderRec(this, pairs, preorderIndex, null);
        return pairs;
	}

	private void parentChildPairsTagPreorderRec(BinarySearchTreeImpl<T> node, List<String> pairs, int[] preorderIndex, T parent) {
        if (node == null || node.isEmpty()) {
            return;
        }

        // Tag the current node with its preorder position
        node.setTag("preorder", preorderIndex[0]++);

        // If the parent is not null, add the pair (parent, current node)
        if (parent != null) {
            pairs.add("(" + parent + "," + node.getContent() + ")");
        }

        // Recursively process the left and right subtrees
        parentChildPairsTagPreorderRec(node.getLeftBST(), pairs, preorderIndex, node.getContent());
        parentChildPairsTagPreorderRec(node.getRightBST(), pairs, preorderIndex, node.getContent());
    }


	/**
	 * Etiqueta solamente los nodos que son hijos izquierdos 
	 *  de su padre con el valor de su posición en postorden, 
	 * devolviendo el número de nodos que son hijos izquierdos.
	 *
	 *   Por ejemplo, sea un árbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}
	 * 
	 * 10
	 * |  5
	 * |  |  2
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * |  |  ∅
	 * |  20
	 * |  |  ∅
	 * |  |  30
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * 
	 * devolverá 2
	 * y el árbol quedaría etiquetado como:
	 * Solo etiqueta el 5 y el 2, porque son los únicos nodos
	 * que son hijos izquierdos
	 *  {10, 
	 *       {5 [(postorder, 2)] , {2 [(postorder, 1)], ∅, ∅}, ∅}, 
	 *       {20, ∅, {30, ∅, ∅}}}
	 * 
	 * @return numero de nodos hijos izquierdos
	 */
	public int tagLeftChildrenPostorder() {
		//TODO
		return 0;
	}


	/**
	* Comprueba si el nodo actual (this) tiene un hermano 
	*  en el mismo nivel, que tiene el mismo nº de instancias que él
	*
	* Por ejemplo, sea un árbol "A":
	* 
	* {10, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}
	* la llamada a A.hasBrotherSameCount() devolverá false,
	*  porque al ser la raiz no tiene hermanos
	* 
	* la llamda a A.getSubtreeWithPath("0").hasBrotherSameCount() devolverá true, 
	* porque el 5 tiene hermano con el mismo count
	
	* @return true si su hermano tiene el mismo nº de instancias que él, 
	*    false  en caso contrario (si no tiene hermano o si lo tiene, este
	*    no tiene el mismo nº de instancias)
	*/
	public boolean hasBrotherSameCount() {
		//TODO
		return false;
	}
	
	/**
	* Devuelve el toString del nodo simétrico al (this)  
	* o la cadena vacía si no tiene nodo simétrico
	*
	** Por ejemplo, sea un árbol "A":
	* 
	* {10, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}
	* la llamada a a.toStringSimetric() devolverá "",
	*  porque al ser la raiz no tiene simétrico
	* 
	* la llamada a a.getSubtreeWithPath("0").toStringSimetric() devolverá , 
	* "{20, ∅, {30, ∅, ∅}}" 
    * la llamada a a.getSubtreeWithPath("00").toStringSimetric() devolverá , 
	* "{30, ∅, ∅}" 
		
	* @return la cadena vacía si no tiene nodo simétrico o el toString del nodo simétrico.
	*    
	*/
	public String toStringSimetric() {
		//TODO
		return null;
	}
	
	/**
	 * Busca y devuelve a partir del nodo que contiene el elemento pasado como parámetro 
	 * el elemento que está up posiciones hacia arriba y right hacia abajo bajando por la rama derecha. 
	 * Primero debe encontrar el elemento y despues comprueba si el nodo que contiene ese elemento
	 * tiene nodo a través del camino indicado por los otros dos parámetros.
     * Debe etiquetar desde el nodo que contiene el elemento,  hasta su objetivo,  los nodos del camino 
     * con un número consecutivo empezando por el 1 en el elemento buscado. 
     * 
     * Por ejemplo: para el árbol ejemplo= {10, {5, {2, ∅, ∅}, {7,∅, ∅},}, {20, {15, {12, ∅, ∅}, ∅ },{30, ∅, ∅}}}. 
     * 
     * Si se hace ejemplo.getRoadUpRight("7",2,2) devolverá el elemento 30 y etiquetará los nodos 7, 5, 10, 20, 30 con numeros consecutivos
     *  y la etiqueta road. 
     *  
     * Así el árbol quedaría etiquetado: 10 [(road, 3)],{5[(road, 2)], {2, ∅, ∅}, {7 [(road, 1)],∅, ∅},}, {20 [(road, 4)], {15, {12, ∅, ∅}, ∅},{30 [(road, 5)], ∅, ∅}}}
     *  siendo el nodo que contiene el 30 el nodo que devuelve.
	 * 
	 * @throws NoSuchElementException si el elemento a comprobar no esta en el arbol	
	 * @throws IllegalArgumentException si element es null
	 */
	public T getRoadUpRight(T elem, int up, int right) {
		//TODO
	return null;
	}	
	
	/**
	 * Importante: Solamente se puede recorrer el arbol una vez
	 * 
	 * Calcula y devuelve el numero de nodos que son hijos unicos y etiqueta cada
	 * nodo que sea hijo unico (no tenga hermano hijo del mismo padre) con la
	 * etiqueta "onlySon" y el valor correspondiente a su posicion segun el
	 * recorrido preorden en este arbol.
	 * 
	 * La rai­z no se considera hijo unico.
	 * 
	 * Por ejemplo, sea un arbol ejemplo, que tiene 3 hijos unicos, 
	 * la llamada a ejemplo.tagOnlySonPreorder() devuelve 3 y los va etiquetando
	 * segun su recorrido en preorden.
	 * 
	 * {30, {10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}, ∅}
	 * 
	 *
	 * el arbol quedari­a etiquetado:
	 * 
	 * {30, {10 [(onlySon, 2)], {5, {2 [(onlySon, 4)], ∅, ∅}, ∅}, {20, {15 [(onlySon, 6)], {12
	 * [(onlySon, 7)], ∅, ∅}, ∅}, ∅}, ∅}
	 * 
	 */
	public int tagOnlySonPreorder() {
    //TODO
	return 0;
	}
		
}
	
	