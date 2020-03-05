package com.ls.utils;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yangzj
 * @desc 系统公用的操作类
 * @create 2018-09-27 16:42
 **/
public class StringUtil {

    /**
     * 把对象转为字符串
     * @param obj
     * @return
     */
    public static String getString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 把对象转为字符串
     * @param obj
     * @return
     */
    public static String getStringTrim(Object obj) {
        return obj == null ? "" : obj.toString().trim();
    }

    /**
     * 处理字符串内容，进行防止SQL注入处理
     * @param str
     * @return
     */
    public static String parseString(String str) {
        return str == null ? "" : str.replaceAll("'", "''");
    }

    /**
     * 转换截取指定的字符串内容，一般用于处理时间字符串问题
     * @param str
     * @return
     */
    public static String parseDateString(String str, int length) {
        if (isEmpty(str)) {
            return "";
        }
        if (length <= 0) {
            return "";
        }
        try {
            return str.replaceAll("T", " ").substring(0, length);
        } catch (Exception e) {
            return "";
        }
    }

    public StringUtil() {
    }

    public static String getSeparator(HttpServletRequest request) {
        String s = request.getAttribute("sys.fieldSeparator").toString();
        return isSame(s, "Tab") ? "\t" : s;
    }

    public static String duplicateString(String text, int count) {
        StringBuilder buf = new StringBuilder();

        for(int i = 0; i < count; ++i) {
            buf.append(text);
        }

        return buf.toString();
    }

    public static String getBracketText(String text, boolean inBracket) {
        int i = text.indexOf(40);
        int j = text.indexOf(41);
        if (i != -1 && j != -1) {
            return inBracket ? text.substring(i + 1, j) : text.substring(0, i);
        } else {
            return text;
        }
    }

    public static String formatFloat(String value, String format) {
        if (isEmpty(value)) {
            return "";
        } else {
            DecimalFormat df = new DecimalFormat(format);
            return df.format(Double.parseDouble(value));
        }
    }

    public static String formatFloat(double value, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(value);
    }

    public static String[] split(String string, String separator) {
        String[] result;
        if (isEmpty(string)) {
            result = new String[]{""};
            return result;
        } else {
            result = new String[stringOccur(string, separator) + 1];
            int oldPos = 0;
            int pos = 0;
            int count = 0;
            int len = separator.length();

            while(pos != -1) {
                pos = string.indexOf(separator, oldPos);
                if (pos != -1) {
                    result[count++] = string.substring(oldPos, pos);
                    pos += len;
                    oldPos = pos;
                }
            }

            result[count] = string.substring(oldPos);
            return result;
        }
    }

    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static String getSql(String sql, String key, String clause) {
        if (!isEmpty(key)) {
            if (!isEmpty(sql)) {
                sql = sql + " and ";
            }

            sql = sql + clause;
        }

        return sql;
    }

    public static boolean isSame(String string1, String string2) {
        String s1;
        if (string1 == null) {
            s1 = "";
        } else {
            s1 = string1;
        }

        String s2;
        if (string2 == null) {
            s2 = "";
        } else {
            s2 = string2;
        }

        return s1.equalsIgnoreCase(s2);
    }

    public static boolean isEqual(String string1, String string2) {
        String s1;
        if (string1 == null) {
            s1 = "";
        } else {
            s1 = string1;
        }

        String s2;
        if (string2 == null) {
            s2 = "";
        } else {
            s2 = string2;
        }

        return s1.equals(s2);
    }

    public static String toHTML(String string) {
        if (isEmpty(string)) {
            return "&nbsp;";
        } else {
            int j = string.length();
            StringBuilder out = new StringBuilder();

            for(int i = 0; i < j; ++i) {
                char c = string.charAt(i);
                switch(c) {
                    case '\n':
                        out.append("<br>");
                    case '\r':
                        break;
                    case '"':
                        out.append("&quot;");
                        break;
                    case '&':
                        out.append("&amp;");
                        break;
                    case '<':
                        out.append("&lt;");
                        break;
                    case '>':
                        out.append("&gt;");
                        break;
                    default:
                        out.append(c);
                }
            }

            return out.toString();
        }
    }

    public static String[] andJoin(String[] list1, String[] list2) {
        ArrayList<String> list = new ArrayList();
        String[] var6 = list1;
        int var5 = list1.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            String s = var6[var4];
            if (stringInList(list2, s) != -1) {
                list.add(s);
            }
        }

