/** 
 * PrimeFaces Prestige Layout
 */
PrimeFaces.widget.Prestige = PrimeFaces.widget.BaseWidget.extend({
    
    init: function(cfg) {
        this._super(cfg);
        this.wrapper = $(document.body).children('.layout-wrapper');
        this.topbar = this.wrapper.find('.layout-topbar');
        this.menuButton = this.topbar.find('.layout-menu-button');
        this.menuContainer = this.wrapper.find('.layout-menu-container');
        this.menuWrapper = this.wrapper.find('.layout-menu-wrapper');
        this.menu = this.jq;
        this.overlayMenuButton = this.menuContainer.find('.overlay-menu-button');
        this.rootMenuitems = this.menu.children('li');
        this.rootMenulinks = this.rootMenuitems.children('a');
        this.menulinks = this.rootMenuitems.find('li a');
        this.expandedMenuitems = this.expandedMenuitems||[];
        this.layoutMask = this.menuContainer.find('layout-content-mask')

        this.topbarMenu = this.topbar.children('.layout-topbar-menu');
        this.topbarItems = this.topbarMenu.children('li');
        this.topbarLinks = this.topbarItems.children('a');

        this.topbarMenuClick = false;

        this.configButton = $('#layout-config-button');
        this.configMenu = $('#layout-config');
        this.configMenuClose = this.configMenu.find('> .layout-config-content > .layout-config-close');

        this.restoreMenuState();
        
        this._bindEvents();
    },
    
    _bindEvents: function() {
        var $this = this;

        this.menuContainer.off('click.menu-container').on('click.menu-container', function() {
            $this.menuClick = true;
        });

        this.menuButton.off('click.menu-button').on('click.menu-button', function(e) {
            $this.menuClick = true;

            if($this.isMobile()) {
                $this.wrapper.toggleClass('layout-mobile-active');
                $(document.body).toggleClass('blocked-scroll');
            }
            
            e.preventDefault();
        });

        this.overlayMenuButton.off('click.overlay-button').on('click.overlay-button', function(e) {
            $this.menuClick = true;
        
            if ($this.wrapper.hasClass('layout-menu-overlay-active')) {
                $this.menuWrapper.addClass('fadeOutUp').removeClass('fadeInDown');
                
                setTimeout(function() {
                    $this.wrapper.removeClass('layout-menu-overlay-active');
                    $this.menuWrapper.removeClass('fadeOutUp');
                }, 150);
            }
            else {
                $this.wrapper.addClass('layout-menu-overlay-active');
                $this.menuWrapper.removeClass('fadeOutUp').addClass('fadeInDown');
            }

            e.preventDefault();
        });

        this.menulinks.off('click.menulinks').on('click.menulinks', function (e) {
            var link = $(this),
            item = link.parent('li'),
            submenu = item.children('ul');

            if (item.hasClass('active-menuitem')) {
                if (submenu.length) {
                    $this.removeMenuitem(item.attr('id'));                    
                  
                    submenu.slideUp(400, function() {
                        item.removeClass('active-menuitem');
                    });                                      
                }

                if(item.parent().is($this.jq)) {
                    $this.menuActive = false;
                }
            }
            else {
                var href = link.attr('href');
                if (href && href != '#') {
                    $this.expandedMenuitems = [];
                    $this.clearMenuState();
                    $this.addParentMenuItems(item);
                }
                $this.addMenuitem(item.attr('id'));
                submenu.slideDown(400, function() {
                    item.addClass('active-menuitem');
                }); 
                $this.deactivateItems(item.siblings(), true, 'active-menuitem');

                if(submenu.length === 0 && $this.isHorizontalMenu()) {
                    $this.resetMenu();
                }
                $this.activate(item);
                
                if(item.parent().is($this.jq)) {
                    $this.menuActive = true;
                }
            }

            if (submenu.length) {
                e.preventDefault();
            }
        });

        this.rootMenulinks.off('click.root-menulinks').on('click.root-menulinks', function (e) {
            var link = $(this),
            item = link.parent('li'),
            submenu = item.children('ul'),
            isHorizontal = $this.wrapper.hasClass('layout-menu-horizontal');

            if (isHorizontal) {
                if (item.hasClass('active-rootmenuitem')) {
                    if (submenu.length) {
                        if ($this.isDesktop()) {
                            item.removeClass('active-rootmenuitem');
                            submenu.hide();
                        }
                        else {
                            submenu.slideUp(400, function() {
                                item.removeClass('active-rootmenuitem');
                            });
                        }
                    }

                    $this.menuActive = false;
                }
                else {
                    var activeItems = item.siblings().filter('.active-rootmenuitem');
                    if ($this.isDesktop()) {
                        activeItems.removeClass('active-rootmenuitem').end().children('ul:visible').hide();
                    }
                    else {
                        activeItems.children('ul:visible').slideUp(400, function() {
                            $(this).parent().removeClass('active-rootmenuitem');
                        });
                    }

                    $this.activate(item, 'active-rootmenuitem');

                    $this.menuActive = true;
                }
            }
            else {
                if (item.hasClass('active-menuitem')) {
                    if (submenu.length) {
                        $this.removeMenuitem(item.attr('id'));
                        submenu.slideUp(400, function() {
                            item.removeClass('active-menuitem');
                        });
                    }
                }
                else {
                    $this.addMenuitem(item.attr('id'));
                    $this.deactivateItems(item.siblings(), true, 'active-menuitem');
                    $this.activate(item, 'active-menuitem');
                }
            }

            if (submenu.length) {
                e.preventDefault();
            }
            else {
                var href = link.attr('href');
                if (href && href != '#') {
                    $this.expandedMenuitems = [];
                    $this.clearMenuState();
                    $this.addMenuitem(item.attr('id'));
                }
            }
        });

        this.rootMenuitems.off('mouseenter.root-menuitem').on('mouseenter.root-menuitem', function(e) {    
            if($this.isHorizontalMenu()) {
                var item = $(this),
                submenu = item.children('ul');
                
                if(!item.hasClass('active-rootmenuitem')) {
                    $this.menu.find('.active-rootmenuitem').removeClass('active-rootmenuitem');
                    $this.menu.find('> li .active-menuitem').removeClass('active-menuitem');
                    $this.menu.find('ul:visible').hide();
                    
                    if($this.menuActive && submenu.length) {
                        item.addClass('active-rootmenuitem');
                        item.children('ul').show();
                    }
                }
            }
        });

        this.topbarLinks.off('click.topbarlinks').on('click.topbarlinks', function (e) {
            var link = $(this),
                item = link.parent(),
                submenu = link.next();

            if ($this.isDesktop() && item.hasClass('topbar-search')) {
                e.preventDefault();
                return;
            }

            $this.topbarMenuClick = true;

            item.siblings('.active-topmenuitem').removeClass('active-topmenuitem');         

            if (submenu.length) {
                if (item.hasClass('active-topmenuitem')) {
                    if(item.hasClass('user-profile')){
                        $this.hideTopBarSubMenu(item);
                    }else{
                        item.removeClass('active-topmenuitem');  
                    }
                }
                else {
                    item.addClass('active-topmenuitem');
                    submenu.removeClass('fadeOutUp');
                    submenu.addClass('fadeInDown');
                }
            }

            if (link.attr('href') === '#') {
                e.preventDefault();
            }
        });

        this.configButton.off('click.config').on('click.config', function(e) {
            if($this.configMenu.hasClass('layout-config-active')) {
                $this.configMenu.removeClass('layout-config-active')

                $(document.body).removeClass('blocked-scroll-config');
            }
            else {
                $this.configMenu.addClass('layout-config-active');

                $(document.body).addClass('blocked-scroll-config');
            }
        
            e.preventDefault();
        });

        this.configMenu.off('click.layout-config').on('click.layout-config', function() {
            $this.configClick = true;
        });

        this.configMenuClose.off('click.config').on('click.config', function(e) {
            $this.configMenu.removeClass('layout-config-active')

            $(document.body).removeClass('blocked-scroll-config');
        
            e.preventDefault();
        });

        $(document.body).off('click.layoutBody').on('click.layoutBody', function(e) {
            if($this.isHorizontalMenu() && !$this.menuClick && $this.isDesktop()) {
                $this.resetMenu();
            }

            if($this.isOverlayMenu() && !$this.menuClick && $this.isDesktop()) {
                $this.menuWrapper.addClass('fadeOutUp').removeClass('fadeInDown');
                
                setTimeout(function() {
                    $this.wrapper.removeClass('layout-menu-overlay-active');
                    $this.menuWrapper.removeClass('fadeOutUp');
                }, 150);
            }

            if (!$this.topbarMenuClick && e.target.nodeName !== "INPUT") {
                $this.hideTopBarSubMenu($this.topbarItems.filter('.active-topmenuitem'));
            }

            if(!$this.menuClick) {
                $this.wrapper.removeClass('layout-overlay-active layout-mobile-active');
                $(document.body).removeClass('blocked-scroll');
            }

            if(!$this.configClick) {
                $this.configMenu.removeClass('layout-config-active');
                $(document.body).removeClass('blocked-scroll-config');
            }

            $this.menuClick = false;
            $this.configClick = false;
            $this.topbarMenuClick = false;
        });
    },

    hideTopBarSubMenu: function(item) {
        var submenu = item.children('ul');
        submenu.addClass('fadeOutUp').removeClass('fadeInDown');

        setTimeout(function() {
            item.removeClass('active-topmenuitem');
            submenu.removeClass('fadeOutUp');
        }, 150);

    },

    resetMenu: function() {
        var menu = this.isHorizontalMenu() ? this.menu.children('li') : this.menu;
        menu.removeClass('active-rootmenuitem');
        menu.find('.active-menuitem').removeClass('active-menuitem');
        menu.find('ul:visible').hide();
        this.menuActive = false;
    },

    activate: function(item, menuitemActiveClass) {
        var submenu = item.children('ul');
        item.addClass(menuitemActiveClass);

        if(submenu.length) {
            if(this.isHorizontalMenu())
                submenu.show();
            else
                submenu.slideDown();
        }
    },

    deactivate: function(item) {
        var submenu = item.children('ul');
        item.removeClass('active-menuitem active-rootmenuitem');

        if(submenu.length) {
            submenu.hide();
        }
    },

    deactivateItems: function(items, animate,className) {
        var $this = this;
        var classNameSelector = '.' + className;

        for(var i = 0; i < items.length; i++) {
            var item = items.eq(i),
            submenu = item.children('ul');

            if(submenu.length) {
                if(item.hasClass(className)) {
                    var activeSubItems = item.find(classNameSelector);

                    if(animate) {
                        submenu.slideUp(400, function() {
                            var _item = $(this).parent();
                            _item.removeClass(className);
                            _item.find(classNameSelector).each(function() {
                                $this.deactivate($(this));
                            });
                        });
                    }
                    else {
                        item.removeClass(className);
                        submenu.hide();
                        item.find(classNameSelector).each(function() {
                            $this.deactivate($(this));
                        });
                    }

                    $this.removeMenuitem(item.attr('id'));
                    activeSubItems.each(function() {
                        $this.removeMenuitem($(this).attr('id'));
                    });
                }
                else {
                    item.find(classNameSelector).each(function() {
                        var subItem = $(this);
                        $this.deactivate(subItem);
                        $this.removeMenuitem(subItem.attr('id'));
                    });
                }
            }
            else if(item.hasClass(className)) {
                $this.deactivate(item);
                $this.removeMenuitem(item.attr('id'));
            }
        }
    },
   
    addParentMenuItems: function (item) {
        var parentMenuitem = item.parent().closest('li[role="menuitem"]');
        
        if (parentMenuitem.length) {
            var isRootMenuitem = parentMenuitem.parent().is(this.jq);

            this.addMenuitem(parentMenuitem.attr('id'));
            if (!isRootMenuitem) {
                this.addParentMenuItems(parentMenuitem);
            }
        }
    },

    removeMenuitem: function (id) {
        this.expandedMenuitems = $.grep(this.expandedMenuitems, function (value) {
            return value !== id;
        });

        this.saveMenuState();
    },

    addMenuitem: function (id) {
        if ($.inArray(id, this.expandedMenuitems) === -1) {
            this.expandedMenuitems.push(id);
        }
        this.saveMenuState();
    },

    saveMenuState: function() {
        $.cookie('prestige_expandeditems', this.expandedMenuitems.join(','), {path: '/'});
    },
    
    saveScrollState: function (value) {
        $.cookie('prestige_scroll', value, { path: '/' });
    },

    clearMenuState: function() {
        $.removeCookie('prestige_expandeditems', {path: '/'});
        $.removeCookie('prestige_scroll', { path:'/' });
    },

    isDesktop: function() {
        return window.innerWidth > 991;
    },

    isMobile: function() {
        return window.innerWidth <= 991;
    },

    isHorizontalMenu: function() {
        return this.wrapper.hasClass('layout-menu-horizontal') && this.isDesktop();
    },

    isOverlayMenu: function() {
        return this.wrapper.hasClass('layout-menu-overlay') && this.isDesktop();
    },

    restoreMenuState: function() {
        var menucookie = $.cookie('prestige_expandeditems');
        if (menucookie) {
            var isHorizontal = this.wrapper.hasClass('layout-menu-horizontal');
            this.expandedMenuitems = menucookie.split(',');
            for (var i = 0; i < this.expandedMenuitems.length; i++) {
                var id = this.expandedMenuitems[i];
                if (id) {
                    var menuitem = $("#" + this.expandedMenuitems[i].replace(/:/g, "\\:"));
                    menuitem.addClass('active-menuitem');

                    var submenu = menuitem.children('ul');
                    if (submenu.length) {
                        var isRootMenuitem = menuitem.parent().is(this.jq);

                        if (isHorizontal && isRootMenuitem) {
                            submenu.hide();
                        }
                        else {
                            submenu.show();
                        }
                    }
                }
            }
        }
        else if (this.isHorizontalMenu()) {
            var firstRootItem = this.rootMenuitems.eq(0);
            firstRootItem.addClass('active-menuitem');
            this.addMenuitem(firstRootItem.attr('id'));
        }
    }
});


