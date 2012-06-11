GLOBAL_CONFIG=
{
	dir:'Symagic2/',
	lib:'lib/'
}
//业务逻辑函数
function load_login()
{
	if(window.event)stopDefault();
	var l=document.createElement('div');
	l.id='login_float';
	showOverlay();
	Ajax({
		url:GLOBAL_CONFIG.lib+'login.html',
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
		url:GLOBAL_CONFIG.lib+'regist.html',
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
		url:'log',
		type:'POST',
		data:login_form,
		onSuccess:function(e){
			alert(e);
		}
	})
	alert(get_session());
	return false;
}
function regist(form) 
{
	var regist_form=$(form).serialize();
	alert(regist_form);
	return false;
	
}
function safe_question(e)
{
	if(e.value=='自定义问题')
	{
		e.parentNode.parentNode.nextSibling.nextSibling.style.display='table-row';
	}
	else e.parentNode.parentNode.nextSibling.nextSibling.style.display='none';
}
function close_float(elem)
{
	var f=elem.parentNode.parentNode.parentNode;
	$(f).fadeOut('fast',function(){
		f.parentNode.removeChild(f);
		hideOverlay();
		});
	
}
//ADS库函数
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
						case 404:
							break;
						case 500:
							break;
						case 503:
							break;
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
	}
	else return 1;
}
function onfocus_check(elem,default_text)
{
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
	pre.style.display='inline-block';
	pre.focus();
}
function password_onblur(elem)
{
	elem.value=trim(elem.value);
	if(elem.value=='')
	{
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