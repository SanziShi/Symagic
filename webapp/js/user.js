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
function address_edit(e)
{
	alert(e.event);
	showOverlay();
	document.getElementById('test').style.display='block';
}
function close_address(e)
{
	var p=e.parentNode.parentNode;
	p.style.display='none';
	hideOverlay();
}
function nickname_c(e)
{
	var c=document.getElementById('nickname_c').value;
	Ajax({
		url:'user/update_nickname',
		data:'nickname='+c,
		onSuccess:function(e){var a=JSON.parse(e);if(a.updateResult){alert('修改成功');location.href='user'}}
		})
}
function pass_submit()
{
	var a=document.getElementById('pass_before').value;
	var b=document.getElementById('pass_new').value;
	var c=document.getElementById('pass_confirm').value;
	Ajax({
		url:'user/update_password?password='+a+'&newPassword='+b+'&newPasswordConfirm='+c,
		onSuccess:function(e){var a=JSON.parse(e);if(a.updateResult)alert('密码修改成功！');location.reload();}
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
			document.getElementById('adress-container').innerHTML=e;
			}
		})
}
function get_district(d)
{
	var va=d.value;
	if(va=='s1')
	{
		document.getElementById('level2ID').innerHTML='<option value="s2">请选择</option>';
		document.getElementById('level3ID').innerHTML='<option value="">请选择</option>';
		return false;
	}
	else if(va=='s2')
	{
		document.getElementById('level3ID').innerHTML='<option value="">请选择</option>';
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
					n.innerHTML='<option value="-2">请选择</option>';
				}else n.innerHTML='<option value="">请选择</option>';
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
		url:'submit_address',
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
							}
						})
				}
				else
				{
					alert('添加失败！');
				}
			}
		})
}
/*****8*修改地址函数*****/
/******dom完成加载执行函数*******/
$().ready(function(e) {
    addListener(document.getElementById('t1'),"click",function(e){
		stopDefault(e);
		var y=getY(e);
		var t=document.getElementById('test');
		showOverlay();
		t.style.top=y-t.style.height+'px';
		t.style.display='block';
		});
});