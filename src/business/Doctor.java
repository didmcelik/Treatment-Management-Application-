package business;

import exceptions.AnalysisNotFoundException;
import exceptions.PatientNotFoundException;

import java.util.ArrayList;
import java.util.Date;

public class Doctor<T extends Analysis>{
    private int id;

    public Doctor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * To list all patients under his/her care
     * @param examinations
     * @return
     */
    public ArrayList<Patient> seeAllPatients(ExaminationManager manager){
        ArrayList<Patient> myPatients = new ArrayList<>();
        for(Examination examination:manager.getExaminations()){
            if(examination.getDoctor().getId()==this.id){
                myPatients.add(examination.getPatient());

            }
        }
        return myPatients;
    }
    /**
     * To ask for blood test and/or radiology after the examination
     * @param test
     * @param examinations
     * @param patient
     */
    public void askAnalyze(T test,ExaminationManager manager,Patient patient){
            for(Examination examination:manager.getExaminations()){
                if(examination.getDoctor().getId()==this.id){
                    if(examination.getPatient().equals(patient)){
                        examination.setAnalysis(test);
                    }
                }
            }

    }
    /**
     * To write necessary prescription after the examination 
     * @param examination
     * @param prescription
     */
    public void writePrescription(Examination examination,String prescription){
        examination.setPrescription(prescription);

    }
    /**
     * To decide on a surgery after the examination,
     * @param examination
     * @param doctors
     * @param surgeryDate
     * @param stayNum
     */
    public String decideSurgery( Examination examination, ArrayList<Doctor> doctors, Date surgeryDate,int stayNum){

        if(this.getClass().equals("Surgeon")){
            Treatment surgery = new Surgery((Surgeon) this,surgeryDate,stayNum);
            examination.setTreatment(surgery);
        }
        else{
            for (Doctor doctor:doctors) {
                if (doctor.getClass().equals("Surgeon")) {
                    Treatment surgery = new Surgery((Surgeon) doctor,surgeryDate,stayNum);
                    examination.setTreatment(surgery);
                    break;
                }

            }
        }
        return visit(stayNum,examination.getPatient());


    }
    /**
     * decide on a therapy after the examination,
     * @param examination
     * @param date
     * @param stayNum
     */
    
    public void decideThreapy( Examination examination, Date date,int stayNum){
       Treatment threapy = new Therapy(date,stayNum);
       examination.setTreatment(threapy);
       
    }
    /**
     * To see the patients he/she will see that day
     * @param examinations
     * @param thatDay
     * @return
     */
    public ArrayList<Patient> seeThatDayPatients(ExaminationManager manager,Date thatDay){
        ArrayList<Patient> patients = new ArrayList<>();
        for(Examination examination:manager.getExaminations()){
            if((examination.getDoctor().getId()==this.id) && (thatDay.equals(examination.getDate()))){
            	
                patients.add(examination.getPatient());
            }
        }
        return patients;
    }
    /**
     * To list all the patients examined
     * @param examinations
     * @return
     */
    public ArrayList<Patient> seeAllExaminedPatient(ExaminationManager manager){
            ArrayList<Patient> oldPatient = new ArrayList<>();
            for(Examination examination:manager.getExaminations()){
            	if(examination.getDoctor().getId() == this.id) {
	                int count = 0;
	                Patient patient = examination.getPatient();
	                int id = patient.getId();
	                for(Examination checkExamination:manager.getExaminations()){
	                    int checkId = checkExamination.getPatient().getId();
	                    if(id == checkId){
	                        count++;
	                    }
	                }
	                if(count>2){
	                    if(!oldPatient.contains(patient)) {
	                        oldPatient.add(examination.getPatient());
	                    }
	                }
            	}
            }
            return oldPatient;
        
    }
    /**
     * search any patient that examined in the past
     * @param oldPatient
     * @param patient
     * @return
     * @throws PatientNotFoundException
     */
    public boolean searchOldPatient(ExaminationManager manager,Patient patient) throws PatientNotFoundException {
    	ArrayList<Patient> oldPatient = seeAllExaminedPatient(manager);
    	if (oldPatient.contains(patient)){
            return true;
        }
        else{
            throw new PatientNotFoundException("Patient does not exist.");
        }
    }
    /**
     * search and/or see the results of analysis
     * @param examinations
     * @param patient
     * @return
     * @throws AnalysisNotFoundException
     */
    public ArrayList<Analysis> sreachAnalaysis(ExaminationManager manager,Patient patient) throws AnalysisNotFoundException {
        for(Examination examination:manager.getExaminations()){
            if(examination.getPatient().getId()==patient.getId()){
            	if(examination.getDoctor().getId() == this.id) {
            		return examination.getAnalysis();
            	}
            }
        }
        return null;
    }
    /**
     *  the doctor should visit
	 *	the patient until he/she is released from the hospital
     * @param stayNum
     * @param patient
     * @return
     */
    public String visit(int stayNum,Patient patient) {
    	return "Doctor " + this.id +", you must visit " + patient.getId() +" for " + stayNum + " days."; 
    }
    /**
     * search any appointed surgery for him/her
     * @param manager Examination manager
     * @return count that doctor surgery number
     */
	public int searchSurgery(ExaminationManager manager) {
		return 0;
	}




}
