Page Title: KateSpade

#Object Definitions
====================================================================================
lst_categories										css				.has-sub-menu
subcategories										css				.menu-vertical
subcategories_links									css				.menu-vertical li a
header_navigation									css				#top-banner-interior
logo_kateSpade										xpath			//img[contains(@src,'ks-logo.svg')]
link_country										css				#site-tab-bar .name
link_signIn											xpath			//div[@class='user-panel']//div[@class='link']//a[contains(@href,'Account-Show')]
link_register										xpath			//div[@class='user-panel']//div[@class='link']//a[contains(@href,'Account-StartRegister')]
link_signInRegister									xpath			//div[@class='user-links has-icon signin']//a[contains(@href,'Account-Show')]
link_logout											css				.user-panel .user-logout
text_username										xpath			//div[@class='user-links has-icon signin']//span[contains(text(),'Hi')]
link_wishlist										xpath			//li[@class='user-info wishlist']//a[contains(@href,'Wishlist-Show')]
icon_Wishlist										css				li.user-info.wishlist img.heart-icon
icon_search											css				.search-icon-toggle
stickyTopBanner										css				.top-banner.sticky
stickyLogo_kateSpade								css				.primary-logo-sticky a
logo_accountLink									css				li.account-link img.arrow-icon
stickyLink_signIn									xpath			//div[@class='signin-modal signin-modal-sticky signin-not-registered']//a[contains(@href,'Account-Show')]
stickyLink_register									xpath			//div[@class='signin-modal signin-modal-sticky signin-not-registered']//a[contains(@href,'Account-StartRegister')]
link_myAccount										xpath			//li[@class='account-link']//div[contains(@class,'signin-registered')]//a[contains(@href,'Account-Show')]
icon_bag											css				.bag-icon-mobile
stickyBagQty										css				.stickyQty
link_NewShop									    css 			.has-sub-menu.ks-new-arrivals					
subcategory_viewall   								xpath   		//ul[@class='menu-category level-1']/li[${value}]//a[contains(@href,'view-all')]
link_ClothingShop									css				.has-sub-menu.ks-clothing
msg_headerPromotionalMessagingBar                   css				.tab-container .shipping-returns
icon_storeLocator									css				.stores-link.has-icon>a
text_search											css				.katesearchval
btn_search											css				.submit-search
categories											xpath			(//a[contains(@class,'has-sub-menu')])[${text}]
txt_usernameFr										xpath			//div[@class='user-links has-icon signin']//a[contains(@href,'account')][contains(text(),'Bonjour')]
breadcrumbSubCatgry									xpath			(//a[@class='breadcrumb-element'])[2]
prdctCatgry											css				.level-1 li:nth-child(${index}) i.menu-item-toggle+a
icon_hamburger										css				.menu-toggle
