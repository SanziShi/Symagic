function amount_modify(e)
{
	e.value=e.value.replace(/\D+/g,'');
}
function checkout()
{
	document.getElementById('checkout').submit();
}
function tests(e)
{
	var a=$(a).serialize();
	alert(a);
}