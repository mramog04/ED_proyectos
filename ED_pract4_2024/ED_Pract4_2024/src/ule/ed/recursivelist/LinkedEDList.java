package ule.ed.recursivelist;

import java.util.NoSuchElementException;

public class LinkedEDList<T> implements EDList<T> {

	//	referencia al primer  de la lista
	private Node<T> front;

	

	private class Node<T> {

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		T elem;

		Node<T> next;
	}

	private void elemNull(T elem){
		if(elem == null){
			throw new NullPointerException();
		}
	}



	@Override
	public boolean isEmpty() {
		// TODO 
		return this.front==null;
	}


	@Override
	public int size() {
		// TODO RECURSIVAMENTE
		return sizeRec(this.front);
	}

	private int sizeRec(Node<T> current){
		if(current == null){
			return 0;
		}else{
			return 1 + sizeRec(current.next);
		}
	}


	@Override
	public void addLast(T elem) {
		// TODO RECURSIVAMENTE
		elemNull(elem);
		Node<T> newNode =  new Node<T>(elem);
		addLastRec(this.front, newNode);
	}

	private void addLastRec(Node<T> current,Node<T> newNode){
		if(size()==0){
			this.front=newNode;//puede ser que aqui vaya un this.front = newNode;
		}else if(current.next==null){
			current.next=newNode;
		}else{
			addLastRec(current.next, newNode);
		}
	}

	
	@Override
	public void addPos(T elem, int position) {
		// TODO RECURSIVAMENTE
		elemNull(elem);
		if(position<=0){
			throw new IllegalArgumentException();
		}
		Node<T> newNode =  new Node<T>(elem);
		addPosRec(this.front,newNode,position-1,1);
	}

	private void addPosRec(Node<T> current,Node<T> newNode,int pos,int aux){
		if(pos>size()){
			addLast(newNode.elem);
		}else if(pos == 0){
			this.front=newNode;
			newNode.next=current;
		}else if(aux==pos){
			newNode.next=current.next;
			current.next=newNode;
		}else{
			aux++;
			addPosRec(current.next, newNode,pos, aux);
		}
	}

	@Override
	public boolean addBefore(T elem, T target) {
		// TODO Auto-generated method stub
		elemNull(elem);
		elemNull(target);
		if(this.front == null){
			addLast(elem);
			return false;
		}
		return addBeforeRec(front, elem, target, false, false);
	}
	//probablemente falle algo relacionado con devolver si es true o false
	private boolean addBeforeRec(Node<T> current,T elem,T target,boolean found,boolean fin){
		if(found==false && fin == true){
			addPos(elem, 1);
			return false;
		}else if(found == false && fin == false){
			if(current.elem.equals(target)){
				Node<T> newNode = new Node<T>(elem);
				newNode.next=current.next;
				current.next=newNode;
				found=true;
				fin=true;
			}if(current.next==null){
				fin = true;
				addBeforeRec(current, elem, target, found, fin);
			}else{
				addBeforeRec(current.next, elem, target, found, fin);
			}
		}
		return true;
	}

	@Override
	public T getElemPos(int position) {
		// TODO RECURSIVAMENTE
		if(position<1 || position > size()){
			throw new IllegalArgumentException();
		}
		return getElemPosRec(position,1 , this.front);
	}
	//muy probable que esto este mal hecho.
	private T getElemPosRec(int position,int aux,Node<T> current){
		Node<T> resultNode = current;
		if(position!=aux){
			aux++;
			resultNode = new Node<T>(getElemPosRec(position, aux, current.next));
		}
		return resultNode.elem;
	}


	@Override
	public int getPosFirst(T elem) {
		// TODO RECURSIVAMENTE
		elemNull(elem);
		return getPosFirstRec(this.front, elem, 1);
	}


	private int getPosFirstRec(Node<T> nodo, T elem, int pos) {
        if (nodo == null) {
            throw new NoSuchElementException("El elemento no está en la lista.");
        }
        if (nodo.elem.equals(elem)) {
            return pos;
        }
        return getPosFirstRec(nodo.next, elem, pos + 1);
    }

	@Override
	public int getPosLast(T elem) {
		// TODO RECURSIVAMENTE
		elemNull(elem);
		return getPosLastRec(this.front, elem, 1, -1);
	}

