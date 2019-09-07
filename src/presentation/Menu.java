package presentation;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import business.*;
import exceptions.PatientNotFoundException;

public class Menu {
	private ExaminationManager manager;
	private Hospital hospital;
	private Receptionist receptionist;
	private ArrayList<String> doctorIds;
	private ArrayList<String> patientIds;
	public Menu(ExaminationManager manager, Hospital hospital,Receptionist receptionist,ArrayList<String> doctorIds) {
		this.manager = manager;
		this.hospital = hospital;
		this.doctorIds = doctorIds;
		this.patientIds = new ArrayList<String>();
		this.receptionist = receptionist;
	}
	private static Scanner in = new Scanner(System.in);
	
	
	public static void MenuStart(){		
		System.out.println();
		System.out.println("[1] Add new Doctor.(Program has one default doctor at the starting point)");
        System.out.println("[2] Add new Surgeon Doctor.(Program has one default surgeon doctor at the starting point.");
        System.out.println("[3] I am a recepitionist.");
        System.out.println("[4] I am a doctor.");
        System.out.println("[5] Quit.");
		
	}
	public boolean ExecuteMenu() throws ParseException {
		presentation.MenuExecution menu = new presentation.MenuExecution(hospital,manager,receptionist);
        int cmdId = in.nextInt();
        in.nextLine();
        switch (cmdId) {
            case 1:
            	System.out.println();
            	System.out.println("Please enter a valid id for new Doctor");
            	boolean check = false;
                while (!check) {
                    String input = in.nextLine();
                    if((isInteger(input)) && (!this.doctorIds.contains(input))) {
                    	menu.addDoctor(input);
                    	doctorIds.add(input);
                    	check = true;
                    	
                    }
                    else {
                    	System.out.println();
                    	System.out.println("Doctor ids:\n"+doctorIds.toString());
                    	System.out.println("Please enter a valid id for new Doctor");
                    	check = false;
                    }
                }
                return true;
            case 2:
            	System.out.println();
            	System.out.println("Please enter a valid id for new Doctor");
            	check = false;
                while (!check) {
                    String input = in.nextLine();
                    if((isInteger(input)) && (!this.doctorIds.contains(input))) {
                    	menu.addSurgeon(input);
                    	doctorIds.add(input);
                    	check = true;
                    	
                    }
                    else {
                    	System.out.println();
                    	System.out.println("Doctor ids:\n"+doctorIds.toString());
                    	System.out.println("Please enter a valid id for new Doctor");
                    	check = false;
                    }
                }
                return true;
            case 3:
            	System.out.println();
            	System.out.println("Hello!");
            	System.out.println("Please enter a valid id for new Patient, patient type, doctor id patient wants to see");
            	System.out.println("and date for examination in yyyy-mm-dd format (For example:1,walking case,0,2018-12-11)");
            	System.out.println("Doctor ids:\n"+doctorIds.toString());
            	check = false;
            	while(!check) {
            		String input = in.nextLine();
            		System.out.println("You entered: " + input);
            		String[] inputs = input.split(",");
            		if((isInteger(inputs[0]))&&(!this.patientIds.contains(inputs[0]))&&(isValidPatientType(inputs[1]))&&(isInteger(inputs[2]))&&(this.doctorIds.contains(inputs[2]))&&checkDate(inputs[3])) {
            			System.out.println(menu.addPatient(inputs));            			
            			patientIds.add(inputs[0]);
            			check = true;
            		}
            		else {
            			System.out.println();
                    	System.out.println("Please enter a valid id for new Patient, patient type and doctor id patient wants to see(For example:1,walking case,0,2018-12-11)");
                    	check = false;
            		}
            		
            	}
            	return true;
            case 4:
            	System.out.println();
            	System.out.println("Hello, welcome to Doctor's console.");
            	System.out.println("Please enter your id:");
            	check = false;
            	outerLoop:
            	while(!check) {
            		String input = in.nextLine();
            		System.out.println("You entered: " + input);
            		if((isInteger(input))&&(this.doctorIds.contains(input))) {
            			while (true) {
            	            doctorMenu();
            	            if (!executeDoctor(input,menu))
            	                break outerLoop;
            	        }
            		}
            		else {
            			System.out.println();
            			System.out.println("Doctor ids:\n"+doctorIds.toString());
            			System.out.println("Please enter a valid id.");
            			check = false;
            		}
            	}
            	return true;
            case 5:
            	System.out.println("Goodbye!");
            	return false;
            default:
                System.err.println();
                System.err.println("I do not know what to do for command id " + cmdId);
                System.err.println("Please try again!");
                System.err.println();
                return true;
            	
        }      	
                    
	}
	// to display doctor menu
    private void doctorMenu() {
    	System.out.println();
    	System.out.println("[1] see the patients you will see that day");
        System.out.println("[2] ask for blood test and/or radiology after the examination");
        System.out.println("[3] write necessary prescription after the examination");
        System.out.println("[4] decide on a surgery after the examination");
        System.out.println("[5] list all patients under your care");
        System.out.println("[6] search and/or see the results of analysis");
        System.out.println("[7] list all the patients examined");
        System.out.println("[8] search any patient that examined in the past");
        System.out.println("[9] search any appointed surgery for you");
        System.out.println("[10] Quit.");
    }
    // to execute doctor menu
	private boolean executeDoctor(String id,MenuExecution menu) throws ParseException {
		int cmdId = in.nextInt();
        in.nextLine();
        switch (cmdId) {
            case 1:
            	System.out.println();
            	System.out.println("Please enter which day's patients you want to see(in yyyy-mm-dd format)");
            	boolean check = false;
            	while(!check) {
            		String input = in.nextLine();
            		System.out.println("You entered: " + input);
            		if(checkDate(input)) {
            			System.out.println(menu.seeThatDayPatient( id,input).toString());
            			check = true;
            		}
            		else {
                		System.out.println("Enter a valid date in yyyy-mm-dd format");
                		check = false;
            		}
            	}    		
            	return true;
            case 2:
            	System.out.println();
            	System.out.println("Please enter a patient id and type you want to analyze.(1,blood test");
            	System.out.println("If two analysis you can write two.(1,two)");
            	check = false;
            	while(!check){
            		String input = in.nextLine();
            		String[] inputs = input.split(",");
            		System.out.println("You entered: " + input);
            		if(isInteger(inputs[0]) && (checkAnalysis(inputs[1]))&&(patientIds.contains(inputs[0]))) {
            			menu.askAnalyze(id,inputs);
            			check = true;
            		}
            		else {
            			System.out.println("Please enter a valid id and valid analysis type.");
            			check = false;
            		}
            	}
            	return true;
            case 3:
            	System.out.println();
            	System.out.println("Please enter a patient id and prescription that you want to write(1,two days rest)");
            	check = false;
            	while(!check){
            		String input = in.nextLine();
            		String[] inputs = input.split(",");
            		System.out.println("You entered: " + input);
            		if(isInteger(inputs[0])) {
            			menu.writePrescription(id,inputs);
            			check = true;
            		}
            		else {
            			System.out.println("Please enter a valid id");
            			check = false;
            		}
            	}
            	return true;
            case 4:
            	System.out.println();
            	System.out.println("Enter patient id that you going to decide surgery.");
            	
        		check = false;
            	while(!check){
            		String input = in.nextLine();
            		System.out.println("You entered: " + input);
	        		if((isInteger(input))&&(this.patientIds.contains(input))){
	        			String[] result = menu.printAnalysisResult(id,input);
	        			
	        			if(result[0].equals("false")) {
	        				System.out.println(result[1]);
	        				return true;
	        			}
	        			System.out.println(result[1]);
	        			check = true;
	        		}
	        		else {
	        			System.out.println("Please enter a valid id");
            			check = false;
	        		}
            	}
            	System.out.println();
            	System.out.println("Enter your decision: yes or no");
            	String decision = in.nextLine();
        		System.out.println("You entered: " + decision);
            	check = false;
            	while(!check) {
            		if(decision.equalsIgnoreCase("yes")) {
            			System.out.println();
                    	System.out.println("Enter patient id, surgery date in yyyy-mm-dd format and patient inmate day(3,2018-12-24,4)");
                    	
                    	check = false;
                    	while(!check) {
                    		String surgeryInput = in.nextLine();
                        	String[] surgeryInputs = surgeryInput.split(",");
                        	System.out.println("You entered: " + surgeryInput);
	                    	if((checkDate(surgeryInputs[1])) && (isInteger(surgeryInputs[2]))&& isInteger(surgeryInputs[0]) && (patientIds.contains(surgeryInputs[0]))) {
	                    		System.out.println(menu.decideSurgery(id,surgeryInputs));
	                    		check = true;
	                    	}
	                    	else {
	                    		System.out.println("Please enter valid id, date and day number.");
	                    		check = false;
	                    	}
                    	}
                    	check = true;
            		}
            		else if(decision.equalsIgnoreCase("no")) {
            			System.out.println();
                    	System.out.println("Enter patient id,threapy date in yyyy-mm-dd format and patient inmate day(2,2018-12-24,4)");
                    	
                    	check = false;
                    	while(!check) {
                    		String threapyInput = in.nextLine();
                        	String[] threapyInputs = threapyInput.split(",");
                        	System.out.println("You entered: " + threapyInput);
	                    	if((checkDate(threapyInputs[1])) && (isInteger(threapyInputs[2])) && isInteger(threapyInputs[0]) && (patientIds.contains(threapyInputs[0]))) {
	                    		menu.decideThreapy(id,threapyInputs);
	                    		System.out.println("Patient will be treated by threapy.");
	                    		check = true;
	                    	}
	                    	else {
	                    		System.out.println("Please enter valid id, date and day number.");
	                    		check = false;
	                    	}
                    	}
                    	check = true;
            		}
            		else {
            			System.out.println("Please enter yes or no.");
                		check = false;
            		}
            	}
            	return true;
            case 5:
            	System.out.println();
            	System.out.println(menu.seeAllPatient(id).toString());
            	return true;
            case 6:
            	System.out.println();
            	System.out.println("Please enter a patient id that you want to see anlaysis.)");
            	check = false;
            	while(!check){
            		String input = in.nextLine();
            		
            		System.out.println("You entered: " + input);
            		if(isInteger(input)) {
            			System.out.println(menu.searchAnalysis(id,input));
            			check = true;
            		}
            		else {
            			System.out.println("Please enter a valid id");
            			check = false;
            		}
            	}
            	return true;
            case 7:
            	System.out.println();
            	System.out.println(menu.seeAllExaminedPatient(id).toString());
            	return true;
            case 8:
            	System.out.println();
            	System.out.println("Please enter patient id that you want to search.");
            	check = false;
            	while(!check){
            		String input = in.nextLine();
            		
            		System.out.println("You entered: " + input);
            		if(isInteger(input)) {
            			try {
							System.out.println(menu.searchOldPatient(id,input,patientIds.contains(input)));
						} catch (PatientNotFoundException e) {
							System.out.println(e.getMessage());
							
						}
            			check = true;
            		}
            		else {
            			System.out.println("Please enter a valid id");
            			check = false;
            		}
            	}
            	return true;
            case 9:
            	System.out.println();
            	System.out.println(menu.searchSurgery(id));
            case 10:
            	System.out.println("You exit doctor console.");
            	return false;
            default:
                System.err.println();
                System.err.println("I do not know what to do for command id " + cmdId);
                System.err.println("Please try again!");
                System.err.println();
                return true;
        }
		
	}
	//to check analysis type
	private boolean checkAnalysis(String check) {
		if((check.equalsIgnoreCase("blood test")) || (check.equals("radiology")) || (check.equalsIgnoreCase("two"))) {
			return true;
		}
		return false;
	}
	 // to check if input is valid	
    private  boolean checkDate (String date){
        String[] tempArray = date.split("-");
        int len =tempArray.length;
        int[] intAr = new int[len];
        int i =0;
        int j =len-1;
       
        boolean check = true;
		if(len!=3) {
		        return false;	
		        }
        while (i<=j) {
            if(isInteger(tempArray[i])){
                intAr[i] = Integer.parseInt(tempArray[i]);

                i++;
                check = true;

            }

            else{

                check = false;
            }
        }
        if(!(isValidMonth(intAr[1])&&isValidDay(intAr[2])&&isValidYear(intAr[0]))){

            check = false;

        }
        return check;
    }
	// to check if year is valid
    private boolean isValidYear(int year) {
    	if(year == 2018) {
    		return true;
    	}
    	return false;
    }
    // to check if month is valid
    private  boolean isValidMonth(int month){
        if((1 <= month) &&(month <= 12)){
            return true;
        }
        
        return false;
        
    }
    // to check if day is valid
    private  boolean isValidDay(int day){
        if((1 <= day) &&(day <= 31)){
            return true;
        }
  
        return false;
        
    }
    //To check patient type
	private boolean isValidPatientType(String check) {
		if(check.equalsIgnoreCase("walking case")||(check.equalsIgnoreCase("emergency"))||check.equalsIgnoreCase("inmate")) {
			return true;
		}
		else {
			return false;
		}
	}
	//to check if the input integer
    private  boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        if (str.isEmpty()) {
            return false;
        }
        if(str.charAt(0)=='-'){
            return false;

        }
        for (int i =0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }

        }
        return true;
    }

  

}
