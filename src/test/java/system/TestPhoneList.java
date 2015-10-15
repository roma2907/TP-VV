package system;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import system.PhonyList;

public class TestPhoneList {

	private PhonyList<Object> list;
	
	@Before
	public void setUp(){
		list = new PhonyList<Object>();
	}
	
	/**
	 * test d'une valeur dans le tableau.
	 */
	@Test
	public void testContains_InArray(){
		generateValue();
		//Erreur contains >= 0
		assertTrue(list.contains(1));
	}
	
	/** test d'une valeur qui n'est pas dans le tableau.
	 */
	@Test
	public void testContainsNotInArray(){
		generateValue();
		assertFalse(list.contains(new Object()));
	}
	
	/**
	 * Test d'une valeur nulle qui est dans le tableau.
	 */
	@Test
	public void testContains_InArrayNullValue(){
		generateValueWithNull();
		assertTrue(list.contains(null));
	}
	
	/**
	 * test indexOf d'une valeur qui n'est pas dans le tableau.
	 */
	@Test
	public void testIndexOf_NotInArray(){
		Object o = new Object();
		assertEquals(-1,list.indexOf(o));
	}
	
	/**
	 * test indexOf d'une valeur qui est dans le tableau.
	 */
	@Test
	public void testIndexOf_InArray(){
		generateValue();
		assertEquals(1,list.indexOf(-2));
	}
	
	/**
	 * test indexOf d'une valeur nulle dans le tableau.
	 */
	@Test
	public void testIndexOf_InArrayNullValue(){
		generateValueWithNull();
		assertEquals(2,list.indexOf(null));
	}
	
	/**
	 * test add sur une liste vide.
	 */
	@Test
	public void testAdd_InEmptyList(){
		Object o = new Object();
		assertTrue(list.add(o));
		assertEquals(1,list.size());
		assertEquals(o,list.get(0));
	}
	
	/**
	 * Test add sur une liste non vide.
	 */
	@Test
	public void testAdd_List(){
		generate9Value();
		Object o1 = new Object();
		Object o2 = new Object();
		assertTrue(list.add(o1));
		assertEquals(10,list.size());
		assertEquals(o1,list.get(9));
		assertTrue(list.add(o2));
		assertEquals(11,list.size());
		assertEquals(o2,list.get(10));
	}
	

	/**
	 * Test addAll avec une debut d'index supérieur à la taille de la liste.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddAll_indexSuperiorSize(){
		generateValue();
		List<Integer> entiers = Arrays.asList(1,22,98);
		list.addAll(11, entiers);
	}
	
	/**
	 * Test addAll avec un index négatif.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddAll_indexNegative(){
		generateValue();
		List<Integer> entiers = Arrays.asList(1,22,98);
		list.addAll(-1, entiers);
	}
	
	/**
	 * Test addAll avec une valeur nulle pour la liste à ajouter.
	 */
	@Test(expected=NullPointerException.class)
	public void testAddAll_NullList(){
		generateValue();
		list.addAll(0, null);
	}
	
	/**
	 * Test nominal.
	 */
	@Test
	public void testAddAll_List(){
		generateValue();
		List<Integer> entiers = Arrays.asList(1,22,98);
		boolean result = list.addAll(4, entiers);
		assertEquals(1,list.get(4));
		assertEquals(22,list.get(5));
		assertEquals(98,list.get(6));
		assertEquals(7,list.size());
		assertTrue(result);
	}
	
	/**
	 * Test addAll avec index compris entre 0 et la size de la liste.
	 */
	@Test
	public void testAddAll_ListIndexBetween0AndSize(){
		generateValue();
		List<Integer> entiers = Arrays.asList(1,22,98);
		boolean result = list.addAll(3, entiers);
		assertEquals(1,list.get(3));
		assertEquals(22,list.get(4));
		assertEquals(98,list.get(5));
		assertEquals(2.3,list.get(6));
		assertEquals(7,list.size());
		assertTrue(result);
	}
	
	/**
	 * Test addAll
	 */
	@Test
	public void testAddAll_EmptyListToAdd(){
		generateValue();
		boolean result = list.addAll(3, new ArrayList<Object>());
		assertEquals(4,list.size());
		assertFalse(result);
	}
	
