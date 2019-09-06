var classListVm;
    $(function () {
        fnInit();
        fnGetList();
    })

    function fnInit() {
        classListVm = new Vue({
            el: ".classListVue",
            data: {
                isLoad:false,
                classList: [],
                bgColor: ['linear-gradient(147deg,#FEBD75 0%,#FE8B8B 100%)', 'linear-gradient(147deg,#75D0FE 0%,#8BB0FE 100%)', 'linear-gradient(147deg,#63EBB4 0%,#50E5AB 100%)', 'linear-gradient(147deg,#FFB5B5 0%,#FB90AB 100%)',
                    'linear-gradient(147deg,#B68EFD 0%,#7E72FF 100%)'
                ],
            },
            methods: {

            },
            watch: {

            }
        })
    }
    function fnGetList() {
        var postData = {
            pageNo: '1',
            pageSize: '100000',
        };
        POST('apiTeacherLogin/findClassInfoList', postData, function (ret) {
            if (ret && ret.status == '200') {
                classListVm.classList = ret.data.listData;
            }
            // $S.CloseProgress();
            classListVm.isLoad = true;
        });
    }