<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html lang="en">
<jsp:include page="header.jsp" />
<body>
	<div class="container">
		<jsp:include page="topNav.jsp" />
		<c:if test="${productBean ne null }">
			<%@include file="messages.jsp"%>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Key</th>
						<th>Value</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Product Id</td>
						<td>${productBean.productId}</td>
					</tr>

					<tr>
						<td>Product Title</td>
						<td>${productBean.title}</td>
					</tr>
					<tr>
						<td>Product Categories</td>
						<td>
							<ul>
								<c:forEach var="categoryIds"
									items="${productBean.categoryIdToCategoryBeanList}">
									<li><c:forEach var="category" items="${categoryIds.value}" varStatus="loopCounter">
											 ${category.categoryName}
											 <c:if test="${fn:length(categoryIds.value) != loopCounter.count}">
											 >
											</c:if>
										</c:forEach></li>
								</c:forEach>
							</ul>
						</td>
					</tr>
					<tr>
						<td>Product Description</td>
						<td>${productBean.description}</td>
					</tr>

					<tr>
						<td>Product Start Date</td>
						<td><fmt:formatDate value="${productBean.startDate}"
								pattern="dd-MMM-yyyy hh:mm:ss aa" /></td>
					</tr>
					<tr>
						<td>Product End Date</td>
						<td><fmt:formatDate value="${productBean.endDate}"
								pattern="dd-MMM-yyyy hh:mm:ss aa" /></td>
					</tr>
					<tr>
						<td>Product Origin Country</td>
						<td>${productBean.originCountry.countryName}</td>
					</tr>
					
					<tr>
						<td>Product Stock Quantity</td>
						<td>${productBean.items[0].stock.quantity}</td>
					</tr>


				</tbody>
		</c:if>
	</div>
</body>
</html>