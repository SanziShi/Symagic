// JavaScript Document
function click_show(id){
	var t;
	t = document.getElementByName(id);
	for (i = 0; i < id.length; i++) {
		if (t[i].style.display == "") {
		t[i].style.display = "none";
	} else {
		t[i].style.display = "";
	}
		}
	}
	
	/*for (i = 0; i < sum; i++) {
		if (("t" + i) != table) {
			t = document.getElementById("t" + i);
			t.style.display = "none";
		}
	}*/
	
