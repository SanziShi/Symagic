// JavaScript Document
function change_captcha(e)
{
	e.src='captcha_get_captcha';
}

function delete_tag(id){
	var tag = document.getElementById(id);
	tag.parentNode.parentNode.removeChild(tag);
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
