package business;

import java.util.Random;

public class Radiology extends Analysis {
    private boolean result;

    public Radiology(){
        if(isReady()){
            this.result = testResult();
        }
    }
    /**
     * to create random test result
     * @return result
     */
    public boolean testResult(){
        int temp = new Random().nextInt(2);
        if(temp == 0){
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Radiology [result=" + result + "]\n";
	}
    

}