        return listToStrings(list);
    }

    public static String[] orJoin(String[] list1, String[] list2) {
        ArrayList<String> list = new ArrayList();
        String[] var6 = list1;
        int var5 = list1.length;

        String s;
        int var4;
        for(var4 = 0; var4 < var5; ++var4) {
            s = var6[var4];
            if (list.indexOf(s) == -1) {
                list.add(s);
            }
        }

        var6 = list2;
        var5 = list2.length;

        for(var4 = 0; var4 < var5; ++var4) {
            s = var6[var4];
            if (list.indexOf(s) == -1) {
                list.add(s);
            }
        }

        return listToStrings(list);
    }

    public static String[] notJoin(String[] list1, String[] list2) {
        ArrayList<String> list = new ArrayList();
        String[] var6 = list1;
        int var5 = list1.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            String s = var6[var4];
            if (stringInList(list2, s) == -1) {
                list.add(s);
            }
        }

        return listToStrings(list);
    }

    public static boolean stringsCross(String[] list1, String[] list2, boolean allowEmpty) {
        int l2;
        if (allowEmpty) {
            int l1 = list1.length;
            l2 = list2.length;
            if (l1 == 0 || l1 == 1 && isEmpty(list1[0]) || l2 == 0 || l2 == 1 && isEmpty(list2[0])) {
                return true;
            }
        }

        String[] var6 = list1;
        int var5 = list1.length;

        for(l2 = 0; l2 < var5; ++l2) {
            String s1 = var6[l2];
            String[] var10 = list2;
            int var9 = list2.length;

            for(int var8 = 0; var8 < var9; ++var8) {
                String s2 = var10[var8];
                if (s1.equals(s2)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int stringInList(String[] list, String string) {
        int j = list.length;

        for(int i = 0; i < j; ++i) {
            if (list[i].equals(string)) {
                return i;
            }
        }

        return -1;
    }

    public static String[] listToStrings(List<String> list) {
        int j = list.size();
        String[] l = new String[j];

        for(int i = 0; i < j; ++i) {
            l[i] = (String)list.get(i);
        }

        return l;
    }

    public static void mergeStringToList(String string, List<String> list) {
        if (!isEmpty(string)) {
            String[] stringList = split(string, "|");
            int j = stringList.length;

            for(int i = 0; i < j; ++i) {
                String trimString = stringList[i].trim();
                if (!isEmpty(trimString) && list.indexOf(trimString) == -1) {
                    list.add(trimString);
                }
            }

        }
    }

    public static String trimLeft(String string) {
        String s = string + "*";
        s = s.trim();
        return s.substring(0, s.length() - 1);
    }

    public static String trimRight(String string) {
        String s = "*" + string;
        s = s.trim();
        return s.substring(1);
    }

    public static String getBoolString(boolean value) {
        return value ? "true" : "false";
    }

    public static boolean getStringBool(String value) {
        return isEqual(value, "1") || isSame(value, "true");
    }

    public static int stringOccur(String source, String dest) {
        if (!isEmpty(source) && !isEmpty(dest)) {
            int pos = 0;
            int count = 0;

            while(pos != -1) {
                pos = source.indexOf(dest, pos);
                if (pos != -1) {
                    ++pos;
                    ++count;
                }
            }

            return count;
        } else {
            return 0;
        }
    }

    public static boolean isNumeric(String string, boolean decimal) {
        String ts = string.trim();
        if (decimal && stringOccur(string, ".") > 1) {
            return false;
        } else {
            if (ts.length() > 1 && isEqual(ts.substring(0, 1), "-")) {
                ts = ts.substring(1);
            }

            int j = ts.length();
            if (j == 0) {
                return false;
            } else {
                for(int i = 0; i < j; ++i) {
                    char ch = ts.charAt(i);
                    if (!Character.isDigit(ch) && (!decimal || ch != '.')) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    public static String toLineString(String string, boolean blank) {
        if (string == null) {
            return "";
        } else {
            if (blank) {
                string = string.trim();
            }

            int len = string.length();
            if (len == 0) {
                return "";
            } else {
                StringBuilder buffer = new StringBuilder();

                for(int i = 0; i < len; ++i) {
                    char c = string.charAt(i);
                    switch(c) {
                        case '\t':
                            if (blank) {
                                buffer.append(" ");
                            } else {
                                buffer.append("\\t");
                            }
                            break;
                        case '\n':
                            if (blank) {
                                buffer.append(" ");
                            } else {
                                buffer.append("\\n");
                            }
                            break;
                        case '\u000b':
                        case '\f':
                        default:
                            buffer.append(c);
                            break;
                        case '\r':
                            if (blank) {
                                buffer.append(" ");
                            } else {
                                buffer.append("\\r");
                            }
                    }
                }

                return buffer.toString();
            }
        }
    }

    public static String toExpress(String string) {
        if (string == null) {
            return "";
        } else {
            int len = string.length();
            if (len == 0) {
                return string;
            } else {
                StringBuilder buffer = new StringBuilder();

                for(int i = 0; i < len; ++i) {
                    char c = string.charAt(i);
                    switch(c) {
                        case '\n':
                            buffer.append("\\n");
                            break;
                        case '\r':
                            buffer.append("\\r");
                            break;
                        case '"':
                            buffer.append("\\\"");
                            break;
                        case '\\':
                            buffer.append("\\\\");
                            break;
                        default:
                            buffer.append(c);
                    }
                }

                return buffer.toString();
            }
        }
    }

    public static String getNamePart(String string) {
        int index = string.indexOf("=");
        return index == -1 ? string : string.substring(0, index);
    }

    public static String getValuePart(String string) {
        int index = string.indexOf("=");
        return index == -1 ? "" : string.substring(string.indexOf("=") + 1);
    }

    public static String fetchString(HttpServletRequest request, String name) {
        if (isEmpty(name)) {
            return null;
        } else {
            Object obj = request.getAttribute(name);
            return obj == null ? request.getParameter(name) : obj.toString();
        }
    }

    public static String replaceParameters(HttpServletRequest request, String text) {
        String paramName;
        String paramValue;
        String result;
        int startPos;
        int endPos;
        for(result = text; (startPos = result.indexOf("{#")) > -1 && (endPos = result.indexOf("#}", startPos)) > -1; result = replace(result, "{#" + paramName + "#}", paramValue)) {
            paramName = result.substring(startPos + 2, endPos);
            paramValue = fetchString(request, paramName);
            if (paramValue == null) {
                paramValue = "";
            }
        }

        return result;
    }

    public static String replace(String string, String oldString, String newString) {
        return innerReplace(string, oldString, newString, true);
    }

    public static String replaceFirst(String string, String oldString, String newString) {
        return innerReplace(string, oldString, newString, false);
    }

    public static String substring(String string, int pos, int len) {
        return string != null && string.length() >= len - pos ? string.substring(pos, pos + len) : "";
    }

    public static String getText(String string) {
        return string == null ? "" : string;
    }

    public static String parseExpress(String exp) {
        return replace(replace(replace(exp, "\\r", "\r"), "\\n", "\n"), "\\t", "\t");
    }

    public static String joinArray(String[] list, String spliter) {
        StringBuilder buf = new StringBuilder();
        boolean isFirst = true;
        String[] var7 = list;
        int var6 = list.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            String s = var7[var5];
            if (isFirst) {
                isFirst = false;
            } else {
                buf.append(spliter);
            }

            buf.append(s);
        }

        return buf.toString();
    }

    public static String joinList(ArrayList<String> array, String spliter) {
        StringBuilder buf = new StringBuilder();
        boolean isFirst = true;

        String s;
        for(Iterator var5 = array.iterator(); var5.hasNext(); buf.append(s)) {
            s = (String)var5.next();
            if (isFirst) {
                isFirst = false;
            } else {
                buf.append(spliter);
            }
        }

        return buf.toString();
    }

    private static String innerReplace(String string, String oldString, String newString, boolean isAll) {
        if (isEmpty(string)) {
            return "";
        } else {
            int start = 0;
            int len = oldString.length();
            if (len == 0) {
                return string;
            } else {
                int index = string.indexOf(oldString);
                if (index == -1) {
                    return string;
                } else {
                    StringBuilder buffer = new StringBuilder();

                    do {
                        buffer.append(string.substring(start, index) + newString);
                        start = index + len;
                        if (!isAll) {
                            break;
                        }

                        index = string.indexOf(oldString, start);
                    } while(index != -1);

                    buffer.append(string.substring(start));
                    return buffer.toString();
                }
            }
        }
    }
}
