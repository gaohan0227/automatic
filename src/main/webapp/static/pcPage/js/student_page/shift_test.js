var shiftTestVm;
    $(function () {
        fnInit();
        // api.addEventListener({
        //     name: 'openPreview'
        // }, function (ret) {
        //     if (ret) {
        //         if (ret.value.bussActivityKindCode == '7') {
        //             shiftTestVm.listData = ret.value.questionList;
        //             shiftTestVm.params = ret.value.params;
        //             shiftTestVm.isPreview = true;
        //         }
        //     }
        // });
        // api.addEventListener({
        //     name: 'submitTest'
        // }, function (ret) {
        //     if (ret) {
        //         shiftTestVm.fnGetTest();
        //     }
        // });
    })

    function fnInit() {
        shiftTestVm = new Vue({
            el: ".shiftTestVue",
            data: {
                listData: [],
                studentMaterialLevelId: localStorage.studentMaterialLevelId || "",
                score: "0",
                isLoad: false,
                isDoIt: false,
                params: "",
                isPreview: false,
                testTime: ""
            },
            mounted: function () {
                this.fnGetTest();
            },
            methods: {
                fnGetTest: function () {
                    // $S.ShowProgress();
                    var postData = {
                        "dynamicMaterialId": localStorage.studentMaterialLevelId || "",
                        "bussActivityKindCode": "7",
                        "studentMaterialLevelId": localStorage.studentMaterialLevelId || ""
                    }
                    POST("apiQuestion/findQuestionCompleteList", postData, function (ret) {
                        if (ret && ret.status == "200") {
                            for (var i = 0; i < ret.data.list.length; i++) {
                                ret.data.list[i].isDo = false;
                                if (ret.data.list[i].doStatus > -1) {
                                    shiftTestVm.isDoIt = true;
                                }
                            }
                            shiftTestVm.listData = ret.data.list;
                            shiftTestVm.score = ret.data.score;
                            shiftTestVm.testTime = ret.data.testTime;
                        }
                        // $S.CloseProgress();
                        shiftTestVm.isLoad = true;
                    })
                }
            },
            filters: {
                fnIndexNum: function (i) {
                    return i + 1;
                }
            }
        })
    }
    function fnOpenShiftTest() {
        openQuestionWin(shiftTestVm.studentMaterialLevelId, '7', '', '', shiftTestVm.testTime);
    }

    function fnContinue(key) {
        openQuestionListWin(shiftTestVm.params, shiftTestVm.listData, key)
    }
    //提交测试
    function fnSubmitShift() {
        var param = {
            alertFn: "fnSubmitShiftTest()",
            alertType: "8"
        }
        fnOpenAlert(param);
    }

    function fnSubmitShiftTest() {
        // $S.ShowProgress();
        var postData = {
            "whetherTime": "0",
            "questionResultList": shiftTestVm.listData
        }
        POST('apiQuestion/saveQuestionResult', postData, function (ret) {
            if (ret && ret.status == "200") {
                shiftTestVm.fnGetTest();
            }
            // $S.CloseProgress();
        });
    }
