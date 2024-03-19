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

	//HECHAR UN VISTAZO, NO BORRA BIEN
	@Test
	public void removeAllTest() throws EmptyCollectionException{
		Assert.assertEquals(2, listaPepe.removeAll(pepe));
		Assert.assertEquals(lista., 2);
		Assert.assertEquals("( )", listaPepe.toString());
	}
}
	
	
