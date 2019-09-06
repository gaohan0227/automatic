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
        fnGetMessList();
        fnAddHeaderRefreshingEvent(function() { //设置下拉刷新
            fnGetMessList('headerRefreshing');
        });

        fnAddFooterLoadingEvent(function() { // 设置上拉加载
            fnGetMessList('load');
        });

    }

    function fnGetMessList(pSign) {
        if (pSign != "load") { //刷新
            if (pSign != "headerRefreshing") {
//              $S.ShowProgress();
            }
            vm.pageNo = 1;
        } else { //加载
//          $S.ShowProgress();
        }
        var postData = {
            "kind": "0",
            "pageNo": vm.pageNo,
            "pageSize": "5"
        }
        POST("apiTeacherLogin/findMessageInfoList", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                if (vm.pageNo == '1') {
                    vm.listData = [];
                }
                vm.listData.push.apply(vm.listData, ret.data.listData);
                vm.pageNo++;
            }
//          $S.CloseProgress();
//          api.refreshHeaderLoadDone();
            vm.isLoad = true;
            $(".message_list").find('.message_box').css({
                left: 0,
                'transition': 'left 300ms',
                '-webkit-transition': 'left 300ms'
            }).attr('isShow', false);
            setTimeout(function() {
                $(".message_box").slide2del({
                    sItemClass: ".message_box",
                    sDelBtnClass: ".message_del_btn",
                    // delHandler: function(target) {
                    //     if (target && target.stopPropagation) {
                    //         target.stopPropagation();
                    //     } else {
                    //         window.event.cancelBubble = true;
                    //     };
                    //     console.log("删除")
                    //     var mesId = target.attr("id");
                    //     fnRemoveMess(mesId)
                    // },
                    itemClickHandler: function(target) {}
                });
            }, 500)
        })
    }
    var _delId = "";

    function fnRemoveMess(el, mId) {
        if (_delId == mId) {
            return;
        }
        _delId = mId;
        if (el && el.stopPropagation) {
            el.stopPropagation();
        } else {
            window.event.cancelBubble = true;
        };
//      $S.ShowProgress();
        var postData = {
            "id": mId,
        }
        POST("apiTeacherLogin/deleteMessageInfo", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                fnToast("删除成功");
                for (var i = 0; i < vm.listData.length; i++) {
                    for (var x = 0; x < vm.listData[i].messageInfoList.length; x++) {
                        if (vm.listData[i].messageInfoList[x].id == mId) {
                            vm.listData[i].messageInfoList.splice(x, 1);
                        }
                    }
                    if (vm.listData[i].messageInfoList.length == '0') {
                        vm.listData.splice(i, 1);
                    }
                }
                $(".message_list").find('.message_box').css({
                    left: 0,
                    'transition': 'left 300ms',
                    '-webkit-transition': 'left 300ms'
                }).attr('isShow', false);
            } else {
                _delId = "";
            }
//          $S.CloseProgress();
        })
    }

    function fnOpenMessageDetail(e) {
//      var isLeft = $(e).attr('isShow');
//      console.log(isLeft+"---")
//      if (isLeft == "true") {
//          return;
//      }
//      var pageParam = {
//          "mId": $api.attr(e, 'id')
//      }
//      for (var i = 0; i < vm.listData.length; i++) {
//          for (var x = 0; x < vm.listData[i].messageInfoList.length; x++) {
//              if (vm.listData[i].messageInfoList[x].id == pageParam.mId) {
//                  vm.listData[i].messageInfoList[x].ifRead = '1';
//              }
//          }
//      }
//      fnOpenPublicWin("详情页面", "mine/message_detail", pageParam)
    }