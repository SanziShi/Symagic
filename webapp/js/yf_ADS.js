GLOBAL=
{
	dir:'Symagic2/',
	lib:'lib/',
	cart_buff:''

}
//业务逻辑函数
function change_captcha(e)
{
	e.src='captcha_get_captcha';
}
function load_login()
{
	if(window.event)stopDefault();
	var l=document.createElement('div');
	l.id='login_float';
	showOverlay();
	Ajax({
		url:GLOBAL.lib+'login.html',
		type:'GET',
		onSuccess:function(e){
			l.innerHTML=e;
			document.body.appendChild(l);
			$(l).fadeIn('fast');
			}
	})
}

function load_regist()
{
	if(window.event)stopDefault();
	var r=document.createElement('div');
	r.id='regist_float';
	showOverlay();
	Ajax({
		url:GLOBAL.lib+'regist.html',
		type:'GET',
		onSuccess:function(e){
			r.innerHTML=e;
			document.body.appendChild(r);
			$(r).fadeIn('fast');
			}
	})
}
function get_session()
{
	Ajax({url:'get_session_info',async:false,onSuccess:function(e){return e;}})
}
function login(form)
{
	var login_form=$(form).serialize();
	Ajax({
		url:'login',
		type:'POST',
		data:login_form,
		onSuccess:function(e){},
		onError:function(){
			location.pathname='/index.html'}
	})
	return false;
}
function regist(form) 
{
	var regist_form=$(form).serialize();
	Ajax({
		url:'regist',
		type:'POST',
		data:regist_form,
		onSuccess:function(e){
		}
	})
	return false;
	
}
function safe_question(e)
{
	var p=e.parentNode.parentNode.nextSibling.nextSibling;
	var q=p.firstChild.nextSibling.nextSibling.nextSibling.firstChild.nextSibling;
	if(e.value=='自定义问题')
	{
		q.name='securityQuestion';
		e.name='';
		p.style.display='table-row';
	}
	else 
	{
		if(!e.name||q.name){e.name='securityQuestion';q.name='';}
		p.style.display='none';
	}
}
function clear_notice()
{
	var a=document.body.childNodes;
	var n=new Array();
	for(var b=0;a[b];++b)
	{
		if(a[b].getAttribute)
		{
			if(typeof(a[b].getAttribute('id'))=='string'&&a[b].getAttribute('id').indexOf('JunLu')>-1)
			{
				n.push(a[b]);
			};
		}
	};
	for (x in n)document.body.removeChild(n[x]);
}
function close_float(elem)
{
	var a=document.body.childNodes;
	var n=new Array();
	for(var b=0;a[b];++b)
	{
		if(a[b].getAttribute)
		{
			if(typeof(a[b].getAttribute('id'))=='string'&&a[b].getAttribute('id').indexOf('JunLu')>-1)
			{
				n.push(a[b]);
			};
		}
	};
	for (x in n)document.body.removeChild(n[x]);
	var f=elem.parentNode.parentNode.parentNode;
	$(f).fadeOut('fast',function(){
		f.parentNode.removeChild(f);
		hideOverlay();
		});
}
function show_user_con(num)
{
	for(var x=1;x!=5;++x)
	{
		if(x!=num)
		{
			//if(document.getElementById(x).style&&document.getElementById(x).style.display=='block')
			$('#'+x).slideUp(1);
		}
	}
	//if(!document.getElementById(x).style||document.getElementById(x).style.display!='block')
	$('#'+num).slideDown(250);
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

/*--------------yf_ADS库函数-------------------*/
//增加事件监听注册器
function addListener(element,e,fn){
     if(element.addEventListener){
          element.addEventListener(e,fn,false);
     } else {
          element.attachEvent("on"+e,fn);
     }
} 
//增加contains原型函数
/* p=parentNode, c=childNode */
function contains(p,c)
{  
    return p.contains ? p != c && p.contains(c) : !!(p.compareDocumentPosition(c) & 16);  
}
/* e即为事件，target即为绑定事件的节点 */
function mouseover_check(e,target)
{
	var related,
	//type=e.type.toLowerCase();//这里获取事件名字
	related=e.relatedTarget||e.fromElement;
	return related && related.prefix!='xul' && !contains(target,related) && related!==target;
}
function mouseout_check(e,target)
{
	var related,
	type=e.type.toLowerCase();//这里获取事件名
	related=e.relatedTarget||e.toElement;
	return related && related.prefix!='xul' && !contains(target,related) && related!==target;
}
//阻止默认事件函数
function stopDefault(e){ 
	if (e&&e.preventDefault)
	{
		e.preventDefault(); 
	}
	else 
	{
		window.event.returnValue = false; 	
	}
    return false; 
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
		acceptdatatype:option.acceptdatatype||'json',
		data:option.data||''
	};
	var ajax;
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
		ajax.send()
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
	ajax.onreadystatechange=function()
	{
		switch (ajax.readyState)
		{
			case 0: 
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				try
				{
					switch(ajax.status)
					{
						case 200:option.onSuccess(ajax.responseText);
							break;
						case 403:
							break;
						case 404:option.onError(ajax.responseText);
							break;
						case 500:
							break;
						case 503:
							break;
						default:option.onComplete(ajax.responseText);
					}
				}catch(e){}
			default:break;
		}
		//alert('ajax.status:'+ajax.status+"  ajax.readyState:"+ajax.readyState);
	}
}

