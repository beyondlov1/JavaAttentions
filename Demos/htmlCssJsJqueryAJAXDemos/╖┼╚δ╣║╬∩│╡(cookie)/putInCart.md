```js
function getCookieValue(key, cookie) {
		var cookieValue = ""
		var cookieArray = cookie.split("; ");
		for (var i = 0; i < cookieArray.length; i++) {
			var cookieKey = cookieArray[i].split("=")[0];
			if (cookieKey == key) {
				cookieValue = cookieArray[i].split("=")[1];
				break;
			}
		}
		return cookieValue;
	}

function putInCart(id) {
		var cookie = document.cookie;
		var cookieValue = getCookieValue("cart_" + cartType, cookie);
		var newCookieValue = "";
		if (cookieValue == "") {
			newCookieValue = "-" + id + "_1";
		} else {

			var bookinfoArray = cookieValue.split("-");
			//找到了就在循环里改，没找到就加一个
			var isFound = false;
			for (var i = 0; i < bookinfoArray.length; i++) {
				var bookId = bookinfoArray[i].split("_")[0];
				var bookCount = bookinfoArray[i].split("_")[1];

				if (id == bookId) {
					isFound = true;
					newCookieValue += "-" + bookId + "_" + (parseInt(bookCount) + 1);
				} else {
					newCookieValue += "-" + bookinfoArray[i];
				}
			}
			if (!isFound) {
				newCookieValue += "-" + id + "_1";
			}
		}

		newCookieValue = newCookieValue.substring(1);
		document.cookie = "cart_" + cartType + "=" + newCookieValue+";path=/bookshop";
}
```

