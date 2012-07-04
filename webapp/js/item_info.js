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
	if(e.value=='')e.value=1;
}
function comment_submit(f)
{
	var form_data=$(f).serialize();
	Ajax({
			url:'item/comment',
			data:form_data,
			onSuccess:function(e)
				{
					var r=JSON.parse(e);
					if(r.submitResult)
					{
						alert('评价提交成功！');
						location.reload();
						return false;
					}
					else 
					{
						alert('抱歉，提交评论失败！')
						return false;
					}
				}
		})
	return false
}