package ule.ed.list;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayEDList<T> implements IEDList<T> {
	static final int DEFAULT_CAPACITY=10;

    private T[] data;
	private int count;
	
	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA IMPLEMENTADA CON ARRAYS

	

	private class ArrayEDListIterator<T> implements Iterator<T> {
		private int current=0;

		@Override
		public boolean hasNext() {
		//TODO
		return false;
		
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			//TODO	
			return null;
		}
		}


	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES
	
	// FIN ITERADORES
	
	
	@SuppressWarnings("unchecked")
	public  ArrayEDList() {
	   // TODO: inicializar los atributos (crear el array con capacidad por defecto)
		this.count = 0;
		this.data=(T[])(new Object[DEFAULT_CAPACITY]);
	}

	@SuppressWarnings("unchecked")
	public  ArrayEDList(int capacity) {
	  // TODO
	  this.count = 0;
	  this.data=(T[])(new Object[capacity]);
	}
	@Override
	public int size() {
		// TODO 
		return this.count;
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return (count==0);
	}

	
	private void expandCapacity(){

		T[] larger = (T[])(new Object[data.length*2]);
		for(int i = 0;i<data.length;i++){
			larger[i]=this.data[i];
		}
		this.data=larger;
	}
	
	@Override
	public void addFirst(T elem) {
		// TODO 
		
	}


	@Override
	public void addLast(T elem) {
		// TODO
		if(!(contains(elem))){
			if(size()==data.length){
				expandCapacity();
				data[count]=elem;
				count++;
			}
		}
	}

	@Override
	public void addPenult(T elem) {
		// TODO	
	}

	@Override
	public void addPos(T elem, int position) {
		// TODO 
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
	public int removeElem(T elem) throws EmptyCollectionException {
		// TODO 
		return 0;	
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
		return "";
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
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ArrayEDListIterator<T>();
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
