var calendar;
$(function() {
  fnInit();
  fnInitPage(); //签到
  fnMonthList(); //获取当前月的列表
  fnTestList(); //获取测试列表
});

function fnInit() {
  calendar = new Vue({
    el: '.calendarVue',
    data: {
      isLoad: false,
      isCalendarHelp: '0',
      isSwitchBook: '0',
      isSignIn: '0',
      IntegralValue: '0',
      newYear: new Date().getFullYear(),
      newMonth: new Date().getMonth() + 1,
      newDay: new Date().getDate(),
      changeDay: new Date().getDate(),
      weekList: ['日', '一', '二', '三', '四', '五', '六'],
      monthData: [],
      dayTestList: [],
      testList: [],
      mLevelId: localStorage.studentMaterialLevelId || '',
    },
    methods: {
      fnIsHelpShow: function(key) {
        this.isCalendarHelp = key;
      },
      fnIsSignIn: function(key) {
        this.isSignIn = key;
      },
      fnIsSwitchBook: function(key) {
        this.isSwitchBook = key;
      },
      fnChangeDay: function(year, month, day) {
        var _testList = [];
        for (var i = 0; i < this.dayTestList.length; i++) {
          if (this.dayTestList[i].month == year + '-' + month) {
            for (var x = 0; x < this.dayTestList[i].datelist.length; x++) {
              if (this.dayTestList[i].datelist[x].day == day) {
                _testList = this.dayTestList[i].datelist[x].list;
              }
            }
          }
        }
        this.changeDay = day;
        calendar.testList = _testList;
      },
      fnBeforeMonth: function() {
        this.newYear = this.newMonth - 1 > 0 ? this.newYear : this.newYear - 1;
        this.newMonth = this.newMonth - 1 > 0 ? this.newMonth - 1 : 12;
        this.changeDay = '';
        calendar.testList = [];
        fnMonthList();
        fnTestList();
      },
      fnAfterMonth: function() {
        this.newYear =
          this.newMonth * 1 + 1 > 12 ? this.newYear * 1 + 1 : this.newYear;
        this.newMonth = this.newMonth * 1 + 1 > 12 ? 1 : this.newMonth * 1 + 1;
        this.changeDay = '';
        calendar.testList = [];
        fnMonthList();
        fnTestList();
      },
      fnSetDataStyle: function(year, month, day) {
        if (month != this.newMonth) {
          return 'color_gray';
        }
        var _class = [];
        for (var i = 0; i < this.dayTestList.length; i++) {
          if (this.dayTestList[i].month == year + '-' + month) {
            for (var x = 0; x < this.dayTestList[i].datelist.length; x++) {
              if (this.dayTestList[i].datelist[x].day == day) {
                _class.push('have_test');
              }
            }
          }
        }
        if (day == this.newDay && month == new Date().getMonth() + 1) {
          _class.push('new_day');
        }
        if (day == this.changeDay) {
          _class.push('change_day');
        }
        return _class.join(' ');
      },
    },
  });
}

function fnInitPage() {
  // $S.ShowProgress();
  POST('apiTeacherLogin/studentSign', '', function(ret) {
    if (ret && ret.status == '200') {
      calendar.IntegralValue = ret.data.IntegralValue;
      if (ret.data.IntegralValue > 0) {
        calendar.isSignIn = '1';
        setTimeout(function() {
          calendar.isSignIn = '0';
        }, 3000);
      }
    }
    // $S.CloseProgress();
    // calendar.isLoad = true;
  });
}

function fnOpenResource() {
  fnOpenPublicWin('', 'index/resource_list');
}

function fnBack() {
  fnDelayCloseWin();
}

