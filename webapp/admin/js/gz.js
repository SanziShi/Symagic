// JavaScript Document
function change_captcha(e)
{
	e.src='captcha_get_captcha';
}

//异步调用函数
Ajax=function (option){
	option=
	{
		type:option.type||'POST',
		url:option.url||'',
		async:option.async||'ture',
		timeout:option.timeout||5000,
		onComplete:option.onComplete||function(){},
		onError:option.onError||function(){},
		onSuccess:option.onSuccess||function(){},
		onSend:option.onSend||function(){},
		onTimeout:option.onTimeout||function(){},
		acceptdatatype:option.acceptdatatype||'json',
		data:option.data||''
	};
	var ajax;
	var timer;
	if(typeof XMLHttpRequest=='undefined')
	{
		ajax=new ActiveXObject("Microsofr.XMLHttp");
	}
	else 
	{
		ajax=new XMLHttpRequest();
	}
	ajax.open(option.type,option.url,option.async);
	if(option.type=='GET')
	{
		ajax.setRequestHeader("If-Modified-Since","0"); 
		ajax.send();
	}
	else 
	{
		switch(option.acceptdatatype)
		{
			case 'json':ajax.setRequestHeader("Accept",'application/json, text/javascript');
				ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				break;
			default :ajax.setRequestHeader("Accept",'application/json, text/javascript');
				ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		}
		ajax.setRequestHeader("If-Modified-Since","0"); 
		ajax.send(option.data);
	}
	timer=setTimeout(function()
		{
			if(typeof option.onTimeout=="function") option.onTimeout();
			if(ajax)
			{
				ajax.abort();
				ajax=null;
			}
			return ture;
		}
		,option.timeout);
	ajax.onreadystatechange=function()
	{
		switch (ajax.readyState)
		{
			case 0: 
				break;
			case 1:
				break;
			case 2:option.onSend();
				break;
			case 3:
				break;
			case 4:
				try
				{
					switch(ajax.status)
					{
						case 200:if(timer)clearTimeout(timer);
							option.onSuccess(ajax.responseText);
							ajax=null;
							break;
						case 404:if(timer)clearTimeout(timer);
							option.onError(ajax.responseText);
							ajax=null;
							break;
						default:if(timer)clearTimeout(timer);
							option.onComplete(ajax.responseText);
							ajax=null;
					}
				}catch(e){}
			default:break;
		}
		//alert('ajax.status:'+ajax.status+"  ajax.readyState:"+ajax.readyState);
	}

}

function delete_tag(id){
	var tag = document.getElementById(id);
	tag.parentNode.removeChild(tag);
	}

//商品管理中删除一个商品
function ajax_delete_tag(id){
	var result = confirm("该操作将会将商品移除，确定继续吗？");
	if(result==true){
	Ajax({
					url:'item_manager/delete?itemId='+id,
				    type:'GET',
					onSend:function(){},
					onSuccess:function()
						{
							/*var t=document.createElement('div');
							t.id='cart_container';
							t.innerHTML=e;
							document.getElementById('cart').appendChild(t);
							t=null;*/
							 var tag = document.getElementById(id);
                             tag.parentNode.removeChild(tag);
						}
						});
	}
         
	}

//商品下架
function ajax_item_off(e,id){
	Ajax({
	                url:'item_manager/off?itemId='+id,
				    type:'GET',
					onSend:function(){},
					onSuccess:function()
						{
							/*var t=document.createElement('div');
							t.id='cart_container';
							t.innerHTML=e;
							document.getElementById('cart').appendChild(t);
							t=null;*/
							set_value(e);
						}
						});
	
	
	}
	

	
function show_item_search(e)
{
	if(e.className=='collapse')
	{
		e.className='collapsed';
		$('#item_search1').slideDown(70);
	}
	else
	{
		e.className='collapse';
		$('#item_search1').slideUp(70);
	}
}

function expanse(e)
{
	
		e.className='collapsed';
		$('#item_search1').slideDown(70);
	
}

function set_value(e){
	if(e.value=="下架")
	e.value = "恢复";
	else 
	e.value = "下架";
	}
