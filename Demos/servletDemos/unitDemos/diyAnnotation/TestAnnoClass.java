package com.beyond.test;

public class TestAnnoClass {
	@TestAnno(driver = "driver", url = "url", username = "username", password = "password")
	public void run() {
		try {
			TestAnno testAnno = TestAnnoClass.class.getMethod("run").getAnnotation(TestAnno.class);
			System.out.println(testAnno.driver());

		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
