	 window.ImageCrop = function(divId, width, height, divWidth, divHeight) {
	    var dataContainer = {};
		dataContainer.currentBgWidth=width;
		dataContainer.currentBgHeight = height;
		var origWidth = width, origHeight = height;
		var scale = 1;
		dataContainer.currentBgX =0, dataContainer.currentBgY =0;
		var lastDragDeltaX =0, lastDragDeltaY =0;
		var e2 = document.getElementById(divId);
		 Hammer(e2).on("doubletap", function(event) {
			fitToScreen();
		 });
		 Hammer(e2, {drag_block_horizontal: !0,drag_block_vertical: !0,prevent_default: !0}).on("drag", function(event) {
			event.gesture.preventDefault();
			var deltaX = event.gesture.deltaX - lastDragDeltaX, deltaY = event.gesture.deltaY - lastDragDeltaY;
			lastDragDeltaX = event.gesture.deltaX, lastDragDeltaY = event.gesture.deltaY, dataContainer.currentBgX += deltaX, dataContainer.currentBgY += deltaY;
			updateBgStyle(e2, dataContainer.currentBgWidth, dataContainer.currentBgHeight, dataContainer.currentBgX, dataContainer.currentBgY);
		 });
		 var lastDragDeltaX = 0, lastDragDeltaY = 0;
		 var pinchInitBgWidth, pinchInitBgHeight;
		 Hammer(e2).on("pinch", function(event) {
			event.gesture.preventDefault(), pinchInitBgWidth || (pinchInitBgWidth = dataContainer.currentBgWidth, pinchInitBgHeight = dataContainer.currentBgHeight);
			dataContainer.currentBgWidth = pinchInitBgWidth * event.gesture.scale, dataContainer.currentBgHeight = pinchInitBgHeight * event.gesture.scale;
			updateBgStyle(e2, dataContainer.currentBgWidth, dataContainer.currentBgHeight, dataContainer.currentBgX, dataContainer.currentBgY);
		 }), Hammer(e2).on("release", function() {
			lastDragDeltaX = lastDragDeltaY = 0, pinchInitBgWidth = pinchInitBgHeight = 0
		 });
		 
		 var wheel = wheelType();
		 e2[wheel] = wheelUpdate;
		 function wheelUpdate(e) {
			var deltaY = 0;
			var zoom = 0.02;
            e.preventDefault(), e.deltaY ? deltaY = e.deltaY : e.wheelDelta && (deltaY = -e.wheelDelta);
            var offsetX = e.pageX - e2.offsetLeft, offsetY = e.pageY - e2.offsetTop, 
			bgCursorX = offsetX - dataContainer.currentBgX, bgCursorY = offsetY - dataContainer.currentBgY, bgRatioX = bgCursorX / dataContainer.currentBgWidth, bgRatioY = bgCursorY / dataContainer.currentBgHeight, 
			bgWidthNew = dataContainer.currentBgWidth, bgHeightNew = dataContainer.currentBgHeight, bgPosXNew = dataContainer.currentBgX, bgPosYNew = dataContainer.currentBgY;
            if (0 > deltaY ? (bgWidthNew += bgWidthNew * zoom, bgHeightNew += bgHeightNew * zoom) : (bgWidthNew -= bgWidthNew * zoom, bgHeightNew -= bgHeightNew * zoom),
			bgPosXNew = offsetX - bgWidthNew * bgRatioX, bgPosYNew = offsetY - bgHeightNew * bgRatioY, 
			deltaY > 0) {
                var currentReduction = Math.max(bgWidthNew / origWidth, bgHeightNew / origHeight);
                if (currentReduction <= 0.3)
                    return
            }
            dataContainer.currentBgWidth = bgWidthNew, dataContainer.currentBgHeight = bgHeightNew, dataContainer.currentBgX = bgPosXNew, dataContainer.currentBgY = bgPosYNew;
			updateBgStyle(e2, dataContainer.currentBgWidth, dataContainer.currentBgHeight, dataContainer.currentBgX, dataContainer.currentBgY);
		}
		function fitToScreen(){
			if(origWidth > origHeight){
				dataContainer.currentBgHeight = divHeight;
				dataContainer.currentBgWidth = divHeight / origHeight * origWidth;
			}
			else{
				dataContainer.currentBgHeight = divWidth / origWidth * origHeight;
				dataContainer.currentBgWidth = divWidth;
			}
			if(divWidth > dataContainer.currentBgWidth){
				dataContainer.currentBgHeight = divWidth / dataContainer.currentBgWidth * dataContainer.currentBgHeight; dataContainer.currentBgWidth = divWidth;
			}
			if(divHeight > dataContainer.currentBgHeight){
				dataContainer.currentBgWidth = divHeight / dataContainer.currentBgHeight * dataContainer.currentBgWidth; dataContainer.currentBgHeight = divHeight;
			}
			dataContainer.currentBgX = (divWidth - dataContainer.currentBgWidth) / 2, dataContainer.currentBgY = (divHeight - dataContainer.currentBgHeight) / 2;
			updateBgStyle(e2, dataContainer.currentBgWidth, dataContainer.currentBgHeight, dataContainer.currentBgX, dataContainer.currentBgY );
		}
		function updateBgStyle(img, bgWidth, bgHeight, bgPosX, bgPosY) {
			var w = parseInt(bgWidth), h = parseInt(bgHeight), x = parseInt(bgPosX), y = parseInt(bgPosY);
			img.style.backgroundSize = w + "px " + h + "px", img.style.backgroundPosition = x + "px " + y + "px";
			dataContainer.currentBgWidth = bgWidth, dataContainer.currentBgHeight = bgHeight, dataContainer.currentBgX = bgPosX, dataContainer.currentBgY = bgPosY;	
			console.log("bgwidth "+bgWidth +" bgHeight "+ bgHeight+ " bgX "+ bgPosX + " bgY " + bgPosY);
		}
		fitToScreen();
		return dataContainer;
	 }