function fnOpenUnitTest(mLevelId) {
  var _changeDate = new Date(
    calendar.newYear + '-' + calendar.newMonth + '-' + calendar.changeDay
  );
  if (_changeDate > new Date()) {
    fnToast('未到该日期，不能查看对应的单元测试列表');
    return;
  }
  if (mLevelId != calendar.mLevelId) {
    calendar.fnIsSwitchBook('1');
    return;
  }
  fnOpenPublicWin('', 'mine/unit_test_list');
}
//获取每个月的最大天数；
function fnGetMonthDay(year, month) {
  if (month == '4' || month == '6' || month == '9' || month == '11') {
    //1 3 5 7 8 10 11
    return '30';
  } else if (
    month == '1' ||
    month == '3' ||
    month == '5' ||
    month == '7' ||
    month == '8' ||
    month == '10' ||
    month == '12'
  ) {
    return '31';
  } else if (
    parseInt(year) % 4 == 0 &&
    (parseInt(year) % 100 != 0 || parseInt(year) % 400 == 0) &&
    month == '2'
  ) {
    return '29';
  }
  return '28';
}
//获取当前月的天的list
function fnMonthList() {
  var monthLength = fnGetMonthDay(calendar.newYear, calendar.newMonth);
  var firstDate = new Date(
    calendar.newYear + '-' + calendar.newMonth + '-' + '1'
  );
  var lastDate = new Date(
    calendar.newYear + '-' + calendar.newMonth + '-' + monthLength
  );
  var _monthList = [];
  if (firstDate.getDay() != '0') {
    var beforeMonth = calendar.newMonth - 1 > 0 ? calendar.newMonth - 1 : 12;
    var beforeMonthYear =
      beforeMonth > 0 ? calendar.newYear : calendar.newYear - 1;
    var beforeMonthLength = fnGetMonthDay(beforeMonthYear, beforeMonth);
    for (var i = 0; i < firstDate.getDay(); i++) {
      var item = {
        year: beforeMonthYear,
        month: beforeMonth,
        day: beforeMonthLength - i,
      };
      _monthList.push(item);
    }
  }
  for (var i = 1; i <= monthLength; i++) {
    var item = {
      year: calendar.newYear,
      month: calendar.newMonth,
      day: i,
    };
    _monthList.push(item);
  }
  if (lastDate.getDay() != '6') {
    var afterMonth =
      calendar.newMonth * 1 + 1 > 12 ? 1 : calendar.newMonth * 1 + 1;
    // var afterMonthLength = fnGetMonthDay(afterMonth);
    for (var i = 1; i <= 6 - lastDate.getDay(); i++) {
      var item = {
        year: afterMonth > 12 ? calendar.newYear * 1 + 1 : calendar.newYear,
        month: afterMonth,
        day: i,
      };
      _monthList.push(item);
    }
  }
  calendar.monthData = _monthList;
}
//获取单元测试列表
function fnTestList() {
  var _isUpdate = false;
  var _month = calendar.newYear + '-' + calendar.newMonth;
  for (var i = 0; i < calendar.dayTestList.length; i++) {
    if (calendar.dayTestList[i].month == _month) {
      _isUpdate = true;
    }
  }
  if (!_isUpdate) {
    // $S.ShowProgress();
    var _dateList = {
      month: _month,
      datelist: [],
    };
    calendar.dayTestList.push(_dateList);
  }

  var postData = {
    publishDate: calendar.newYear + '-' + formatNumber(calendar.newMonth),
  };
  POST('apiAppTeacher/findCalendarTestList', postData, function(ret) {
    if (ret && ret.status == '200') {
      for (var i = 0; i < calendar.dayTestList.length; i++) {
        if (calendar.dayTestList[i].month == _month) {
          calendar.dayTestList[i].datelist = fnSortDate(ret.data.listData);
        }
      }
      if (calendar.changeDay != '') {
        calendar.fnChangeDay(
          calendar.newYear,
          calendar.newMonth,
          calendar.changeDay
        );
      }
    }
    // $S.CloseProgress();
    calendar.isLoad = true;
    console.log(JSON.stringify(calendar.dayTestList));
  });
}

function fnSortDate(_list) {
  var listData = [],
    _dTestList = [],
    lastDay = 0,
    data = _list;
  data.map(function(item, index) {
    var date = new Date(item.testBeginDate);
    item.day = date.getDate();
    lastDay = lastDay == 0 ? item.day : lastDay;
    if (lastDay != item.day || index == data.length - 1) {
      if (index == data.length - 1) {
        if (lastDay == item.day) {
          _dTestList[_dTestList.length] = item;
        } else {
          var _dayTime = _dTestList[_dTestList.length - 1].day;
          var subData = {
            day: _dayTime,
            list: _dTestList,
          };
          listData[listData.length] = subData;
          _dTestList = [item];
        }
      }
      lastDay = item.day;
      var _dayTime = _dTestList[_dTestList.length - 1].day;
      var subData = {
        day: _dayTime,
        list: _dTestList,
      };
      listData[listData.length] = subData;
      _dTestList = [item];
    } else {
      _dTestList[_dTestList.length] = item;
      console.log(JSON.stringify(_dTestList));
    }
  });
  return listData;
}
