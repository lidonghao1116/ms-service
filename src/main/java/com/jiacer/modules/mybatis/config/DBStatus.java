package com.jiacer.modules.mybatis.config;

import java.math.BigDecimal;
import java.util.Map;
import com.google.common.collect.Maps;

public class DBStatus {
	
	/**
	 * 通用布尔类型
	 */
	public static class Bool {
		public static final String TRUE = "1";
		public static final String FALSE = "0";
		
		public static String getDesc(String status) {
			return msg.get(status);
		}
		
		public static Map<String, String> msg = Maps.newHashMap();
		static {
			msg.put(TRUE, "是");
			msg.put(FALSE, "否");
		}
	}
	
	public static class DelFlag{
    	public static final String TRUE="1";//已删除
    	public static final String FALSE="0";//未删除
    }
	
	public static class IsUseable{
		public static String TRUE="1";//可用
		public static String FALSE="0";//不可用
		
		public static String getDesc(String status) {
			return msg.get(status);
		}
		
		public static Map<String, String> msg = Maps.newHashMap();
		static {
			msg.put(TRUE, "是");
			msg.put(FALSE, "否");
		}
	}
	
	/**
	 * 短信通道
	 */
	public static class SmsSpCode {
		public static final String YU_TONG_XUN ="01"; // 云通讯
	}
	
	/**
	 * 短信发送状态
	 */
	public static class SmsResult {
		public static final String SUBMIT_SUCCESS="01"; // 提交成功
		public static final String SUBMIT_FAILURE="02"; // 提交失败
		public static final String SEND_SUCCESS="03"; // 发送成功
		public static final String SEND_FAILURE="04"; // 发送失败
	}
	
	/**
	 * 短信内容类型
	 */
	public static class SmsContentType {
		public static final String REG_SMS_CODE="01"; // 注册验证码
		public static final String RESET_PWD_SMS_CODE="02"; // 重置密码验证码
		public static final String ORDER_SUCCESS="03"; // 预约成功
		public static final String PAY_SUCCESS="04"; // 支付成功
		public static final String ORDER_REMIND="05"; // 预约提醒
		public static final String REFUND_SUCCESS="06"; // 退款成功
	}
	
	public static class QuestionScore{//问题得分
		public static String SINGLE="01";//单选
		public static String RIGHT_AND_WRONG="02";//是非
		public static String MULTI="03";//是非
		
		public static BigDecimal getDesc(String type) {
			return msg.get(type);
		}
		
		public static Map<String, BigDecimal> msg = Maps.newHashMap();
		static {
			msg.put(SINGLE, new BigDecimal(0));
			msg.put(RIGHT_AND_WRONG,new BigDecimal(0));
			msg.put(MULTI,new BigDecimal(0));
		}
	}
	
	public static class CertType{
		public static String SFZ="00";//省份证
	}
	
	/**
	 * 用户状态
	 */
	public static class UserStatus {
		public static final String NORMAL="00"; // 正常
	}
	
	public static class ProductStatus{
		public static String ON_SHELF="01";
		public static String OFF_SHELF="02";
		public static String STOPED="03";
	}
	
	/**
	 * 用户状态
	 */
	public static class OrderStatus {
		public static final String SUCCESS="05"; // 成功
	}
	
	public static class OrderType{
		public static String YUYUE="01";//用户自己预约
		public static String LURU="02";//系统录入
		public static String BUKAO="03";//补考系统生成
		public static String ASSIGN="04";//重新分班系统生成
	}
	
	public static class HandleStatus{
		public static String PENDING="01";//待审核
		public static String SUCCESS="02";//审核已分班-报名成功
		public static String FAILED="03";//审核不通过-报名失败
	}
	
	public static class UserType{
		public static String NORMAL="00";//会员用户
		public static String SYSTEM_USER="01";//系统用户
	}
	
	public static class CourseStatus{
		public static String NOMAL="01";
	}
	/**
	 * 用户请求来源
	 */
	public static class RequestSource {
		public static final String WECHAT = "01"; // 微信
	}
}
