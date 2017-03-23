package ve.com.eptest.usernameValidation.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ve.com.eptest.usernameValidation.Generator;
import ve.com.eptest.usernameValidation.Result;
import ve.com.eptest.usernameValidation.UserValidation;
import ve.com.eptest.usernameValidation.Validation;

@Component
public class UserValidationService implements UserValidation{
	
	private static final String _VISITOR = "visitor";

	@Autowired
	private Validation validationService;
	
	@Autowired
	private Generator generatorService;
	
	@Autowired
	private Environment ev;
	
	public Result checkUsername(String username) throws Exception {
		Result result  = new Result();
				
		boolean validation = validateUsername(username);
		
		if(!validation){
			List<String> options = generateOptions(username);
			result.setOptionsList(options);
			result.setSuccess(validation);
		}
		return result;
	}

	private boolean validateUsername(String username) throws Exception {
		
		boolean validation =true;
		
		this.validationService.validateForm(username);
		
		if(this.validationService.validateExist(username)){
			validation=false;
		}
		
		if(this.validationService.validateReserveWord(username)){
			username = getNewUsername();
			validation=false;
		}
		return  validation;
	}

	private String getNewUsername() {
		return this.validationService.getRandomUser();
	}

	private List<String> generateOptions(String username) throws Exception {
		List<String> options = new ArrayList<String>();
		
		String option = this.generatorService.capitalLetter(username);		
		validateAndAddOption(options, option);
		
		option = this.generatorService.defaultGuest(username);
		validateAndAddOption(options, option);
		
		option = this.generatorService.duplicateUsername(username);
		validateAndAddOption(options, option);
		
		option = this.generatorService.reverse(username);
		validateAndAddOption(options, option);
		
		List<String> consecutives = this.generatorService.consecutive(username, 10);
		for (String consecutive : consecutives) {
			validateAndAddOption(options, consecutive);
		}
		return options;
	}

	private void validateAndAddOption(List<String> options, String option) throws Exception {
		if(validateUsername(option)){
			options.add(option);			
		}
	}
	
	

}
