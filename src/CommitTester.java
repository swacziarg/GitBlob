import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CommitTester {

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		
//	}

	@Test
	static void cTest() throws NoSuchAlgorithmException, IOException {
		Commit com1 = new Commit("a test for commits", "simon",null);
		Commit com2 = new Commit("test2", "simon",com1);
		Commit com3 = new Commit("test3", "simon",com2);
		Commit com4 = new Commit("test4", "simon",com3);

	}
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}


}
