package data.model;

public class Error {
	private String messege;
	private boolean error;
	
	public Error(String mes) {
		messege = mes;
		error = true;
	}
	public Error() {
		error = true;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	
}
