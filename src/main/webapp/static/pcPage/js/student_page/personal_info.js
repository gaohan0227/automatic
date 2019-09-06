var personalInfoVm;
$(function () {
    fnInit();
});

function fnInit() {
    personalInfoVm = new Vue({
        el: ".peisonalInfoVue",
        data: {
            newIndex: 1,
        },
        methods: {

        }
    })
}