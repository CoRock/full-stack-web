<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.corock.mysite.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
	<h1><a href="${pageContext.servletContext.contextPath}">MySite</a></h1>
	<ul>
		<%
			UserVO authUser = (UserVO) session.getAttribute("authUser");
		%>
		<c:choose>
			<c:when test="${empty authUser}">
				<li><a href="${pageContext.servletContext.contextPath}/user?a=loginform">로그인</a><li>
				<li><a href="${pageContext.servletContext.contextPath}/user?a=joinform">회원가입</a><li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.servletContext.contextPath}/user?a=modifyform">회원정보수정</a><li>
				<li><a href="${pageContext.servletContext.contextPath}/user?a=logout">로그아웃</a><li>
				<li>${authUser.name}님 안녕하세요 ^^;</li>
			</c:otherwise>	
		</c:choose>
	</ul>
</div>
