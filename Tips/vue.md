#### install

1. 安裝node.js

2. npm install -g vue-cli 

3. 新建一个项目文件夹，命名为 vue-demo，cd到此文件夹，输入:vue init webpack vue-demo，回车，按照如下操作进行初始化 (如果有提示要安裝什麼東西，按提示走)

4. cd到項目中， npm install, npm run dev 

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

#### checkbox

checkbox 要用onChange方法获得值，因为onClick方法运行的比较靠前

要用数组来接受值 (v-model)

#### 父组件向子组件传递数据

在子组件中加入props， 在父组件中绑定数据(v-bind:message="xxxx")

#### 子组件向父组件传递数据

利用子组件的 this.$emit(“add”, args)， 触发父组件的方法，add为出发的动作；args为父组件方法的参数；



#### vue中使用其他框架：如jquery

1. 在vue项目中安装jquery框架（cd 到项目中）

```
 npm install jquery --save
```

2. 在vue的build/webpack.base.conf文件当中引入jquery

```
module.exports = {
  ...
  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      'vue$': 'vue/dist/vue.esm.js',
      '@': resolve('src'),
      'jquery': path.resolve(__dirname, '../node_modules/jquery/src/jquery')
    }
  },
  ...
}
```

3.在需要的地方

```
import $ from 'jquery'
export default {
  name: 'hello',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted:function(){
    let test = $('#test').text()
    console.log(test)
  },
  methods:{

  }
}
```
