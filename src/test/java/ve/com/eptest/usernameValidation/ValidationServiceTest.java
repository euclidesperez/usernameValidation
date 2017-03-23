package ve.com.eptest.usernameValidation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Assert;
import ve.com.eptest.config.AppConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ValidationServiceTest {

	private static final String _EUPEREZ = "euperez";
	private static final String _NEW_USER = "jduque";
	private static final String _EMPTY = null;
	private static final String _USERNAME_BAD_LENGHT = "dan";
	private static final String _USERNAME_RESERVE_WORD = "goddess";
	@Autowired
	private Validation validationService;
	
	@Test
	public void testValidateExist(){
		Boolean validateExist = this.validationService.validateExist(_EUPEREZ);
		Assert.assertTrue(validateExist);
	}
	
	@Test 
	public void testValidateNotExist(){
		Boolean validateExist = this.validationService.validateExist(_NEW_USER);
		Assert.assertFalse(validateExist);
	}
	
	@Test 
	public void testValidateForm() throws Exception{
		this.validationService.validateForm(_EUPEREZ);
		Assert.assertTrue(true);
	}
	
	@Test(expected=Exception.class)
	public void testValidateFormNull() throws Exception{
		this.validationService.validateForm(null);
		Assert.assertTrue(true);
	}
	
	@Test(expected=Exception.class)
	public void testValidateFormEmpty() throws Exception{
		this.validationService.validateForm(_EMPTY);
		Assert.assertTrue(true);
	}
	
	
	@Test(expected=Exception.class)
	public void testValidateFormBadLenght() throws Exception{
		this.validationService.validateForm(_USERNAME_BAD_LENGHT);
		Assert.assertTrue(true);
	}
	
	@Test
	public void testValidateReserveWord(){
		Boolean validateExist = this.validationService.validateReserveWord(_USERNAME_RESERVE_WORD);
		Assert.assertTrue(validateExist);
	}
	
	@Test 
	public void testValidateReserveWordNotIncluded(){
		Boolean validateExist = this.validationService.validateReserveWord(_EUPEREZ);
		Assert.assertFalse(validateExist);
	}
	
	@Test 
	public void testGetRandomUser(){
		String user = this.validationService.getRandomUser();
		Assert.assertNotNull(user);
		Assert.assertFalse(user.isEmpty());
		System.out.println(user);
	}
	
	
}
