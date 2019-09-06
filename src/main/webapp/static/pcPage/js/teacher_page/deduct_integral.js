var vm;
  $(function() {
        fnInit();
    });
    //初始化
    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                integral: "",
            },
            mounted: function() {
                this.$nextTick(function() {
                    //搜索
                    if (document.getElementById('search_from')) {
                        document.getElementById('search_from').onsubmit = function() {
                            fnAlertDeduct();
                        };
                    }
                })
            },
            methods: {
                fnClearVal: function() {
                    this.integral = ""
                }
            }
        })
    }

    function fnAlertDeduct() {
        fnKeyboard();
        if (fnVerify('phone_input')) {
            var param = {
                alertFn: "fnDeductIntegral()",
                alertType: "4"
            }
            fnOpenAlert(param)
        }
    }

    function fnSaveInfo() {
        if (vm.add_student_type == 'classname' && api.pageParam.classType == 'add') {
            api.sendEvent({
                name: 'classname',
                extra: {
                    classname: vm.integral
                }
            });
        } else {

        }
        fnDelayCloseWin()
    }

    function fnDeductIntegral() {
//      $S.ShowProgress();
        var postData = {
            "studentId": "034f2d37b12e4aaf837d776512d9e4b5",
            "integralNum":vm.integral
        }
        POST("apiTeacherLogin/deductionIntegral", postData, function(ret) {
            if (ret && ret.status == "200") {
                fnToast("积分扣除成功");
            }
        })
    }