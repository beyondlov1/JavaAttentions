package com.beyond.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnno {
	String driver();

	String url();

	String username();

	String password();
}
