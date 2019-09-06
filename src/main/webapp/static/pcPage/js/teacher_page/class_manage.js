var vm;
    $(function() {
        fnInit();
        fnInitPage();
    });

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                pageType: "index",
                pageNo:"1",
                classList:[],
                bgColor: ['linear-gradient(147deg,#FEBD75 0%,#FE8B8B 100%)', 'linear-gradient(147deg,#75D0FE 0%,#8BB0FE 100%)', 'linear-gradient(147deg,#63EBB4 0%,#50E5AB 100%)', 'linear-gradient(147deg,#FFB5B5 0%,#FB90AB 100%)', 'linear-gradient(147deg,#B68EFD 0%,#7E72FF 100%)'],
                avatarClass:['avatarShadow0','avatarShadow1','avatarShadow2','avatarShadow3','avatarShadow4'],
                isLoad:false
            } 
        })
    }
    function fnInitPage() {
        fnGetList();
//      fnAddHeaderRefreshingEvent(function() { //设置下拉刷新
//          fnGetList('headerRefreshing');
//      });
//
//      fnAddFooterLoadingEvent(function() { // 设置上拉加载
//          fnGetList('load');
//      });

    }

    function fnGetList(pSign) {
        if (pSign != "load") { //刷新
            if (pSign != "headerRefreshing") {
//              $S.ShowProgress();
            }
            vm.pageNo = 1;
        } else { //加载
//          $S.ShowProgress();
        }
        var postData = {
            "pageNo": 1,
            "pageSize": 10
        }
        POST("apiTeacherLogin/findClassList", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                if (vm.pageNo == '1') {
                    vm.classList = [];
                }
                vm.classList.push.apply(vm.classList, ret.data.listData);
                console.log(JSON.stringify(vm.classList))
                vm.pageNo++;
            }
//          api.refreshHeaderLoadDone();
            vm.isLoad = true;
        })
    }
    //
    function fnClassAdd() {
        var pageParam = {
            classType:"add"
        }
        fnOpenPublicWin("班级信息","index/add_class",pageParam)
    }
    //
    function fnSearchClass(){
        var pageParam = {
            isBack:false,
            type:"search",
            isBtn:true,
            btnType:"title",
            btnName:"取消",
            headerFn:"fnBack",
            btnColor:"#6d6d6d"
        }
        fnOpenPublicWin("","index/search_class_list",pageParam)
    }
    //打开班级详情
    function fnOpenClassDetail(cId){
        var pageData = {
            classId:cId
        }
        fnOpenPublicWin("班级详情","index/class_detail",pageData)
    }
    //打开班级报告
    function fnOpenClassReport(cId){
        // var param = {
        //     name:"class_report_win",
        //     url:"../mine/class_report_win.html",
        //     pageParam:{
        //         classId:cId
        //     }
        // }
        // fnOpenWin(param)
        var pageData = {
            classId:cId
        }
        fnOpenPublicWin("班级报告","mine/class_report_win",pageData)
    }