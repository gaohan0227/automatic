var taskListVm;
    $(function () {
        fnInit();
        fnGetTaskList();
        fnGetUnitList(taskListVm);
    })

    function fnInit() {
        taskListVm = new Vue({
            el: ".taskListVue",
            data: {
                taskList: [],
                isLoad:false,
                unitId:localStorage.unitId|| "",
                unitList:JSON.stringify(localStorage.unitList)|| [],
                unitName: localStorage.unitName|| "",
            },
        })
    }
    function fnGetTaskList() {
        // $S.ShowProgress();
        var postData = {
            "unitId": taskListVm.unitId,
            "pageNo": "1",
            "pageSize": "10000"
        }
        POST("apiTeacherLogin/findMyWorkList", postData, function (ret) {
            if (ret && ret.status == "200") {
                taskListVm.taskList = ret.data.listData;
            }
            // $S.CloseProgress();
            taskListVm.isLoad = true;
        })
    }