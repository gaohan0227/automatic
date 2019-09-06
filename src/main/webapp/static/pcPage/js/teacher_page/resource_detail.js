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
                answerStatus:"0",
                name:"",
                bgColor: ["#FF7474", "#FFA174", "#F6D45C", "#9AE679", "#89C5FE", "#C794FE", "#FF91E1"],
                isLoad: false,
                isLock: false,
            },
            mounted: function() {
                this.$nextTick(function() {
                    var _headerH = AD('header').offsetH();
//                  $api.css($api.dom(".resource_detail_card"), 'top:' + (_headerH + 16) + 'px;');
                })
            }
        })
    }

    function fnInitPage() {
//      $S.ShowProgress();
        var postData = {
            "parentMaterialModuleId": "72e6402228b24b80a3bdb04229a69059"
        }
        POST("apiAppTeacher/findTeacherChildMoudleList", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.listData = ret.data.listMap;
                vm.answerStatus = ret.data.answerStatus;
                fnIsLock();
            }
//          $S.CloseProgress();
            vm.isLoad = true;
        })
    }

    function fnBack() {
        fnDelayCloseWin()
    }

    function fnOpenTeacherTest() {
        var pageParam = {
            "mId": "",
        }
        fnOpenPublicWin("", "index/teacher_test",pageParam)
    }

    function fnIsLock() {
        var _isLock = true;
        for (var i = 0; i < vm.listData.length; i++) {
            if (vm.listData[i].ifDeblovk != '1') {
                _isLock = false;
            }
        }
        vm.isLock = _isLock;
    }
    function fnOpenDetail(name, rId) {
        var pageData = {
            "id": rId,
        };
        fnOpenPublicWin(name, "index/video_deatil", pageData)
    }