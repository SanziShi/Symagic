function amount_modify(e)
{
	e.value=e.value.replace(/\D+/g,'');
	if(e.value=='')e.value=0;
}
/*****进入下单页面*******/
function checkout()
{
	var a=document.getElementById('checkout');
	var inputs=document.getElementsByTagName('input');
	var URL='';
	for( i in inputs)
	{
		if(inputs[i].id&&inputs[i].id.indexOf('amount')>-1)
		{
			if(inputs[i].getAttribute('default')==inputs[i].value)continue;
			var f=parseInt(inputs[i].id);
			URL='cart/update?itemID='+f+'&itemNumber='+inputs[i].value;
		}
	}
	if(URL)
	{
		Ajax({
			url:URL,
			onSuccess:function(e)
				{
					var c=JSON.parse(e);
					if(c.updateResult)
					{
						a.submit();
					}
					else
					{
						alert('c.resultInfo');
						location.reload();
					}
				}
			})
		return false;
	}
	else a.submit();
	stopDefault();
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
function add_to_favorites()
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
			url:'order/check_item?itemID='+id+'&itemNum='+(a.value+1),
			onSuccess:function(checkr)
				{
					var che=JSON.parse(checkr);
					if(che.checkResult)
					{
						Ajax({
							url:u,
							onSuccess:function(r)
								{
									var d=JSON.parse(r);
									if(d.addResult)
									{
										location.reload();
										/*a.value++;
										a.setAttribute('default',a.value);
										get_session({S:function(s)
											{
												num.innerHTML=s.totalNumber
											}
										});*/
									}
									else alert('商品数量修改失败！')
								}
							})
					}
					else
					{
						alert('抱歉，库存不够，您可以先收藏该商品');
						a.value=a.getAttribute('default');
					}
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
			url:'order/check_item?itemID='+id+'&itemNum='+(a.value-1),
			onSuccess:function(checkr)
				{
					var che=JSON.parse(checkr);
					if(che.checkResult)
					{
						Ajax({
							url:u,
							onSuccess:function(r)
								{
									var d=JSON.parse(r);
									if(d.updateResult)
									{
										//if(--a.value==0)
											//{
												location.reload();	
											//};
										/*a.setAttribute('default',a.value);
										get_session({S:function(s)
											{
												num.innerHTML=s.totalNumber
											}
										});*/
									}
									else alert('商品数量修改失败！')
								}
							})
					}
					else
					{
						alert('抱歉，库存不够，您可以先收藏该商品');
						a.value=a.getAttribute('default');
					}
				}
		})
	
}
function check_item_num(id,e)
{
	var num=document.getElementById('cart_num');
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
		Ajax({
			url:'order/check_item?itemID='+id+'&itemNum='+e.value,
			onSuccess:function(checkr)
				{
					var che=JSON.parse(checkr);
					if(che.checkResult)
					{
						var u='cart/update?itemID='+id+'&itemNumber='+e.value;
						Ajax({
						url:u,
						onSuccess:function(r)
							{
								var d=JSON.parse(r);
								if(d.updateResult)
								{
									location.reload();
									/*e.setAttribute('default',e.value);
									get_session({S:function(s)
										{
										num.innerHTML=s.totalNumber;
										}
									});*/
								}
								else alert('商品数量修改失败！')
							}
						})
					}
					else
					{
						alert('抱歉，库存不够，您可以先收藏该商品');
						e.value=e.getAttribute('default');
					}
				}
		})
	}
}





