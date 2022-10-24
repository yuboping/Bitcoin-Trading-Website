'use strict';

$(document).ready(function () {
    $(".city-state-data li .first-box").click(function () {
        debugger;
        alert('1');
        var $li = $(this).parent();
        if (!$li.hasClass('active')) {
            // $(".city-state-data li.active").removeClass('active');
            $li.addClass('active');
        } else {
            $li.removeClass('active');
        }
    });
});