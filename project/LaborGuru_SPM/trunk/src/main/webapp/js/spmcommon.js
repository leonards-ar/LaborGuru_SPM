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

function toInt(n) {
	if(isNaN(n)) {
		return 0;
	} else {
		return parseInt(n, 10);
	}
}

function formatTimeNumber(n) {
	n = '' + n;
	
	if(n == null || n == '' || n == '-' || isNaN(n)) {
		return '00';
	}
	
	if(n.length < 2) {
		return '0' + n;
	}
	
	return n;
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
	
	if(message != null && message != '') {
		var emo = getObjectByID('extendedWaitMessage');
		if(emo) {
			emo.innerHTML = message;
		}
	}
	return true;
}

/*
 * Parses the input time to the standard format HH:MM
 * The rules are the following:
 * 4, 4a, 4am, 4:00, 4:00 AM, 4 am, 4: ==> 04:00
 * 18, 6p, 6pm, 18:00, 6:00 PM, 6 pm, 18: ==> 18:00
 * 
 * Regular expression: (\d*):*(\d\d)\s*(\w*)
 */
/* CASE 1: hmm[pm] */
var TIME_REGEXP1 = /^\s*(\d{1})(\d\d){1}\s*([aApP][mM]*)*\s*$/;

/* CASE 2: hhmm[pm] */
var TIME_REGEXP2 = /^\s*(\d\d){1}(\d\d){1}\s*([aApP][mM]*)*\s*$/;

/* CASE 3: h[pm], hh[pm], h:[mm][pm], hh:[mm][pm] */
var TIME_REGEXP3 = /^\s*(\d*)[:.]*(\d\d)*\s*([aApP][mM]*)*\s*$/;

function getRegExpMatch(timeTxt) {
	var m = null;
	m = TIME_REGEXP1.exec(timeTxt);
	if(m) { return m;}
	m = TIME_REGEXP2.exec(timeTxt);
	if(m) { return m;}
	return TIME_REGEXP3.exec(timeTxt);
} 
 
function parseTime(timeTxt) {
	var m = getRegExpMatch(timeTxt);
	if(m == null) {
		return '';
	} else {
		var hs = 0;
		var mins = 0
		var d = null;
		// Hours m[1]
		if(m.length > 0 && m[1]) {
			hs = toInt(m[1]);
		}
		// Minutes m[2]
		if(m.length > 1 && m[2]) {
			mins = toInt(m[2]);
		}
		// AM/PM m[3]
		if(m.length > 2 && m[3]) {
			d = m[3];
			if(d && (d.toLowerCase() == 'p' || d.toLowerCase() == 'pm') && hs >= 0 && hs < 12) {
				hs = hs + 12;
			}
		}
		return formatTimeNumber(hs) + ':' + formatTimeNumber(mins);
	}
}

function updateTime(formElement) {
	formElement.value = parseTime(formElement.value);
}