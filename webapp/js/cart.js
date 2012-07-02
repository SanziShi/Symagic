function amount_modify(e)
{
	e.value=e.value.replace(/\D+/g,'');
}
/*****进入下单页面*******/
function checkout()
{
	var a=document.getElementById('checkout');//.submit();
	a.submit();
	//var q=$(a).serialize();
}
/*****从cart页面删除商品
function delete_from_page(id)
{
	Ajax({
		url:'cart/delete?items='+id,
		onSuccess:function(e){var a=JSON.parse(e);if(a.deleteResult)location.reload()}
		})
}
*******/
function delete_form_cart(f)
{
	var request='';
	var form=document.getElementById(f);
	var k=$(form).serializeArray();
	for(var each in k)
	{
		if(k[each].name.indexOf('ID')>-1)
		request=request+'&items='+k[each].value;
	}
	Ajax({
		url:'cart/delete',
		data:request,
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				if(a.deleteResult)
				{
					alert('删除成功!');
					location.reload();
				}
				else alert(a.resultInfo);
			}
		})
}
function add_to_favorite()
{
	var f=document.getElementById('checkout');
	var request='';
	var k=$(f).serializeArray();
	for(var each in k)
	{
		if(k[each].name.indexOf('ID')>-1)
		request=request+'&items='+k[each].value;
	}
	Ajax({
		url:'favorite/add_favorite',
		data:request,
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				if(a.deleteResult)
				{
					alert('添加成功!');
				}
				else alert(a.resultInfo);
			}
		})
}



