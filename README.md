# ssm-crud
员工管理系统
功能：
- 分页
- 数据校验
  - jQuery前端校验+JSR303后端校验
- ajax
- Rest风格的URI：使用HTTP协议请求方式的动词，来表示对资源的操作GET（查询），POST（新增），PUT（修改），DELETE（删除）

所需技术：
- 基础框架-ssm（Spring+SpringMVC+MaBatis）
- 数据库-Mysql
- 前端框架-bootstrap快速搭建简洁美观的界面
- 项目的依赖管理-Maven
- 分页-pagehelper
- 逆向工程-MyBatis Generator

URL标准
- /emp/{id} 	GET请求	查询员工
- /emp			POST请求	保存员工
- /emp/{id}	 PUT请求	 修改员工
- /emp/{id}	 DELETE		删除员工


跟着尚硅谷雷丰阳老师的视频做的整合，相信不少跟着这个视频做的人中间都遇到了坑。。。

我只能说，老师的思路是没错的，还卡在哪里的同学可以仔细检查下自己导入的jar包是否冲突

我把我中间遇到的坑都写在了笔记里，大家可以参考一下，说不定就有相同的错误

笔记在CSDN

地址：https://blog.csdn.net/zuijiaxizi/article/details/108964814
