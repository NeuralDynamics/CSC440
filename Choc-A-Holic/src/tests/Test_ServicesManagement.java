package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.*;

public class Test_ServicesManagement {
	
	ServicesManager sm;
	@Before
	public void setUp() throws Exception {
		sm = new ServicesManager(); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddService() {
		sm.load();
		sm.addService(new Service("aerobics exercise session", 883948, 20.25));
		Service svc = sm.findService(883948);
		assertNotNull(svc);
		assertTrue(svc.getServiceName().equals("aerobics exercise session"));
		assertTrue(svc.getServiceFee() == 20.25);
	}

}
