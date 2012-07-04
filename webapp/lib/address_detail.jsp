<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!---订单的地址修改table--->
               <table id="order_address" width="100%" cellspacing="0" border="0">
                			<tbody>
                    		<tr>
                            	<td width="80" valign="middle" align="right"><font color="red">*</font>收货人姓名：</td>
                            	<td><input name="receiverName" class="txt" type="text" value="<s:property value='addressList.receiverName'/>"/></td>
                            </tr>
                        	<tr>
                        	  	<td valign="middle" align="right"><font color="red">*</font>所在地区：</td>
                              	<td>
                                <!--默认地址选项迭代--->
                                	<select onchange="get_district(this)" id="level1ID" name="districtLevel1ID" >
                                			<option value="s1" >请选择</option>
                               				<s:iterator value="addressList.level1Districts" var='middle'>
                                            	<s:if test='#middle.ID==addressList.level1DistrictDefaultID'>
                                				<option value="<s:property value='#middle.ID'/>" selected="selected" ><s:property value='#middle.name'/></option>
                                				</s:if>
                                                <s:else>
                                                <option value="<s:property value='#middle.ID'/>" ><s:property value='#middle.name'/></option>
                                                </s:else>
                                            </s:iterator>
                                	</select>
                                	<select onchange="get_district(this)" id="level2ID" name="districtLevel2ID">
                                		<option value="s2">请选择</option>
                                        <s:iterator value="addressList.level2Districts" var='middle'>
                                            	<s:if test='#middle.ID==addressList.level2DistrictDefaultID'>
                                				<option value="<s:property value='#middle.ID'/>" selected="selected" ><s:property value='#middle.name'/></option>
                                				</s:if>
                                                <s:else>
                                                <option value="<s:property value='#middle.ID'/>" ><s:property value='#middle.name'/></option>
                                                </s:else>
                                            </s:iterator>
                                	</select>
                                	<select id="level3ID" name="districtLevel3ID">
                                		<option value="">请选择</option>
                                        <s:iterator value="addressList.level3Districts" var='middle'>
                                            	<s:if test='#middle.ID==addressList.level3DistrictDefaultID'>
                                				<option value="<s:property value='#middle.ID'/>" selected="selected" ><s:property value='#middle.name'/></option>
                                				</s:if>
                                                <s:else>
                                                <option value="<s:property value='#middle.ID'/>" ><s:property value='#middle.name'/></option>
                                                </s:else>
                                            </s:iterator>
                                	</select>
                                <!---默认地址选项迭代结束--->
                                </td>
                            </tr>
                    		<tr>
                    		  	<td valign="middle" align="right"><font color="red">*</font>详细地址：</td>
                              	<td><span id="consign_address"></span><input name="addressDetail" type="text" class="txt long" value="<s:property value='addressList.addressDetail'/>"></td>
                            </tr>
                    		<tr>
                            	<td valign="middle" align="right"><font color="red">*</font>手机号码：</td>
                            	<td><input name="mobileNum" type="text" class="txt" value="<s:property value='addressList.mobileNum'/>"/>或者&nbsp;&nbsp;或&nbsp;&nbsp;固定电话：<input name="phoneNum" type="text" class="txt" value="<s:property value='addressList.phoneNum'/>"></td>
                            </tr>
                    		<tr>
                            	<td valign="middle" align="right">邮箱地址：</td><td><s:property value='#session.userName'/></td>
                            </tr>
                    		<tr>
                            	<td valign="middle" align="right"><font color="red">*</font>邮政编码：</td>
                            	<td><input name="zipcode" type="text" class="txt short" value="<s:property value='addressList.zipcode'/>"/></td>
                            </tr>
                            <!---<tr>
                                	<td></td>
                                    <td><input type="submit" value="保存修改"/></td>
                            </tr>--->
                    		</tbody>
                		</table>
                <!--- 订单的地址修改table--->