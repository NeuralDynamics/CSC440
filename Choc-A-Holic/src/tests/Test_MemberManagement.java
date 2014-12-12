package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.*;

public class Test_MemberManagement {
	
	MemberManager mm;

	@Before
	public void setUp() throws Exception {
		mm = new MemberManager();
	}
	
	@Test
	public void testAddMember() {
		mm.load();
		mm.addMember(new Member(131313131, "Josh Sommerfeld", "717 Arcadian Ave", "Waukesha", "WI", 53186, "2624085097", "oooo"));
		Member mem = mm.findMember(131313131);
		assertNotNull(mem);
		assertTrue(mem.getName().equals("Josh Sommerfeld"));
	}

}
