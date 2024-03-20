package ule.ed.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;


public class LinkedEDListTest {
	private LinkedEDList<String> lista;
	private LinkedEDList<String> emptyList;
	private LinkedEDList<String> list;
	private String numNull;
	private String num;



	
	@Before
	public void setUp() {
		lista= new LinkedEDList<String>();
		emptyList=new LinkedEDList<String>();
		list= new LinkedEDList<String>();
		list.addFirst("4");
		list.addFirst("3");
		list.addFirst("2");
		list.addFirst("1");
		numNull=null;
		num="num";

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
		lista=new LinkedEDList<String>();
		
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
		Assert.assertEquals(0, emptyList.size());
		Assert.assertEquals(4, list.size());
	}

	@Test
	public void isEmptyTest(){
		Assert.assertFalse(list.isEmpty());
		Assert.assertTrue(emptyList.isEmpty());
	}

	@Test
	public void clearTest(){
		list.clear();
		Assert.assertTrue(list.isEmpty());
	}

	@Test(expected=NullPointerException.class)
	public void addLastNullTest(){
		emptyList.addLast(numNull);
	}
	
	@Test
	public void addLastTest(){
		emptyList.addLast(num);
		Assert.assertEquals("(num )", emptyList.toString());
		emptyList.addLast(num);
		emptyList.addLast(num);
		Assert.assertEquals("(num num num )", emptyList.toString());
	}

	@Test(expected=NullPointerException.class)
	public void addPenultNull(){
		emptyList.addPenult(numNull);
	}
	
	@Test
	public void addPenult(){
		emptyList.addPenult(num);
		Assert.assertEquals("(num )", emptyList.toString());
		emptyList.addPenult(num);
		emptyList.addPenult(num);
		Assert.assertEquals("(num num num )", emptyList.toString());
	}

	@Test(expected=NullPointerException.class)
	public void addPosNull(){
		emptyList.addPos(numNull, 1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addPosIllegalTest(){
		emptyList.addPos(num, 0);
		emptyList.addPos(num, -1);
	}

	@Test
	public void addPosTest(){
		emptyList.addPos(num, 1);
		Assert.assertEquals("(num )", emptyList.toString());
		emptyList.addPos(num, 2);
		emptyList.addPos(num, 3);
		Assert.assertEquals("(num num num )", emptyList.toString());
		list.addPos("NUM", 2);
		Assert.assertEquals("(1 NUM 2 3 4 )", list.toString());
	}

	@Test(expected=EmptyCollectionException.class)
	public void removeFirstEmptyTest() throws EmptyCollectionException{
		emptyList.removeFirst();
	}

	@Test
	public void removeFirstTest() throws EmptyCollectionException{
		Assert.assertEquals("1", list.removeFirst());
		Assert.assertEquals("(2 3 4 )", list.toString());
	}

	@Test(expected=EmptyCollectionException.class)
	public void removeLastEmptyTest() throws EmptyCollectionException{
		emptyList.removelast();
	}

	@Test
	public void removeLastTest() throws EmptyCollectionException{
		Assert.assertEquals("4", list.removelast());
		Assert.assertEquals("(1 2 3 )", list.toString());
	}

	@Test(expected=EmptyCollectionException.class)
	public void removePenultEmptyTest() throws EmptyCollectionException{
		emptyList.removePenult();
	}

	@Test
	public void removePenultTest() throws EmptyCollectionException{
		Assert.assertEquals("3", list.removePenult());
		Assert.assertEquals("(1 2 4 )", list.toString());
	}

	@Test(expected=NullPointerException.class)
	public void removeElemNullTest() throws EmptyCollectionException{
		list.removeElem(numNull);
	}

	@Test(expected=EmptyCollectionException.class)
	public void removeElemEmptyTest() throws EmptyCollectionException{
		emptyList.removeElem(num);
	}

	@Test(expected=NoSuchElementException.class)
	public void removeElemNoListTest() throws EmptyCollectionException{
		list.removeElem(num);
	}


	@Test
	public void removeElemTest() throws EmptyCollectionException{
		list.addLast("2");
		Assert.assertEquals(1, list.removeElem("1"));
		list.addFirst("1");
		Assert.assertEquals(2, list.removeElem("2"));
		Assert.assertEquals(3, list.removeElem("4"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void getElemPosIllegalTest(){
		list.getElemPos(0);
		list.getElemPos(20);
	}

	@Test
	public void getElemPosTest(){
		Assert.assertEquals("1", list.getElemPos(1));
		Assert.assertEquals("2", list.getElemPos(2));
		Assert.assertEquals("3", list.getElemPos(3));
		Assert.assertEquals("4", list.getElemPos(4));
	}

	@Test
	public void 
}
