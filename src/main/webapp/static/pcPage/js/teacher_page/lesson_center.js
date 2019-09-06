var vm;
    var size = 3;
     $(function() {
        fnInit();
        fnInitPage();
    });

    function fnInitPage() {
        fnGetList();
        // fnAddHeaderRefreshingEvent(function() { //设置下拉刷新
        //     fnGetList('headerRefreshing');
        // });
        // fnAddFooterLoadingEvent(function() { // 设置上拉加载
        //     fnGetList('load');
        // });
    }

    function fnGetList(pSign) {
        // if (pSign != "load") { //刷新
        //     if (pSign != "headerRefreshing") {
        //         $S.ShowProgress();
        //     }
        //     vm.pageNo = 1;
        // } else { //加载
//          $S.ShowProgress();
        // }
           var postData = {
               "pageNo": vm.pageNo,
               "pageSize": "10"
           }
       POST("apiAppTeacher/findTeacherMaterialList", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                if (vm.pageNo == '1') {
                    vm.listData = [];
                }
                vm.listData.push.apply(vm.listData, ret.data.listData);
                fnArrManage();
                // vm.pageNo++;
            }
            vm.isLoad = true;
        })
    }
    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                listData: [],
                manageList: [],
                isLoad:false,
                pageNo:"1"
            },
            mounted: function() {
                
            },
            filters: {
                fnIndexNum: function(i) {
                    return i + 1;
                }
            }
        })
    }

    function fnArrManage() {
        var _resultArr = [];
        for (var x = 0; x < Math.ceil(vm.listData.length / size); x++) {
            var start = x * size;
            var end = start + size;
            _resultArr.push(vm.listData.slice(start, end));
        }
        vm.manageList = _resultArr;
        console.log(JSON.stringify(vm.manageList))
    }

    function fnOpenResource(mId) {
        var pageParam = {
            "mId":mId,
        }
        fnOpenPublicWin("", "index/resource_list",pageParam)
    }

    function fnBack() {
        fnDelayCloseWin()
    }