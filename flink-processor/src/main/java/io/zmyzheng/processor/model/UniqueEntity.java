package io.zmyzheng.processor.model;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-10 19:05
 * @Version 3.0.0
 */
public interface UniqueEntity<T> {
    T getUniqueKey();
}
