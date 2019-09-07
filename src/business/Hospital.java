package business;

import exceptions.PatientNotFoundException;

import java.util.ArrayList;

public class Hospital {
    private ArrayList<Doctor> doctors;
    
    private ArrayList<Patient> patients;
    

    public Hospital(ArrayList<Doctor> doctors, ArrayList<Patient> patients) {
        this.doctors = doctors;
        
        this.patients = patients;
        
    }

    public Hospital() {

        this.doctors = new ArrayList<Doctor>();
        
        this.patients = new ArrayList<>();
        
    }
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

   

    public ArrayList<Patient> getPatients() {
        return patients;
    }

   /**
    * To find doctor by id
    * @param id the source that contains doctor id
    * @return if doctor exist return doctor
    */
    public Doctor findDoctor(int id) {
    	for(Doctor doctor : doctors) {
    		if(doctor.getId() == id) {
    			return doctor;
    		}
    	}
    	return null;
    }
    /**
     * To find patient by id
     * @param id the source that contains patient id
     * @return if doctor exist return patient
     */
    public Patient findPatient(int id) {
    	for(Patient patient : patients) {
    		if(patient.getId() == id) {
    			return patient;
    		}
    	}
    	return null;
    }
    /**
     * To add new doctor
     * @param doctor new doctor that going to be added
     */
    public void addDoctor(Doctor doctor){
        this.doctors.add(doctor);
    }
   /**
    * To add new patient
    * @param patient new patient that going to be added
    */
    public void addPatient(Patient patient){
        this.patients.add(patient);
    }
  

}
