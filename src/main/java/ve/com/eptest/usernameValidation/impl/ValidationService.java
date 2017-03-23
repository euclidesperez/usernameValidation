package ve.com.eptest.usernameValidation.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import ve.com.eptest.usernameValidation.Validation;

@Service
public class ValidationService implements Validation {

	private static final int _DEFAULT_LENGHT = 6;
	private static final String _USERS = "test.username";
	private static final String _RESERVE_WORDS = "test.reserve";
	private static final String _ALTERNATE_WORDS = "test.altern";
	
	@Autowired
	private Environment env;
	
	public void validateForm(String username) throws Exception {
		
		if(StringUtils.isEmpty(username) || 
				StringUtils.isBlank(username) ||
				(StringUtils.length(username)<_DEFAULT_LENGHT)){
			throw new Exception("Bad lenght");
		}

	}

	public Boolean validateReserveWord(String username) {
		Boolean result = false;
		List<String> reserveWordList = getReserveWordList();
		Iterator<String> itWords = reserveWordList.iterator();
		while (itWords.hasNext()) {
			String word =itWords.next();
			if(StringUtils.contains(username, word)){
				result=true;
				break;
			}
		}
		return result;
	}

	private List<String> getReserveWordList() {
		return fromPropertiesValueToList(this.env.getProperty(_RESERVE_WORDS));
		
	}

	public Boolean validateExist(String username) {
		Boolean result = false;
		List<String> userExistList = getUserExistList();
		Iterator<String> itUser = userExistList.iterator();
		while (itUser.hasNext()) {
			String user =itUser.next();
			if(StringUtils.equalsIgnoreCase(username, user)){
				result=true;
				break;
			}
		}
		
		return result;
	}

	private List<String> getUserExistList() {
		return fromPropertiesValueToList(this.env.getProperty(_USERS));
		
	}
	
	private List<String> fromPropertiesValueToList(String propertiesValue){		
		StrTokenizer strings = StrTokenizer.getCSVInstance(propertiesValue);
		return strings.getTokenList();
	}

	public String getRandomUser() {
		List<String> alterns = getAlternUsername();		
		int index = (new Random()).nextInt(alterns.size());
        return alterns.get(index);
	}

	private List<String> getAlternUsername() {
		return fromPropertiesValueToList(this.env.getProperty(_ALTERNATE_WORDS));
	}

}
