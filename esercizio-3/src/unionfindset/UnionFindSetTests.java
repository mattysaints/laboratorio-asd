package unionfindset;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UnionFindSetTests {

	private UnionFindSet<Integer> u1,u2,u3;
	private Integer i1, i2, i3;
	private String s1,s2,s3;

	@Before
	public void init_UnionFindSet() {
		u1 = new UnionFindSet<>();
		u2 = new UnionFindSet<>();
		u3 = new UnionFindSet<>();
		
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
	public void test_makeSet_str() throws Exception {
		String[] set = {s1,s2,s3};
		u1.makeSet(set);

		for(String i: set)
			assertEquals(i,u1.find(i));
	}

	@Test 
	public void test_unionSet_int() {
		Integer[] set1 = {i1};
		Integer[] set2 = {i2};

		u1.makeSet(set1);
		u2.makeSet(set2);

		//union

	}

}