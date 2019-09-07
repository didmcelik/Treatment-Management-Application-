package presentation;
import java.text.ParseException;
import java.util.ArrayList;

import business.*;

public class Main {
	 public static void main(String[] args) throws ParseException {
		 Hospital hospital = new Hospital();
		 ExaminationManager manager = new ExaminationManager();
		 ArrayList<String> ids = new ArrayList<>();
		 Doctor doctor = new Doctor(0);
		 Doctor surgeon = new Surgeon(1);
		 ids.add("0");
		 ids.add("1");
		 hospital.addDoctor(doctor);
		 hospital.addDoctor(surgeon);
		 Receptionist receptionist = new Receptionist(hospital);
		 Menu menu = new Menu(manager,hospital,receptionist,ids);
		 while(true) {
			 Menu.MenuStart();
	            if (!menu.ExecuteMenu())
	                break;
		 }
	 }

}
