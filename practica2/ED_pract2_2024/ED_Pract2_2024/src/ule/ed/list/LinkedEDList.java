package ule.ed.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

public class LinkedEDList<T> implements IEDList<T> {

	//	referencia al primer  de la lista
	private Node<T> front;

	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA

	private class Node<T> {

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		T elem;

		Node<T> next;
	}
	///////
	///// ITERADOR normal //////////

	//@SuppressWarnings("hiding")
	private class LinkedListIterator<T> implements Iterator<T> {
		// declarar atributos del iterador
		private Node<T> current;
		
		public LinkedListIterator(Node<T> aux) {
			//TODO
			this.current = aux;
		}

		@Override
		public boolean hasNext() {
			//TODO
			return current != null;
		}

		@Override
		public T next() {
	  // TODO
			if(!hasNext()){
				throw new NoSuchElementException("No hay siguiente elemento");
			}
			T elem = current.elem;
			current = current.next;
			return elem;			
		}	
	}
	

	private class evenPositionsIterator<T> implements Iterator<T> {
		// declarar atributos del iterador
		private Node<T> current;
		
		public evenPositionsIterator(Node<T> front) {
			//TODO Auto-generated constructor stub
			this.current=front;
		}


		@Override
		public boolean hasNext() {
			//TODO
			return current != null && current.next != null;
		}

		@Override
		public T next() {
	  // TODO
			if(!hasNext()){
				throw new NoSuchElementException("No hay siguiente elemento");
			}
			T elem = current.next.elem;
			current = current.next.next;
			return elem;			
		}	
	}

	private class oddPositionsIterator<T> implements Iterator<T> {
		// declarar atributos del iterador
		private Node<T> current;
		
		public oddPositionsIterator(Node<T> front) {
			//TODO Auto-generated constructor stub
			this.current=front;
		}


		@Override
		public boolean hasNext() {
			//TODO
			return current.next != null;
		}

		@Override
		public T next() {
	  // TODO
			if(!hasNext()){
				throw new NoSuchElementException("No hay siguiente elemento");
			}
			T elem = current.elem;
			current = current.next.next;
			return elem;			
		}	
	}

	private class OddEvenIterator<T> implements Iterator<T> {
		// declarar atributos del iterador
		private Node<T> current;
		public int value = 0;
		
		public OddEvenIterator(Node<T> front) {
			//TODO
			this.current = front.next;
		}

		@Override
		public boolean hasNext() {
			//TODO
			return current!= null && current.next != null;
		}

		@Override
		public T next() {
			T elem = current.elem;
			Node<T> save = current.next;
			while(value==0){
				if(hasNext()){
					elem=current.elem;
					current=current.next.next;
					return elem;
				}else{
					value = 1;
				}
			}
			current=save;
			while(value==1){
				if(hasNext()){
					elem=current.elem;
					current=current.next.next;
					return elem;
				}else{
					value=0;
				}
			}
			return elem;
		}	
	}
	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES
	
		// FIN ITERADORES
		private void elemnull(T elem){
			if(elem == null){
				throw new NullPointerException();
			}
		}

		private void emptylist() throws EmptyCollectionException{
			if(front==null){
				throw new EmptyCollectionException("Lista vacia");
			}
		}

		private boolean contains(T elem) {
			Node<T> current = this.front;
			while (current != null) {
				if (current.elem.equals(elem)) {
					return true;
				}
				current = current.next;
			}
			return false;
		}
		
		private int countOccurrences(T data) {
			int count = 0;
			Node<T> current = this.front;
			while (current != null) {
				if (current.elem.equals(data)) {
					count++;
				}
				current = current.next;
			}
			return count;
		}
	


	@Override
	public int size() {
		// TODO 
		int contador = 0;
		Node<T> current = this.front;
		while (current != null){
			current = current.next;
			contador++;
		}
		return contador;
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return this.front==null;
	}

	@Override
	public void addFirst(T elem) {
		// TODO 
		elemnull(elem);
		Node<T> current = this.front;
		Node<T> newNode = new Node<T>(elem);
		front=newNode;
		newNode.next = current;


	}

	@Override
	public void addLast(T elem) {
		// TODO 
		elemnull(elem);
		Node<T> current = this.front;
		Node<T> newNode = new Node<T>(elem);
		if (current == null) {
			front = newNode;
			return;
		}
		while(current.next!=null){
			current=current.next;
		}
		current.next=newNode;
	}

	@Override
	public void addPenult(T elem) {
		// TODO 
		elemnull(elem);
		Node<T> last = this.front;
		Node<T> current = this.front;
		if (current == null) {
			addFirst(elem);
			return;
		}
		if(current.next==null){
			addLast(elem);
			return;
		}
		Node<T> newNode = new Node<T>(elem);
		while(last.next!=null){
			last=last.next;
		}
		for(int i = size()-2;i>0;i--){
			current=current.next;
		}
		current.next=newNode;
		newNode.next=last;
	}

