package ule.edi.tree;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;





public class BinarySearchTreeTests {

   
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |	∅
	* |  |  |	∅
	* |  |	 ∅
	* |  20
	* |  |  15
	* |  |  |	∅
	* |  |  | 	∅
	* |  |  30
	* |  |  |  	∅
	* |  |  |  	∅
    */	
	private BinarySearchTreeImpl<Integer> ejemplo = null;
	
	
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  	∅
	* |  |  |  	∅
	* |  | 	 ∅
	* |  20
	* |  |  15
	* |  |  |  12
	* |  |  |  |  	∅
	* |  |  |  |  	∅
	* |  | 	 ∅
  */
	private BinarySearchTreeImpl<Integer> other=null;
	private BinarySearchTreeImpl<Integer> tree;
	
	@Before
	public void setupBSTs() {
		tree = new BinarySearchTreeImpl<>();
			
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 20, 5, 2, 15, 30);
		Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
		
		
		other =new BinarySearchTreeImpl<Integer>();
		other.insert(10, 20, 5, 2, 15, 12);
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		
	    	}
	
	@Test
	public void testRemoveCountMayor1() {
		ejemplo.insert(20);
		ejemplo.insert(20);
		Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(3), {15, ∅, ∅}, {30, ∅, ∅}}}");
		ejemplo.remove(20);
	    Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(2), {15, ∅, ∅}, {30, ∅, ∅}}}");
	}
	
	@Test
	public void testRemoveCountMayor1HastaVaciar() {
		ejemplo.insert(20);
		ejemplo.insert(20);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20(3), {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
		ejemplo.remove(20);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20(2), {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
		ejemplo.remove(20);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
		ejemplo.remove(20);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {30, {15, ∅, ∅}, ∅}}",ejemplo.toString());
	}
	
	@Test
	public void testRemoveHoja() {
		ejemplo.remove(30);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, ∅}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove1Hijo() {
		ejemplo.remove(5);
		Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove2Hijos() {
		ejemplo.remove(10);
		Assert.assertEquals("{15, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}",ejemplo.toString());
	}

	
			
				
		@Test(expected = IllegalArgumentException.class)
		public void testInsertException() {
			Integer i = null;
			other.insert(i);	
		}
		
	
		@Test(expected = IllegalArgumentException.class)
		public void testContainsNull() {
			other.contains(null);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testRemoveNullElement() {
			Integer i = null;
			other.remove(i);
		}
		
		@Test(expected = NoSuchElementException.class)
		public void testRemoveNoSuchElement() {
			other.remove(11);
		}

		@Test
		public void sizeTest(){
			assertEquals(6, ejemplo.size());
		}

		@Test 
		public void instancesCountTest(){
			assertEquals(6, ejemplo.instancesCount());
			ejemplo.insert(20);
			assertEquals(7, ejemplo.instancesCount());

		}

		@Test
		public void removeElem2Test(){
			ejemplo.insert(20);
			ejemplo.insert(20);
			ejemplo.insert(20);
			assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(4), {15, ∅, ∅}, {30, ∅, ∅}}}");
			assertEquals(3, ejemplo.remove(20, 3));
			assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
		}

		@Test
		public void testRemoveWithSingleChild() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			tree.insert(20);
		
			Assert.assertEquals("{10, {5, ∅, ∅}, {15, ∅, {20, ∅, ∅}}}", tree.toString());
			tree.remove(15);
			Assert.assertEquals("{10, {5, ∅, ∅}, {20, ∅, ∅}}", tree.toString());
		}
		
		@Test
		public void testRemoveWithTwoChildren() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			tree.insert(12);
			tree.insert(20);
		
			Assert.assertEquals("{10, {5, ∅, ∅}, {15, {12, ∅, ∅}, {20, ∅, ∅}}}", tree.toString());
			tree.remove(15);
			Assert.assertEquals("{10, {5, ∅, ∅}, {20, {12, ∅, ∅}, ∅}}", tree.toString());
		}

		@Test//ReHacer estos test si hay tiempo en elgun momento
		public void testIteratorWidth() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			tree.insert(70);

			List<Integer> expected = Arrays.asList(50, 30, 80, 10, 40, 60, 70);
			Iterator<Integer> it = tree.iteratorWidth();

			List<Integer> actual = new ArrayList<>();
			while (it.hasNext()) {
				actual.add(it.next());
			}

			Assert.assertEquals(expected, actual);
		}

		@Test
		public void testIteratorWidthInstances() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(30);
			tree.insert(80);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);

			List<Integer> expected = Arrays.asList(50, 30, 30, 80, 80, 10, 40, 60);
			Iterator<Integer> it = tree.iteratorWidthInstances();

			List<Integer> actual = new ArrayList<>();
			while (it.hasNext()) {
				actual.add(it.next());
			}

			Assert.assertEquals(expected, actual);
		}

		@Test
		public void testToStringSimetric() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(2);
			tree.insert(20);
			tree.insert(30);

			// Raíz del árbol, no tiene nodo simétrico
			assertEquals("", tree.toStringSimetric());

			// Nodo 10, su nodo simétrico es el nodo 20
			assertEquals("{20, ∅, {30, ∅, ∅}}", tree.getSubtreeWithPath("0").toStringSimetric());

			// Nodo 5, su nodo simétrico es el nodo 30
			assertEquals("{30, ∅, ∅}", tree.getSubtreeWithPath("00").toStringSimetric());

			// Nodo 2, no tiene nodo simétrico
		}
		
		@Test
		public void testToStringSimetric_2() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(2);
			tree.insert(20);
			tree.insert(30);
		
			// Raíz del árbol, no tiene nodo simétrico
			assertEquals("", tree.toStringSimetric());
		
			// Nodo 10, su nodo simétrico es el nodo 20
			assertEquals("{20, ∅, {30, ∅, ∅}}", tree.getSubtreeWithPath("0").toStringSimetric());
		
			// Nodo 5, su nodo simétrico es el nodo 30
			assertEquals("{30, ∅, ∅}", tree.getSubtreeWithPath("00").toStringSimetric());
		
		}

		@Test
		public void testContainsElementPresent() {
			Assert.assertTrue(ejemplo.contains(10));
			Assert.assertTrue(ejemplo.contains(5));
			Assert.assertTrue(ejemplo.contains(20));
			Assert.assertTrue(ejemplo.contains(2));
			Assert.assertTrue(ejemplo.contains(15));
			Assert.assertTrue(ejemplo.contains(30));
		}
	
		@Test
		public void testContainsElementNotPresent() {
			Assert.assertFalse(ejemplo.contains(100));
			Assert.assertFalse(ejemplo.contains(25));
			Assert.assertFalse(ejemplo.contains(0));
		}
	
		@Test(expected = IllegalArgumentException.class)
		public void testContainsNullElement() {
			ejemplo.contains(null);
		}

	
		@Test
		public void testToStringComplexTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(2);
			tree.insert(20);
			tree.insert(15);
			tree.insert(30);
			tree.insert(20);
	
			assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20(2), {15, ∅, ∅}, {30, ∅, ∅}}}", tree.toString());
		}
		

		@Test
		public void testIteratorWidthEmptyTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			Iterator<Integer> it = tree.iteratorWidth();
			Assert.assertFalse(it.hasNext());
		}
	
		@Test
		public void testIteratorWidthSingleElement() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			Iterator<Integer> it = tree.iteratorWidth();
			Assert.assertTrue(it.hasNext());
			Assert.assertEquals(Integer.valueOf(10), it.next());
			Assert.assertFalse(it.hasNext());
		}
	
		@Test
		public void testIteratorWidthSimpleTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			List<Integer> expected = Arrays.asList(10, 5, 15);
			List<Integer> actual = new ArrayList<>();
			Iterator<Integer> it = tree.iteratorWidth();
			while (it.hasNext()) {
				actual.add(it.next());
			}
			Assert.assertEquals(expected, actual);
		}
	
		@Test
		public void testIteratorWidthComplexTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			tree.insert(70);
			List<Integer> expected = Arrays.asList(50, 30, 80, 10, 40, 60, 70);
			List<Integer> actual = new ArrayList<>();
			Iterator<Integer> it = tree.iteratorWidth();
			while (it.hasNext()) {
				actual.add(it.next());
			}
			Assert.assertEquals(expected, actual);
		}
	
		@Test
		public void testIteratorWidthWithMultipleInstances() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(30);
			tree.insert(80);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			List<Integer> expected = Arrays.asList(50, 30, 80, 10, 40, 60);
			List<Integer> actual = new ArrayList<>();
			Iterator<Integer> it = tree.iteratorWidth();
			while (it.hasNext()) {
				actual.add(it.next());
			}
			Assert.assertEquals(expected, actual);
		}

		@Test
		public void testIteratorWidthInstancesEmptyTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			Iterator<Integer> it = tree.iteratorWidthInstances();
			Assert.assertFalse(it.hasNext());
		}
	
		@Test
		public void testIteratorWidthInstancesSingleElement() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			Iterator<Integer> it = tree.iteratorWidthInstances();
			Assert.assertTrue(it.hasNext());
			Assert.assertEquals(Integer.valueOf(10), it.next());
			Assert.assertFalse(it.hasNext());
		}
	
		@Test
		public void testIteratorWidthInstancesSimpleTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			List<Integer> expected = Arrays.asList(10, 5, 15);
			List<Integer> actual = new ArrayList<>();
			Iterator<Integer> it = tree.iteratorWidthInstances();
			while (it.hasNext()) {
				actual.add(it.next());
			}
			Assert.assertEquals(expected, actual);
		}
	
		@Test
		public void testIteratorWidthInstancesComplexTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			tree.insert(70);
			List<Integer> expected = Arrays.asList(50, 30, 80, 10, 40, 60, 70);
			List<Integer> actual = new ArrayList<>();
			Iterator<Integer> it = tree.iteratorWidthInstances();
			while (it.hasNext()) {
				actual.add(it.next());
			}
			Assert.assertEquals(expected, actual);
		}
	
		@Test
		public void testIteratorWidthInstancesWithMultipleInstances() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(30);
			tree.insert(80);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			List<Integer> expected = Arrays.asList(50, 30, 30, 80, 80, 10, 40, 60);
			List<Integer> actual = new ArrayList<>();
			Iterator<Integer> it = tree.iteratorWidthInstances();
			while (it.hasNext()) {
				actual.add(it.next());
			}
			Assert.assertEquals(expected, actual);
		}
	
		@Test
		public void testIteratorWidthInstancesWithManyMultipleInstances() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(30);
			tree.insert(30);
			tree.insert(80);
			tree.insert(80);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			List<Integer> expected = Arrays.asList(50, 30, 30, 30, 80, 80, 80, 10, 40, 60);
			List<Integer> actual = new ArrayList<>();
			Iterator<Integer> it = tree.iteratorWidthInstances();
			while (it.hasNext()) {
				actual.add(it.next());
			}
			Assert.assertEquals(expected, actual);
		}

		@Test
		public void testSizeEmptyTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			assertEquals(0, tree.size());
		}
	
		@Test
		public void testSizeSingleElement() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			assertEquals(1, tree.size());
		}
	
		@Test
		public void testSizeSimpleTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			assertEquals(3, tree.size());
		}
	
		@Test
		public void testSizeComplexTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			tree.insert(70);
			assertEquals(7, tree.size());
		}
	
		@Test
		public void testSizeWithMultipleInstances() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(30);
			tree.insert(80);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			assertEquals(6, tree.size());
		}
	
		@Test
		public void testSizeWithManyMultipleInstances() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(30);
			tree.insert(30);
			tree.insert(80);
			tree.insert(80);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			assertEquals(6, tree.size());
		}
	
		@Test
		public void testSizeEjemploTree() {
			assertEquals(6, ejemplo.size());
		}
	
		@Test
		public void testSizeOtherTree() {
			assertEquals(6, other.size());
		}

		@Test
		public void testInstancesCountEmptyTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			assertEquals(0, tree.instancesCount());
		}
	
		@Test
		public void testInstancesCountSingleElement() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			assertEquals(1, tree.instancesCount());
		}
	
		@Test
		public void testInstancesCountSimpleTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			assertEquals(3, tree.instancesCount());
		}
	
		@Test
		public void testInstancesCountComplexTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			tree.insert(70);
			assertEquals(7, tree.instancesCount());
		}
	
		@Test
		public void testInstancesCountWithMultipleInstances() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(30);
			tree.insert(80);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			assertEquals(8, tree.instancesCount());
		}
	
		@Test
		public void testInstancesCountWithManyMultipleInstances() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(30);
			tree.insert(30);
			tree.insert(80);
			tree.insert(80);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			assertEquals(10, tree.instancesCount());
		}
	
		@Test
		public void testInstancesCountEjemploTree() {
			assertEquals(6, ejemplo.instancesCount());
		}
	
		@Test
		public void testInstancesCountOtherTree() {
			assertEquals(6, other.instancesCount());
		}
	
		@Test
		public void testInstancesCountExampleTree() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(30);
			tree.insert(10);
			tree.insert(40);
			tree.insert(40);
			tree.insert(40);
			tree.insert(40);
			tree.insert(80);
			tree.insert(80);
			tree.insert(60);
			assertEquals(11, tree.instancesCount());
		}

		

		@Test
		public void testRemoveSingleElement() {
			tree.insert(10);
			tree.remove(10);
			assertEquals("∅", tree.toString());
		}

		@Test
		public void testRemoveLeafElement() {
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			tree.remove(5);
			assertEquals("{10, ∅, {15, ∅, ∅}}", tree.toString());
		}

		@Test
		public void testRemoveElementWithOneLeftChild() {
			tree.insert(10);
			tree.insert(5);
			tree.insert(2);
			tree.remove(5);
			assertEquals("{10, {2, ∅, ∅}, ∅}", tree.toString());
		}

		@Test
		public void testRemoveElementWithOneRightChild() {
			tree.insert(10);
			tree.insert(5);
			tree.insert(7);
			tree.remove(5);
			assertEquals("{10, {7, ∅, ∅}, ∅}", tree.toString());
		}

		@Test
		public void testRemoveElementWithTwoChildren() {
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			tree.insert(12);
			tree.insert(20);
			tree.remove(15);
			assertEquals("{10, {5, ∅, ∅}, {20, {12, ∅, ∅}, ∅}}", tree.toString());
		}

		@Test
		public void testRemoveMultipleInstances() {
			tree.insert(10);
			tree.insert(10);
			tree.remove(10);
			assertEquals("{10, ∅, ∅}", tree.toString());
			tree.remove(10);
			assertEquals("∅", tree.toString());
		}

		@Test
		public void testRemoveWithSuccessor() {
			tree.insert(50);
			tree.insert(30);
			tree.insert(70);
			tree.insert(20);
			tree.insert(40);
			tree.insert(60);
			tree.insert(80);

			tree.remove(30);
			assertEquals("{50, {40, {20, ∅, ∅}, ∅}, {70, {60, ∅, ∅}, {80, ∅, ∅}}}", tree.toString());
			tree.remove(70);
			assertEquals("{50, {40, {20, ∅, ∅}, ∅}, {80, {60, ∅, ∅}, ∅}}", tree.toString());

			tree.remove(50);
			assertEquals("{60, {40, {20, ∅, ∅}, ∅}, {80, ∅, ∅}}", tree.toString());
		}

		@Test
		public void testRemoveRootWithSuccessor() {
			tree.insert(50);
			tree.insert(30);
			tree.insert(70);
			tree.insert(20);
			tree.insert(40);
			tree.insert(60);
			tree.insert(80);

			tree.remove(50);
			assertEquals("{60, {30, {20, ∅, ∅}, {40, ∅, ∅}}, {70, ∅, {80, ∅, ∅}}}", tree.toString());
		}

		@Test
		public void testGetSubtreeWithPathEmptyPath() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			tree.insert(45);
	
			assertEquals("{50, {30, {10, ∅, ∅}, {40, ∅, {45, ∅, ∅}}}, {80, {60, ∅, ∅}, ∅}}", tree.toString());
			assertEquals("{50, {30, {10, ∅, ∅}, {40, ∅, {45, ∅, ∅}}}, {80, {60, ∅, ∅}, ∅}}", tree.getSubtreeWithPath("").toString());
		}
	
		@Test
		public void testGetSubtreeWithPathLeftPath() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			tree.insert(45);
	
			assertEquals("{30, {10, ∅, ∅}, {40, ∅, {45, ∅, ∅}}}", tree.getSubtreeWithPath("0").toString());
		}
	
		@Test
		public void testGetSubtreeWithPathRightPath() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(50);
			tree.insert(30);
			tree.insert(80);
			tree.insert(10);
			tree.insert(40);
			tree.insert(60);
			tree.insert(45);
	
			assertEquals("{80, {60, ∅, ∅}, ∅}", tree.getSubtreeWithPath("1").toString());
		}
	
		@Test
		public void testGetContentWithPathEmptyPath() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(20);
			tree.insert(30);
	
			assertEquals(Integer.valueOf(10), tree.getContentWithPath(""));
		}
	
		@Test
		public void testGetContentWithPathLeftPath() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(20);
			tree.insert(30);
	
			assertEquals(Integer.valueOf(5), tree.getContentWithPath("0"));
		}
	
		@Test
		public void testGetContentWithPathRightPath() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(20);
			tree.insert(30);
	
			assertEquals(Integer.valueOf(20), tree.getContentWithPath("1"));
		}
	
		@Test
		public void testGetContentWithPathComplexPath() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(20);
			tree.insert(30);
	
			assertEquals(Integer.valueOf(30), tree.getContentWithPath("11"));
		}

		@Test
		public void testTagDescendent() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(20);
			tree.insert(2);
			tree.insert(30);
	
			tree.tagDescendent();
	
			assertEquals("{10 [(descend, 3)], {5 [(descend, 4)], {2 [(descend, 5)], ∅, ∅}, ∅}, {20 [(descend, 2)], ∅, {30 [(descend, 1)], ∅, ∅}}}", tree.toString());
		}

		@Test
		public void testParentChildPairsTagPreorder() {
			BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
			tree.insert(10);
			tree.insert(5);
			tree.insert(20);
			tree.insert(2);
			tree.insert(30);
	
			List<String> pairs = tree.parentChildPairsTagPreorder();
	
			// Comprobación de pares esperados
			assertEquals("[(10, 5), (5, 2), (10, 20), (20, 30)]", pairs.toString());
		}

		
   
	}


