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
		return current < count;
		
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			//TODO	
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			current++;
			return (T) data[current-1];
		}
		}

	private class ArrayEDListEvenPositionIterator<T> implements Iterator<T>{
		private int current=1;

		@Override
		public boolean hasNext() {
		//TODO
		return current < count;
		
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			//TODO	
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			current+=2;
			return (T) data[current-2];
		}
	} 
	private class ArrayEDListOddPositionIterator<T> implements Iterator<T> {
		private int current=1;

		@Override
		public boolean hasNext() {
		//TODO
		return current < count;
		
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			//TODO	
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			current+=2;
			return (T) data[current-2];
		}
		}
	

		private class ArrayEDListOddEvenIterator<T> implements Iterator<T> {
			private int current=1;
			private int a = 0;
	
			@Override
			public boolean hasNext() {
			//TODO
			return current < count;
			
			}
	
			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				//TODO	
				if(!hasNext()){
					throw new NoSuchElementException();
				}
				if(current%2==0){
					current++;
				}
				if(current==count){
					a=20;
					current = 0;
				}
				if(a==20){
					current+=2;
					return (T) data[current-2];
				}
				current+=2;
				return (T) data[current-2];
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
		for(int i = 0; i < count;i++){
			if(this.data[i].equals(elem)){
				value = true;
			}
		}
		return value;
	}

	private int countOcurrences(T elem){
		int contador=0;
		for(int i = 0; i < count;i++){
			if(this.data[i].equals(elem)){
				contador++;
			}
		}
		return contador;
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
		}
		for(int i = 0;i<count;i++){
			save[i+1]=data[i];
		}
		save[0]=elem;
		data = save;
		count++;
	}


	@Override
	public void addLast(T elem) {
		// TODO
		elemnull(elem);
		if(!(contains(elem)) && size()==data.length){
			expandCapacity();
		}
		data[count]=elem;
		count++;
	}

	@Override
	public void addPenult(T elem) {
		// TODO	
		elemnull(elem);
		T[] save = (T[])(new Object[data.length*2]);
		if(!(contains(elem)) && size()==data.length){
			expandCapacity();
		}
		save = data;
		save[count+1]=data[count];
		save[count]=elem;
		count++;
		data = save;

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
		}
		save = data;
		for(int i = position-1;i<=data.length;i++){
			save[i+1]=data[i];
		}
		save[position]=elem;
		data = save;
	}
	

	// Es muy probable que aqui salte un nullpointer exception
	@Override
	public T removeFirst() throws EmptyCollectionException {
		// TODO 
		emptyList();
		T value = data[0];
		T[] save = (T[])(new Object[data.length]);
		for(int i = 0;i<count;i++){
			save[i-1]=data[i];
		}
		count--;
		data=save;
		return value;
		
	}

	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO 
		emptyList();
		T value = data[count-1];
		count--;
		value=data[count];
		for(int i = 0;i<count;i++){
			data[i]=data[i+1];
		}
		return value;
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		emptyList(); 
		T value = data[count - 2];
		T[] save = (T[]) (new Object[data.length]);
		for (int i = 0; i < count - 1; i++) {
			save[i] = data[i];
		}
		save[count - 1] = null;
		data = save;
		count--; 
    return value;
	}


	@Override
	public int removeElem(T elem) throws EmptyCollectionException {
		// TODO 
		emptyList();
		int resultado=-1;
		for(int i = 0;i<count;i++){
			if(data[i].equals(elem)){
				resultado=i;
				break;
			}
		}
		if (resultado==-1){
			throw new NoSuchElementException("Elemento no econtrado");
		}
		T[] save = (T[])(new Object[data.length]);
		for(int i=resultado;i<count;i++){
			save[i]=data[i+1];
		}
		save[count-1]=null;
		data=save;
		count--;
		return resultado;	
	}
	
	@Override
	public T getElemPos(int position) {
		// TODO 
		if(position<0||position>size()){
			throw new IllegalArgumentException("Posicion invalida");
		}
		return data[position];
	}

	@Override
	public int getPosLast(T elem) {
		// TODO 
		elemnull(elem);
		for(int i = data.length;i>=0;i--){
			if (data[i]==elem){
				return i;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		// TODO 
		int contador=0;
		for(int i = 0;i<count;i++){
			if(data[i]==elem){
				removeElem(elem);
				contador++;
			}
		}
		return contador;
	}

	@Override
	public String toString() {
		// TODO
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for(int i = 0;i<count;i++){
			if(data[i]!=null){
				sb.append(data[i]+" ");
			}
		} 
		sb.append(")");
		return sb.toString();
	}
   
	
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		T[] save = (T[])(new Object[data.length]);
		data=save;
		count=0;
	}

	@Override
	public int getPosFirst(T elem) {
		// TODO Auto-generated method stub
		elemnull(elem);
		for(int i = 0;i < count;i++){
			if(data[i]==elem){
				return i;
			}
		}
		throw new NoSuchElementException("El elemento no estaba en la lista.");
		
	}

	//METODO NO HECHO!!!!!
	@Override
	public IEDList<T> listOfRepeatedElems() {
		// TODO Auto-generated method stub
		int i = 0,pos = 0;
		ArrayEDList list = new ArrayEDList<T>();
		while(data[i]!=null){
			if(countOcurrences(data[i])>1 && !list.contains(data[i])){
				list.addPos(data[i],pos);
			}
			i++;
		}
		return list;
	}

	@Override
	public int countElem(T elem) {
		// TODO Auto-generated method stub
		elemnull(elem);
		int value = -1;
		for(int i = 0;i<count;i++){
			if(data[i]!=null && data[i]==elem){
				value=i;
				break;
			}
		}
		if(value==-1){
			throw new NoSuchElementException("El elemento no esta en la lista");
		}
		return value;
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		
		return new ArrayEDListIterator<T>();
	}


	@Override
	public Iterator<T> evenPositionsIterator() {
		// TODO Auto-generated method stub
		
		return new ArrayEDListEvenPositionIterator<T>();
	}

	@Override
	public Iterator<T> oddPositionsIterator() {
		// TODO Auto-generated method stub
		return new ArrayEDListOddPositionIterator<T>();
	}

	@Override
	public Iterator<T> OddEvenIterator() {
		// TODO Auto-generated method stub
		return new ArrayEDListOddEvenIterator<T>();
	}
}
