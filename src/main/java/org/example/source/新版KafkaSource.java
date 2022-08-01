package org.example.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;

/***********************************
 *@Desc TODO
 *@ClassName 新版KafkaSource
 *@Author DLX
 *@Data 2022/7/28 14:30
 *@Since JDK1.8
 *@Version 1.0
 ***********************************/
public class 新版KafkaSource {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //<String>builder()前面代表的是从kafka里读出数据的格式
        KafkaSource<String> kafkaSource = KafkaSource.<String>builder()
                .setTopics("test_topic")
                .setGroupId("test_groupid")
                .setBootstrapServers("localhost:9092")
                //设置从最新开始消费
//                .setStartingOffsets(OffsetsInitializer.latest())
                //设置从自定义偏移量开始读取
//                .setStartingOffsets(OffsetsInitializer.offsets(new Map<TopicPartition,Long>()))
                //设置初始偏移量，从上一次程序停止时的偏移量消费，如果没有记录则重置为最新开始消费
                .setStartingOffsets(OffsetsInitializer.committedOffsets(OffsetResetStrategy.LATEST))
                .setValueOnlyDeserializer(new SimpleStringSchema())
                //把本算子设置为Bounded(有界流)，读到指定位置就停止读取并退出，常用于补数或重跑
//                .setBounded(OffsetsInitializer.latest())
                //把本算子设置为Unbounded(无界流)，读到指定位置就停止读取但是不退出，用于需要从kafka中读取一部分数据
//                .setUnbounded(OffsetsInitializer.latest())
                //开启了kafka消费者自动提交偏移量的机制，会定期将消费偏移量提交到_consumer_offset中
                .setProperty("enable.auto.commit", "true")
                .build();
        DataStreamSource<String> streamSource = env.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "");
        streamSource.print();
        env.execute();
    }
}
