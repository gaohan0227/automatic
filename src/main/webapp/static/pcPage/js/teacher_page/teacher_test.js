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
                isLoad: false,
                answerStatus: "0"
            },
            mounted: function() {
                this.$nextTick(function() {
                    // var _headerH = AD('header').offsetH();
                    // $api.css($api.dom(".teacher_test_card"), 'top:' + (_headerH + 95) + 'px;');
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
        var postData = {
            "dynamicMaterialId": "111",
            "bussActivityKindCode": "9",
        }
        POST("apiQuestion/findQuestionCompleteList", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.listData = ret.data.list;
                for (var i = 0; i < ret.data.list.length; i++) {
                    if (ret.data.list[i].doStatus > -1) {
                        vm.answerStatus = '1'
                    }
                }
            }
            vm.isLoad = true;
        })
    }

    function fnBack() {
        fnDelayCloseWin()
    }
    //打开测试
    function fnOpenTestDetail() {
        var tParam = {
            name: "index/teacher_test_detail_win",
            url: "../index/teacher_test_detail.html",
            slidBackEnabled: false,
            pageParam: {
                "mId": "",
            }
        }
        fnOpenWin(tParam);
    }