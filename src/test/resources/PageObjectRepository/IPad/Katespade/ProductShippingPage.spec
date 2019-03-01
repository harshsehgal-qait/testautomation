Page Title: KateSpade

#Object Definitions
====================================================================================
input_guestEmail							css						#dwfrm_singleshipping_shippingAddress_email_emailAddress
input_fname									css						#dwfrm_singleshipping_shippingAddress_addressFields_firstName 
input_lname									css						#dwfrm_singleshipping_shippingAddress_addressFields_lastName
input_pcode									css						#dwfrm_singleshipping_shippingAddress_addressFields_postal
input_phone									css						#dwfrm_singleshipping_shippingAddress_addressFields_phone
chck_billingAddress							css						[for=dwfrm_singleshipping_shippingAddress_useAsBillingAddress] span
input_address								css						#dwfrm_singleshipping_shippingAddress_addressFields_address1
input_city									css						#dwfrm_singleshipping_shippingAddress_addressFields_city
radio_shippingMethod						name					dwfrm_singleshipping_shippingAddress_shippingMethodID
btn_continuePayment							css						[name="dwfrm_singleshipping_shippingAddress_save"]
link_enterManually							css						.manual-postal-code>a                                     
shipping_page                       		css                     .shipping-form              
btn_regstrdUserSignInNow					css						#shipping-login h2
txtbox_emailAddress							css						#dwfrm_login_username
txtbox_password								css						#dwfrm_login_password
btn_SignIn									css						[name='dwfrm_login_login']
link_forgotPassword							css						#password-reset
drpdown_savedAddress						css						#dwfrm_singleshipping_addressList
select_savedAddress							css						#dwfrm_singleshipping_addressList option
frst_savedAddress							css						#dwfrm_singleshipping_addressList option:nth-child(2)
errorCountryTxtbox							css						.country.required.error
lbl_stepNo									css						.step-1.active .step-number
lbl_stepFooter								css						.step-1.active .step-footer
list_fieldsError							css						.shipping-form span.error
radioBtn_standardShpng						css						[for='shipping-method-ground']
radioBtn_expressShpng						css						[for='shipping-method-2day']
list_shpngMethodRadioBtns					css						#shipping-method-list .form-row
lbl_shpngMethodOrderSummary					css						.order-shipping
btn_disabledContinueToPayment				xpath					//button[@name='dwfrm_singleshipping_shippingAddress_save'][@disabled]
minicartPlusIcon                            css                     .checkout-mini-cart a:nth-child(1)
prdct_img                                   css                     .toggle-content img
prdct_name                                  css                     .toggle-content .mini-cart-name
prdct_sku                                   css                     .toggle-content .sku .value
prdct_price                                 css                     .toggle-content .visually-hidden
prdct_qty                                   css                     .toggle-content .mini-cart-pricing>span
myBag_header                                css                     .section-header.toggle.expanded
firstProduct_style                          xpath                   (//div[@class='sku']//span[@class='value'])[4]
secondProduct_style                         xpath                   (//div[@class='sku']//span[@class='value'])[5]
thirdProduct_style                          xpath                   (//div[@class='sku']//span[@class='value'])[6]
checkbox_addtoaddressbook                   css                     [for="dwfrm_singleshipping_shippingAddress_addToAddressBook"]
country_dropdown                            css                     #dwfrm_singleshipping_shippingAddress_addressFields_country option
label_giftMessageandGiftBox                 css                     .edit-all-gifts .section-header 
label_yesShowMeoptionGiftMsgGiftBox         css                     [for="shipping_show_gift_options"] .text-input-label
giftBox_model                               css                     .gift-options-container
header_giftOption                           css                     #edit-giftoptions-form h2
label_gifyOption                            css                     #edit-giftoptions-form h6
gift_productImg                             css                     .item-list .item-image img
gift_productName                            css                     .product-list-item .name
gift_prdctAttributes                        css                     .product-list-item .attribute span
freeGiftCardMessage                         css                     [for="dwfrm_multigift_giftOptions_productLineItems_i0_giftoptions_hasNote"] .text-input-label
includeGiftWrapping                         css                     [for="dwfrm_multigift_giftOptions_productLineItems_i0_giftoptions_hasGiftWrap"] .text-input-label
hideAllPriceOnPackingSlip                   css                     [for="dwfrm_multigift_includeGiftReceipt"]
freegift_container                          css                     .textareacontainer .gift-message-chars
country_toggle        			            css 		            #site-tab-bar a
lbl_subTotal								css						.order-subtotal>td:nth-child(2)
txt_coupnSuccessMsg							css						.cart-coupon 
lbl_total									css						.order-value
summaryBox									css						.checkout-order-totals
shippingOrderDiscount						css						.order-shipping-discount
myBagSectionHeader							css						.section-header.toggle
dropDown_Country                        	css                     select.input-select
dropDown_Country_option                 	css                     select.input-select option
label_ShippingMethod                    	css                     .shipping-name
select_Country                          	xpath                   //select[contains(@class,"country")]//option[@class="select-option" and text()="${name}"]
label_chooseShipngMethod                    css                    	fieldset legend
textbox_FindAddress                         css                     #dwfrm_addressy_addressFind
textbox_Company                             css                     #dwfrm_singleshipping_shippingAddress_addressFields_companyName
input_NewFindAdd_Relative_To_Country        xpath                   //select[@id='dwfrm_singleshipping_shippingAddress_addressFields_country']/../../../following-sibling::div[1]//input[@id='dwfrm_addressy_addressFind']
textbox_FindAddress                         css                     #dwfrm_addressy_addressFind
lnk_searchAgain							    css						.search-again-postal-code a
dropdown_AddressSuggest                     xpath                   //div[contains(@class,'pcaautocomplete pcatext') and not(contains(@class,'pcacountrylist'))]
dropdown_ListOfAddress                      xpath                   //div[contains(@class,'pcaautocomplete pcatext') and not(contains(@class,'pcacountrylist'))]//div[contains(@class,'pcaitem')]
msg_errorFindAddressReq                     css                     .empty-address-postal-code
lnk_enterManually						    css						.manual-postal-code a
msg_errorFindAddressReq                     css                     .empty-address-postal-code
input_postalCodeLookUp						css						#dwfrm_addressy_addressFind
div_pcaAutoComplete							css						.pcaautocomplete.pcatext[style$='px;']
