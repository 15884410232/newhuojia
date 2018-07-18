package com.test.util;

import java.util.UUID;

public class UuidUtil {
	public static String getUUID32(){
	    String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
	    System.out
	    .println(uuid);
	    return uuid;
	}
	public static void main(String[] args){
		getUUID32();
		
	}
}
