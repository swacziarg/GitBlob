import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class Index {
	HashMap<String, Blob> blobs = new HashMap<String, Blob>();
	FileWriter ike;
//	String location;
	File Index;
	
	public Index() throws IOException {
		File folder = new File("objects");
		folder.mkdir();
		File newIndex = new File("index");
		newIndex.createNewFile();
	}
	
//	public void initialize() {
//		
//	}
	
	public void add(String input) throws IOException {
		String name = input;
		Blob blob = new Blob(name);
		blobs.put(input, blob);
		updateIndex();
	}

	public void remove(String input) throws IOException {
		String name = input;
		Path p = Paths.get(".\\objects\\"+blobs.get(input).getSha());
		Files.delete(p);
		
		blobs.remove(input);
		updateIndex();
	}
	
	//makes the index file
	public void updateIndex() throws IOException {
//		new FileWriter("index.txt", false).close();
		FileWriter ike = new FileWriter("index");
		int c = 0;
		for(String key : blobs.keySet()) {
			if (c!=0) {
				ike.write("\n"+key+" : "+blobs.get(key).getSha());
				
			}
			else {
				ike.write(key+" : "+blobs.get(key).getSha());
				c++;
			}
		}
		ike.close();
	}
}