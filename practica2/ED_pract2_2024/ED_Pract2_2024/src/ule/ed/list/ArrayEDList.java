package ule.ed.list;

import java.util.Arrays;
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
			current+=2;
			return (T) data[current-2];
		}
		}
	

		private class ArrayEDListOddEvenIterator<T> implements Iterator<T> {
			private int current=0;
			private boolean pares=false;
	
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
			T element = (T) data[current];
    		current += 2;
        	if (!pares && current >= size()) {
        		current = 1;
				pares = true;
    		}
    		return element;
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

	public int getCount(){
		return this.count;
	}
	private void elemnull(T elem){
		if(elem == null){
			throw new NullPointerException();
		}
	}
	
	private void expandCapacity(){

		T[] larger = (T[])(new Object[data.length*2]);
		System.arraycopy(data, 0, larger, 0, size());
		this.data=larger;
	}
	
	private boolean contains(T elem){
		boolean value = false;
		if(size()==0){
			return false;
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public void addPenult(T elem) {
		// TODO	
		elemnull(elem);
		T[] save;
		if(!(contains(elem)) && size()==data.length){
			expandCapacity();
			save = (T[])(new Object[data.length*2]);
		}else{
			save = (T[])(new Object[data.length]);
		}
		if(size()==0 || size()==1){
			addFirst(elem);
			return;
		}
		System.arraycopy(data, 0, save, 0, size() - 1);
    	save[size() - 1] = elem; 
   		save[size()] = data[size() - 1]; 
    	count++;
    	data = save;
	}

	@Override
	public void addPos(T elem, int position) {
		// TODO 
		elemnull(elem);
		if(position<=0){
			throw new IllegalArgumentException();
		}
		if(position>size()){
			addLast(elem);
			return;
		}
		if(position==1){
			addFirst(elem);
			return;
		}
		if(!(contains(elem)) && size()==data.length){
			expandCapacity();
		}
		T[] save = Arrays.copyOf(data, data.length*2);
		for(int i = size();i>= position;i--){
			save[i]=save[i-1];
		}
		save[position-1]=elem;
		data = save;
		count++;
	}
	

	// NO FUNCIONA BIEN EL EMPTYCOLLECTTIONEXCEPTION
	@Override
	public T removeFirst() throws EmptyCollectionException {
		// TODO 
		emptyList();
		T value = data[0];
		T[] save = (T[])(new Object[data.length]);
		for(int i = 1;i<count;i++){
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
		T value;
		if(size()==1){
			value = data[0];
			return removeFirst();
		}
		value = data[count-1];
		data[count-1]=null;
		count--;
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T removePenult() throws EmptyCollectionException {
		emptyList();
		if(size()==1){
			throw new NoSuchElementException();
		}
		T value = data[size()-2];
		if(size()==2){
			removeFirst();
			return value;
		}
		T[] save = (T[]) (new Object[data.length]);
		System.arraycopy(data, 0, save, 0, size()-1);
		save[size()-2]=data[size()-1];
		save[size()]=null;
		data = save;
		count--; 
    	return value;
	}


	@Override
	public int removeElem(T elem) throws EmptyCollectionException {
		// TODO 
		emptyList();
		elemnull(elem);
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
		for(int i=resultado;i<count-1;i++){
			data[i]=data[i+1];
		}
		data[count-1]=null;
		count--;
		return resultado+1;	
	}
	
	@Override
	public T getElemPos(int position) {
		// TODO 
		if(size()==0){
			throw new IllegalArgumentException("Posicion invalida");
		}
		if(position<=0||position>size()){
			throw new IllegalArgumentException("Posicion invalida");
		}
		
		return data[position-1];
	}

	@Override
	public int getPosLast(T elem) {
		// TODO 
		elemnull(elem);
		for(int i = count-1;i>=0;i--){
			if (data[i].equals(elem)){
				return i+1;
			}
		}
		throw new NoSuchElementException();
	}
	//repasar hacerlo de otra forma
	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		// TODO 
	elemnull(elem); 
    emptyList();
    
	int contador = 0;
	T[] save = (T[]) (new Object[data.length]);

	for (int i = 0; i < count; i++) {
		if (data[i].equals(elem)) { 
			contador++; 
		} else {
			save[i - contador] = data[i];
		}
	}
    count -= contador;
    for (int i = 0; i < count; i++) {
        data[i] = save[i];
    }
    
    if (contador == 0) {
        throw new NoSuchElementException("Elemento no encontrado");
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

	//PUEDE HABER PROBLEMAS CON LAS POSICIONES; DE MOMENTO HAY UNNA ÑAPA
	@Override
	public int getPosFirst(T elem) {
		// TODO Auto-generated method stub
		elemnull(elem);
		for(int i = 0;i < count;i++){
			if(data[i]==elem){
				return i+1;
			}
		}
		throw new NoSuchElementException("El elemento no estaba en la lista.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public IEDList<T> listOfRepeatedElems() {
		// TODO Auto-generated method stub
		int i = 0,pos = 1;
		ArrayEDList list = new ArrayEDList<T>();
		while(data[i]!=null){
			if(countOcurrences(data[i])>1 && !list.contains(data[i])){
				list.addPos(data[i],pos);
			}
			i++;
			pos++;
		}
		return list;
	}

	@Override
	public int countElem(T elem) {
		// TODO Auto-generated method stub
		elemnull(elem);
		int value = 0;
		for(int i = 0;i<count;i++){
			if(data[i]!=null && data[i]==elem){
				value++;
			}
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
