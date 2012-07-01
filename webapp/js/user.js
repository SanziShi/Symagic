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