PrimeFaces.PrestigeConfigurator = {

    changeLayout: function(layoutTheme, componentTheme) {
        var linkElement = $('link[href*="layout-"]');
        var href = linkElement.attr('href');
        var startIndexOf = href.indexOf('layout-') + 7;
        var endIndexOf = href.indexOf('.css');
        var currentColor = href.substring(startIndexOf, endIndexOf);

        this.replaceLink(linkElement, href.replace('layout-' + currentColor, layoutTheme));
        this.changeComponentsTheme(componentTheme);
    },

    changeComponentsTheme: function(theme) {
        var library = 'primefaces-prestige';
        var linkElement = $('link[href*="theme.css"]');
        var href = linkElement.attr('href');
        var index = href.indexOf(library) + 1;
        var currentTheme = href.substring(index + library.length);
        this.replaceLink(linkElement, href.replace(currentTheme, theme));
    },

    changeMenuToOverlay: function() {
        $('.layout-wrapper').removeClass('layout-menu-horizontal').addClass('layout-menu-overlay');
        this.updateMenuState();
    },

    changeMenuToHorizontal: function() {
        $('.layout-wrapper').removeClass('layout-menu-overlay').addClass('layout-menu-horizontal');
        this.updateMenuState();
    },

    changeWrapperToBoxed: function() {
        $('.layout-wrapper').removeClass('layout-fullwidth');
        this.updateMenuState();
    },

    changeWrapperToFullWidth: function() {
        $('.layout-wrapper').addClass('layout-fullwidth');
        this.updateMenuState();
    },

    beforeResourceChange: function() {
        PrimeFaces.ajax.RESOURCE = null;    //prevent resource append
    },

    replaceLink: function(linkElement, href) {
        PrimeFaces.ajax.RESOURCE = 'javax.faces.Resource';
        
        var isIE = this.isIE();

        if (isIE) {
            linkElement.attr('href', href);
        }
        else {
            var cloneLinkElement = linkElement.clone(false);

            cloneLinkElement.attr('href', href);
            linkElement.after(cloneLinkElement);
            
            cloneLinkElement.off('load').on('load', function() {
                linkElement.remove();
            });
        }
    },

    isIE: function() {
        return /(MSIE|Trident\/|Edge\/)/i.test(navigator.userAgent);
    },

    updateMenuState: function() {
        var menu = PF('prestigeMenuWidget');

        if (menu) {
            menu.restoreMenuState();
        }
    }
};

