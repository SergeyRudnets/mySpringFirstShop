
<#import "parts/common.ftl" as c>

<@c.page>
<div class="section" id = "productListnearAside">
			<!-- container -->
			<div class="container">
				<!-- row -->
                <div class="row">
                    <div id="store" class="col-md-9">
                        <!-- store products -->
                        <div class="row">
                            <#list products as product>
                <!-- product -->
                                <#--<form method="get" class="addToCart">-->
                <div class="col-md-4 col-xs-6">
                    <div class="product">
                        <div class="product-img">
                            <img src="static/img/${product.category}/${product.filename}" alt="">
                            <div class="product-label">
                                <span class="sale">-${product.discount}%</span>
                            </div>
                        </div>
                        <div class="product-body">
                            <h3 class="product-name"><a href="${product.category}/${product.title}">${product.title}</a></h3>
                            <h4 class="product-price">${product.price} <del class="product-old-price">${product.oldPrice}</del></h4>
                        </div>
                        <div class="add-to-cart">
                            <form action="add-to-cart-btn_" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="hidden" id = "add-to-cart-btnVal" value="${product.title}" name="add-to-cart-btnVal" />
                                <input type="hidden" id = "add-to-cart-btnVal2" value="${product.category}" name="add-to-cart-btnVal2" />
                            <button class="add-to-cart-btn" type="submit" onclick=""><i class="fa fa-shopping-cart"></i> add to cart</button>
                            </form>
                        </div>
                    </div>
                    </div>
                                <#--</form>-->
            </#list>
                        </div>
                        <div class="store-filter clearfix">
                            <span class="store-qty">Showing 20-${products?size} products</span>
                            <ul class="store-pagination">
                                <li class="active">1</li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
</div>
<aside class="carousel-aisde">
    <div>
        <div id="carouselhAuto2">
        <#list 1..16 as i>
            <div >
                <img src="static/img/${category}/Carusel/${i}.jpg" />
                <br /><span class="thumbnail-text"><a href="#" type="hidden" ></a></span>
            </div>
        </#list>
        </div>
    </div>
</aside>
</@c.page>
