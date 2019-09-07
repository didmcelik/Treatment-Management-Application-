package business;

public class Receptionist {
	private Hospital hospital;

	public Receptionist(Hospital hospital) {
		this.hospital = hospital;
	}
	/**
	 * To add new patient
	 * @param patient new patient that going to be added
	 */
	public void addPatient(Patient patient) {
		this.hospital.addPatient(patient);
	}
	
	
	
}

