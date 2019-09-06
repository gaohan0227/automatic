var homeVm;
$(function () {
	fnInit();
	fnLoadUserInfo();
	// $('#myCarousel').carousel('cycle');
	// $('#myCarousel').carousel({ interval: 2000 });
});

function fnLoadUserInfo(levelId, type) {
	var postData = {
		studentMaterialLevelId: levelId || localStorage.studentMaterialLevelId || '',
	};
	POST('apiTeacherLogin/findStudentInfoDetail', postData, function (ret) {
		if (ret && ret.status == '200') {
			ret.data.unitName = '';
			ret.data.unitId = '';
			ret.data.isDo = '0';
			if (ret.data.materialInfoId != '') {
				ret.data.unitName = localStorage.unitName || '';
				ret.data.unitId = localStorage.unitId || '';
				ret.data.isDo = localStorage.isDo || '';
				var _unitId = localStorage.unitId || '';
				var _levelId = localStorage.studentMaterialLevelId || '';
				var isExitUnit = true;
				if (_levelId == ret.data.studentMaterialLevelId) {
					for (var i = 0; i < ret.data.materialUnitList.length; i++) {
						if (ret.data.materialUnitList[i].id == _unitId) {
							ret.data.unitName = ret.data.materialUnitList[i].moduleName;
							ret.data.unitId = ret.data.materialUnitList[i].id;
							ret.data.isDo = ret.data.materialUnitList[i].isDo;
							isExitUnit = false;
						}
					}
				}
				if (isExitUnit) {
					ret.data.unitName = '';
					ret.data.unitId = '';
					ret.data.isDo = '';
					if (ret.data.materialUnitList.length > 0) {
						ret.data.unitName = ret.data.materialUnitList[0].moduleName;
						ret.data.unitId = ret.data.materialUnitList[0].id;
						ret.data.isDo = ret.data.materialUnitList[0].isDo;
					}
				}
			}
			homeVm.schoolInfo = ret.data;
			ret.data.materialUnitList =
				ret.data.materialUnitList.length > 0
					? JSON.stringify(ret.data.materialUnitList)
					: '';
			var schoolId = localStorage.schoolId || '';
			if (schoolId != ret.data.schoolId) {
				homeVm.fnIndexInfo();
			}
			if (_levelId != ret.data.studentMaterialLevelId) {
				setTimeout(function () {
					fnSetPublicUnit(); //刷新存储的单元信息的展示
				}, 300);
			}
			if (ret.data.materialUnitList == '') {
				localStorage.materialUnitList = '';
			}
			fnWhenLoginSucess(ret.data);
			if (type == 'task') {
				fnOpenTaskList();
			}
		}
		// $S.CloseProgress();
	});
}

function fnInit() {
	homeVm = new Vue({
		el: '.homeVue',
		data: {
			schoolInfo: '',
			isLoad:false,
			indexInfo: '',
		},
		mounted: function () {
			this.fnIndexInfo();
		},
		methods: {
			fnIndexInfo: function () {
				// $S.ShowProgress();
				POST('apiTeacherLogin/findBannerStudent', '', function (ret) {
					if (ret && ret.status == '200') {
						if(ret.data.hotList.length>0){
							for (var i = 0; i < ret.data.hotList.length; i++) {
								ret.data.hotList[i].isHover='0';
							}
						}
						homeVm.indexInfo = ret.data;
						localStorage.avatarUrl = ret.data.avatarUrl;
						if(ret.data.avatarUrl&&ret.data.avatarUrl!=""){
							headerVm.headerIcon[headerVm.headerIcon.length-1].img =  ret.data.avatarUrl;
						}
					}
					//   $S.CloseProgress();
				});
			},
			fnHover: function (index, type) {
				this.indexInfo.hotList[index].isHover = type;
			}
		},
	});
}
//打开班级管理页面
function fnOpenClassManage() {
	var pageParam = {
		isBtn: true,
		btnType: 'image',
		btnName: 'index/class_add',
		headerFn: 'fnClassAdd',
		pageType: 'index',
	};
	fnOpenPublicWin('班级管理', 'index/class_manage', pageParam);
}

function fnOpenSwitchBook() {
	fnOpenPublicWin('', 'index/switch_book');
}

function fnOpenCalendar() {
	fnOpenPublicWin('', 'index/calendar');
}

function fnOpenMyTask(el) {
	if (el && el.stopPropagation) {
		el.stopPropagation();
	} else {
		window.event.cancelBubble = true;
	}
	fnLoadUserInfo('', 'task');
}

function fnOpenTaskList() {
	if (localStorage.isHavingClass == '0') {
		fnToast('暂未加入班级');
		return;
	}
	var pageData = {
		type: 'title',
		isBtn: true,
		btnType: 'title',
		btnName: localStorage.unitName || '',
		headerFn: 'fnChangeUnit',
		bgColor: '#F8DA93',
		btnColor: '#2f2f2f',
	};
	fnOpenPublicWin('我的作业', 'index/my_task', pageData);
}

function fnOpenDubShow() {
	var tParam = {
		name: 'dub_show_win',
		url: '../index/dub_show_win.html',
	};
	fnOpenWin(tParam);
}

function fnBookshelf() {
	fnOpenPublicWin('', 'index/bookshelf');
}

function fnOpenMyDub() {
	if (homeVm.schoolInfo.materialName == '') {
		fnToast('当前暂无教材');
		return;
	}
	fnOpenPublicWin('', 'mine/my_dub_win');
}

function fnOpenDubDetail(id, videoUrl, videoCover, videoName) {
	var pageParam = {
		moduleId: id,
		videoUrl: videoUrl,
		videoCover: videoCover,
		videoName: videoName,
	};
	fnOpenPublicWin('', 'index/dub_detail', pageParam);
}

//打开书籍详情
function fnOpenBookDetail(bId, bName) {
	var pageData = {
		bookId: bId,
	};
	fnOpenPublicWin(bName, 'index/book_detail', pageData);
}
