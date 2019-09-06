!function(){
	 // 判断浮点型  
   $.validator.addMethod("isFloat", function(value, element) {       
        return this.optional(element) || /^[-\+]?\d+(\.\d+)?$/.test(value);       
   }, "只能包含数字、小数点等字符");
// 匹配密码，以字母开头，长度在6-12之间，只能包含字符、数字和下划线。      
   jQuery.validator.addMethod("isPwd", function(value, element) {       
        return this.optional(element) || /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,12}$/.test(value);       
   }, "长度6-12之间，只能包含字符、数字和下划线,且两两组合。");
// IP地址验证   
   jQuery.validator.addMethod("ip", function(value, element) {    
     return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);    
   }, "请填写正确的IP地址。");
//移动电话验证   
   jQuery.validator.addMethod("mobilePhone", function(value, element) {
		return this.optional(element) ||  /^1[0-9]{10}$/.test(value);
	}, "请输入正确的手机号码");
 //固定电话验证   
   jQuery.validator.addMethod("fixPhone", function(value, element) {
		return this.optional(element) || /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(value);
	}, "请输入正确的座机号码");
 //身份证号  
   jQuery.validator.addMethod("idCard", function(value, element) {
		return this.optional(element) ||  /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
	}, "请输入正确的身份证号码");
 //座机与移动电话验证   
   jQuery.validator.addMethod("fixAndMobilePhone", function(value, element) {
		return this.optional(element) ||  /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$|(^11[3|4|5|7|8]\d{9}$)/.test(value);
	}, "请输入正确的固定电话或手机号码");
}();