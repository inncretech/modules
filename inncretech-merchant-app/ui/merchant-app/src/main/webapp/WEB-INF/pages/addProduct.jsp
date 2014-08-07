<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="f" uri="http://www.inncretech.com/functions"%>

<html lang="en" ng-app="productApp">
<jsp:include page="header.jsp" />
<script type="text/javascript"
	src="<c:url value="/resources/js/controller.js?<%=new Date() %>"/>"></script>
<script type="text/javascript">
var product=${f:convertToJson(productBean)};
var countryMap =${f:convertToJson(dropDownBean.countryMap)};
var blankItem=${f:getItemBlankJsonObject()};
</script>
<body ng-controller="ProductController">
	<div class="container">
		<jsp:include page="topNav.jsp" />
		<form:form commandName="productBean" cssClass="form-horizontal">
			<%@include file="messages.jsp"%>
			<fieldset>
				<div class="control-group">
					<label for="title" class="control-label">Product Title</label>
					<div class="controls">
						<input type="text" name="title" ng-model="product.title"
							placeholder="Enter Product Title" required="true"
							autocomplete="off" title="Please Enter Product Title"
							cssClass="input-xlarge" />
					</div>
				</div>

				<div class="control-group">
					<label for="description" class="control-label">Product
						Description</label>
					<div class="controls">
						<textarea rows="3" cols="10" name="description"
							placeholder="Enter Description" required="true"
							autocomplete="off" title="Please Enter Description"
							cssClass="span3" ng-model="product.description"></textarea>
					</div>
				</div>


				<div class="control-group">
					<label class="control-label" for="startDateFrom">Start
						Date(From/To): </label>
					<div class="input-append date" id="dp3"
						data-date-format="dd-mm-yyyy" style="padding-left: 20px;">
						<input type="text" name="startDate" class="span2"
							placeholder="dd-mm-yyyy" readonly="true"
							ng-model="product.startDate" /> <span class="add-on"><i
							class="icon-calendar"></i></span>

					</div>
					<div class="input-append date" id="dp13"
						data-date-format="dd-mm-yyyy" style="padding-left: 20px;">
						<input type="text" name="endDate" class="span2"
							placeholder="dd-mm-yyyy" readonly="true"
							ng-model="product.endDate" /> <span class="add-on"><i
							class="icon-calendar"></i></span>
					</div>
				</div>
				<div class="control-group">
					<label for="categoryIds" class="control-label">Category Ids
						:</label>
					<div class="controls">
						<div id="cateogryDropDown"></div>
						<div id="cateogry"></div>
						<div>
							<select name="categoryIds" id="categoryIds"
								style="display:none;" multiple="multiple"></select>
						</div>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="originCountry">Origin
						Country <span style="color: #FF0000;">*</span>:
					</label>
					<div class="controls">
						<select ng-model="product.originCountry.countryId"
							name="originCountry.countryId"
							ng-Options="id as value for (id,value) in countryMap"
							required="true" title="Please Select Origin Country">
							<option value="">---Select Origin Country ----</option>
						</select>
					</div>
				</div>
				<c:import url="item.jsp" />
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-primary" id="select_all">Submit</button>
					</div>
				</div>
			</fieldset>
		</form:form>
		<c:import url="addItem.jsp"/>

	</div>
	<script type="text/javascript">
		function rowAdded(rowElement) {
			$(rowElement).find("input").val('');
		}
		function rowRemoved(rowElement) {
		}
		
		$(document).ready(function() {
			//comobo box
		   	var config = {
			rowClass : 'itemClass',
		   	addRowId : 'addItems',
		  	removeRowClass : 'removeItem',
		  	formId : 'productBean',
		  	rowContainerId : 'itemsContainer',
		   	indexedPropertyName : 'items',
		   	indexedPropertyMemberNames : 'title,priceBean.mrp,priceBean.sellingPrice,sku,stock.quantity,dimensionsAndWeight.weight,dimensionsAndWeight.length,dimensionsAndWeight.width,dimensionsAndWeight.height',
			rowAddedListener : rowAdded,
		 };
		    new DynamicListHelper(config);
		});
		$(document)
				.ready(
						function() {
							getCategoryData(0);
							$('#dp3,#dp13').datepicker();
							populateSelectedCategoryonRefresh(<c:out value="${productBean.categoryIds}"/>);
						});

		$(function() {
			var createAllErrors = function() {
				var form = $(this);
				var errorList = $('div.errorMessages', form);
				var showAllErrorMessages = function() {
					errorList.empty();
					//Find all invalid fields within the form.
					form.find(':invalid').each(
							function(index, node) {
								var message = node.title || 'Invalid value.';
								errorList.show()
										.append(
												'<span> * ' + message
														+ '</span> </br>');
							});
					if ($('.errorMessages').text() == '') {
						$('.errorMessages').hide();
					}
				};
				$('button[type=submit]', form)
						.on('click', showAllErrorMessages);
				$('input[type=text]', form).on('keypress', function(event) {
					//keyCode 13 is Enter
					if (event.keyCode == 13) {
						showAllErrorMessages();
					}
				});
			};

			$('form').each(createAllErrors);
		});
	</script>

</body>

</html>