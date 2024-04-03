package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Node;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {


	//	referencia al primer aux de la lista
	private DoubleNode<T> front;

	//	referencia al Último aux de la lista
	private DoubleNode<T> last;


	private class DoubleNode<T> {

		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}

		T elem;

		DoubleNode<T> next;
		DoubleNode<T> prev;
	}

	///// ITERADOR normal //////////

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIterator<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIterator(DoubleNode<T> aux) {
		}

		@Override
		public boolean hasNext() {
			// TODO
			return false;
		}
	

		@Override
		public T next() {
		// TODO
			return null;
		}
	}

	////// FIN ITERATOR



	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIteratorReverse(DoubleNode<T> aux) {
			// TODO	
			}

		@Override
		public boolean hasNext() {
			// TODO	
			return false;
			}

		@Override
		public T next() {
			// TODO
			return null;
		}
	}
	
	// TODO: añadir clases para el resto de iteradores

	/////

	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		// permite añadir varios elementos a la lista en el constructor
		for (T elem:v) {
			this.addLast(elem);
		}
	}

	private void elemnull(T elem){
		if(elem == null){
			throw new NullPointerException();
		}
	}

	@Override
	public boolean isEmpty() {
		//TODO
		return this.front==null;
		}


	@Override
	public void clear() {
		//TODO
		this.front=null;
		this.last=null;
	}

	@Override
	public void addFirst(T elem) {
		// TODO Auto-generated method stub
		elemnull(elem);
		DoubleNode<T> current = this.front;
		DoubleNode<T> newNode = new DoubleNode<T>(elem);
		if(isEmpty()){
			this.last=current;
		}
		front=newNode;
		newNode.next = current;
	}


	@Override
	public void addLast(T elem) {
		// TODO Auto-generated method stub
		elemnull(elem);
		DoubleNode<T> current = this.last;
		DoubleNode<T> newNode = new DoubleNode<T>(elem);
		if(isEmpty()){
			this.front=current;
		}
		current.next=newNode;
		this.last=newNode;
	}

	//va a saltar fallo en posicion 2;
	@Override
	public void addPos(T elem, int position) {
		// TODO Auto-generated method stub
		elemnull(elem);
		if(position<=0){
			throw new IllegalArgumentException();
		}
		if(position>size()){
			addLast(elem);
		}
		DoubleNode<T> current = this.front;
		DoubleNode<T> current_next = this.front.next;
		DoubleNode<T> newNode = new DoubleNode<T>(elem);
		for(int i = position-2;i>0;i--){
			current=current.next;
			current_next=current_next.next;
		}
		current.next=newNode;
		newNode.prev=current;
		newNode.next=current_next;
		current_next.prev=newNode;
	}



	@Override
	public T getElemPos(int position) {
		if(position <= 0 || position >size()){
			throw new IllegalArgumentException();
		}
		DoubleNode<T> current = this.front;
		for(int i = 0;i<position-1;i++){
			current=current.next;
		}
		//TODO
		return current.elem;
	}


	@Override
	public int getPosFirst(T elem) {
		//TODO
		elemnull(elem);
		DoubleNode<T> current = this.front;
		boolean encontrado = false;
		boolean noEncontrado = false;
		int contador = 1;
		while(encontrado == false){
			if(current.elem.equals(elem)){
				encontrado=true;
			}else if(current.next==null){
				encontrado = true;
				noEncontrado = true;
			}else{
				current=current.next;
				contador++;
			}
		}
		if(noEncontrado==true){
			throw new NoSuchElementException();
		}
		return contador;
	}


	@Override
	public int getPosLast(T elem) {
		//TODO
		elemnull(elem);
		DoubleNode<T> current = this.last;
		boolean encontrado = false;
		boolean noEncontrado = false;
		int contador = size();
		while(encontrado == false){
			if(current.elem.equals(elem)){
				encontrado=true;
			}else if(current.prev==null){
				encontrado = true;
				noEncontrado = true;
			}else{
				current=current.prev;
				contador--;
			}
		}
		if(noEncontrado==true){
			throw new NoSuchElementException();
		}
		return contador;
	}

	//dudas de que funcione bien
	@Override
	public T removeLast()  throws EmptyCollectionException{
		//TODO
		DoubleNode<T> current = this.last;
		if(size()==1){
			this.front.next=this.last;
			return current.elem;
		}
		DoubleNode<T> current_prev = this.last.prev;
		this.last.prev=current_prev;
		current_prev.next=this.last;
		return current.elem;
	}
	

	@Override
	public T removePos(int pos)  throws EmptyCollectionException{
		// TODO
		if(pos < 1 || pos >size()){
			throw new IllegalArgumentException();
		}
		if(size()==0){
			throw new EmptyCollectionException(null);
		}
		DoubleNode<T> current = this.front;
		if(size()==1){
			removeLast();
		}
		for(int i = size()-1;pos<i;i--){
			current=current.next;
		}
		DoubleNode<T> current_prev = current.prev;
		DoubleNode<T> current_next = current.next;
		current_prev.next=current_next;
		current_next.prev=current_next;
		return current.elem;
	}


	@Override
	public int removeN(T elem, int times) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public DoubleList<T> copy() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int size() {
		//TODO
		int contador = 0;
		DoubleNode<T> current = this.front;
		while (current != null){
			current = current.next;
			contador++;
		}
		return contador;
	}


	
	@Override
	public int maxRepeated() {
	// TODO
		return 0;
	}


	@Override
	public void addAfter(T elem, T target) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addAfterAll(T elem, T target) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public T removePenul() throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int countElem(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean sameElems(DoubleList<T> other) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public String toString() {
		// TODO
		return null;
	}
	
	@Override
	public String toStringReverse() {
		// TODO
		return "";
	}


	@Override
	public String toStringFromUntil(int from, int until) {
		// TODO
				
		return null;
	}
	
	@Override
	public String toStringFromUntilReverse(int from, int until) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Iterator<T> iterator() {
		return new DoubleLinkedListIterator<>(front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new DoubleLinkedListIteratorReverse<>(last);
	}
	

	@Override
	public Iterator<T> progressIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> progressReverseIterator() {
		// TODO Auto-generated method stub
		return null;
	}


}