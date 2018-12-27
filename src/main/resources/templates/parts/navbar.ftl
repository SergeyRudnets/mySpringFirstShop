<#include "security.ftl">


<nav id="navigation" class="navbar navbar-expand-lg navbar-light bg-light">
    <!-- container -->
    <div class="container">
        <!-- responsive-nav -->
        <div id="responsive-nav">
            <!-- NAV -->
            <ul class="main-nav nav navbar-nav mr-auto">
                <li class="active"><a href="/">Home</a></li>

            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User list</a>
            </li>
            </#if>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/user/profile">Profile</a>
            </li>
            </#if>

                <li><a href="/volleyball">VolleyBall</a></li>
                <li><a href="#">Football</a></li>
                <li><a href="#">BasketBall</a></li>
                <#--<li><a href="#">Tennis</a></li>-->
                <#--<li><a href="#">Hockey</a></li>-->
                <#--<li><a href="#">Billiards</a></li>-->
            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>

<#--<nav class="navbar navbar-expand-lg navbar-light bg-light">-->
    <#--<a class="navbar-brand" href="/">mySpring</a>-->
    <#--<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">-->
        <#--<span class="navbar-toggler-icon"></span>-->
    <#--</button>-->

    <#--<div class="collapse navbar-collapse" id="navbarSupportedContent">-->
        <#--<ul class="navbar-nav mr-auto">-->
            <#--<li class="nav-item">-->
                <#--<a class="nav-link" href="/">Home</a>-->
            <#--</li>-->
            <#--<li class="nav-item">-->
                <#--<a class="nav-link" href="/main">Messages</a>-->
            <#--</li>-->
            <#--<#if isAdmin>-->
            <#--<li class="nav-item">-->
                <#--<a class="nav-link" href="/user">User list</a>-->
            <#--</li>-->
            <#--</#if>-->
            <#--<#if user??>-->
            <#--<li class="nav-item">-->
                <#--<a class="nav-link" href="/user/profile">Profile</a>-->
            <#--</li>-->
            <#--</#if>-->
        <#--</ul>-->
        <#--<nav class="navbar navbar-light bg-light">-->
            <#--<a class="navbar-brand">Navbar</a>-->
            <#--<form class="form-inline">-->
                <#--<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">-->
                <#--<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>-->
            <#--</form>-->
        <#--</nav>-->
        <#--<div class="navbar-title mr-3">${name}</div>-->
    <#--</div>-->
<#--</nav>-->
