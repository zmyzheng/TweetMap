package io.zmyzheng.collector;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-02 19:30
 * @Version 3.0.0
 */
public interface Sinkable<T> {
    void connect();
    void send(T data);
    void close();
}
