package business;

import java.util.ArrayList;

public class ExaminationManager {
  private ArrayList<Examination> examinations;
  
  public ExaminationManager(ArrayList<Examination> examinations) {

	this.examinations = examinations;
  }
  public ExaminationManager() {

	this.examinations = new ArrayList<Examination>();
 }
  /**
   * To add new examination to examinations list
   * @param examination new examination
   */
  public void addExamination(Examination examination){
        this.examinations.add(examination);
  }
  public ArrayList<Examination> getExaminations() {
        return examinations;
  }
 

}
