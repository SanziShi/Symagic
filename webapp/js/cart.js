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
/*****从cart页面删除商品*******/
function delete_from_page(id)
{
	Ajax({
		url:'cart/delete?itemID='+id,
		onSuccess:function(e){var a=JSON.parse(e);if(a.deleteResult)location.reload()}
		})
}