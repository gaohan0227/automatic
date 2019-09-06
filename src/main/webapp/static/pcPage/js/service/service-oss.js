var END_POINT = 'https://oss-cn-beijing.aliyuncs.com'; //阿里云OSS服务在各个区域的地址
var ACCESS_KEY_ID = 'LTAIh2rfrRn4dQab'; //从阿里云获取的accessKeyId
var ACCESS_KEY_SECRET = 'jEBz02dFQNyv3QisN0JVsZmN5biPyq'; //从阿里云获取的accessKeySecret
var BASE_URL = 'http://jylf.oss-cn-beijing.aliyuncs.com'; //文件对外的域名，去掉了前面的http,因为在fnCacheImage()函数中会添加：http://paekdusan.oss-cn-beijing.aliyuncs.com
var BUCKET_NAME = "jylf"; //Bucket名称
var FOLDER = 'jylf/'; //Folder名称

/**
 * 阿里云OSS初始化：支持明文模式和自签名模式 。移动终端是一个不受信任的环境，如果把AccessKeyId和AccessKeySecret
 * 直接保存在终端本地用来加签请求，存在极高的风险。明文模式鉴权必须设置accessKeyId和accessKeySecret，此方式建议
 * 只在测试时使用。正式环境请使用自签名模式，此模式serverUrl为必须。
 * 目前使用的是明文模式。
 */
var aliyunOSS;

function fnInitAliYunOss() {
    aliyunOSS = api.require('aliyunOSS');
    aliyunOSS.initOSSClient({
        endpoint: END_POINT,
        accessKeyId: ACCESS_KEY_ID,
        accessKeySecret: ACCESS_KEY_SECRET,
    }, function(ret, err) {
        if (ret) {
            console.log(JSON.stringify(ret));
        }
    });
}


function fnUploadToAliYunOss(fileurl, pCallBack) {
    if (fileurl.indexOf('http') >= 0) {
        pCallBack(fileurl);
    } else {
        var timestamp = new Date().getTime() + Math.ceil(Math.random() * 1000);
        var s = fileurl;
        console.log(fileurl)
        var photoSrc = s.split("/");
        var photoName = FOLDER + timestamp + photoSrc[photoSrc.length - 1];
        console.log("上传文件名为：" + photoName);
        aliyunOSS.upload({
            file: fileurl,
            bucketName: BUCKET_NAME,
            objectKey: photoName,
            uploadType: 1,
        }, function(ret, err) {
            console.log(JSON.stringify(ret));
                console.log(JSON.stringify(err));
            if (ret) {
                if (ret.oper == "complete") {
                    var pImg = BASE_URL + '/' + photoName;
                    pCallBack(pImg);
                }
            } else {
                fnUploadToAliYunOss(fileurl,pCallBack)
            }
        });
    }
}
