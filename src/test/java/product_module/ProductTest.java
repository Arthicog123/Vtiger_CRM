package product_module;


import org.testng.annotations.Test;

import genericUtils.BaseClass;

public class ProductTest extends BaseClass{

	@Test(retryAnalyzer = genericUtils.RetryImplementationClass.class)
	public void productTest() {
		System.out.println("---product test----");
	}
}
