// JavaScript Document
GLOBAL_CONFIG=
{
	dir:'/Symagic'	
}
Ajax=function (option){
	option=
	{
		type:option.type||'POST',
		url:option.url||'',
		async:option.async||'ture',
		timeout:option.timeout||4000,
		onComplete:option.onComplete||function(){},
		onError:option.onError||function(){},
		onSuccess:option.onSuccess||function(){},
		data:option.data||''
	};
	var ajax;
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
		ajax.send()
	}
	else 
	{
		ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ajax.send(option.data);
	}
	ajax.onreadystatechange=function()
	{
		alert(ajax.readyState);
		alert(ajax.status);
		
		if(ajax.status=200&&ajax.readyState==4)alert(ajax.responseText);
		//option.onComplete(ajax.responseText);
	}
}
