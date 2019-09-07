package business;

import exceptions.AnalysisNotFoundException;

import java.util.ArrayList;
import java.util.Date;

public class Examination <T1 extends Analysis,T2 extends Treatment>{
  
    private Doctor doctor;
    private Patient patient;
    private ArrayList<T1> analysis;
    private T2 treatment;
    private String prescription;
    private Date date;



    public Examination( Doctor doctor, Patient patient,Date date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.analysis = new ArrayList<>();
    }

    public void setAnalysis(T1 analysis) {
        this.analysis.add(analysis);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setTreatment(T2 treatment) {
        this.treatment = treatment;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    
    public String getPrescription() {
        return prescription;
    }
    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }
    

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<T1> getAnalysis() throws AnalysisNotFoundException {
        if(analysis.isEmpty()){
            throw new AnalysisNotFoundException("Analysis does not exist.");
        }
        else{
            return analysis;
        }

    }

    public T2 getTreatment() {
        return treatment;
    }
}
