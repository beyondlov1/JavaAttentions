> demo参考: NoteCloud
>
> 入门: https://www.w3cschool.cn/java/javafx-css.html

#### TableView

###### 绑定数据

思想: javaFx将前后端分离, 采用observerList作为数据, 绑定到相应的控件中. 操作observerList会相应的对界面进行操作, 和vue类似

TableView中的展现方式主要用setCellFactory和setCellValueFactory两个方法, 前者设置返回什么样的控件, 后者绑定数据

```java
contentTableColumn.setCellFactory(new Callback<TableColumn<Document, String>, TableCell<Document, String>>() {
    @Override
    public TableCell<Document, String> call(TableColumn<Document, String> param) {
        return new TableCell<Document, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    String content = HtmlUtils.parseHtml2Text(item);
                    Text text = new Text(content);
                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setPrefHeight(30);
                    textFlow.setMaxHeight(100);
                    setGraphic(textFlow);
                }
            }
        };
    }
});
contentTableColumn.setCellValueFactory(cellData -> cellData.getValue().contentProperty());
```

###### 点击table一行事件

```java
contentTextArea.setWrapText(true);
        documentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Document>() {
            @Override
            public void changed(ObservableValue<? extends Document> observable, Document oldValue, Document newValue) {
                String content = newValue.getContent();
                //markdownToHtml
                content = StringUtils.replaceUrlsToMarkDownStyle(content);
                content = MarkDownUtils.convertMarkDownToHtml(content);

                //add css
                content = HtmlUtils.addGithubMarkDownCss(content);
                webView.getEngine().loadContent(content);
                selectedId = newValue.getId();
            }
        });
```

###### 排序

直接对observerList进行排序

#### WebView

webView可以展现网页, getEngine方法获得引擎, 可以加载html或者url

加载html时不能加载里面的外链css, http的css也不行

#### MarkDown转html

```xml
<dependency>
    <groupId>org.markdownj</groupId>
    <artifactId>markdownj-core</artifactId>
    <version>0.4</version>
</dependency>
```

```java
public static String convertMarkDownToHtml(String markdown){
    MarkdownProcessor markdownProcessor = new MarkdownProcessor();
    return markdownProcessor.markdown(markdown);
}
```

###### github风格的css

https://gist.github.com/andyferra/2554919

https://github.com/zhangjikai/markdown-css

Html/MarkDown互转工具: https://www.bejson.com/convert/html2markdown/

#### 组件自适应窗口

SceneBuilder中有右键有fitParent的选项, VBox和HBox有相应的属性 VBox:vgrow=xxx HBox:vgrow=xxx

这两个属性在gui设计的界面没有选项, 只能在xml中写属性

#### 这两个属性在gui设计的界面没有选项, 只能在xml中写属性

#### 让table中的某一列填充整个table

JavaFx TableView Columns don't fill the TableView Width

https://stackoverflow.com/questions/44294622/javafx-tableview-columns-dont-fill-the-tableview-width

#### 消除窗口标题栏
https://blog.csdn.net/qq_32571359/article/details/72957307

#### 系统托盘添加图标
https://gist.github.com/jewelsea/e231e89e8d36ef4e5d8a
