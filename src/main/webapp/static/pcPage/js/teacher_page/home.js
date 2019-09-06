var $S, USER;
var vm;
$(function() {
    fnInit()
});

function fnInit() {
    vm = new Vue({
        el: ".vueApp",
        data: {
            isFocus: "0",
            indexInfo: "",
            mySwiper: null
        },
        mounted: function() {                    
            this.fnIndexInfo();            
        },
        methods: {
            fnIndexInfo: function() {
                POST("apiTeacherLogin/findBannerTeacher", "", function(ret, err) {
                    if (ret && ret.status == '200') {
                        vm.indexInfo = ret.data;
                        if (vm.indexInfo.schoolBannerList.length > 0) {
                            vm.$nextTick(function() {
                                if (vm.mySwiper != null) {
                                    vm.mySwiper.destroy();
                                }              
                            })
                        }
                    }
                })                                            
            }             
        }
    })
}
//打开班级管理页面
function fnOpenClassManage() {
    var pageParam = {
        isBtn: true,
        btnType: "image",
        btnName: "index/class_add",
        headerFn: "fnClassAdd",
        pageType: "index"
    }
    fnOpenPublicWin("班级管理", "index/class_manage", pageParam)
}

function fnOpenLessonCenter() {
    fnOpenPublicWin("", "index/lesson_center")
}

function fnOpenJobPublish() {
    fnOpenPublicWin("作业发布", "index/job_publish")
}

function fnOpenVideoDetail(videoUrl, videoCover, videoName) {
    var pageData = {
        "videoUrl": videoUrl,
        "videoCover": videoCover,
        "videoName": videoName
    };
    fnOpenPublicWin("", "index/video_deatil", pageData)
}