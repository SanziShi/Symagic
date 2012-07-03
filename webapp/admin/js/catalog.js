// JavaScript Document
//function click_show(id){
//	var t = $('.'+"id");
//	alert(t[0]);
//	alert("wasdkkasdkfa");
//	for(i = 0; i < $t.lenth;i++){
//	var t = $t[i];
//	}
	
	

function show(table, sum) {
		var t;
		t = document.getElementById(table);
		if (t.style.display == "") {
			t.style.display = "none";
		} else {
			t.style.display = "";
		}
		for (i = 0; i < sum; i++) {
			if (("t" + i) != table) {
				t = document.getElementById("t" + i);
				t.style.display = "none";
			}
		}
	}
	
function click_show(id,num){
	var t;
	for(i = 0; i < num; i++){
		t = document.getElementById(id + i);
		if (t.style.display == "none") {
			t.style.display = "";
		} else {
			t.style.display = "none";
		}
		}
	}
	
	/*for (i = 0; i < sum; i++) {
		if (("t" + i) != table) {
			t = document.getElementById("t" + i);
			t.style.display = "none";
		}
	}*/
	
