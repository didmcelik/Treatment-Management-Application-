package business;

import java.util.ArrayList;
import java.util.Random;

public class BloodTest extends Analysis {
    private double glucose;
    private double urea;
    private double hemoglobin;

    private double randomDouble(double start,double end){
        double random = new Random().nextDouble();
        double result = start + (random * (end - start));
        return result;
    }



    public BloodTest() {
        if(isReady()){
            this.glucose = randomDouble(20,150);//70-105
            this.urea = randomDouble(0,100);//15-55
            this.hemoglobin = randomDouble(0,10);//4-6
        }

    }
    

    @Override
	public String toString() {
		return "BloodTest [glucose=" + glucose + ", urea=" + urea + ", hemoglobin=" + hemoglobin + "]\n";
	}



	public double getGlucose() {
        return glucose;
    }

    public double getUrea() {
        return urea;
    }

    public double getHemoglobin() {
        return hemoglobin;
    }


}
