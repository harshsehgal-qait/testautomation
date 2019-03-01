Page Title: Katespade

#Object Definitions     
====================================================================================
label_signInYourEmail                   css                     .login-box.login-account h2
input_loginUsername						id						dwfrm_login_username
input_loginPassword						id						dwfrm_login_password
label_rememberMe						xpath					//label[@for='dwfrm_login_rememberme']/span
lnk_forgotPassword						css						#password-reset
btn_signIn								name					dwfrm_login_login
btn_createAnAccount						name					dwfrm_login_register
button_CreateAnAccount					css  				   .secondary:nth-child(1)
textbox_FirstName					 	id  				    dwfrm_profile_customer_firstname
textbox_LastName						id  				    dwfrm_profile_customer_lastname
textbox_eMailAddress					id      				dwfrm_profile_customer_email
textbox_confirmEmail        			id  				    dwfrm_profile_customer_emailconfirm
textbox_password						id				        dwfrm_profile_login_password
textbox_confirmPassword					id  				    dwfrm_profile_login_passwordconfirm
button_apply							css 					div[class*='page-content'] .form-row-button .secondary
textMessage_Hi							xpath	 			    //div[@id='primary']/h1[contains(text(),'Hi')]
header_username							css 	 			    #primary h1
button_Add_New_Address					css 					.address-create.button.secondary
label_addAddress						xpath					//div[@id='dialog-container']//h1
textbox_addressName						css						input#dwfrm_profile_address_addressid
textbox_address_FirstName				css						input#dwfrm_profile_address_firstname
textbox_address_LastName				css						input#dwfrm_profile_address_lastname
textbox_address_PostalCode				css						input#dwfrm_profile_address_postal
textbox_address_Phone					css						input#dwfrm_profile_address_phone
textbox_login_emailAddress				css						input#dwfrm_login_username
textbox_login_password					css						input#dwfrm_login_password
button_login							xpath					//button[@name='dwfrm_login_login']
order_history							css						.content-asset #order a
order_history_page						css						#primary h1
lst_of_orders							css						.order-title-bar .order-number
button_apply_AddAddress					xpath					//button[@name='dwfrm_profile_address_create'] 
textbox_address1						css						#dwfrm_profile_address_address1
textbox_city							css						#dwfrm_profile_address_city
list_address							css						ul.address-list
lbl_primaryHeader						css						#primary>h1
username_field							css						#dwfrm_login_username
password_field							css						#dwfrm_login_password
link_myAccount                          css 					.my-account a
link_myInformation						css 					#edit-account a
link_creditCard							css					    .account-options li:nth-child(3) a
link_wishlist							css					    .account-options li:nth-child(5) a
link_orderHistory						css					    .account-options li:nth-child(4) a
icon_myInformation						xpath					//li[@id='edit-account']//a[contains(@href,'EditProfile')]
cancle_btn								css						.cancel.cancel-button.simple
address_book_page						css						#addresses h1
savedAddress							css						.address-tile:nth-child(1)
btn_editAddress							css						.address-tile.default:nth-child(1) .address-edit.edit-button
btn_deleteAddress						css						.address-tile.default:nth-child(1) .remove-button
cross_btn								css						.ui-icon-closethick
edit_btn								css						.button.address-edit.edit-button
edit_header								css						#dialog-container h1
update_btn								css						.form-row.form-row-button button:nth-child(1)
textbox_address2						css						#dwfrm_profile_address_address2
delete_btn								css						.delete.button.remove-button
default_add								css						.address-tile.default h2
find_address							css						.form-row.find-address
add_options								css 					#dwfrm_profile_address_postalAddresses option
add_option_1							css 					#dwfrm_profile_address_postalAddresses option:nth-child(2)
search_again							css						.search-again-postal-code
address_dropdown						css						#dwfrm_profile_address_postalAddresses
enter_manually							css						.manual-postal-code
link_signin							    css 					#navigation .signin a:nth-child(1)
link_register							css						#navigation .signin a:nth-child(2)
lbl_signInHeader						css						.login-account>h2		
link_forgotPas							css						#password-reset
lbl_rememberMe							css						[for="dwfrm_login_rememberme"]>span
lbl_createAccount						css						#primary>h1
contentSlot								css						.slot-content-include h1
select_myAccount						css						.account-menu-select span
lbl_accountSetting						css						.toggle
link_shopConfiedently					xpath					//a[contains(@href,'secureshoppingguarantee')]
lbl_doNotRecieveEmail					css						[for="dwfrm_profile_customer_custom_addToEmailList_1"]
btn_chkOrder 							css 					[href*='CheckOrder']
chkbx_privacyOptIn						css						[for='privacy-opt-in']
contentAsset_creditCard                 css                     .account-options .fa-card+h2
button_addNewAddress                    css                     .add-card
dialogContainer_addCreditCardLabel      css                     .add-card-title
crossbutton_addcredit                   css                     .ui-icon-closethick
addcredit_typefield                     css                     #dwfrm_paymentinstruments_creditcards_newcreditcard_type
pdp_page                                css						#pdpMain
page_header  						    css						#primary h1
side_nav								css						.secondary-navigation .content-asset a
delete_yes_btn							css						.ui-button-text-only:nth-child(1)
addcredit_typefield_error               css                     #dwfrm_paymentinstruments_creditcards_newcreditcard_type-error
addcredit_typefield_dropdownVal         css                     #dwfrm_paymentinstruments_creditcards_newcreditcard_type option
addcreitcard_owner                      css                     #creditCard_owner
addcreitcard_owner_error                css                     #creditCard_owner-error
addcreditcard_number                    css                     #creditCard_number
addcreditcard_number_error              css                     #creditCard_number-error
addcreditcard_startdate                 css                     #adyen_creditCard_startDate
addcreditcard_expdate                   css                     #adyen_creditCard_expDate
addcreditcard_expdate_error             css                     #adyen_creditCard_expDate-error
addcreditcard_issueNo                   css                     #adyen_creditCard_issueNumber
addcreditcard_cvv                       css                     #creditCard_cvn
addcreditcard_cvv_error                 css                     #creditCard_cvn-error
addcredit_typedropdown                  css                     #dwfrm_paymentinstruments_creditcards_newcreditcard_type option
addcredit_applyButton                   css                     #add-card-submit
addcredit_cancelButton                  css                     .cancel.cancel-button
creditcard_pageheader                   css                     .account-payments-header h1
creditcard_errormsg                     css                     #primary .error
creditcard_savedHeader                  css                     .paymentslist .cc-type-header
address_title							css						.address-tile.default>div:nth-child(2)
address_name							css						.address-tile.default>div:nth-child(3)
address_location						css						.address-tile.default>div:nth-child(4)
make_default_btn						css						a.default.button
alreadyRegstrdErrorMsg					css						[for='dwfrm_profile_customer_email']+div+div.error-message
radioBtn_addToMailingList				css						[for='dwfrm_profile_customer_custom_addToEmailList_0']
subText									css						.account-options h2+p
firstName								id						dwfrm_profile_customer_firstname
lastName								id						dwfrm_profile_customer_lastname
firstName_error							id						dwfrm_profile_customer_firstname-error
lastName_error							id						dwfrm_profile_customer_lastname-error
emailId									id						dwfrm_profile_customer_email
emailId_error							id						dwfrm_profile_customer_email-error
confirmEmail							id						dwfrm_profile_customer_emailconfirm
confirmEmail_error						id						dwfrm_profile_customer_emailconfirm-error
acc_password							id						dwfrm_profile_login_password
acc_password_error						id						dwfrm_profile_login_password-error
optIn_msg								css						[for="dwfrm_profile_customer_custom_addToEmailList_0"]
optOut_msg								css						[for="dwfrm_profile_customer_custom_addToEmailList_1"]	
radio_btn   							css						.input-radio
submit_btn								css						[name="dwfrm_profile_confirm"]
disabled_submitBtn						css						[name="dwfrm_profile_confirm"][disabled]
changePassword_header					css						#ChangePasswordForm .section-header	
current_password						id						dwfrm_profile_login_currentpassword
current_password_error					id						dwfrm_profile_login_currentpassword-error
new_password							id						dwfrm_profile_login_newpassword
new_password_error						id						dwfrm_profile_login_newpassword-error
confirm_new_password					id						dwfrm_profile_login_newpasswordconfirm
confirm_new_password_error				id						dwfrm_profile_login_newpasswordconfirm-error	
apply_btn								css						[name="dwfrm_profile_changepassword"]
wrong_curr_pass_error					css						.form-caption.error-message
addressName_error						id 						dwfrm_profile_address_addressid-error
add_firstName_error						id						dwfrm_profile_address_firstname-error
add_lastName_error						id						dwfrm_profile_address_lastname-error
add_postalCode_error					id						dwfrm_profile_address_postal-error
add_phoneNum_error 						id						dwfrm_profile_address_phone-error
country_drpdwn							id						dwfrm_profile_address_country
weHaveFound								css						.postal-addresses-header
addressNotFound							css						.no-results-postal-code
company_text							id						dwfrm_profile_address_companyName
address1_error							id						dwfrm_profile_address_address1-error
address2_text							id						dwfrm_profile_address_address2
city_error_msg							id						dwfrm_profile_address_city-error
country_text							id  					dwfrm_profile_address_custom_county
link_addressBook						css						.account-options li:nth-child(2) a
mobile_link_addressBook					css						#edit-address a
label_resetPassword                     css                     #dialog-container h1
text_resetPassword                      css                     #dialog-container p
forgotPassword_field_email              css                     #dwfrm_requestpassword_email
forgotpassword_send_button              css                     #PasswordResetForm .secondary
forgotpassword_thankyou_text            css                     #dialog-container p
goBackToHomePage                        css                     #dialog-container a
list_errorMsg							css						.error-message
btn_yes									css						.delete-confirm-button:nth-child(1)
hamburger_username						css						.navigation-top .signin a
btn_deleteByIndex						xpath					(//a[@class=' delete button remove-button'])[${index}]
confirmPswdError						css						#dwfrm_profile_login_passwordconfirm-error
addressbook_zips                        css                     .mini-address-location
label_signinyouremail                   css                     #primary .login-box.login-account h2
dropDown_Country                        css                     select.input-select
dropDown_Country_option                 css                     select.input-select option
button_Close                            css                     button[class*='ui-button']
button_signIn_new                       xpath                   (//*[@class='secondary'])[1]
button_CreateAccount_new                xpath                   (//div[@class='col-2']//div[contains(@class,'login-create-account')]//*[contains(@class,'secondary')])[1]
textbox_FindAddress                     css                     #dwfrm_addressy_addressFind
input_NewFindAdd_Relative_To_Country    xpath                   //select[@id='dwfrm_profile_address_country']/../../../following-sibling::div[1]//input[@id='dwfrm_addressy_addressFind']
text_FieldsCollapsed                    xpath                   //div[@class='suggested-address-fields' and contains(@style,'block')]//input[@placeholder='${name}']
dropdown_AddressSuggest                 xpath                   //div[contains(@class,'pcaautocomplete pcatext') and not(contains(@class,'pcacountrylist'))]
dropdown_ListOfAddress                  xpath                   //div[contains(@class,'pcaautocomplete pcatext') and not(contains(@class,'pcacountrylist'))]//div[contains(@class,'pcaitem')]
msg_errorFindAddressReq                 css                     .empty-address-postal-code
span_addressFindError					css						#dwfrm_addressy_addressFind-error
button_DeleteCard                       css                     .button-text.delete.remove-button
button_ConfirmDelete                    xpath                   //button[contains(@class,"delete-confirm-button")]/span[text()="Yes"]
