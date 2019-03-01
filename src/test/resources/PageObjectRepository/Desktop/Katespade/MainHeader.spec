Page Title: KateSpade

#Object Definitions
====================================================================================
categories											xpath			(//a[contains(@class,'has-sub-menu')])[${text}] 
menu_categories										css				#main h1
category_nav										css				.menu-category.level-1
main_breadcrum										css				.breadcrumb
subcategories_title									css				.clearfix.full-width h1
lst_categories										css				.has-sub-menu
subcategories										css				.menu-vertical
subcategories_links									css				.menu-vertical li a
header_navigation									css				#top-banner-interior
logo_kateSpade										xpath			//img[contains(@src,'logo.svg')]
link_country										css				#site-tab-bar .name
link_signIn											xpath			//div[@class='user-panel']//div[contains(@class,"signin")]/span[contains(text(),Sign)]
link_register										xpath			//div[@class='user-panel']//div[contains(@class,"signin")]/span[contains(text(),Register)]
link_signInRegister									css				.user-panel .signin
link_logout											css				.user-panel .user-logout
text_username										xpath			//div[@class='user-links has-icon signin']//span[contains(text(),'H')]
link_wishlist										xpath			//li[@class='user-info wishlist']//a[contains(@title,"W")]
icon_Wishlist										css				li.user-info.wishlist img.heart-icon
icon_search											css				div.search-toggle a
stickyTopBanner										css				.top-banner.sticky
stickyLogo_kateSpade								css				.primary-logo-sticky a
logo_accountLink									css				li.account-link img.arrow-icon
stickyLink_signIn									xpath			//div[@class='signin-modal signin-modal-sticky signin-not-registered']//a[@title="Go to: Sign In"]
stickyLink_register									xpath			//div[@class='signin-modal signin-modal-sticky signin-not-registered']//a[@title="Go to: Register"]
link_myAccount										xpath			//li[@class='account-link']//div[contains(@class,'signin-registered')]//a[@title="Go to: My Account"]
icon_bag											css				.bag-icon a
stickyBagQty										css				.stickyQty
link_NewShop									    css 			.has-sub-menu.ks-new-arrivals					
subcategory_viewall   								xpath   		//ul[@class='menu-category level-1']/li[${value}]//a[contains(text(),'view all')]
msg_headerPromotionalMessagingBar					css				#site-tab-bar .tab-container .shipping-returns
link_ClothingShop									css				.has-sub-menu.ks-clothing
link_handbag										css				.has-sub-menu.ks-handbags
text_search											css				.katesearchval
btn_search											css				.submit-search
lnk_subCategory										xpath			//ul[@class='menu-vertical']//li//a[contains(text(),"${text}")]
subcategory_dresses									xpath			//ul[@class='menu-category level-1']/li[${value}]//a[contains(@href,'dresses')]
txt_usernameFr										xpath			//div[@class='user-links has-icon signin']//span[contains(text(),'Bonjour')]
breadcrumbSubCatgry									xpath			(//a[@class='breadcrumb-element'])[2]
subcategory_viewallFr  								xpath   		//ul[@class='menu-category level-1']/li[${value}]//a[contains(text(),'Tout Afficher')]
link_subcategoryWithIndex							xpath			(//ul[@class='menu-vertical']//li//div[@class='category-heading']/following-sibling::a)[${text}]
