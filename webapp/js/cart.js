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
					alert('添加成功!请进入我的商城查看收藏商品');
				}
				else alert(a.resultInfo);
			}
		})
}
/******点击购物车页面+按钮函数******/
function add(id)
{
	var num=document.getElementById('cart_num');
	var a=document.getElementById(id+'amount');
	var u='cart/add_to_cart?'+'items[0].itemID='+id+'&items[0].itemNumber=1';
	Ajax({
		url:u,
		onSuccess:function(r)
			{
				var d=JSON.parse(r);
				if(d.addResult)
				{
					a.value++;
					a.setAttribute('default',a.value);
					get_session({S:function(s)
						{
							num.innerHTML=s.totalNumber
						}
					});
				}
				else alert('商品数量修改失败！')
			}
			
		})
}
/******点击购物车页面-按钮函数******/
function reduce(id)
{
	var num=document.getElementById('cart_num');
	var a=document.getElementById(id+'amount');
	var u='cart/update?itemID='+id+'&itemNumber='+(a.value-1);
	Ajax({
		url:u,
		onSuccess:function(r)
			{
				var d=JSON.parse(r);
				if(d.updateResult)
				{
					a.value--;
					a.setAttribute('default',a.value);
					get_session({S:function(s)
						{
							num.innerHTML=s.totalNumber
						}
					});
				}
				else alert('商品数量修改失败！')
			}
			
		})
}
function check_item_num(id,e)
{
	if(e.value==0)
	{
		if(confirm('确认将该商品从购物车中删除？'))
		{
			delete_from_cart(id,'p');
		}
		else e.value=e.getAttribute('default');
	}
	else
	{
		var u='cart/update?itemID='+id+'&itemNumber='+e.value;
		Ajax({
		url:u,
		onSuccess:function(r)
			{
				var d=JSON.parse(r);
				if(d.updateResult)
				{
					a.setAttribute('default',a.value);
					get_session({S:function(s)
						{
							num.innerHTML=s.totalNumber
						}
					});
				}
				else alert('商品数量修改失败！')
			}
			
		})
	}
}





