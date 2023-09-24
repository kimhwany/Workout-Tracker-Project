package workout;

import java.io.*;
import java.util.ArrayList;
import java.util.*;


public class ReadWrite {
	
	private Library library;
	
	private String fileName;
	private String equipmentFile;
	
	public ReadWrite(Library library) {
		this.library = library;
		fileName = "data.dat";
		equipmentFile = "equipmentList.txt";
	}
	
	public void deleteFile(String file) {
		File fileToDelete = new File(file);
        if (fileToDelete.exists()) fileToDelete.delete();
	}

	public void saveObjectFile() {
		deleteFile(fileName);
		
		ArrayList<Workout> list = library.getWorkoutList();
		try {
			FileOutputStream fo = new FileOutputStream(fileName);
			ObjectOutputStream outStream = new ObjectOutputStream(fo);
			for (int i=0; i<list.size(); i++) {
				try {
					outStream.writeObject(list.get(i));

				} catch (NumberFormatException e) {
					System.out.println("Data error");
				}
			}	
			outStream.close();
			fo.close();
		}
		catch(IOException e) {
			System.out.println("IO Exception Save");
		}
	}	
	
	
	public void loadObjectFile() {	
		ArrayList<Workout> list = new ArrayList<Workout>();
		
		try {
			FileInputStream fi = new FileInputStream(fileName);
			ObjectInputStream inStream = new ObjectInputStream(fi);
			try {
				while (true) {
					Workout emp = (Workout)inStream.readObject();
					if (emp!=null) { list.add(emp);}
				}
			} catch (EOFException e) {
				inStream.close();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found");
		} catch (IOException e) {
			System.out.println("IOException Load");
		}
		
		library.setWorkoutList(list);		
	} 
	
	
	public void saveEquipmentFile() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(equipmentFile));
			for(int i = 0; i < library.getEquipmentList().size(); i++) {
				pw.println(library.getEquipmentList().get(i));
			}
		}
		catch(FileNotFoundException e){
			System.out.println("Create file first");
		}
		finally{
			if(pw != null) {
				pw.close();
			}
		}
	}
	
	
	public void loadEquipmentFile() {
		ArrayList<String> equipmentList = new ArrayList<String>();
		try {
			Scanner scan = new Scanner(new File("equipmentList.txt"));
			while(scan.hasNext()) {
				equipmentList.add(scan.next());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("No equipments added yet");
		}
		library.setEquipmentList(equipmentList);
	}

}
