package org.symagic.user.utilty;

public class MathUtilty {
public static float roundWithdigits(float value){
	int base=(int) Math.pow(10, 2);
  	return(float)(Math.round(value*base))/base;
}
}