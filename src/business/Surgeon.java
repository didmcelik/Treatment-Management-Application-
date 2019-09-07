package business;

import exceptions.AnalysisNotFoundException;

import java.util.ArrayList;

public class Surgeon extends Doctor {
    public Surgeon(int id) {
        super(id);
    }
    
    @Override
    public int searchSurgery(ExaminationManager manager) {
        int count = 0;
    	for(Examination examination:manager.getExaminations()){
           if(examination.getDoctor().getId() == this.getId()) {
        	   if(examination.getTreatment().getClass().equals("Surgery")) {
        		   count++;
        	   }
        	   
           }
        }
        return count;
    }
}
