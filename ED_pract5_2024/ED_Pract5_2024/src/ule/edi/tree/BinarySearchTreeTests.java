package ule.edi.tree;


import static org.junit.Assert.assertEquals;

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
	
	@Before
	public void setupBSTs() {
		
			
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

		
		

	}


