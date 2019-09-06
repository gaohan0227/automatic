var vm;
    
 $(function() {
        fnInit();
        fnInitPage();
        fnGetClassList();
//      api.addEventListener({
//          name: 'chooseDate'
//      }, function(ret, err) {
//          if (ret) {
//              if (ret.value.chooseType == 'class') {
//                  //转班
//                  fnClassOperation('1', "newId", ret.value.chooseId)
//              }
//          }
//      });
    });
    //初始化
    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                studentInfo: "",
                classList: [],
            },
            mounted: function() {
                this.$nextTick(function() {
                    // $api.css($api.dom(".student_header"), 'background-image:url(../../image/mine/default_avatar.png);');
                })
            },
            filters: {
                fnSexText: function(key) {
                    if (key == '1') {
                        return "男"
                    } else if (key == '2') {
                        return "女"
                    }
                    return ""
                }
            }
        })
    }

    function fnInitPage() {
//      $S.ShowProgress();
        var postData = {
            id: "034f2d37b12e4aaf837d776512d9e4b5",//学生id
            classInfoId:"305b8820a5c44b6c90ef37f05c64224f"
        }
         POST("apiTeacherLogin/findStudentInfo", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.studentInfo = ret.data;
            }
        })
    }

    function fnOpenClassInfo() {
        var pageParam = {
            classType: "exit"
        }
        fnOpenPublicWin("班级信息", "index/add_class", pageParam)
    }

    function fnBack() {
        fnDelayCloseWin()
    }

    function fnRemove() {
        var pageData = {
            bottomAlertList: [{
                text: "是",
                key: "1"
            }, {
                text: "否",
                key: "2"
            }],
            bottomAlertFn: 'fnRemoveStudent'
        }
        fnOpenBottomAlert(pageData)
    }
    //移除学生
    function fnRemoveStudent(val) {
        if (val == '1') {
            fnClassOperation('0')
        }
    }
    //转班或者移除班级
    function fnClassOperation(kind, type, val) {
        var postData = {
            "id": "",
            "studentId": "",
            "kind": kind
        }
        if (kind == '1') {
            postData[type] = val;
        }
        POST("apiTeacherLogin/deleteClassByIdStudent", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                if (kind == '1') {
                    fnToast("换班成功")
                } else if (kind == '0') {
                    fnToast("移除成功")
                }
//              api.execScript({
//                  name: 'index/class_detail_win',
//                  frameName: 'index/class_detail',
//                  script: 'fnGetClassInfo();'
//              });
                fnDelayCloseWin(800);
            } else {
                if (kind == '1') {
                    fnGetClassList();
                }
            }
        })
    }

    function fnDeductIntegral() {
        var pageData = {
            "studentId":"",
            "isBtn": true,
            "btnType": "title",
            "btnName": "确定",
            "btnColor":"#acacac",
            "headerFn": "fnAlertDeduct",
        }
        fnOpenPublicWin("积分扣除", "index/deduct_integral", pageData)
    }

    function fnGetClassList() {
        var postData = {
            id: "",
            studentId: "",
        }
        POST("apiTeacherLogin/findMaterialInfoClassList", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.classList = ret.data.listData;
            }
        })
    }

    function fnChangeClass() {
        var param = {
            name: "choose_course_level",
            url: "widget://html/choose_course_level.html",
            pageParam: {
                chooseType: 'class',
                chooseId: "",
                chooseList: vm.classList,
            }
        }
        fnOpenFrame(param)
    }