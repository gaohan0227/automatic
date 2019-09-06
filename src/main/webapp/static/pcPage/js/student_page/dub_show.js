$(function () {
  fnInit();
  fnInitPage();
});
function fnInit() {
  vm = new Vue({
    el: '.dubShowVue',
    data: {
      listData: [
        {
          dubText: '课程配音',
          list: [],
        },
        {
          dubText: '拓展配音',
          list: [],
        },
        {
          dubText: '推荐配音',
          list: [],
        },
      ],
      isLoad: false,
      isLoadText: false,
    },
    methods: {
      fnHover: function (index, val, type) {
        this.listData[index].list[val].isHover = type;
      },
    },
  });
}
function fnInitPage() {
  // $S.ShowProgress();
  var postData = {
    materialUnitId: localStorage.unitId,
  };
  POST('AppModule/dueModuleList', postData, function (ret, err) {
    if (ret && ret.status == '200') {
      vm.isLoadText = false;
      if (ret.data.anyQueryList) {
        for (var i = 0; i < ret.data.anyQueryList.length; i++) {
          ret.data.anyQueryList[i].isHover = '0';
          ret.data.anyQueryList[i].videoUrl = ret.data.anyQueryList[i].studentDubVideoUrl;
          ret.data.anyQueryList[i].id = ret.data.anyQueryList[i].moduleDubId;
        }
      }
      if (ret.data.classMaps) {
        for (var i = 0; i < ret.data.classMaps.length; i++) {
          ret.data.classMaps[i].isHover = '0';
        }
      }
      if (ret.data.expandMaps) {
        for (var i = 0; i < ret.data.expandMaps.length; i++) {
          ret.data.expandMaps[i].isHover = '0';
        }
      }
      vm.listData[0].list = ret.data.classMaps || [];
      vm.listData[1].list = ret.data.expandMaps || [];
      vm.listData[2].list = ret.data.anyQueryList || [];
      for (var i = 0; i < vm.listData.length; i++) {
        if (vm.listData[i].list.length > 0) {
          vm.isLoadText = true;
        }
      }
    }
    vm.isLoad = true;
    // $S.CloseProgress();
  });
}
