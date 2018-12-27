<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-12">
                        <ul class="breadcrumb-tree">
                            <li><a href="/">Home</a></li>
                            <li><a href="/${product.category}">${product.category}</a></li>
                            <li><a href="/${product.title}">${product.title}</a></li>
                        </ul>
                    </div>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
		<!-- /BREADCRUMB -->
<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- Product main img -->
    <div class="col-md-5 col-md-push-2">
        <div id="product-main-img">
            <div class="product-preview">
                <img src="/../static/img/${product.category}/${product.filename}" alt="">
            </div>
            <#list 1..3 as prev>
                <div class="product-preview">
                    <img src="/../static/img/${product.category}/${product.title}_Prev${prev}.jpg" alt="">
                </div>

            </#list>
        </div>
    </div>
    <!-- /Product main img -->
    <!-- Product thumb imgs -->
    <div class="col-md-2  col-md-pull-5">
        <div id="product-imgs">
            <div class="product-preview">
                <img src="/../static/img/${product.category}/${product.filename}" alt="">
            </div>
            <#list 1..3 as prev>
                <div class="product-preview">
                    <img src="/../static/img/${product.category}/${product.title}_Prev${prev}.jpg" alt="">
                </div>

            </#list>
        </div>
    </div>
    <!-- /Product thumb imgs -->
    <!-- Product details -->
    <div class="col-md-5">
        <div class="product-details">
            <h2 class="product-name">${product.title}</h2>
            <div>
                <h3 class="product-price"> ${product.price} BYN<del class="product-old-price">${oldPrice} BYN</del></h3>
                <span class="product-available">In Stock</span>
            </div>
            <p>${product.description}.</p>

             <div class="add-to-cart">
                <div class="qty-label">
                    Qty
                    <div class="input-number">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <input type="number" value="1">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <span class="qty-up">+</span>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <span class="qty-down">-</span>
                    </div>
                </div>
                 <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
            </div>


        </div>
    </div>
    <!-- /Product details -->
                </div>
            </div>
</div>
</@c.page>