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
		var tmp=document.createElement('option');
		l2.innerHTML='';
		tmp.value='s2';tmp.innerHTML='请选择';
		l2.appendChild(tmp);
		l3.innerHTML='';
		tmp.value='';
		l3.appendChild(tmp);
		return false;
	}
	else if(va=='s2')
	{
		var tmp=document.createElement('option');
		tmp.value='';tmp.innerHTML='请选择';
		l3.innerHTML='';
		l3.appendChild(tmp);
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
		url:'address/submit_address',
		data:form,
		onSuccess:function(e)
			{
				var r=JSON.parse(e);
				if(r.submitResult)
				{
					alert('添加成功');
					show_address();
					/*Ajax({
						url:'address',
						onSuccess:function(e)
							{
								document.getElementById('address-container').innerHTML=e;
							}
						})*/
				}
				else
				{
					alert('添加失败！');
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
					show_address();
				}
				else alert(a.resultInfo);
			}
		})
	return false;
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
					show_address();
				}
				else alert(a.resultInfo);
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