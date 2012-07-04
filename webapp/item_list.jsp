<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css"/>
<link href="css/item.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/yf_ADS.js"></script>
<script type="text/javascript" src="js/item_list.js"></script>
</head>
<body>
<div id="container">
<div id="cart">
	<span id="cart_loading"></span>
	<div id="cart_none">您的购物车中还没有商品，请选购！</div>
	<div id="cart_container"></div>
	
</div>
	<div id="logalleft">
		<div id="top-overlay"></div>
    <!----吊顶栏------>
	<div class="top">
    <div class="top_right">
    <ul>
    <s:if test="#session.nickname!=null">
    <li><s:property value="#session.nickname"/>！&nbsp;&nbsp;欢迎回到Symagic！</li>
    <li id="logout_top" onclick="logout();"><a>安全退出</a></li>
    </s:if>
    <s:else>
    <li>欢迎来到Symagic！</li>
    <li id="login_top" onclick="load_login();"><a>登录</a></li>
    <li id="regist_top" onclick="load_regist();"><a>免费注册</a></li>
    </s:else>
    <li class="division">|</li><li id="mymall"><a href="user"><span id="mymall_icon"></span>我的商城</a></li><li class="division">|</li>
    <li id="cart_top"><a id="cart_a" href="cart">
    <span id="cart_icon"></span>购物车 <strong id="cart_num"><s:property value='#session.totalNumber'/></strong> 件</a>
    </li>
    </ul>
    </div>
    </div>
  <!------吊顶结束----->
	</div>
	<div id="globallink">
		<ul>
			<li><a href="index">首页</a></li>
			<li><a href="item_list">商品列表</a></li>
			<li><a href="cart">我的购物车</a></li>
			<li><a href="user">我的商城</a></li>
			<li><a href="send_notes">购物指引</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
		</ul>
	</div>
	
	<div id="main">
		<div id="search2">
			<div id="searchleft">
				<img src="image/ico_site.jpg"  id="ico_site"/>
				网站路径：<a href="index">首页</a>&gt;&gt;<a href="">商品列表</a>
			</div>
			<form action="quick_search" method="post">
			<div id="searchright2">
            	<input style="display:none" name="page" value="1">
			  <input type="text" name="keyword" id="textInput"/>
			  <input type="submit" value="搜索" id="searchbutton" />
			</div>
			<div id="searchright1">
			 <select name="catalogID" >
			  <option value="0">所有类别</option>
			  <s:iterator value="catalog" var='outer'>
				<option value="<s:property value='#outer.ID'/>"><s:property value='#outer.name'/></option>
				<s:iterator value="#outer.childCatalog" var="inner">
				<option value="<s:property value='#inner.ID'/>">&nbsp;&nbsp;&nbsp;<s:property value='#inner.name'/></option>
				</s:iterator>
				</s:iterator>
              </select> 
              </div>
		  </form>
		</div>
		
        <div class="clear"></div>
        <div id="item_left">
        <dl>
        <!--左边迭代--->
        <s:iterator value="catalog" var='outer'>
        <div title="<s:property value='#outer.desc'/>" id="<s:property value='#outer.ID'/>">
        	<dt onclick="click_left_b(<s:property value='#outer.ID'/>)" ><s:property value='#outer.name'/></dt>
        </div>
        	<s:iterator value="#outer.childCatalog" var="inner">
        	<div title="<s:property value='#inner.desc'/>" id="<s:property value='#inner.ID'/>">
            	<dd onclick="click_left_b(<s:property value='#inner.ID'/>)"><s:property value='#inner.name'/></dd>
            </div>
        	</s:iterator>
        </s:iterator>
        <!--左边迭代结束--->
        <br/>
        <dt></dt>
        </dl>
        </div>
        <div  class="user_note">
			<div class="fliter1">
            	<font style="margin-bottom:2px;">高级搜索</font>
                <!--分页开始-->
                <div id="page_container">
                <s:if test="page==1">
                	<span class="none" >上一页</span>
                </s:if>
                <s:else>
                	<a href="<s:property value='actionURL'/>?page=<s:property value='page-1'/>">上一页</a>
                </s:else>
                <!--页面数字迭代与判断逻辑-->
                <s:if test='totalPage<=6&&totalPage>0'>
                	<s:iterator begin="1" end="totalPage"  status='st'>  
                		<s:if test="page==#st.index+1">
                        	<a href="javascript:void(0)" class="selected"><s:property value="#st.index+1"/></a>
                        </s:if> 
                        <s:else>
                        	<a href="<s:property value='actionURL'/>?page=<s:property value='#st.index+1'/>"><s:property value='#st.index+1'/></a>
                        </s:else> 
                	</s:iterator>
                </s:if>
                <!---->
                <s:if test="totalPage>6">
                <s:if test="page<=4">
                	<s:iterator begin="1" end="page"  status='st'>
                    	<s:if test="page==#st.index+1">
                        	<a href="javascript:void(0)" class="selected"><s:property value="#st.index+1"/></a>
                    	</s:if>
                        <s:else>
                        	<a href="<s:property value='actionURL'/>?page=<s:property value='#st.index+1'/>"><s:property value='#st.index+1'/></a>		
                        </s:else>
                    </s:iterator>
                    <span id="dot">...</span>
                    <s:if test="page==totalPage">
                        	<a href="javascript:void(0)" class="selected"><s:property value="totalPage"/></a>
                    	</s:if>
                        <s:else>
                        	<a href="<s:property value='actionURL'/>?page=<s:property value='totalPage'/>"><s:property value='totalPage'/></a>		
                        </s:else>
                </s:if>
                <s:elseif test="page+3>=totalPage">
                 		<s:if test="page==1">
                        	<a href="javascript:void(0)" class="selected"><s:property value="totalPage"/></a>
                    	</s:if>
                        <s:else>
                        	<a href="<s:property value='actionURL'/>?page=1"><s:property value='totalPage'/></a>		
                        </s:else>
                    <span id="dot">...</span>
                	<s:iterator begin="totalPage-3" end="totalPage"  status='st'>
                    	<s:if test="page==#st.index+1">
                        	<a href="javascript:void(0)" class="selected"><s:property value="#st.index+1"/></a>
                    	</s:if>
                        <s:else>
                        	<a href="<s:property value='actionURL'/>?page=<s:property value='#st.index+1'/>"><s:property value='#st.index+1'/></a>		
                        </s:else>
                    </s:iterator>
                </s:elseif>
                <s:else>
                	<s:if test="page==1">
                        	<a href="javascript:void(0)" class="selected"><s:property value="totalPage"/></a>
                    	</s:if>
                        <s:else>
                        	<a href="<s:property value='actionURL'/>?page=1"><s:property value='totalPage'/></a>		
                        </s:else>
                	<span id="dot">...</span>
                    <s:iterator begin="page-1" end="page+1"  status='st'>
                    	<s:if test="page==#st.index+1">
                        	<a href="javascript:void(0)" class="selected"><s:property value="#st.index+1"/></a>
                    	</s:if>
                        <s:else>
                        	<a href="<s:property value='actionURL'/>?page=<s:property value='#st.index+1'/>"><s:property value='#st.index+1'/></a>		
                        </s:else>
                    </s:iterator>
                    <span id="dot">...</span>
                    <s:if test="page==totalPage">
                        <a href="javascript:void(0)" class="selected"><s:property value="totalPage"/></a>
                    </s:if>
                    <s:else>
                        <a href="<s:property value='actionURL'/>?page=<s:property value='totalPage'/>"><s:property value='totalPage'/></a>		
                    </s:else>
                </s:else>
                </s:if>	
                <!---->
                <!--页面数字迭代与判断逻辑结束-->
                <s:if test="page==totalPage">
                	<span class="none">下一页</span>
                </s:if>
                <s:else>
                	<a href="<s:property value='actionURL'/>?page=<s:property value='page+1'/>">下一页</a>
                </s:else>
                </div>
                <!--分页结束-->
                <span title="展开或者收缩" class="collapse" onclick="show_item_search(this);"></span>
            </div>
			<div id="item_search1" class="user_note_content hide">
				<form action="item_list" method="post">
                <table>
				<tr>
					<th>书&nbsp;&nbsp;&nbsp;名：</th>
            		<td>
					<input type="text"  name="name"/>
					</td>
                   <th>作&nbsp;&nbsp;&nbsp;者：</th>
                    <td>
					<input type="text" name="author"/>
					</td>
          		 </tr>
				<tr>
					<th>出版社：</th>
            		<td>
					<input type="text" name="publisher"/>
					</td>
                    
					</td>
          		 </tr>
				 <tr>
					<th>书籍类别：</th>
            		<td>
						<select name="catalogID" class="midselect">
              				<option value="0">所有类别</option>
			  				<s:iterator value="catalog" var='outer'>
							<option value="<s:property value='#outer.ID'/>"><s:property value='#outer.name'/></option>
							<s:iterator value="#outer.childCatalog" var="inner">
							<option value="<s:property value='#inner.ID'/>">&nbsp;&nbsp;&nbsp;<s:property value='#inner.name'/></option>
							</s:iterator>
							</s:iterator>
            			</select>
					</td>
                   <th>出版时间：</th>
            		<td>
						<select name="publishTime" class="midselect">
              				<option value="0">不论时间</option>
                            <s:iterator value='searchDate' var='iter' status='st'>
                            <option value="<s:property value="#st.index+1"/>"><s:property value='#iter'/></option>
             				</s:iterator>
            			</select>
					</td> 
          		 </tr>
				
				 <tr>
					<th>页数范围：</th>
            		<td>
						<select name="searchPage" class="midselect">
                        	<option value="0">不限页数</option>
              				<option value="1">0-200</option>
             				<option value="2">200-400</option>
              				<option value="3">400-600</option>
			  				<option value="4">600以上</option>
            			</select>
					</td>
					<th>版&nbsp;&nbsp;&nbsp;次：</th>
            		<td>
						<select name="edition" class="midselect">
              				<option value="0">全部版次</option>
             				<option value="1">第一版</option>
              				<option value="2">第二版</option>
              				<option value="3">第三版</option>
			  				<option value="4">第四版</option>
            			</select>
					</td>
          		 </tr>
				 <tr>
					
					<th>装&nbsp;&nbsp;&nbsp;帧：</th>
            		<td>
						<select name="binding" class="midselect">
              				<option value="0">不论</option>
             				<option value="1">平装</option>
              				<option value="2">精装</option>
            			</select>
					</td>
                    <th>开&nbsp;&nbsp;&nbsp;本：</th>
            		<td>
						<select name="booksize" class="midselect">
              				<option value="0">不限</option>
             				<option value="1">32开</option>
              				<option value="2">16开</option>
							<option value="3">8开</option>
            			</select>
					</td>
          		 </tr>
				 <tr>
					
				 <th>市场价格：</th>
            		<td>
						<select name="price" class="midselect">
              				<option value="0">任意价格</option>
             				<option value="1">0-10元</option>
              				<option value="2">10-30元</option>
              				<option value="3">30-50元</option>
			  				<option value="4">50-100元</option>
              				<option value="5">100元以上</option>
            			</select>
					</td>
                    <th>商品折扣：</th>
            		<td>
					  	<select name="discount" class="midselect">
							<option value="0">所有折扣</option>
              				<option value="1">3折以下</option>
              				<option value="2">3-5折</option>
							<option value="3">5-7折</option>
							<option value="4">7折及以上</option>
            			</select> 
					</td>
          		 </tr>
				 <tr>
				 <th></th>
				 	<td><input type="submit"  value="搜索" onClick="">
				 </tr>
        </table>	
		</form>	
        <div class="fliter"></div>	
			</div>
		</div>
 <div class="user_note">
   	<div id="item_container">
   	
   	<!--商品迭代div-->
   	<input id="amount" value="1" style="display:none"/>
   	<s:iterator value="items" var='iter' status='st'>
   	<div class="item">
   		<div class="p-img">
   			<div class="i-img">
        		<a  href="item_detail?itemID=<s:property value='#iter.itemID'/>">
        		<img src="<s:property value='#request.get("javax.servlet.forward.context_path")'/><s:property value='#iter.picturePath'/>">
            	</a>
        	</div>
    	</div>
        <div class="dl">
        	<div class="p-name">
            	<a target="_blank" href="item_detail?itemID=<s:property value='#iter.itemID'/>"><s:property value='#iter.name'/></a>
        	</div>
   		<div class="p-info">作　　者：<a href="quick_search?keyword=<s:property value='#iter.author'/>"><s:property value='#iter.author'/></a>著&nbsp;&nbsp;&nbsp;&nbsp;
    	</div>
   	<div class="p-info">出&nbsp;版&nbsp;社：<a href="quick_search?keyword=<s:property value='#iter.publisher'/>"><s:property value='#iter.publisher'/></a>&nbsp;&nbsp;&nbsp;&nbsp;</div>
   	<div class="p-info"><span class="date-pub">出版时间：<s:property value='#iter.publishTime'/></span>&nbsp;&nbsp;<span class="fl">评&nbsp;&nbsp;分：</span><s:property value='#iter.rating'/></div>
   	<div class="p-info"><div class="mark-price">定　　价：<del>￥<s:property value='#iter.marketPrice'/></del>
    </div>商城价：<strong>￥<s:property value='#iter.price'/></strong><span>（<s:property value='#iter.discount'/>折）</span>
    </div>
   <div class="btns">
   <s:if test='!#iter.offline'>
   		<div class="add_to_cart1" onclick="add_to_cart(<s:property value='#iter.itemID'/>)"></div>
   </s:if>
   <s:else>
  		<div class="add_to_cart1_none" ></div>
   </s:else>
        <div style=" float:left">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="add_to_favorite(<s:property value='#iter.itemID'/>)">添加至收藏夹</a></div>
   </div>
   </div>
   </div>
   </s:iterator>
   <!--商品迭代div---end-->
   </div>
 </div>
 
 <div id="footer">
				<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 |
					电子邮件：Symagics@gmail.com </span> <span id="footerright"> Symagic网上书城
					Power by Symagic &nbsp;</span>
			</div>
</div>

</body>
</html>
