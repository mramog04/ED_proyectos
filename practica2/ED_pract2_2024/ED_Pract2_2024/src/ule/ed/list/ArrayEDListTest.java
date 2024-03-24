package ule.ed.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;


public class ArrayEDListTest {
	private ArrayEDList<String> lista;
	private ArrayEDList<String> listaPepe;
	private String pepe = "pepe";
	private String pepeNull = null;
	
	@Before
	public void setUp() {
		 lista= new ArrayEDList<String>();
		 listaPepe = new ArrayEDList<String>();
		 listaPepe.addFirst(pepe);
		 listaPepe.addFirst(pepe);
		 listaPepe.addFirst(pepe);
		 listaPepe.addFirst(pepe);
	}

	@Test
	public void ArrayVaciaTest() {
		assertEquals(0,lista.size());
	}
	
	// test addFirst comprueba que dispara excepción
	@Test(expected=NullPointerException.class)
	public void ArrayAddFirstElementoNuloTest() {
			lista.addFirst(null);
	}
	
	@Test
	public void ArrayAddFirstTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
	}
	
	@Test
	public void ArrayAddFirstExpandCapacityTest() {
		lista=new ArrayEDList<String>(3);
		
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		lista.addFirst("10");
		Assert.assertEquals("(10 7 3 2 )", lista.toString());		
	}


	//test de iteradores
	@Test
	public void ArrayIteratorTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		Iterator<String>  iter=lista.iterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("3", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void ArrayEvenIteratorNElemesParTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		lista.addFirst("8");
		Assert.assertEquals("(8 7 3 2 )", lista.toString());

		Iterator<String>  iter=lista.evenPositionsIterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}
	
	
	
	
	// TEST ITERADORES EN LISTA VACÍA
	@Test(expected=NoSuchElementException.class)
	public void ArrayNextListaVaciaTest() {
			lista.iterator().next();	}
	
	@Test
	public void sizeTest(){
		Assert.assertEquals(0, lista.size());
		lista.addFirst(pepe);
		Assert.assertEquals(1, lista.size());
		lista.addLast(pepe);
		lista.addLast(pepe);
		lista.addLast(pepe);
		Assert.assertEquals(4, lista.size());
	}
	
	@Test
	public void isEmptyTest(){
		Assert.assertTrue(lista.isEmpty());
		lista.addFirst(pepe);
		Assert.assertFalse(lista.isEmpty());
	}

	@Test
	public void clearTest(){
		lista.addFirst(pepe);
		lista.addLast(pepe);
		lista.addLast(pepe);
		lista.addLast(pepe);
		lista.addLast(pepe);
		Assert.assertFalse(lista.isEmpty());
		lista.clear();
		Assert.assertTrue(lista.isEmpty());
	}

	@Test(expected=NullPointerException.class)
	public void addFirstNullTest(){
		lista.addFirst(pepeNull);
	}

	@Test
	public void addFirstTest(){
		lista.addFirst(pepe);
		Assert.assertEquals("(pepe )", lista.toString());
	}

	@Test(expected=NullPointerException.class)
	public void addLastNullTest(){
		lista.addLast(pepeNull);
	}

	@Test
	public void addLastTest(){
		lista.addLast(pepe);
		Assert.assertEquals("(pepe )", lista.toString());
		lista.addFirst("1");
		Assert.assertEquals("(1 pepe )", lista.toString());
	}

	@Test(expected=NullPointerException.class)
	public void addPenultNullTest(){
		lista.addPenult(pepeNull);
	}

	@Test
	public void addPenultTest(){
		lista.addPenult(pepe);
		Assert.assertEquals("(pepe )", lista.toString());
		lista.addFirst(pepe);
		lista.addLast(pepe);
		lista.addPenult("PEPE");
	}

	@Test(expected=NullPointerException.class)
	public void addPosNullTest(){
		lista.addPos(pepeNull, 1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addPosIllegalArgumentTest(){
		lista.addPos(pepe, 0);
		lista.addPos(pepe, -1);
	}

	@Test
	public void addPosTest(){
		lista.addPos(pepe, 1);
		lista.addPos(pepe, 2);
		lista.addPos(pepe, 3);
		lista.addPos(pepe, 4);
		Assert.assertEquals("(pepe pepe pepe pepe )", lista.toString());
	}

	@Test(expected=EmptyCollectionException.class)
	public void removeFirstEmptyTest() throws EmptyCollectionException{
		lista.removeFirst();
	}

	@Test
	public void removeFirst() throws EmptyCollectionException{
		Assert.assertEquals(pepe, listaPepe.removeFirst());
		Assert.assertEquals("(pepe pepe pepe )", listaPepe.toString());
	}

	@Test(expected=EmptyCollectionException.class)
	public void removeLastEmptyTest() throws EmptyCollectionException{
		lista.removelast();
	}

	@Test
	public void removeLastTest() throws EmptyCollectionException{
		Assert.assertEquals(pepe, listaPepe.removelast());
		Assert.assertEquals("(pepe pepe pepe )", listaPepe.toString());
	}

	@Test(expected=EmptyCollectionException.class)
	public void removePenultEmptyTest() throws EmptyCollectionException{
		lista.removePenult();
	}

	@Test(expected=NoSuchElementException.class)
	public void removePenult1ElemTest() throws NoSuchElementException, EmptyCollectionException{
		lista.addFirst(pepe);
		lista.removePenult();
	}

	@Test
	public void removePenultTest() throws EmptyCollectionException{
		Assert.assertEquals(pepe, listaPepe.removePenult());
		Assert.assertEquals("(pepe pepe pepe )", listaPepe.toString());
	}

	@Test(expected=EmptyCollectionException.class)
	public void removeElemEmptyTest() throws EmptyCollectionException{
		lista.removeElem(pepe);
	}

	@Test(expected=NoSuchElementException.class)
	public void removeElemNOelemTest() throws NoSuchElementException, EmptyCollectionException{
		listaPepe.removeElem("PEPE");
	}

	@Test
	public void removeElemTest() throws EmptyCollectionException{
		listaPepe.removeElem(pepe);
		listaPepe.removeElem("pepe");
		Assert.assertEquals("(pepe pepe )", listaPepe.toString());
	}

	@Test(expected=IllegalArgumentException.class)
	public void getElemPosIllegalArgumentTest(){
		listaPepe.getElemPos(0);
		listaPepe.getElemPos(-1);
	}

	@Test
	public void getElemPosTest(){
		Assert.assertEquals(pepe, listaPepe.getElemPos(1));
		Assert.assertEquals(pepe, listaPepe.getElemPos(2));
	}

	@Test(expected=NullPointerException.class)
	public void getPosFirstNullTest(){
		lista.getPosFirst(null);
	}

	@Test(expected=NoSuchElementException.class)
	public void getPosFirstNoSuchExcepTest(){
		listaPepe.getPosFirst("PEPE");
	}

	@Test
	public void getPosFirstTest(){
		Assert.assertEquals(1,listaPepe.getPosFirst(pepe));
		listaPepe.addLast("PEPE");
		Assert.assertEquals(5,listaPepe.getPosFirst("PEPE"));
	}

	@Test(expected=NullPointerException.class)
	public void getPosLastNullTest(){
		lista.getPosLast(null);
	}

	@Test(expected=NoSuchElementException.class)
	public void getPosLastNoSuchExcepTest(){
		listaPepe.getPosLast("PEPE");
	}

	@Test(expected=NullPointerException.class)
	public void removeAllNullTest() throws EmptyCollectionException{
		listaPepe.removeAll(pepeNull);
	}

	@Test(expected=NoSuchElementException.class)
	public void removeAllNoSuchTest() throws EmptyCollectionException{
		listaPepe.removeAll("PEPE");
	}

	@Test(expected=EmptyCollectionException.class)
	public void removeAllEmptyTest() throws EmptyCollectionException{
		lista.removeAll(pepe);
	}

	@Test
	public void removeAllTest() throws EmptyCollectionException{
		listaPepe.addFirst("PEPE");
		Assert.assertEquals(1,listaPepe.removeAll("PEPE"));
		Assert.assertEquals(4, listaPepe.removeAll(pepe));
		Assert.assertEquals("()", listaPepe.toString());
	}

	@Test
	public void listOfRepeatedElemesTest(){
		listaPepe.addFirst("PEPE");
		lista.addFirst(pepe);
		Assert.assertEquals(lista.toString(), listaPepe.listOfRepeatedElems().toString());
	}

	@Test(expected=NullPointerException.class)
	public void countElemNull(){
		lista.countElem(pepeNull);
	}

	
	@Test
	public void countElemTest(){
		Assert.assertEquals(4, listaPepe.countElem(pepe));
	}

	@Test(expected=NoSuchElementException.class)
	public void iteratorNoSuchTest(){
		Iterator<String>  iter=lista.evenPositionsIterator();
		Assert.assertFalse(iter.hasNext());
		iter.next();
	}
	@Test
	public void iteratorTest(){
		listaPepe.addLast("PEPE");
		Iterator<String>  iter=listaPepe.iterator();
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("pepe", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("pepe", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("pepe", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("pepe", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("PEPE", iter.next());
	}

	@Test(expected=NoSuchElementException.class)
	public void evenPositionIteratorNoSuchTest(){
		Iterator<String>  iter=lista.evenPositionsIterator();
		Assert.assertFalse(iter.hasNext());
		iter.next();
	}

	@Test
	public void evenPositionIteratorTest(){
		listaPepe.addLast("PEPE");
		listaPepe.addLast("PEPE");
		Iterator<String>  iter=listaPepe.evenPositionsIterator();
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("pepe", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("pepe", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("PEPE", iter.next());
	}

	@Test(expected=NoSuchElementException.class)
	public void oddPositionsIteratorNoSuchTest(){
		Iterator<String>  iter=lista.oddPositionsIterator();
		Assert.assertFalse(iter.hasNext());
		iter.next();
	}

	@Test
	public void oddPositionsIteratorTest(){
		listaPepe.addLast("PEPE");
		Iterator<String>  iter=listaPepe.oddPositionsIterator();
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("pepe", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("pepe", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("PEPE", iter.next());
	}

	@Test(expected=NoSuchElementException.class)
	public void oddEvenIteratorNoSuchTest(){
		Iterator<String>  iter=lista.OddEvenIterator();
		Assert.assertFalse(iter.hasNext());
		iter.next();
	}

	@Test
	public void oddEvenIteratorTest(){
		lista.addLast("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("5");
		lista.addLast("6");
		Iterator<String>  iter=lista.OddEvenIterator();
		Assert.assertEquals("1", iter.next());
		Assert.assertEquals("3", iter.next());
		Assert.assertEquals("5", iter.next());
		Assert.assertEquals("2", iter.next());
		Assert.assertEquals("4", iter.next());
		Assert.assertEquals("6", iter.next());
	}

	//Se supone que en los test de agora hay un test con este nombre que falla,da nosuchElemenException,
	@Test
	public void countElemAllPosTest(){
		Assert.assertEquals(4, listaPepe.countElem(pepe));
	}

	//hay un test con este nombre que tambien falla, no entiendo que pide
	@Test
	public void AddPenult_expandCapacity(){
		lista.addPenult("2");
		lista.addPenult("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addPenult("4");
		Assert.assertEquals("(3 4 2 )", lista.toString());
	}

	@Test
	public void removePenult_2elemTest() throws EmptyCollectionException{
		lista.addFirst("2");
		lista.addLast("3");
		Assert.assertEquals("2", lista.removePenult());
		Assert.assertEquals("(3 )", lista.toString());
	}

	@Test(expected=IllegalArgumentException.class)
	public void getElemPos_PosCeroVacia(){
		lista.getElemPos(1);
	}

	@Test
	public void removeLastTest_caso2() throws EmptyCollectionException{
		lista.addFirst("1");
		lista.addLast("2");
		lista.removelast();
		Assert.assertEquals(("(1 )"), lista.toString());
		lista.removelast();
		Assert.assertEquals(("(1 )"), lista.toString());
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		Assert.assertEquals("4", lista.removelast());

	}

	@Test
	public void addPosMayorTest(){
		lista.addPos(pepe,20);
	}

	@Test
	public void countElem_not_contains(){
		Assert.assertEquals(0,listaPepe.countElem("1"));
	}

	//en los test de agora hay un test que se llama asi y que noo pasa
	@Test
	public void removeElemPosInternas() throws EmptyCollectionException{
		listaPepe.addPos("1", 1);
		Assert.assertEquals(1, listaPepe.removeElem("1"));
	}

	@Test
	public void getPosLastTest(){
		listaPepe.addLast("1");
		Assert.assertEquals(5, listaPepe.getPosLast("1"));
		Assert.assertEquals("1", listaPepe.getElemPos(5));
	}

	//Test de agora salta que necesita la excepcion IllegalArgument...
	@Test(expected = IllegalArgumentException.class)
	public void getElemPosCeroNoVacia(){
		lista.addPos(pepe, 0);
		lista.getElemPos(0);
	}

	@Test
	public void removePenultToOneTest() throws EmptyCollectionException{
		lista.addFirst("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("5");
		Assert.assertEquals("4",lista.removePenult());
		Assert.assertEquals("(1 2 3 5 )", lista.toString());
		lista.removePenult();
		Assert.assertEquals("(1 2 5 )", lista.toString());
		lista.removePenult();
		Assert.assertEquals("(1 5 )", lista.toString());
		lista.removePenult();
		Assert.assertEquals("(5 )", lista.toString());
	}

	//Test de agora con este nombre no pasa
	@Test
	public void removeElemLastPos() throws EmptyCollectionException{
		lista.addFirst("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("5");
		Assert.assertEquals("5", lista.removelast());
	}
	

	@Test
	public void evenOddIteratorTest(){
		lista.addFirst("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("5");
		Iterator<String> iter = lista.OddEvenIterator();
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("1", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("3", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("5", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("2", iter.next());
		Assert.assertTrue(iter.hasNext());
		Assert.assertEquals("4", iter.next());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getElemPos0NoVacia(){
		lista.addPos("1", 0);
		Assert.assertEquals("1",lista.getElemPos(0));
	}

	@Test
	public void removeElemUltimaPosTest() throws EmptyCollectionException{
		lista.addFirst("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("5");
		Assert.assertEquals(5, lista.removeElem("5"));
		Assert.assertEquals("(1 2 3 4 )", lista.toString());
	}
}
	
	
