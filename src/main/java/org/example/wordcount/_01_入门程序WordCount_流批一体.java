package org.example.wordcount;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/***********************************
 *@Desc TODO
 *@ClassName _01_入门程序WordCount_流批一体
 *@Author DLX
 *@Data 2022/7/28 11:35
 *@Since JDK1.8
 *@Version 1.0
 ***********************************/
public class _01_入门程序WordCount_流批一体 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        //设置运行模式
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);
        DataStreamSource<String> streamSource = env.readTextFile("data/wc/input/wc.txt");
        SingleOutputStreamOperator<Tuple2<String, Integer>> flatMap = streamSource.flatMap(new MyFlatMapFunc());
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = flatMap.keyBy("f0").sum("f1");
        sum.print();
        env.execute();
    }
}
