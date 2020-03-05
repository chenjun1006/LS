<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="/WEB-INF/views/common/head.jsp"%>
    <div class="right">
        <img class="wColck" src="/images/clock.jpg" alt=""/>
        <div class="wFont">
            <h3>${userSession.username }</h3>
            <p>欢迎来到蓝闪管理系统!</p>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/views/common/foot.jsp" %>
