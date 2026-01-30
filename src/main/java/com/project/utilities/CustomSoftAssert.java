package com.project.utilities;

import java.io.IOException;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import com.project.base.BaseClass;

public class CustomSoftAssert extends SoftAssert{
	BaseClass baseClass = new BaseClass();
	@Override
	public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
		try {
			new TestUtils().getScreenshotExtentReport(baseClass.dirName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
