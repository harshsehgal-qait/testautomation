Page Title: KateSpade

#Object Definitions
====================================================================================
list_address							css						ul.address-list
tile_productTile						css						.thumb-link img 
icon_quickView							css						#quickviewbutton
breadCrum								css						.breadcrumb
lbl_productPrice						css						.product-price
breadCrumHeader							css						#main>h1
quickViewWindow							css						.quickview-dialog
icon_zoom								css						.product-image-container #full-screen-button>a
lbl_productName							css					    #pdpMain .product-name
quickViewProductPrice					css						#pdpMain .product-price
icon_review							    css						#BVRRRatingOverall_Rating_Summary_1
btn_addToCart							css						#add-to-cart
link_seeDetails							css						.product-view-details a
img_quickBuyAlt							css						#pdpMain .thumb:nth-child(${text}) img
icon_swatches							css						.swatch-list .product-swatches-all
icon_MoreSwatches						xpath					//li[@class='grid-tile '][1]//a[contains(text(),'+')]
header_ResultsFor                       css                    #main h1
category_search                         css                    .hit-underline
h2_refinementCategory					css					   .refinement.Category>h2
textbox_Search							css 	       		   .search-placeholder-wrapper .katesearchval
text_search								css						li.search-menu
drpdn_Filter							css						#show-filter
drpdn_hide_filter                       css                     #hide-filter
drpdn_gridSortHeader					css						#grid-sort-header
sortngOptions							css						#grid-sort-header option
frstRefinmntColor						css						.refinementColor li:nth-child(1) a
subCatWithNoSubLink						css						.refinement.Category h3+ul li.no-sub a
subCatWithSubLink						xpath					//ul[@id='category-level-1']//i/../a
frstPlusIcon							xpath					(//i[@class='fa fa-plus toggle'])[1]
list_subSubCatgry						css						.expanded+ul li
firstMinusIcon							css						.expanded .fa-minus
activeSubCatLink						css						.refinement-link.active
expandedSubCatgry						css						.category-match li
refinemrntOption						css						.search-refinements-section-inner h3
list_prodName							css						.product-name a
btn_loadMore							css						.load-more-wrapper span
list_prodctImg							css						.product-image .front.face img
icon_close								css						.ui-icon-closethick
text_promotionalMsg						xpath					(//div[@class='promotional-message'][contains(text(),'${text}')])[1]
link_allPrdctSwatch						xpath					(//ul[@class='swatch-list'])[${swatchIndex}]//li[@class='product-swatches-all']/a
list_allPrdctSwatch						xpath					(//ul[@class='swatch-list'])[${swatchIndex}]//li[@class='product-swatches-all']
list_swatch								xpath					(//ul[@class='swatch-list'])[${swatchIndex}]//li								
link_allPrdctSwatch						css						.grid-tile:nth-child(${index}) .swatch-list li.product-swatches-all a
list_allPrdctSwatch						css						.grid-tile:nth-child(${index}) .swatch-list li 
list_swatch								css						.grid-tile:nth-child(${index}) .swatch-list li a								
list_frontFaceImg						css						.front.face img
list_backFaceImg						css						.back.face img	
list_productStPrice						css						.product-set-price
searched_header                         css                     .cat-name-title
filter_options                          css                     .refinement-header
write_first_review						id						BVRRRatingSummaryLinkWriteFirstID
write_review                           	id                      BVRRRatingSummaryLinkWriteID
icon_ratings							css						.BVBrowserWebkit
frstRefinmntSize						css						.size li:nth-child(1) a
searchfor                               css                     .search-phrase a




