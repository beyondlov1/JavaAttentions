## BOM ##
- window.innerWidth window.outlineWidth: 窗口大小
- window.lcoation: 本页url(href)
- window.history: 前进后退
- window.navigator: 获得浏览器相关性信息
- window.screen

## 弹出框 ##
- alert()
- prompt("提示信息", 默认值): 返回字符串
- confirm("提示信息"):返回boolean

## bootstrap jquery ##
- buttons
- icheck
- 各种组件

## 计时 ##
- setTimeOut("showTime()",1000)
- clearTimeOut()
- setInterval() clearInterval()

	//计时器 demo
	function showTime() {
        var now = new Date();
        var hour = now.getHours()
        var min = now.getMinutes()
        var sec = now.getSeconds()
        if (hour < 10) {
            hour = "0" + hour
        }
        if (min < 10) {
            min = "0" + min
        }
        if (sec < 10) {
            sec = "0" + sec
        }
        clock.innerText = hour + " : " + min + " : " + sec;
        setTimeout("showTime()", 1000)
    }

    setTimeout("showTime()", 1000)

## cookie ##

- document.cookie="key=value"
- escape unescape/encodeURI decodeURI
- 增删改查demo
	

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


## DOM ##

- document.createElement document.createTextNode
- parent.insertBefore()
- parent.removeChild()
- parent.appendChild()
- parent.replaceChild()

- createAttribute()
- setAttribute()
- getAttribute()

- mydiv.parentNode
- nodeType text:3 element:1 document:9
- document.referrer  可以获取从哪里点进来的 , 可以防盗链
- document.domain location?

- onclick="foo(event)"
- event.pageX event.screenX
- e.srcElement

## JSON

- 解析
- String 转成 JSON对象