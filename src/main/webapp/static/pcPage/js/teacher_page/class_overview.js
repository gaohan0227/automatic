var vm;
    window.onload = function() {
        fnInit();
        fnInitPage();
    }

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                infoList: [],
                isLoad: false,
            },
            mounted: function() {
                this.$nextTick(function() {

                })
            }
        })
    }

    function fnInitPage() {
        var postData = {
            "classId": "ef4214145977428388e8eba822f06753"
        }
       POST("apiQuestion/findClassOverView", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.infoList = ret.data.listData;
            }
            vm.isLoad = true;
        })
    }