package com.ls.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class WebBuilderUtil {

    public static void main(String[] args) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("CA1", Math.random() * 1000);
            map.put("CA2", "CAS" + String.valueOf(Math.random() * 1000));
            map.put("CA3", new Date());
            map.put("CA4", i);
            map.put("CA5", "ABC");
            list.add(map);
        }
        System.out.println(getDataJson(list, list.size(), "-", "", false));
    }

    public static String getDataJson(List<Map<String, Object>> resultSet, int total,
                                     String metaData, String colDefine,
                                     boolean dateAsString) {
        int count = 0;
        boolean hasTotal = false;
        StringBuilder express = new StringBuilder();

        if (resultSet == null || resultSet.size() == 0)
            return "{\"total\":0,\"row\":[]}";
        Map<String, Object> meta = resultSet.get(0);

        express.append("{\"total\":");
        express.append(total);
        if (!StringUtil.isEmpty(metaData)) {
            express
                    .append(",\"metaData\":{\"totalProperty\":\"total\",\"root\":\"row\",\"fields\":[");
            if (metaData.equals("-"))
                express.append(getFieldsJson(meta, dateAsString));
            else
                express.append(metaData);
            express.append("]}");
        }
        if (!StringUtil.isEmpty(colDefine)) {
            express.append(",\"colDefine\":[");
            if (colDefine.equals("-"))
                express
                        .append(getResultSetMeta(meta, dateAsString));
            else
                express.append(colDefine);
            express.append("]");
        }
        express.append(",\"row\":[");
        for (int i = 0; i < resultSet.size(); i++) {
            if (i > 0) {
                express.append(",");
            }
            express.append("{");
            Map<String, Object> map = resultSet.get(i);

            int j = 0;
            for (String column : map.keySet()) {
                if (j > 0) {
                    express.append(",");
                }
                express.append("\"");
                express.append(StringUtil.replace(StringUtil.toExpress(column), ".", "．"));
                express.append("\":\"");
                express.append(StringUtil.toExpress(getFieldValue(map.get(column), dateAsString)));
                express.append("\"");
                j++;
            }
            express.append("}");
        }
        express.append("]}");
        if (hasTotal)
            express.insert(0, "{\"total\":" + Integer.toString(count));
        return express.toString();
    }

    public static String getFieldsJson(Map<String, Object> meta,
                                       boolean dateAsString) {
        StringBuilder express = new StringBuilder();

        int i = 0;
        for (String column : meta.keySet()) {
            if (i > 0)
                express.append(",");
            express.append("{\"name\":\"");
            express.append(StringUtil.replace(StringUtil.toExpress(column), ".", "．"));
            express.append("\",\"type\":\"");
            String category = getDataTypeCategory(meta.get(column),
                    dateAsString);
            express.append(category);
            if ((!dateAsString) && (category.equals("date")))
                express.append("\",\"dateFormat\":\"Y-m-d H:i:s");
            express.append("\"}");
            i++;
        }
        return express.toString();
    }

    public static String getDataTypeCategory(Object value, boolean dateAsString) {
        if (dateAsString) {
            return "string";
        }
        if (value instanceof BigDecimal) {
            return "float";
        } else if (value instanceof Date) {
            return "date";
        } else {
            return "string";
        }
    }

    public static String getResultSetMeta(Map<String, Object> meta,
                                          boolean dateAsString) {
        StringBuilder express = new StringBuilder();

        int i = 0;
        for (String column : meta.keySet()) {
            if (i > 0)
                express.append(",");
            String cap = column;
            String enCap = StringUtil.toExpress(cap);
            express.append("{dataIndex:\"");
            express.append(StringUtil.replace(enCap, ".", "．"));
            express.append("\",header:\"");
            express.append(enCap);
            express.append("\",width:");
            String type = getDataTypeCategory(meta.get(cap), dateAsString);
            int len = 30;
            int k = cap.length() + 1;
            if (len < k)
                len = k;
            if (len < 4)
                len = 4;
            if ("float".equals(type)) {
                express.append(150);
                if (!dateAsString)
                    express.append(",renderer:function(v){if(v){if(v.format('His')=='000000')return v.format('Y-n-j');else return v.format('Y-n-j H:i:s');}else return '';}");
            } else {
                express.append(len * 9);
            }
            if ("string".equals(getDataTypeCategory(type, true)))
                express.append(",align:\"right\"");
            express.append(",sortable:true");
            express.append("}");
            i++;
        }
        return express.toString();
    }

    public static String getFieldValue(Object value, boolean dateAsString) {
        String type = getDataTypeCategory(value, dateAsString);

        if ("date".equals(type)) {
            return dateToString((Date) value);
        }
        return StringUtil.getString(value);
    }

    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(date);
        }
    }

}

