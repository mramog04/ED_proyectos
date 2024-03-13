package ule.ed.list;

import java.util.Iterator;
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
		
		public LinkedListIterator(Node<T> aux) {
			//TODO
		}

		@Override
		public boolean hasNext() {
			//TODO
			
			return false;
		}

		@Override
		public T next() {
	  // TODO
			
			return null;

			
		}	
	}
	
	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES
	
		// FIN ITERADORES
		private void elemnull(T elem){
			if(elem == null){
				throw new NullPointerException();
			}
		}
	


	@Override
	public int size() {
		// TODO 
		int contador = 0;
		Node<T> current = this.front;
		while (current.next != null){
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
		Node<T> next = this.front;
		Node<T> current = this.front;
		Node<T> newNode = new Node<T>(elem);
		for(int i = position-1;i>0;i--){
			next=next.next;
		}
		for(int i = position-2;i>0;i--){
			current=current.next;
		}
		current.next=newNode;
		newNode.next=next;
	}

	@Override
	public T removeFirst() throws EmptyCollectionException {
		// TODO 
	    
		return null;
	}

	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO 
		return null;
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		// TODO 
		return null;
		
	}
	
	@Override
	public T getElemPos(int position) {
		// TODO 
		return null;
	}

	@Override
	public int getPosLast(T elem) {
		// TODO	
		return 0;
	 }
	

	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		// TODO 	
		return 0;

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
			return 0;
		}

	
		@Override
		public Iterator<T> iterator() {
			// TODO 
			return new LinkedListIterator<T>(front);
		}
		

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getPosFirst(T elem) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public IEDList<T> listOfRepeatedElems() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int countElem(T elem) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Iterator<T> evenPositionsIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterator<T> oddPositionsIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterator<T> OddEvenIterator() {
			// TODO Auto-generated method stub
			return null;
		}
}