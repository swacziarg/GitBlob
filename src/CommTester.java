

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		PrintWriter writer11 = new PrintWriter("testFile11.txt", "UTF-8");
		writer11.print("test writer11");
		writer11.close();
		PrintWriter writer12 = new PrintWriter("testFile12.txt", "UTF-8");
		writer12.print("test writer12");
		writer12.close();
		PrintWriter writer22 = new PrintWriter("testFile22.txt", "UTF-8");
		writer22.print("test writer22");
		writer22.close();
		PrintWriter writer33 = new PrintWriter("testFile33.txt", "UTF-8");
		writer33.print("test writer33");
		writer33.close();
		PrintWriter writer13 = new PrintWriter("testFile13.txt", "UTF-8");
		writer13.print("test writer13");
		writer13.close();
		PrintWriter writer23 = new PrintWriter("testFile23.txt", "UTF-8");
		writer23.print("test writer23");
		writer23.close();
		PrintWriter writer4 = new PrintWriter("testFile4.txt", "UTF-8");
		writer4.print("test writer4");
		writer4.close();
		PrintWriter writer14 = new PrintWriter("testFile14.txt", "UTF-8");
		writer14.print("test writer14");
		writer14.close();
		PrintWriter writer24 = new PrintWriter("testFile24.txt", "UTF-8");
		writer24.print("test writer24");
		writer24.close();
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
		File f11 = new File("testFile11.txt");
		f11.delete();
		File f2 = new File("testFile2.txt");
		f2.delete();
		File f22 = new File("testFile33.txt");
		f22.delete();
		File f122 = new File("testFile13.txt");
		f122.delete();
		File f222 = new File("testFile23.txt");
		f222.delete();
		File f33 = new File("testFile4.txt");
		f33.delete();
		File f133 = new File("testFile14.txt");
		f133.delete();
		File f233 = new File("testFile24.txt");
		f233.delete();
		File f444 = new File("testFile12.txt");
		f444.delete();
		File f4444 = new File("testFile22.txt");
		f4444.delete();
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
		Index index1 = new Index();
//		index.init();
		File testIndex = new File("index");
		assertTrue(testIndex.exists());
		
		index1.add("testFile.txt");
		index1.add("testFile12.txt");
		index1.add("testFile22.txt");

		
		Commit com1 = new Commit("a test for commits", "simon",null);
		Index index2 = new Index(); 

		index2.add("testFile1.txt");
		index2.add("testFile24.txt");
		index2.add("testFile14.txt");
		Commit com2 = new Commit("test2", "simon",com1);
		Index index3 = new Index();

		index3.add("testFile2.txt");
		index3.add("testFile4.txt");
		index3.add("testFile23.txt");
		Commit com3 = new Commit("test3", "simon",com2);
		Index index4 = new Index();

		index4.add("testFile11.txt");
		index4.add("testFile33.txt");
		index4.add("testFile13.txt");
		Commit com4 = new Commit("test4", "simon",com3);
		index1.remove("testFile.txt");
		index1.remove("testFile12.txt");
		index1.remove("testFile22.txt");
		index2.remove("testFile1.txt");
		index2.remove("testFile24.txt");
		index2.remove("testFile14.txt");
		index3.remove("testFile2.txt");
		index3.remove("testFile4.txt");
		index3.remove("testFile23.txt");
		index4.remove("testFile11.txt");
		index4.remove("testFile13.txt");
		index4.remove("testFile33.txt");
		String temp = "";
		DateTimeFormatter datey = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		temp = datey.format(now);
		assertTrue(com4.getWritten().equals("blob : testFile11.txt : 3d12c2fa0c11cf364cb4393737eb53e1a1afa96e\nblob : testFile33.txt : 2b05b1d8389e2a397880cfb61b241ed956ec3f37\nblob : testFile13.txt : 744e9f6566b3240169ef43b654c9860ac23c53d7\ntree : objects\\21f521d49287f90e7931884b178c8716749cbd24\ntree : objects\\15878deb7dd9d372ae15b44bf9fa0e504ef14427\n\nsimon\n"+
		temp
		+ "\ntest4"));
	}

}
