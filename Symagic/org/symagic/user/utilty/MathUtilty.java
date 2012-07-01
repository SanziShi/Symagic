package org.symagic.user.utilty;


public class MathUtilty {
public static float roundWithdigits(float value){
	int base=(int) Math.pow(10, 2);
  	return(float)(Math.round(value*base))/100;
}
//public static void main(String[]args){
//	MathContext v=new MathContext(5,RoundingMode.HALF_DOWN);
//	BigDecimal a=new BigDecimal("0.3",v);
//	DecimalFormat df=new DecimalFormat("0.00");
//	System.out.println(Float.valueOf(df.format(a)));
//    BigDecimal bd=new BigDecimal("1");
//    bd=bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
//  
//}

}
