package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

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


	@Override
	public boolean isEmpty() {
		//TODO
		return false;
		}


	@Override
	public void clear() {
		//TODO
		
	}

	@Override
	public void addFirst(T elem) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addLast(T elem) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addPos(T elem, int position) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public T getElemPos(int position) {
		
		//TODO
		return null;
	}


	@Override
	public int getPosFirst(T elem) {
		//TODO

		return 0;
	}


	@Override
	public int getPosLast(T elem) {
		//TODO

		return 0;
	}

	
	@Override
	public T removeLast()  throws EmptyCollectionException{
		//TODO
		return null;
	}
	

	@Override
	public T removePos(int pos)  throws EmptyCollectionException{
		// TODO
		return null;
	

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
		return 0;
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