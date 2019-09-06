var errorBookVm;
$(function () {
    fnInit();
    fnGetRrrorList();
})

function fnInit() {
    errorBookVm = new Vue({
        el: ".errorQuestionVue",
        data: {
            errorQuestionList: [],
            totalCount: "",
            isLoad: false,
        },
    })
}
function fnGetRrrorList() {
    // $S.ShowProgress();
    var postData = {
        "unitId": localStorage.unitId || ""
    }
    POST("apiQuestion/findMyErrorQuestionList", postData, function (ret) {
        if (ret && ret.status == "200") {
            errorBookVm.totalCount = ret.data.listData.length;
            errorBookVm.errorQuestionList = ret.data.listData;
        }
        // $S.CloseProgress();
        errorBookVm.isLoad = true;
    })
}