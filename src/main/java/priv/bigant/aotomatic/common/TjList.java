package priv.bigant.aotomatic.common;

import java.io.Serializable;
import java.util.ArrayList;

public class TjList<E> extends ArrayList<E> implements Serializable {

    public TjList<E> addCon(E a) {
        super.add(a);
        return this;
    }

    public TjList() {
    }

    public boolean add(E... a) {
        int var2;
        for(int var10000 = var2 = 0; var10000 < a.length; var10000 = var2) {
            super.add(a[var2++]);
        }

        return true;
    }

    public static <E> TjList<E> newInstance() {
        return new TjList();
    }
}
