package io.zmyzheng.processor;


/**
 * @Author Mingyang Zheng
 * @Date 2021-01-05 21:57
 * @Version 3.0.0
 */
public interface StreamProcessor {

    void configureStreamExecutionEnvironment();

    void addSource();

    void defineProcessingLogic();

    void addSink() throws Exception;

    void process() throws Exception;


}
