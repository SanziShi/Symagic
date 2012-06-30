// JavaScript Document

//下拉日期选择
function DateSelector(selYear, selMonth, selDay)
{
    this.selYear = selYear;
    this.selMonth = selMonth;
    this.selDay = selDay;
    this.selYear.Group = this;
    this.selMonth.Group = this;
    // 给年份、月份下拉菜单添加处理onchange事件的函数
    if(window.document.all != null) // IE
    {
        this.selYear.attachEvent("onchange", DateSelector.Onchange);
        this.selMonth.attachEvent("onchange", DateSelector.Onchange);
    }
    else // Firefox
    {
        this.selYear.addEventListener("change", DateSelector.Onchange, false);
        this.selMonth.addEventListener("change", DateSelector.Onchange, false);
    }

    if(arguments.length == 4) // 如果传入参数个数为4，最后一个参数必须为Date对象
        this.InitSelector(arguments[3].getFullYear(), arguments[3].getMonth() + 1, arguments[3].getDate());
    else if(arguments.length == 6) // 如果传入参数个数为6，最后三个参数必须为初始的年月日数值
        this.InitSelector(arguments[3], arguments[4], arguments[5]);
    else // 默认使用当前日期
    {
        var dt = new Date();
        this.InitSelector(dt.getFullYear(), dt.getMonth() + 1, dt.getDate());
    }
}

// 增加一个最小年份的属性
DateSelector.prototype.MinYear = 2007;

// 增加一个最大年份的属性
DateSelector.prototype.MaxYear = (new Date()).getFullYear();

// 初始化年份
DateSelector.prototype.InitYearSelect = function()
{
    // 循环添加OPION元素到年份select对象中
    for(var i = this.MaxYear; i >= this.MinYear; i--)
    {
        // 新建一个OPTION对象
        var op = window.document.createElement("OPTION");
        
        // 设置OPTION对象的值
        op.value = i;
        
        // 设置OPTION对象的内容
        op.innerHTML = i;
        
        // 添加到年份select对象
        this.selYear.appendChild(op);
    }
}

// 初始化月份
DateSelector.prototype.InitMonthSelect = function()
{
    // 循环添加OPION元素到月份select对象中
    for(var i = 1; i < 13; i++)
    {
        // 新建一个OPTION对象
        var op = window.document.createElement("OPTION");
        
        // 设置OPTION对象的值
        op.value = i;
        
        // 设置OPTION对象的内容
        op.innerHTML = i;
        
        // 添加到月份select对象
        this.selMonth.appendChild(op);
    }
}

// 根据年份与月份获取当月的天数
DateSelector.DaysInMonth = function(year, month)
{
    var date = new Date(year, month, 0);
    return date.getDate();
}

// 初始化天数
DateSelector.prototype.InitDaySelect = function()
{
    // 使用parseInt函数获取当前的年份和月份
    var year = parseInt(this.selYear.value);
    var month = parseInt(this.selMonth.value);
    
    // 获取当月的天数
    var daysInMonth = DateSelector.DaysInMonth(year, month);
    
    // 清空原有的选项
    this.selDay.options.length = 0;
    // 循环添加OPION元素到天数select对象中
    for(var i = 1; i <= daysInMonth ; i++)
    {
        // 新建一个OPTION对象
        var op = window.document.createElement("OPTION");
        
        // 设置OPTION对象的值
        op.value = i;
        
        // 设置OPTION对象的内容
        op.innerHTML = i;
        
        // 添加到天数select对象
        this.selDay.appendChild(op);
    }
}

// 处理年份和月份onchange事件的方法，它获取事件来源对象（即selYear或selMonth）
// 并调用它的Group对象（即DateSelector实例，请见构造函数）提供的InitDaySelect方法重新初始化天数
// 参数e为event对象
DateSelector.Onchange = function(e)
{
    var selector = window.document.all != null ? e.srcElement : e.target;
    selector.Group.InitDaySelect();
}

// 根据参数初始化下拉菜单选项
DateSelector.prototype.InitSelector = function(year, month, day)
{
    // 由于外部是可以调用这个方法，因此我们在这里也要将selYear和selMonth的选项清空掉
    // 另外因为InitDaySelect方法已经有清空天数下拉菜单，因此这里就不用重复工作了
    this.selYear.options.length = 0;
    this.selMonth.options.length = 0;
    
    // 初始化年、月
    this.InitYearSelect();
    this.InitMonthSelect();
    
    // 设置年、月初始值
    this.selYear.selectedIndex = this.MaxYear - year;
    this.selMonth.selectedIndex = month - 1;
    
    // 初始化天数
    this.InitDaySelect();
    
    // 设置天数初始值
    this.selDay.selectedIndex = day - 1;
}



