<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div ng-repeat="item in product.items">
	<div id=" div{{$index}}">
		<div class="control-group">
			<label for="title" class="control-label">Item Title</label>
			<div class="controls">
				<span class="input-medium uneditable-input">{{item.title}}</span> <input
					type="hidden" name="items[{{$index}}].title" ng-model="item.title"
					ng-value="item.title" /> <input type="hidden"
					name="items[{{$index}}].itemId" ng-model="item.itemId"
					ng-value="item.itemId" />
			</div>
		</div>
		<div class="control-group">
			<label for="price" class="control-label">MRP</label>
			<div class="controls">
				<span class="input-medium uneditable-input">{{item.priceBean.mrp}}</span>
				<input type="hidden" name="items[{{$index}}].priceBean.mrp"
					ng-model="item.priceBean.mrp" ng-value="item.priceBean.mrp" />
			</div>
		</div>
		<div class="control-group">
			<label for="price" class="control-label">Selling Price</label>
			<div class="controls">
				<span class="input-medium uneditable-input">{{item.priceBean.sellingPrice}}</span>
				<input type="hidden" name="items[{{$index}}].priceBean.sellingPrice"
					ng-value="item.priceBean.sellingPrice"
					ng-model="item.priceBean.sellingPrice" />
			</div>
		</div>

		<div class="control-group">
			<label for="stock.quantity" class="control-label">Stock
				Quantity</label>
			<div class="controls">
				<span class="input-medium uneditable-input">{{item.stock.quantity}}</span>
				<input type="hidden" name="items[{{$index}}].stock.quantity"
					ng-model="item.stock.quantity" ng-value="item.stock.quantity" />
			</div>
		</div>

		<div class="control-group">
			<label for="sku" class="control-label">SKU or ProductID</label>
			<div class="controls">
				<span class="input-medium uneditable-input">{{item.sku}}</span> <input
					type="hidden" name="items[{{$index}}].sku" ng-model="item.sku"
					ng-value="item.sku" />
			</div>
		</div>

		<div class="control-group">
			<label for="weight" class="control-label"> Weight </label>
			<div class="input-append" style="padding-left: 20px;">
				<span class="input-medium uneditable-input">{{item.dimensionsAndWeight.weight}}</span>
				<input type="hidden"
					name="items[{{$index}}].dimensionsAndWeight.weight"
					ng-model="item.dimensionsAndWeight.weight"
					ng-value="item.dimensionsAndWeight.weight" />
			</div>
			<div class="input-append">
				<span class="input-medium uneditable-input">{{item.dimensionsAndWeight.length}}</span>
				<input type="hidden"
					name="items[{{$index}}].dimensionsAndWeight.length"
					ng-model="item.dimensionsAndWeight.length"
					ng-value="item.dimensionsAndWeight.length" />
			</div>
		</div>
		<div class="control-group">
			<label for="width" class="control-label"> Dimension</label>

			<div class="input-append" style="padding-left: 20px;">
				<span class="input-medium uneditable-input">{{item.dimensionsAndWeight.width}}</span>
				<input type="hidden"
					name="items[{{$index}}].dimensionsAndWeight.width"
					ng-model="item.dimensionsAndWeight.width"
					ng-value="item.dimensionsAndWeight.width" />
			</div>



			<div class="input-append">
				<span class="input-medium uneditable-input">{{item.dimensionsAndWeight.height}}</span>
				<input type="hidden"
					name="items[{{$index}}].dimensionsAndWeight.height"
					ng-model="item.dimensionsAndWeight.height"
					ng-value="item.dimensionsAndWeight.height" />
			</div>
		</div>
		<input type="button" ng-click="remove($index)" value="Remove Rows" />
		<a href="#myModal" class="btn btn-sucess" data-toggle="modal"
			ng-click="editItem($index)" role="button">Edit Data</a>
	</div>
</div>

