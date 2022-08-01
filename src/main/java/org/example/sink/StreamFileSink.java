package org.example.sink;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.file.sink.FileSink;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.OutputFileConfig;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.DateTimeBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;
import org.example.bean.EventLog;

/***********************************
 *@Desc TODO
 *@ClassName StreamFileSink
 *@Author DLX
 *@Data 2022/8/1 16:54
 *@Since JDK1.8
 *@Version 1.0
 ***********************************/
public class StreamFileSink {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<EventLog> streamSource = env.addSource(new MyRichSourceFunc());
        SingleOutputStreamOperator<String> jsonStream = streamSource.map(new RichMapFunction<EventLog, String>() {
            @Override
            public String map(EventLog eventLog) throws Exception {
                return JSON.toJSONString(eventLog);
            }
        });
        FileSink<String> rowFileSink = FileSink.forRowFormat(new Path("data/sink/output/streamFileSink"), new SimpleStringEncoder<String>("utf-8"))
                //文件滚动策略（间隔10s或文件大小达到1M）
                .withRollingPolicy(DefaultRollingPolicy.builder().withRolloverInterval(10 * 1000).withMaxPartSize(1024 * 1024).build())
                //分桶策略如何划分子文件夹
                .withBucketAssigner(new DateTimeBucketAssigner<String>())
                //多长时间检查一次是否需要创建文件夹
                .withBucketCheckInterval(5)
                //输出文件的配置
                .withOutputFileConfig(OutputFileConfig.builder().withPartPrefix("zhibai").withPartSuffix(".txt").build()).build();
        jsonStream.sinkTo(rowFileSink);
        env.execute("");
    }
}
