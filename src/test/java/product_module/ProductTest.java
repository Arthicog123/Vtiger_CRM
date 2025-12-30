package product_module;


import org.testng.annotations.Test;

import genericUtils.BaseClass;

public class ProductTest extends BaseClass{
	
	//hi i have made changes in forked aarthi

	@Test(retryAnalyzer = genericUtils.RetryImplementationClass.class)
	public void productTest() {
		System.out.println("---product test----");
	}
}
