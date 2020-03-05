<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="/WEB-INF/views/common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
           		<form method="post" action="/user/userlist?pageNum=">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>用户名：</span>
					 <input name="queryname" class="input-text"	type="text" value="${queryUserName }">
					 
					 <span>用户角色：</span>
					 <select name="queryUserRole">
						<c:if test="${roleList != null }">
						   <option value="0">--请选择--</option>
						   <c:forEach var="role" items="${roleList}">
						   		<option <c:if test="${role.id == queryUserRole }">selected="selected"</c:if>
						   		value="${role.id}">${role.roleName}</option>
						   </c:forEach>
						</c:if>
	        		 </select>
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="查 询" type="submit" id="searchbutton">
					 <a href="/user/useradd" >添加用户</a>
				</form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户角色</th>
                    <th width="30%">操作</th>
                </tr>
			   <c:forEach var="userList" items="${userList }" varStatus="status">
					<tr>
						<td>
							<span>${userList.userCode }</span>
						</td>
						<td>
							<span>${userList.userName }</span>
						</td>
						<td>
							<span>
								<c:if test="${userList.gender==1}">男</c:if>
								<c:if test="${userList.gender==2}">女</c:if>
							</span>
						</td>
						<td>
							<span>${userList.age}</span>
						</td>
						<td>
							<span>${userList.phone}</span>
						</td>
						<td>
							<span>${userList.userRoleName}</span>
						</td>
						<td>
							<span>
								<a class="viewUser" href="javascript:;" userid=${userList.id } username=${userDTO.userName }>
									<img src="/images/read.png" alt="查看" title="查看"/>
								</a>
							</span>
							<span>
								<a class="modifyUser" href="javascript:;" userid=${userList.id } username=${userDTO.userName }>
									<img src="/images/xiugai.png" alt="修改" title="修改"/>
								</a>
							</span>
							<span>
								<a class="deleteUser" href="javascript:;" userid=${userList.id } username=${userDTO.userName }>
									<img src="/images/schu.png" alt="删除" title="删除"/>
								</a>
							</span>
						</td>
					</tr>
			   </c:forEach>
			</table>

			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>

		  	<c:import url="rollpage.jsp">
	          	<c:param name="totalCount" value="${totalCount}"/>
	          	<c:param name="currentPageNo" value="${currentPageNo}"/>
	          	<c:param name="totalPageCount" value="${totalPageCount}"/>
          	</c:import>
        </div>
    </section>
<%@include file="/WEB-INF/views/common/foot.jsp" %>
<script type="text/javascript" src="/js/userlist.js"></script>
