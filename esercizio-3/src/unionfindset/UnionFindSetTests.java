package unionfindset;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UnionFindSetTests {

	private UnionFindSet<Integer> u1;
	private UnionFindSet<String> u2;
	private Integer i1, i2, i3;
	private String s1,s2,s3;

	@Before
	public void init_UnionFindSet() {
		u1 = new UnionFindSet<>();
		u2 = new UnionFindSet<>();

		i1 = 3;
		i2 = 4;
		i3 = 2;

		s1 = "a";
		s2 = "c";
		s3 = "e";
	}

	@Test
	public void test_makeSet_int() throws Exception {
		Integer[] set = {i1,i2,i3};
		u1.makeSet(set);

		for(Integer i: set)
			assertEquals(i,u1.find(i));
	}

	@Test 
	public void test_unionSet_int() throws Exception {
		Integer[] set = {i1,i2,i3};
		u1.makeSet(set);

		u1.union(i1,i2);
		assertEquals(i2,u1.find(i1));	

	}

	@Test 
	public void test_makeSet_string() throws Exception {
		String[] set = {s1,s2,s3};
		u2.makeSet(set);

		for(String i: set)
			assertEquals(i,u2.find(i));
	}



}