package com.bit.common;

import java.util.Random;

public class Utils {
	
	public static String generaCodigoVerficacion() {
		Random rand = new Random();
		String id = String.format("%04d", rand.nextInt(999999999));
		
		return id;
	}
	
	public static void main (String[] args) {
		
		
		System.out.println("codigo de verificacion: " + Utils.generaCodigoVerficacion());
	}

}
