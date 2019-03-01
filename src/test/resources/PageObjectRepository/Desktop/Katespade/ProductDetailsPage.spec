Page Title: KateSpade

#Object Definitions
====================================================================================
primaryImage												css							.primary-image
list_productThumbnails										css							.product-thumbnails-list .slick-active
productName													css							h1.product-name
icon_fullScreenPlus											xpath						(//img [contains(@src,'full-screen-plus')])[1]
icon_writeReview											css							#BVRRSummaryContainer #BVRRRatingSummaryLinkWriteID>a
icon_colorSwatches											css							.product-variations ul
icon_facebook												xpath						//a[@data-share='facebook']
icon_twitter												xpath						//a[@data-share='twitter']
icon_pinterest												xpath						//a[@data-share='pinterest']
label_size													xpath						(//div[@id='small-description']//ul)[1]
label_material												xpath						(//div[@id='small-description']//ul)[2]
label_style													xpath						(//div[@id='small-description']//ul)[3]
label_detail												xpath						(//div[@id='small-description']//ul)[3]
label_productDescription									css							div#small-details
label_breadcrum												css							.breadcrumb
label_productPrice											css							#product-content .product-price
swatch_color												css 						.swatches.color li:nth-child(${text}) a
button_addToBag												css							.add-to-cart
link_addToWishlist											xpath 						//div[@class='product-actions']/a[contains(@class,'wishlist')]
icon_firstColorSwatch										css							.swatches.color li:nth-child(1) a img
button_login												css							.login-box.login-account button[name='dwfrm_login_login']
label_recommendationHeader									css							.product-listing-1x4 h2 span
list_productRecommendationImage								css							.product-listing-1x4 li.slick-slide.slick-active .product-image a
list_productRecommendedTiles								css							.product-listing-1x4 li.slick-slide.slick-active
button_quickShop											css							a#quickviewbutton
text_firstRecommendedProductName							css							.einstain-inited .slick-current .product-name>a
label_productName											css							.product-col-1 h1
img_recommendedProduct										css							.einstain-inited .slick-current .product-image .front img
label_mostRecentlyViewedHeader								css							.product-listing.last-visited h2
list_mostRecentlyViewedItemsImage							css							.product-listing.last-visited .product-image .rollover
text_firstRecentlyViewedItemName							css							.last-visited .slick-current.slick-active .product-image+div a
img_recentlyViewedProduct									css							.product-image .thumb-link img
label_promotionalMessage									css							#product-content .promotional-message
list_sizeSwatch												css							.swatches.size li a
list_colorSwatch											css							.product-variations ul li a img
img_contentAssetWrapper										css							.content-asset-wrapper img[src*='productbottom.PNG']
label_color													css   						.attribute .attr-value
msg_inStock													css							.inventory .in-stock-msg
link_addAllToWishlist										css							.button-text.wishlist
text_productSet_salesPrice									css							.salesprice
list_productSetItems										css							.item-name
drpdwn_firstProductSetItemQty								xpath						(//div[@class='select-style'])[1]
label_productSet_firstName									xpath						(//a[@class='item-name'])[1]
label_productSet_inventoryMessage							xpath						(//p[@class='in-stock-msg'])[1]
label_buyOrNone												css							.product-cta
button_addAllToBag											css							#add-all-to-cart
label_productSetPrice										css							.salesprice
list_productPrice_productSet								css							.price-sales
list_producrSetImageThumbnails								css							.product-thumbnails a
drpdwn_qtySelected											xpath						(//option[@selected='selected'])[1]
label_share                                                 css                         .socialsharing
thumbnailSlider												css							.product-thumbnails .slick-slider
btn_prdctSetAddToCart										css							.add-to-cart
icon_close													css							.ui-icon-closethick	
try_new_search								                css		        			[for='generalerror-search']
link_sizeChart												css							.size-chart-link a
img_sizeGuide												css							img[src*=sizeguide]
errorPageMsg												css							.error-page-message
txtbx_genErrorSearch										css							#generalerror-search
errPg_contentAsset											css							.error-page-footer .content-asset
miniCartProdName											css							.mini-cart-name a
header_miniCart												css							.mini-cart-header
prdctNameShpngPage											css							.cart-row .product-list-item a
link_breadcrumbLevels										css							.breadcrumb li a
zoomImg														css							.zoomImg
link_socialSharing											css							.socialsharing a
link_thmbnailSelected										css							.thumb.selected .productthumbnail
salesPrice_recommendedItem									css							.slick-active.recommendation-item .price-sales
list_frontFaceImg											css							.front.face img
list_backFaceImg											css							.back.face img
altImgSelected												css							.thumb.selected
inventoryMsg												css							.inventory .availability-msg p
span_productPrice											css							.product-detail .price-sales
lnk_nonSelectedSwatchColor									xpath						//ul[@class='swatches color']//li[@class='selectable']/a
div_maximumLimitPurchase									css							.purchase-limit
dropdown_qty												css							#Quantity
section_UGC                                                 css                         div#ugcContainer
section_Review                                              css                         div#BVRRContainer
button_ViewGallery                                          css                         div#ugcButtonContainer #ugcViewGallery a
button_SubmitPhoto                                          css                         div#ugcButtonContainer #ugcSubmitPhoto a
lnk_wishList												css							.user-info.wishlist
