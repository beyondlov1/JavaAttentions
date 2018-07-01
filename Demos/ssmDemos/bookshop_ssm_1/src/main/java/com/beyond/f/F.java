package com.beyond.f;

public interface F {
	String UPLOADROOT = "files";
	String LOGIN_USER_ID_COOKIE_KEY = "loginUserId";
	String LOGIN_USER_NAME_COOKIE_KEY = "loginUserName";

	Integer ORDER_ACCEPTED = 1;
	Integer ORDER_OWNER_CONFIRMED = 3;
	Integer ORDER_EXCHANGER_CONFIRMED = 5;
	Integer ORDER_COMPLETED = 7;

	Integer ORDER_ACCEPT_WEIGHT = 1;
	Integer ORDER_OWNER_CONFIRM_WEIGHT = 2;
	Integer ORDER_EXCHANGER_CONFIRM_WEIGHT = 4;
}
