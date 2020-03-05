package com.ls.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yangzj
 * @desc HTTP请求相关的工具类
 * @create 2018-01-28 17:06
 **/
public class HttpUtil {


    public static String sendGet(String urlstr, String content) {
        return sendGet(urlstr, content, "");
    }

    /**
     * 通过PUT提交表单发送报文
     * @param urlstr
     * @param content
     * @return
     */
    public static String sendGet(String urlstr, String content, String encode) {
        return sendHttp(urlstr, "GET", content, encode);
    }


    public static String sendPost(String urlstr, String content) {
        return sendPost(urlstr, content, "");
    }

    /**
     * 通过POST提交表单发送报文
     * @param urlstr
     * @param content
     * @return
     */
    public static String sendPost(String urlstr, String content, String encode) {
        return sendHttp(urlstr, "POST", content, encode);
    }

    public static String sendPost(String urlstr, Map<String, String> headers, String content) {
        return sendHttp(urlstr, "POST", headers, content, "");
    }

    public static String sendHttp(String urlstr, String httpMethod, String content, String encode) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Content-Length", String.valueOf(content.length()));
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36");
        headers.put("Accept", "*/*");
        headers.put("Accept-Language", "zh-CN,zh");
        return sendHttp(urlstr, httpMethod, headers, content, encode);
    }

    public static String sendHttp(String urlstr,
                                  String httpMethod,
                                  Map<String, String> headers,
                                  String content,
                                  String encode){
        return sendHttp(urlstr, httpMethod, headers, content, encode, "");
    }

    public static String sendHttp(String urlstr,
                                  String httpMethod,
                                  Map<String, String> headers,
                                  String content,
                                  String encode,
                                  String httpMediaType) {
        try {
            URL url;
            if ("GET".equals(httpMethod)) {
                url = new URL(urlstr + "?" + content);
            } else {
                url = new URL(urlstr);
            }
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            if ("GET".equals(httpMethod)) {
                http.setRequestMethod("GET");
                http.setRequestProperty("accept", "*/*");
                http.setRequestProperty("connection", "Keep-Alive");
                http.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            } else {
                http.setRequestMethod("POST");
            }
            http.setConnectTimeout(30000);
            if ("json".equals(httpMediaType)) {
                http.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                http.setRequestProperty("accept","application/json");
            }
            if (headers != null) {
                for (Iterator<String> it = headers.keySet().iterator(); it.hasNext(); ) {
                    String key = it.next();
                    String value = headers.get(key);
                    http.setRequestProperty(key, value);
                }
            }
            if ("GET".equals(httpMethod)) {
                http.connect();// 连接
            } else {
                http.setDefaultUseCaches(false);
                http.setDoOutput(true);
                http.connect();// 连接
                // 获得输出流,向服务器输出数据
                DataOutputStream out = new DataOutputStream(http.getOutputStream());
//            System.out.println("===========================");
//            System.out.println(content);
                byte[] contentBytes;
                if (!StringUtil.isEmpty(encode)) {
                    try {
                        contentBytes = content.getBytes(encode);
                    } catch (Exception e) {
                        contentBytes = content.getBytes();
                    }
                    out.write(contentBytes);
                } else {
                    out.write(content.getBytes());
                }
                out.flush();
                out.close();
            }
            // 获得服务器响应的结果和状态码
            int responseCode = http.getResponseCode();
            if (responseCode != 200) {
                StringBuffer sb = new StringBuffer();
                InputStream in = http.getErrorStream();
                if (in != null) {
                    String charset = "utf-8";
                    InputStreamReader inr = new InputStreamReader(in, charset);
                    BufferedReader br = new BufferedReader(inr);
                    String line = null;

                    while((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                } else {
                    sb.append("无错误信息返回");
                }
                return "发送请求失败(HTTP-COD:" + responseCode + ";ResponseMessage:" + sb.toString() + ")";
            } else {
                InputStream in = http.getInputStream();
                String charset = "utf-8";
                InputStreamReader inr = new InputStreamReader(in, charset);
                BufferedReader br = new BufferedReader(inr);
                String line = null;
                StringBuffer sb = new StringBuffer();

                while((line = br.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage() == null ? "空指针错误" : e.getMessage();
        }
    }


    /**
     * 获取文件留
     */
    public static byte[] getHttpByte(String urlstr,
                                     String httpMethod,
                                     Map<String, String> headers,
                                     String content,
                                     String encode) {
        HttpURLConnection http = null;
        try {
            URL url;
            if ("GET".equals(httpMethod)) {
                if(!"".equals(content) && content != null) {
                    url = new URL(urlstr + "?" + content);
                }else{
                    url = new URL(urlstr);
                }
            } else {
                url = new URL(urlstr);
            }
            http = (HttpURLConnection) url.openConnection();
            if ("GET".equals(httpMethod)) {
                http.setRequestMethod("GET");
//                http.setRequestProperty("accept", "*/*");
//                http.setRequestProperty("connection", "Keep-Alive");
//                http.setRequestProperty("user-agent",
//                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            } else {
                http.setRequestMethod("POST");
            }
            // 设置连接超时时间
            http.setConnectTimeout(30000);
            // 设置读取服务器返回数据超时时间
            http.setReadTimeout(30000);
            if (headers != null) {
                for (Iterator<String> it = headers.keySet().iterator(); it.hasNext(); ) {
                    String key = it.next();
                    String value = headers.get(key);
                    http.setRequestProperty(key, value);
                }
            }
            if ("GET".equals(httpMethod)) {
                http.connect();// 连接
            } else {
                http.setDefaultUseCaches(false);
                http.setDoOutput(true);
                http.connect();// 连接
                // 获得输出流,向服务器输出数据
                DataOutputStream out = new DataOutputStream(http.getOutputStream());
                byte[] contentBytes;
                if (!StringUtil.isEmpty(encode)) {
                    try {
                        contentBytes = content.getBytes(encode);
                    } catch (Exception e) {
                        contentBytes = content.getBytes();
                    }
                    out.write(contentBytes);
                } else {
                    out.write(content.getBytes());
                }
                out.flush();
                out.close();
            }
            // 获得服务器响应的结果和状态码
            int responseCode = http.getResponseCode();
            StringBuffer sb = new StringBuffer();
            InputStream in = null;
            String charset = "utf-8";
            if (responseCode == 200) {
                in = http.getInputStream();
            }
            if (in != null) {
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                byte[] buff = new byte[100];
                int rc = 0;
                while ((rc = in.read(buff, 0, 100)) > 0) {
                    swapStream.write(buff, 0, rc);
                }
                swapStream.close();
                in.close();
                return swapStream.toByteArray();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (http != null) {
                http.disconnect();
            }
        }
    }

    /**
     * 通过URL下载指定文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @return
     * @throws IOException
     */
    public static String downLoadFromUrl(String urlStr,
                                         String fileName,
                                         String savePath) throws IOException {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(30000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            //得到输入流
            InputStream ins = conn.getInputStream();
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();

            System.out.println("info:"+url+" download success");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage() == null ? "空指针错误" : e.getMessage();
        }
    }

    /**
     * @Title: getIpAddr
     * @author kaka  www.zuidaima.com
     * @Description: 获取客户端IP地址
     * @param @return
     * @return String
     * @throws
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (("127.0.0.1").equals(ip) || ("0.0.0.0.0.1").equals(ip)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
    

	public static String getString(Object o) {
		if(o != null){
    		return o.toString();
    	}else{
    		return "";
    	}
	}

}
