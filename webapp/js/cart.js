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
function tests(e)
{
	var a=$(a).serialize();
	alert(a);
	return false;
}
function checkbox_change(e)
{
	alert(e.id);
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
	var form=document.getElementById(f);
	var k=form.getElementsByTagName('input');
	var l=k.length;
	for(var i=0;i<l;++i)
	{
		alert(k[i].id);
	}
	return false;
	Ajax({
		url:'cart/delete',
		data:d,
		onSuccess:function(e)
			{
				var a=JSON.parse(e);
				if(a.deleteResult)alert('删除成功~');
			}
		})
}