// Create a JSON object only if one does not already exist. We create the

var JSON;
if (!JSON) {
    JSON = {};
}

(function () {
    'use strict';

    function f(n) {
                return n < 10 ? '0' + n : n;
    }

    if (typeof Date.prototype.toJSON !== 'function') {

        Date.prototype.toJSON = function (key) {

            return isFinite(this.valueOf())
                ? this.getUTCFullYear()     + '-' +
                    f(this.getUTCMonth() + 1) + '-' +
                    f(this.getUTCDate())      + 'T' +
                    f(this.getUTCHours())     + ':' +
                    f(this.getUTCMinutes())   + ':' +
                    f(this.getUTCSeconds())   + 'Z'
                : null;
        };

        String.prototype.toJSON      =
            Number.prototype.toJSON  =
            Boolean.prototype.toJSON = function (key) {
                return this.valueOf();
            };
    }

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        gap,
        indent,
        meta = {                '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        },
        rep;


    function quote(string) {


        escapable.lastIndex = 0;
        return escapable.test(string) ? '"' + string.replace(escapable, function (a) {
            var c = meta[a];
            return typeof c === 'string'
                ? c
                : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
        }) + '"' : '"' + string + '"';
    }


    function str(key, holder) {


        var i,                      k,                      v,                      length,
            mind = gap,
            partial,
            value = holder[key];


        if (value && typeof value === 'object' &&
                typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }


        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }


        switch (typeof value) {
        case 'string':
            return quote(value);

        case 'number':


            return isFinite(value) ? String(value) : 'null';

        case 'boolean':
        case 'null':


            return String(value);


        case 'object':


            if (!value) {
                return 'null';
            }


            gap += indent;
            partial = [];


            if (Object.prototype.toString.apply(value) === '[object Array]') {


                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }


                v = partial.length === 0
                    ? '[]'
                    : gap
                    ? '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']'
                    : '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }


            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    if (typeof rep[i] === 'string') {
                        k = rep[i];
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            } else {


                for (k in value) {
                    if (Object.prototype.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            }


            v = partial.length === 0
                ? '{}'
                : gap
                ? '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}'
                : '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
    }


    if (typeof JSON.stringify !== 'function') {
        JSON.stringify = function (value, replacer, space) {


            var i;
            gap = '';
            indent = '';


            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1) {
                    indent += ' ';
                }


            } else if (typeof space === 'string') {
                indent = space;
            }


            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                    (typeof replacer !== 'object' ||
                    typeof replacer.length !== 'number')) {
                throw new Error('JSON.stringify');
            }


            return str('', {'': value});
        };
    }



    if (typeof JSON.parse !== 'function') {
        JSON.parse = function (text, reviver) {


            var j;

            function walk(holder, key) {


                var k, v, value = holder[key];
                if (value && typeof value === 'object') {
                    for (k in value) {
                        if (Object.prototype.hasOwnProperty.call(value, k)) {
                            v = walk(value, k);
                            if (v !== undefined) {
                                value[k] = v;
                            } else {
                                delete value[k];
                            }
                        }
                    }
                }
                return reviver.call(holder, key, value);
            }



            text = String(text);
            cx.lastIndex = 0;
            if (cx.test(text)) {
                text = text.replace(cx, function (a) {
                    return '\\u' +
                        ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                });
            }



            if (/^[\],:{}\s]*$/
                    .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
                        .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
                        .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {


                j = eval('(' + text + ')');


                return typeof reviver === 'function'
                    ? walk({'': j}, '')
                    : j;
            }
            throw new SyntaxError('JSON.parse');
        };
    }
}());

function change_captcha(e)
{
	e.src='captcha_get_captcha';
}

