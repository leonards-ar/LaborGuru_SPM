function breakout_of_frame() {
  if (top.location != location) {
    top.location.href = document.location.href ;
  }
}
		
function getObjectByID(objectId) {
	if(document.getElementById) {
		return document.getElementById(objectId);
	} else if (document.all) {
		return document.all[objectId];
	} else if (document.layers) {
		return document.layers[objectId];
	} else {
		return null;
	}
}

function showWaitSplash() {
	return showSplash('splash', 'content', '');
}

function showWaitSplashWithMessage(message) {
	return showSplash('splash', 'content', message);
}

function showSplash(splashId, contentId, message) {
	var so = getObjectByID(splashId);
	var co = getObjectByID(contentId);
	if(co && co.style) {
		co.style.opacity = "0.40";
		co.style.filter = "alpha(opacity='40')";
	}
	
	if(so && so.style) {
		so.style.visibility = "visible";
		var sw = screen.width;
		so.style.left = Math.round((sw/2) - 100);
	}
	return true;
}