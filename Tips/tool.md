

### 图床
https://zhuanlan.zhihu.com/p/58863378

https://www.imgtp.com/
https://postimages.org/
https://sm.ms/

### 图片隐写术
http://tools.jb51.net/aideddesign/img_add_info




### html 转pdf
wkhtmltopdf




### python3 长截图
```
from sys import argv
from urllib.parse import quote
import requests
from selenium import webdriver
from time import sleep
import base64

url = "https://www.cnblogs.com/superhin/p/11481910.html"

if argv[1]:
    url = argv[1]

chrome_options = webdriver.ChromeOptions()
chrome_options.add_argument('--headless')
driver = webdriver.Chrome(options=chrome_options)
driver.get(url)

# 取出页面的宽度和高度
page_width = driver.execute_script("return document.body.scrollWidth")
page_height = driver.execute_script("return document.body.scrollHeight")

# 直接开启设备模拟，不要再手动开devtools了，否则截图截的是devtools的界面！
driver.execute_cdp_cmd('Emulation.setDeviceMetricsOverride', {'mobile':False, 'width':page_width, 'height':page_height, 'deviceScaleFactor': 1})

# sleep(3)

# 执行截图
res = driver.execute_cdp_cmd('Page.captureScreenshot', { 'fromSurface': True})

with open(quote(url,safe="",encoding="utf-8")+".png", 'wb') as f:
    img = base64.b64decode(res['data'])
    f.write(img)

driver.quit()

```
headless 代表无界面



### gtk设置剪切板图片 + selenium
```

import threading
from time import sleep
import gi
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk, Gdk, GdkPixbuf, GLib

from sys import argv
from urllib.parse import quote
import requests
from selenium import webdriver
from time import sleep
import base64

def saveurlasimage(url:str, filename:str):
    chrome_options = webdriver.ChromeOptions()
    chrome_options.add_argument('--headless')
    driver = webdriver.Chrome(options=chrome_options)
    driver.get(url)

    # 取出页面的宽度和高度
    page_width = driver.execute_script("return document.body.scrollWidth")
    page_height = driver.execute_script("return document.body.scrollHeight")

    # 直接开启设备模拟，不要再手动开devtools了，否则截图截的是devtools的界面！
    driver.execute_cdp_cmd('Emulation.setDeviceMetricsOverride', {'mobile':False, 'width':page_width, 'height':page_height, 'deviceScaleFactor': 1})

    # sleep(3)

    # 执行截图
    res = driver.execute_cdp_cmd('Page.captureScreenshot', { 'fromSurface': True})

    with open(filename, 'wb') as f:
        img = base64.b64decode(res['data'])
        f.write(img)

    driver.quit()

def run():

    clip = Gtk.Clipboard.get(Gdk.SELECTION_CLIPBOARD)
    print("bbb")
    text:str = clip.wait_for_text()
    if text and (text.startswith("https://") or text.startswith("http://")):
        print(text)
        url = text
        filename = quote(url,safe="",encoding="utf-8")+".png"
        saveurlasimage(url, filename)
        clip.clear()
        clip.set_image(GdkPixbuf.Pixbuf.new_from_file(filename))
    GLib.timeout_add(1000, run)


def on_activate(app):
    run()

app = Gtk.Application(application_id='org.gtk.Example')
app.connect('activate', on_activate)
timeout_id = GLib.timeout_add(1000, run)
app.run(None)

Gtk.main()



```

