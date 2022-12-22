package com.example.shop.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.buf.MessageBytes;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.shop.common.consts.AnsiConst.*;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestData;
        long startTime = System.currentTimeMillis();

        String trxId = request.getHeader("12cm-trx-id");
        if (trxId == null || trxId.equals("")) {
                trxId = createTrxNumber();
        }
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        String userAgent = request.getHeader("User-Agent");
        String userId = request.getHeader("user_id");

        Object a = findCoyoteRequest(request);
        Field coyoteRequest = a.getClass().getDeclaredField("coyoteRequest");
        coyoteRequest.setAccessible(true);
        Object b = coyoteRequest.get(a);

        Field uriMB = b.getClass().getDeclaredField("uriMB");
        uriMB.setAccessible(true);
        MessageBytes requestUri = (MessageBytes)uriMB.get(b);
        String requestUrl = requestUri.toString();

        String logMsg = "\n" +
                ANSI_BLUE + "============================================================================\n" +
                ANSI_CYAN + "Transaction Start [" + ANSI_RESET + trxId + ANSI_CYAN + "]\n" +
                ANSI_YELLOW + "Request URL    : " + ANSI_RESET + requestUrl + "\n" +
                ANSI_YELLOW + "IP             : " + ANSI_RESET + ip + "\n" +
                ANSI_YELLOW + "User-Agent     : " + ANSI_RESET + userAgent + "\n" +
                ANSI_YELLOW + "OS             : " + ANSI_RESET + getOs(userAgent) + "\n" +
                ANSI_YELLOW + "User-id        : " + ANSI_RESET + userId + "\n" +
                ANSI_BLUE + "============================================================================\n" + ANSI_RESET;
        log.info(logMsg);

        request.setAttribute("startTime", startTime);
        request.setAttribute("12cm-trx-id", trxId);
        request.setAttribute("requestUri", requestUrl);

        if ("POST".equals(request.getMethod()) || "PUT".equals(request.getMethod())) {
//            log.info("post log");
//            log.info("[+] PATH : {}, PARAMS {}", request.getPathInfo(), request.getAttribute("requestBody"));
            requestData = Optional.ofNullable(request.getAttribute("requestBody")).orElse("").toString();
        } else {
            requestData = getParameters(request);
        }

        if(requestData.length() == 1){
            requestData = "None";
        }

        request.setAttribute("requestData", requestData);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        printLog(request, response, null);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        if (ex != null) {
//            printLog(request, response, ex);
//        }
    }

    private void printLog(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        try {
            long startTime = (Long) request.getAttribute("startTime");
            SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;

            String logMsg = "\n" +
                    ANSI_BLUE + "============================================================================\n" +
                    ANSI_YELLOW + "Request [" + ANSI_CYAN + dayTime.format(startTime) + ANSI_YELLOW + "]\n" +
                    ANSI_BLUE + "============================================================================\n" +
                    ANSI_YELLOW + "Url            : " + ANSI_RESET + request.getAttribute("requestUri") + "\n" +
                    ANSI_YELLOW + "Method         : " + ANSI_RESET + request.getMethod() + "\n" +
                    ANSI_YELLOW + "trxId          : " + ANSI_RESET + request.getAttribute("trxId") + "\n" +
                    ANSI_YELLOW + "UserId         : " + ANSI_RESET + request.getHeader("user_id") + "\n" +
                    ANSI_YELLOW + "Data           : " + ANSI_RESET + request.getAttribute("requestData") + "\n" +
                    ANSI_BLUE + "============================================================================\n" +
                    ANSI_YELLOW + "Response [" + ANSI_CYAN + dayTime.format(endTime) + ANSI_YELLOW + "]\n" +
                    ANSI_BLUE + "============================================================================\n" +
                    ANSI_YELLOW + "Process Time   : " + ANSI_RESET + processTime + "ms\n" +
                    ANSI_YELLOW + "Status         : " + ANSI_RESET + response.getStatus() + "\n" +
                    ANSI_YELLOW + "Content-Type   : " + ANSI_RESET + response.getHeader("Content-Type") + "\n" +
                    ANSI_YELLOW + "Content-Length : " + ANSI_RESET + response.getHeader("Content-Length") + "\n" +
                    ANSI_BLUE + "============================================================================\n" + ANSI_RESET;
            if (response.getStatus() >= 400) {
                logMsg += "ERROR"+ "\n" +
                        ANSI_BLUE + "============================================================================\n" +
                        ANSI_RED + request.getAttribute("error") + "\n" +
                        ANSI_BLUE + "============================================================================\n" + ANSI_RESET;
                log.error(logMsg);

            } else {
                log.info(logMsg);
            }
        } catch (Exception e) {
            log.error("error : ", e);
        }
    }

    private String createTrxNumber() {
        String TRX_NUM_PREFIX = "VMKR-SERV";
        String TRX_DATE_TIME = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        return TRX_NUM_PREFIX + "-" + TRX_DATE_TIME + "-" + RandomStringUtils.randomAlphabetic(6) + "-" + RandomStringUtils.randomAlphabetic(5);
    }

    private String getParameters(HttpServletRequest request) {
        StringBuilder posted = new StringBuilder();
        Enumeration<?> e = request.getParameterNames();
        if (e != null) {
            posted.append("?");
        }
        while (e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            }
            String curr = (String) e.nextElement();
            posted.append(curr).append("=");
            if (curr.contains("password")
                    || curr.contains("pass")
                    || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }

        return posted.toString();
    }

    private String getOs(String userAgent) {
        String os = "Unknown";
        userAgent = userAgent.toLowerCase();

        if (userAgent.contains("windows")) {
            os = "Windows";
        } else if(userAgent.contains("mac")) {
            os = "Mac";
        } else if(userAgent.contains("x11")) {
            os = "Unix";
        } else if(userAgent.contains("android")) {
            os = "Android";
        } else if(userAgent.contains("iphone")) {
            os = "IPhone";
        }

        return os;
    }

    private Class getClassByName(Class classObject, String name){
        Map<Class, List<Field>> fieldMap = new HashMap<>();
        Class returnClass = null;
        Class tempClass = classObject;
        while (tempClass != null) {
            fieldMap.put(tempClass, Arrays.asList(tempClass .getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }

        for(Map.Entry<Class,List<Field>> entry: fieldMap.entrySet()){
            for (Field f : entry.getValue()) {
                if(f.getName().equals(name)){
                    returnClass = entry.getKey();
                    break;
                }
            }
        }
        return returnClass;
    }

    private Object findCoyoteRequest(Object request)  throws Exception {
        Class a = getClassByName(request.getClass(), "request");
        Field request1 = a.getDeclaredField("request");
        request1.setAccessible(true);
        Object b = request1.get(request);
        if (getClassByName(b.getClass(), "coyoteRequest") == null) {
            return findCoyoteRequest(b);
        } else {
            return b;
        }
    }
}
