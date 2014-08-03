<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
	<legend>Item Information</legend>
	<table>
		<thead>
			<tr>
				<th></th>
			</tr>
		</thead>
		<tbody id="itemsContainer">


			<c:forEach items="${productBean.items}" var="couponBean"
				varStatus="i" begin="0">
				<tr class="itemClass">
					<td>
						<div>

							<div class="control-group">
								<label for="title" class="control-label">Item Title</label>
								<div class="controls">
									<form:input path="items[${i.index }].title" type="text"
										placeholder="Enter Product Title" required="true"
										autocomplete="off" title="Please Enter Product Title"
										cssClass="input-xlarge" />
								</div>
								<div class="controls">
									<form:input path="items[${i.index }].itemId" type="hidden"/>
								</div>
							</div>




							<div class="control-group">
								<label for="price" class="control-label">Price</label>
								<div class="input-append" style="padding-left: 20px;">
									<form:input path="items[${i.index }].priceBean.mrp"
										placeholder="Enter Retail Price" required="true"
										autocomplete="off" title="Please Enter Retail Price"
										cssClass="span2" type="number" min="0" max="1000000" />
								</div>
								<div class="input-append">
									<form:input path="items[${i.index }].priceBean.sellingPrice"
										placeholder="Enter Selling Price" required="true"
										autocomplete="off" title="Please Enter Selling Price"
										cssClass="span2" type="number" min="0" max="1000000" />
								</div>
							</div>

							<div class="control-group">
								<label for="stock.quantity" class="control-label">Stock
									Quantity</label>
								<div class="controls">
									<form:input path="items[${i.index }].stock.quantity"
										placeholder="Product Quantity" required="true"
										autocomplete="off" title="Please Enter Quantity"
										cssClass="span2" type="number" min="0" max="10000" />
								</div>
							</div>

							<div class="control-group">
								<label for="sku" class="control-label">SKU or ProductID</label>
								<div class="controls">
									<form:input path="items[${i.index }].sku"
										placeholder="Enter SKU" required="true" autocomplete="off"
										title="Please Enter SKU" cssClass="span2" />
								</div>
							</div>

							<div class="control-group">
								<label for="weight" class="control-label"> Weight </label>
								<div class="input-append" style="padding-left: 20px;">
									<form:input
										path="items[${i.index }].dimensionsAndWeight.weight"
										placeholder="Enter Weight" required="true" autocomplete="off"
										title="Please Enter Product Weight" cssClass="input-small"
										type="number" min="0" max="10000" />
								</div>
								<div class="input-append">
									<form:input
										path="items[${i.index }].dimensionsAndWeight.length"
										placeholder="Enter Length" required="true" autocomplete="off"
										title="Please Enter Product Length" cssClass="input-small"
										type="number" min="0" max="10000" />
								</div>
								</div>
								<div class="control-group">
								<label for="width" class="control-label"> Dimension</label>
								
								<div class="input-append" style="padding-left: 20px;">
									<form:input path="items[${i.index }].dimensionsAndWeight.width"
										placeholder="Enter Width" required="true" autocomplete="off"
										title="Please Enter Product Width" cssClass="input-small"
										type="number" min="0" max="10000" />
								</div>

								

								<div class="input-append">
									<form:input
										path="items[${i.index }].dimensionsAndWeight.height"
										placeholder="Enter Height" required="true" autocomplete="off"
										title="Please Enter Product Height" cssClass="input-small"
										type="number" min="0" max="10000" />
								</div>
							</div>
							<a href="#" class="removeItem">Remove</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</fieldset>
<a href="#" id="addItems">Add</a>



