package ule.ed.recursivelist;


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
		return 0;
	}



	@Override
	public int getPosLast(T elem) {
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO RECURSIVAMENTE
		return null;
	}



	

	@Override
	public T removeLastElem(T elem) {
		// TODO RECURSIVAMENTE
		return null;
	}



	@Override
	public EDList<T> reverse() {
		// TODO RECURSIVAMENTE
		return null;
	}



	@Override
	public int removeOddElements(){
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public int removeConsecDuplicates() {
		// TODO RECURSIVAMENTE
		return 0;
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


	

	
	
}
