 $(function() {
        fnInit();
        fnInitPage();
//      api.addEventListener({
//          name: 'checkQuestionList'
//      }, function(ret, err) {
//          if (ret) {
//              vm.questionIdList = ret.value.list;
//          }
//      });
//
    });

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                jobModule: [],
                questionIdList:[]
            },
            methods:{
                fnSubmit: function() {
//                  api.execScript({
//                      name: 'index/job_publish_win',
//                      frameName: 'index/job_publish',
//                      script: 'fnSubmit();'
//                  });
                }
            }
        })
    }
    function fnOpenJobTypeQuestion(mId){
        var pageParam = {
            "mId":mId,
            "questionIdList":vm.questionIdList
        }
        fnOpenPublicWin("发布作业","index/job_type_question_list",pageParam)
    }
    function fnInitPage() {
//      $S.ShowProgress();
        var postData = {
            "parentId": "5a2070cdfc5346ddaa43fd7cbb49ffb0"
        }
       POST("apiAppTeacher/findHWChirldMoudleList", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.jobModule = ret.data.listData;
            }
//          $S.CloseProgress();
        })
    }