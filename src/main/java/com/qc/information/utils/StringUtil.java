package com.qc.information.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: czt
 * @Date: 18-10-30 下午3:56
 */
public class StringUtil {

    /**
     * 转换文件大小
     *
     * @param size
     * @return
     */
    public static String FormetFileSize(String size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        int fileS = Integer.parseInt(size);
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "Kb";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static String getUUIDStr() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public static Integer getRowSpanTable(Map<String, String> rcMap, Integer x, Integer y) {
        if (rcMap.get("R" + y + "C" + x) == null || rcMap.get("R" + y + "C" + x).toString().equals("")) {
            return 1;
        }
        String[] strs = rcMap.get("R" + y + "C" + x).split("/");
        if (strs == null || strs.length == 0) {
            return 1;
        }
        return Integer.parseInt(strs[0]);
    }

    public static String getRowSpanTableLN(Map<String, String> rcMap, Integer x, Integer y) {
        if (rcMap.get("R" + y + "C" + x) == null || rcMap.get("R" + y + "C" + x).toString().equals("")) {
            return "";
        }
        String[] strs = rcMap.get("R" + y + "C" + x).split("/");
        if (strs == null || strs.length < 2) {
            return "";
        }
        return strs[1];
    }


    public static String getSetValStr(String valType, String setVal) {
        String reStr = setVal;
        if ("Select".equals(valType) || "MultipleSelect".equals(valType)) {
            String[] arr = setVal.split("\\|");
            reStr = "";
            for (String str : arr) {
                reStr += str.substring(str.indexOf("-") + 1) + ",";
            }
            if (reStr.length() > 0) {
                reStr = reStr.substring(0, (reStr.length() - 1));
            }
        }
        return reStr;
    }

    public static String getReValStr(String valType, String setVal, String reVal) {
        String reStr = reVal;
        if (!StringUtil.isNullOrEmpty(reVal) && ("Select".equals(valType) || "MultipleSelect".equals(valType))) {
            String[] arr = setVal.split("\\|");
            reStr = "";
            for (String str : arr) {
                if (reVal.equals(str.substring(0, str.indexOf("-")))) {
                    reStr = str.substring(str.indexOf("-") + 1) + ",";
                }
            }
            if (reStr.length() > 0) {
                reStr = reStr.substring(0, (reStr.length() - 1));
            }
        }
        return reStr;
    }

    public static long parseLong(Object str) {
        String s = convertToString(str);
        long result = 0L;
        if (!s.equals(""))
            result = Long.parseLong(s);
        return result;
    }

    public static double parseDouble(Object str) {
        String s = convertToString(str);
        double result = 0.0D;
        if (!s.equals(""))
            result = Double.parseDouble(s);
        return result;
    }

    public static Double convertToDouble(Object obj, int digitNum) {
        Double valueDouble = 0.00;
        if (obj == null) {
            return 0.00;
        } else {
            if (obj instanceof Integer || obj instanceof Double) {
                valueDouble = (Double) obj;
            } else if (obj instanceof BigDecimal) {
                valueDouble = ((BigDecimal) obj).doubleValue();
            } else {
                valueDouble = Double.parseDouble(obj.toString());
            }
        }
        return new BigDecimal(valueDouble).setScale(digitNum, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static Integer convertToInteger(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        } else if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).intValue();
        } else {
            return Integer.parseInt(obj.toString());
        }
    }

    public static BigDecimal toBigDec(Object str) {
        BigDecimal b = new BigDecimal(str.toString());
        return b;
    }

    public static String toNum(Object str) {
        String b = "0";
        if (str != null) {
            if (!StringUtil.isNullOrEmpty(str.toString())) {
                b = str.toString();
            }
        }
        return b;
    }

    /**
     * 转换obj成字符串，null转为空字符串“”
     *
     * @param obj
     * @return
     */
    public static String convertToString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    /**
     * 移动字符串开头的空格
     *
     * @param str
     * @return
     */
    public static String trimStart(String str) {
        return trim(str, null, -1);
    }

    /**
     * 移除字符串结尾的空格
     *
     * @param str
     * @return
     */
    public static String trimEnd(String str) {
        return trim(str, null, 1);
    }

    /**
     * 判断字符串是空或空字符串
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        for (char ch : str.toCharArray()) {
            if (!Character.isDigit(ch)) return false;
        }
        return true;
    }

    /**
     * 字符串左边填补字符
     *
     * @param str
     * @param totalLength 字符串长度
     * @param c           填补的字符
     * @return
     */
    public static String padLeft(String str, int totalLength, char c) {
        String str1 = str;
        int bit = totalLength;
        int n = str1.length();
        if (n < bit) {
            int m = bit - n;
            for (int i = 0; i < m; i++) {
                str1 = Convert.toString(c) + str1;
            }
            return str1;
        } else return str;
    }

