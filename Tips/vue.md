#### install

1. 安裝node.js

2. npm install -g vue-cli 

3. 新建一个项目文件夹，命名为 vue-demo，cd到此文件夹，输入:vue init webpack vue-demo，回车，按照如下操作进行初始化 (如果有提示要安裝什麼東西，按提示走)

4. cd到項目中， npm run dev 

   參考：https://www.jianshu.com/p/96751fcdaaab

#### 使用

App.vue是入口

template寫html， 只能有一個div，只能內嵌

script寫腳本， data(){return {message:"xxxx"}}, components:{xxx}

style 寫css

引用其他組件：在script中加入import xxx from './components/xxx.vue', 之後在script中輸出，在template中使用

#### 訪問其他頁面

修改routers.js

參考：https://segmentfault.com/a/1190000009366036

https://segmentfault.com/a/1190000007124470

####  webpack，vue-loader，热重载

https://www.jianshu.com/p/865a34969cf3