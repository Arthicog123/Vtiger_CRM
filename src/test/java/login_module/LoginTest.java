package login_module;


import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtils.BaseClass;


public class LoginTest extends BaseClass{

	@Test(retryAnalyzer = genericUtils.RetryImplementationClass.class, groups = "smoke")
	public void loginTest() {
		System.out.println("---login test----");

	}

}
