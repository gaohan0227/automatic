package priv.bigant.aotomatic.common.table;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import priv.bigant.aotomatic.common.BaseBean;
import priv.bigant.aotomatic.common.BaseEntity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class JoinTableToolsInfoBean {
    private Class<BaseEntity> mainTable;
    private SFunction<? extends BaseEntity,?> mainRefFieldName;
    private Class<BaseEntity> childTable;
    private SFunction<? extends BaseEntity,?> childRefFieldName;

    public Map<Class<BaseEntity>, SFunction<? extends BaseEntity,?>> findTableOneToOneFieldMap() {
        HashMap<Class<BaseEntity>,SFunction<? extends BaseEntity,?>> var1 = new HashMap();
        var1.put(this.mainTable, this.mainRefFieldName);
        var1.put(this.childTable, this.childRefFieldName);
        return var1;
    }

    public void setChildRefFieldName(SFunction<? extends BaseEntity,?> a) {
        this.childRefFieldName = a;
    }

    public JoinTableToolsInfoBean(Class<BaseEntity> mainTable, SFunction<? extends BaseEntity,?> b, Class<BaseEntity> childTable, SFunction<? extends BaseEntity,?> d) {
        this.mainTable = mainTable;
        this.mainRefFieldName = b;
        this.childTable = childTable;
        this.childRefFieldName = d;
    }

    public void setMainTable(Class<BaseEntity> a) {
        this.mainTable = a;
    }

    public SFunction<? extends BaseEntity,?> getChildRefFieldName() {
        return this.childRefFieldName;
    }

    public void setChildTable(Class<BaseEntity> a) {
        this.childTable = a;
    }

    public void setMainRefFieldName(SFunction<? extends BaseEntity,?> a) {
        this.mainRefFieldName = a;
    }

    public Class<BaseEntity> getMainTable() {
        return this.mainTable;
    }

    public SFunction<? extends BaseEntity,?> getMainRefFieldName() {
        return this.mainRefFieldName;
    }

    public Class<BaseEntity> getChildTable() {
        return this.childTable;
    }
}