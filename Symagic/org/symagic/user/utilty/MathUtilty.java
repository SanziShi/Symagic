package org.symagic.user.utilty;

import java.text.DecimalFormat;


public class MathUtilty {
public static float roundWithdigits(float value){
	int base=(int) Math.pow(10, 1);
	DecimalFormat format=new DecimalFormat("0.00");
  	return  (float)(Math.round(value*base))/base;
}

}
