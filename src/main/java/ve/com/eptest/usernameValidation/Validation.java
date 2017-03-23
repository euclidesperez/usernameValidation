package ve.com.eptest.usernameValidation;

public interface Validation {
	
	public void validateForm(String username) throws Exception;
	
	public Boolean validateReserveWord(String username);
	
	public Boolean validateExist(String username);
	
	public String getRandomUser();
}
