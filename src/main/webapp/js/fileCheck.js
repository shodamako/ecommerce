window.onload = function() {
	document.getElementById("chkButton").onclick = function() {
		var upFiles = document.getElementById("image").files;
		for (var i = 0; i < upFiles.length; i++) {
			if (upFiles[i].size >= 5120000) {
				alert("選択したファイルのサイズが5,120kbを超えています。");
			}else{
				alert("選択したファイルのサイズは許容範囲内です。");
			}
		}
	}
}