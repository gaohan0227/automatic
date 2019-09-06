var vm;
$(function () {
    fnInitDub();
    fnInitPage();
});
function fnInitDub() {
    vm = new Vue({
        el: '.dubShowVue',
        data: {
            studentMaterialLevelId: localStorage.studentMaterialLevelId|| "",
            bookList: "",
            manageList: [],
            isLoad:false,
            size:5
        },
    });
}
function fnInitPage() {
    var callback  = function(ret) {
        if (ret && ret.status == "200") {
            vm.bookList = ret.data.listData;
            fnArrManage();
        }
    }
    POST("apiAppMaterial/findMaterialList", "",callback)
}

function fnArrManage() {
    var _resultArr = [];
    for (var x = 0; x < Math.ceil(vm.bookList.length / vm.size); x++) {
        var start = x * vm.size;
        var end = start + vm.size;
        _resultArr.push(vm.bookList.slice(start, end));
    }
    vm.manageList = _resultArr;
    vm.isLoad = true;
    console.log(JSON.stringify(vm.manageList));
}
function fnAddMaterial() {
    if (fnVerify("alert_input")) {
        var postData = {
            "materialSequenceNum": $("#alert_input input").val()
        }
        POST("apiAppMaterial/addMaterial", postData, function(ret) {
            if (ret && ret.status == "200") {
               fnToast("添加成功");
               fnInitPage();
               fnCloseInputAlert();
            }
        })
    }
}

function fnChangeBook(levelId) {
        if(levelId==vm.studentMaterialLevelId){
            return;
        }
        fnOpenAlert("是否确认切换该教材？","fnChangeMaterial('"+levelId+"')")
    }

    function fnChangeMaterial(levelId) {
        localStorage.studentMaterialLevelId = levelId;
        vm.studentMaterialLevelId = localStorage.studentMaterialLevelId;
        fnCloseAlert();
    }