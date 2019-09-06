var vm;
    
 window.onload = function() {
        fnInit();
        fnInitPage();
    }

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                classId: "",
                studentId: "",
                unitId: "",
                unitName: "",
                reportInfo: "",
                isLoad: false,
            },
        })
    }

    function fnInitPage() {
        var postData = {
            "studentId": vm.studentId,
            "materialUnitId": vm.unitId,
        }
       POST("AppModule/dueReport", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.reportInfo = ret.data;
            }
            vm.isLoad = true;
        })
    }

    function fnBack() {
        fnDelayCloseWin()
    }