//相关逻辑函数
function add()
{
	document.getElementById('amount').value=parseInt(document.getElementById('amount').value)+1;
}
function reduce()
{
	if(document.getElementById('amount').value>1)document.getElementById('amount').value=parseInt(document.getElementById('amount').value)-1;
}
function amount_modify(e)
{
	e.value=e.value.replace(/\D+/g,'');
}
function favorite()
{
}