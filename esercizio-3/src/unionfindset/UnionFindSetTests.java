package unionfindset;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UnionFindSetTests {

	private Integer[] integers;
	private String[] strings;
	private UnionFindSet<Integer> ufsInt;
	private UnionFindSet<String> ufsStr;


	@Before
	public void init_UnionFindSet() {
		ufsInt = new UnionFindSet<>();
		ufsStr = new UnionFindSet<>();

		integers = new Integer[]{3,4,2,9,7,1};
		strings = new String[]{"albero","casa","elefante","gara","mano","computer"};
	}


	// Tests UnionFindSet of integers

	@Test(expected=UnionFindSetException.class)
	public void test_makeSet_null() throws Exception {
		ufsInt.makeSet(null);
	}

	@Test(expected=UnionFindSetException.class)
	public void test_makeSet_zeroLength() throws Exception {
		ufsInt.makeSet(new Integer[0]);
	}

	@Test
	public void test_makeSet_alreadyCreated() throws Exception {
		ufsInt.makeSet(new Integer[3]);
		assertFalse(ufsInt.makeSet(new Integer[4]));
	}

	@Test
	public void test_makeSet_int() throws Exception {
		ufsInt.makeSet(integers);

		for(Integer i: integers)
			assertEquals(i,ufsInt.find(i));
	}

	@Test(expected=UnionFindSetException.class)
	public void test_union_null() throws Exception {
		ufsInt.makeSet(integers);

		ufsInt.union(null,integers[1]);
	}

	@Test
	public void test_union_notFound() throws Exception {
		ufsInt.makeSet(integers);

		assertFalse(ufsInt.union(10,integers[1]));
	}

	@Test 
	public void test_union_int() throws Exception {
		ufsInt.makeSet(integers);

		ufsInt.union(integers[0],integers[1]);
		assertEquals(integers[0],ufsInt.find(integers[1]));	
	}

	@Test
	public void test_union_differentRank_int() throws Exception {
		ufsInt.makeSet(integers);

		ufsInt.union(integers[0],integers[1]);
		ufsInt.union(integers[0],integers[2]);

		Integer i;
		for(int k=0; k<3;k++) {
			i = integers[k];
			assertEquals(integers[0],ufsInt.find(i));
		}
	}

	@Test(expected=UnionFindSetException.class)
	public void test_find_null() throws Exception {
		ufsInt.makeSet(integers);

		ufsInt.find(null);
	}

	@Test(expected=UnionFindSetException.class)
	public void test_find_notFound() throws Exception {
		ufsInt.makeSet(integers);

		ufsInt.find(10);
	}

	@Test
	public void test_find_self_int() throws Exception {
		ufsInt.makeSet(integers);

		assertEquals(integers[0],ufsInt.find(integers[0]));
	}

	@Test
	public void test_find_root_int() throws Exception {
		ufsInt.makeSet(integers);

		ufsInt.union(integers[0],integers[1]);
		ufsInt.union(integers[1],integers[2]);
		ufsInt.union(integers[2],integers[3]);

		assertEquals(integers[0],ufsInt.find(integers[3]));
	}

	// Tests UnionFindSet of strings

	@Test 
	public void test_makeSet_String() throws Exception {
		ufsStr.makeSet(strings);

		for(String i: strings)
			assertEquals(i,ufsStr.find(i));
	}

	@Test 
	public void test_union_String() throws Exception {
		ufsStr.makeSet(strings);

		ufsStr.union(strings[0],strings[1]);
		assertEquals(strings[0],ufsStr.find(strings[1]));	
	}

	@Test
	public void test_union_differentRank_String() throws Exception {
		ufsStr.makeSet(strings);

		ufsStr.union(strings[0],strings[1]);
		ufsStr.union(strings[0],strings[2]);

		String i;
		for(int k=0; k<3; k++) {
			i = strings[k];
			assertEquals(strings[0],ufsStr.find(i));
		}
	}

	@Test
	public void test_find_self_String() throws Exception {
		ufsStr.makeSet(strings);

		assertEquals(strings[0],ufsStr.find(strings[0]));
	}

	@Test
	public void test_find_root_String() throws Exception {
		ufsStr.makeSet(strings);

		ufsStr.union(strings[0],strings[1]);
		ufsStr.union(strings[1],strings[2]);
		ufsStr.union(strings[2],strings[3]);

		assertEquals(strings[0],ufsStr.find(strings[3]));
	}

}