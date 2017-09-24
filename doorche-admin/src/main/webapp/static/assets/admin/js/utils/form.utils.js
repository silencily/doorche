/**
 * Created by seven on 2016/4/4.
 */

if (typeof jQuery === 'undefined') {
    throw new Error('FormUtils\'s JavaScript requires jQuery')
}

+function ($) {
    'use strict';
    $.extend({
        formUtils: {
            //form 提交，可以添加一些统一行为，如进度条等
            post: function ($form, url, closeOverlay, target) {
                if (!target) {
                    target = '_self';
                }
                $form.attr("target", target);
                $form.attr("method", "post");
                if (url) {
                    $form.attr("action", url);
                }
                if (!$form.valid()) {
                    return;
                }
                $form.submit();

                if (closeOverlay) {
                    return;
                }
                //显示加载进度
                $("#doorche-loading").removeClass("hide");
            },
            //动态创建form进行提交
            dynamicPost: function (url, fields, closeOverlay, target) {
                var params = [];
                $.each(fields, function (n, value) {
                    params.push($('<input>', {name: value.name, value: value.value}));
                });
                if (!target) {
                    target = '_self';
                }
                var $form = $('<form>', {method: 'post', action: url, target: target}).append(params);
                $(document.body).append($form);
                $form.submit();
                if (closeOverlay) {
                    return;
                }
                //显示加载进度
                $("#doorche-loading").removeClass("hide");
            }
        }
    });
}(jQuery);
//form validate
$(document).ready(function () {
    $("form").validate({
        errorElement: "em",
        errorPlacement: function (error, element) {
            // Add the `help-block` class to the error element
            error.addClass("help-block");

            if (element.prop("type") === "checkbox") {
                error.insertAfter(element.parent("label"));
            } else {
                error.insertAfter(element);
            }
        },
        highlight: function (element, errorClass, validClass) {
            $(element).parent().addClass("has-error").removeClass("has-success");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parent().addClass("has-success").removeClass("has-error");
        }
    });

});