/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD (Register as an anonymous module)
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// Node/CommonJS
		module.exports = factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {

	var pluses = /\+/g;

	function encode(s) {
		return config.raw ? s : encodeURIComponent(s);
	}

	function decode(s) {
		return config.raw ? s : decodeURIComponent(s);
	}

	function stringifyCookieValue(value) {
		return encode(config.json ? JSON.stringify(value) : String(value));
	}

	function parseCookieValue(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape...
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}

		try {
			// Replace server-side written pluses with spaces.
			// If we can't decode the cookie, ignore it, it's unusable.
			// If we can't parse the cookie, ignore it, it's unusable.
			s = decodeURIComponent(s.replace(pluses, ' '));
			return config.json ? JSON.parse(s) : s;
		} catch(e) {}
	}

	function read(s, converter) {
		var value = config.raw ? s : parseCookieValue(s);
		return $.isFunction(converter) ? converter(value) : value;
	}

	var config = $.cookie = function (key, value, options) {

		// Write

		if (arguments.length > 1 && !$.isFunction(value)) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setMilliseconds(t.getMilliseconds() + days * 864e+5);
			}

			return (document.cookie = [
				encode(key), '=', stringifyCookieValue(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// Read

		var result = key ? undefined : {},
			// To prevent the for loop in the first place assign an empty array
			// in case there are no cookies at all. Also prevents odd result when
			// calling $.cookie().
			cookies = document.cookie ? document.cookie.split('; ') : [],
			i = 0,
			l = cookies.length;

		for (; i < l; i++) {
			var parts = cookies[i].split('='),
				name = decode(parts.shift()),
				cookie = parts.join('=');

			if (key === name) {
				// If second argument (value) is a function it's a converter...
				result = read(cookie, value);
				break;
			}

			// Prevent storing a cookie that we couldn't decode.
			if (!key && (cookie = read(cookie)) !== undefined) {
				result[name] = cookie;
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		// Must not alter options, thus extending a fresh object...
		$.cookie(key, '', $.extend({}, options, { expires: -1 }));
		return !$.cookie(key);
	};

}));

/* Issue #924 is fixed for 5.3+ and 6.0. (compatibility with 5.3) */
if(window['PrimeFaces'] && window['PrimeFaces'].widget.Dialog) {
    PrimeFaces.widget.Dialog = PrimeFaces.widget.Dialog.extend({
        
        enableModality: function() {
            this._super();
            $(document.body).children(this.jqId + '_modal').addClass('ui-dialog-mask');
        },
        
        syncWindowResize: function() {}
    });
}

/* Issue #2131 */
if(window['PrimeFaces'] && window['PrimeFaces'].widget.Schedule && isLtPF8Version()) {
    PrimeFaces.widget.Schedule = PrimeFaces.widget.Schedule.extend({
        
        setupEventSource: function() {
            var $this = this,
            offset = moment().utcOffset()*60000;

            this.cfg.events = function(start, end, timezone, callback) {
                var options = {
                    source: $this.id,
                    process: $this.id,
                    update: $this.id,
                    formId: $this.cfg.formId,
                    params: [
                        {name: $this.id + '_start', value: start.valueOf() + offset},
                        {name: $this.id + '_end', value: end.valueOf() + offset}
                    ],
                    onsuccess: function(responseXML, status, xhr) {
                        PrimeFaces.ajax.Response.handle(responseXML, status, xhr, {
                                widget: $this,
                                handle: function(content) {
                                    callback($.parseJSON(content).events);
                                }
                            });

                        return true;
                    }
                };

                PrimeFaces.ajax.Request.handle(options);
            }
        }
    });
}

/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD (Register as an anonymous module)
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// Node/CommonJS
		module.exports = factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {

	var pluses = /\+/g;

	function encode(s) {
		return config.raw ? s : encodeURIComponent(s);
	}

	function decode(s) {
		return config.raw ? s : decodeURIComponent(s);
	}

	function stringifyCookieValue(value) {
		return encode(config.json ? JSON.stringify(value) : String(value));
	}

	function parseCookieValue(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape...
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}

		try {
			// Replace server-side written pluses with spaces.
			// If we can't decode the cookie, ignore it, it's unusable.
			// If we can't parse the cookie, ignore it, it's unusable.
			s = decodeURIComponent(s.replace(pluses, ' '));
			return config.json ? JSON.parse(s) : s;
		} catch(e) {}
	}

	function read(s, converter) {
		var value = config.raw ? s : parseCookieValue(s);
		return $.isFunction(converter) ? converter(value) : value;
	}

	var config = $.cookie = function (key, value, options) {

		// Write

		if (arguments.length > 1 && !$.isFunction(value)) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setMilliseconds(t.getMilliseconds() + days * 864e+5);
			}

			return (document.cookie = [
				encode(key), '=', stringifyCookieValue(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// Read

		var result = key ? undefined : {},
			// To prevent the for loop in the first place assign an empty array
			// in case there are no cookies at all. Also prevents odd result when
			// calling $.cookie().
			cookies = document.cookie ? document.cookie.split('; ') : [],
			i = 0,
			l = cookies.length;

		for (; i < l; i++) {
			var parts = cookies[i].split('='),
				name = decode(parts.shift()),
				cookie = parts.join('=');

			if (key === name) {
				// If second argument (value) is a function it's a converter...
				result = read(cookie, value);
				break;
			}

			// Prevent storing a cookie that we couldn't decode.
			if (!key && (cookie = read(cookie)) !== undefined) {
				result[name] = cookie;
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		// Must not alter options, thus extending a fresh object...
		$.cookie(key, '', $.extend({}, options, { expires: -1 }));
		return !$.cookie(key);
	};

}));

/* Issue #924 is fixed for 5.3+ and 6.0. (compatibility with 5.3) */
if(window['PrimeFaces'] && window['PrimeFaces'].widget.Dialog) {
    PrimeFaces.widget.Dialog = PrimeFaces.widget.Dialog.extend({
        
        enableModality: function() {
            this._super();
            $(document.body).children(this.jqId + '_modal').addClass('ui-dialog-mask');
        },
        
        syncWindowResize: function() {}
    });
}

function isLtPF8Version() {
    var version = window['PrimeFaces'].VERSION;
    if (!version) {
        return true;
    }

    return parseInt(version.split('.')[0], 10) < 8;
}