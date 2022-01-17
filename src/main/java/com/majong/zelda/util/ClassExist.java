package com.majong.zelda.util;

public class ClassExist {
	public static boolean isClassExist(String name) {
		boolean exist=true;
		try {
			Class.forName(name);
		}catch(Exception e) {exist=false;}
		return exist;
	}
}
