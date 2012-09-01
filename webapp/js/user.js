function show_user_con(num)
{
	$('#favorite').slideUp(1);
	$('#address').slideUp(1);
	for(var x=1;x!=5;++x)
	{
		if(x!=num)
		{
			//if(document.getElementById(x).style&&document.getElementById(x).style.display=='block')
			$('#'+x).slideUp(1);
		}
	}
	//if(!document.getElementById(x).style||document.getElementById(x).style.display!='block')
	$('#'+num).slideDown(250);
}
function address_edit(id)
{
	showOverlay();
	document.getElementById('address'+id).style.display='block';
}
function close_address_edit(id)
{
	document.getElementById(id).style.display='none';
	hideOverlay();
}
function nickname_c(e)
{
	var c=document.getElementById('nickname_c').value;
	var before=document.getElementById('nick_name').value;
	if(before==c)
	{
		alert('与原昵称相同，请检查您的输入！');
		return false;
	}
	Ajax({
		url:'user/update_nickname',
		data:'nickname='+c,
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				if(a.updateResult)
				{
					alert('修改成功');location.href='user';
				}
				else
				{
					if(a.resultInfo!=null)alert(a.resultInfo);
					else alert('修改失败');
				}
			}
		})

}
function pass_submit()
{
	var a=document.getElementById('pass_before').value;
	var b=document.getElementById('pass_new').value;
	var c=document.getElementById('pass_confirm').value;
	if(a.length<6||a.length>20||b.length<6||b.length>20||b.length<6||b.length>20)
	{
		alert('密码长度非法！');
		return false;
	}
	if(b!=c)
	{
		alert('两次输入密码不同！');
		return false;
	}
	Ajax({
		url:'user/update_password?password='+a+'&newPassword='+b+'&newPasswordConfirm='+c,
		onSuccess:function(e){
				var a=JSON.parse(e);
				if(a.updateResult)
				{
					alert('密码修改成功！');
					location.reload();
				}
				else
				{
					alert('原密码错误！');
					//alert(a.resultInfo);
				}
			}
		})
}
function show_favorite()
{
	//document.getElementById('favorite_loading').style.display='block';
	for(var x=1;x!=5;++x)$('#'+x).slideUp(1);
	$('#address').slideUp(1);
	$('#favorite').slideDown(1);
	Ajax({
		url:'favorite',
		onSend:function(){document.getElementById('favorite_loading').style.display='block';},
		onSuccess:function(e){
			document.getElementById('favorite_loading').style.display='none';
			document.getElementById('favorite-container').innerHTML=e;
			}
		})
}
function show_address()
{
	for(var x=1;x!=5;++x)$('#'+x).slideUp(1);
	$('#favorite').slideUp(1);
	$('#address').slideDown(1);
	Ajax({
		url:'address',
		onSend:function(){document.getElementById('address_loading').style.display='block';},
		onSuccess:function(e){
			document.getElementById('address_loading').style.display='none';
			document.getElementById('address-container').innerHTML=e;
			}
		})
}
/*****获取下级目录函数*****/
function get_district(d)
{
	var l2=document.getElementById('level2ID');
	var l3=document.getElementById('level3ID');
	var va=d.value;
	if(va=='s1')
	{
		$(l2).empty();
		$(l2).append('<option value="s2">请选择</option>');
		$(l3).empty();
		$(l3).append('<option value="s3">请选择</option>');
		return false;
	}
	else if(va=='s2')
	{
		$(l3).empty();
		$(l3).append('<option value="s3">请选择</option>');
		return false;
	}
	else{
	Ajax({
		url:'address/get_next_level?ID='+va,
		onSuccess:function(e)
			{
				var dis=JSON.parse(e);
				var l=dis.length;
				var n=d.nextSibling.nextSibling;
				if(d.id=='level1ID')
				{
					$(l2).empty();
					$(l2).append('<option value="s2">请选择</option>');
					$(l3).empty();
					$(l3).append('<option value="s3">请选择</option>');
				}else 
				{
					$(l3).empty();
					$(l3).append('<option value="s3">请选择</option>');
				}
				var t=document.createElement('select');
				for(var g=0;g<l;++g)
				{
					var temp=document.createElement('option');
					temp.value=dis[g].ID;
					temp.innerHTML=dis[g].name;
					n.appendChild(temp);
				}
			}
		})
	}
}
function get_district_edit(d,id)
{
	var l2=document.getElementById('level2ID'+id);
	var l3=document.getElementById('level3ID'+id);
	var va=d.value;
	if(va=='s1')
	{
		$(l2).empty();
		$(l2).append('<option value="s2">请选择</option>');
		$(l3).empty();
		$(l3).append('<option value="s3">请选择</option>');
		return false;
	}
	else if(va=='s2')
	{
		$(l3).empty();
		$(l3).append('<option value="s3">请选择</option>');
		return false;
	}
	else{
	Ajax({
		url:'address/get_next_level?ID='+va,
		onSuccess:function(e)
			{
				var dis=JSON.parse(e);
				var l=dis.length;
				var n=d.nextSibling.nextSibling;
				if(d.id=='level1ID')
				{
					$(l2).empty();
					$(l2).append('<option value="s2">请选择</option>');
					$(l3).empty();
					$(l3).append('<option value="s3">请选择</option>');
				}else 
				{
					$(l3).empty();
					$(l3).append('<option value="s3">请选择</option>');
				}
				var t=document.createElement('select');
				for(var g=0;g<l;++g)
				{
					var temp=document.createElement('option');
					temp.value=dis[g].ID;
					temp.innerHTML=dis[g].name;
					n.appendChild(temp);
				}
			}
		})
	}
}
/**添加新地址表单提交函数***/
function add_address(f)
{
	var form=$(f).serialize();
	Ajax({
		url:'address/submit_address',
		data:form,
		onSuccess:function(e)
			{
				var r=JSON.parse(e);
				if(r.submitResult)
				{
					alert('添加成功');
					Ajax({
						url:'address',
						onSuccess:function(e)
							{
								document.getElementById('address-container').innerHTML=e;
								return false;
							}
					})
					return false;
				}
				else
				{
					alert(r.resultInfo);
				}
			}
		})
		return false;
}
/******修改地址提交函数*****/
function address_edit_submit(f,id)
{
	Ajax({
		url:'address/address_edit_submit',
		data:$(f).serialize(),
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				if(a.submitResult)
				{
					alert('保存成功！');
					close_address_edit(id);
					Ajax({
						url:'address',
						onSuccess:function(e)
							{
								document.getElementById('address-container').innerHTML=e;
								return false;
							}
					})
					return false;
				}
				else alert(a.resultInfo);
			}
		})
	return false;
}
function cancel_order(id)
{
	if(!confirm('确认取消该订单？'))return false;
	var getid='#order_list'+id;
	Ajax({
		url:'order/cancel_order?orderID='+id,
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				if(a.cancelResult)
				{
					alert('订单已取消！');
					$(getid).fadeOut(300);
				}
				else alert(a.resultInfo);
			}
		})
}
/****删除地址函数****/
function delete_address(id)
{
	Ajax({
		url:'address/delete_address?addressID='+id,
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				if(a.deleteResult)
				{
					alert('删除成功！');
					var t='#address_tr'+id;
					$(t).fadeOut(200);
				}
				else alert(a.resultInfo);
			}
		})
}
/******批量删除收藏夹*****/
function delete_favorites()
{
	var request='';
	var f=document.getElementById('favorite_form');
	var k=$(f).serializeArray();
	for(var each in k)
	{
		if(k[each].name.indexOf('ID')>-1)
		request=request+'&items='+k[each].value;
	}
	Ajax({
		url:'favorite/delete_favorite',
		data:request,
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				if(a.deleteResult)
				{
					alert('删除成功！');
					Ajax({
						url:'favorite',
						onSuccess:function(e){
						document.getElementById('favorite_loading').style.display='none';
						document.getElementById('favorite-container').innerHTML=e;
						}
						})
				}
			}
		})
}
/******dom完成加载执行函数*******/
$().ready(function(e) {
	if(document.getElementById('t1')){
    addListener(document.getElementById('t1'),"click",function(e){
		stopDefault(e);
		var y=getY(e);
		var t=document.getElementById('test');
		showOverlay();
		t.style.top=y-t.style.height+'px';
		t.style.display='block';
		});
	}
});