Page Title: KateSpade

#Object Definitions
====================================================================================
link_addToWishlist          		    css     				    .button-text.wishlist
wishlist_page							css							#primary h1
Product_Name							xpath						(//div[@class='name '])[${text}]	
sku_value								xpath						(//div[@class='product-list-item']//div[@class='sku']//span[@class='value'])[${text}]
item_quantity          					css     				    .option-add-to-cart select:nth-child(${text})
add_to_bag								xpath						//button[@name='dwfrm_wishlist_items_i0_addToCart']
product_details							css							.item-details .product-list-item div:nth-child(${text})
item_image                              css							.item-image
edit_button								css							.button.edit-button
remove_button							css							.remove-button.desktop-remove:nth-child(${text})
side_nav								css							.content-asset li:nth-child(${text})
button_CreateAnAccount					xpath				    //button[@name='dwfrm_login_register']
textbox_FirstName					 	xpath				    //input[@id='dwfrm_profile_customer_firstname']
textbox_LastName						xpath				    //input[@id='dwfrm_profile_customer_lastname']
textbox_eMailAddress					xpath				    //input[@id='dwfrm_profile_customer_email']
textbox_confirmEmail        			xpath 				    //input[@id='dwfrm_profile_customer_emailconfirm']
textbox_password						xpath				    //input[@id='dwfrm_profile_login_password']
textbox_confirmPassword					xpath				    //input[@id='dwfrm_profile_login_passwordconfirm']
button_submit							css						#RegistrationForm button.secondary
header_Hi								css	 				   	.user-links.has-icon.signin
wishlist_signin							css						.login-box.login-account
label_wishlist							css					    .wishlist-content
button_first_addTobag					xpath				    //button[@name='dwfrm_wishlist_items_i0_addToCart']							
list_products							css						.item-details .name
label_date								css						.option-date-added .field-wrapper>div
pdp_page								id						 pdpMain
close_pdp								css						 .ui-icon-closethick
inventory								css						 .product-availability-list
minicart_quantity						css						 .mini-cart-link .minicart-quantity
selectQuantity							css						 .quantity-select
page_header								css						 #primary h1
contentAsset_createBenifits				css						.createbenefits
contentAsset_accNavAsset				css						.account-nav-asset	
inventoryMsgInStock						css						.is-in-stock
frstbtn_edit							xpath					(//a[@class='button edit-button'])[1]
frst_qtyDrpdn							xpath					(//select[contains(@class,'quantity-select')])[1]							
frst_selectQtyDrpdn						xpath					(//select[contains(@class,'quantity-select')]//option[@selected])[1]
selectQtyDrpdn							xpath					(//div[contains(@class,'select-style')])[1]//option
contentAsset_accNavText					css						.account-nav-asset
lnk_myAccount							css						.my-account a[href*='account']
lnk_myInfo								css						#edit-account a[href*='profile']
lnk_addressBook							css						#edit-address a[href*='addressbook']
lnk_creditCards							css						#edit-cards a[href*='wallet']
lnk_orderHistory						css						#order a[href*='orders']
lnk_wishlist							css						#wishlist a[href*='wishlist']
lnk_shopConfidently						css						a[href*='secureshoppingguarantee']
label_emptyWishlist						css						#primary h2
chkbx_privacyOptIn						css						[for='privacy-opt-in']
wishlistBanner							css					    .wishlist-content
