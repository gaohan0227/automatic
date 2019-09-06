var msgListVm;
$(function () {
    fnInit();
    fnGetMessList();
})

function fnInit() {
    msgListVm = new Vue({
        el: ".messageListVue",
        data: {
            messageList: [],
            isLoad: false
        }
    })
}

function fnGetMessList() {
    var postData = {
        "kind": "1",
    }
    POST("apiTeacherLogin/PCMessageInfoList", postData, function (ret, err) {
        if (ret && ret.status == "200") {
            msgListVm.messageList = ret.data.listData;
        }
        // $S.CloseProgress();
        msgListVm.isLoad = true;
    })
}
function fnRemoveMess(el, mId) {
    if (el && el.stopPropagation) {
        el.stopPropagation();
    } else {
        window.event.cancelBubble = true;
    };
    // $S.ShowProgress();
    var postData = {
        "id": mId,
    }
    POST("apiTeacherLogin/deleteMessageInfo", postData, function (ret, err) {
        if (ret && ret.status == "200") {
            fnToast("删除成功");
            for (var i = 0; i < msgListVm.messageList.length; i++) {
                for (var x = 0; x < msgListVm.messageList[i].messageInfoList.length; x++) {
                    if (msgListVm.messageList[i].messageInfoList[x].id == mId) {
                        msgListVm.messageList[i].messageInfoList.splice(x, 1);
                    }
                }
                if (msgListVm.messageList[i].messageInfoList.length == '0') {
                    msgListVm.messageList.splice(i, 1);
                }
            }
        };
        // $S.CloseProgress();
    })
}

function fnOpenMessageDetail(e) {
    var pageParam = {
        "mId": $api.attr(e, 'id')
    }
    for (var i = 0; i < msgListVm.messageList.length; i++) {
        for (var x = 0; x < msgListVm.messageList[i].messageInfoList.length; x++) {
            if (msgListVm.messageList[i].messageInfoList[x].id == pageParam.mId) {
                msgListVm.messageList[i].messageInfoList[x].ifRead = '1';
            }
        }
    }
    fnOpenPublicWin("详情页面", "mine/message_detail", pageParam);
}