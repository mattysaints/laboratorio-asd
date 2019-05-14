package unionfindset

import static org.junit.Assert.*;
import org.junit.Test;

public class UnionFindSetTests {

	private Unionfindset<T> u1;
	private Unionfindset<T> u2;

	@Before
	public class UnionFindSet_Create() {
		u1 = new Unionfindset<>();
		u2 = new Unionfindset<>();
	}

	@Test
	public void Make_SetInt() {
		int [] i1 = {1};
		u1.Make_SetInt(i1);

		assertTrue(u1.find(i1) == 1);

	}



}