

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//import Git.Blob;

class CommTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//create test folder, objects folder in test, three txt files
	
		File objFolder = new File("objects");
		objFolder.mkdirs();
		PrintWriter writer = new PrintWriter("testFile.txt", "UTF-8");
		writer.print("test 1");
		writer.close();
		PrintWriter writer1 = new PrintWriter("testFile1.txt", "UTF-8");
		writer1.print("test 2");
		writer1.close();
		PrintWriter writer2 = new PrintWriter("testFile2.txt", "UTF-8");
		writer2.print("test 3");
		writer2.close();
	}
//	File file = new File("something.txt");
//	PrintWriter something = new PrintWriter(file);
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
				//deleting all testTXT files
		File f = new File("testFile.txt");
		f.delete();
		File f1 = new File("testFile1.txt");
		f1.delete();
		File f2 = new File("testFile2.txt");
		f2.delete();
		//delete index file
		File index = new File("index");
		index.delete();
		
		
		File directory = new File("objects\\");
		if (directory.isDirectory()) {
	        for (File sub : directory.listFiles()) {
	            sub.delete();
	        }
	    }
		directory.delete();

	}	

	
	// **************TESTS
	@Test 
	void testBlobContents() throws IOException, NoSuchAlgorithmException
	{
		Index index = new Index();
//		index.init();
		File testIndex = new File("index");
		assertTrue(testIndex.exists());
		
		index.add("testFile.txt");
		index.add("testFile1.txt");
		index.add("testFile2.txt");
		Commit com1 = new Commit("a test for commits", "simon",null);
		Commit com2 = new Commit("test2", "simon",com1);
		Commit com3 = new Commit("test3", "simon",com2);
		Commit com4 = new Commit("test4", "simon",com3);
	}

}
