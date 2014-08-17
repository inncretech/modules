<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="f" uri="http://www.inncretech.com/functions"%>
<html lang="en" ng-app="productApp">
<jsp:include page="header.jsp" />
<script type="text/javascript">
var product=${f:convertToJson(productBean)};
var countryMap =${f:convertToJson(dropDownBean.countryMap)};
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
                              <input type="text" name="title" ng-model="product.title" placeholder="Enter Product Title" required="true"
                                   autocomplete="off" title="Please Enter Product Title" cssClass="input-xlarge"
                              />
                         </div>
                    </div>
                    <div class="control-group">
                         <label for="description" class="control-label">Product Description</label>
                         <div class="controls">
                              <textarea rows="3" cols="10" name="description" placeholder="Enter Description" required="true" autocomplete="off"
                                   title="Please Enter Description" cssClass="span3" ng-model="product.description"
                              ></textarea>
                         </div>
                    </div>
                    <div class="control-group">
                         <label class="control-label" for="startDateFrom">Start Date(From/To): </label>
                         <div class="input-append date" id="dp3" data-date-format="dd-mm-yyyy" style="padding-left: 20px;">
                              <input type="text" name="startDate" class="span2" placeholder="dd-mm-yyyy" readonly="true" ng-model="product.startDate" />
                              <span class="add-on"><i class="icon-calendar"></i></span>
                         </div>
                         <div class="input-append date" id="dp13" data-date-format="dd-mm-yyyy" style="padding-left: 20px;">
                              <input type="text" name="endDate" class="span2" placeholder="dd-mm-yyyy" readonly="true" ng-model="product.endDate" />
                              <span class="add-on"><i class="icon-calendar"></i></span>
                         </div>
                    </div>
                    <div class="control-group">
                         <label for="categoryIds" class="control-label">Category Ids :</label>
                         <div class="controls">
                              <div id="cateogryDropDown"></div>
                              <div id="cateogry"></div>
                              <div>
                                   <select name="categoryIds" id="categoryIds" style="display: none;" multiple="multiple"></select>
                              </div>
                         </div>
                    </div>
                    <div class="control-group">
                         <label class="control-label" for="originCountry">Origin Country <span style="color: #FF0000;">*</span>:
                         </label>
                         <div class="controls">
                              <select ng-model="product.originCountry.countryId" name="originCountry.countryId"
                                   ng-Options="id as value for (id,value) in countryMap" required="true" title="Please Select Origin Country"
                              >
                                   <option value="">---Select Origin Country ----</option>
                              </select>
                         </div>
                    </div>
                    <div class="control-group">
                         <label class="control-label" for="images">Add Images <span style="color: #FF0000;">*</span>:
                         </label>
                         <div ng-controller="ImageUploadController" class="controls" style="width: 450px;">
                              <div ng-show="true" id="imgDiv">
                                   <div class="row row-choise text-center" style="margin-bottom: 15px;">
                                        <div id="div{{$index}}" ng-repeat="image in imagePreviewData" class="col-md-4 text-center"
                                             style="height: 125px"
                                        >
                                             <div style="border: 1px solid rgba(0, 0, 0, .1); height: 125px; overflow: hidden">
                                                  <span class="" ng-click="removeFromTemplate(image)"
                                                       style="position: absolute; right: 20px; color: red; font-size: 21px; font-weight: bold; line-height: 1; cursor: pointer;"
                                                  >&times;</span> <img ng-src="{{image.imageUrl}}" style="width: 120%;" index="{{$index}}" />
                                             </div>
                                        </div>
                                        <div class="col-md-4 text-center" ng-repeat="q in imageArray" style="height: 125px;">
                                             <div style="height: 100%; position: relative; overflow: hidden;">
                                                  <img ng-src="{{q.imageUrl}}" style="width: 120%; opacity: 0.5" />
                                                  <div class="spinner"></div>
                                             </div>
                                        </div>
                                        <div class="col-md-4" ng-hide="imagePreviewData.length>=3 || imagePreviewData.length+queue.length >=3">
                                             <button class="btn btn-add-img btn-lg btn-block" onclick="openUploadFileDialog(this)"
                                                  title="Choose a file to upload" alt="Choose a file to upload" ng-disabled="imageLoading"
                                             >
                                                  <i class="glyphicon glyphicon-plus"></i>
                                             </button>
                                        </div>
                                   </div>
                              </div>
                              <input type="file" style="display: none;" id="uploadImageFileId"
                                   onchange="angular.element(this).scope().uploadCreateImage(this)"
                              />
                         </div>
                    </div>
                    <c:import url="item.jsp" />
                    <a href="#myModal" class="btn btn-sucess" data-toggle="modal" role="button">Add More Items</a>
                    <div class="control-group">
                         <div class="controls">
                              <button type="submit" class="btn btn-primary" id="select_all" ng-click="validateProductForm()">Submit</button>
                         </div>
                    </div>
               </fieldset>
          </form:form>
          <c:import url="addItem.jsp" />
     </div>
     <script type="text/javascript">
		$(document).ready(function() {
							getCategoryData(0);
							$('#dp3,#dp13').datepicker();
							populateSelectedCategoryonRefresh(<c:out value="${productBean.categoryIds}"/>);
						});

	   
	   
	    
		
	</script>
</body>
</html>