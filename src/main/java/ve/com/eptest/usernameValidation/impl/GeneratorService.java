package ve.com.eptest.usernameValidation.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ve.com.eptest.usernameValidation.Generator;

@Service
public class GeneratorService implements Generator {

	public String capitalLetter(String username) {
		String result;
		if(Character.isUpperCase(username.charAt(0))){
			result = characterToUppeCase(username,username.length()-1);			
		}else{
			username = characterToUppeCase(username,0);
			result = characterToUppeCase(username,username.length()-1);
		}
		return result;
	}
	
	private String characterToUppeCase(String string, int position){
		char[] charArray = string.toCharArray();			
		charArray[position] = Character.toUpperCase(charArray[position]);
		return String.valueOf(charArray);	
	}
	
	public String duplicateUsername(String username) {
		String result;
		result = username.concat(characterToUppeCase(username,0));
		return result;
	}

	public List<String> consecutive(String username, Integer size) {
		List<String> options = new ArrayList<String>();
		for (int i = 1; i <= size; i++) {
			options.add(username.concat(String.valueOf(i)));
		}
		return options;
	}

	public String defaultGuest(String username) {
		String result;
		result = "guest".concat(characterToUppeCase(username, 0));
		return result;
	}

	public String reverse(String username) {		
		return StringUtils.reverse(username);
	}

}
