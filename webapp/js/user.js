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
function address_edit(e)
{
	alert(e.event);
	showOverlay();
	document.getElementById('test').style.display='block';
}
function close_address(e)
{
	var p=e.parentNode.parentNode;
	p.style.display='none';
	hideOverlay();
}
$().ready(function(e) {
    addListener(document.getElementById('t1'),"click",function(e){
		stopDefault(e);
		var y=getY(e);
		var t=document.getElementById('test');
		showOverlay();
		t.style.top=y-t.style.height+'px';
		t.style.display='block';
		});
});