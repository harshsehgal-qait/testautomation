Page Title: KateSpade

#Object Definitions
====================================================================================
storeLocatorHeader									css						.store-locator-header h1
textbox_address										css						#address
drpdwn_distance										css						#distance
button_findStores									css						.form-row.locator-submit button 
checkbox_retail										xpath					//input[@id='retail' and @type = 'checkbox'] 
checkbox_outlet										xpath					//input[@id='outlet' and @type = 'checkbox']
checkbox_stockist									xpath					//input[@id='stockist' and @type = 'checkbox']
label_addressInfo									xpath					(//div[@class='addressinfo'])[1]/div[1]
link_storeDetails									xpath					(//div[@class='storedetailslink']//a)[1]
button_getDirections								css						.get-directions a
label_breadcrumbs									css						.breadcrumb
map_storeLocator									css						#map-canvas
label_searchedStoresHeader							css						.stores-header
link_getDirections									css 					.storeinfo .directions a
label_distance										xpath					(//div[@class='distance'])[1]
label_storeName										css 					.storename a
list_distance										css						#distance option
text_storeNoInMap									xpath					(//div[@id='map-canvas']//div[@class='markerLabel'])[2]
text_storeNoInMapFr									xpath					(//div[@class='markerLabel'])[5]
icon_pin_StoreNo									css 					.storenumber a
lnk_storeDetailsMap									css						.mapContent .storedetailslink a
lnk_getDirections									css						.mapContent .directions a
text_map_address1									css						.contentBody.addressinfo .address1
label_frstDistance									xpath					(//div[@class='distance'])[1]	
icon_retail											xpath					//input[@id='retail']/../following-sibling::span[@class='retail']
icon_outlet											xpath					//input[@id='retail']/../following-sibling::span[@class='outlet']
icon_stockist										xpath					//input[@id='retail']/../following-sibling::span[@class='stockist']
find_store_near_you									css						.store-locator-header h1
city_pin_on_map										css						.contentBody.addressinfo .address2+div
store_phone											css						.storephone
lbl_noStore											css						.no-stores
refine_label										css						.refine-label
checkbox											css						[type='checkbox']
outlet												css						.outlet a
stockist											css						.stockist a
pincode_city										css						.storeinfo .address2+div
phone												css						.addressinfo .phone
store_detail_page_header							css						.store-details h1
store_detail_zip									css						.cityStateZip
store_detail_phone									css						.phone
store_img											css						#store-image img
store_hrs											css						.store-hours
store_info											css						.storeinfo
retailStoreNo										css						div[class="store"][ data-storetype="retail"]
