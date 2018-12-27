<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<!-- SECTION -->
		<div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-7">
                        <!-- Billing Details -->
                        <div class="billing-details">
                            <div class="section-title">
                                <h3 class="title">Billing address</h3>
                            </div>
                            <div class="form-group">
                                <input class="input" type="text" name="first-name" placeholder="Имя">
                            </div>
                            <div class="form-group">
                                <input class="input" type="text" name="last-name" placeholder="Фамилия">
                            </div>
                            <div class="form-group">
                                <input class="input" type="email" name="email" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <input class="input" type="text" name="address" placeholder="Адрес">
                            </div>
                            <div class="form-group">
                                <input class="input" type="text" name="city" placeholder="Город">
                            </div>
                            <div class="form-group">
                                <input class="input" type="tel" name="tel" placeholder="Телефон">
                            </div>
                                     <!-- Order notes -->
                        <div class="order-notes">
                            <textarea class="input" placeholder="Order Notes"></textarea>
                        </div>
                        <!-- /Order notes -->
                    </div>
                    </div>
                    <!-- Order Details -->
                    <div class="col-md-5 order-details">
                        <div class="section-title text-center">
                            <h3 class="title">Your Order</h3>
                        </div>
                        <div class="order-summary">
                            <div class="order-col">
                                <div><strong>PRODUCT</strong></div>
                                <div><strong>TOTAL</strong></div>
                            </div>
                            <div class="order-products">
                                <#list cart as cartlist>
                                <div class="order-col">
                                    <div>1${cartlist.quantity}x ${cartlist.product.title}</div>
                                    <div>BYN${cartlist.product.price}</div>
                                </div>
                                </#list>
                            </div>
                            <div class="order-col">
                                <div><strong>TOTAL</strong></div>
                                <div><strong class="order-total">BYN${disc}</strong></div>
                            </div>
                        </div>
                        <div class="input-checkbox">
                            <input type="checkbox" id="terms">
                            <label for="terms">
                                <span></span>
                                I've read and accept the <a href="#">terms & conditions</a>
                            </label>
                        </div>
                        <a href="#" class="primary-btn order-submit">Place order</a>
                    </div>
                    <!-- /Order Details -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
		<!-- /SECTION -->
</@c.page>