package ve.com.eptest.usernameValidation;

import java.util.Iterator;

import org.apache.commons.lang3.text.StrTokenizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Assert;
import ve.com.eptest.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserValidationServiceTest {
	
	
	private static final String _EUPEREZ = "euperez";
	private static final String _NULL_USERNAME = null;
	private static final String _EUPEREZ_NEW = "eperezc";
	private static final String _RESERVE_WORD = "goddess";
	private static final String _MULTIPLE_USERS = "emmflores,eucperez,jhonny,jhonnathan";
	
	@Autowired
	private UserValidation userValidationService;
	
	@Test(expected = Exception.class)  
	public void testCheckUsernameNull() throws Exception{
		basicCheckTest(_NULL_USERNAME);
	}
	
	@Test
	public void testCheckUsername() throws Exception{
		basicCheckTest(_EUPEREZ_NEW);
			}
	
	@Test
	public void testCheckUsernameExist() throws Exception{
		basicCheckTest(_EUPEREZ);
		
	}
	
	@Test
	public void testCheckUsernameReserveWord() throws Exception{
		basicCheckTest(_RESERVE_WORD);		
	}
	
	@Test
	public void testMultipleUsers() throws Exception{
		StrTokenizer strings = StrTokenizer.getCSVInstance(_MULTIPLE_USERS);
		Iterator<String> it = strings.getTokenList().iterator();
		while (it.hasNext()) {
			String username = (String) it.next();
			basicCheckTest(username);			
		}
	}
	
	
	private void basicCheckTest(String username) throws Exception {
		Result checkUsername = new Result();
		
		checkUsername = this.userValidationService.checkUsername(username);
		Assert.assertNotNull(checkUsername);
		if(checkUsername.getSuccess()){
			Assert.assertNull(checkUsername.getOptionsList());	
		}else{
			Assert.assertNotNull(checkUsername.getOptionsList());
			Assert.assertFalse(checkUsername.getOptionsList().isEmpty());
			System.out.println("Option List");
			for (String option : checkUsername.getOptionsList()) {				
				System.out.println(option);
			}
		}
		
	}
	

}
