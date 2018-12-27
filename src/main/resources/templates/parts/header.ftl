<#--<#assign themeDisplay = httpUtil.getpath(['theme-display']['path-theme-images'])>-->

<#include "security.ftl">
<#import "login.ftl" as l>
<!-- HEADER -->
		<header>
            <!-- TOP HEADER -->
            <div id="top-header">
                <div class="container">
                    <ul class="header-links pull-left">
                        <li><a href="#"><i class="fa fa-phone"></i> +375 29 5003713</a></li>
                        <li><a href="#"><i class="far fa-envelope"></i>rudnets1.17@gmail.com</a></li>
                        <li><a href="#"><i class="fa fa-map-marker"></i>Minsk, ul. Amuratorskaya, 4</a></li>
                    </ul>
                    <ul class="header-links pull-right">
                        <li><a href="#"><img src="<#if '${.caller_template_name}'=='productView.ftl'>../</#if>static/img/Br.png" class="img-fluid"> BYN</a></li>
                        <li><a href="<#if user??>/user/profile<#else>/login</#if>"><i class="far fa-user"></i> ${name} </a></li>
 <#if (user??)>
                       <li>
                            <@l.logout />
                        </li>
 </#if>
                    </ul>
                </div>
            </div>
            <!-- /TOP HEADER -->

            <!-- MAIN HEADER -->
            <div id="header">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- LOGO -->
                        <div class="col-md-3">
                            <div class="header-logo">
                                <a href="/" class="logo">

                                        <img src="<#if '${.caller_template_name}'=='productView.ftl'>../</#if>static/img/logo.png" alt="" >



                                </a>
                            </div>
                        </div>
                        <!-- /LOGO -->

                        <!-- SEARCH BAR -->
                        <div class="col-md-6">
                            <div class="header-search">
                                <form action="productListSearch" name="productListSearch">
                                    <select class="input-select" name="section" id="section">
                                        <option value="">All Categories</option>
                                        <#list categories as cat>
                                        <option value=${cat}>${cat}</option>
                                        </#list>


                                    </select>
                                    <input class="input" placeholder="searh" name ="search" id = "search">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <button class="search-btn" name="search">Search</button>
                                </form>
                            </div>
                        </div>
                        <!-- /SEARCH BAR -->



                        <!-- ACCOUNT -->
                        <div class="col-md-3 clearfix">
                            <div class="header-ctn">
                                <div>

                                </div>
                                <!-- Cart -->
                                <#if user??>
                                <#if cart??>

                                <div class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Your Cart</span>
                                        <div class="qty">${total}</div>
                                    </a>

                                    <div class="cart-dropdown">
                                        <div class="cart-list">
                                            <#list cart as cartlist>
                                            <div class="product-widget">
                                                <div class="product-img">
                                                    <img src="<#if '${.caller_template_name}'=='productView.ftl'>../</#if>static/img/${cartlist.product.category}/${cartlist.product.filename}" alt="">
                                                </div>
                                                <div class="product-body">
                                                    <h3 class="product-name"><a href="#">${cartlist.product.title}</a></h3>
                                                    <h4 class="product-price"><span class="qty">${cartlist.quantity}x</span>${cartlist.product.price}</h4>
                                                </div>
                                                <button class="delete"><i class="fas fa-times"></i></button>
                                            </div>
                                            </#list>
                                        </div>
                                        <div class="cart-summary">
                                            <small>${total} Item(s) selected</small>
                                            <h5>SUBTOTAL: BYN ${sum}</h5>
                                        </div>
                                        <div class="cart-btns">
                                            <a href="/cart">View Cart</a>
                                            <a href="/checkout">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                                </#if>
                                </#if>
                                <!-- /Cart -->

                                <!-- Menu Toogle -->
                                <div class="menu-toggle">
                                    <a href="#">
                                        <i class="fa fa-bars"></i>
                                        <span>Menu</span>
                                    </a>
                                </div>
                                <!-- /Menu Toogle -->
                            </div>
                        </div>
                        <!-- /ACCOUNT -->


                    </div>
                    <!-- row -->

                </div>
                <!-- container -->
            </div>
            <!-- /MAIN HEADER -->
        </header>
		<!-- /HEADER -->