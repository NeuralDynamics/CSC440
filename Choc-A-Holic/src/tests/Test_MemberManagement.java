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
		mm.addMember(new Member(122122, "Josh Sommerfeld", "717 Arcadian Ave", "Waukesha", "WI", 53186, "2624085097", "oooo", "1564654", "456456"));
		Member mem = mm.findMember(122122);
		assertNotNull(mem);
	}

}
