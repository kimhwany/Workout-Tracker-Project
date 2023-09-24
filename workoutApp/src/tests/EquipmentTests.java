package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import workout.Library;

class EquipmentTests {

	Library library = new Library();
	
	@Test
	void testSetEquipmentList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Treadmill");
		list.add("Weight bench");
		list.add("Indoor bike");
		library.setEquipmentList(list);
		assertEquals(list, library.getEquipmentList());
	}

	@Test
	void testAddEquipment() {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("Treadmill");
		library.addEquipment("Treadmill");
		assertEquals(temp, library.getEquipmentList());
	}
	
	@Test
	void testAddEquipment2() {
		library.addEquipment("Treadmill");
		File file = new File("equipmentList.txt");
		assertTrue(file.exists());
		file.delete();
	}

}