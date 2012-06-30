function amount_modify(e)
{
	e.value=e.value.replace(/\D+/g,'');
}
function checkout()
{
	var a=document.getElementById('checkout');//.submit();
	var q=$(a).serialize();
	alert(q);
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