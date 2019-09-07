package presentation;

import java.util.ArrayList;
import java.util.Date;
import java.text.*;

import business.*;
import exceptions.AnalysisNotFoundException;
import exceptions.PatientNotFoundException;

public class MenuExecution {
	private Hospital hospital;
	private ExaminationManager manager;
	private Receptionist receptionist;
	public MenuExecution(Hospital hospital, ExaminationManager manager, Receptionist receptionist) {
		this.hospital = hospital;
		this.manager = manager;
		this.receptionist = receptionist;
	}
	/**
	 * To add new doctor
	 * @param id new doctor's unique id
	 */
	public void addDoctor(String id) {
		int intId = Integer.parseInt(id);
		Doctor doctor = new Doctor(intId);
		hospital.addDoctor(doctor);
		
	}
	/**
	 * To add new surgeon doctor
	 * @param id new surgeon doctor's unique id
	 */
	public void addSurgeon(String id) {
		int intId = Integer.parseInt(id);
		Doctor surgeon = new Surgeon(intId);
		hospital.addDoctor(surgeon);
	}
	/**
	 * Add new patient and create examination for him/her
	 * @param inputs patient's information
	 * @return patient and examination information
	 * @throws ParseException
	 */
	public String addPatient(String[] inputs) throws ParseException {
		int patientId =Integer.parseInt(inputs[0]);
		String type = inputs[1];
		Patient patient = createPatient(type,patientId);
		receptionist.addPatient(patient);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = inputs[3];
		Date examinationDate = format.parse(dateString);
		Doctor doctor =findDoctor(Integer.parseInt(inputs[2]));
		Examination examination = new Examination(doctor,patient,examinationDate);
		this.manager.addExamination(examination);
		return "Patient who id's " + patientId + " your examination date is on " + dateString + " and your doctor id is " + inputs[2] +"."; 
	}
	/**
	 * See the patients he/she will see that day 
	 * @param id doctor id
	 * @param date check date to find that day's patient
	 * @return
	 * @throws ParseException
	 */
	public ArrayList<Patient> seeThatDayPatient(String id, String date) throws ParseException{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date checkDate = format.parse(date);
		Doctor doctor = findDoctor(Integer.parseInt(id));
		return doctor.seeThatDayPatients(manager, checkDate);
		
	}
	/**
	 * Ask for blood test and/or radiology after the examination
	 * @param id doctor id
	 * @param inputs analysis information
	 */
	public void askAnalyze(String id,String[] inputs) {
		Doctor doctor = findDoctor(Integer.parseInt(id));
		Patient patient = findPatient(Integer.parseInt(inputs[0]));
		if(inputs[1].equalsIgnoreCase("blood test")) {
			Analysis test = new BloodTest();
			doctor.askAnalyze(test, manager, patient);
		}
		else if(inputs[1].equalsIgnoreCase("radiology")) {
			Analysis test = new Radiology();
			doctor.askAnalyze(test, manager, patient);
		}
		else {
			Analysis test = new BloodTest();
			doctor.askAnalyze(test, manager, patient);
			Analysis testRadio = new Radiology();
			doctor.askAnalyze(testRadio, manager, patient);
		}
	}
	/**
	 * write necessary prescription after the examination
	 * @param id doctor id
	 * @param inputs prescription information
	 */
	public void writePrescription(String id,String[] inputs) {
		Doctor doctor = findDoctor(Integer.parseInt(id));
		Patient patient = findPatient(Integer.parseInt(inputs[0]));
		for(Examination examination:manager.getExaminations()){
            if(examination.getDoctor().getId()==Integer.parseInt(id)){
                if(examination.getPatient().equals(patient)){
                    examination.setPrescription(inputs[1]);
                }
            }
        }		
	}
	/**
	 * list all patients under his/her care
	 * @param id doctor id
	 * @return patients
	 */
	public ArrayList<Patient> seeAllPatient(String id){
		Doctor doctor = findDoctor(Integer.parseInt(id));
		return doctor.seeAllPatients(manager);
	}
	/**
	 * search and/or see the results of analysis
	 * @param id doctor id
	 * @param input patient id to find her/him analysis
	 * @return analysis as string
	 */
	public String searchAnalysis(String id, String input) {
		Doctor doctor = findDoctor(Integer.parseInt(id));
		Patient patient = findPatient(Integer.parseInt(input));
		try {
			return doctor.sreachAnalaysis(manager, patient).toString();
			
		} catch (AnalysisNotFoundException e) {
			return e.getMessage();
		}
		
	}
	/**
	 * To print analysis result
	 * @param id doctor id
	 * @param input patient id to find her/him analysis
	 * @return analysis result as String
	 */
	public String[] printAnalysisResult(String id,String input) {
		String[] output = new String[2];
		Doctor doctor = findDoctor(Integer.parseInt(id));
		
		Patient patient = findPatient(Integer.parseInt(input));
	
		for(Examination examination:manager.getExaminations()){
			System.out.println(examination.getPatient().getId()==patient.getId());
            if(examination.getDoctor().getId()==doctor.getId()){
                if(examination.getPatient().getId()==patient.getId()){
                    try {
                    	output[0] = "true";
						output[1] = examination.getAnalysis().toString();
						return output;
					} catch (AnalysisNotFoundException e) {
						output[1] = e.getMessage();
						output[0] = "false";
					}
                }
            }
        }
		return output;
		
	}
	/**
	 * To create surgery based on analysis result
	 * @param id doctor id
	 * @param surgeryInputs surgery informations
	 * @return surgery information as string
	 * @throws ParseException
	 */
	public String decideSurgery(String id,String[] surgeryInputs) throws ParseException {
		Doctor doctor = findDoctor(Integer.parseInt(id));
		Patient patient = findPatient(Integer.parseInt(surgeryInputs[0]));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(surgeryInputs[1]);
		for(Examination examination:manager.getExaminations()){
            if(examination.getDoctor().getId()==doctor.getId()){
                if(examination.getPatient().equals(patient)){
                	return doctor.decideSurgery(examination,hospital.getDoctors(),date,Integer.parseInt(surgeryInputs[2]));
                }
            }
		}
		return  null;
	}
	/**
	 * If surgery decision is no the patient get theapy as treatment
	 * @param id doctor id
	 * @param threapyInputs theapy informantion
	 * @throws ParseException
	 */
	public void decideThreapy(String id, String[] threapyInputs) throws ParseException {
		Doctor doctor = findDoctor(Integer.parseInt(id));
		Patient patient = findPatient(Integer.parseInt(threapyInputs[0]));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(threapyInputs[1]);
		for(Examination examination:manager.getExaminations()){
            if(examination.getDoctor().getId()==doctor.getId()){
                if(examination.getPatient().equals(patient)){
                	doctor.decideThreapy(examination,date,Integer.parseInt(threapyInputs[2]));
                }
            }
		}
	}
	/**
	 * search any patient that examined in the past
	 * @param id doctor id 
	 * @param input old patient id
	 * @param check true if patient id is in patient id list or false
	 * @return
	 * @throws PatientNotFoundException
	 */
	public String searchOldPatient(String id,String input,boolean check) throws PatientNotFoundException {
		if(!check) {
			throw new PatientNotFoundException("Patient does not exist.");
		}
		else {
			Doctor doctor = findDoctor(Integer.parseInt(id));
			Patient patient = findPatient(Integer.parseInt(input));
			try {
				return Boolean.toString(doctor.searchOldPatient(manager, patient));
			}
			catch(PatientNotFoundException e) {
				return e.getMessage();
			}
		}
		
	}
	/**
	 * list all the patients examined
	 * @param id doctor id
	 * @return patients
	 */
	public ArrayList<Patient> seeAllExaminedPatient(String id){
		Doctor doctor = findDoctor(Integer.parseInt(id));
		return doctor.seeAllExaminedPatient(manager);
	}
	/**
	 * search any appointed surgery for him/her
	 * @param id doctor id
	 * @return surgery information as string
	 */
	public String searchSurgery(String id) {
		Doctor doctor = findDoctor(Integer.parseInt(id));
		return "You have " + doctor.searchSurgery(manager) + " appointed surgery.";
	}
	private Patient findPatient(int id) {
		return hospital.findPatient(id);
	}
	private Doctor findDoctor(int id) {
		return hospital.findDoctor(id);
	}
	private Patient createPatient(String type,int id) {
		if(type.equalsIgnoreCase("walking case")) {
			Patient patient = new WalkingCase(id,this.hospital);
			return patient;
		}
		else if(type.equalsIgnoreCase("inmate")) {
			Patient patient = new Inmate(id,this.hospital);
			return patient;
		}
		else {
			Patient patient = new Emergency(id,this.hospital);
			return patient;
		}
	}

}
