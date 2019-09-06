var vm;
 window.onload = function() {
        fnInit();
        fnInitPage();
    }

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                listData: [],
                pageNo: "1",
                isLoad: false
            }
        })
    }

    function fnInitPage() {
        fnGetList();
        fnAddHeaderRefreshingEvent(function() { //设置下拉刷新
            fnGetList('headerRefreshing');
        });

        fnAddFooterLoadingEvent(function() { // 设置上拉加载
            fnGetList('load');
        });

    }

    function fnGetList(pSign) {
        if (pSign != "load") { //刷新
            if (pSign != "headerRefreshing") {
//              $S.ShowProgress();
            }
            vm.pageNo = 1;
        } else { //加载
//          $S.ShowProgress();
        }
        var postData = {
            "pageNo": vm.pageNo,
            "pageSize": "10"
        }
       POST("apiTeacherLogin/findIntegralHistory", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                if (vm.pageNo == '1') {
                    vm.listData = [];
                }
                vm.listData.push.apply(vm.listData, ret.data.listData);
                console.log(JSON.stringify(vm.listData))
                vm.pageNo++;
            }
//          api.refreshHeaderLoadDone();
            vm.isLoad = true;
        })
    }