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

	private void elemnull(T elem){
		if(elem == null){
			throw new NullPointerException();
		}
	}
	
	private void expandCapacity(){

		T[] larger = (T[])(new Object[data.length*2]);
		for(int i = 0;i<data.length;i++){
			larger[i]=this.data[i];
		}
		this.data=larger;
	}
	
	private boolean contains(T elem){
		boolean value = false;
		for(int i = 0; i <= data.length;i++){
			if(data[i].equals(elem)){
				value = true;
			}
		}
		return value;
	}

	private void emptyList() throws EmptyCollectionException{
		if(count == 0){
			throw new EmptyCollectionException(null);
		}
	}
	@Override
	public void addFirst(T elem) {
		// TODO 
		elemnull(elem);
		T[] save = (T[])(new Object[data.length*2]);
		if(!(contains(elem)) && size()==data.length){
			expandCapacity();
			for(int i = 0;i<=data.length;i++){
				save[i+1]=data[i];
			}
			save[0]=elem;
			data = save;
			count++;
		}
	}


	@Override
	public void addLast(T elem) {
		// TODO
		elemnull(elem);
		if(!(contains(elem)) && size()==data.length){
			expandCapacity();
			data[count]=elem;
			count++;
		}
	}

	@Override
	public void addPenult(T elem) {
		// TODO	
		elemnull(elem);
		T[] save = (T[])(new Object[data.length*2]);
		if(!(contains(elem)) && size()==data.length){
			expandCapacity();
			save = data;
			save[count+1]=data[count];
			save[count]=elem;
			count++;
			data = save;
		}

	}

	@Override
	public void addPos(T elem, int position) {
		// TODO 
		elemnull(elem);
		T[] save = (T[])(new Object[data.length*2]);
		if(position<=0){
			throw new IllegalArgumentException();
		}
		if(position>size()){
			addLast(elem);
		}
		if(!(contains(elem)) && size()==data.length){
			expandCapacity();
			save = data;
			for(int i = position-1;i<=data.length;i++){
				save[i+1]=data[i];
			}
			save[position]=elem;
			data = save;
		}
	}

	// Es muy probable que aqui salte un nullpointer exception
	@Override
	public T removeFirst() throws EmptyCollectionException {
		// TODO 
		emptyList();
		T value;
		T[] save = (T[])(new Object[data.length*2]);
		for(int i = 0;i<=data.length;i++){
			save[i-1]=data[i];
			count--;
		}
		value=data[0];
		data=save;
		return value;
		
	}

	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO 
		emptyList();
		T value;
		value=data[count];
		for(int i = 0;i<=data.length;i++){
			data[i]=data[i+1];
		}
		return value;
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
