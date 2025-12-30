package contacts_module;


import org.testng.annotations.Test;

import genericUtils.BaseClass;

public class ContactTest extends BaseClass{

	@Test(retryAnalyzer = genericUtils.RetryImplementationClass.class)
	public void contactTest() {
		System.out.println("----contact test----");
	}
}
