package organization_module;

import org.testng.annotations.Test;

import genericUtils.BaseClass;

public class OrganizationTest extends BaseClass{
	
	@Test(retryAnalyzer = genericUtils.RetryImplementationClass.class)
	public void orgTest() {
		System.out.println("---org test----");
	}
}