	/**
	 * Suppression du première élément d'une liste.
	 */
	@Test 
	public void testRemove_FirstValueInArray(){
		generateValue();
		assertTrue(list.contains(1));
		boolean result = list.remove(1);
		assertFalse(list.contains(1));
		assertEquals(3,list.size());
		assertTrue(result);
	}
	
	/**
	 * 
	 * Suppression d'une valeur nulle.
	 */
	@Test
	public void testRemove_NullValue(){
		generate9Value();
		assertTrue(list.contains(null));
		//Error elementData[index] == null
		boolean result = list.remove(null);
		assertFalse(list.contains(null));
		assertEquals(8,list.size());
		assertTrue(result);
	}
	
	/**
	 * Suppression d'une valeur qui n'ets pas dans le tableau.
	 */
	@Test
	public void testRemove_NotInArray(){
		generateValue();
		assertFalse(list.contains(132));
		boolean result = list.remove(132);
		assertFalse(result);
	}
	
	/**
	 * Suppression du dernier élément d'une liste
	 */
	@Test
	public void testRemove_LastValueInArray(){
		generateValue();
		assertTrue(list.contains(2.3));
		boolean result = list.remove(2.3);
		assertFalse(list.contains(2.3));
		assertEquals(3,list.size());
		assertTrue(result);
	}
	
