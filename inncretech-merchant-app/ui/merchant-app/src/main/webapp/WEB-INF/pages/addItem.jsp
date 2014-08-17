<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <form id="itemBean">
          <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true" ng-click="clearErrorMessage()">×</button>
               <h3 id="myModalLabel">Add Items</h3>
          </div>
          <div class="modal-body">
               <%@include file="messages.jsp"%>
               <div id="rr">
                    <div class="control-group">
                         <label for="item.title" class="control-label">Item Title</label>
                         <div class="controls">
                              <input type="text" placeholder="Enter Product Title" required="true" autocomplete="off"
                                   title="Please Enter Product Title" cssClass="input-xlarge" name="item.title" ng-model="item.title"
                              /> <input type="hidden" name="item.itemId" ng-model="item.itemId" ng-value="item.itemId" />
                         </div>
                    </div>
                    <div class="control-group">
                         <label for="item.priceBean.mrp" class="control-label">MRP</label>
                         <div class="controls">
                              <input type="text" name="item.priceBean.mrp" placeholder="Enter Retail Price" required="true" autocomplete="off"
                                   title="Please Enter Retail Price" cssClass="span2" type="number" min="0" max="1000000"
                                   ng-model="item.priceBean.mrp"
                              />
                         </div>
                    </div>
                    <div class="control-group">
                         <label for="item.priceBean.sellingPrice" class="control-label">Selling Price</label>
                         <div class="controls">
                              <input type="text" name="item.priceBean.sellingPrice" placeholder="Enter Selling Price" required="true"
                                   autocomplete="off" title="Please Enter Selling Price" cssClass="span2" type="number" min="1" max="1000000"
                                   ng-model="item.priceBean.sellingPrice"
                              />
                         </div>
                    </div>
                    <div class="control-group">
                         <label for="item.stock.quantity" class="control-label">Stock Quantity</label>
                         <div class="controls">
                              <input type="text" name="item.stock.quantity" placeholder="Product Quantity" required="true" autocomplete="off"
                                   title="Please Enter Quantity" cssClass="span2" type="number" min="1" max="10000" ng-model="item.stock.quantity"
                              />
                         </div>
                    </div>
                    <div class="control-group">
                         <label for="item.sku" class="control-label">SKU or ProductID</label>
                         <div class="controls">
                              <input type="text" name="item.sku" placeholder="Enter SKU" required="true" autocomplete="off" title="Please Enter SKU"
                                   cssClass="span2" ng-model="item.sku"
                              />
                         </div>
                    </div>
                    <div class="control-group">
                         <label for="item.dimensionsAndWeight.weight" class="control-label"> Weight </label>
                         <div class="controls">
                              <input type="text" name="items[{{$index}}].dimensionsAndWeight.weight" placeholder="Enter Weight" required="true"
                                   autocomplete="off" title="Please Enter Product Weight" cssClass="input-small" type="number" min="0" max="10000"
                                   ng-model="item.dimensionsAndWeight.weight"
                              />
                         </div>
                    </div>
                    <div class="control-group">
                         <label for="item.dimensionsAndWeight.length" class="control-label"> Length </label>
                         <div class="controls">
                              <input type="text" name="item.dimensionsAndWeight.length" placeholder="Enter Length" required="true" autocomplete="off"
                                   title="Please Enter Product Length" cssClass="input-small" type="number" min="0" max="10000"
                                   ng-model="item.dimensionsAndWeight.length"
                              />
                         </div>
                    </div>
                    <div class="control-group">
                         <label for="item.dimensionsAndWeight.width" class="control-label"> Dimension</label>
                         <div class="controls">
                              <input type="text" name="item.dimensionsAndWeight.width" placeholder="Enter Dimension" required="true"
                                   autocomplete="off" title="Please Enter Product Dimension" cssClass="input-small" type="number" min="0" max="10000"
                                   ng-model="item.dimensionsAndWeight.width"
                              />
                         </div>
                    </div>
                    <div class="control-group">
                         <label for="item.dimensionsAndWeight.height" class="control-label"> Dimension</label>
                         <div class="controls">
                              <input type="text" name="item.dimensionsAndWeight.height" placeholder="Enter Height" required="true" autocomplete="off"
                                   title="Please Enter Product Height" cssClass="input-small" type="number" min="0" max="10000"
                                   ng-model="item.dimensionsAndWeight.height"
                              />
                         </div>
                    </div>
               </div>
          </div>
          <div class="modal-footer">
               <input type="button" class="btn btn-primary" id="addItem"  ng-click="addItem(item)" value="Add Item" aria-hidden="true" /> <input
                    type="button" class="btn" data-dismiss="modal" id="cancel" value="cancel"
               />
          </div>
     </form>
</div>
