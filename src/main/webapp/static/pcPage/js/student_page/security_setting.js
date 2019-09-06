var settingVm;
$(function () {
    fnInit();
})

function fnInit() {
    settingVm = new Vue({
        el: ".settingVue",
        data: {
            telePhone: headerVm.pageNavigation[headerVm.pageNavigation.length - 1].pageData.tel,
        },
        methods: {
            fnSetTel: function (tel) {
                var ary = tel.split("");
                ary.splice(3, 4, "****");
                return ary.join("");
            }
        }
    })
}