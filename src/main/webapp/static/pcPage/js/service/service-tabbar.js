/********************************************************************************
 * writer: ljliu
 * create date: 2018-8-15
 * rewriter:
 * rewrite date:
 * description: 对底部标签栏tabBar的相关操作进行了封装
 * 注意： 本方法使用了NVTabBar模块，使用前需在控制台添加该模块后再使用
 *********************************************************************************/
var _NVTabBar;

function TABBAR() {
    var sysType = api.systemType;
    var IconS = 22;
    var FSize = 11.0;
    var NormalColor = 'rgba(198,198,198,1)';
    var SelectColor = 'rgba(243,185,40,1)';
    var IconB = 6;
    var pbottom = USER.Get('safeBottom');
    var _tabBar = {};
    _NVTabBar = api.require("NVTabBar");
    var _TABHEIGHT = 50; //tabBar 高度
    var _itemsNum; //TabBar按钮元素数量
    var _items = []; //底部导航items
    var _marginB;
    if (sysType == 'ios') { //ios沉浸式
        _marginB = -5;
    } else {
        _marginB = -2.0;
    }
    // var _marginB_Middle = _marginB + 4;
    if (pbottom > 0) { //true 是iphoneX pbottom有值
        _marginB = -2.0;
    }
    //底部导航样式
    var width = screen.availWidth;
    var x = sysType == 'ios' ? 22 : width / 6 + 10;
    var _style = {
        bg: '#fff', //(可选项)字符串类型；模块背景，支持 rgb、rgba、#、img；默认：#ffffff
        h: _TABHEIGHT, //(可选项)数字类型；模块的高度(含分割线)；默认：50
        dividingLine: { //(可选项)JSON对象；模块顶部的分割线配置
            width: 0.5, //(可选项)数字类型；分割线粗细；默认：0.5
            color: '#EAEAEA' //(可选项)字符串类型；分割线颜色；默认：#000
        },
        badge: { //(可选项)JSON对象；徽章样式配置；若不传则取内部字段默认值
            bgColor: '#E0474C', //#E0474C(可选项)字符串类型；徽章背景色，支持rgb、rgba、#；默认：#ff0
            numColor: '#FFFFFF', //(可选项)字符串类型；徽章数字字体颜色，支持rgb、rgba、#；默认：#fff
            size: 6.0, //(可选项)数字类型；徽章半径大小；默认值：6.0
            fontSize: 10, // (可选项) 数字类型;设置徽章字体大小;默认值: 10 ;注意:仅支持iOS。
            centerX: x, //(可选项)数字类型；徽章中心点坐标(相对于所属item的背景面板坐标系)；默认值：icon图标的右上角
            centerY: 6.0 //(可选项)数字类型；徽章中心点坐标(相对于所属item的背景面板坐标系)；默认值：icon图标的右上角
        }
    };
    //底部导航items默认参数设置
    function _itemDefaultConfig(sender, index) {

        var _config = new Object();
        _config.w = api.winWidth / 3.0; //(可选项)数字类型；子项的宽度(识别点击事件的区域宽度)；默认：api.winWidth/items子项总数
        _config.bg = { //(可选项)JSON对象；子项背景配置，若不传则取内部字段默认值
            marginB: _marginB, //(可选项)数字类型；子项背景距离模块底部的距离，设置大于0的数字可实现凸起效果；默认：0
            image: 'rgba(255,0,0,0)', //(可选项)字符串类型；子项的背景，支持rgb、rgba、#、img(仅支持本地图片路径fs://、widget://)；默认：rgba(0,0,0,0)
        };
        _config.iconRect = { //(可选项)JSON对象；子项按钮图标的大小配置，位置居中显示；默认值见内部字段
            w: IconS, //(可选项)数字类型；子项按钮图标的宽度；默认：25.0
            h: IconS, //(可选项)数字类型；子项按钮图标的高度；默认：25.0
        };
        _config.icon = { // JSON对象；子项按钮图标配置
            normal: 'widget://image/tabs/' + sender.icon + '.png', // 字符串类型；子项按钮常态下的背景图片路径，要求本地路径(fs://、widget://)
            highlight: 'widget://image/tabs/' + sender.icon + '_selected.png', //(可选项)字符串类型；子项按钮高亮态下的背景图片路径，要求本地路径(fs://、widget://)，若不传或传空则无按钮高亮效果
            selected: 'widget://image/tabs/' + sender.icon + '_selected.png' //(可选项)字符串类型；子项按钮按钮选中后的背景图片路径，要求本地路径(fs://、widget://)，若不传或传空则无选中后效果
        };
        _config.title = { //(可选项)JSON对象；子项标题配置，若不传则取内部字段默认值
            text: sender.name, //(可选项)字符串类型；子项按钮下面的标题文字，若不传或传空则不显示
            size: FSize, //(可选项)数字类型；子项标题文字大小；默认：12.0
            normal: NormalColor, //(可选项)字符串类型；子项标题文字常态颜色；默认：#696969
            selected: SelectColor, //(可选项)字符串类型；子项标题文字选中后颜色；默认：#ff0
            marginB: IconB //(可选项)数字类型；子项标题距离模块下边缘的距离；默认：6.0
                //  ttf:'Alkatip Basma Tom'//(可选项)字符串类型；默认值：当前系统字体；
                //本参数在 iOS 平台上表示字体名称 (必须已在 config 文件内配置 ttf 文件(http://docs.apicloud.com/Dev-Guide/app-config-manual#14-1)，并在 widget 包内包含该 ttf 文件)；
                //本参数在 android 平台上表示 ttf 文件路径，要求本地路径(fs://、widget://)
        };
        // if (index == '1') {
        //     _config.bg = { //（可选项）JSON对象；子项背景配置，若不传则取内部字段默认值
        //             marginB: 0, //（可选项）数字类型；子项背景距离模块底部的距离，设置大于0的数字可实现凸起效果；默认：0
        //             image: 'rgba(255,0,0,0)', //（可选项）字符串类型；子项的背景，支持rgb、rgba、#、img（仅支持本地图片路径fs://、widget://）；默认：rgba(0,0,0,0)
        //         },
        //         _config.iconRect = { //(可选项)JSON对象；子项按钮图标的大小配置，位置居中显示；默认值见内部字段
        //             w: 58, //(可选项)数字类型；子项按钮图标的宽度；默认：25.0
        //             h: 58, //(可选项)数字类型；子项按钮图标的高度；默认：25.0
        //         };
        // }
        return _config;
    }

    /**
     * 设置item的对象属性参数
     * @param  {Array} pItems item对象数组
     *        举例
     *        icon: {
                  normal: 'fs://res/NVTabBar/icon2.png',
                  highlight: 'fs://res/NVTabBar/icon21.png',
                  selected: 'fs://res/NVTabBar/icon23.png'
              },
     title: {
                  text: '联系人',
                  size: 12.0,
                  normal: '#696969',
                  selected: '#ff0',
                  marginB: 6.0
              }
     ...
     *
     */
    _tabBar.SetItems = function(pItems) {

        if ('[object Array]' !== Object.prototype.toString.call(pItems)) {
            // console.log('TABBAR 中SetItems方法输入参数格式错误 type== '+Object.prototype.toString.call(pItems));
            return;
        }
        //遍历赋值
        _items = pItems.map(function(item, index) {
            var tConfig = _itemDefaultConfig(item, index);
            // for (var key in item) {
            //     var tValue = item[key];
            //     if ('[object Object]' === Object.prototype.toString.call(tValue)) {
            //         var tSubConfig = tConfig[key];
            //         for (var subKey in tValue) {
            //             tSubConfig[subKey] = tValue[subKey];
            //         }
            //     } else {
            //         tConfig[key] = tValue;
            //     }
            // }
            return tConfig;
        });
    }

    //打开TabBar
    _tabBar.Open = function(pCallback) {
            // console.log('items===='+JSON.stringify(param))
            _NVTabBar.open({
                styles: _style,
                items: _items,
                selectedIndex: 0
            }, function(ret, err) {
                if ('function' === typeof pCallback) {
                    pCallback(ret, err);
                }
            });
        }
        // 未读消息标志
    _tabBar.setBadge = function(isBadge) {
            if (isBadge) {
                _NVTabBar.setBadge({
                    index: 1,
                    badge: ''
                });
            } else {
                _NVTabBar.setBadge({
                    index: 1,
                    badge: null
                });
            }
        }
        //隐藏模块(并没有从内存清除)
    _tabBar.Hide = function() {
            _NVTabBar.hide();
        }
        //显示已隐藏的模块
    _tabBar.Show = function() {
            _NVTabBar.show();
        }
        //关闭模块，并从内存里清除
    _tabBar.Close = function() {
            _NVTabBar.close();
        }
        /**
         * 设置按钮右上角的徽章
         * @param  {Number} pIndex   (可选项)要设置的子项的下标,默认值0
         * @param  {String} pContent (可选项)要设置的徽章的内容，若不传则表示清除已显示的徽章，若传空字符串则显示小红点(大小为徽章的1.0/2.0)
         */
    _tabBar.SetBadge = function(pIndex, pContent) {
            _NVTabBar.setBadge({
                index: pIndex,
                badge: pContent
            });
        }
        /**
         * 设置按钮的选中状态
         * @param  {Number} pIndex    (可选项)要设置的子项的索引,默认0
         * @param  {Boolen} pSelected (可选项)要设置的子项按钮的状态，默认true
         * @param  {Array} pIcons     (可选项)设置子按钮的多图联播效果(gif图效果)，若不传本参数则默认显示open接口内配置的图片
         *                             实例代码
         *                             ['fs://res/gif1.png','fs://res/gif2.png','fs://res/gif3.png','fs://res/gif4.png','fs://res/gif5.png','fs://res/gif6.png']
         * @param  {Array} pInterval  (可选项)动画帧之间的时间间隔(单位:毫秒 ms),默认300 pIcons存在时有效
         */
    _tabBar.SetSelected = function(pIndex, pSelected, pIcons, pInterval) {

            _NVTabBar.setSelect({
                index: pIndex,
                selected: pSelected,
                icons: pIcons,
                interval: pInterval
            });
        }
        //将已经打开的模块置为最上层显示
    _tabBar.BringToFront = function() {
        _NVTabBar.bringToFront();
    }

    return _tabBar;
}
