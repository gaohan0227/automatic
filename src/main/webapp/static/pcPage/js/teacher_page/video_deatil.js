var vm;  
    var _video;
     $(function() {
        fnInit();
        fnInitPage();
        _video = document.getElementById("video_data");
//      if (api.pageParam.id && api.pageParam.id != '') {
//          vm.isLoad = false;
//          fnInitPage();
//      } else {
//          vm.isLoad = true;
//          vm.type = '2';
//      }
    });

    function fnInit() {
        vm = new Vue({
            el: ".vueApp",
            data: {
                isLoad: false,
                type: "",
                imgUrl: "",
                progress: '0',
                isChangeSpeed: "0",
                currentTime: 0,
                duration: 0,
                id:  "",
                videoUrl:  "",
                videoCover: "",
                videoName:  "",
                isPlay: false, //视频是否播放过
                inPlay: "0", //视频是否在播放中 0否 1是
                newSpeed: "1",
                speedList: [{
                    key: "0.5"
                }, {
                    key: "0.75"
                }, {
                    key: "1"
                }, {
                    key: "1.25"
                }, {
                    key: "1.5"
                }, {
                    key: "2"
                }]
            },
            methods: {
                fnCloseChangeSpeed: function(key) {
                    this.isChangeSpeed = key;
                },
                fnSetNewsSpeed: function(key) {
                    this.newSpeed = key;
                    _video = document.getElementById("video_data");
                    _video.playbackRate = key;
                    this.isChangeSpeed = '0';
                }
            }
        })
    }

    function fnBack() {
        fnDelayCloseWin()
    }

    function change(el) {
        //用于修改时间
        vm.progress = $api.val(el);
        _video = document.getElementById("video_data");
        _video.currentTime = _video.duration * (vm.progress / 100);
        vm.currentTime = _video.duration * (vm.progress / 100);
        vm.duration = _video.duration && _video.duration > 0 ? _video.duration : 0;
    }

    function fnVideoPlay(key) {
        _video = document.getElementById("video_data");
        if (!vm.isPlay) {
            vm.isPlay = true;
        }
        vm.inPlay = key;
        if (key == '1') { //播放
            _video.play();
            // vm.progress = _video.duration && _video.duration > 0 ? (_video.currentTime / _video.duration) * 100 : '0'; //已播放百分比
            // vm.currentTime = _video.currentTime
            vm.duration = _video.duration && _video.duration > 0 ? _video.duration : 0;
            fnEvevtVideo()
        } else { //暂停
            _video.pause();
        }
    }

    function fnEvevtVideo() {
        _video = document.getElementById("video_data");
        _video.addEventListener("timeupdate", function() {
            vm.progress = _video.duration && _video.duration > 0 ? (_video.currentTime / _video.duration) * 100 : '0'; //已播放百分比
            vm.currentTime = _video.currentTime;
            vm.duration = _video.duration && _video.duration > 0 ? _video.duration : 0;
        })
    }
    //视频播放完成
    function fnVideoEnd() {
        vm.inPlay = '0';
        fnLock();
    }

    function fnSecToMin(pSec) { //秒数变分钟数
        var min = Math.floor(pSec / 60),
            second = Math.floor(pSec % 60),
            hour, newMin, time;

        if (min > 60) {
            hour = Math.floor(min / 60);
            newMin = min % 60;
        }

        if (second < 10) {
            second = '0' + second;
        }
        if (min < 10) {
            min = '0' + min;
        }
        return time = hour ? (hour + ':' + newMin + ':' + second) : (min + ':' + second);
    }
    //视频全屏
    function fnOpenFullVideo() {
        var tParam = {
            name: "video_full_detail_win",
            url: "./video_full_detail.html",
            slidBackEnabled: false,
            pageParam: {
                progress: vm.progress,
                currentTime: vm.currentTime,
                duration: vm.duration,
                videoUrl: vm.videoUrl,
                videoCover: vm.videoCover,
                videoName: vm.videoName,
                inPlay: vm.inPlay, //视频是否在播放中 0否 1是
                newSpeed: vm.newSpeed,
            }
        }
        fnOpenWin(tParam);
        vm.inPlay = '0';
        fnVideoPlay(vm.inPlay)
    }

    function fnInitPage() {
        var postData = {
            "id": "747b06f67ab74d6dae3572c38f27fd82"
        }
        POST("apiAppTeacher/findTeacherChildMoudleDetail", postData, function(ret, err) {
            if (ret && ret.status == "200") {
                vm.type = ret.data.contentType;
                if (ret.data.contentType == '1') {
                    vm.imgUrl = ret.data.contentUrl;
                    fnLock();
                } else if (ret.data.contentType == '2') {
                    vm.videoUrl = ret.data.contentUrl;
                    vm.videoCover = ret.data.picUrl;
                }
            }
            vm.isLoad = true;
        })
    }
    //解锁
    function fnLock(){
        if(vm.id==""){
            return;
        }
        var postData = {
            "id": ""
        }
        POST("apiAppTeacher/deblockTeacherChildMoudle", postData, function(ret, err) {
            if (ret && ret.status == "200") {
//              api.execScript({
//                  name: 'index/resource_detail_win',
//                  frameName: 'index/resource_detail',
//                  script: 'fnGetList();'
//              });

            }
        })
    }