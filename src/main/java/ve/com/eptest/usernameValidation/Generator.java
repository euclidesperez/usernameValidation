package ve.com.eptest.usernameValidation;

import java.util.List;

public interface Generator {

	public String capitalLetter(String username);
	
	public String duplicateUsername(String username);
	
	public List<String> consecutive(String username, Integer size);
	
	public String defaultGuest(String username);
	
	public String reverse(String username);
	
}
