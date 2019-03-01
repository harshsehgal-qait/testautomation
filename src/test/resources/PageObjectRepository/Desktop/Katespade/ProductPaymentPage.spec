Page Title: KateSpade

#Object Definitions
====================================================================================
card_no										css					#creditCard_number
expiryDate									css					#adyen_creditCard_expDate
card_cvv									css					#creditCard_cvn
creditCardOwner								css					#creditCard_owner
lbl_billing									css					.step-2.active
radioCreditCard								css					[for="is-CREDIT_CARD"]
btn_placeOrder								css					#billing-submit
chk_sameAsShipping							css					[for=dwfrm_billing_billingAddress_sameAsShipping] span
lbl_stepNo									css					.step-2.active .step-number
input_fname									css					#dwfrm_billing_billingAddress_addressFields_firstName
input_lname									css					#dwfrm_billing_billingAddress_addressFields_lastName
input_postalCode							css					#dwfrm_billing_billingAddress_addressFields_postal
input_phoneNo								css					#dwfrm_billing_billingAddress_addressFields_phone
drpdwn_country								css					#dwfrm_billing_billingAddress_addressFields_country
btn_disabledPalceOrder						css					#billing-submit[disabled]
link_enterManually							css					.manual-postal-code>a	
input_address								css					#dwfrm_billing_billingAddress_addressFields_address1
input_city									css					#dwfrm_billing_billingAddress_addressFields_city
input_errorFname							css					#dwfrm_billing_billingAddress_addressFields_firstName-error
input_errorLname							css					#dwfrm_billing_billingAddress_addressFields_lastName-error
input_errorPostalCode						css					#dwfrm_billing_billingAddress_addressFields_postal-error
input_errorPhoneNo							css					#dwfrm_billing_billingAddress_addressFields_phone-error
drpDwn_savedCards							css					#adyenCreditCardList
select_savedCard							css					#adyenCreditCardList option
lbl_error									css					span.error
chk_addToAddressBook						css					[for='dwfrm_billing_billingAddress_addToAddressBook'] span
startDate									css					#adyen_creditCard_startDate
issueNumber									css					#adyen_creditCard_issueNumber
creditCardOwnerError						css					#creditCard_owner-error
cardNumberError								css					#creditCard_number-error
expiryDateError								css					#adyen_creditCard_expDate-error
securityCodeError							css					#creditCard_cvn-error
drpdwn_cardType								css					#dwfrm_billing_paymentMethods_creditCard_type
select_cardType								css					#dwfrm_billing_paymentMethods_creditCard_type option
cardTypeError								css					#dwfrm_billing_paymentMethods_creditCard_type-error
katespade_logo       			           css                 .primary-logo a
chkbx_applyPromoCode						css					[for='dwfrm_billing_promoCode']
txtbx_enterPromoCode						css					#dwfrm_billing_couponCode
btnApply									css					#add-coupon
lbl_subTotal								css					.order-subtotal>td:nth-child(2)
lbl_orderTotal								css					.order-total>td:nth-child(2)
lbl_successCartCouponMsg					css					.cart-coupon
lnk_sumaryBx_removeCpn						css					.remove-coupon
couponError									css					.coupon-error
radioBtn_paypal								css					[for='is-paypal']
radioBtn_klarna								css					[for='is-klarna']
drpdn_gender								css					.payment-method-expanded #dwfrm_adyPaydata_gender
txtbx_dob									css					.payment-method-expanded #dwfrm_adyPaydata_dob
txtbx_houseNo								css					.payment-method-expanded #dwfrm_adyPaydata_houseNumber
txtbx_houseExtn								css					.payment-method-expanded #dwfrm_adyPaydata_houseExtension
lnk_searchAgain							    css					.search-again-postal-code a
btn_findAddress								css					.tertiary
header_postalAddress						css					.postal-addresses-header					
drpdn_chooseAddress							css					#dwfrm_billing_billingAddress_addressFields_postalAddresses
option_chooseAddress						css					#dwfrm_billing_billingAddress_addressFields_postalAddresses option:nth-child(${index})
cvnTooltip									css					.payment-method .tooltip
cvnDetails									css					.tooltip .cvn-tooltip p
dropDown_Country                			css                 .select-style select[class*='country']
dropDown_Country_option         			css                 .select-style select[class*='country'] option
textbox_FindAddress             			css                 #dwfrm_addressy_addressFind
enter_manually								css					div.manual-postal-code
dropdown_AddressSuggest 			        xpath               //div[contains(@class,'pcaautocomplete pcatext') and not(contains(@class,'pcacountrylist'))]
dropdown_ListOfAddress   					xpath               //div[contains(@class,'pcaautocomplete pcatext') and not(contains(@class,'pcacountrylist'))]//div[contains(@class,'pcaitem')]
msg_errorFindAddressReq         			css                 .empty-address-postal-code
input_NewFindAdd_Relative_To_Country		xpath               //select[@id='dwfrm_singleshipping_shippingAddress_addressFields_country']/../../../following-sibling::div[1]//input[@id='dwfrm_addressy_addressFind']
