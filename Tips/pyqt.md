

### pyqt 监听QTextEdit focus事件并改变样式
监听: 在组件中复写
```
    def focusInEvent(self, QFocusEvent):
        print('focus in')
        pix = QPixmap('icon/action2.png')
        self.setPixmap(pix)
    def focusOutEvent(self, QFocusEvent):
        pix = QPixmap('icon/123.png')
        self.setPixmap(pix)
```
https://www.jianshu.com/p/ef674f39499d

改样式:
ui->lineEdit->setStyleSheet("background:transparent;border-width:0;border-style:outset");
https://blog.csdn.net/xiongyingeng/article/details/51085451

