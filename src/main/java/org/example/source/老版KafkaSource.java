package org.example.source;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;

/***********************************
 *@Desc TODO
 *@ClassName 新版KafkaSource
 *@Author DLX
 *@Data 2022/7/28 14:21
 *@Since JDK1.8
 *@Version 1.0
 ***********************************/
public class 老版KafkaSource {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //设置Kafka相关参数
        Properties properties = new Properties();
        //设置Kafka的地址和端口
        properties.setProperty("bootstrap.servers", "59.XXX.XXX.35:9092,59.XXX.XXX.36:9092,59.XXX.XXX.37:9092");
        //读取偏移量策略：如果没有记录偏移量，就从头读，如果记录过偏移量，就接着读
        properties.setProperty("auto.offset.reset", "earliest");
        //设置消费者组ID
        properties.setProperty("group.id", "group1");
        //没有开启checkpoint，让flink提交偏移量的消费者定期自动提交偏移量
        properties.setProperty("enable.auto.commit", "true");
        //创建FlinkKafkaConsumer并传入相关参数
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(
                "mytest", //要读取数据的Topic名称
                new SimpleStringSchema(), //读取文件的反序列化Schema
                properties //传入Kafka的参数
        );
        //使用addSource添加kafkaConsumer
        DataStreamSource<String> kafkaStream = env.addSource(kafkaConsumer);
        System.out.println("kafkaStream创建的DataStream并行度为："+kafkaStream.getParallelism());
        kafkaStream.print();
        env.execute("");
    }
}
