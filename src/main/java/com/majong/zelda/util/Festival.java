package com.majong.zelda.util;

import java.util.Date;

public class Festival {
	private static final Date NEWYEARSEVE[]= {
			new Date(2022-1900,1-1,31),
			new Date(2023-1900,1-1,21),
			new Date(2024-1900,2-1,9),
			new Date(2025-1900,1-1,28),
			new Date(2026-1900,2-1,16),
			new Date(2027-1900,2-1,5),
			new Date(2028-1900,1-1,25),
			new Date(2029-1900,2-1,12),
			};
	public static boolean isLunarSpringFestival(Date date) {
		long datetime=date.getTime();
		for(int i=0;i<8;i++) {
			long newyeartime=NEWYEARSEVE[i].getTime();
			if(datetime>newyeartime&&datetime<newyeartime+604800000)
				return true;
		}
		return false;
	}
}
