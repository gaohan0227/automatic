var vm;
      $(function() {
        fnInit();
//      api.addEventListener({
//          name: 'setClass'
//      }, 
//      function(ret, err) {
//          if (ret) {
//              vm.classId = ret.value.classId;
//              vm.className = ret.value.className;
//              vm.unitId = ret.value.unitId;
//              vm.unitName = ret.value.unitName;
//              fnInitPage();
//              vm.readyRelease = false;
//              vm.questionIdList = "";
//          }
//      });
//      api.addEventListener({
//          name: 'checkQuestionList'
//      },
//      function(ret, err) {
//          if (ret) {
//              vm.questionIdList = ret.value.list;
//          }
//      });
    });

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                classId: "",
                className: "",
                unitId: "",
                unitName: "",
                jobModule: [],
                questionIdList: [],
                readyRelease: false
            },
            mounted: function() {
                this.$nextTick(function() {

                })
            }
        })
    }

    function fnChangeClass() {
        var pageData = {
            classId: vm.classId,
            unitId: vm.unitId,
            unitName: vm.unitName,
        }
//      fnOpenPublicWin("班级列表", "release/class_list", pageData)
    }

    function fnOpenJobTypeDeatil(mId) {
        var pageParam = {
            parentId: mId,
            questionIdList: vm.questionIdList
        }
//      fnOpenPublicWin("发布作业", "index/job_type_detail", pageParam)
    }

    function fnInitPage() {
//      $S.ShowProgress();
        var postData = {
            "materialUnitId": vm.unitId,
        }
        POST("apiAppTeacher/findHWMoudleList", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.jobModule = ret.data.listData;
            }
//          $S.CloseProgress();
        })
    }

    function fnSubmit() {
        if (vm.readyRelease) {
            fnReleaseJob();
        } else {
//          api.closeToWin({
//              name: api.winName
//          });
            vm.readyRelease = true;
//          api.sendEvent({
//              name: 'publishTitle',
//              extra: {
//                  title: '立即发布',
//              }
//          });
        }
    }
    //发布作业
    function fnReleaseJob() {
//      $S.ShowProgress();
        var postData = {
            "classId":vm.classId,
            "unitId":vm.unitId,
            "questionIdList":vm.questionIdList,
        }
        POST("apiAppTeacher/publishUnitHomeWork", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                fnToast("作业发布成功");
                fnDelayCloseWin(800)
            }
//          $S.CloseProgress();
        })
    }