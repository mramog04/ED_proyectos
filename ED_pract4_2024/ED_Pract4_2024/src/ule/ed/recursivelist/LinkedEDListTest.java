package ule.ed.recursivelist;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;


public class LinkedEDListTest {
	private LinkedEDList<String> lista;
	private LinkedEDList<String> lista_num;
	
	@Before
	public void test() {
		 lista= new LinkedEDList<String>();
		 lista_num = new LinkedEDList<String>();
		 lista_num.addLast("1");
		 lista_num.addLast("2");
		 lista_num.addLast("3");
		 lista_num.addLast("4");
		 lista_num.addLast("5");
		 lista_num.addLast("6");
	}

	@Test
	public void test_Vacia() {
		assertEquals(0,lista.size());
		assertTrue(lista.isEmpty());
		//test del size
		assertEquals(6, lista_num.size());
	}
	
	@Test
	public void test_AddLast() {
		lista.addLast("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
	}
	

	@Test(expected=NullPointerException.class)
	public void test_addLast_ElementoNulo() {
			lista.addLast(null);
			
	}
	
	@Test
	public void linkedtestAddPos_Varios() {
		lista.addPos("2",1);
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addPos("3",1);
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addPos("7",2);
		Assert.assertEquals("(3 7 2 )", lista.toString());
		lista.addPos("10",3);
		Assert.assertEquals("(3 7 10 2 )", lista.toString());
		
	}
	
	@Test
	public void addPosTest(){
		lista.addPos("1", 1);
		assertEquals("(1 )", lista.toString());
		lista.addPos("2", 2);
		assertEquals("(1 2 )", lista.toString());
		lista.addPos("3", 20);
		assertEquals("(1 2 3 )", lista.toString());
		lista.addPos("A", 3);
		assertEquals("(1 2 A 3 )", lista.toString());
	}

	@Test(expected = NullPointerException.class)
	public void addPosExcpTest(){
		lista.addPos(null, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addPosExcp_2Test(){
		lista.addPos("1", 0);
		lista.addPos("1", -1);
	}

	@Test
	public void getElemPos(){
		assertEquals("1", lista_num.getElemPos(1));
		assertEquals("2", lista_num.getElemPos(2));
		assertEquals("3", lista_num.getElemPos(3));
		assertEquals("4", lista_num.getElemPos(4));

	}

	@Test(expected = IllegalArgumentException.class)
	public void getElemPosExcp(){
		assertEquals("1", lista.getElemPos(1));
		assertEquals("1", lista_num.getElemPos(200));
	}

	@Test
	public void getPosFirstTest(){
		assertEquals(1,lista_num.getPosFirst("1"));
		assertEquals(2,lista_num.getPosFirst("2"));
		assertEquals(3,lista_num.getPosFirst("3"));
		assertEquals(4,lista_num.getPosFirst("4"));
	}

	@Test(expected = NullPointerException.class)
	public void getPosFirstExcpTest(){
		lista_num.getPosFirst(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void getPosFirstExcp_2Test(){
		lista_num.getPosFirst("A");
	}

	@Test
	public void getPosLastTest(){
		lista_num.addLast("1");
		assertEquals(7, lista_num.getPosLast("1"));
		assertEquals(2, lista_num.getPosLast("2"));
	}

	@Test(expected = NullPointerException.class)
	public void getPosLastExcpTest(){
		lista_num.getPosLast(null);
		lista.getPosLast(null);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void getPosLastExcp2Test(){
		lista_num.getPosLast("A");
	}

	@Test
	public void removeLastTest() throws EmptyCollectionException{
		assertEquals("(1 2 3 4 5 6 )", lista_num.toString());
		lista_num.removelast();
		assertEquals("(1 2 3 4 5 )", lista_num.toString());
		lista_num.removelast();
		assertEquals("(1 2 3 4 )", lista_num.toString());
		lista_num.removelast();
		assertEquals("(1 2 3 )", lista_num.toString());
		lista_num.removelast();
		assertEquals("(1 2 )", lista_num.toString());
		lista_num.removelast();
		assertEquals("(1 )", lista_num.toString());
		lista_num.removelast();
		assertEquals("()", lista_num.toString());
	}

	@Test(expected = EmptyCollectionException.class)
	public void removeLastExcpTest() throws EmptyCollectionException{
		lista.removelast();
	}

	@Test
	public void removeOddElementsTest(){
		assertEquals(3, lista_num.removeOddElements());
		assertEquals("(2 4 6 )", lista_num.toString());
		assertEquals(0, lista.removeOddElements());
	}

	@Test
	public void removeEvenElements(){
		assertEquals(3, lista_num.removeEvenElements());
		assertEquals("(1 3 5 )", lista_num.toString());
		assertEquals(0, lista.removeOddElements());
	}

	@Test
	public void removeConsecDuplicateTest(){
		lista_num.addPos("1", 2);
		lista_num.addPos("1", 2);
		assertEquals(2, lista_num.removeConsecDuplicates());
		assertEquals("(1 2 3 4 5 6 )", lista_num.toString());
		assertEquals(0, lista.removeConsecDuplicates());
	}

	@Test
	public void removeFirstElemTest(){
		lista_num.addLast("1");
		assertEquals(1,lista_num.removeFirstElem("1"));
		assertEquals("(2 3 4 5 6 1 )", lista_num.toString());
		lista_num.addLast("3");
		assertEquals(2, lista_num.removeFirstElem("3"));
		assertEquals("(2 4 5 6 1 3 )", lista_num.toString());

		assertEquals(3, lista_num.removeFirstElem("5"));
		assertEquals("(2 4 6 1 3 )", lista_num.toString());

		assertEquals(4, lista_num.removeFirstElem("1"));
		assertEquals("(2 4 6 3 )", lista_num.toString());

		assertEquals(4, lista_num.removeFirstElem("3"));
		assertEquals("(2 4 6 )", lista_num.toString());

		assertEquals(2, lista_num.removeFirstElem("4"));
		assertEquals("(2 6 )", lista_num.toString());

		assertEquals(2, lista_num.removeFirstElem("6"));
		assertEquals("(2 )", lista_num.toString());

		assertEquals(1, lista_num.removeFirstElem("2"));
		assertEquals("()", lista_num.toString());
	}

	@Test(expected=NoSuchElementException.class)
	public void removeFirstElemExcpTest(){
		lista_num.removeFirstElem("A");
		lista.removeFirstElem("1");
	}

	/*@Test
	public void removeLastElemTest(){
		lista_num.addLast("1");
		assertEquals(7, lista_num.removeLastElem("1"));
		assertEquals("(1 2 3 4 5 6 )", lista_num.toString());
	}*/










	@Test 
	public void getElemPosTest(){
		Assert.assertEquals("2", lista_num.getElemPos(2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getElemPosExcpTest(){
		lista_num.getElemPos(200);
		lista_num.getElemPos(-20);
	}
}
