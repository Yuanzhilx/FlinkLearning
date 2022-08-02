package org.example.sink;

import com.alibaba.fastjson.JSON;
import org.apache.avro.Schema;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.file.sink.FileSink;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.core.fs.Path;
import org.apache.flink.formats.parquet.ParquetBulkWriter;
import org.apache.flink.formats.parquet.ParquetWriterFactory;
import org.apache.flink.formats.parquet.avro.ParquetAvroWriters;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.OutputFileConfig;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.DateTimeBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.OnCheckpointRollingPolicy;
import org.example.avro.schema.AvroEventLogBean;
import org.example.bean.EventLog;

import java.util.HashMap;
import java.util.Map;

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
        env.enableCheckpointing(2000, CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setCheckpointStorage("file:///d:/ckpt");
        env.setParallelism(2);
        DataStreamSource<EventLog> streamSource = env.addSource(new MyRichSourceFunc());
        SingleOutputStreamOperator<String> jsonStream = streamSource.map(new RichMapFunction<EventLog, String>() {
            @Override
            public String map(EventLog eventLog) throws Exception {
                return JSON.toJSONString(eventLog);
            }
        });
        //forRowFormat 按行写
//        FileSink<String> rowFileSink = FileSink.forRowFormat(new Path("data/sink/output/streamFileSink"), new SimpleStringEncoder<String>("utf-8"))
//                //文件滚动策略（间隔10s或文件大小达到1M）
//                .withRollingPolicy(DefaultRollingPolicy.builder().withRolloverInterval(10 * 1000).withMaxPartSize(1024 * 1024).build())
//                //分桶策略如何划分子文件夹
//                .withBucketAssigner(new DateTimeBucketAssigner<String>())
//                //多长时间检查一次是否需要创建文件夹
//                .withBucketCheckInterval(5)
//                //输出文件的配置
//                .withOutputFileConfig(OutputFileConfig.builder().withPartPrefix("zhibai").withPartSuffix(".txt").build()).build();
//        jsonStream.sinkTo(rowFileSink);

        //forBulkFormat 按批写
        //1.手动构造schema，forGenericRecord方式下需要自行传入schema对象
//        Schema schema = Schema.createRecord("id", "用户id", "com.example", true);
//        ParquetAvroWriters.forGenericRecord(schema);//需要写avrc文件，根据模式生成Writer

        //2.需要写avsc文件，https://avro.apache.org/docs/1.11.0/gettingstartedjava.html
        //插件编译的时候就可以自动执行，生成对应bean文件
//        ParquetWriterFactory<AvroEventLogBean> writerFactory = ParquetAvroWriters.forSpecificRecord(AvroEventLogBean.class);//根据模式生成Writer
//        FileSink<AvroEventLogBean> parquetSink = FileSink.forBulkFormat(new Path("data/sink/output/streamFileSink"), writerFactory)
//                .withBucketAssigner(new DateTimeBucketAssigner<AvroEventLogBean>())
//                .withBucketCheckInterval(5)
//                .withRollingPolicy(OnCheckpointRollingPolicy.build())//Bulk模式下滚动条件只有一种，当发生CheckPoint的时候才会滚动文件
//                .withOutputFileConfig(OutputFileConfig.builder().withPartPrefix("zhibai").withPartSuffix(".parquet").build()).build();
//
//        streamSource.map(eventLogBean -> {
//                    HashMap<CharSequence,CharSequence> eventInfo = new HashMap<>();
//                    for (Map.Entry<String, String> entry : eventLogBean.getEventInfo().entrySet()) {
//                        eventInfo.put(entry.getKey(),entry.getValue());
//                    }
//                    return new AvroEventLogBean(eventLogBean.getGuid(),eventLogBean.getSessionId(),eventLogBean.getEventId(),eventLogBean.getTimeStamp(),eventInfo);
//                }).returns(AvroEventLogBean.class).sinkTo(parquetSink);

        //3.利用Avro规范Bean对象，来生成ParquetAvroWriter工厂
        FileSink<EventLog> parquetSink = FileSink.forBulkFormat(new Path("data/sink/output/streamFileSink"), ParquetAvroWriters.forReflectRecord(EventLog.class))
                .withBucketAssigner(new DateTimeBucketAssigner<EventLog>())
                .withBucketCheckInterval(5)
                .withRollingPolicy(OnCheckpointRollingPolicy.build())//Bulk模式下滚动条件只有一种，当发生CheckPoint的时候才会滚动文件
                .withOutputFileConfig(OutputFileConfig.builder().withPartPrefix("zhibai").withPartSuffix(".parquet").build()).build();
        streamSource.sinkTo(parquetSink);
        env.execute("");
    }
}
