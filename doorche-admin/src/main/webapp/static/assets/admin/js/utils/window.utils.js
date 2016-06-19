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
                listable: false,
                confirmable: true,
                url: "",
                onConfirm: function (data) {
                }
            },
            _open: function () {
                $("#modal-dialog-window #modal-dialog-title").text(this.settings.title);
                $("#modal-dialog-window #modal-dialog-confirm").one("click", this._confirm);
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
                $.windowUtils.settings.onConfirm(data);
                $("#modal-dialog-window").modal("hide");
            },
            open: function (options) {
                $.extend(this.settings, options);
                this._open();
            },
            openListingWin: function (url, title, callback) {
                var _options = {url: url, title: title, listable: true, onConfirm: callback};
                this.open(_options);
            },
            handleData: function () {
                return "";
            }

        }

    });

}(jQuery);