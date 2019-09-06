    var vm;
    var classId;
    $(function() {
        fnInit();
        fnInitPage();
    });

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                studentList: [],
                changeList: [],
                isCheckAll: false,
                pageNo: "1",
                isLoad: false,
            },
            mounted: function() {
                this.$nextTick(function() {

                })
            },
            methods: {
                fnCheckAll: function() {
                    this.isCheckAll = !this.isCheckAll;
                    if (this.isCheckAll) {
                        var _checkList = [];
                        for (var i = 0; i < this.studentList.length; i++) {
                            var _item = {
                                id: this.studentList[i].id
                            }
                            _checkList.push(_item)
                        }
                        this.changeList = _checkList;
                    } else {
                        this.changeList = [];
                    }
                    // api.sendEvent({
                    //     name: 'isCheckAll',
                    //     extra: {
                    //         isCheckAll: vm.isCheckAll,
                    //     }
                    // });
                },
                fnIsChecked: function(sId) {
                    for (var i = 0; i < this.changeList.length; i++) {
                        if (this.changeList[i].id == sId) {
                            return i;
                        }
                    }
                    return -1;
                },
                fnCheckStudent: function(sId) {
                    var _sIndex = this.fnIsChecked(sId);
                    if (_sIndex > -1) {
                        this.changeList.splice(_sIndex, 1);
                    } else {
                        var _sItem = {
                            id: sId
                        }
                        this.changeList.push(_sItem)
                    }
                    if (this.changeList.length == this.studentList.length) {
                        this.isCheckAll = true;
                    } else {
                        this.isCheckAll = false;
                    }
                    // api.sendEvent({
                    //     name: 'isCheckAll',
                    //     extra: {
                    //         isCheckAll: vm.isCheckAll,
                    //     }
                    // });
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
        //     $S.ShowProgress();
        // }
        var postData = {
            "classId": classId,
            "pageNo": vm.pageNo,
            "pageSize": "10000000"
        }
        POST("apiTeacherLogin/findClassByStudentInfo", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                if (vm.pageNo == '1') {
                    vm.studentList = [];
                    vm.changeList = [];
                    vm.isCheckAll = false;
                    // api.sendEvent({
                    //     name: 'isCheckAll',
                    //     extra: {
                    //         isCheckAll: vm.isCheckAll,
                    //     }
                    // });
                }
                vm.studentList.push.apply(vm.studentList, ret.data.listData);
                console.log(JSON.stringify(vm.studentList))
                if (!vm.isLoad) {
                    fnOpenBottomFrame();
                }
                vm.pageNo++;
                if (vm.isCheckAll) {
                    var _checkList = [];
                    for (var i = 0; i < vm.studentList.length; i++) {
                        var _item = {
                            id: vm.studentList[i].id
                        }
                        _checkList.push(_item)
                    }
                    vm.changeList = _checkList;
                }
            }
            // $S.CloseProgress();
            // api.refreshHeaderLoadDone();
            // vm.isLoad = true;
            // api.setFrameAttr({
            //     name: 'index/change_student_bottom',
            //     hidden: vm.studentList.length > 0 ? false : true
            // });

        })
    }

    function fnOpenUnitTest() {
        fnOpenPublicWin("", "release/change_student")
    }
    //修改当前的frame的大小以及打开底部的frame
    function fnOpenBottomFrame() {
        // var bottom_h = $api.offset($api.dom(".change_student_bottom")).h;
        // var _headerH = api.pageParam.headerH;
        // var _footerH = api.pageParam.footerH;
        // api.setFrameAttr({
        //     name: api.frameName,
        //     rect: {
        //         x: 0,
        //         y: _headerH,
        //         w: "auto",
        //         h: api.winHeight - _headerH - _footerH - bottom_h
        //     }
        // });
        // var param = {
        //     name: "index/change_student_bottom",
        //     url: './change_student_bottom.html',
        //     useWKWebView: true,
        //     bgColor: 'rgba(0,0,0,0)',
        //     rect: {
        //         x: 0,
        //         y: api.winHeight - _footerH - bottom_h,
        //         w: "auto",
        //         h: bottom_h
        //     },
        //     pageParam: {
        //         studentList: vm.studentList
        //     }
        // }
        // fnOpenFrame(param);
        // $api.css($api.dom(".change_student_bottom"), "height:0;");

    }

    function fnConfirmAdd() {
        var postData = {
            "classId": classId,
            "studentList": vm.changeList
        }
        POST("apiTeacherLogin/selectStudentInfo", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                fnToast("添加成功");
                // api.execScript({
                //     name: 'index/class_detail_win',
                //     frameName: 'index/class_detail',
                //     script: 'fnGetClassInfo();'
                // });
                // fnDelayCloseWin(800)
            }
        })
    }