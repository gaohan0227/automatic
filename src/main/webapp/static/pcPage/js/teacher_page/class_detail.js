var vm;
var pageData = getPageData();
   $(function() {
        fnInit();
        fnGetClassInfo();
    });
    //初始化
    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                classInfo: "",
            }
        })
    }
    //获取班级信息
    function fnGetClassInfo() {
        var postData = {
            id: pageData.classId
        }
       POST("apiTeacherLogin/getClassInfo", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.classInfo = ret.data;
            }
        })
    };

    function fnOpenClassInfo() {
        var pageParam = {
            classType: "exit",
            classId: api.pageParam.classId,
            classLogoUrl: vm.classInfo.classLogoUrl,
            classNames: vm.classInfo.classNames,
            materialName: vm.classInfo.materialName,
            materialLevelName: vm.classInfo.materialLevelName,
            classNum: vm.classInfo.classNum,
            createDate: vm.classInfo.createDate,
        }
        fnOpenPublicWin("班级信息", "index/add_class", pageParam)
    }

    function fnBack() {
        fnDelayCloseWin()
    }

    function fnChangeStudent() {
        var pageData = {
            classId: api.pageParam.classId
        }
        fnOpenPublicWin("选择学生", "index/change_student", pageData)
    }

    function fnOpenStudentInfo(sId) {
        var pageData = {
            "classId": api.pageParam.classId,
            "studentId": sId
        }
        fnOpenPublicWin("", "index/student_info", pageData)
    }

    function fnAddStudent() {
        var pageData = {
            "classId": api.pageParam.classId,
            "isBtn": true,
            "btnType": "title",
            "btnName": "确定",
            "btnColor":"#acacac",
            "headerFn": "fnAddStudent",
        }
        fnOpenPublicWin("新增学生", "index/add_student", pageData)
    }