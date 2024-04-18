package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

public class DoubleLinkedImplTest {
	DoubleLinkedListImpl<String> lv;
	DoubleLinkedListImpl<String> listaConElems;

	@Before
	public void antesDe() {
		lv=new DoubleLinkedListImpl<String>();
		listaConElems=new DoubleLinkedListImpl<String>("A","B","C","A","B","D");
		/* listaConElems.addFirst("D");
		listaConElems.addFirst("B");
		listaConElems.addFirst("A");
		listaConElems.addFirst("C");
		listaConElems.addFirst("B");
		listaConElems.addFirst("A");
        */
	}



	@Test
	public void isEmptyTest() {
		Assert.assertTrue(lv.isEmpty());
		Assert.assertEquals(0,lv.size());
		Assert.assertFalse(listaConElems.isEmpty());
		Assert.assertEquals(6,listaConElems.size());

	}

	@Test
	public void clearTest() {
		lv.clear();
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);

		listaConElems.clear();
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertEquals(0,listaConElems.size());
	
	}

	@Test
	public void countElemTest() {
		Assert.assertEquals(0,lv.countElem("A"));
		Assert.assertEquals(2,listaConElems.countElem("A"));
		Assert.assertEquals(2,listaConElems.countElem("B"));
		Assert.assertEquals(1,listaConElems.countElem("C"));
		Assert.assertEquals(1,listaConElems.countElem("D"));
		Assert.assertEquals(0,listaConElems.countElem("Z"));

	}

	

	
	@Test
	public void addFirstTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		Assert.assertTrue(lista.isEmpty());
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
	}
	
	@Test(expected=NullPointerException.class)
	public void addElementoNuloFirstTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst(null);
	}
	
	@Test
	public void addLastTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		Assert.assertTrue(lista.isEmpty());
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
	}
	
	
	@Test(expected=EmptyCollectionException.class)
	public void testRemoveLast() throws EmptyCollectionException {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		Assert.assertEquals("7", lista.removeLast());
		Assert.assertEquals("3", lista.removeLast());
		Assert.assertEquals("2", lista.removeLast());
		Assert.assertEquals("2", lista.removeLast());
	}
	
	@Test
	public void testaddPos() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		lista.addPos("2", 3);
		Assert.assertEquals("(2 3 2 7 )", lista.toString());
		lista.addPos("1", 1);
		Assert.assertEquals("(1 2 3 2 7 )", lista.toString());
		lista.addPos("5", 6);
		Assert.assertEquals("(1 2 3 2 7 5 )", lista.toString());
		lista.addPos("6", 10);
		Assert.assertEquals("(1 2 3 2 7 5 6 )", lista.toString());
	}
	

	@Test
	public void testaddAfter() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		lista.addAfter("1", "2");
		Assert.assertEquals("(2 1 3 7 )", lista.toString());
		lista.addAfter("2", "2");
		Assert.assertEquals("(2 2 1 3 7 )", lista.toString());
		lista.addAfter("2", "7");
		Assert.assertEquals("(2 2 1 3 7 2 )", lista.toString());
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testaddAfterTargetNulo() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		lista.addAfter("3", null);
	}
	
	@Test public void testaddAfterInexistente() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");  
		Assert.assertEquals("(2 3 7 )", lista.toString());
		lista.addAfter("3", "4");
		Assert.assertEquals("(2 3 7 3 )", lista.toString());
	 }
	
	@Test
	public void testGetElemPos() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("1","2", "2", "3","2","7");
		Assert.assertEquals("(1 2 2 3 2 7 )", lista.toString());
		Assert.assertEquals("2", lista.getElemPos(2));
		Assert.assertEquals("7", lista.getElemPos(6));
		Assert.assertEquals("1", lista.getElemPos(1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetElemPosExceso() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("2", lista.getElemPos(20));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetElemPosDefecto() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("2", lista.getElemPos(0));
	}
	
	@Test
	public void testGetPosFirst() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "2","2","3","1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		Assert.assertEquals(2, lista.getPosFirst("1"));
		Assert.assertEquals(1, lista.getPosFirst("2"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetPosFirstNulo() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.getPosFirst(null));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testGetPosFirstInexistente() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.getPosFirst("R"));
	}
	
	@Test
	public void testGetPosLast() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "2","2","3","1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		Assert.assertEquals(6, lista.getPosLast("1"));
		Assert.assertEquals(4, lista.getPosLast("2"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetPosLastNulo() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.getPosLast(null));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testGetPosLastInexistente() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.getPosLast("R"));
	}
	
	@Test
	public void testRemovePos() throws EmptyCollectionException {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		lista.addFirst("4");
		Assert.assertEquals("4", lista.removePos(1));
		Assert.assertEquals("(2 3 1 )", lista.toString());
		Assert.assertEquals("3", lista.removePos(2));
		Assert.assertEquals("(2 1 )", lista.toString());
		Assert.assertEquals("1", lista.removePos(2));
		Assert.assertEquals("(2 )", lista.toString());
		Assert.assertEquals("2", lista.removePos(1));
		Assert.assertEquals("()", lista.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemovePosDefecto() throws EmptyCollectionException {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.removePos(5));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemovePosExceso() throws EmptyCollectionException {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.removePos(-5));
	}
	
	@Test
	public void testRemoveN() throws EmptyCollectionException {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "2","2","3","1");
			Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		Assert.assertEquals(3, lista.removeN("2",5));
		Assert.assertEquals("(1 3 1 )", lista.toString());
		Assert.assertEquals(2, lista.removeN("1",3));
		Assert.assertEquals("(3 )", lista.toString());
		Assert.assertEquals(1, lista.removeN("3",1));
		Assert.assertEquals("()", lista.toString());
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testCountElem() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		lista.countElem(null);
	}
	
	@Test
	public void testSize() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		Assert.assertEquals(0, lista.size());
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(3, lista.size());
	}

	

	@Test
	public void maxRepeatedTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "2","2","3","1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		Assert.assertEquals("(1 3 2 2 1 2 )", lista.toStringReverse());
			
		Assert.assertEquals(3, lista.maxRepeated());
		lista.clear();
		Assert.assertEquals(0, lista.maxRepeated());
	}


	

	@Test
	public void toStringFromUntilTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "2","2","3","1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		Assert.assertEquals("(2 1 2 )", lista.toStringFromUntil(1, 3));
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toStringFromUntil(1, 6));
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toStringFromUntil(1, 10));
		Assert.assertEquals("(2 3 1 )", lista.toStringFromUntil(4, 7));
		Assert.assertEquals("()", lista.toStringFromUntil(8, 10));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void toStringFromUntilFromNegativoTest() {
		listaConElems.toStringFromUntil(-3, 4);
	}
	
	
	@Test
	public void toStringTest() {
		Assert.assertEquals("()",  lv.toString());
	}
	
	
	@Test(expected=NoSuchElementException.class)
	public void IteratorNextEnVaciaTest() {

		Iterator<String> iterador = lv.iterator();
		Assert.assertFalse(iterador.hasNext());
		iterador.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void progressIteratorTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "2","2","3","1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		
		lista.addLast("4");
		
		lista.addLast("6");
		Assert.assertEquals("(2 1 2 2 3 1 4 6 )", lista.toString());
		Iterator<String> iterator = lista.progressReverseIterator();
		StringBuffer nuevo = new StringBuffer("(");
		while(iterator.hasNext()) {
			nuevo.append(iterator.next()+ " ");
		}
		nuevo.append(")");
		Assert.assertEquals("(6 4 3 1 )", nuevo.toString());
		Assert.assertFalse(iterator.hasNext());
		iterator.next();
	}
	
	@Test
	public void ConstructorColeccionTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("A","B","C","D");
		Assert.assertEquals("(A B C D )", lista.toString());
		}	


		@Test
		public void test_countElem() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
			lista.addFirst("2");
			lista.addLast("3");
			lista.addLast("1");
			Assert.assertEquals(1, lista.countElem("2"));
			Assert.assertEquals(1, lista.countElem("3"));
			Assert.assertEquals(1, lista.countElem("1"));
			Assert.assertEquals(0, lista.countElem("4")); // Verificar cuando el elemento no está presente
		}
	
		@Test
		public void test_size() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
			Assert.assertEquals(0, lista.size());
			lista.addFirst("2");
			lista.addLast("3");
			lista.addLast("1");
			Assert.assertEquals(3, lista.size());
		}
	
		@Test
		public void test_maxRepeated() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "2","2","3","1");
			Assert.assertEquals(3, lista.maxRepeated());
			lista.clear();
			Assert.assertEquals(0, lista.maxRepeated()); // Verificar cuando la lista está vacía
		}
	
		@Test
		public void test_toStringFromUntil() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "2","2","3","1");
			Assert.assertEquals("(2 1 2 )", lista.toStringFromUntil(1, 3));
			Assert.assertEquals("(2 1 2 2 3 1 )", lista.toStringFromUntil(1, 6));
			Assert.assertEquals("()", lista.toStringFromUntil(8, 10)); // Verificar cuando los índices están fuera de rango
		}
	
		@Test(expected=IllegalArgumentException.class)
		public void test_toStringFromUntilFromNegativo() {
			listaConElems.toStringFromUntil(-3, 4); // Verificar excepción cuando el índice inicial es negativo
		}
	
		@Test
		public void test_toString() {
			Assert.assertEquals("()", lv.toString()); // Verificar la representación de una lista vacía
		}
	
		@Test(expected=NoSuchElementException.class)
		public void test_IteratorNextEnVacia() {
			Iterator<String> iterador = lv.iterator();
			Assert.assertFalse(iterador.hasNext());
			iterador.next(); // Verificar excepción cuando se llama a next en una lista vacía
		}
	
		@Test(expected=NoSuchElementException.class)
		public void test_progressIterator() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "2","2","3","1");
			lista.addLast("4");
			lista.addLast("6");
			Iterator<String> iterator = lista.progressReverseIterator();
			StringBuffer nuevo = new StringBuffer("(");
			while(iterator.hasNext()) {
				nuevo.append(iterator.next()+ " ");
			}
			nuevo.append(")");
			Assert.assertEquals("(6 4 3 1 )", nuevo.toString());
			Assert.assertFalse(iterator.hasNext());
			iterator.next(); // Verificar excepción cuando se llama a next después de que no haya más elementos
		}

		@Test
		public void test_addAfter_LastElement() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "2", "2", "3", "1");
			lista.addLast("4"); // Añadimos un elemento al final de la lista
			lista.addAfter("5", "4"); // Añadimos un elemento después del último elemento
			Assert.assertEquals("(2 1 2 2 3 1 4 5 )", lista.toString());
		}

		@Test
		public void testRemovePenul_enListaCon3Elementos() throws EmptyCollectionException {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("2", "1", "3");
			lista.removePenul();
			Assert.assertEquals("(2 3 )", lista.toString());
		}

		@Test
		public void testToStringReverse_enListaVacia() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
			Assert.assertEquals("()", lista.toStringReverse());
		}
		
		@Test
		public void testToStringReverse_enListaConUnElemento() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("A");
			Assert.assertEquals("(A )", lista.toStringReverse());
		}
		
		@Test
		public void testToStringReverse_enListaConVariosElementos() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("A", "B", "C");
			Assert.assertEquals("(C B A )", lista.toStringReverse());
		}
		
		
		
		@Test
		public void testToStringReverse_enListaConElementosRepetidos() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("A", "B", "A", "C", "C");
			Assert.assertEquals("(C C A B A )", lista.toStringReverse());
		}

		@Test
		public void test_toSameElems_MismosElemsDistintoOrden() {
			DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>("A", "B", "C");
			DoubleLinkedListImpl<String> lista2 = new DoubleLinkedListImpl<String>("B", "A", "C");
			
			Assert.assertEquals(true, lista1.sameElems(lista2));
		}

		@Test
		public void testAddAfterAll() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("B", "B", "C", "B", "D", "B");
		
			// Agregar "X" después de cada ocurrencia de "B"
			lista.addAfterAll("X", "B");
		
			// La lista debería ser: A -> B -> X -> C -> B -> X -> D -> B -> X
			Assert.assertEquals("(B X B X C B X D B X )", lista.toString());
		}

		@Test
		public void testRemovePenul_enListaCon2Elementos() throws EmptyCollectionException {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>("A", "B");
			
			// Remover el penúltimo elemento de la lista
			Assert.assertEquals("A", lista.removePenul());
			
			// Verificar que el penúltimo elemento ("A") ha sido removido correctamente
			Assert.assertEquals("(B )", lista.toString());
			Assert.assertEquals(1, lista.size());
		}

		@Test
		public void testaddAfterAllEnVacia() {
			DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<Integer>();
		
			// Agregar "2" después de cada ocurrencia de "1" (que no existe en la lista vacía)
			lista.addAfterAll(1, 2);
		
			// La lista debería seguir estando vacía
			Assert.assertEquals("(1 )", lista.toStringReverse());
			Assert.assertEquals(1, lista.size());
		}
		
		@Test
		public void testToStringReverse() {
			// Lista vacía
			DoubleLinkedListImpl<Integer> listaVacia = new DoubleLinkedListImpl<>();
			Assert.assertEquals("()", listaVacia.toStringReverse());
	
			// Lista con un elemento
			DoubleLinkedListImpl<Integer> listaUnElemento = new DoubleLinkedListImpl<>(1);
			Assert.assertEquals("(1 )", listaUnElemento.toStringReverse());
	
			// Lista con dos elementos
			DoubleLinkedListImpl<Integer> listaDosElementos = new DoubleLinkedListImpl<>(1, 2);
			Assert.assertEquals("(2 1 )", listaDosElementos.toStringReverse());
	
			// Lista con tres elementos
			DoubleLinkedListImpl<Integer> listaTresElementos = new DoubleLinkedListImpl<>(1, 2, 3);
			Assert.assertEquals("(3 2 1 )", listaTresElementos.toStringReverse());
		}
	
		@Test
		public void testToStringFromUntil() {
			// Lista vacía
			DoubleLinkedListImpl<Integer> listaVacia = new DoubleLinkedListImpl<>();
			Assert.assertEquals("()", listaVacia.toStringFromUntil(1, 3));
	
			// Lista con un elemento
			DoubleLinkedListImpl<Integer> listaUnElemento = new DoubleLinkedListImpl<>(1);
			Assert.assertEquals("(1 )", listaUnElemento.toStringFromUntil(1, 3));
	
			// Lista con dos elementos
			DoubleLinkedListImpl<Integer> listaDosElementos = new DoubleLinkedListImpl<>(1, 2);
			Assert.assertEquals("(1 2 )", listaDosElementos.toStringFromUntil(1, 3));
	
			// Lista con tres elementos
			DoubleLinkedListImpl<Integer> listaTresElementos = new DoubleLinkedListImpl<>(1, 2, 3);
			Assert.assertEquals("(1 2 3 )", listaTresElementos.toStringFromUntil(1, 3));
		}
	
		@Test
		public void testToStringFromUntilReverse() {
			// Lista vacía
			DoubleLinkedListImpl<Integer> listaVacia = new DoubleLinkedListImpl<>();
			Assert.assertEquals("()", listaVacia.toStringFromUntilReverse(3, 1));
	
			// Lista con un elemento
			DoubleLinkedListImpl<Integer> listaUnElemento = new DoubleLinkedListImpl<>(1);
			Assert.assertEquals("(1 )", listaUnElemento.toStringFromUntilReverse(3, 1));
	
			// Lista con dos elementos
			DoubleLinkedListImpl<Integer> listaDosElementos = new DoubleLinkedListImpl<>(1, 2);
			Assert.assertEquals("(2 1 )", listaDosElementos.toStringFromUntilReverse(3, 1));
	
			// Lista con tres elementos
			DoubleLinkedListImpl<Integer> listaTresElementos = new DoubleLinkedListImpl<>(1, 2, 3);
			Assert.assertEquals("(3 2 1 )", listaTresElementos.toStringFromUntilReverse(3, 1));
		}

		@Test(expected = NoSuchElementException.class)
		public void IteratorReverseNextEnVaciaTest() {
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<>();
	
			// Obtener un iterador reverso en una lista vacía
			Iterator<String> iterador = lista.reverseIterator();
	
			// Intentar llamar a next() en una lista vacía debe lanzar NoSuchElementException
			Assert.assertFalse(iterador.hasNext());
			iterador.next();
		}

		@Test
		public void testAddAfterAllInexistente(){
			listaConElems.addAfterAll("3", "Z");
			Assert.assertEquals("(A B C A B D 3 )", listaConElems.toString());
		}

		@Test
		public void iteratorReverseProgressTest(){
			Iterator<String> iterator = listaConElems.progressReverseIterator();
			Assert.assertTrue(iterator.hasNext());
			Assert.assertEquals("D", iterator.next());
			Assert.assertTrue(iterator.hasNext());
			Assert.assertEquals("B", iterator.next());
			Assert.assertTrue(iterator.hasNext());
			Assert.assertEquals("C", iterator.next());
			Assert.assertFalse(iterator.hasNext());
		}

		@Test
		public void removeTest() throws EmptyCollectionException{
			DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>("A", "B", "C","D","E");
			Assert.assertFalse(lista1.isEmpty());
			lista1.removePenul();
			Assert.assertEquals("(A B C E )", lista1.toString());
			lista1.removeLast();
			Assert.assertEquals("(A B C )", lista1.toString());
			lista1.removePos(1);
			Assert.assertEquals("(B C )", lista1.toString());
			lista1.removeN("B", 2);
			Assert.assertEquals("(C )", lista1.toString());
		}

		@Test(expected=NoSuchElementException.class)
		public void removeEx_1() throws EmptyCollectionException{
			DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>("A");
			lista1.removePenul();
		}

		@Test(expected = NoSuchElementException.class)
		public void removeEx_2() throws EmptyCollectionException{
			DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>("A", "B", "C","D","E");
			lista1.removeN("Z", 3);
		}

		@Test(expected = EmptyCollectionException.class)
		public void removeEx_3() throws EmptyCollectionException{
			DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>();
			lista1.removePos(3);
		}

		@Test
		public void removeEx_4() throws EmptyCollectionException{
			DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>("A","B");
			lista1.removePenul();
			Assert.assertEquals("(B )", lista1.toStringFromUntilReverse(1, 1));
		}

		@Test
		public void addAfterTest_tergetNoExiste(){
			DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>("2","3","7");
			lista1.addAfter("1", "A");
			Assert.assertEquals("(2 3 7 1 )", lista1.toStringFromUntil(1, 4));
		}

		@Test
		public void IteratorPrgressTest(){
			DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>("A", "B", "C","D","E");
			Iterator<String> iterator = lista1.progressIterator();
			Assert.assertTrue(iterator.hasNext());
			Assert.assertEquals("A", iterator.next());
			Assert.assertTrue(iterator.hasNext());
			Assert.assertEquals("B", iterator.next());
			Assert.assertTrue(iterator.hasNext());
			Assert.assertEquals("D", iterator.next());
			Assert.assertFalse(iterator.hasNext());
		}

		@Test
		public void testAddAfterAll_TargetNotPresent() {
			DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>(1, 2, 3);
		
			// Agregar el elemento 4 después de todas las apariciones de 5 (que no está presente)
			lista.addAfterAll(4, 5);
		
			// La lista debería tener el elemento 4 agregado al final
			Assert.assertEquals("(1 2 3 4 )", lista.toString());
			Assert.assertEquals(4, lista.size());
		}

		@Test
		public void testAddAfterAll_TargetPresent() {
			DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>(1, 2, 3, 2, 4);
		
			// Agregar el elemento 5 después de todas las apariciones del 2
			lista.addAfterAll(5, 2);
		
			// La lista debería tener el elemento 5 agregado después de todas las apariciones del 2
			Assert.assertEquals("(1 2 5 3 2 5 4 )", lista.toString());
			Assert.assertEquals(7, lista.size());
		}

		@Test
		public void testToStringReverse_2() {
			DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>(1, 2, 3, 4, 5);
		
			// Verificar que toStringReverse imprime correctamente la lista en orden inverso
			Assert.assertEquals("(5 4 3 2 1 )", lista.toStringReverse());
		}
		
		@Test
		public void testAddAfterAll_AddElementAtEnd_CheckReverseOrder() {
			DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>(1, 2, 3, 2, 4);
		
			// Agregar el elemento 5 después de todas las apariciones del 7 (no presente)
			lista.addAfterAll(5, 7);
		
			// La lista debería tener el elemento 5 agregado al final
			Assert.assertEquals("(1 2 3 2 4 5 )", lista.toString());
			Assert.assertEquals(6, lista.size());
		
			// Verificar que toStringReverse imprime correctamente la lista en orden inverso
			Assert.assertEquals("(5 4 2 3 2 1 )", lista.toStringReverse());
		}
		
		@Test
		public void testAddAfterAll_AddElementAtEnd_CheckReverseOrder_2() {
			DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>(1, 2, 3, 4, 2);
		
			// Agregar el elemento 5 después de todas las apariciones del 2 (último elemento)
			lista.addAfterAll(5, 2);
		
			// La lista debería tener el elemento 5 agregado al final
			Assert.assertEquals("(1 2 5 3 4 2 5 )", lista.toString());
			Assert.assertEquals(7, lista.size());
		
			// Verificar que toStringReverse imprime correctamente la lista en orden inverso
			Assert.assertEquals("(5 2 4 3 5 2 1 )", lista.toStringReverse());
		}
		
		@Test
		public void testEmptyList() {
			DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>();
			Iterator<Integer> iterator = lista.progressReverseIterator();
	
			Assert.assertFalse(iterator.hasNext());
		}

		@Test
		public void testSingleElementList() {
			DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>(1);
			Iterator<Integer> iterator = lista.progressReverseIterator();
	
			Assert.assertTrue(iterator.hasNext());
			Assert.assertEquals(Integer.valueOf(1), iterator.next());
			Assert.assertFalse(iterator.hasNext());
		}
		

		
		@Test(expected = NoSuchElementException.class)
		public void testNextPastEndOfList() {
			DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>(1, 2, 3, 4, 5);
			Iterator<Integer> iterator = lista.progressReverseIterator();
	
			while (iterator.hasNext()) {
				iterator.next();
			}
	
			// Debería lanzar una excepción NoSuchElementException
			iterator.next();
		}
	
		@Test(expected = NoSuchElementException.class)
		public void testNextOnEmptyList() {
			DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>();
			Iterator<Integer> iterator = lista.progressReverseIterator();
	
			// Debería lanzar una excepción NoSuchElementException
			iterator.next();
		}

		@Test
		public void testRemovePenul_TwoAndThreeElements() throws EmptyCollectionException {
			// Lista con dos elementos
			DoubleLinkedListImpl<Integer> listaDos = new DoubleLinkedListImpl<>(1, 2);
			listaDos.removePenul();
			// Verificar que el tamaño de la lista sea 1 después de eliminar el penúltimo elemento
			Assert.assertEquals(1, listaDos.size());
			// Verificar que la representación de la lista sea correcta
			Assert.assertEquals("(2 )", listaDos.toString());
			Assert.assertEquals("(2 )", listaDos.toStringReverse());
			
			// Lista con tres elementos
			DoubleLinkedListImpl<Integer> listaTres = new DoubleLinkedListImpl<>(1, 2, 3);
			listaTres.removePenul();
			// Verificar que el tamaño de la lista sea 2 después de eliminar el penúltimo elemento
			Assert.assertEquals(2, listaTres.size());
			// Verificar que la representación de la lista sea correcta
			Assert.assertEquals("(1 3 )", listaTres.toString());
			Assert.assertEquals("(3 1 )", listaTres.toStringReverse());
		}

						
		@Test
		public void testRemovePenulWithThreeElements() throws EmptyCollectionException {
			// Lista con tres elementos
			DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<>("A", "B", "C");

			// Verificar que se elimina correctamente el penúltimo elemento "B"
			Assert.assertEquals("B", lista.removePenul());

			// Verificar que el tamaño de la lista sea 2 después de eliminar el penúltimo elemento
			Assert.assertEquals(2, lista.size());

			// Verificar que la representación de la lista sea correcta
			Assert.assertEquals("(A C )", lista.toString());
			Assert.assertEquals("(C A )", lista.toStringReverse());
		}


}