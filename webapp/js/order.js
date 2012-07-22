function get_district(d)
{
	var l2=document.getElementById('level2ID');
	var l3=document.getElementById('level3ID');
	var va=d.value;
	alert(va);
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
					$(l2).append('<option value="s3">请选择</option>');
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
function detect(e,id)
{
	var idname='normal_address'+id;
	iddiv=document.getElementById(idname);
	iddiv.className+=' selected';
	var father=iddiv.parentNode.childNodes;
	for(var i in father)
	{
		if(father[i].id!=idname)father[i].className='';
	}
	Ajax({
		url:'order/get_address_detail?addressID='+id,
		onSuccess:function(e)
			{
				document.getElementById('table_container').innerHTML=e;
			}
		})
}
function save_address(id)
{
	var f=document.getElementById(id).innerHTML;
	var temp=document.createElement('form');
	temp.innerHTML=f;
	var form_data=$(temp).serialize();
	Ajax({
		url:'address/submit_address',
		data:form_data,
		onSuccess:function(re)
			{
				var a=JSON.parse(re);
				if(a.submitResult)
				{
					alert('新增地址成功');
					location.reload();
				}
			}
		})
}
function delete_address(id)
{
	Ajax({
		url:'address/delete_address?addressID='+id,
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				if(a.deleteResult)
				{
					var i='#normal_address'+id;
					$(i).fadeOut(200);
				}
				else alert(a.resultInfo);
			}
		})
}
function use_score_change(e)
{
	var s=document.getElementById('score');
	if(e.checked)s.disabled='';
	else 
	{
		s.disabled='disabled';
	}
}
function check_score(e)
{
	e.value=e.value.replace(/\D+/g,'');
	if(e.value=='')e.value=0;
	else Ajax({
		url:'order/check_score?score='+e.value,
		onSuccess:function(t)
			{
				var a=JSON.parse(t);
				if(a.checkResult)
				{
					document.getElementById('total_price').innerHTML=a.price;
				}
				else 
				{
					Stip('score').show({p: 'top',content:'您的积分不够，请重新选择',time:3000,kind:"error"});
				}
			}
		})
}
function order_submit()
{
	var t=document.getElementById('score');
	if(t.disabled=='disabled')document.getElementById('order_submit').submit();
	else Ajax({
		url:'order/check_score?score='+t.value,
		onSuccess:function(y)
			{
				var a=JSON.parse(y);
				if(a.checkResult)
				{
					document.getElementById('total_price').innerHTML=a.price;
					document.getElementById('order_submit').submit();
				}
				else 
				{
					Stip('score').show({p: 'top',content:'您的积分不够，请重新选择',time:3000,kind:"error"});
					return false;
				}
			}
		})
	return false;
}
function order_edit_confirm()
{
	var id=document.getElementById('orderID').value;
	var f=document.getElementById('order_edit_submit');
	Ajax({
		url:'order/order_edit_submit',
		data:$(f).serialize(),
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				if(a.submitResult)
				{
					alert('修改成功！');
					location.href='order_detail?orderID='+id;
				}
				else 
				{
					alert(a.resultInfo);
				}
			}
		})
}
function clear_address(id)
{
	var tid='#'+id;
	$(tid+' input').each(function(i) {
        this.value='';
    });
	var t=document.getElementById('level1ID');
	t.value='s1';
	var t=document.getElementById('level2ID');
	$(t).empty();
	$(t).append('<option value="s2">请选择</option>');
	var t=document.getElementById('level3ID');
	$(t).empty();
	$(t).append('<option value="s3">请选择</option>');
}





