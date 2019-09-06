var personalCenterVm;
var _calendar = { "pageName": "签到日历", "pageUrl": "mine/calendar", "pageJs": "calendar", "pageData": "", "nextPage": "" };
var _myTaskList = { "pageName": "我的作业", "pageUrl": "mine/task_list", "pageJs": "task_list", "pageData": "", "nextPage": "" };
var _myErrorList = { "pageName": "我的错题", "pageUrl": "mine/error_book", "pageJs": "error_book", "pageData": "", "nextPage": "" };
var _myDubList = { "pageName": "我的配音", "pageUrl": "mine/my_dub_list", "pageJs": "my_dub_list", "pageData": "", "nextPage": "" };
var _learnReport = { "pageName": "学习报告", "pageUrl": "mine/learn_report", "pageJs": "learn_report", "pageData": "", "nextPage": "" };
var _unitTestList = { "pageName": "单元测试", "pageUrl": "mine/unit_test_list", "pageJs": "unit_test_list", "pageData": "", "nextPage": "" };
var _shiftTest = { "pageName": "插班测试", "pageUrl": "mine/shift_test", "pageJs": "shift_test", "pageData": "", "nextPage": "" };
var _classList = { "pageName": "我的班级", "pageUrl": "mine/class_list", "pageJs": "class_list", "pageData": "", "nextPage": "" };
var _messageList = { "pageName": "消息通知", "pageUrl": "mine/message_list", "pageJs": "message_list", "pageData": "", "nextPage": "" };
var _setting = { "pageName": "安全设置", "pageUrl": "mine/security_setting", "pageJs": "security_setting", "pageData": "", "nextPage": "" };
$(function () {
    fnInit();
    fnInitPage();
    fnLoadPersonalPage();
})
function fnLoadPersonalPage(){
    if(headerVm.pageNavigation.length>0){
        var lastPageUrl = headerVm.pageNavigation[headerVm.pageNavigation.length - 1].pageUrl;
        var _nextPageUrl = headerVm.pageNavigation[headerVm.pageNavigation.length - 1].nextPage;
        var isNextpage = false;
        console.log(lastPageUrl);
        if (lastPageUrl == 'mine/personal_center') {
            for (var i = 0; i < personalCenterVm.personalInfoList.length; i++) {
                if (personalCenterVm.personalInfoList[i].pageUrl == _nextPageUrl) {
                    isNextpage = true;
                    fnOpenNextPage(i);
                }
            }
        }
        if (!isNextpage) {
            fnLoadPageAndJs("personalPage", "mine/personal_index", "personal_index");
        }
    }
}
function fnInit() {
    personalCenterVm = new Vue({
        el: ".personalInfoVue",
        data: {
            isLoad: false,
            materialInfoId: localStorage.materialInfoId || "", //教材id
            materialName: localStorage.materialName || "", //教材名称
            studentMaterialLevelId: localStorage.studentMaterialLevelId || "", //教材等级id
            materialLevelName: localStorage.materialLevelName || "", //教材等级名称
            userInfo: "",
            currentLevelList: [],
            currentLevelIndex: "0",
            currentLevelWidth: "0%",
            personalInfoList: [_calendar, _myTaskList, _myErrorList, _myDubList, _learnReport, _unitTestList, _shiftTest, _classList, _messageList, _setting],
        },
    })
}

function fnSetMaterial() {
    personalCenterVm.materialInfoId = localStorage.materialInfoId || ""; //教材id
    personalCenterVm.materialName = localStorage.materialName || ""; //教材名称
    personalCenterVm.studentMaterialLevelId = localStorage.studentMaterialLevelId || ""; //教材等级id
    personalCenterVm.materialLevelName = localStorage.materialLevelName || ""; //教材等级名称
}
//获取用户信息
function fnInitPage() {
    fnSetMaterial();
    // $S.ShowProgress();
    POST("apiTeacherLogin/getStudentInfo", "", function (ret) {
        if (ret && ret.status == "200") {
            ret.data.integralReal = ret.data.integralReal || "0";
            ret.data.integral = ret.data.integral || "0";
            personalCenterVm.userInfo = ret.data;
            _setting.pageData = {"tel":ret.data.telephoneNum};
            fnGetIntegralList();
        }
        // $S.CloseProgress();
    })
};
var _isHaveLevel = false;

function fnGetIntegralList() {
    // $S.ShowProgress();
    POST("apiTeacherLogin/findIntegerlist", "", function (ret) {
        if (ret && ret.status == "200") {
            personalCenterVm.currentLevelList = ret.data.listData;
            _isHaveLevel = false;
            if (personalCenterVm.userInfo.integralReal && personalCenterVm.userInfo.integralReal > 0) {
                for (var i = 0; i < personalCenterVm.currentLevelList.length; i++) {
                    if (personalCenterVm.userInfo.integralReal >= personalCenterVm.currentLevelList[i].startNum && personalCenterVm.userInfo.integralReal <= personalCenterVm.currentLevelList[i].endNum) {
                        _isHaveLevel = true;
                        personalCenterVm.currentLevelIndex = i;
                        personalCenterVm.currentLevelWidth = Math.round((personalCenterVm.userInfo.integralReal - personalCenterVm.currentLevelList[i].startNum) / (personalCenterVm.currentLevelList[i].endNum - personalCenterVm.currentLevelList[i].startNum) * 100) + "%";
                    }
                }
                if (!_isHaveLevel) {
                    personalCenterVm.currentLevelIndex = 4;
                    personalCenterVm.currentLevelWidth = "100%";
                }
            }

        }
        personalCenterVm.isLoad = true
        // $S.CloseProgress();
    })
}
function fnOpenNextPage(key) {
    headerVm.pageNavigation.splice(1);
    personalCenterVm.personalInfoList[key].isMine = '1';
    headerVm.pageNavigation.push(personalCenterVm.personalInfoList[key]);
    headerVm.indexPageUrl = personalCenterVm.personalInfoList[key].pageUrl;
    fnLoadPageAndJs("personalPage", personalCenterVm.personalInfoList[key].pageUrl, personalCenterVm.personalInfoList[key].pageJs);
    // setTimeout(function () {
    //     $(".personalPage").load("../html/" + personalCenterVm.personalInfoList[key].pageUrl + ".html");
    //     fnLoadJS("../js/student_page/" + personalCenterVm.personalInfoList[key].pageJs + ".js");
    // }, 100)
}

function fnOpenNextPageUrl(pageName, pageUrl, pageJs, pageData, nextPage) {
    if(pageUrl=='mine/personal_info'){
        headerVm.pageNavigation.splice(1);
    }
    pageName = pageName || "";
    pageUrl = pageUrl || "";
    pageJs = pageJs || "";
    pageData = pageData || "";
    nextPage = nextPage || "";
    var pageInfo = { "pageName": pageName, "pageUrl": pageUrl, "pageJs": pageJs, "pageData": pageData, "nextPage": nextPage,"isMine":'1' };
    headerVm.pageNavigation.push(pageInfo);
    headerVm.indexPageUrl = pageUrl;
    // setTimeout(function () {
    //     $(".personalPage").load("../html/" + pageUrl + ".html");
    //     fnLoadJS("../js/student_page/" + pageJs + ".js");
    // }, 100)
    fnLoadPageAndJs("personalPage", pageUrl, pageJs);
}