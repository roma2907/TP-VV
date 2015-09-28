package system;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestPhonyListExample {

	/*
	 * Helper method to create lists.
	 */
	private PhonyList<Integer> list(Integer... content) {
		PhonyList<Integer> list = new PhonyList<>();
		for (Integer i : content)
			list.add(i);
		return list;
	}

	/*
	 * Helper method to create a big list [1,2,3,...,10000]
	 */
	private PhonyList<Integer> thousandElementsList() {
		PhonyList<Integer> thousandElementsList = new PhonyList<>();
		for (int i = 1; i <= 10000; i++) {
			thousandElementsList.add(i);
		}
		return thousandElementsList;
	}

	/**
	 * Tests the "clear" method with an empty list.
	 * 
	 * @see PhonyList#clear()
	 * @type Functional
	 * @input []
	 * @oracle The obtained list must be empty.
	 * @passed Yes
	 */
	@Test
	public void clear_emptyList() {
		// Creating a call context
		PhonyList<Integer> actual = list();

		// Calling the tested operation
        actual.clear();

		// Oracle
		assertEquals(actual, list());
	}

	/**
	 * Tests the "clear" method with a list of a single element.
	 * 
	 * @see PhonyList#clear()
	 * @type Functional
	 * @input [1]
	 * @oracle The obtained list must be empty.
	 * @passed Yes
	 */
	@Test
	public void clear_oneElement() {
		// Creating a call context
		PhonyList<Integer> actual = list(1);

		// Calling the tested operation
        actual.clear();

		// Oracle
        assertEquals(actual, list());
	}

	/**
	 * Tests the "clear" method with a big list.
	 * 
	 * @see PhonyList#clear()
	 * @type Functional
	 * @input A list containing the 10000 first integers.
	 * @oracle The obtained list must be empty.
	 * @passed Yes
	 */
	@Test
	public void clear_thousandElements() {
		// Creating a call context
		PhonyList<Integer> actual = thousandElementsList();

		// Calling the tested operation
        actual.clear();

		// Oracle
        assertEquals(actual, list());
	}

	/**
	 * Tests the "indexOf" method with an empty list.
	 * 
	 * @see PhonyList#indexOf(Object)
	 * @type Functional
	 * @input list=[], o=3
	 * @oracle It must return -1.
	 * @passed No
	 * @correction <pre>
	 * l.209
	 * - return 0;
	 * + return -1;
	 * </pre>
	 */
	@Test
	public void indexOf_emptyList() {
		// Creating a call context
		PhonyList<Integer> list = list();

		// Calling the tested operation
		int result = list.indexOf(3);

		// Oracle
		assertEquals(-1, result);
	}

	/**
	 * Tests the "indexOf" method with a list of one element and asking for the
	 * element.
	 * 
	 * @see PhonyList#indexOf(Object)
	 * @type Functional
	 * @input list=[3], o=3
	 * @oracle It must return 0.
	 * @passed Yes
	 */
	@Test
	public void indexOf_oneElementPresent() {
		// Creating a call context
		PhonyList<Integer> list = list(3);

		// Calling the tested operation
		int result = list.indexOf(3);

		// Oracle
		assertEquals(0, result);
	}

	/**
	 * Tests the "indexOf" method with a list of one element and asking for some
	 * wrong element.
	 * 
	 * @see PhonyList#indexOf(Object)
	 * @type Functional
	 * @input list=[5], o=1337
	 * @oracle It must return -1.
	 * @passed No
	 * @correction <pre>
	 * l.209
	 * - return 0;
	 * + return -1;
	 * </pre>
	 */
	@Test
	public void indexOf_oneElementWrong() {
		// Creating a call context
		PhonyList<Integer> list = list(5);

		// Calling the tested operation
		int result = list.indexOf(1337);

		// Oracle
		assertEquals(-1, result);
	}

	/**
	 * Tests the "indexOf" method with small list and asking for the first
	 * element.
	 * 
	 * @see PhonyList#indexOf(Object)
	 * @type Functional
	 * @input list=[75,93,0,4,-56], o=3
	 * @oracle It must return 0.
	 * @passed Yes
	 */
	@Test
	public void indexOf_smallFirstPresent() {
		// Creating a call context
		PhonyList<Integer> list = list(75, 93, 0, 4, -56);

		// Calling the tested operation
		int result = list.indexOf(75);

		// Oracle
		assertEquals(0, result);
	}


	/**
	 * Tests the "indexOf" method with a small list and asking for an element
	 * present twice.
	 * 
	 * @see PhonyList#indexOf(Object)
	 * @type Functional
	 * @input list=[75,93,0,4,-56,4,89], o=4
	 * @oracle It must return 3.
	 * @passed Yes
	 */
	@Test
	public void indexOf_smallDoubleInt() {
		// Creating a call context
		PhonyList<Integer> list = list(75, 93, 0, 4, -56, 4, 89);

		// Calling the tested operation
		int result = list.indexOf(4);

		// Oracle
		assertEquals(3, result);
	}

	/**
	 * Tests the "indexOf" method with a small list and asking for a null
	 * element present twice.
	 * 
	 * @see PhonyList#indexOf(Object)
	 * @type Functional
	 * @input list=[75,93,0,null,-56,null,89], o=null
	 * @oracle It must return 3.
	 * @passed Yes
	 */
	@Test
	public void indexOf_smallDoubleNull() {
		// Creating a call context
		PhonyList<Integer> list = list(75, 93, 0, null, -56, null, 89);

		// Calling the tested operation
		int result = list.indexOf(null);

		// Oracle
		assertEquals(3, result);
	}

	/**
	 * Tests the "indexOf" method with a small list and asking for the last
	 * element.
	 * 
	 * @see PhonyList#indexOf(Object)
	 * @type Functional
	 * @input list=[75,93,0,null,-56,null,89], o=89
	 * @oracle It must return 7.
	 * @passed Yes
	 */
	@Test
	public void indexOf_smallLastPresent() {
		// Creating a call context
		PhonyList<Integer> list = list(75, 93, 0, null, -56, null, 89);

		// Calling the tested operation
		int result = list.indexOf(89);

		// Oracle
		assertEquals(6, result);
	}

	/**
	 * Tests the "indexOf" method with a small list and asking for 
	 * a missing element.
	 * 
	 * @see PhonyList#indexOf(Object)
	 * @type Functional
	 * @input list=[75,93,0,4,-56,4,89], o=1337
	 * @oracle It must return -1.
	 * @passed No
	 * @correction <pre>
	 * l.209
	 * - return 0;
	 * + return -1;
	 * </pre>
	 */
	@Test
	public void indexOf_smallWrong() {
		// Creating a call context
		PhonyList<Integer> list = list(75, 93, 0, 4, -56, 4, 89);

		// Calling the tested operation
		int result = list.indexOf(1337);

		// Oracle
		assertEquals(-1, result);
	}

	/**
	 * Tests the "indexOf" method with a big list and asking for some middle
	 * element.
	 * 
	 * @see PhonyList#indexOf(Object)
	 * @type Functional
	 * @input list=[1,...,10000], o=-16
	 * @oracle It must return -1.
	 * @passed Yes
	 */
	@Test
	public void indexOf_thousandElementsWrong() {
		// Creating a call context
		PhonyList<Integer> list = thousandElementsList();

		// Calling the tested operation
		int result = list.indexOf(-16);

		// Oracle
		assertEquals(-1, result);
	}

	/**
	 * Tests the "indexOf" method with a big list and asking for the last
	 * element.
	 * 
	 * @see PhonyList#indexOf(Object)
	 * @type Functional
	 * @input list=[1,...,10000], o=10000
	 * @oracle It must return 9999.
	 * @passed Yes
	 */
	@Test
	public void indexOf_thousandElementsLastPresent() {
		// Creating a call context
		PhonyList<Integer> list = thousandElementsList();

		// Calling the tested operation
		int result = list.indexOf(10000);

		// Oracle
		assertEquals(9999, result);
	}

}
