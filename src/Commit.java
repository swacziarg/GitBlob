import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Commit {
	private Commit child;
	private Commit parent;
	private String summary = "";
	private String author = "";
	private String fileName = "";
	private String contents = "";
	private Tree pTree; 
	
	public Commit(String summaryInput, String authorInput, Commit parentPointer) throws NoSuchAlgorithmException, IOException {
		//yucky erik code
		summary = summaryInput;
		author = authorInput;
		String temp = "";
		if(parentPointer!=null) {
			setParent(parentPointer);
			parentPointer.setChild(this);
			temp = parentPointer.getName();
		}
		else {
			setParent(null);
		}
		
		
		
		//yummy simon code
		File index = new File("index");
		ArrayList<String> reader = new ArrayList<String>();
		BufferedReader fr = new BufferedReader(new FileReader(index));
		while (fr.ready()) {
			reader.add("blob : " + fr.readLine());
		}
		
		fr.close();

		pTree = new Tree(reader);
		
		writeFile();
		
	}

	public void setParent(Commit input) {
		parent = input;
	}
	
	public void setChild(Commit input) {
		child = input;
	}
	
	public String getDate() {
		String temp = "";
		DateTimeFormatter datey = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		temp = datey.format(now);
		return temp;
	}
	
	
	public String getName() {
		return "objects\\"+fileName;
	}
	public String getTreeLoc() {
		return "objects\\"+pTree.getTreeName();
	}
	public String getWritten() {
		return contents;
	}
	public void writeFile() throws IOException {
		String pT = "";
		String pa = "";
		String ch = "";
		if(pTree!=null)
			pT = "objects\\"+pTree.getTreeName();
		if(parent!=null)
			pa = "tree : " + parent.getTreeLoc();
		
		
		String fileConts = (pTree.toString()+"\ntree : "+pT+"\n"+pa+"\n"+ch+"\n"+author+"\n"+getDate()+"\n"+summary);
		fileName = shaCreator(fileConts);
		File f = new File("objects\\"+fileName);
		contents = fileConts;
		BufferedWriter w = new BufferedWriter(new FileWriter(f));
		w.write(fileConts);
		w.close();
		//clears index 
		PrintWriter pw = new PrintWriter("index");
		pw.close();

	}
//	public void deleteFile(File fileToDelete) throws IOException {
//		fileToDelete.delete();
//		File index = new File("index");
//		BufferedWriter w = new BufferedWriter(new FileWriter(index));
//		w.write("*deleted* " + fileToDelete.getName());
//		w.close();
//	}
//	public void editFile(File fileToEdit,String newText) throws IOException {
//		BufferedWriter wr = new BufferedWriter(new FileWriter(fileToEdit));
//		wr.write("");
//		wr.write(newText);
//		wr.close();
//		File index = new File("index");
//		BufferedWriter w = new BufferedWriter(new FileWriter(index));
//		w.write("*edited* " + fileToEdit.getName());
//		w.close();
//	}
	public static String shaCreator(String in) {
		try {
	        // getInstance() method is called with algorithm SHA-1
	        MessageDigest md = MessageDigest.getInstance("SHA-1");

	        // digest() method is called
	        // to calculate message digest of the input string
	        // returned as array of byte
	        byte[] messageDigest = md.digest(in.getBytes());

	        // Convert byte array into signum representation
	        BigInteger no = new BigInteger(1, messageDigest);

	        // Convert message digest into hex value
	        String hashtext = no.toString(16);

	        // Add preceding 0s to make it 32 bit
	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }

	        // return the HashText
	        return hashtext;
	    }

	    // For specifying wrong message digest algorithms
	    catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException(e);
	    }
	}
}