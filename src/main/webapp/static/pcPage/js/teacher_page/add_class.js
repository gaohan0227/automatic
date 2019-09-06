var vm;
       $(function() {
//      UIAlbumBrowser = api.require('UIAlbumBrowser');
        fnInit();
        fnInitPage();
//      api.addEventListener({
//          name: 'setAvatar'
//      }, function(ret, err) {
//          if (ret) {
//              if (ret.value.avatar) {
//                  if (vm.classType == "exit") {
//                      fnExitClassInfo("0", "classLogoUrl", ret.value.avatar)
//                  } else {
//                      vm.classLogoUrl = ret.value.avatar;
//                  }
//              }
//          }
//      });
//      api.addEventListener({
//          name: 'select_value'
//      }, function(ret, err) {
//          if (ret) {
//              if (api.pageParam.classNum && (api.pageParam.classNum > ret.value.select_value)) {
//                  fnToast("只能选择大于创建时的人数");
//                  return;
//              }
//              if (vm.classType == "exit") {
//                  fnExitClassInfo("0", "classNum", ret.value.select_value)
//              } else {
//                  vm.classNum = ret.value.select_value
//              }
//          }
//      });
//      api.addEventListener({
//          name: 'classname',
//      }, function(ret, err) {
//          if (ret) {
//              vm.classNames = ret.value.classname
//          }
//      });
//      api.addEventListener({
//          name: 'chooseDate'
//      }, function(ret, err) {
//          if (ret) {
//              if (ret.value.chooseType == 'course') {
//                  vm.materiaId = ret.value.chooseId;
//                  vm.materialName = ret.value.chooseText;
//                  vm.studentMaterialLevelList = ret.value.levelList;
//                  vm.studentMaterialLevelId = "";
//                  vm.studentMaterialLevelText = "";
//              } else if (ret.value.chooseType == 'level') {
//                  vm.studentMaterialLevelId = ret.value.chooseId;
//                  vm.studentMaterialLevelText = ret.value.chooseText;
//              }
//          }
//      });

    });
    //初始化
    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                classType:"",
                classLogoUrl: "",
                classNames:  "",
                classStartTime: formatTime(( new Date()), 'date'),
                classNum:  "50",
                materiaList: [],
                materiaId: "",
                materialName: "",
                studentMaterialLevelList: [],
                studentMaterialLevelId: "",
                studentMaterialLevelText:  "",
                success: true
            }
        })
    }

    function fnInitPage() {
        if (vm.classType == 'exit') {
            // fnGetClassInfo(); //获取班级信息
        } else if (vm.classType == 'add') {
            fnGetCourseList(); //获取课程及级别列表
        }

    }
    //     //获取班级信息
    //     function fnGetClassInfo() {
    //         $S.ShowProgress();
    //         var postData = {
    //             id: api.pageParam.classId
    //         }
    //         $S.POST("apiTeacherLogin/getClassInfo", postData, function(ret, err) {
    //             if (ret && ret.status == "200") {
    //
    //             }
    //             $S.CloseProgress();
    //         })
    //     };
    //获取课程及级别列表
    function fnGetCourseList() {
//      $S.ShowProgress();
        POST("apiTeacherLogin/findMaterialInfo", "", function(ret, err) {
            if (ret && ret.status == "200") {
                vm.materiaList = ret.data.listData;
            }
//          $S.CloseProgress();
        })
    }

    function fnOpenSelectSize() {
        var param = {
            name: "select_duration",
            url: "widget://html/select_duration.html",
            useWKWebView: true,
            pageParam: {
                val: vm.classNum,
                type: ""
            }
        }
        fnOpenFrame(param)
    }

    function fnOpenModifyInfo() {
        var pageParam = {
            modify_type: "classname",
            modify_val: vm.classNames,
            classType: vm.classType,
            page_name: "班级名称",
            btn_name: vm.classType == 'add' ? "确定" : "保存",
        }
        fnOpenPublicWin("", "mine/modify_info", pageParam)
    }

    function fnChooseCourseLevel(type) {
        var param = {
            name: "choose_course_level",
            url: "widget://html/choose_course_level.html",
            pageParam: {
                chooseType: type,
                chooseId: type == 'course' ? vm.materiaId : vm.studentMaterialLevelId,
                chooseList: type == 'course' ? vm.materiaList : vm.studentMaterialLevelList,
            }
        }
        fnOpenFrame(param)
    }

    function fnIsAlbum() {
//      if (api.systemType == 'ios') {
//          UIAlbumBrowser.requestAlbumPermissions({}, function(ret, err) {
//              if (ret.isAccessPermissions) {
//                  fnChangeType();
//              } else {
//                  fnToast("相册权限未开启")
//              }
//          });
//      } else {
//          fnChangeType();
//      }
    }

    function fnChangeType() {
        var pageData = {
            bottomAlertList: [{
                text: "拍照",
                key: "1"
            }, {
                text: "相册",
                key: "2"
            }],
            bottomAlertFn: 'fnChangeImg'
        }
        fnOpenBottomAlert(pageData)
    }

    function fnChangeImg(val) {
        if (val == '1') {
            fnOpenCamera()
        } else if (val == '2') {
            fnOpenChangeImg()
        }
    }

    function fnOpenCamera() {
        var FNPhotograph = api.require('FNPhotograph');
        FNPhotograph.open({
            path: 'fs://savePath',
            album: true,
            quality: 'medium'
        }, function(ret) {
            console.log(JSON.stringify(ret))
            if (ret.eventType == "takePhoto") {
                fnOpenClipPic(ret.imagePath);
                setTimeout(function() {
                    FNPhotograph.close();
                }, 500)
            }else if (ret.eventType == "cameraError") {
                fnToast("相机权限未开启")
            }
        });
    }

    function fnOpenChangeImg() {
        UIAlbumBrowser.open({
            max: 1,
            type: "image",
            isOpenPreview: false,
            classify: false,
            styles: {
                bg: '#fff',
                mark: {
                    icon: '',
                    position: 'bottom_left',
                    size: 20
                },
                nav: {
                    bg: 'rgba(0,0,0,0.6)',
                    titleColor: '#fff',
                    titleSize: 18,
                    cancelColor: '#fff',
                    cancelSize: 16,
                    finishColor: '#fff',
                    finishSize: 16
                }
            },
            rotation: false
        }, function(ret) {
            if (ret) {
                if (ret.eventType == 'confirm') {
                    if (ret.list.length > 0) {
                        fnTrashPath(ret.list[0].path)
                    }
                }
            }
        });
    }

    function fnTrashPath(path) {
//      $S.ShowProgress();
        UIAlbumBrowser.transPath({
            path: path
        }, function(ret, err) {
            if (ret) {
                fnOpenClipPic(ret.path)
            } else {
                console.log('图片路径转换失败')
            }
        });
//      $S.CloseProgress();
    }

    function fnOpenClipPic(url) {
        var tParam = {
            name: 'clip_pic_win',
            url: 'widget://html/clip_pic_win.html',
            pageParam: {
                picUrl: url
            }
        }
        fnOpenWin(tParam);
    }
    //添加班级
    var addClick = true;

    function fnAddClass() {
        if (vm.classLogoUrl == "") {
            fnToast("请上传头像");
            return;
        } else if (vm.classNames == "") {
            fnToast("请填写班级名称");
            return;
        } else if (vm.materiaId == "") {
            fnToast("请选择课程");
            return;
        } else if (vm.studentMaterialLevelId == "") {
            fnToast("请选择级别");
            return;
        }
        if (!addClick) {
            return;
        }
        addClick = false;
        var postData = {
            classLogoUrl: vm.classLogoUrl,
            classNames: vm.classNames,
            materiaId: vm.materiaId,
            studentMaterialLevelId: vm.studentMaterialLevelId,
            classNum: vm.classNum,
            classStartTime: vm.classStartTime
        };
//      $S.ShowProgress();
        POST("apiTeacherLogin/addClassInfo", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                fnToast("班级添加成功");
//              api.execScript({
//                  name: 'index/class_manage_win',
//                  frameName: 'index/class_manage',
//                  script: 'fnInitPage();'
//              });
                fnDelayCloseWin(800)
            } else {
                addClick = true;
            }
//          $S.CloseProgress();
        })
    }

    function fnDelClass() {
        var param = {
            alertFn: "fnExitClassInfo('1')",
            alertType: "5"
        }
        fnOpenAlert(param)
    }

    function fnExitClassInfo(kind, type, val) {
        var postData = {
            "id":"",
            "kind": kind
        }
        if (kind == '0') {
            postData[type] = val;
        }
//      $S.ShowProgress();
        POST("apiTeacherLogin/updateClassinfo", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                api.execScript({
                    name: 'index/class_manage_win',
                    frameName: 'index/class_manage',
                    script: 'fnInitPage();'
                });
                if (kind == '1') {
                    fnToast("班级删除成功");
                    setTimeout(function() {
                        api.closeToWin({
                            name: 'index/class_manage_win'
                        });
                    }, 800)
//                  $S.CloseProgress();
                    return;
                };
                api.execScript({
                    name: 'index/class_detail_win',
                    frameName: 'index/class_detail',
                    script: 'fnGetClassInfo();'
                });
                vm[type] = val;
            }
//          $S.CloseProgress();
        })
    }