    /**
     * 字符串右边填补字符
     *
     * @param str
     * @param totalLength 字符串长度
     * @param c           填补的字符
     * @return
     */
    public static String padRight(String str, int totalLength, char c) {
        String str1 = str;
        int bit = totalLength;
        int n = str1.length();
        if (n < bit) {
            int m = bit - n;
            for (int i = 0; i < m; i++) {
                str1 = str1 + Convert.toString(c);
            }
            return str1;
        } else return str;
    }

    /**
     * 构造一个字符串
     *
     * @param c
     * @param count
     * @return
     */
    public static String getString(char c, int count) {
        char[] cs = new char[count];
        for (int i = 0; i < count; i++) {
            cs[i] = c;
        }
        return String.copyValueOf(cs);
    }

    /**
     * 移除字符串左右两边的字符
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        if (str != null && str != "") {
            return str.trim();
        }
        return "";
    }

    private static String trim(String str, String stripChars, int mode) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        int start = 0;
        int end = length;

        // 扫描字符串头部
        if (mode <= 0) {
            if (stripChars == null) {
                while ((start < end)
                        && (Character.isWhitespace(str.charAt(start)))) {
                    start++;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while ((start < end)
                        && (stripChars.indexOf(str.charAt(start)) != -1)) {
                    start++;
                }
            }
        }

        // 扫描字符串尾部
        if (mode >= 0) {
            if (stripChars == null) {
                while ((start < end) && (Character.isWhitespace(str.charAt(end - 1)))) {
                    end--;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while ((start < end) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                    end--;
                }
            }
        }

        if ((start > 0) || (end < length)) {
            return str.substring(start, end);
        }

        return str;
    }

    public static String format(String str, Object... args) {
        return format(str, Pattern.compile("\\{(\\d+)\\}"), args);
    }

    /**
     * 字符串参数格式化
     *
     * @param str
     * @param args
     * @return
     */
    public static String format(final String str, Pattern pattern, Object... args) {
        // 这里用于验证数据有效性
        if (str == null || "".equals(str))
            return "";
        if (args.length == 0) {
            return str;
        }

        String result = str;

        // 这里的作用是只匹配{}里面是数字的子字符串
        Pattern p = pattern;
        Matcher m = p.matcher(str);

        while (m.find()) {
            // 获取{}里面的数字作为匹配组的下标取值
            int index = Integer.parseInt(m.group(1));
            // 这里得考虑数组越界问题，{1000}也能取到值么？？
            if (index < args.length) {
                // 替换，以{}数字为下标，在参数数组中取值
                result = result.replace(m.group(), args[index].toString());
            } else {
                result = result.replace(m.group(), "");
            }
        }
        return result;
    }

    public static String substr(String str, int start, int len) {
        return str.substring(start, len);
    }

    /**
     * 字符串转小写
     *
     * @param str
     * @param start
     * @param len
     * @return
     */
    public static String toLower(String str, int start, int len) {
        if (start + len > str.length())
            throw new RuntimeException(format("字符串转小写时，超出长度 Str:{0} Len{1} Start{2}", str, start, len));

        String lStr = str.substring(start, len + start);
        String sStr = str.substring(0, start);
        String eStr = str.substring(start + len, str.length());
        return sStr + lStr.toLowerCase() + eStr;
    }

    /**
     * 字符串转大写
     *
     * @param str
     * @param start
     * @param len
     * @return
     */
    public static String toUpper(String str, int start, int len) {
        if (start + len > str.length())
            throw new RuntimeException(format("字符串转大写时，超出长度 Str:{0} Len{1} Start{2}", str, start, len));

        String uStr = str.substring(start, len);
        String sStr = str.substring(0, start);
        String eStr = str.substring(start + len, str.length() - start - len);

        return sStr + uStr.toUpperCase() + eStr;
    }

    static class Convert {
        public static String toString(Object obj) {
            if (obj == null) return null;
            else if (obj instanceof String) return (String) obj;
            else {
                return obj.toString();
            }
        }

        public static String toString(char c) {
            return String.valueOf(c);
        }

        public static String toString(int i) {
            return String.valueOf(i);
        }
    }

    /**
     * 去除字符串里的html标签
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

    /**
     *
     * @param htmlStr
     * @return
     */
    public static String delSpace(String htmlStr){
        htmlStr = htmlStr.replaceAll("\n", "");
        htmlStr = htmlStr.replaceAll("\t", "");
        return htmlStr.trim(); //返回文本字符串
    }
}
