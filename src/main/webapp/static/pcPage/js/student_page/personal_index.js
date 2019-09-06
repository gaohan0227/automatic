var personalIndexVm;
$(function () {
    fnInit();
    fnGetList();
    fnGetClassId();
});

function fnInit() {
    personalIndexVm = new Vue({
        el: '.personalIndexVue',
        data: {
            isClassList: false,
            isUnitList: false,
            unitTestList: [],
            classList: [],
            classId: "",
            bgColor: [
                'linear-gradient(147deg,#FEBD75 0%,#FE8B8B 100%)',
                'linear-gradient(147deg,#75D0FE 0%,#8BB0FE 100%)',
                'linear-gradient(147deg,#63EBB4 0%,#50E5AB 100%)',
                'linear-gradient(147deg,#FFB5B5 0%,#FB90AB 100%)',
                'linear-gradient(147deg,#B68EFD 0%,#7E72FF 100%)',
            ],
        },
        methods: {},
        watch: {},
    });
}
function fnGetList() {
    var postData = {
        pageNo: '1',
        pageSize: '4',
    };
    POST('apiTeacherLogin/findClassInfoList', postData, function (ret) {
        if (ret && ret.status == '200') {
            personalIndexVm.classList = ret.data.listData;
        }
        // $S.CloseProgress();
        personalIndexVm.isClassList = true;
    });
}
function fnGetClassId() {
    // $S.ShowProgress();
    var postData = {
        "MaterialLevelId": localStorage.studentMaterialLevelId || "",
    }
    POST("apiQuestion/getClassIdByMaterialLevelId", postData, function (ret) {
        if (ret && ret.status == "200") {
            personalIndexVm.classId = ret.data.classId;
            fnTestList()
            _unitTestList.pageData = {
                "classId":ret.data.classId
            }
        }
        // $S.CloseProgress();
    })
}
function fnTestList() {
    // $S.ShowProgress();
    var postData = {
        classId: personalIndexVm.classId,
    };
    POST('apiAppMaterial/findStudentUnitTestList', postData, function (ret) {
        if (ret && ret.status == '200') {
            personalIndexVm.unitTestList = ret.data.listData;
        }
        // $S.CloseProgress();
        personalIndexVm.isUnitList = true;
    });
}
