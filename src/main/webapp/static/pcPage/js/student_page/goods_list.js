 var goodsListVm;
    $(function () {
        fnInit();
        getData();
    })

    function fnInit() {
        goodsListVm = new Vue({
            el: ".goodsListVue",
            data: {
                goosList: [],
                isLoad:false,
            },
        })
    }

    function getData() {
        var callback = function (ret) {
            if(ret&&ret.status=="200"){
                goodsListVm.goosList = ret.data.listData;
            }
            goodsListVm.isLoad = true;
        };
        var postData = { "pageSize": '10000', "pageNo": '1' };
        POST('apiTeacherLogin/findGoodsList', postData, callback);
    }