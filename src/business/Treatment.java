package business;

import java.util.Date;

public abstract class Treatment {
    private Date start;
    private int finish;

    public Treatment( Date start, int finish) {
        this.start = start;
        this.finish = finish;
    }
}
