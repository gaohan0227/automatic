var vm;
$(function() {
            fnInit();
            fnInitPage();
        });

        function fnInit() {
            vm = new Vue({
                el: ".vueApp",
                data: {
                    questionList: [],
                    questionIdList:  [],
                    isCheckAll: false,
                    pageNo: "1",
                    isLoad: false,
                },
                methods: {
                    fnCheckAll: function() {
                        this.isCheckAll = !this.isCheckAll;
                        if(this.isCheckAll) {
                            for(var i = 0; i < this.questionList.length; i++) {
                                if(this.fnIsHaveChecked(this.questionList[i].relationId) < 0) {
                                    var qItem = {
                                        "questionRelationId": this.questionList[i].relationId,
                                        "questionName": this.questionList[i].questionName,
                                        "questionLibraryId": this.questionList[i].questionLibraryId,
                                    }
                                    this.questionIdList.push(qItem);
                                }
                            }
                            fnGetCheckQuestionList();
                        } else {
                            for(var i = 0; i < this.questionList.length; i++) {
                                var qIndex = this.fnIsHaveChecked(this.questionList[i].relationId);
                                if(qIndex > -1) {
                                    this.questionIdList.splice(qIndex, 1);
                                }
                            }
                            fnGetCheckQuestionList();
                        }
//                      api.sendEvent({
//                          name: 'isCheckAll',
//                          extra: {
//                              isCheckAll: vm.isCheckAll,
//                          }
//                      });
                    },
                    fnIsHaveChecked: function(sId) {
                        for(var i = 0; i < this.questionIdList.length; i++) {
                            if(this.questionIdList[i].questionRelationId == sId) {
                                return i;
                            }
                        }
                        return -1;
                    },
                    fnCheckQuestion: function(sId, name, id) {
                        if(sId && sId != '') {
                            var _sIndex = this.fnIsHaveChecked(sId);
                            if(_sIndex > -1) {
                                this.questionIdList.splice(_sIndex, 1);
                                fnGetCheckQuestionList();
                            } else {
                                var _sItem = {
                                    "questionRelationId": sId,
                                    "questionName": name,
                                    "questionLibraryId": id
                                }
                                this.questionIdList.push(_sItem)
                                fnGetCheckQuestionList();
                            }
                        }
                        var _checkedNum = 0;
                        for(var i = 0; i < vm.questionList.length; i++) {
                            for(var y = 0; y < vm.questionIdList.length; y++) {
                                if(vm.questionList[i].relationId == vm.questionIdList[y].questionRelationId) {
                                    _checkedNum++;
                                }
                            }
                        }
                        if(_checkedNum == this.questionList.length) {
                            this.isCheckAll = true;
                        } else {
                            this.isCheckAll = false;
                        }
//                      api.sendEvent({
//                          name: 'isCheckAll',
//                          extra: {
//                              isCheckAll: vm.isCheckAll,
//                          }
//                      });
                    }
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
            if(pSign != "load") { //刷新
                if(pSign != "headerRefreshing") {
//                  $S.ShowProgress();
                }
                vm.pageNo = 1;
            } else { //加载
//              $S.ShowProgress();
            }
            var postData = {
                "childrenModelId": "111",
                "pageNo": vm.pageNo,
                "pageSize": "10"
            }
            POST("apiQuestion/findHomeWorkQustionListByChildrenModelId", postData, function(ret, err) {
                if(ret && ret.status == "200") {
                    if(vm.pageNo == '1') {
                        vm.questionList = [];
                        vm.isCheckAll = false;
//                      api.sendEvent({
//                          name: 'isCheckAll',
//                          extra: {
//                              isCheckAll: vm.isCheckAll,
//                          }
//                      });
                    }
                    vm.questionList.push.apply(vm.questionList, ret.data.listData);
                    console.log(JSON.stringify(vm.questionList))
                    if(!vm.isLoad) {
                        fnOpenBottomFrame();
                    }
                    vm.pageNo++;
                    vm.fnCheckQuestion(); //判断是否全选
                    if(vm.isCheckAll) {
                        for(var i = 0; i < vm.questionList.length; i++) {
                            if(vm.fnIsHaveChecked(vm.questionList[i].relationId) < 0) {
                                var qItem = {
                                    "questionRelationId": vm.questionList[i].relationId,
                                    "questionName": vm.questionList[i].questionName,
                                    "questionLibraryId": vm.questionList[i].questionLibraryId,
                                }
                                vm.questionIdList.push(qItem);
                            }
                        }
                        fnGetCheckQuestionList();
                    }
                }
//              $S.CloseProgress();
//              api.refreshHeaderLoadDone();
                vm.isLoad = true;
//              api.setFrameAttr({
//                  name: 'index/job_type_question_list_bottom',
//                  hidden: vm.questionList.length > 0 ? false : true
//              });

            })
        }

        function fnOpenUnitTest() {
            fnOpenPublicWin("", "release/change_student")
        }
        //修改当前的frame的大小以及打开底部的frame
        function fnOpenBottomFrame() {
//          var bottom_h = $api.offset($api.dom(".job_type_question_list_bottom")).h;
//          var _headerH = api.pageParam.headerH;
//          var _footerH = api.pageParam.footerH;
//          api.setFrameAttr({
//              name: api.frameName,
//              rect: {
//                  x: 0,
//                  y: _headerH,
//                  w: "auto",
//                  h: api.winHeight - _headerH - _footerH - bottom_h
//              }
//          });
//          var param = {
//              name: "index/job_type_question_list_bottom",
//              url: './job_type_question_list_bottom.html',
//              useWKWebView: true,
//              bgColor: 'rgba(0,0,0,0)',
//              rect: {
//                  x: 0,
//                  y: api.winHeight - _footerH - bottom_h,
//                  w: "auto",
//                  h: bottom_h
//              },
//              pageParam: {
//                  questionList: vm.questionList,
//                  questionIdList: vm.questionIdList
//              }
//          }
//          fnOpenFrame(param);
//          $api.css($api.dom(".job_type_question_list_bottom"), "height:0;");

        }

        function fnGetCheckQuestionList() {
            console.log(JSON.stringify(vm.questionIdList))
            console.log(vm.questionIdList.length)
//          api.sendEvent({
//              name: 'checkQuestionList',
//              extra: {
//                  list: vm.questionIdList,
//              }
//          });
        }