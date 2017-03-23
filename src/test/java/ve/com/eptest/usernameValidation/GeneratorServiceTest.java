package ve.com.eptest.usernameValidation;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import ve.com.eptest.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class GeneratorServiceTest {
	private static final String ZEREPUE = "zerepue";
	private static final String GUEST_EUPEREZ = "guestEuperez";
	private static final String EUPEREZ_EUPEREZ = "euperezEuperez";
	private static final String EUPERE_Z = "EupereZ";
	private static String _USERNAME = "euperez";
	private static Integer _CONSECUTIVE_SIZE = 10;
	
	
	@Autowired
	private Generator generatorService;
	
	@Test
	public void testCapitalLetter(){
		String result=this.generatorService.capitalLetter(_USERNAME);
		Assert.assertEquals(EUPERE_Z, result);
	}
	
	@Test
	public void testDuplicateUsername(){
		String result=this.generatorService.duplicateUsername(_USERNAME);
		Assert.assertEquals(EUPEREZ_EUPEREZ, result);
	}
	
	@Test 
	public void testReverse(){
		String result=this.generatorService.reverse(_USERNAME);
		Assert.assertEquals(ZEREPUE, result);
	}
		
	@Test 
	public void testDefaultGuest(){
		String result=this.generatorService.defaultGuest(_USERNAME);
		Assert.assertEquals(GUEST_EUPEREZ, result);
	}
	
	@Test
	public void testConsecutive(){
		List<String> result = this.generatorService.consecutive(_USERNAME, _CONSECUTIVE_SIZE);
		Assert.assertNotNull(result);
		Assert.assertEquals(_CONSECUTIVE_SIZE.intValue(), result.size());
		for (String string : result) {
			System.out.println(string);
		}
	}
}