	@Override
	public void addPos(T elem, int position) {
		// TODO 
		elemnull(elem);
		if(position<=0){
			throw new IllegalArgumentException();
		}
		Node<T> nextNode = this.front;
		Node<T> current = this.front;
		if(position==1){
			addFirst(elem);
			return;
		}
		if (position>size()){
			addLast(elem);
			return;
		}
		Node<T> newNode = new Node<T>(elem);
		for(int i = position-1;i>0;i--){
			nextNode=nextNode.next;
		}
		for(int i = position-2;i>0;i--){
			current=current.next;
		}
		current.next=newNode;
		newNode.next=nextNode;
	}

	@Override
	public T removeFirst() throws EmptyCollectionException {
		// TODO 
	    emptylist();
		Node<T> current = this.front;
		Node<T> nextNode = current.next;
		this.front=nextNode;
		return current.elem;
	}

	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO 
		emptylist();
		Node<T> current = this.front;
		for(int i = size()-2;i>0;i--){
			current=current.next;
		}
		Node<T> save= current.next;
		current.next = null;
		return save.elem;
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		// TODO 
		emptylist();
		Node<T> current = this.front;
		Node<T> penult;
		Node<T> last;
		for(int i = size()-3;i>0;i--){
			current=current.next;
		}
		penult=current.next;
		last=penult.next;
		current.next=last;
		return penult.elem;
		
	}
	
	@Override
	public T getElemPos(int position) {
		// TODO 
		if(position > size() || position < 1){
			throw new IllegalArgumentException("La lista no tiene tantos elementos");
		}
		Node<T> current = this.front;
		for(int i = position-1;i>0;i--){
			current=current.next;
		}
		return current.elem;
	}

	@Override
	public int getPosLast(T elem) {
		// TODO	
		elemnull(elem);
		Node<T> current = this.front;
		Node<T> Node = new Node<T>(elem);
		int contador = 0;
		while(current != Node){
			current = current.next;
			contador++;
		}
		if(contador==0){
			throw new NoSuchElementException("El elemento no esta en la lista");
		}
		return contador;
	 }
	

	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		// TODO 	
		elemnull(elem);
		emptylist();
		int contador = 0;
		Node<T> previusNode = this.front;
		Node<T> current = previusNode.next;
		Node<T> nextNode = current.next;
		Node<T> Node = new Node<T>(elem);
		while(previusNode == Node){
			previusNode = previusNode.next;
			current = current.next;
			nextNode = nextNode.next;
		}
		for(int i = size();i>0;i--){
			if(current==Node){
				current=nextNode;
				previusNode.next=current;
				nextNode=nextNode.next;
				contador++;
			}
		}
		return contador;

	}

	
	
	@Override
	public String toString() {
		// TODO
		StringBuilder sb = new StringBuilder();
		Node<T> current = this.front;
		sb.append("(");
		while(current!=null){
			sb.append(current.elem+" ");
			current=current.next;
		}
		sb.append(")");
		return sb.toString();
	}


		@Override
		public int removeElem(T elem) throws EmptyCollectionException {
			// TODO Auto-generated method stub
			elemnull(elem);
			emptylist();
		
			Node<T> prevNode = null;
			Node<T> currentNode = front;
			int contador = 0;
			boolean borrado = false;
		
			while (currentNode != null && !borrado) {
				if (currentNode.elem.equals(elem)) { 
					if (prevNode == null) { 
						front = currentNode.next; 
					} else {
						prevNode.next = currentNode.next; 
					}
					currentNode = currentNode.next; 
					borrado = true;
				} else {
					prevNode = currentNode; 
					currentNode = currentNode.next;
				}
				contador++;
			}
			if(!borrado){
				throw new NoSuchElementException();
			}
			return contador;
		}

	
		@Override
		public Iterator<T> iterator() {
			// TODO 
			return new LinkedListIterator<T>(front);
		}
		

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			this.front = null;	
		}

		@Override
		public int getPosFirst(T elem) {
			// TODO Auto-generated method stub
			elemnull(elem);
			Node<T> current = this.front;
			Node<T> Node = new Node<T>(elem);
			int contador = 0;
			while(current!=Node){
				current = current.next;
				contador++;
			}
			return contador;
		}

		@SuppressWarnings("unchecked")
		@Override
		public IEDList<T> listOfRepeatedElems() {
			LinkedList<T> repeatedList = new LinkedList<>();
    		Node<T> current = front;

    		while (current != null) {
        		if (!repeatedList.contains(current.elem) && countOccurrences(current.elem) > 1) {
            		repeatedList.add(current.elem);
        		}
        		current = current.next;
    		}

    		return  (IEDList<T>) repeatedList;
		}

		@Override
		public int countElem(T elem) {
			// TODO Auto-generated method stub
			Node<T> current = this.front;
			Node<T> Node = new Node<T>(elem);
			int contador = 0;
			for(int i = size()-1;i>0;i++){
				if(current==Node){
					contador++;
				}
				current=current.next;
			}
			return contador;
		}

		@Override
		public Iterator<T> evenPositionsIterator() {
			// TODO Auto-generated method stub
			return new evenPositionsIterator<T>(front);
		}

		@Override
		public Iterator<T> oddPositionsIterator() {
			// TODO Auto-generated method stub
			return new oddPositionsIterator<T>(front);
		}

		@Override
		public Iterator<T> OddEvenIterator() {
			// TODO Auto-generated method stub
			return new OddEvenIterator<T>(front);
		}
}