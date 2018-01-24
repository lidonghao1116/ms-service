package com.jiacer.modules.common.utils;

/**
 *  MyBatis中like查询时，防止sql注入，处理查询字符串的工具类
 * 注：只针对mapper.xml中的取值方式为“#{}”的情况
 */
public class SqlUtils {

    public static void main(String[] args) {
        String str = "aaa_bbb'ccc^ddd%eee!fff]ggg[hhh";
        System.out.println(like(str));
    }

    /**
     * 示例：name like '%xxx%'
     */
    public static String like(String str) {
        return likeEscape(str, true, true);
    }

    /**
     * 示例：name like '%xxx'
     */
    public static String likeLeft(String str) {
        return likeEscape(str, true, false);
    }

    /**
     * 示例：name like 'xxx%'
     */
    public static String likeRight(String str) {
        return likeEscape(str, false, true);
    }

    /**
     * str ---> like查询的字符串
     * left ---> 字符串前是否拼接“%”
     * right ---> 字符串尾是否拼接“%”
     */
    private static String likeEscape(String str, boolean left, boolean right) {
        if (str==null||"".equals(str.trim())) {
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        if (left) {
            buffer.append("%");
        }

        //注意："]"不能处理
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '\'':
                    buffer.append(c);
                    break;
                case '[':
                    buffer.append("[[]");
                    break;
                case '_':
                    buffer.append("[_]");
                    break;
                case '%':
                    buffer.append("[%]");
                    break;
                case '^':
                    buffer.append("[^]");
                    break;
                case '!':
                    buffer.append("[!]");
                    break;
                default:
                    buffer.append(c);
            }
        }

        if (right) {
            buffer.append("%");
        }
        return buffer.toString();
    }

}