	/**
	 * Test IsEmpty sur une liste vide
	 */
	@Test
	public void testIsEmpty_WithEmptyList(){
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Test isEmpty sur une liste non vide
	 */
	@Test 
	public void testIsEmpty_List(){
		generateValue();
		assertFalse(list.isEmpty());
	}
	
	/**
	 * Test elementData sur une liste vide.
	 */
	@Ignore
	@Test
	public void testElementData_EmptyList(){
		//Erreur méthode devrait être private 
		//list.elementData(0);
	}
	
	/**
	 * Test get sur première élément d'une liste vide.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet_EmptyList(){
		list.get(0);
	}
	
	/**
	 * Test get sur un élément négatif d'une liste vide.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGet_EmptyListNegativeValue(){
		list.get(-1);
	}
	
	/**
	 * Test get sur un le premier élément d'une liste.
	 */
	@Test
	public void testGet_FirstValueInList(){
		generateValue();
		assertEquals(1,list.get(0));
	}
	
	/**
	 * Test get sur un le dernier élément d'une liste.
	 */
	@Test
	public void testGet_LastValueInList(){
		generateValue();
		assertEquals(2.3,list.get(3));
	}
	
	/**
	 * Test set sur première élément d'une liste vide.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSet_EmptyList(){
		list.set(0,2);
	}
	
	/**
	 * Test set sur un élément négatif d'une liste vide.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSet_EmptyListNegativeValue(){
		list.set(-1,2);
	}
	
	/**
	 * Test set sur première élément d'une liste.
	 */
	@Test
	public void testSet_FirstValueInList(){
		generateValue();
		assertEquals(1, list.get(0));
		Object o = list.set(0, 2);
		// Erreur elementData[++index] = element
		assertEquals(2,list.get(0));
		assertEquals(1,(int)o);
	}
	
	/**
	 * Test set sur dernier élément d'une liste.
	 */
	@Test
	public void testSet_LastValueInList(){
		generateValue();
		assertEquals(2.3, list.get(3));
		Object o = list.set(3, 2);
		assertEquals(2,list.get(3));
		assertEquals(2.3,(double)o,0);
	}
	
	/**
	 * Test removeAll à partir d'une liste vide.
	 */
	@Test
	public void testRemoveAll_EmptyArray(){
		List<Integer> entiers = Arrays.asList(1,23);
		boolean result = list.removeAll(entiers);
		assertFalse(result);
	}
	
	/**
	 * Test removeAll sur une liste de 4 élément avec la liste des objets à supprimer qui contient un des élement de la liste.
	 */
	@Test
	public void testRemoveAll_OneInList(){
		generateValue();
		List<Integer> entiers = Arrays.asList(-2,23);
		assertEquals(4,list.size());
		assertTrue(list.contains(-2));
		boolean result = list.removeAll(entiers);
		assertTrue(result);
		assertFalse(list.contains(-2));
		assertEquals(3,list.size());
	}
	
	/**
	 * Test removeAll liste avec une valeur nulle.
	 */
	@Test(expected = NullPointerException.class) 
	public void testRemoveAll_WithNull(){
		generateValue();
		list.removeAll(null);
	}
	
	/**
	 * Test removeAll suppression d'une liste d'objet vide.
	 */
	@Test
	public void testRemoveAll_EmptyList(){
		generateValue();
		List<Object> objects = Arrays.asList();
		boolean result = list.removeAll(objects);
		assertFalse(result);
	}
	
	/**
	 * Test removeRange dans une liste de valeurs avec une bonne plage.
	 */
	@Test 
	public void testRemoveRange_ListValue(){
		generateValue();
		list.removeRange(1, 3);
		assertEquals(2,list.size());
	}
	
	/**
	 * Test removeRange avec un index de fin supérieur à la taille de la liste.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveRange_indexSuperiorSize(){
		generateValue();
		list.removeRange(0, 7);
	}
	
	/**
	 * Test removeRange sur une liste vide.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveRange_EmptyList(){
		list.removeRange(0, 2);
	}
	
	/**
	 * Test removeRange valeur d'index négatif. 
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveRange_negativeIndex(){
		generateValue();
		list.removeRange(-1, 2);
	}
	
	/**
	 * Test equals same instance.
	 */
	@Test
	public void testEquals_SameInstance(){
		boolean result = list.equals(list);
		assertTrue(result);
	}
	
	/**
	 * Test equals on a other object. 
	 */
	@Test
	public void testEquals_OtherType(){
		boolean result = list.equals(2);
		assertFalse(result);
	}
	
	/**
	 * Test equals with an other bigger list
	 */
	@Test
	public void testEquals_OtherList(){
		generate9Value();
		PhonyList<Object> phonylist = new PhonyList<Object>();
		boolean result = list.equals(phonylist);
		assertFalse(result);
	}
	
	/**
	 * test equals, 2 PhonyList with the same size but with different value.
	 */
	@Test 
	public void testEquals_SameSizeButDifferentValue(){
		PhonyList<Object> phonylist = new PhonyList<Object>();
		list.add(2);
		list.add("3");
		phonylist.add("2");
		phonylist.add(3);
		boolean result = list.equals(phonylist);
		assertFalse(result);
	}
	
	/**
	 * test equals, 2 PhonyList with the same size, with same value.
	 */
	@Test 
	public void testEquals_SameSizeSameValue(){
		PhonyList<Object> phonylist = new PhonyList<Object>();
		list.add(2);
		list.add("3");
		phonylist.add(2);
		phonylist.add("3");
		boolean result = list.equals(phonylist);
		assertTrue(result);
	}
	
	/**
	 * test equals, 2 PhonyList with the same size, the first own a null value.
	 */
	@Test 
	public void testEquals_SameSizeWithNullValueInFirstList(){
		PhonyList<Object> phonylist = new PhonyList<Object>();
		list.add(2);
		list.add(null);
		phonylist.add(2);
		phonylist.add("3");
		boolean result = list.equals(phonylist);
		assertFalse(result);
	}
	
	/**
	 * test equals, 2 PhonyList with the same size, with same value with null value.
	 */
	@Test 
	public void testEquals_SameSizeSameValueWithNull(){
		PhonyList<Object> phonylist = new PhonyList<Object>();
		list.add(2);
		list.add(null);
		phonylist.add(2);
		phonylist.add(null);
		boolean result = list.equals(phonylist);
		assertTrue(result);
	}
	
	
	
	private void generateValue(){
		list.add(1);
		list.add(-2);
		list.add("test");
		list.add(2.3);
	}
	
	private void generate9Value(){
		list.add(1);
		list.add(-2);
		list.add("test");
		list.add(2.3);
		list.add(null);
		list.add("");
		list.add(356);
		list.add(3D);
		list.add(new Object());
	}
	
	private void generateValueWithNull(){
		list.add("");
		list.add(-23);
		list.add(null);
		list.add("unit");
		list.add(56.5);
		list.add(null);
	}

}
