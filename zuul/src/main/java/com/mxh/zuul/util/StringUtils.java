package com.mxh.zuul.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串操作类
 *
 * @author chenting
 * @date 2016年8月18日
 */
public class StringUtils {
    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 判断字符串是否为非空(不是 null 或 "" 或 "null"). <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str 要进行判断的字符串
     * @return 字符串为空返回false，非空返回true. 注意：
     */
    public static boolean isNotNull(String str) {
        boolean isNotNull = true;
        if (str == null || "".equals(str.trim()) || "null".equals(str)) {
            isNotNull = false;
        }
        return isNotNull;
    }

    /**
     * 转换空值为一个字符串对象. <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str 要进行转换的字符串
     * @return 如为空返回"",非空则返回原字符. 注意：
     */
    public static String converNullTostr(String str) {
        if (str == null || "".equals(str.trim()) || "null".equals(str)) {
            str = "";
        }
        return str;
    }

    /**
     * 判断字符串是否为空. <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str 要进行判断的字符串
     * @return 如为空返回true, 非空则返回false. 注意：
     */
    public static boolean isNull(String str) {
        boolean isNull = true;
        if (str != null && !"".equals(str.trim()) && !"null".equals(str)) {
            isNull = false;
        }
        return isNull;
    }

    /**
     * 随机生成通用唯一标识符. <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @return 返回通用唯一标识符. 注意：
     */
    public static String randomUUID() {

        return UUID.randomUUID().toString();
    }

    /**
     * 使字符串增加到指定长度. <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param instr  传入的字符串
     * @param length 指定的字符串长度。
     * @return 返回扩展到指定长度后的字符串. 注意：
     */
    public static String setLength(String instr, int length) {

        int realLen = instr.getBytes().length;
        if (realLen < length) {
            for (int i = realLen; i < length; i++) {
                instr = instr + " ";
            }
        }
        return instr;
    }

    /**
     * 字符串截取(获取指定中文数的字符串). <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param subject 待处理 字符串
     * @param num     取前几个(中文字长)
     * @return 返回指定长度的字符串. 注意：
     */
    public static String getShortString(String subject, int num) {

        int n = 0;
        int i = 0;
        int j = 0;
        int byteNum = num * 2;

        boolean flag = true;
        if (subject == null) {
            return " ";
        }
        if (subject.endsWith(".")) {
            subject = subject.substring(0, subject.length() - 1);
        }

        for (i = 0; i < subject.length(); i++) {
            if ((int) (subject.charAt(i)) < 128) {
                n += 1;
            } else {
                n += 2;
            }
            if (n > byteNum && flag) {
                j = i;
                flag = false;
            }
            if (n >= byteNum + 2)
                break;
        }

        if (n >= byteNum + 2 && i != subject.length() - 1) {
            subject = subject.substring(0, j);
            subject += "…";
        }

        return subject;
    }

    /**
     * 功能的简述. 获取字符串长度，一个中文占2个字符 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号
     *
     * @param content
     * @return 注意：
     */
    public static int getStringLength(String content) {

        if (StringUtils.isNull(content)) {
            return 0;
        }
        content = content.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("&apos;", "'");
        content = content.replaceAll("[\u4E00-\u9FA5]", "xx");
        return content.length();
    }

    /**
     * 代码转换. <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param s     要转换的字符串
     * @param code1 原字符集。
     * @param code2 目的字符集。
     * @return 返回转码后的字符串. 注意：
     */
    public static String covertCode(String s, String code1, String code2) {
        String result = null;
        try {
            if (s == null || s.trim().equals(""))
                result = null;
            else
                result = new String(s.getBytes(code1), code2);

        } catch (java.io.UnsupportedEncodingException ex) {
            logger.info(ex.toString());
        }
        return result;
    }

