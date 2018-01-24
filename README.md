### 家策微课堂

| version | date | dev |
| :---: | :---: | :---: |
| 1.0| unkown | unkown |
| 2.0.2|2017-06-26|gaoyan|


### version update:

```

CREATE TABLE `user_subscribes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int not null comment '用户id',
  `openid` varchar(100) not null comment '用户openid',
  `user_name` varchar(100) not null comment '用户openid',
  `channel` varchar(100) not null comment 'channel, e.g.  new job/new resume',
  `receive_time` varchar(10) null comment '接收推送时间段',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='订阅信息表';


CREATE TABLE `user_subscribe_areas` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sub_id` int(11) NOT NULL COMMENT '订阅id',
  `province` varchar(100) not null comment '订阅省',
  `city` varchar(100) not null comment '订阅市',
  `district` varchar(100) default null comment '订阅区',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='订阅地区表';


CREATE TABLE `user_subscribe_subjects` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sub_id` int(11) NOT NULL COMMENT '订阅id',
  `subject` varchar(100) not null comment '订阅主题(e.g. job:月嫂/育儿嫂/··)',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='订阅服务主题表';


```


#### 项目描述
```
提供以微信为载体的在线学堂报名购买等服务，及个人信息等管理。  
配合总控系统管理
```
#### 测试环境
> 家策微学堂API:[点击查看](https://documenter.getpostman.com/view/150886/jiacer-app/6fTzkMM)  
> domain: http://test.jiacersxy.com/jiacerapps/  
> server：root@139.224.49.192/Jclm@88  
> tomcat：/usr/tomcat  
> nginx: /usr/local/nginx  
> mysql: root/Jclm@88  
> 测试服务号:
 * appID: wx383289261cd4f41f  
 * appsecret: 708e9497272f5e3ba94fd72b0279fb99  
 扫码关注：![img](http://odxt0dmky.bkt.clouddn.com/11.jpeg)


#### 项目结构

```
java
 |-- com.jiacer.modules
 |     |--- cache
 |     |--- clientInterface
 |     |      |--- bean
 |     |      |--- common
 |     |      |--- controller ## API接口
 |     |      |--- jsonResult
 |     |      |--- service
 |     |      |--- validation
 |     |      
 |     |--- common
 |     |--- filter     ## 登陆Filter
 |     |--- interceptor  ## 测试用户Case
 |     |--- job
 |     |--- listener
 |     |--- mybatis
 |     |--- payment.common
 |     |--- sms
 |     |--- wechat
 |     
resources
 |
devResources
 |
testResources
 |
productResources
 |
webapp

```
### 业务模型
![img](http://odxt0dmky.bkt.clouddn.com/%E5%AE%B6%E7%AD%96%E5%BE%AE%E8%AF%BE%E5%A0%82.png)

##### 在线学堂
   * 推荐课程
     * 列表
   * 已购课程
     * 列表
   * 课程详情
     * 在线视频
        * 视频列表
        * 试看／观看
        * 购买
        * 分享
     * 模拟答题       
        * 答题／得分
     * 学习记录
        * 列表
   * 学员注册
        * 微信关联
        
##### 我的信息 
   * 个人详情
        * 查询／修改
   * 学习登记
        * 课程报名
   * 培训记录
        * 列表
   * 我的证书
        * 列表



# script  
* 导入视频

```




INSERT INTO videos (id,video_name,video_size,video_format,video_url,video_desc,is_usable,course_id,is_guest_watch,chapter_no)
SELECT CAST(file_id as SIGNED),file_name,video_size,'mp4','',type
,'1',
case `type`
when '家策微课堂-小儿推拿' then '5'
end
,'1',left(file_name,1)
FROM tencent_video where type = '家策微课堂-小儿推拿';


update videos set video_desc='第二章节：各式推拿手法' where video_desc='家策微课堂-小儿推拿' and chapter_no='2';
update videos set video_desc='第三章节：常见疾病治疗方法' where video_desc='家策微课堂-小儿推拿' and chapter_no='3';

update videos set `is_guest_watch`=0  where course_id=5;

update videos set `is_guest_watch`=1  where course_id=5 and chapter_no='2' limit 2;

update videos set `is_guest_watch`=1  where course_id=5 and chapter_no='3' limit 2;








```