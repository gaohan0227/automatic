var vm;
   $(function() {
        fnInit();
    });
    //初始化
    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                modify_type: "username",
                page_name: "",
                btn_name:  "保存",
                modify_val:"",
            },
            methods: {
                fnClearVal: function() {
                    this.modify_val = ""
                }
            }
        })
    }

    function fnSaveInfo() {
//      if (!fnVerify("info_input")) {
//          return;
//      }
//      if (vm.modify_type == 'classname' && api.pageParam.classType == 'add') {
//          api.sendEvent({
//              name: 'classname',
//              extra: {
//                  classname: vm.modify_val
//              }
//          });
//          fnBack()
//      } else {
//          var postData = {};
//          var mType;
//          if (vm.modify_type == 'username') {
//              mType = "teacherName"
//          } else if (vm.modify_type == 'email') {
//              mType = "email"
//          }
//          api.execScript({
//              name: 'mine/personal_info_win',
//              frameName: 'mine/personal_info',
//              script: 'fnUpdateInfor("' + mType + '","' + vm.modify_val + '");'
//          });
//          fnBack()
        // }
    }

    function fnBack() {
        fnDelayCloseWin()
    }