function amount_modify(e)
{
	e.value=e.value.replace(/\D+/g,'');
}
function cart_submit()
{
	document.cart.submit();
}