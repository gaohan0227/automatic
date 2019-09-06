package priv.bigant.aotomatic.common;

import java.util.List;

public class FieldValueConvertFinder extends FieldValueConvertFinderFactory {
    //	@Override
//	 public Map<String,String> findData(String tableName,String fieldName) throws Exception{
//		Map<String,String> tt=Maps.newConcurrentMap();
//		tt.put("1", "nihao");
//		return tt;
//	 }
    /*    public void initFieldValueConvertList(List<FieldValueConvertInfoBean> alljtbSourceList) {
     *//*******************************手工添加-开始*************************************//*
        //	result.put("0", "失败");
        //	result.put("1", "成功");
        FieldValueConvertInfoBean pushState=new FieldValueConvertInfoBean("remoteControl","allowVisit");
        pushState.putMapKeys("0","1").putMapValues("不允许","允许");
        alljtbSourceList.add(pushState);
    }*/

}
