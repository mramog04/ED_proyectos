package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.w3c.dom.Node;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {


	//	referencia al primer aux de la lista
	public DoubleNode<T> front;

	//	referencia al Último aux de la lista
	public DoubleNode<T> last;


	public class DoubleNode<T> {

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
			node=aux;
		}

		@Override
		public boolean hasNext() {
			// TODO
			return node!=null;
		}
	

		@Override
		public T next() {
		// TODO
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			DoubleNode<T> aux = node;
			node=node.next;
			return aux.elem;
		}
	}

	////// FIN ITERATOR



	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIteratorReverse(DoubleNode<T> aux) {
			// TODO	
			node=aux;
			}

		@Override
		public boolean hasNext() {
			// TODO	
			return node.prev!=null;
			}

		@Override
		public T next() {
			// TODO
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			DoubleNode<T> aux = node;
			node=node.prev;
			return aux.elem;
		}
	}
	
	// TODO: añadir clases para el resto de iteradores
	@SuppressWarnings("hiding")
	private class DoubleLinkedListProgressIterator<T> implements Iterator<T> {
		DoubleNode<T> node;
		int cont=0,incr=1;
		public DoubleLinkedListProgressIterator(DoubleNode<T> aux) {
			// TODO	
			node=aux;
			}

		@Override
		public boolean hasNext() {
			// TODO	
			return node!=null;
			}

		@Override
		public T next() {
			// TODO
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			T value = node.elem;
			for (int i = 0; i < cont; i++) {
				if (node == null) {
					break;
				}
				node = node.next;
			}
			cont++;
			if (node == null) {
				node = node.next;
				cont = ++incr + 1;
			}
			return value;
		}
	}
	
	@SuppressWarnings("hiding")
	private class DoubleLinkedListProgressReverseIterator<T> implements Iterator<T> {
		DoubleNode<T> node;
		int skip=1;
		public DoubleLinkedListProgressReverseIterator(DoubleNode<T> aux) {
			// TODO	
			node=aux;
			}

		@Override
		public boolean hasNext() {
			// TODO	
			return node!=null && node.prev!=null;
			}

		@Override
		public T next() {
			// TODO
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			T value = node.elem;
			for (int i = 0; i < skip; i++) {
				node = node.prev;
				if (node == null) {
					break;
				}
			}
			skip++;
			return value;
		}
	}

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

	public DoubleNode<T> getFront(){
		return this.front;
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
		DoubleNode<T> newNode = new DoubleNode<>(elem);
		if (isEmpty()) {
			this.front = newNode;
			this.last = newNode;
		} else if(size()==1){
			this.front.next=newNode;
			this.last=newNode;
			newNode.prev=this.front;
		} else {
			this.last.next = newNode;
			newNode.prev = this.last;
			this.last = newNode;
		}
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
			return;
		}
		if(position==1){
			elemnull(elem);
			DoubleNode<T> current = this.front;
			DoubleNode<T> newNode = new DoubleNode<T>(elem);
			front=newNode;
			newNode.next = current;
			current.prev=newNode;
			return;
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
		if(isEmpty()){
			throw new EmptyCollectionException(null);
		}
		T elementoBorrado = this.last.elem;

		if(size()==1){
			this.front=null;
			this.last=null;
		}else {
			DoubleNode<T> newNodeLast = this.last.prev;
			this.last=newNodeLast;
			newNodeLast.next=null;
		}
		return elementoBorrado;
	}
	

	@Override
	public T removePos(int pos)  throws EmptyCollectionException{
		// TODO
		if(pos < 1 || pos >size()){
			throw new IllegalArgumentException();
		}
		if(isEmpty()){
			throw new EmptyCollectionException(null);
		}
		DoubleNode<T> current = this.front;
		if(size()==1){
			T removedElem=removeLast();
			return removedElem;
		}
		for(int i = 1;i<pos;i++){
			current=current.next;
		}
		DoubleNode<T> current_prev = current.prev;
		DoubleNode<T> current_next = current.next;
		if (current_prev == null) {
			this.front = current_next;
		} else {
			current_prev.next = current_next;
		}
	
		if (current_next != null) {
			current_next.prev = current_prev;
		} else {
			this.last = current_prev;
		}
		return current.elem;
	}


	@Override
	public int removeN(T elem, int times) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		elemnull(elem);
		if(times<1){
			throw new IllegalArgumentException();
		}
		if(isEmpty()){
			throw new EmptyCollectionException(null);
		}
		int contador = 0;
		DoubleNode<T> current = this.front;
	
		while (current != null && contador < times) {
			if (current.elem.equals(elem)) {
				DoubleNode<T> prevNode = current.prev;
				DoubleNode<T> nextNode = current.next;
	
				if (prevNode != null) {
					prevNode.next = nextNode;
				} else {
					this.front = nextNode;
				}
	
				if (nextNode != null) {
					nextNode.prev = prevNode;
				} else {
					this.last = prevNode;
				}
	
				contador++;
			}
	
			current = current.next;
		}
		return contador;
	}



	@Override
	public DoubleList<T> copy() {
		// TODO Auto-generated method stub
		DoubleList<T> newList = new DoubleLinkedListImpl<T>(); 

        DoubleNode<T> current = this.front;
        while (current != null) {
            newList.addLast(current.elem);
            current = current.next;
		}

		return newList;
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
	if (isEmpty()) { 
        return 0;
    }

    int maxCount = 0; 
    int currentCount = 1; 
    T maxRepeatedElement = null; 

    DoubleNode<T> current = this.front;
    DoubleNode<T> nextNode;

    while (current != null) {
        nextNode = current.next;
        while (nextNode != null) {
            if (current.elem.equals(nextNode.elem)) {
                currentCount++; 
            }
            nextNode = nextNode.next;
        }
        
        if (currentCount > maxCount) {
            maxCount = currentCount;
            maxRepeatedElement = current.elem;
        }
        
        currentCount = 1; 
        current = current.next;
    }

    return maxCount;

	}


	@Override
	public void addAfter(T elem, T target) {
		// TODO Auto-generated method stub
		elemnull(elem);
		elemnull(target);
	
		DoubleNode<T> newNode = new DoubleNode<>(elem);
		DoubleNode<T> current = this.front;
	
		while (current != null && !current.elem.equals(target)) {
			current = current.next;
		}
	
		if (current == null) {
			throw new NoSuchElementException();
		}
	
		DoubleNode<T> nextNode = current.next;
		current.next = newNode;
		newNode.prev = current;
		newNode.next = nextNode;
		if (nextNode != null) {
			nextNode.prev = newNode;
		} else {
			this.last = newNode;
		}
	}


	@Override
	public void addAfterAll(T elem, T target) {
		// TODO Auto-generated method stub
		elemnull(elem);
		elemnull(elem);
		DoubleNode<T> current = this.front;
		DoubleNode<T> current_next = current.next;
		DoubleNode<T> newNode = new DoubleNode<T>(elem);
		int contador=0;
		while(contador < size()){
			if(current.elem.equals(target)){
				current.next=newNode;
				current_next.prev=newNode;
				newNode.prev=current;
				newNode.next=current_next;
			}
			current_next=current_next.next;
			current=current.next;
			contador++;
		}
	}


	@Override
	public T removePenul() throws EmptyCollectionException {
		// TODO Auto-generated method stub
		if(size()==1){
			throw new NoSuchElementException();
		}
		if(size()==0){
			throw new EmptyCollectionException(null);
		}
		DoubleNode<T> current = this.last;
		DoubleNode<T> current_prev = current.prev;
		DoubleNode<T> current_prev_prev = current.prev.prev;
		current_prev_prev.next=current;
		current.prev=current_prev_prev;
		return current_prev.elem;
	}


	@Override
	public int countElem(T elem) {
		// TODO Auto-generated method stub
		elemnull(elem);
		if(size()==0){
			return 0;
		}
		if(size()==1){
			if(this.front.elem.equals(elem)){
				return 1;
			}else{
				return 0;
			}
		}
		DoubleNode<T> current = this.front;
		int contador=0,i=0;
		while(contador < size() && current!=null){
			if(current.elem.equals(elem)){
				i++;
			}
			current=current.next;
			contador++;
		}
		return i;
	}

	private DoubleNode<T> getFrontNode(DoubleList<T> list) {
		Iterator<T> iterator = list.iterator();
		if (iterator.hasNext()) {
			return new DoubleNode<>(iterator.next());
		}
		return null;
	}

	
	@Override
	public boolean sameElems(DoubleList<T> other) {
		// TODO Auto-generated method stub
		if (other == null) {
			throw new NullPointerException("La lista pasada como parámetro es nula");
		}
	
		int thisSize = size();
		int otherSize = other.size();
	
		if (thisSize != otherSize) {
			return false;
		}
	
		DoubleNode<T> otherFront = getFrontNode(other);
		DoubleNode<T> currentThis = this.front;
		DoubleNode<T> currentOther = otherFront;
	
		while (currentThis != null) {
			if (!currentThis.elem.equals(currentOther.elem)) {
				return false;
			}
			currentThis = currentThis.next;
			currentOther = currentOther.next;
		}
	
		// Verificar si llegamos al final de other
		if (currentOther != null) {
			return false;
		}
	
		return true;
	}



	@Override
	public String toString() {
		// TODO
		StringBuilder sb = new StringBuilder();
		DoubleNode<T> current = this.front;
		sb.append("(");
		while(current!=null){
			sb.append(current.elem+" ");
			current=current.next;
		}
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public String toStringReverse() {
		// TODO
		if (isEmpty()) { 
			return "()"; 
		}
	
		StringBuilder sb = new StringBuilder();
		sb.append("(");
	
		DoubleNode<T> current = this.last; 
		while (current != null) { 
			sb.append(current.elem).append(" "); 
			current = current.prev; 
		}
	
		sb.append(")"); 
		return sb.toString();
	}


	@Override
	public String toStringFromUntil(int from, int until) {
		// TODO
		if (from <= 0 || until <= 0 || until < from) {
			throw new IllegalArgumentException("Los parámetros from y until deben ser mayores que 0 y until debe ser mayor o igual que from.");
		}
	
		StringBuilder sb = new StringBuilder();
		sb.append("(");
	
		DoubleNode<T> current = this.front; 
		int currentPosition = 1;
		while (current != null && currentPosition < from) {
			current = current.next;
			currentPosition++;
		}
	
		
		while (current != null && currentPosition <= until) {
			sb.append(current.elem+" "); 
			current = current.next; 
			currentPosition++;
		}
	
		sb.append(")"); 
		return sb.toString();
	}
	
	@Override
	public String toStringFromUntilReverse(int from, int until) {
		// TODO Auto-generated method stub
		if (from <= 0 || until <= 0 || from < until) {
			throw new IllegalArgumentException("Los parámetros from y until deben ser mayores que 0 y from debe ser mayor o igual que until.");
		}
	
		StringBuilder sb = new StringBuilder();
		sb.append("(");
	
		DoubleNode<T> current = this.last; 
		int currentPosition = size(); 
		while (current != null && currentPosition > from) {
			current = current.prev;
			currentPosition--;
		}
	
		
		while (current != null && currentPosition >= until) {
			sb.append(current.elem).append(" "); 
			current = current.prev; 
			currentPosition--;
		}
	
		sb.append(")"); 
		return sb.toString();
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
		return new DoubleLinkedListProgressIterator<>(front);
	}

	@Override
	public Iterator<T> progressReverseIterator() {
		// TODO Auto-generated method stub
		return new DoubleLinkedListProgressReverseIterator<>(last);
	}


}