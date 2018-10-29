package program;
/**
 * File name: PushFileInfo.java
 * ==================================================
 * This class writes information about product groups
 * and their products from the program into files.
 */
import java.io.*;
import java.util.ArrayList;

public class PushFileInfo {
	
	/**
	 * This method creates file (or overwrites an existing one in case if its name was given to this method) 
	 * with the given name and writes there information from the given ArrayList.
	 * @param fileName name of the file
	 * @param list ArrayList with information
	 * @throws IOException
	 */
	public static <T> void writeToFile(String fileName, ArrayList<T> list) throws IOException {
		BufferedWriter bWriter = null;
		FileWriter fWriter = null;
		fWriter = new FileWriter(fileName);
		bWriter = new BufferedWriter(fWriter);
		for (int i = 0; i < list.size(); i++) {
			bWriter.write(list.get(i).toString());
			bWriter.newLine();
		}
		bWriter.close();
	}
}
