var learnReportVm;
    $(function () {
        fnInit();
        fnGetClassId();
        fnGetDubReport();
        fnGetTopicReport();
    })
    function fnInit() {
        learnReportVm = new Vue({
            el: '.learnReportVue',
            data: {
                classId: "",
                studentId: localStorage.custId,
                unitId: localStorage.unitId,
                newIndex: "0",
                dubReportInfo: "",
                taskReportInfo: "",
                testReportInfo: "",
                testScore: "",
                topicTotalDuration: "",
                topicCompletionDegree: "",
                dubIsLoad: false,
                taskIsLoad: false,
                testIsLoad: false,
                topicIsLoad: false,
                bgColor: ['linear-gradient(90deg,rgba(104,175,255,1) 0%,rgba(97,214,255,1) 100%)', 'linear-gradient(90deg,rgba(252,143,147,1) 0%,rgba(255,196,120,1) 100%)', 'linear-gradient(270deg,rgba(255,229,125,1) 0%,rgba(255,177,99,1) 100%)', 'linear-gradient(90deg,rgba(87,210,154,1) 0%,rgba(65,224,190,1) 100%)']
            },
            methods: {
                fnSetIndex: function (key) {
                    this.newIndex = key;
                }
            }
        });
    };
    function fnGetClassId() {
        // $S.ShowProgress();
        var postData = {
            "MaterialLevelId": localStorage.studentMaterialLevelId || "",
        }
        POST("apiQuestion/getClassIdByMaterialLevelId", postData, function (ret) {
            if (ret && ret.status == "200") {
                learnReportVm.classId = ret.data.classId || "";
                if (learnReportVm.classId != "") {
                    fnGetTaskReport();
                    fnGetUnitQuestionId();
                } else {
                    learnReportVm.taskIsLoad = true;
                    learnReportVm.testIsLoad = true;
                }
            }
            // $S.CloseProgress();
        })
    }
    function fnGetDubReport() {
        // $S.ShowProgress();
        var postData = {
            "studentId": learnReportVm.studentId,
            "materialUnitId": learnReportVm.unitId,
        }
        POST("AppModule/dueReport", postData, function (ret) {
            if (ret && ret.status == "200") {
                learnReportVm.dubReportInfo = ret.data;
            }
            // $S.CloseProgress();
            learnReportVm.dubIsLoad = true;
        })
    }
    function fnGetTaskReport() {
        // $S.ShowProgress();
        var postData = {
            "studentId": learnReportVm.studentId || "",
            "unitId": learnReportVm.unitId || "",
            "classId": learnReportVm.classId
        }
        POST("apiQuestion/findMyTaskReport", postData, function (ret) {
            if (ret && ret.status == "200") {
                learnReportVm.taskReportInfo = ret.data.listData;
            };
            // $S.CloseProgress();
            learnReportVm.taskIsLoad = true;
        })
    }
    function fnGetUnitQuestionId() {
        // $S.ShowProgress();
        var postData = {
            "classId": learnReportVm.classId,
            "unitId": learnReportVm.unitId,
        }
        POST("apiQuestion/getASetUnitQuestionIdByClassIdAndUnitId", postData, function (ret) {
            if (ret && ret.status == "200") {
                fnGetUnitReport(ret.data.aSetUnitQuestionId)
            }
            // $S.CloseProgress();
        })
    }
    function fnGetUnitReport(qId) {
        // $S.ShowProgress();
        var postData = {
            "studentId": learnReportVm.studentId,
            "dynamicMaterialId": learnReportVm.unitId,
            "bussActivityKindCode": "3",
            "setUnitQuestionId": qId,
            "studentMaterialLevelId": localStorage.studentMaterialLevelId || ""
        }
        POST("apiQuestion/findQuestionCompleteList", postData, function (ret) {
            if (ret && ret.status == "200") {
                learnReportVm.testReportInfo = ret.data.list;
                learnReportVm.testScore = ret.data.score;
            }
            // $S.CloseProgress();
            learnReportVm.testIsLoad = true;
        })
    }
    function fnGetTopicReport() {
        // $S.ShowProgress();
        var postData = {
            "unitId": learnReportVm.unitId || "",
        }
        POST("apiQuestion/findMyRushThroughCustomsReport", postData, function (ret) {
            if (ret && ret.status == "200") {
                learnReportVm.topicTotalDuration = ret.data.totalDuration > 0 ? ret.data.totalDuration : '0';
                learnReportVm.topicCompletionDegree = ret.data.completionDegree > 0 ? ret.data.completionDegree : '0';
            }
            // $S.CloseProgress();
            learnReportVm.topicIsLoad = true;
        })
    }