    /**
     * 功能的简述: 格式化日期 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param date 要转换的日期
     * @return 返回转码后的字符串. 注意：
     */
    public static String DateString(Date date) {
        if (date == null)
            date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date).toString();
    }

    /**
     * 功能的简述: 转换数据库使用的特殊字符 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param strReplace 要处理的字符串
     * @return 返回替换码后的字符串. 注意：
     */
    public static String replaceDBSpecialChar(String strReplace) {

        String strRet = strReplace;
        if (strRet != null)
            strRet = strRet.replaceAll("'", "''");
        else
            strRet = "";

        return strRet;
    }

    /**
     * 获取一个字符串的 md5，默认用 UTF-8 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param data 需要处理的字符串
     * @return 编码后的字符串 注意：
     */
    public static String md5(String data) {
        return md5(data, "UTF-8");
    }

    /**
     * 获取一个字符串的 md5 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param data    需要处理的字符串
     * @param charset 所采用的 charset
     * @return 编码后的字符串 注意：
     */
    public static String md5(String data, String charset) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");// 创建一个MD5消息文搞
            m.update(data.getBytes(charset));// 更新被文搞描述的位元组
            byte s[] = m.digest();// 最后更新使用位元组的被叙述的排列,然后完成文摘计算
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < s.length; i++) {
                // 进行十六进制转换
                result.append(Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6));
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * escape编码(与javascript的escape功能完全一致，类似encodeing) <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param src 需要编码的字符串
     * @return 编码后的字符串 注意：
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * escape解码，和javascript的unescape通用 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param src 需要解码的字符串
     * @return 解码后的字符串 注意：
     */
    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 判断是否为合法的日期时间字符串 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str_input   要进行判断的字符串
     * @param rDateFormat 期望的合法日期格式
     * @return boolean;符合为true,不符合为false 注意：
     */
    public static boolean isDate(String str_input, String rDateFormat) {
        if (!isNull(str_input)) {
            SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
            formatter.setLenient(false);
            try {
                // formatter.format(formatter.parse(str_input));
                String s = formatter.format(formatter.parse(str_input));
                return str_input.equals(s);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 判断是否为合法的日期时间字符串。 允许的格式:yyyy-MM-dd、yyyyMMdd、yyyy-MM-dd
     * HH:mm:ss、yyyyMMddHHmmss <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false 注意：
     */
    public static boolean isDate(String str_input) {
        boolean isOk = false;
        if (isNull(str_input)) {
            return isOk;
        }
        // if(!isOk){
        // isOk=isDate(str_input, "yyyy");
        // }
        // if(!isOk){
        // isOk=isDate(str_input, "yyyy-MM");
        // }
        // if(!isOk){
        // isOk=isDate(str_input, "yyyy/MM");
        // }
        // if(!isOk){
        // isOk=isDate(str_input, "yyyyMM");
        // }
        if (!isOk) {
            isOk = isDate(str_input, "yyyy-MM-dd");
        }
        if (!isOk) {
            isOk = isDate(str_input, "yyyy/MM/dd");
        }
        // if(!isOk){
        // isOk=isDate(str_input, "yyyyMMdd");
        // }
        if (!isOk) {
            isOk = isDate(str_input, "yyyy-MM-dd HH:mm:ss");
        }
        // if(!isOk){
        // isOk=isDate(str_input, "yyyyMMddHHmmss");
        // }
        if (!isOk) {
            isOk = isDate(str_input, "yyyy/MM/dd HH:mm:ss");
        }
        // if(!isOk){
        // isOk=isDate(str_input, "yyyy-MM-dd HH:mm");
        // }
        // if(!isOk){
        // isOk=isDate(str_input, "yyyyMMddHHmm");
        // }
        if (!isOk) {
            isOk = isDate(str_input, "yyyy/MM/dd HH:mm");
        }
        return isOk;
    }

    public static String dateFormat(String str_input) {
        String ret = str_input;
        if (isNull(str_input)) {
            return ret;
        }

        if (isDate(str_input, "yyyy-MM")) {
            ret += "-01";
        } else if (isDate(str_input, "yyyy/MM")) {
            ret += "/01";
        } else if (isDate(str_input, "yyyyMM")) {
            ret += "01";
        } else if (isDate(str_input, "yyyy")) {
            ret += "-01-01";
        }

        return ret;
    }

    /**
     * 判断是否是时间
     *
     * @param str
     * @return boolean
     * @author chenting
     * @date 2017年4月25日
     */
    public static boolean isDateTime(String str) {
        String regex = "(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?(\\s)*(PM|AM)?)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 正则表达式字符串匹配 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param regex 正则表达式字符串
     * @param
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false; 注意：
     */
    public static boolean match(String regex, String str_input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str_input);
        return matcher.matches();
    }

    /**
     * 判断是否为合法的email地址字符串 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false 注意：
     */
    public static boolean isEmail(String str_input) {
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return match(regex, str_input);
    }

    /**
     * 判断是否为合法的电话号码 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false 注意：
     */
    public static boolean isPhone(String str_input) {
        String regex = "^(([0-9]{11})|^(([0-9]{7,8})|([0-9]{4}|[0-9]{3})-([0-9]{7,8})|([0-9]{4}|[0-9]{3})-([0-9]{7,8})-([0-9]{4}|[0-9]{3}|[0-9]{2}|[0-9]{1})|([0-9]{7,8})-([0-9]{4}|[0-9]{3}|[0-9]{2}|[0-9]{1}))$)";
        return match(regex, str_input);
    }

    /**
     * 判断是否为合法的移动电话号码 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false 注意：
     */
    public static boolean isMobilePhone(String str_input) {
        String regex = "^1[3,8,5][0-9]{9}";
        return match(regex, str_input);
    }

    /**
     * 判断是否为数字 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false 注意：
     */
    public static boolean isNumeric(String str_input) {
        if (isNull(str_input)) {
            return false;
        }
        if (str_input.matches("\\d*")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否合法url地址 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false 注意：
     */
    public static boolean isURL(String str_input) {
        if (StringUtils.isNull(str_input)) {
            return false;
        }
        String regex = "^(http|ftp|file)://.*";
        return match(regex, str_input);
    }

    /**
     * 判断是否为合法身份证号 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false 注意：
     */
    public static boolean isIDCard(String str_input) {
        if (str_input.length() == 15) {
            str_input = uptoeighteen(str_input);
        }

        if (str_input.length() != 18) {
            return false;
        }

        String verify = str_input.substring(17, 18);
        verify = verify.toUpperCase();

        if (verify.equals(getVerify(str_input))) {
            if (verifyIDCardYear(str_input)) {
                return verifyIDCardArea(str_input);
            }
        }
        return false;
    }

    /**
     * 身份证号码15位升级到18位(isIDCard中用) <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param fifteencardid 15位身份证号
     * @return 18位身份证号 注意：
     */
    private static String uptoeighteen(String fifteencardid) {
        String eightcardid = fifteencardid.substring(0, 6);
        eightcardid = eightcardid + "19";
        eightcardid = eightcardid + fifteencardid.substring(6, 15);
        eightcardid = eightcardid + getVerify(eightcardid);
        return eightcardid;
    }

    /**
     * 辅助方法(isIDCard中用) <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param
     * @return 返回校验码 注意：
     */

    private static String getVerify(String eightcardid) {
        final int[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
        final int[] vi = {1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2};
        int[] ai = new int[18];

        int remaining = 0;
        if (eightcardid.length() == 18) {
            eightcardid = eightcardid.substring(0, 17);
        }
        if (eightcardid.length() == 17) {
            int sum = 0;
            for (int i = 0; i < 17; i++) {
                String k = eightcardid.substring(i, i + 1);
                ai[i] = Integer.parseInt(k);
            }

            for (int i = 0; i < 17; i++) {
                sum = sum + wi[i] * ai[i];
            }
            remaining = sum % 11;
        }
        return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
    }

    /**
     * 辅助方法(isIDCard中用) <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param
     * @return boolean;符合为true,不符合为false 注意：
     */
    private static boolean verifyIDCardYear(String eightcardid) {
        String cardYear = eightcardid.substring(6, 14);
        return isDate(cardYear, "yyyyMMdd");
    }

    /**
     * 辅助方法(isIDCard中用) <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param
     * @return boolean;符合为true,不符合为false 注意：
     */
    private static boolean verifyIDCardArea(String eightcardid) {
        String[] aCity = new String[]{null, null, null, null, null, null, null, null, null, null, null, "北京", "天津", "河北", "山西", "内蒙古", null, null, null, null, null, "辽宁", "吉林", "黑龙江", null, null,
                null, null, null, null, null, "上海", "江苏", "浙江", "安微", "福建", "江西", "山东", null, null, null, "河南", "湖北", "湖南", "广东", "广西", "海南", null, null, null, "重庆", "四川", "贵州", "云南", "西藏", null,
                null, null, null, null, null, "陕西", "甘肃", "青海", "宁夏", "***", null, null, null, null, null, "台湾", null, null, null, null, null, null, null, null, null, "香港", "澳门", null, null, null,
                null, null, null, null, null, "国外", null, null, null, null, null, null, null, null};
        if (aCity[Integer.parseInt(eightcardid.substring(0, 2))] == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断是否为合法IP地址,不可为全0或全255 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false 注意：
     */
    public static boolean isIP(String str_input) {
        if ("0.0.0.0".equalsIgnoreCase(str_input) || "255.255.255.255".equalsIgnoreCase(str_input)) {
            return false;
        }
        try {
            String number = str_input.substring(0, str_input.indexOf('.'));
            if (Integer.parseInt(number) > 255) {
                return false;
            }
            str_input = str_input.substring(str_input.indexOf('.') + 1);
            number = str_input.substring(0, str_input.indexOf('.'));
            if (Integer.parseInt(number) > 255) {
                return false;
            }
            str_input = str_input.substring(str_input.indexOf('.') + 1);
            number = str_input.substring(0, str_input.indexOf('.'));
            if (Integer.parseInt(number) > 255) {
                return false;
            }
            number = str_input.substring(str_input.indexOf('.') + 1);
            if (Integer.parseInt(number) > 255) {
                return false;
            }

            return true;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 判断是否为合法mac地址 <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false 注意：
     */
    public static boolean isMac(String str_input) {
        String regex = "^(([0-9A-Fa-f]{2}[-|:]){5}[0-9A-Fa-f]{2})$";
        if (isNull(str_input)) {
            return false;
        }
        try {
            if (!match(regex, str_input)) {
                return false;
            }
            //判断Mac地址是否为全0，全f
            if (str_input.matches("^((00)[:|-](00)[:|-](00)[:|-](00)[:|-](00)[:|-](00))$")
                    || str_input.matches("^((ff)[:|-](ff)[:|-](ff)[:|-](ff)[:|-](ff)[:|-](ff))$")) {
                return false;
            }
            //判断第一个字节的最后一个比特位位1
            String[] str = str_input.split(":");
            String one = str == null ? null : str[0];
            if (one.matches("^([0-9a-fA-F]{1}1)$")) {
                return false;
            }
            return true;

        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 根据提供的图片路径，得到目录和文件名
     *
     * @param filePath
     * @return fileDirAndName[0]：目录，fileDirAndName[1]：文件名
     */
    public static String[] photoSplit(String filePath) {
        String[] fileDirAndName = new String[2];
        if (filePath != null && !filePath.equals("")) {
            int flg = filePath.lastIndexOf("/");
            fileDirAndName[0] = filePath.substring(0, flg + 1);
            fileDirAndName[1] = filePath.substring(flg + 1);
            return fileDirAndName;
        } else {
            return null;
        }
    }

    /**
     * 同struts标签的filter=true; <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param str
     */
    public static String htmlFilter(String str) {
        return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    /**
     * 将字符串编码成 Unicode 。
     *
     * @param theString   待转换成Unicode编码的字符串。
     * @param escapeSpace 是否忽略空格。
     * @return 返回转换后Unicode编码的字符串。
     */
    public static String toUnicode(String theString, boolean escapeSpace) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);
        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            // Handle commonOID.properties case first, selecting largest block that
            // avoids the specials below
            if ((aChar > 61) && (aChar < 127)) {
                // if (aChar == '\\') {
                // outBuffer.append('\\');
                // outBuffer.append('\\');
                // continue;
                // }
                outBuffer.append(aChar);
                continue;
            }
            switch (aChar) {
                case ' ':
                    if (x == 0 || escapeSpace) {
                        outBuffer.append('\\');
                    }
                    outBuffer.append(' ');
                    break;
                case '\t':
                    outBuffer.append('\\');
                    outBuffer.append('t');
                    break;
                case '\n':
                    outBuffer.append('\\');
                    outBuffer.append('n');
                    break;
                case '\r':
                    outBuffer.append('\\');
                    outBuffer.append('r');
                    break;
                case '\f':
                    outBuffer.append('\\');
                    outBuffer.append('f');
                    break;
                case '=': // Fall through
                    // case ':': // Fall through
                case '#': // Fall through
                    // case '\\': continue;// Fall through
                case '!':
                    outBuffer.append('\\');
                    outBuffer.append(aChar);
                    break;
                default:
                    if ((aChar < 0x0020) || (aChar > 0x007e)) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex((aChar >> 12) & 0xF));
                        outBuffer.append(toHex((aChar >> 8) & 0xF));
                        outBuffer.append(toHex((aChar >> 4) & 0xF));
                        outBuffer.append(toHex(aChar & 0xF));
                    } else {
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }

    public static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    private static final char[] hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * unicode 转换成 中文
     *
     * @param theString
     * @return
     */

    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }

            } else {
                outBuffer.append(aChar);
            }

        }

        return outBuffer.toString();
    }

    /**
     * 向请求页面返回数据
     *
     * @param response
     * @param returnValue
     * @throws IOException
     */
    public static void sendResponse(HttpServletResponse response, String returnValue, String charset) throws IOException {
        response.setContentType("text/html;charset=" + charset + "");
        response.getWriter().println(returnValue);
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("charset", charset);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization,token");
    }

    /**
     * 获取机器所有网卡的IP（ipv4）
     *
     * @return
     */
    public static List<String> getLocalIP() {
        List<String> ipList = new ArrayList<String>();
        InetAddress ip = null;
        try {
            Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                // 遍历所有ip
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = (InetAddress) ips.nextElement();
                    if (null == ip || "".equals(ip)) {
                        continue;
                    }
                    String sIP = ip.getHostAddress();
                    if (sIP == null || sIP.indexOf(":") > -1) {
                        continue;
                    }
                    ipList.add(sIP);
                    System.out.println(sIP);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipList;
    }

    /**
     * 获取基础路径
     *
     * @return
     */
    public static String getBasePath() {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        if (basePath.indexOf("webapps/") > 0) {
            basePath = basePath.substring(0, basePath.lastIndexOf("webapps/")) + "webapps/";
        }
        if (basePath.indexOf("%20") > 0) {
            basePath = basePath.replaceAll("%20", " ");
        }
        return basePath;
    }

    public static void main(String[] args) {
        String str = "0.0.0.1";
        // logger.info(toUnicode(str, false));
        // logger.info(StringEscapeUtils.escapeJava(str));
        System.out.println(isIP(str));
    }

    /**
     * 获得真实的客户端的ip <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     *
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("x-forwarded-for=========" + sdf.format(new Date()) + "======" + ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            System.out.println("Proxy-Client-IP=========" + sdf.format(new Date()) + "======" + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            System.out.println("WL-Proxy-Client-IP=========" + sdf.format(new Date()) + "======" + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            System.out.println("getRemoteAddr=========" + sdf.format(new Date()) + "======" + ip);
        }
        if (ip.indexOf(",") > -1) {
            String[] ips = ip.split(",");
            ip = ips[0];
        }
        return ip;
    }

    /**
     * 获取本机IP地址，只查询一个
     *
     * @return
     */
    public static String getLocalHost() {
        String localHost = null;
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                System.out.println(netInterface.getName());
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        localHost = ip.getHostAddress();
                        System.out.println("本机的IP = " + ip.getHostAddress());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return localHost;
    }

    /**
     * 获取系统操作类型
     *
     * @return String
     * @author liangjun
     * @date 2017年9月15日
     */
    public static String getOperatingSystem() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        return os;
    }

    /**
     * 检验端口合法性
     *
     * @return String
     * @author liangjun
     * @date 2017年11月6日 true 合法 false不合法
     */
    public static boolean isPort(String serverPort) {
        if (StringUtils.isNull(serverPort)) {
            return false;
        }
        String regex = "^([1-9]|[1-9]\\d{3}|[1-6][0-5][0-5][0-3][0-5])$";
        boolean flag = Pattern.matches(regex, serverPort);
        return flag;
    }

    public static boolean isMtch(String regex, String str_input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str_input);
        return matcher.matches();
    }

    /**
     * 检验文件名称，只能包含，中文英文数字，下划线
     *
     * @author liangjun
     * @date 2017年12月7日 true 合法 false不合法
     */
    public static boolean checkFileName(String fileName) {
        if (StringUtils.isNull(fileName)) {
            return false;
        }
        String pattern = "^[a-zA-Z0-9_\\——\u4e00-\u9fa5]+$";
        boolean matches = Pattern.matches(pattern, fileName);
        return matches;
    }

    // 判断是否是有效的IP
    public static boolean isRtmpIP(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(addr);

        boolean ipAddress = mat.find();

        return ipAddress;
    }

    /**
     * 获取随机数字字母混合字符串，可以指定位数
     *
     * <br>
     * 2015-01-13 xiujinyue 15772小功能增改/passport_手机注册用户默认昵称修改
     *
     * @param num 需要的字符个数
     * @return
     */
    public static String getRandomStrAndNum(int num) {
        String[] random = {"a", "b", "c", "d", "e", "f", "g", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "h", "i", "g", "k", "l", "m", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        return getRandomStr(random, num);
    }

    /**
     * 获取随机字符串，可以指定位数
     *
     * <br>
     * 2015-01-13 xiujinyue 15772小功能增改/passport_手机注册用户默认昵称修改
     *
     * @param random 种子
     * @param num    需要的字符个数
     * @return
     */
    public static String getRandomStr(String[] random, int num) {
        int count = 0;
        String str = "";
        num = num > random.length ? random.length : num;
        for (int i = 0; i < num; i++) {
            count = getRandomNum(0, random.length - 1);
            str += random[count];
        }
        return str;
    }

    /**
     * 生成指定范围的随机数 （含min，max）
     *
     * @param min 起始值
     * @param max 结束值
     * @return 返回包括起始值和结束值在内的随机整数
     */
    public static int getRandomNum(int min, int max) {
        return (int) ((Math.random() * (max - min + 1) + min));
    }

    /*
     * 判断是够包含中文
     */
    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     * @return int
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        //注意此处为正则匹配，不能用"."；
        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        //取最小长度值
        int minLength = Math.min(versionArray1.length, versionArray2.length);
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度  
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符  
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；  
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 拼接字符串
     * 注意：拼接sql字符串使用
     */
    public static String addSplit(String[] str) {
        StringBuffer sb = new StringBuffer();
        for (String s : str) {
            sb.append("'").append(s + "'").append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            //sb.append("\"");
        }
        return sb.toString();
    }

    /**
     * 获取request中body内容
     *
     * @param request
     * @return
     * @author chenting
     * @date 2019年3月25日
     */
    public static String readAsChars(HttpServletRequest request) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    //byte 数组与 int 的相互转换
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    /**
     * 根据byte数组前两个字节获取数据包长度
     *
     * @param blen
     * @return
     */
    public static int getMessageLen(byte[] blen) {
        String hexlen = Integer.toHexString(blen[0]) + Integer.toHexString(blen[1] & 0xff);
        return Integer.parseInt(hexlen, 16);
    }

    public static void setRestTemplateEncode(RestTemplate restTemplate) {
        if (null == restTemplate || ObjectUtils.isEmpty(restTemplate.getMessageConverters())) {
            return;
        }

        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (int i = 0; i < messageConverters.size(); i++) {
            HttpMessageConverter<?> httpMessageConverter = messageConverters.get(i);
            if (httpMessageConverter.getClass().equals(StringHttpMessageConverter.class)) {
                messageConverters.set(i, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            }
        }
    }

    /**
     * 功能描述:  获id取可用的业务或者隧道
     *
     * @param
     * @return:
     * @Author: dyf
     * @Date: 2019/5/10 17:34
     */
    public static int getOidid(Map<String, String> bidmap) {

        int id = 1;
        if (bidmap.size() > 0) {
            int[] arr = new int[bidmap.size()];
            int i = 0;
            for (String keys : bidmap.keySet()) {
                arr[i] = Integer.parseInt(keys);
                i++;
            }
            id = StringUtils.find(arr);
        }
        return id;
    }


    public static int getIndex(Map<String, Object> bidmap) {

        Map<String, Object> indexmap = new HashMap<>();
        for (Object key : bidmap.keySet()) {
            indexmap.put(bidmap.get(key).toString(), key);
        }


        int id = 1;
        if (indexmap.size() > 0) {
            int[] arr = new int[indexmap.size()];
            int i = 0;
            for (String keys : indexmap.keySet()) {
                arr[i] = Integer.parseInt(keys);
                i++;
            }
            id = StringUtils.find(arr);
        }
        return id;
    }

    public static int find(int[] array) {
        Hashtable<Integer, Integer> hasht = new Hashtable<Integer, Integer>();
        int id = 0;
        for (int i = 0; i < array.length; i++) {
            hasht.put(array[i], 1);
        }
        for (int i = 0; i <= 4094; i++) {
            if (hasht.get(i) == null) {
                System.out.println("缺失：" + i);
                id = i;
                break;
            }
        }

        return id;
    }




    /**
     * 功能描述: 获取对象的属性值
     *
     * @param fieldName 实体字段名称
     * @return:
     * @Author: dyf
     * @Date: 2019/5/9 15:58
     */
    private static Object getFieldValueByName(String fieldName, Object obj) {

        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = obj.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(obj, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }


    public static Timestamp timestamp() {
        //格式化当前系统日期
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFm.format(new Date());
        Timestamp time = Timestamp.valueOf(dateTime);
        return time;
    }




    //位图法 解析  512个字节":"  每个字节8位 最后每位求 &
    public static String hexStr2Byte(String hex) {
        int j = 0;
        int y = 0;
        int[] uu = new int[4096];
        String dealWithResult = "";
        if (hex != "") {
            ByteBuffer bf = ByteBuffer.allocate(hex.length() / 2);
            for (int i = 0; i < hex.length(); i++) {
                String hexStr = hex.charAt(i) + "";
                if (hex.charAt(i) == ':') {
                    continue;
                } else {
                    int kk = (hex.charAt(i) & 0x8) >> 3;
                    int kk1 = (hex.charAt(i) & 0x4) >> 2;
                    int kk2 = (hex.charAt(i) & 0x2) >> 1;
                    int kk3 = (hex.charAt(i) & 0x1);
                    if (kk == 1) {
                        uu[y++] = kk * j * 4 + 1;
                        //  System.out.println(uu[y - 1]);
                    }
                    if (kk1 == 1) {
                        uu[y++] = kk1 * j * 4 + 2;
                        //   System.out.println(uu[y - 1]);
                    }
                    if (kk2 == 1) {
                        uu[y++] = kk2 * j * 4 + 3;
                        //  System.out.println(uu[y - 1]);
                    }
                    if (kk3 == 1) {
                        uu[y++] = kk3 * j * 4 + 4;
                        // System.out.println(uu[y - 1]);
                    }
                    j++;
                }
            }

            StringBuffer sb = new StringBuffer();
            if (uu.length > 0) {
                for (int m = 0; m < uu.length; m++) {
                    if (uu[m] != 0) {
                        sb.append(uu[m] + ",");
                    }
                }
            }

            if (!sb.toString().equals("") && sb.toString().length() > 0) {
                dealWithResult = sb.toString().substring(0, sb.toString().lastIndexOf(","));
            } else {
                dealWithResult ="";
            }

        }

        return dealWithResult;
    }

    public static String tohex(String valueTen) {
        int valueTens = Integer.parseInt(valueTen);
        //将十六进制格式化输出
        String strHex2 = String.format("%04x", valueTens);
        return strHex2;
    }

}
