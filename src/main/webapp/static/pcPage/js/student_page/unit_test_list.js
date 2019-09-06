var unitTestListVm;
$(function () {
    fnInit();
    fnTestList();
})

function fnInit() {
    unitTestListVm = new Vue({
        el: ".unitTestVue",
        data: {
            isLoad: false,
            classId: headerVm.pageNavigation[headerVm.pageNavigation.length - 1].pageData.classId,
            unitTestList: []
        },
        methods: {

        },
    })
}
function fnTestList() {
    // $S.ShowProgress();
    var postData = {
        classId: unitTestListVm.classId,
    };
    POST('apiAppMaterial/findStudentUnitTestList', postData, function (ret) {
        if (ret && ret.status == '200') {
            unitTestListVm.unitTestList = ret.data.listData;
        }
        // $S.CloseProgress();
        unitTestListVm.isLoad = true;
    });
}