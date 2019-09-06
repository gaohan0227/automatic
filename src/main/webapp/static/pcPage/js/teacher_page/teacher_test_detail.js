var vm;
    var size = 3;
    var _readSafeTop, _safeBottom;
    $(function() {
//      api.setScreenOrientation({
//          orientation: 'landscape_left'
//      });
        fnInit();
        fnInitPage();
//      api.addEventListener({
//          name: 'keyBack'
//      }, function(ret, err) {
//          fnBack()
//      });

    });

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                questionList: [],
                newIndex: 0,
                isSubmit: false,
                isLoad: false,
            },
            mounted: function() {
                fnInitPage();
            },
            methods: {
                fnSetAnswer: function(nIndex, optionId, isAnswer) {
                    if (!this.questionList[nIndex].isChoice) {
                        this.questionList[nIndex].isChoice = true;
                        this.questionList[nIndex].answerStr = optionId;
                        if (isAnswer == '1') {
                            this.questionList[nIndex].scoreValue = "100";
                        };
                        if (this.newIndex + 1 < this.questionList.length) {
                            this.newIndex = this.newIndex * 1 + 1;
                        } else {
                            this.fnSubmit();
                        }
                    }
                },
                fnSubmit: function() {
//                  $S.ShowProgress();
                    var postData = {
                        "whetherTime": "0",
                        "unitId": "",
                        "bussActivityKindCode": "9",
                        "questionResultList": vm.questionList
                    }
                   POST("apiQuestion/saveQuestionResult", postData, function(ret, err) {
                        if (ret && ret.status == "200") {
//                          api.execScript({
//                              name: 'index/teacher_test_win',
//                              frameName: 'index/teacher_test',
//                              script: 'fnInitPage();'
//                          });
//                          api.execScript({
//                              name: 'index/resource_detail_win',
//                              frameName: 'index/resource_detail',
//                              script: 'fnInitPage();'
//                          });
                            setTimeout(function(){
                                fnBack();
                            },300)
                        }
//                      $S.CloseProgress();
                    })
                }
            },
            filters: {
                fnIndex: function(val) {
                    return String.fromCharCode(64 + parseInt(val * 1 + 1))
                }
            }
        })
    }


    function fnInitPage() {
//      ShowProgress();
        var postData = {
            "dynamicMaterialId": "",
            "bussActivityKindCode": "9",
        }
        POST("apiQuestion/findQuestionList", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                for (var i = 0; i < ret.data.listData.length; i++) {
                    ret.data.listData[i].scoreValue = "0";
                    ret.data.listData[i].answerStr = "";
                    ret.data.listData[i].isChoice = false;
                }
                vm.questionList = ret.data.listData;
            }
            vm.$nextTick(function() {
                setTimeout(function() {
//                  _readSafeTop = api.safeArea.top;
//                  _safeBottom = api.safeArea.bottom;
//                  _safeLeft = api.safeArea.left;
//                  $api.css($api.dom("header"), 'padding-top:' + _readSafeTop + 'px;');
//                  $api.css($api.dom(".vueApp"), 'padding-left:' + _safeLeft + 'px;');
//                  var _headerH = AD('header').offsetH();
//                  $api.css($api.dom(".teacher_test_detail_card"), 'left:' + _safeLeft + 'px;bottom:' + _safeBottom + 'px;top:' + _headerH + 'px;');
                }, 300)
            })
//          $S.CloseProgress();
            vm.isLoad = true;
        })
    }

    function fnBack() {
//      api.setScreenOrientation({
//          orientation: 'portrait_up'
//      });
        fnDelayCloseWin()
    }