	function setCookie(key, value,expiresDay) {
        var cookie = "";
        var now = new Date();
        var exdate = new Date(now.getTime()+expiresDay*24*60*60*1000);
        cookie+=key+"="+value+"; expires="+exdate.toString();
        document.cookie = cookie;
    }

	function deleteCookieByName(name){
        setCookie(name,null,0);
    }


    function findCookieByName(name) {
        var cookieArray = document.cookie.split("; ");
        var findCookieValue = "";

        for (var i = 0; i < cookieArray.length ; i++) {
            var cookie = cookieArray[i];

            if(name == cookie.split("=")[0]){
                findCookieValue=cookie.split("=")[1];
                break;
            }

        }
        return findCookieValue;
    }

    function findAllCookie() {
        return  document.cookie.split("; ");
    }