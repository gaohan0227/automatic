var vm;
    
 window.onload =function() {
        fnInit();
        fnInitPage();
    }

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                listData:[],
                classId:"",
                studentId: "",
                unitId:"",
                unitName: "",
                isLoad:false
            },
        })
    }
    function fnInitPage() {
        var postData = {
            "studentId":vm.studentId,
            "unitId": vm.unitId,
            "classId": vm.classId,
            "pageNo":"1",
            "pageSize":"10000"
        }
        POST("apiQuestion/findMyTaskReport", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.listData = ret.data.listData;
            } else {
                vm.listData = [];
            }
            vm.isLoad = true;
        })
    }
    function fnBack() {
        fnDelayCloseWin()
    }