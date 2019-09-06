var myDubListVm;
    $(function () {
        fnInit();
        fnGetDubListOne();
        fnGetDubListTwo();
    })

    function fnInit() {
        myDubListVm = new Vue({
            el: ".myDubListVue",
            data: {
                isLoad:true,
                newIndex: 1,
                myDubList: [],
                myDubListOne: [],
                myDubListTwo: [],
            },
            methods: {
                fnSetIndex: function (key) {
                    myDubListVm.newIndex = key;
                    if (key == '1') {
                        myDubListVm.myDubList = myDubListVm.myDubListOne
                    } else if (key == '2') {
                        myDubListVm.myDubList = myDubListVm.myDubListTwo;
                    }
                },
                fnHover: function (index, type) {
                    this.myDubList[index].isHover = type;
                },
            }
        })
    }
    function fnGetDubListOne() {
        // $S.ShowProgress();
        var postData = {
            "submitState": '1',
            "pageNo": '1',
            "pageSize": "100000"
        }
        POST("AppModule/myModuleDue", postData, function (ret) {
            if (ret && ret.status == "200") {
                for (var i = 0; i < ret.data.listData.length; i++) {
                    ret.data.listData[i].isHover = '0';
                }
                myDubListVm.myDubListOne = ret.data.listData;
                if (myDubListVm.newIndex == '1') {
                    myDubListVm.myDubList = myDubListVm.myDubListOne;
                }
            }
            // $S.CloseProgress();
            myDubListVm.isLoad = true;
        })
    }
    function fnGetDubListTwo() {
        // $S.ShowProgress();
        var postData = {
            "submitState": '2',
            "pageNo": '1',
            "pageSize": "100000"
        }
        POST("AppModule/myModuleDue", postData, function (ret) {
            if (ret && ret.status == "200") {
                for (var i = 0; i < ret.data.listData.length; i++) {
                    ret.data.listData[i].isHover = '0';
                }
                myDubListVm.myDubListTwo = ret.data.listData;
                if (myDubListVm.newIndex == '2') {
                    myDubListVm.myDubList = myDubListVm.myDubListTwo;
                }
            }
            // $S.CloseProgress();
        })
    }