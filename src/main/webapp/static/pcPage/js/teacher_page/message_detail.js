var vm;
window.onload = function() {
        fnInit();
        fnInitPage();
    }

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                mId:  "",
                mesInfo: ""
            }
        })
    }

    function fnInitPage() {
        var postData = {
            id: vm.mId
        }
        POST("apiTeacherLogin/findMessageInfoDetail", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.mesInfo = ret.data;
                setTimeout(function() {
                    fnAdaptationContent("message_detail")
                }, 500)
            }
        })
    }