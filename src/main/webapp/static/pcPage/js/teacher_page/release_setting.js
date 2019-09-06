    
    var vm;
    $(function() {
        fnInit();
        // api.addEventListener({
        //     name: 'select_value'
        // }, function(ret, err) {
        //     if (ret) {
        //         vm.duration = ret.value.select_value
        //     }
        // });
        // api.addEventListener({
        //     name: 'chooseDate'
        // }, function(ret, err) {
        //     if (ret) {
        //         vm.releaseTime = ret.value.date
        //     }
        // });
        // api.addEventListener({
        //     name: 'setClass'
        // }, function(ret, err) {
        //     if (ret) {
        //         vm.classId = ret.value.classId;
        //         vm.className = ret.value.className;
        //         vm.unitId = ret.value.unitId;
        //         vm.unitName = ret.value.unitName;
        //     }
        // });
    });

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                classId: "",
                className: "",
                unitId: "",
                unitName: "",
                duration: "",
                releaseTime: formatTime(new Date(), "release_date")
            },
            mounted: function() {
                this.$nextTick(function() {

                })
            }
        })
    }

    function fnChangeUnitTest() {
        if (vm.classId == "") {
            fnToast("请选择班级");
            return;
        } else if (vm.duration == "") {
            fnToast("请选择考试时长");
            return;
        }
        var pageData = {
            classId: vm.classId,
            testTime: vm.duration,
            unitId: vm.unitId,
            testBeginDate: vm.releaseTime,
        }
        fnOpenPublicWin("单元测试", "release/change_unit_test", pageData)
    }

    function fnChangeClass() {
        var pageData = {
            classId: vm.classId,
            unitId: vm.unitId,
            unitName: vm.unitName,
        }
        fnOpenPublicWin("班级列表", "release/class_list", pageData)
    }

    // 选择时长
    function fnOpenSelectDur() {
        var param = {
            name: "select_duration",
            url: "../select_duration.html",
            useWKWebView: true,
            pageParam: {
                val: vm.duration,
                type: "分钟"
            }
        }
        fnOpenFrame(param)
    }

    // 选择日期
    function fnOpenChangeDate() {
        var param = {
            name: "choose_date",
            url: "../choose_date.html",
            pageParam: {
                releaseTime: formatTime(vm.releaseTime, 'release_date')
            }
        }
        fnOpenFrame(param)
    }