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
function save_address()
{
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
	if(e.checked)s.style.display='inline-block';
	else s.style.display='none';
}
function check_score(e)
{
	e.value=e.value.replace(/\D+/g,'');
	if(e.value='')e.value=0;
	else Ajax({
		url:'order/check_score?score='+e.value,
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				
			}
		})
}
function order_submit()
{
	document.getElementById('order_submit').submit();
}