	private int getPosLastRec(Node<T> current, T elem, int pos, int lastPos) {
        if (current == null) {
            if (lastPos == -1) {
                throw new NoSuchElementException("El elemento no está en la lista.");
            }
            return lastPos;
        }
        if (current.elem.equals(elem)) {
            lastPos = pos;
        }
        return getPosLastRec(current.next, elem, pos + 1, lastPos);
    }


	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO RECURSIVAMENTE
		if(front==null){
			throw new EmptyCollectionException(null);
		}
		return removeLastRec(this.front);
	}

	private T removeLastRec(Node<T> current) {
        if (current.next == null) {
            T valor = current.elem;
            front = null; // Si el nodo actual es el último, la lista queda vacía
            return valor;
        }
        if (current.next.next == null) {
            T valor = current.next.elem;
            current.next = null; // Si el siguiente nodo es el último, se elimina
            return valor;
        }
        return removeLastRec(current.next);
    }



	

	@Override
	public int removeLastElem(T elem) {
		// TODO RECURSIVAMENTE
		elemNull(elem);
		if (this.front == null) {
			throw new NoSuchElementException();
		}
		int[] pos = {0}; // Utilizamos un array para almacenar la posición y mantenerla mutable
		if (removeLastElemRec(front, null, elem, pos)) {
			return pos[0]+1; // Devolvemos la posición almacenada en el array
		} else {
			throw new NoSuchElementException("La lista no contiene el elemento especificado.");
		}
	}

	private boolean removeLastElemRec(Node<T> current, Node<T> prev, T elem, int[] pos) {
		if (current == null) {
			return false; // No se encontró el elemento en la lista
		}
		boolean removed = removeLastElemRec(current.next, current, elem, pos); // Llamada recursiva para buscar el elemento
		if (removed) {
			pos[0]++; // Incrementamos la posición solo si se elimina el elemento
		}
		if (current.elem.equals(elem)) {
			if (current.next == null) {
				if (prev != null) {
					prev.next = null;
				} else {
					front = null;
				}
				return true; // Se eliminó el último elemento
			}
			return true; // Se eliminó un elemento antes del último
		}
		return removed;
	}

	@Override
	public EDList<T> reverse() {
		// TODO RECURSIVAMENTE
		LinkedEDList<T> reverseList = new LinkedEDList<>();
		reverseRecursive(this.front, reverseList);
		return reverseList;
	}

	private void reverseRecursive(Node<T> current, EDList<T> reverseList) {
        if (current == null) {
            return;
        }
		reverseList.addPos(current.elem,1);
        reverseRecursive(current.next, reverseList);
    }



	@Override
	public int removeOddElements(){
		// TODO RECURSIVAMENTE
		return removeOddElementsRec(front, null, 1);
	}

	private int removeOddElementsRec(Node<T> current, Node<T> prev, int pos) {
        if (current == null) {
            return 0;
        }
        if (pos % 2 != 0) {
            if (prev != null) {
                prev.next = current.next;
            } else {
                front = current.next;
            }
            return 1 + removeOddElementsRec(current.next, prev, pos + 1);
        }
        return removeOddElementsRec(current.next, current, pos + 1);
    }


	@Override
	public int removeConsecDuplicates() {
		// TODO RECURSIVAMENTE
		return removeConsecDuplicatesRec(this.front);
	}

	private int removeConsecDuplicatesRec(Node<T> current) {
        if (current == null || current.next == null) {
            return 0;
        }
        if (current.elem.equals(current.next.elem)) {
            Node<T> nextNode = current.next;
            current.next = nextNode.next;
            return 1 + removeConsecDuplicatesRec(current);
        }
        return removeConsecDuplicatesRec(current.next);
    }


	@Override
	public String toSringExceptFromUntilReverse(int from, int until) {
		// TODO RECURSIVAMENTE
		if (from <= 0 || until <= 0 || from < until) {
			throw new IllegalArgumentException("from y until deben ser mayores que 0, y from debe ser mayor o igual que until");
		}
		StringBuilder result = new StringBuilder();
		result.append("(");		
		result.append(toSringExceptFromUntilReverseRec(from, until, 0, this.front));		
		result.append(")");
		return result.toString();
	}

	private String toSringExceptFromUntilReverseRec(int from, int until, int currentIndex, Node<T> current) {
		if (current == null) {
			return "";
		}
		
		// Verificar si el índice actual está dentro del rango [from, until]
		if (currentIndex < from && currentIndex >= until) {
			// Llamar recursivamente al siguiente nodo sin modificar la cadena resultante
			return current.elem + " " + toSringExceptFromUntilReverseRec(from,until,currentIndex+1,current.next);
		}
		
		// Construir la cadena resultante, insertando el elemento actual seguido de un espacio y llamando recursivamente al siguiente nodo
		return toSringExceptFromUntilReverseRec(from, until, currentIndex + 1, current.next);
	}


	@Override
	public boolean lengthEqualsTo(int n) {
		// TODO RECURSIVAMENTE
		if(n>size()){
			return false;
		}
		return lengthEqualsToRec(this.front, n);
	}

	private boolean lengthEqualsToRec(Node<T> current,int n){
		if(current!=null && n!=0){
			lengthEqualsToRec(current.next, n-1);
		}else if(current!=null && n==0){
			return false;
		}
		return true;
	}

	@Override
	public int removeEvenElements() {
		// TODO Auto-generated method stub
		return removeEvenElementsRec(front, null, 1);
	}
	//puede ser que este metodo este mal
	private int removeEvenElementsRec(Node<T> current, Node<T> prev, int pos){
		if (current == null) {
            return 0;
        }
        if (pos % 2 == 0) {
            if (prev != null) {
                prev.next = current.next;
            } else {
                front = current.next;
            }
            return 1 + removeEvenElementsRec(current.next, prev, pos + 1);
        }
        return removeEvenElementsRec(current.next, current, pos + 1);
	}

	//a lo mejor aqui hay q poner un elemNull
	@Override
	public int removeFirstElem(T elem) {
		// TODO Auto-generated method stub
		elemNull(elem);
		if (front == null) {
			throw new NoSuchElementException("La lista está vacía");//aqui deberia haber un EmptyCollectionException pero bueno...
		}
		
		if (front.elem.equals(elem)) {
			front = front.next;
			return 1;
		}
		
		return removeFirstElemRec(front, elem);	}

	private int removeFirstElemRec(Node<T> current, T elem){
		if (current == null || current.next == null) {
			throw new NoSuchElementException("Elemento no encontrado en la lista");
		}
		
		if (current.next.elem.equals(elem)) {
			current.next = current.next.next;
			return 2;
		}
		
		return 1 + removeFirstElemRec(current.next, elem);
	}

	@Override
	public String toString() {
		// TODO RECURSIVAMENTE
		return "(" + toStringRec(this.front) + ")";
	}

	private String toStringRec(Node<T> current) {
		if (current == null) {
			return "";
		}
		if (current.next == null) {
			return current.elem.toString()+" ";
		}
		return current.elem + " " + toStringRec(current.next);
	}
	

	
	
}
