// JavaScript Document
function change_captcha(e)
{
	e.src='captcha_get_captcha';
}

function delete_tag(id){
	var tag = document.getElementById(id);
	tag.parentNode.parentNode.removeChild(tag);
	}
