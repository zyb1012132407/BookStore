# 传智播客网上书城案例
## 前言
之前学习javaweb都是处于朦胧的状态，正好利用期末考核这个机会巩固一下知识，不过因为自己对jsp的了解不够深入，总是害怕自己遇到坑，
而且想学习点新知识，所以前端主要使用了Bootstrap+Vue+Ajax，后端主要使用了Servlet技术。<br>
本来想好好学的，结果遇到了电脑报废 x N次，感冒 x N次。脑子经常不够用，很容易会犯错，如果有机会的话，本项目将会在暑假期间重构，
并采用Electron + Vue + Element + Servlet的架构。
## 介绍
### 实现功能
#### 用户注册
【实时显示】 检测用户名是否已经存在（存在无法注册），用户名与密码是否含有空格，邮箱是否合法，验证码验证<br><br>
![sign-up](https://github.com/zyb1012132407/bookstore/blob/master/demo/09.png) <br>
#### 用户登录
【实时显示】 检测用户名是否已经存在（不存在无法登录），用户名与密码是否匹配，验证码验证
![sign-in](https://github.com/zyb1012132407/bookstore/blob/master/demo/07.png) <br>
#### 商店预览
实时加载图书信息+图片，提供分类查找与模糊字查找。
![sign-in](https://github.com/zyb1012132407/bookstore/blob/master/demo/05.png) <br>
#### 购物车与提交订单功能
在商店添加购物车之后，可以在购物车界面查找添加内容，提交订单。（支付功能未提供）
![sign-in](https://github.com/zyb1012132407/bookstore/blob/master/demo/08.png) <br>
#### 后台管理
普通用户可在后台管理账号信息与个人订单，管理员可在后台管理所有用户信息，订单信息，图书信息，公告信息。
![sign-in](https://github.com/zyb1012132407/bookstore/blob/master/demo/02.png) <br>
### 部署方法
可通过Idea直接加载项目，简单修改启动设置就可以部署了 <br>
应用目录为/Servlet
### 数据库结构
![sign-in](https://github.com/zyb1012132407/bookstore/blob/master/demo/database.png) <br>
### 仍待解决的问题
#### 命名略混乱（命名合乎大意但语义规范仍有些懒散）
#### 一些处理效率不高，对数据库添加负载
#### 前端超链接混乱，相对路径与绝对路径可能出现混乱
#### 支付界面未添加
#### 防止了Html注入但是防不住SQL注入
#### 一些权限检测是通过前端进行的，很容易被攻击者伪造数据或截断数据
#### 没有加密措施，很容易被抓包，向服务端发送异常请求
#### 服务端预置了许多功能，但因为时间问题，在前端无法实现。
#### blackbox组件库依赖过多，造成轻微负担
#### 忘记密码与邮箱验证未实现，因为没有空闲邮箱了。。。。。
