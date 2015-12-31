
# PullToRefresh-Demo
**[English](https://github.com/xuehuayous/PullToRefresh-Demo)** **[中文](https://github.com/xuehuayous/PullToRefresh-Demo/blob/master/README-zh.md)**

- 该项目是 [Modified PullToRefresh](https://github.com/xuehuayous/Android-PullToRefresh) 项目的一些示例演示。

- [PullToRefresh](https://github.com/xuehuayous/Android-PullToRefresh) 是在[chrisbanes/Android-PullToRefresh](https://github.com/chrisbanes/Android-PullToRefresh)基础上完善而来。

- [PullToRefresh](https://github.com/xuehuayous/Android-PullToRefresh) 扩展了支持 RecyclerView，并且可以轻松定制刷新加载样式。  

## 在项目中使用[PullToRefresh](https://github.com/xuehuayous/Android-PullToRefresh) 

如果您的项目使用 Gradle 构建, 只需要在您的`build.gradle`文件添加下面一行到 `dependencies` :

```
	compile 'com.kevin:pulltorefresh:1.0.7'
```
#Modules

##京东商城
这是一个用 **[PullToRefresh](https://github.com/xuehuayous/Android-PullToRefresh)** 实现的京东商城的加载头部示例。尽管使用的是PullToRefreshListView，您可以使用相同的方式来使用其他PullToRefresh控件。

![Screenshot](https://raw.githubusercontent.com/xuehuayous/PullToRefresh-Demo/master/JingDong/jingdong_header_demo.gif)

##美团
这是一个用 **[PullToRefresh](https://github.com/xuehuayous/Android-PullToRefresh)** 实现的美团的加载头部示例。尽管使用的是PullToRefreshScrollView，您可以使用相同的方式来使用其他PullToRefresh控件。

![Screenshot](https://raw.githubusercontent.com/xuehuayous/PullToRefresh-Demo/master/MeiTuan/meituan_header_demo.gif)

##汽车之家
这是一个用 **[PullToRefresh](https://github.com/xuehuayous/Android-PullToRefresh)** 实现的汽车之家的刷新加载示例，同时使用了[Android-LoopView](https://github.com/xuehuayous/Android-LoopView),使用它你可以轻松实现轮转大图。尽管使用的是PullToRefreshRecyclerView，您可以使用相同的方式来使用其他PullToRefresh控件。

![Screenshot](https://raw.githubusercontent.com/xuehuayous/PullToRefresh-Demo/master/AutoHome/autohome_header_demo.gif)

#天猫
这是一个用 **[PullToRefresh](https://github.com/xuehuayous/Android-PullToRefresh)** 实现的天猫的刷新加载示例，同时使用了[WrapRecyclerView](https://github.com/xuehuayous/WrapRecyclerView), 使用它您可以轻松为RecyclerView添加头部和尾部。尽管使用的是PullToRefreshRecyclerView，您可以使用相同的方式来使用其他PullToRefresh控件。

![Screenshot](https://raw.githubusercontent.com/xuehuayous/PullToRefresh-Demo/master/Tmall/tmall_demo.gif)

## License

    Copyright 2015, Kevin.zhou

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
