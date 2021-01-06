package io.zmyzheng.processor;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-05 23:47
 * @Version 3.0.0
 */
public class StreamProcessingDriver {

    private StreamProcessor processor;

    public StreamProcessingDriver(StreamProcessor processor) {
        this.processor = processor;
    }

    public void run() throws Exception {
        this.processor.addSource();
        this.processor.defineProcessingLogic();
        this.processor.addSink();
        this.processor.process();

    }
}
