var vm;
   
 $(function() {
        fnInit();
        fnInitPage();
    });

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                listData: [],
                materialInfoId: "",
                isLoad: false,
            },
            mounted: function() {
                this.$nextTick(function() {
                    var _headerH = AD('header').offsetH();
//                  $api.css($api.dom(".resource_list_card"), 'top:' + (_headerH + 6) + 'px;');
                })
            },
            filters: {
                fnIndexNum: function(i) {
                    return i + 1;
                }
            }
        })
    }

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
//      $S.ShowProgress();
        // }
        var postData = {
            "materialInfoId": "d429439449b043198992d82f74c68b69",
            "pageNo": vm.pageNo,
            "pageSize": "10"
        }
        POST("apiAppTeacher/findTeacherMoudleList", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                // if (vm.pageNo == '1') {
                //     vm.listData = [];
                // }
                // vm.listData.push.apply(vm.listData, ret.data.listData);
                // vm.pageNo++;
                vm.listData = ret.data.listData;
            }
//          $S.CloseProgress();
            // api.refreshHeaderLoadDone();
            vm.isLoad = true;
        })
    }

    function fnOpenResourceDetail(rId,rName) {
        var pageParam = {
            "rId": rId,
            "rName":rName
        }
        fnOpenPublicWin("", "index/resource_detail", pageParam)
    }

    function fnBack() {
        fnDelayCloseWin()
    }