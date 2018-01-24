package com.jiacer.modules.clientInterface.common;

/**
 * @Description: 系统请求URL路径
 * @author hzp
 * @date 2016-5-23
 *
 */
public interface RequestMappingURL {

	/*********************************基础接口：用户、我*****************************************/
	public static final String	USERS_BASE_URL="/api/user";//用户接口基础地址
	
	public static final String	USERS_INFO_URL="/api/userInfo";//用户接口基础地址
	
	public static final String	COMMON_BASE_URL="/api/common";//公用接口基础地址
	
	public static final String	VIDEO_BASE_URL="/api/videos";//视频基础接口类
	
	public static final String	QUESTION_BASE_URL="/api/questions";//视频基础类
	
	public static final String	COURSE_LEARN_BASE_URL="/api/courseLearn";//课程学习基础接口
	public static final String	COURSE_ONLINE_BASE_URL="/api/course/online";//在线课程学习基础接口

	public static final String SERVICE_BASE_URL = "/api/service"; // 服务接口
	
	/*********************************短信接口*****************************************/
	public static final String SMS_RECEIPT_PUSH_URL  = "/smsReceiptPush"; // 短信发送回执推送
	
	/*********************************测试接口*****************************************/
	public static final String 	TEST_URL="/test";//登陆接口
	
	/*********************************登陆、注册、密码重置、登出接口*****************************************/
	public static final String REGISTER_BIND_URL = "/register/bind"; // 确定注册

	/*********************************图形验证码接口*****************************************/
	public static final String 	CAPTCHA_URL="/captcha";//图形验证码
	public static final String 	CAPTCHA_CHECK_URL="/captcha/check";//图形验证码

	public static final String SEND_SMS_CODE_URL = "/sms/code"; // 发送注册短信验证码
	
	/*********************************课程学习*****************************************/
	public static final String 	ENROLLED_COURSES_URL = "/enrolled"; //已报名
	public static final String 	RECOMMEND_COURSES_URL = "/recommend";//推荐课程
	public static final String 	GET_TIMES_URL = "/courseTimes";//课程对应详情
	public static final String  COURSES_INFO_URL = "/courseInfo";//课程产品对应详情
	public static final String 	APPLY_PRODUCTS_INFO_URL = "/apply";//课程产品申请
	
	/*********************************问题*****************************************/
	public static final String 	SAVE_URL="/save";//保存
	public static final String	LIST_URL="/list";//列表页
	public static final String 	GET_SCORE="/getUserScore";//获取用户答题分数
	public static final String 	GET_USER_ALL_SCORE="/getUserAllScore";//获取用户答题分数
	public static final String 	GET_ANSWER_SHEET="getAnswerSheet";//获取答题卡
	public static final String 	QUESTIONS_ANSWER_DETAILS="getQWDetails";//获取问题答案详情
	/*********************************视频*****************************************/
	public static final String	TYPES_URL="/types";//学习视频分类口
	public static final String	TYPES_CHAPTER_URL="/typeChapter";//视频分类章节表
	public static final String	ALL_CHAPTERS_URL="/allChapters";//所有的章节
	/*********************************用户我*****************************************/
	public static final String 	INFO_URL="/info";//用户详情
	public static final String 	INFO_WECHAT_URL="/wechat";//用户详情
	public static final String 	UPDATE_INFO_URL="/update";//用户修改
	public static final String 	MY_LEARN_RECORDS_URL="/learnRecords";//用户学习情况

	/*********************************微信授权*****************************************/
	public static final String WECHAT_BASE_URL = "/api/wechat"; // 微信基础接口
	public static final String PROCESS_URL = "/process"; // 微信验证
	public static final String WECHATAUTHRIZE_URL="/wechatAuthrize";


	/*********************************在线课程接口*****************************************/
	public static final String ONLINE_RECOMMAND_URL = "/recommand"; // 在线课程推荐
	public static final String ONLINE_OWN_URL = "/own"; // 已购课程
	public static final String ONLINE_INFO_URL = "/info"; // 已购课程
	public static final String ONLINE_VIDEO_URL = "/video"; //在线课程视频
	public static final String ONLINE_TEST_URL = "/test"; //在线课程问答




}
