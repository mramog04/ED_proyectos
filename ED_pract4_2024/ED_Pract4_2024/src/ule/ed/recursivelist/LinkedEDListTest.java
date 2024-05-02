package ule.ed.recursivelist;

import static org.junit.Assert.*;

import org.junit.*;


public class LinkedEDListTest {
	private LinkedEDList<String> lista;
	private LinkedEDList<String> lista_num;
	
	@Before
	public void test() {
		 lista= new LinkedEDList<String>();
		 lista_num = new LinkedEDList<String>();
		 lista_num.addLast("6");
		 lista_num.addLast("5");
		 lista_num.addLast("4");
		 lista_num.addLast("3");
		 lista_num.addLast("2");
		 lista_num.addLast("1");
	}

	@Test
	public void test_Vacia() {
		assertEquals(0,lista.size());
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
	public void getElemPosTest(){
		Assert.assertEquals("2", lista_num.getElemPos(2));
	}
	
	
}