//覆盖层
function showOverlay()
{
	var overlay=document.createElement('div');
	overlay.id='overlay';
	document.body.appendChild(overlay);
	$(overlay).fadeIn('fast');
}	


function hideOverlay()
{
	$('#overlay').fadeOut('fast',function(){$('#overlay').remove()});
}

//表单相关验证函数
function onblur_check(elem,default_text)
{
	elem.value=trim(elem.value);
	if(elem.value=='')
	{
		elem.value=default_text;
		elem.style.color='rgb(153, 153, 153)';
		switch(elem.value)
		{
			case '邮箱地址':var a=Stip(elem);a.show({content:"请输入正确的邮箱地址",kind:'error'});	
			elem.setAttribute('msg_num',a.id);
				break;
			case '由中文、英文、数字及“_”组成':var a=Stip(elem);a.show({content:"昵称长为度5-20位字符",kind:'error'});
			elem.setAttribute('msg_num',a.id);
				break;
		}
	}
	else return 1;
}
function onfocus_check(elem,default_text)
{
	var id;
	if(id=document.getElementById(elem.getAttribute('msg_num')))id.parentNode.removeChild(id);
	if(elem.value==default_text)
	{
		elem.value='';
		elem.style.color='rgb(51, 51, 51)';
	}
	else return 1;
}
function passwordtext_onfocus(elem)
{
	elem.style.display='none';
	var pre=elem.previousSibling.previousSibling;
	if(id=document.getElementById(pre.getAttribute('msg_num')))id.parentNode.removeChild(id);
	pre.style.display='inline-block';
	pre.focus();
}
function password_onblur(elem)
{
	elem.value=trim(elem.value);
	if(elem.value=='')
	{
		var a=Stip(elem);
		a.show({content:"请输入6-20位字符密码",kind:'error'});	
		elem.setAttribute('msg_num',a.id);
		elem.style.display='none';
		elem.nextSibling.nextSibling.style.display='inline-block';
	}
}
function is_account(account)
{
	//var exp=/^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
	var exp=/^[^';,"\{\}\[\]]{3,15}$/;
	if(exp.exec(account))return 1;
	else return false;
}
function is_email(email)
{
	var exp=/^\w+((-\w+)|(\.\w+))*\@[a-z0-9]+((\.|-)[a-z0-9]+)*\.[a-z0-9]+$/i;
	if(exp.exec(email))return 1;
	else return false;
}
function is_password(password)
{
	//var exp=/^~[']{5,17}$/;
	//if(exp.exec(password))return 1;
	//else return false;
	return 1;
}
function pageHeight()
{
	return document.body.scrollHeight;
}
function pageWidth()
{
	return document.body.scrollWidth;
}
function trim(str)
{
     return str.replace(/(^\s*)|(\s*$)/g,'');
}



/**************
气泡提示源码
*****************/

;;(function(win, namespace, undef){
	var D = {
		$:function(id){return document.getElementById(id);},
		gt:function(parent, nodeName){return  parent.getElementsByTagName(nodeName);},
		db:document.body,
		dd:document.documentElement,
		i:0, // 最外层DOM元素开始数
		mix:function(r, s, a){
			for(var i in s){
				r[i] = s[i];
			}
			return r;
		},
		html:"<div class=\"lj-tipsWrap lj-<%=kind%>\" id=\"tipsWrap-<%=r%>\">\
						<div class=\"lj-content\"></div>\
						<span class=\"lj-in lj-<%=p%>\"><span class=\"lj-in lj-span\"></span></span>\
						<a href=\"javascript:void(0)\" id=\"ljClose<%=r%>\" class=\"lj-close\">x</a>\
					</div>"
	}
	
	/* 可配置参数 */
	var defaultConfig = {
		prefix: 'JunLu', // 最外层DOM元素ID前缀
		p: 'right', // 默认方向
		kind: 'correct', // 类型 correct or error
		closeP: 'ljClose', // 关闭按钮前缀
		wrapP: 'tipsWrap-', //
		closeBtn: false, // 默认是否有关闭按钮
		time:null, // 默认显示时间 一直显示
		offset: null,
		content:function(){ return "Hello World";},//默认内容
		of: 15,
		rand: 0
	}
	/* 可配置参数 end */
	
	var TIP = function(id){
		if( !(this instanceof TIP)){
			return new TIP(id);
		}		
		this.elem = id ? typeof id == "string" ? D.$(id) : id : this;
		this.defaultConfig = D.mix({}, defaultConfig);
		this._config = {};
		this.clearTime = null;
		this.func = null;
		(D.db !== document.body) && this._init(); // 防止 D.db 对象加载失败
	}
	
	TIP.prototype = {
		// 显示
		show:function(json){
			
			var self = this, config = self._config,
				wrap, p, c, sp, type = Object.prototype.toString.call(json),
				content = self.defaultConfig.content;
			
			// 不传递参数 和不传递 .content 参数
			if( !json || (json && !json.content) ){
				json = D.mix(json || {}, {content: typeof content == "function" ? content.call(self.elem, self.elem) : content})
			}
			
			// 参数为 String 或者 Number
		    if(/String|Number/.test(type)){
				json = {content:json};
			}
			
			//参数为一个function
			if("[object Function]" == type){
				json = {content:json.call(self.elem, self.elem)};
			}
			
			D.mix(config, self.defaultConfig); // 调用默认参数配置
			D.mix(config, json); // 覆盖默认参数配置
	
			self._updateInfo();
			self.id && self.hide()
			
			wrap = self._append();
			
			D.gt(wrap, "DIV")[0].innerHTML = config.content ;
			p 	= self._pos.call(self, config.p, wrap.offsetWidth, wrap.offsetHeight);
			sp	= self._getScroll();


			wrap.style.top = p.top + sp.top + "px";
			wrap.style.left = p.left + sp.left + "px";
			
			self._winSizeCheck(wrap);
			if(config.time){
				self.clearTime = setTimeout(function(){self.hide(c)}, config.time);
			}
			return false;
		},
		// 隐藏Stip
		hide:function(){
			var self = this
			self.clearTime && clearTimeout(self.clearTime);
			self._clear(D.$(self.id));
		},
		
		_init:function(){
			D.mix(D,{dd:document.body, db:document.documentElement});
		},
		_clear:function(a){
			var config = this._config;
			a && a.parentNode && a.parentNode.removeChild(a);
			win.detachEvent ? win.detachEvent('onresize', this.func) : win.removeEventListener('resize', this.func, false);
		},
		
		// 更新位置大小信息
		_updateInfo:function(){
			var self = this, elem = self.elem, config = self._config;
			config.width	= elem.offsetWidth;
			config.height	= elem.offsetHeight;
			config.offset	= elem.getBoundingClientRect();
		},
		
		// 内部方法
		_append:function(){
			var self = this , config = self._config,
				r, x;
				
			r = config.rand = ++D.i
			x = document.createElement("DIV");
			x.id = config.prefix + r;
			self.id = x.id;
			
			x.innerHTML = D.html.replace("<%=p%>",config.p).replace(/<%=r%>/g, r).replace("<%=kind%>", config.kind);
			document.body.appendChild(x);
			
			if(config.closeBtn){ // 有关闭按钮
				var hide = function(){self.hide();}
				D.$(config.closeP + r).onclick = hide;
			}else{
				D.$(config.closeP + r).style.display = "none";
			}
			
			return D.$(config.wrapP + r);
		},
		// 内部方法
		_pos:function(p,w,h){
				var self = this, C = self._config;
				var a = {
					left	: function(w, h){return {"top":C.offset.top , "left":C.offset.left - w - C.of}},
					top		: function(w, h){return {"top":C.offset.top - h - C.of, "left":C.offset.left}},
					right	: function(w, h){return {"top":C.offset.top , "left":C.offset.left + C.width + C.of}},
					bottom	: function(w, h){return {"top":C.offset.top + C.height + C.of , "left":C.offset.left}}
				}
				
				return a[p](w,h);
		},
		// 内部方法
		_getScroll:function(){
			return {
				top: D.db.scrollTop + D.dd.scrollTop,
				left: D.db.scrollLeft + D.dd.scrollLeft
			}
		},
		// 内部方法
		_winSizeCheck:function(wrap){
			var self = this, config = self._config;
			self.func = function(){
				self._updateInfo();
				var p 	= self._pos.call(self, config.p, wrap.offsetWidth, wrap.offsetHeight);
				var sp	= self._getScroll();
				
				wrap.style.top = p.top + sp.top + "px";
				wrap.style.left = p.left + sp.left + "px";
			};
			win.attachEvent ? win.attachEvent('onresize', self.func) : win.addEventListener('resize', self.func, false);
		}
	}
	
	win[namespace] = TIP; // 声明命名空间
	win[namespace]['config'] = defaultConfig; // 静态默认配置
	
})(window, 'Stip');

/**************
气泡提示源码
*****************/


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


$().ready(function() {
	addListener(document.getElementById('cart_li'),"mouseover",function(e)
	{
		e=e||window.event;
		if(mouseover_check(e,document.getElementById('cart_li')))
		{
			if(document.getElementById('cart_li').className.indexOf('hover')==-1)
			{
				document.getElementById('cart_li').className+=' hover';
				document.getElementById('cart_a').className+=' hover';
			}
		$('#cart').fadeIn('fast');
		}
	});
	addListener(document.getElementById('cart_li'),"mouseout",function(e){
		e=e||window.event;
		if(mouseout_check(e,document.getElementById('cart_li')))
		{
			GLOBAL.cart_buff=setTimeout(function(){
				document.getElementById('cart_li').className='';
				document.getElementById('cart_a').className='';
				$('#cart').fadeOut(1);}
			,10);
		}
	});
	addListener(document.getElementById('cart'),"mouseover",function(e){
		//alert('test')
		e=e||window.event;
		//var c=document.getElementById('cart');
		if(mouseover_check(e,document.getElementById('cart')))
		{
			clearTimeout(GLOBAL.cart_buff);
		}
	});
	addListener(document.getElementById('cart'),"mouseout",function(e){
		e=e||window.event;
		if(mouseout_check(e,document.getElementById('cart')))
		{
			document.getElementById('cart_li').className='';
			document.getElementById('cart_a').className='';
			$('#cart').fadeOut(1);
		}
	});
});
