package business;

import java.util.Random;

public class Analysis {
    private boolean readyState;

    public Analysis() {
       
        this.readyState=isReady();
    }
   
    public void setReadyState(boolean readyState) {
        this.readyState = readyState;
    }
    /**
     * To create ready state
     * @return true if ready, false if not
     */
    public boolean isReady(){
        int temp = new Random().nextInt(2);
        if(temp == 0){
            return false;
        }
        return true;
    }
}
