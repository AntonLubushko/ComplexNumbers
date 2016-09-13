package entity;

public class NoSuchComplexException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public void printStackTrace() {
		super.printStackTrace();
		System.err.println("Nesootvetstvie complexnomu chislu");
		
	}

}
