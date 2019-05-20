package unionfindset;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UnionFindSetTests {

	private UnionFindSet<Integer> u1;
	private UnionFindSet<String> u2;
	private Integer i1, i2, i3, i4;
	private String s1, s2, s3, s4;

	@Before
	public void init_UnionFindSet() {
		u1 = new UnionFindSet<>();
		u2 = new UnionFindSet<>();

		i1 = 3;
		i2 = 4;
		i3 = 2;
		i4 = 9;

		s1 = "a";
		s2 = "c";
		s3 = "e";
		s4 = "g";
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

	@Test 
	public void test_unionSet_string() throws Exception {
		String[] set = {s1,s2,s3};
		u2.makeSet(set);

		u2.union(s1,s2);
		assertEquals(s2,u2.find(s1));	

	}

	@Test
	public void test_unionSet_int2() throws Exception {
		Integer[] set = {i1,i2,i3,i4};
		u1.makeSet(set);

		u1.union(i1,i2);
		u1.union(i3,i4);

		assertEquals(i2,u1.find(i1));
		assertEquals(i4,u1.find(i3));

		u1.union(i2,i4);
		assertEquals(i4,u1.find(i2));	

	}

	@Test
	public void test_unionSet_string2() throws Exception {
		String[] set = {s1,s2,s3,s4};
		u2.makeSet(set);

		u2.union(s1,s2);
		u2.union(s3,s4);

		assertEquals(s2,u2.find(s1));
		assertEquals(s4,u2.find(s3));

		u2.union(s2,s4);
		assertEquals(s4,u2.find(s2));	

	}

	@Test
	public void test_rankSet_int() throws Exception {
		Integer[] set = {i1,i2,i3};
		u1.makeSet(set);

		u1.union(i1,i2);
		u1.union(i3,i2);

		for(Integer i: set)
			assertEquals(i2,u1.find(i));

	}

	@Test
	public void test_rankSet_string() throws Exception {
		String[] set = {s1,s2,s3};
		u2.makeSet(set);

		u2.union(s1,s2);
		u2.union(s3,s2);

		for(String i: set)
			assertEquals(s2,u2.find(i));

	}

	@Test
	public void test_rankSet_int2() throws Exception {
		Integer[] set = {i1,i2,i3,i4};
		u1.makeSet(set);

		u1.union(i2,i1);

		for(int i = 0; i!=1; i++){
			assertEquals(set[i],u1.find(i2));
		}

		for(int j = 2; j!=set.length; j++){
			assertEquals(set[j],u1.find(set[j]));
		}

	}

	@Test
	public void test_rankSet_string2() throws Exception {
		String[] set = {s1,s2,s3,s4};
		u2.makeSet(set);

		u2.union(s2,s1);

		for(int i = 0; i!=1; i++){
			assertEquals(set[i],u2.find(s2));
		}

		for(int j = 2; j!=set.length; j++){
			assertEquals(set[j],u2.find(set[j]));
		}

	}



}