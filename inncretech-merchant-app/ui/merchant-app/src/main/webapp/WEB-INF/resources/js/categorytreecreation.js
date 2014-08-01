/**
 * 
 */

function getCategoryData(categoryId) {
	$.ajax({
		type : "GET",
		url : "/getCategoriesByCategoryId/" + categoryId,
		success : function(response) {
			if (!$.isEmptyObject(response.categoryMap)) {
				var s = createBlankDropDown(response.level);
				for ( var val in response.categoryMap) {
					$('<option />', {
						value : val,
						text : response.categoryMap[val]
					}).appendTo(s);
				}
				s.appendTo('#cateogryDropDown');
			} else {
				$("#cateogryDropDown").append(
						"<input type='button'  id='addCategoryTemp' onclick=addCategoryDiv("
								+ categoryId + "," + response.level
								+ ") class='icon-ok'></input>");
			}
		}
	});

}

function getBreadCrumbByCategoryId(categoryId) {
	$.ajax({
		type : "GET",
		url : "/getBreadCrumbByCategoryId/" + categoryId,
		success : function(response) {
			if (!$.isEmptyObject(response)) {
				$(response).each(function(index) {
					console.log(response[index].categoryName);
				});
			}
		}
	});

}
function createBlankDropDown(level) {
	var s = $('<select id="category' + level + '" onChange="addCategory('
			+ level + ')"/>');
	$('<option />', {
		value : '',
		text : '---select category--'
	}).appendTo(s);
	return s;
}
function addCategory(index) {
	deleteExtraDropDowns(index);
	$("#category" + index + " option:selected").each(function() {
		getCategoryData($(this).val());
	});
}
function addCategoryDataDiv(categoryId, level, flag) {
	var message = '';
	for (var i = 0; i < level; i++) {
		$("#category" + i + " option:selected").each(function() {
			message = message + $(this).text();
			if (i != level - 1) {
				message = message + ">";
			}
		});

	}
	createBreadCrumbString(categoryId, flag, message)
}

function createBreadCrumbString(categoryId, flag, message) {
	if (flag && $('#cateogry' + categoryId).length == 0) {
		$('#cateogry').append(
				"<div id='cateogry" + categoryId + "'>" + message + "</div>");
	} else if ($('#cateogry').length == 0
			&& $('#cateogry' + categoryId).length == 0) {
		$('#cateogry').html(
				"<div id='cateogry" + categoryId + "'>" + message + "</div>");
	}
}
function addCategoryDiv(categoryId, level) {
	if ($('#cateogry' + categoryId).length == 0) {
		addCategoryDataDiv(categoryId, level, true);
	}
	addRemoveCategoryButtonByLeafCategoryId(categoryId);
}

function populateSelectedCategoryonRefresh(selectedCategories) {
	if (selectedCategories != null) {
		$(selectedCategories).each(function(index) {
			getBreadCrumbByCategoryId(selectedCategories[index]);
		});
	}
}
var responseTemp = null;
function getBreadCrumbByCategoryId(categoryId) {
	$.ajax({
		type : "GET",
		url : "/getBreadCrumbByCategoryId/" + categoryId,
		success : function(response) {
			var message = '';
			if (!$.isEmptyObject(response)) {
				responseTemp = response;
				$(response).each(function(index) {
					message = message + response[index].categoryName;
					if (index != response.length - 1) {
						message = message + ">";
					}
				});
			}
			createBreadCrumbString(categoryId, true, message);
			addRemoveCategoryButtonByLeafCategoryId(categoryId);
		}
	});

}

function addRemoveCategoryButtonByLeafCategoryId(categoryId) {
	if ($('#removeCategory' + categoryId).length == 0) {
		$('#cateogry' + categoryId).append(
				"<input type='button' id='removeCategory" + categoryId
						+ "' onclick=removeCategoryDiv(" + categoryId
						+ ") class='icon-trash'></input>");
		$('<option />', {
			value : categoryId,
			text : categoryId
		}).appendTo($("#categoryIds"));
		$('#categoryIds option').prop('selected', 'selected');
	}
}
function removeCategoryDiv(categoryId) {
	$('#cateogry' + categoryId).remove();
	$("#categoryIds option[value='" + categoryId + "']").remove();
}
function deleteExtraDropDowns(index) {
	var tempIndex = index + 1;
	while (tempIndex != 0) {
		if ($('#category' + (tempIndex)).length > 0) {
			$('#category' + (tempIndex)).remove();
			tempIndex++;
		} else {
			tempIndex = 0;
		}

	}
	$('#addCategoryTemp').remove();

}