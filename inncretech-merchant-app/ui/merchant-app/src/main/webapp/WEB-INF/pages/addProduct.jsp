<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html lang="en">
<jsp:include page="header.jsp" />
<script type="text/javascript"
	src="<c:url value="/resources/js/categorytreecreation.js"/>"></script>
<body>
	<div class="container">
		<jsp:include page="topNav.jsp" />
		<form:form commandName="productBean" cssClass="form-horizontal">
			<%@include file="messages.jsp"%>
			<fieldset>
				<div class="control-group">
					<label for="title" class="control-label">Product Title</label>
					<div class="controls">
						<form:input path="title" type="text"
							placeholder="Enter Product Title" required="true"
							autocomplete="off" title="Please Enter Product Title"
							cssClass="input-xlarge" />
					</div>
				</div>




				<div class="control-group">
					<label for="description" class="control-label">Product
						Description</label>
					<div class="controls">
						<form:textarea path="description" placeholder="Enter Description"
							required="true" autocomplete="off"
							title="Please Enter Description" cssClass="span3" />
					</div>
				</div>

				<div class="control-group">
					<label for="price" class="control-label">Price</label>
					<div class="input-append" style="padding-left: 20px;">
						<form:input path="priceBean.mrp" placeholder="Enter Retail Price"
							required="true" autocomplete="off"
							title="Please Enter Retail Price" cssClass="input-large" type="number"/>
					</div>
					<div class="input-append">
						<form:input path="priceBean.sellingPrice"
							placeholder="Enter Selling Price" required="true"
							autocomplete="off" title="Please Enter Selling Price"
							cssClass="input-large" type="number"/>
					</div>
				</div>

				<div class="control-group">
					<label for="stock.quantity" class="control-label">Stock
						Quantity</label>
					<div class="controls">
						<form:input path="stock.quantity" placeholder="Product Quantity"
							required="true" autocomplete="off" title="Please Enter Quantity"
							cssClass="span2" type="number"/>
					</div>
				</div>

				<div class="control-group">
					<label for="sku" class="control-label">SKU or ProductID</label>
					<div class="controls">
						<form:input path="sku" placeholder="Enter SKU" required="true"
							autocomplete="off" title="Please Enter SKU" cssClass="span2" />
					</div>
				</div>

				<div class="control-group">
					<label for="weight" class="control-label">Dimension &
						Weight </label>
					<div class="input-append" style="padding-left: 20px;">
						<form:input path="dimensionsAndWeight.weight"
							placeholder="Enter Weight" required="true" autocomplete="off"
							title="Please Enter Product Weight" cssClass="input-small" type="number"/>
					</div>

					<div class="input-append">
						<form:input path="dimensionsAndWeight.width"
							placeholder="Enter Width" required="true" autocomplete="off"
							title="Please Enter Product Width" cssClass="input-small" type="number"/>
					</div>

					<div class="input-append">
						<form:input path="dimensionsAndWeight.length"
							placeholder="Enter Length" required="true" autocomplete="off"
							title="Please Enter Product Length" cssClass="input-small" type="number"/>
					</div>

					<div class="input-append">
						<form:input path="dimensionsAndWeight.height"
							placeholder="Enter Height" required="true" autocomplete="off"
							title="Please Enter Product Height" cssClass="input-small" type="number"/>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="startDateFrom">Start
						Date(From/To): </label>
					<div class="input-append date" id="dp3"
						data-date-format="dd-mm-yyyy" style="padding-left: 20px;">
						<form:input path="startDate" class="span2"
							placeholder="dd-mm-yyyy" readonly="true" />
						<span class="add-on"><i class="icon-calendar"></i></span>
					</div>
					<div class="input-append date" id="dp13"
						data-date-format="dd-mm-yyyy" style="padding-left: 20px;">
						<form:input path="endDate" class="span2" placeholder="dd-mm-yyyy"
							readonly="true" />
						<span class="add-on"><i class="icon-calendar"></i></span>
					</div>
				</div>
				<div class="control-group">
					<label for="categoryIds" class="control-label">Category Ids
						:</label>
					<div class="controls">
						<div id="cateogryDropDown"></div>
						<div id="cateogry"></div>
						<div>
							<form:select path="categoryIds" cssStyle="display:none;"></form:select>
						</div>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="originCountry">Origin
						Country <span style="color: #FF0000;">*</span>:
					</label>
					<div class="controls">
						<form:select path="originCountry.countryId" required="true"
							title="Please Select Origin Country">
							<form:option value="" label="---Select Origin Country ----"></form:option>
							<form:options items="${dropDownBean.countryMap}"></form:options>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-primary" id="select_all">Submit</button>
					</div>
				</div>
			</fieldset>
		</form:form>


	</div>
	<script type="text/javascript">
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