package com.inncretech.merchant.constants;

public interface UrlConstants {
	String PRODUCT_ID = "{productId}";
	String ADD_PRODUCT_PAGE = "/addProduct";
	String EDIt_PRODUCT_PAGE = "/editProduct/" + PRODUCT_ID;
	String PRODUCT_SUCCESS_PAGE = "/productInformation";
	String HOME_PAGE_URL = "/homePage";
	String REDIRECT_HOME_PAGE_URL = "redirect:/homePage";
	String ROOT_URL = "/";
	String HEADER_URL = "/header";
	String FOOTER_URL = "/footer";
	String TOP_NAVIGATION_URL = "/topNav";
	String CATEGORY_ID = "{categoryId}";
	String CHILD_CATEGORIES_BY_CATEGORY_ID = "/getCategoriesByCategoryId/" + CATEGORY_ID;
	String LIST_PRODUCTS_PAGE = "/listProducts";
	String BREAD_CRUMB_BY_CATEGORY_ID = "/getBreadCrumbByCategoryId/" + CATEGORY_ID;
	String UPLOAD_IMAGE = "/uploadImage";
}
