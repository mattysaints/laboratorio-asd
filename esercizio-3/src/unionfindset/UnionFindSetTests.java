package unionfindset;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UnionFindSetTests {

	private UnionFindSet<Integer> u1;
	private Integer i1, i2, i3;

	@Before
	public void init_UnionFindSet() {
		u1 = new UnionFindSet<>();
		i1 = new Integer(3);
		i2 = new Integer(4);
		i3 = new Integer(2);
	}

	@Test
	public void test_makeSet_int() {
		Integer[] set = {i1,i2,i3};
		u1.makeSet(set);

		for(Integer i: set)
			assertEquals(i,u1.find(i));
	}

}