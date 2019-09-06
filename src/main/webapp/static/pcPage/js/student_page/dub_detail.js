var dubDetailVm;
var _video;
$(function () {
    fnInitDubDetail();
    _video = document.getElementById("video_data");
    fnGetDubRank();
})

function fnInitDubDetail() {
    dubDetailVm = new Vue({
        el: ".dubDetailVue",
        data: {
            isDubDetailLoad: false,
            progress: '0',
            isChangeSpeed: "0",
            currentTime: 0,
            duration: 0,
            moduleId: pageParam.moduleId || "",
            videoUrl: pageParam.videoUrl || "",
            videoCover: pageParam.videoCover || "",
            videoName: pageParam.videoName || "",
            isPlay: false, //视频是否播放过
            inPlay: "0", //视频是否在播放中 0否 1是
            newSpeed: "1",
            rankList: [], //排行榜
            isClass:"0",
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
            fnCloseChangeSpeed: function (key) {
                this.isChangeSpeed = key;
            },
            fnSetNewsSpeed: function (key) {
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
    dubDetailVm.progress = $(el).val();
    _video = document.getElementById("video_data");
    _video.currentTime = _video.duration * (dubDetailVm.progress / 100);
    dubDetailVm.currentTime = _video.duration * (dubDetailVm.progress / 100);
    dubDetailVm.duration = _video.duration && _video.duration > 0 ? _video.duration : 0;
}

function fnVideoPlay(key) {
    _video = document.getElementById("video_data");
    if (!dubDetailVm.isPlay) {
        dubDetailVm.isPlay = true;
    }
    dubDetailVm.inPlay = key;
    if (key == '1') { //播放
        _video.play();
        // dubDetailVm.progress = _video.duration && _video.duration > 0 ? (_video.currentTime / _video.duration) * 100 : '0'; //已播放百分比
        // dubDetailVm.currentTime = _video.currentTime
        dubDetailVm.duration = _video.duration && _video.duration > 0 ? _video.duration : 0;
        fnEvevtVideo()
    } else { //暂停
        _video.pause();
    }
}

function fnEvevtVideo() {
    _video = document.getElementById("video_data");
    _video.addEventListener("timeupdate", function () {
        dubDetailVm.progress = _video.duration && _video.duration > 0 ? (_video.currentTime / _video.duration) * 100 : '0'; //已播放百分比
        dubDetailVm.currentTime = _video.currentTime;
        dubDetailVm.duration = _video.duration && _video.duration > 0 ? _video.duration : 0;
    })
}
//视频播放完成
function fnVideoEnd() {
    dubDetailVm.inPlay = '0';
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
            progress: dubDetailVm.progress,
            currentTime: dubDetailVm.currentTime,
            duration: dubDetailVm.duration,
            videoUrl: dubDetailVm.videoUrl,
            videoCover: dubDetailVm.videoCover,
            videoName: dubDetailVm.videoName,
            inPlay: dubDetailVm.inPlay, //视频是否在播放中 0否 1是
            newSpeed: dubDetailVm.newSpeed,
        }
    }
    fnOpenWin(tParam);
    dubDetailVm.inPlay = '0';
    fnVideoPlay(dubDetailVm.inPlay)
}

function fnGetDubRank() {
    // $S.ShowProgress();
    var postData = {
        "moduleId": dubDetailVm.moduleId
    }
    POST("AppModule/rankingList", postData, function (ret) {
        if (ret && ret.status == "200") {
            dubDetailVm.rankList = ret.data.listData||[];
                dubDetailVm.isClass = ret.data.status||'0';
        }
        // $S.CloseProgress();
        dubDetailVm.isDubDetailLoad = true;
        alert(dubDetailVm.isDubDetailLoad)
    })
}