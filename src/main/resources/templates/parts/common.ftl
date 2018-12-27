<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/js/jquery-1.6.4.min.js" type="text/javascript"></script>
    <#--<script src="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/js/jsCarousel-2.0.0.js" type="text/javascript"></script>-->
    <meta charset="UTF-8">
    <#--ммммиииmeta name="_csrf" content="e62835df-f1a0-49ea-bce7-bf96f998119c" />-->
    <#--<sec:csrfMetaTags/>-->
<#--<sec:csrfMetaTags />-->
    <title>mySpring</title>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">


    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/css/bootstrap.min.css"/>

    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/css/slick.css"/>
    <link type="text/css" rel="stylesheet" href="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/css/slick-theme.css"/>

    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/css/nouislider.min.css"/>


    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/css/style.css"/>
    <script src="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/js/jquery.min.js"></script>
    <script src="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/js/bootstrap.min.js"></script>
    <script src="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/js/slick.min.js"></script>
    <script src="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/js/nouislider.min.js"></script>
    <script src="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/js/jquery.zoom.min.js"></script>
    <script src="<#if '${.caller_template_name}'=='productView.ftl'>/..</#if>/static/js/main.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <script src='https://www.google.com/recaptcha/api.js'></script>
    <#if '${.caller_template_name}'=='productList.ftl'>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#carouselhAuto2').each(function(){
                var settings = {
                    scrollspeed: 1500,
                    delay: 5000,
                    itemstodisplay: 3,
                    autoscroll: true,
                    circular: true,
                    masked: true,
                    onthumbnailclick: null,
                    orientation: 'v'};

            var oclass = 'vertical';

            var slidercontents = $(this).addClass('jscarousal-contents-' + oclass + '');
            var slider = $('<div/>').addClass('jscarousal-' + oclass + '').attr('id', slidercontents.attr('id'));
            var backbutton = $('<div/>').addClass('jscarousal-' + oclass + '-back');
            var forwardbutton = $('<div/>').addClass('jscarousal-' + oclass + '-forward');

            slidercontents.removeAttr('id');
            slidercontents.before(slider);
            slider.append(backbutton);
            slider.append(slidercontents);
            slider.append(forwardbutton);

            var total = $('> div', slidercontents).css('display', 'none').length;
            var index = 0;
            var start = 0;
            var current = $('<div/>');
            var noOfBlocks;
            var interval;
            var display = settings.itemstodisplay;
            var speed = settings.scrollspeed;
            var top;
            var left;
            var height;
            var containerHeight;
            var containerWidth;
            var direction = "forward";
            var scrolling = true;

            function initialize() {
                index = -1;
                noOfBlocks = parseInt(total / display);
                if (total % display > 0) noOfBlocks++;
                index = noOfBlocks - 1;
                var startIndex = 0;
                var endIndex = display;
                var copy = false;
                var allElements = $('> div', slidercontents);
                $('> div', slidercontents).remove();
                if (settings.masked)
                    allElements.addClass('thumbnail-inactive').hover(function() { $(this).removeClass('thumbnail-inactive').addClass('thumbnail-active'); }, function() { $(this).removeClass('thumbnail-active').addClass('thumbnail-inactive'); })
                for (var i = 0; i < noOfBlocks; i++) {
                    if (total > display) {
                        startIndex = i * display;
                        endIndex = startIndex + display;
                        if (endIndex > total) {
                            startIndex -= (endIndex - total);
                            endIndex = startIndex + display;
                            copy = true;
                        }
                    }
                    else {
                        startIndex = 0;
                        endIndex = total;
                    }
                    var wrapper = $('<div/>')
                    allElements.slice(startIndex, endIndex).each(function(index, el) {
                        if (!copy)
                            wrapper.append(el);
                        else wrapper.append($(el).clone(true));
                    });
                    wrapper.find("img").click(
                            function() {
                                if (settings.onthumbnailclick != null) {
                                    settings.onthumbnailclick($(this).attr('src'));
                                }
                            });
                    slidercontents.append(wrapper);
                }
                if (settings.onthumbnailclick != null)
                    $('> div > div', slidercontents).css('cursor', 'pointer');

                $('> div', slidercontents).addClass('hidden');
                $('> div > div', slidercontents).css('display', '');

                /*vertical*/
                if (settings.orientation == 'v') {
                    top = $('> div:eq(' + index + ')', slidercontents).css('top');
                    if (top == 'auto') top = "0px";
                    containerHeight = slidercontents.height();
                    height = slidercontents.get(0).offsetHeight;
                    $('> div', slidercontents).css('top', '-' + containerHeight + 'px');
                    $('> div:eq(' + index + ')', slidercontents).stop(false, true).animate({ 'top': top }, speed, function() { scrolling = false; });
                }
                /*horizontal*/
                if (settings.orientation == 'h') {
                    left = $('> div:eq(' + index + ')', slidercontents).css('left');
                    containerWidth = slidercontents.width();
                    height = slidercontents.get(0).offsetHeight;
                    $('> div', slidercontents).css('left', '-' + containerWidth + 'px');
                    $('> div:eq(' + index + ')', slidercontents).stop(false, true).animate({ left: 0 }, speed, function() { scrolling = false; });
                }
                $('> div:eq(' + index + ')', slidercontents).addClass('visible').removeClass('hidden');

                slider.mouseenter(function() { if (settings.autoscroll) stopAnimate(); }).mouseleave(function() { if (settings.autoscroll) animate(); });
                if (settings.autoscroll)
                    animate();
                forwardbutton.click(function() {
                    if (!scrolling) {
                        direction = "forward";
                        if (settings.circular)
                            if (index <= 0) index = noOfBlocks;
                        showThumbs();
                    }
                });
                backbutton.click(function() {
                    if (!scrolling) {
                        direction = "backward";
                        if (settings.circular)
                            if (index >= noOfBlocks - 1) index = -1;
                        showThumbs();
                    }
                });
            }
            initialize();
            function stopAnimate() {
                scrolling = false;
                clearTimeout(interval);
                slider.children().clearQueue();
                slider.children().stop(false, true);
            }
            function animate() {
                clearTimeout(interval);
                if (settings.autoscroll)
                    interval = setTimeout(changeSlide, settings.delay);
            }
            function changeSlide() {
                if (direction == "forward") {
                    if (index <= 0) index = noOfBlocks;
                } else {
                    if (index >= noOfBlocks - 1) { index = -1; }
                }
                showThumbs();
                interval = setTimeout(changeSlide, settings.delay);
            }
            function getDimensions(value) {
                return value + 'px';
            }
            function showThumbs() {
                scrolling = true;
                var current = $('.visible', slidercontents);
                var scrollSpeed = speed;

                if (direction == "forward") {
                    index--;
                    if (index >= 0) {
                        if (settings.orientation == 'v') {
                            $('>div:eq(' + index + ')', slidercontents).css('top', getDimensions(containerHeight)).removeClass('hidden').addClass('visible').stop(false, true).animate({ 'top': top }, scrollSpeed, function() { scrolling = false; });
                            current.stop(false, true).animate({ 'top': '-=' + getDimensions(containerHeight) }, scrollSpeed, function() {
                                $(this).removeClass('visible').addClass('hidden');
                                $(this).css('top', top);
                                scrolling = false;
                            });
                        }
                        else {
                            $('>div:eq(' + index + ')', slidercontents).css('left', getDimensions(containerWidth)).removeClass('hidden').addClass('visible').stop(false, true).animate({ 'left': '-=' + getDimensions(containerWidth) }, scrollSpeed, function() { scrolling = false; });
                            current.stop(false, true).animate({ 'left': '-=' + getDimensions(containerWidth) }, scrollSpeed, function() {
                                $(this).removeClass('visible').addClass('hidden');
                                $(this).css('left', left);
                                scrolling = false;
                            });
                        }
                    } else index = 0;

                }
                else if (direction == "backward") {
                    index++;
                    if (index < noOfBlocks) {
                        if (settings.orientation == 'v') {
                            $('>div:eq(' + index + ')', slidercontents).removeClass('hidden').addClass('visible').css({
                                'top': getDimensions(-containerHeight)
                            }).stop(false, true).animate({ 'top': top }, scrollSpeed, function() { scrolling = false; });

                            current.stop(false, true).animate({ 'top': '+=' + getDimensions(containerHeight) }, scrollSpeed,
                                    function() {
                                        $(this).removeClass('visible').addClass('hidden');
                                        $(this).css('top', getDimensions(-containerHeight));
                                        scrolling = false;
                                    });
                        }
                        else {
                            $('>div:eq(' + index + ')', slidercontents).removeClass('hidden').addClass('visible').css({
                                'left': getDimensions(-containerWidth)
                            }).stop(false, true).animate({ 'left': '+=' + getDimensions(containerWidth) }, scrollSpeed, function() { scrolling = false; });

                            current.stop(false, true).animate({ 'left': '+=' + getDimensions(containerWidth) }, scrollSpeed,
                                    function() {
                                        $(this).removeClass('visible').addClass('hidden');
                                        $(this).css('left', getDimensions(-containerWidth));
                                        scrolling = false;
                                    });
                        }

                    } else index = noOfBlocks - 1;
                }

            }
        });
        });
    </script>
    <script src="/static/js/jquery-1.6.4.min.js" type="text/javascript"></script>
    <#--<script src="/static/js/jsCarousel-2.0.0.js" type="text/javascript"></script>-->
        <link type="text/css" rel="stylesheet" href="/static/css/carousel.css"/>

    </#if>
</head>
<body>


    <#include "header.ftl">
    <#include "navbar.ftl">

<div class="container mt-5 float-left" >
    <#nested>
</div>
<<#include "footer.ftl">

</body>
</html>
</#macro>
