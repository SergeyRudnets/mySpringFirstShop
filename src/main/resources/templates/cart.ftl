<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

<div class="cart-table-area section-padding-100">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 col-lg-8">
                <div class="cart-title mt-50">
                    <h2>Shopping Cart</h2>
                </div>
                <div class="cart-table clearfix">
                    <table class="table table-responsive">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                        <#list cart as cartlist>
                            <td class="cart_product_img">
                                <a href="${cartlist.product.category}/${cartlist.product.title}"><img src="static/img/${cartlist.product.category}/${cartlist.product.filename}" alt="Product" id = "cart_product_img"></a>
                            </td>
                            <td class="cart_product_desc">
                                <h5>${cartlist.product.title}</h5>
                            </td>
                            <td class="price">
                                <span>${cartlist.product.price}</span>
                            </td>
                            <td class="qty">
                                <div class="qty-btn d-flex">
                                    <p>Qty</p>
                                    <div class="input-number">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                        <input type="number" value=${cartlist.quantity}>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                        <span class="qty-up">+</span>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                        <span class="qty-down">-</span>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-12 col-lg-4" id = "CartTotal">
                <div class="cart-summary">
                    <h5>Cart Total</h5>
                    <ul class="summary-table">
                        <li><span>subtotal:</span> <span>BYN${sum}</span></li>
                        <li><span>order discount:</span> <span>BYN ${disc}  </span></li>
                        <#assign newSum = sum - disc>
                        <li><span>total:</span> <span>BYN${newSum}</span></li>
                        <a href="/checkout" class="primary-btn order-submit">Check out</a>
                </div>
            </div>
        </div>
    </div>
</div>
</@c.page>