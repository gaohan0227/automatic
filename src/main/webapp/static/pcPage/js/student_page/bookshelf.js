    var vm;
    $(function () {
        fnInit();
        fnGetBookList();
    });


    function fnInit() {
        vm = new Vue({
            el: ".bookshelfVue",
            data: {
                listData:[],
                manageList: [],
                isLoad: false,
                endReadingNum: "",
                booksNums: "",
                searchVal:"",
                search:"",
                size :'5'
            },
            mounted: function() {
                this.$nextTick(function() {
                    
                })
            },
            filters: {
                fnIndexNum: function(i) {
                    return i + 1;
                }
            }
        })
    }

    function fnGetBookList(type) {
        var postData = {
            "booksName": vm.searchVal
        }
        POST("AppBooks/booksList", postData, function(ret) {
            if (ret && ret.status == "200") {
                vm.listData = ret.data.listMap;
                vm.endReadingNum = ret.data.endReadingNum;
                vm.booksNums = ret.data.booksNums;
                fnArrManage();
                if(type&&type=="search"){
                    vm.search=vm.searchVal;
                }
            }
        })
    }

    function fnArrManage() {
        var _resultArr = [];
        for (var x = 0; x < Math.ceil(vm.listData.length / vm.size); x++) {
            var start = x * vm.size;
            var end = start + vm.size;
            _resultArr.push(vm.listData.slice(start, end));
        }
        vm.manageList = _resultArr;
        console.log(JSON.stringify(vm.manageList))
        vm.isLoad = true;
    }
    function fnAddBook() {
        if (fnVerify("alert_input")) {
            var postData = {
                "materialSequenceNum": $("#alert_input input").val()
            }
            POST("AppBooks/saveBookInfo", postData, function(ret) {
                if (ret && ret.status == "200") {
                   fnToast("添加成功");
                   vm.searchVal="";
                   vm.search = "";
                   fnGetBookList();
                   fnCloseInputAlert();
                }
            })
        }
    }
    var booksId,booksName;
    function fnOpenBookDetail(id,name){
        booksId = id;
        booksName = name;
        
    }