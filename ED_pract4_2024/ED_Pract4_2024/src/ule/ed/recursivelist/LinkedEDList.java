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

	private void addFirst(T elem) {
        Node<T> newNode = new Node<T>(elem);
        if (front == null) {
            front = newNode;
        } else {
            Node<T> aux = front;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = newNode;
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
		return false;
	}

	@Override
	public T getElemPos(int position) {
		// TODO RECURSIVAMENTE
		if(position<1 || position > size()){
			throw new IllegalArgumentException();
		}
		getElemPosRec(position,1 , this.front);
		return null;
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
		if(this.front==null){
			throw new NoSuchElementException();
		}
		if(front.next==null){
			if(front.elem.equals(elem)){
				front=null;
				return 1;
			}else{
				throw new NoSuchElementException();
			}
		}
		return removeLastElemRec(front, null, elem, 1);
	}

	private int removeLastElemRec(Node<T> current, Node<T> prev, T elem, int pos) {
        if (current.next == null) {
            if (current.elem.equals(elem)) {
                if (prev != null) {
                    prev.next = null;
                } else {
                    front = null;
                }
                return pos;
            } else {
                throw new NoSuchElementException("La lista no contiene el elemento especificado.");
            }
        }
        if (current.next.elem.equals(elem)) {
            return removeLastElemRec(current.next, current, elem, pos + 1);
        }
        return removeLastElemRec(current.next, prev, elem, pos);
    }

	@Override
	public EDList<T> reverse() {
		// TODO RECURSIVAMENTE
		EDList<T> reverseList = new EDList<>();//no se como iniciar esto la vrd
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
		return removeOddRec(front, null, 1);
	}

	private int removeOddRec(Node<T> current, Node<T> prev, int pos) {
        if (current == null) {
            return 0;
        }
        if (pos % 2 != 0) {
            if (prev != null) {
                prev.next = current.next;
            } else {
                front = current.next;
            }
            return 1 + removeOddRec(current.next, prev, pos + 1);
        }
        return removeOddRec(current.next, current, pos + 1);
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
		return null;
	}


	@Override
	public boolean lengthEqualsTo(int n) {
		// TODO RECURSIVAMENTE
		return false;
	}

	@Override
	public int removeEvenElements() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int removeFirstElem(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}
	//hay que hacer el toString de forma recursiva esto demomento es para ir omprobando los test
	@Override
	public String toString() {
		// TODO RECURSIVAMENTE
		return "(" + toStringRecursive(this.front) + ")";
	}

	private String toStringRecursive(Node<T> current) {
		if (current == null) {
			return "";
		}
		if (current.next == null) {
			return current.elem.toString();
		}
		return current.elem + " " + toStringRecursive(current.next);
	}
	

	
	
}
