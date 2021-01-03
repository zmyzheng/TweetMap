package io.zmyzheng.collector;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-02 19:34
 * @Version 3.0.0
 */
public interface SocialMediaCollector<T> {
    void start();

    T collect();

    void stop();
}
