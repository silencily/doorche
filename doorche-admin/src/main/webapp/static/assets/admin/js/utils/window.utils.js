/**
 * Created by seven on 2016/6/10.
 */
if (typeof jQuery === 'undefined') {
    throw new Error('WindowUtils\'s JavaScript requires jQuery')
}

+function ($) {
    'use strict';
    $.extend({
        windowUtils: {
            settings: {
                title: "弹出窗口",
                confirmValidateMsg: "确认未获取返回值！",
                listable: false,
                confirmable: true,
                url: "",
                onConfirm: function (data) {
                }
            },
            _open: function () {
                $("#modal-dialog-window #modal-dialog-title").text(this.settings.title);
                $("#modal-dialog-window #modal-dialog-confirm").on("click", this._confirm);
                var iframeUrl = this.settings.url;
                if (this.settings.listable) {
                    iframeUrl += "?paginator.page=0";
                }
                var iframe = "<iframe class='modal-dialog-iframe' src='" + iframeUrl + "'></iframe>";
                $("#modal-dialog-window #modal-dialog-content").children().remove();
                $("#modal-dialog-window #modal-dialog-content").append(iframe);
                $("#modal-dialog-window").modal("show");
                $("#modal-dialog").draggable({handle: ".modal-header", cursor: "move"});
            },
            _confirm: function () {
                var data = $.windowUtils.handleData();
                if (data == null || data == "") {
                    $("#modal-dialog-title span").remove();
                    $.windowUtils.shake();
                    var message = "<span style='color: #dd4b39;margin-left: 20px;'>" + $.windowUtils.settings.confirmValidateMsg + "</span>";
                    $("#modal-dialog-title").append(message);
                    return;
                }
                $.windowUtils.settings.onConfirm(data);
                $("#modal-dialog-window").modal("hide");
            },
            open: function (options) {
                $.extend(this.settings, options);
                this._open();
            },
            openListingWin: function (url, title, callback) {
                var _options = {url: url, title: title, listable: true, onConfirm: callback, confirmValidateMsg: "至少选择一条记录！"};
                this.open(_options);
            },
            handleData: function () {
                return "";
            },
            shake:function(){
                var $panel = $("#modal-dialog");
                var box_left = ($(window).width() -  $panel.width()) / 2;
                $panel.css({'left': box_left,'position':'absolute'});
                for(var i=1; 4>=i; i++){
                    $panel.animate({left:box_left-(40-10*i)},50);
                    $panel.animate({left:box_left+2*(40-10*i)},50);
                }
            }

        }

    });

}(jQuery);