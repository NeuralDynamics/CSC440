package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import app.IWriter;
import app.IReader;
import app.FileWriter;
import app.FileReader;

public class Test_FileManagement {
	
	IWriter<String> w = null;
	IReader<String> r = null;

	@Before
	public void setUp() throws Exception {
		w = new FileWriter<String>("FileWriter.tst");
		r = new FileReader<String>("FileReader.tst");
	}

	@Test
	public void testWrite() {
		w.writeData("Test Data");
	}
	
	@Test
	public void testRead() {
		String tmp = (String)r.readData();
		assertTrue(tmp.equals("Test Data"));
	}

}
