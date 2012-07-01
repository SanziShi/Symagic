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
function select_address(e)
{
	e.parentNode.className+=' selected';
}







