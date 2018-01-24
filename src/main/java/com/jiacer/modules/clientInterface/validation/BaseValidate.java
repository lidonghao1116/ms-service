package com.jiacer.modules.clientInterface.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
* @ClassName: BaseValidate 
* @Description: TODO
* @author 贺章鹏
* @date 2016年10月21日 下午12:22:53 
*  
*/
public class BaseValidate {
	
	public static Boolean checkUrl(String url){
		String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=*~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
		Pattern patt = Pattern.compile(regex );
		Matcher matcher = patt.matcher(url);
		boolean isMatch = matcher.matches();
		if (!isMatch) {
			return false;
		} else {
			return true;
		}
	}
	
	/** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  
    
    /** 
     * 电话号码验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isPhone(String str) {   
        Pattern p1 = null,p2 = null;  
        Matcher m = null;  
        boolean b = false;    
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
        if(str.length() >9)  
        {   m = p1.matcher(str);  
            b = m.matches();    
        }else{  
            m = p2.matcher(str);  
            b = m.matches();   
        }    
        return b;  
    }
    
    /** 
     * 身份证校验
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isIdcard(String str) {   
    	IdcardValidate idcardValidator=new IdcardValidate();
        return idcardValidator.isValidatedAllIdcard(str);  
    } 
	
	public static void main(String[] args) {
		String url="http://mp.weixin.qq.com/s?src=3&timestamp=1474036325&ver=1&signature=M7GLKdH68bd7AfVPOwx3lc5os6w3PVqrfii4DlN*RYbEVtEm5kgmarEOuokdEv6xjGIZn8sYl-KEFNqBs4V2K6OhPNsqgF1Xl-Jk-8yRtqOEjj9Tqw8-rRAdrWsJBY0NyGQmT*i9-3NEf6iHERk05R0vEMjxw7sjgq6OEIGPyPw=";
		System.err.println(checkUrl(url));
	}
}
