var vm;
    $(function() {
        fnInit();
    });
    //初始化
    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                studentPhone: "",
            },
            mounted: function() {
                this.$nextTick(function() {
                    //搜索
                    if (document.getElementById('search_from')) {
                        document.getElementById('search_from').onsubmit = function() {
                            fnAddStudent();
                        };
                    }
                })
            },
            methods: {
                fnClearVal: function() {
                    this.studentPhone = ""
                }
            }
        })
    }

    function fnSaveInfo() {
        // if (vm.add_student_type == 'classname' && api.pageParam.classType == 'add') {
        //     api.sendEvent({
        //         name: 'classname',
        //         extra: {
        //             classname: vm.studentPhone
        //         }
        //     });
        // } else {

        // }
        // fnDelayCloseWin()
    }
    var isClick = false;
    function fnAddStudent() {
        if (!isClick) {
            isClick = true;
            if (!fnVerify('phone_input')) {
                isClick = false;
                return;
            }
            var postData = {
                "classId": api.pageParam.classId,
                "telephone": vm.studentPhone
            }
            POST("apiTeacherLogin/addStudentInfo", postData, function(ret, err) {
                if (ret && ret.status == "200") {
                    fnToast("添加成功");
                    api.execScript({
                        name: 'index/class_detail_win',
                        frameName: 'index/class_detail',
                        script: 'fnGetClassInfo();'
                    });
                    fnDelayCloseWin(800)
                } else {
                    isClick = false;
                }
            })
        }
    }