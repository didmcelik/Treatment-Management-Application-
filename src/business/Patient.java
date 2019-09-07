package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public abstract class Patient {
    private int id;
    

    private Hospital hospital;

 


	public Patient(int id, Hospital hospital) {
        this.id = id;
        
        this.hospital = hospital;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id;
    } 
    public int getId() {
        return id;
    }

	@Override
	public String toString() {
		return "Patient [id=" + id + "]";
	}
    



  


}
