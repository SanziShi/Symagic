function click_left_b(id)
{
	write_cookie('left_banner_list',id);
	location.href='quick_search?page=1&catalogID='+id+'&keyword=';
}
function quick_search_a(data)
{
	location=encodeURIComponent(data);
	
}
$().ready(function(e) {
    var left_id=read_cookie('left_banner_list');
	document.getElementById(left_id).className='selected';
});