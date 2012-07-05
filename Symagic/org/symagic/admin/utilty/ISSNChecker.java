package org.symagic.admin.utilty;

public class ISSNChecker {
	
	public static boolean checkISSN( String issn ){
		
		if( issn.length() != 8 ) return false;
		
		char[] charArray = issn.toUpperCase().toCharArray();
		
		int sum = 0;
		
		for( int i = 0; i < 7; i++ ){
			int temp = charArray[i] - '0';
			
			if( temp < 0 || temp > 9 ) return false;
			
			sum += temp * (8 - i);
			
		}
	    
		int checkCode = 11 - ( sum % 11 );
		
		if( checkCode == 10 ){
			if( charArray[7] != 'X' ) return false;
		}
		else{
			if( charArray[7] - '0' != checkCode ) return false;
		}
			
		
		return true;
	}
	
}
