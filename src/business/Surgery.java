package business;

import java.util.Date;

public class Surgery  extends Treatment{
    private Surgeon surgeon;


    public Surgery( Surgeon surgeon, Date start,int finish) {
        super(start,finish);
        this.surgeon = surgeon;
    }
}
