package priv.bigant.aotomatic.security;

public interface Security<T> {

    void add(String id,T obj);

    void remove();

    void updateObj(T obj);

    String getId();
}