//异步调用函数
Ajax=function (option){
	option=
	{
		type:option.type||'POST',
		url:option.url||'',
		async:option.async||'ture',
		timeout:option.timeout||5000,
		onComplete:option.onComplete||function(){},
		onError:option.onError||function(){},
		onSuccess:option.onSuccess||function(){},
		onSend:option.onSend||function(){},
		onTimeout:option.onTimeout||function(){},
		acceptdatatype:option.acceptdatatype||'json',
		data:option.data||''
	};
	var ajax;
	var timer;
	if(typeof XMLHttpRequest=='undefined')
	{
		ajax=new ActiveXObject("Microsofr.XMLHttp");
	}
	else 
	{
		ajax=new XMLHttpRequest();
	}
	ajax.open(option.type,option.url,option.async);
	if(option.type=='GET')
	{
		ajax.setRequestHeader("If-Modified-Since","0"); 
		ajax.send();
	}
	else 
	{
		switch(option.acceptdatatype)
		{
			case 'json':ajax.setRequestHeader("Accept",'application/json, text/javascript');
				ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				break;
			default :ajax.setRequestHeader("Accept",'application/json, text/javascript');
				ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		}
		ajax.setRequestHeader("If-Modified-Since","0"); 
		ajax.send(option.data);
	}
	timer=setTimeout(function()
		{
			if(typeof option.onTimeout=="function") option.onTimeout();
			if(ajax)
			{
				ajax.abort();
				ajax=null;
			}
			return ture;
		}
		,option.timeout);
	ajax.onreadystatechange=function()
	{
		switch (ajax.readyState)
		{
			case 0: 
				break;
			case 1:
				break;
			case 2:option.onSend();
				break;
			case 3:
				break;
			case 4:
				try
				{
					switch(ajax.status)
					{
						case 200:if(timer)clearTimeout(timer);
							option.onSuccess(ajax.responseText);
							ajax=null;
							break;
						case 404:if(timer)clearTimeout(timer);
							option.onError(ajax.responseText);
							ajax=null;
							break;
						default:if(timer)clearTimeout(timer);
							option.onComplete(ajax.responseText);
							ajax=null;
					}
				}catch(e){}
			default:break;
		}
		//alert('ajax.status:'+ajax.status+"  ajax.readyState:"+ajax.readyState);
	}

}

function delete_tag(id){
	var tag = document.getElementById(id);
	tag.parentNode.removeChild(tag);
	}

//订单管理中删除一个订单
function ajax_delete_order(id){
	var result = confirm("该操作将会将订单删除，确定继续吗？");
	if(result==true){
	Ajax({
					url:'order/delete/?orderID='+id,
				    type:'GET',
					onSend:function(){},
					onSuccess:function(e)
						{
							/*var t=document.createElement('div');
							t.id='cart_container';
							t.innerHTML=e;
							document.getElementById('cart').appendChild(t);
							t=null;*/
							 var obj = JSON.parse(e);
							 if(e.deleteResult==true){
							 var tag = document.getElementById(id);
                             tag.parentNode.removeChild(tag);
							 alert("删除成功！");
							 }
							 else alert("删除出错，请返回重新尝试！");
						}
						});
	}
         
	}

//商品管理中删除一个商品
function ajax_delete_tag(id){
	var result = confirm("该操作将会将商品移除，确定继续吗？");
	if(result==true){
	Ajax({
					url:'item_manager/delete?itemID='+id,
				    type:'GET',
					onSend:function(){},
					onSuccess:function(e)
						{
							/*var t=document.createElement('div');
							t.id='cart_container';
							t.innerHTML=e;
							document.getElementById('cart').appendChild(t);
							t=null;*/
							 var obj = JSON.parse(e);
							 if(obj.deleteResult==true){
							 var tag = document.getElementById(id);
                             tag.parentNode.removeChild(tag);
							  alert("删除成功！");
							 }
							 else alert("删除出错，请返回重新尝试！");
						}
						});
	}
         
	}

//商品下架
function ajax_item_off(e,id){
	var result = confirm("该操作将会将商品下架，确定继续吗？");
	Ajax({
	                url:'item_manager/off?itemID='+id,
				    type:'GET',
					onSend:function(){},
					onSuccess:function()
						{
							/*var t=document.createElement('div');
							t.id='cart_container';
							t.innerHTML=e;
							document.getElementById('cart').appendChild(t);
							t=null;*/
							set_value(e);
							var obj = JSON.parse(e);
							 if(obj.deleteResult==true){
							 var tag = document.getElementById(id);
                            tag.parentNode.removeChild(tag);
							  alert("下架成功！");
							 }
							 else alert("下架出错，请返回重新尝试！");
						}
						});
	
	
	}
	

	
function show_item_search(e)
{
	if(e.className=='collapse')
	{
		e.className='collapsed';
		$('#item_search1').slideDown(70);
	}
	else
	{
		e.className='collapse';
		$('#item_search1').slideUp(70);
	}
}

function expanse(e)
{
	
		e.className='collapsed';
		$('#item_search1').slideDown(70);
	
}

function set_value(e){
	if(e.value=="下架")
	e.value = "上架";
	else 
	e.value = "下架";
	}
	
//商品数量增加、减少
function add(id)
{
	document.getElementById(id).value=parseInt(document.getElementById(id).value)+1;
}
function reduce(id)
{
	if(document.getElementById(id).value>1)document.getElementById(id).value=parseInt(document.getElementById(id).value)-1;
}
//数量修改
function amount_modify(e)
{
	e.value=e.value.replace(/\D+/g,'');
}
