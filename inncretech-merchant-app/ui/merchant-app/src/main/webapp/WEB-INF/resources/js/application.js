var openUploadFileDialog = function(thisObj){
    var formParent = $(thisObj).parents("form");
    formParent.find("#uploadImageFileId").click();
} ;

var openUploadPictureDialog = function(){
    $("#uploadPictureFileId").click();
} ;


function loadImage(img, divId, divWidth, divHeight){
	$img = $(img);
	var src = $img.attr('src');
	var width = $img.width(), height = $img.height();
	console.log("div id  "+ divId);
	$img.parent().css({background: "url('" + src + "') 0 0 no-repeat"});
	$img.attr('src', null);
	return new ImageCrop(divId, width, height, divWidth, divHeight);
 }