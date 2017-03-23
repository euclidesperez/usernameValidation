package ve.com.eptest.usernameValidation;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7496074530797012557L;

	private Boolean  success;
	
	private List<String> optionsList;
	
	public Result(){
		this.success = true;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public List<String> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<String> optionsList) {
		this.optionsList = optionsList;
	}
	
	
	
}
