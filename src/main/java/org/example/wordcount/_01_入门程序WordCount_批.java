package org.example.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/***********************************
 *@Desc TODO
 *@ClassName _01_入门程序WordCount_批
 *@Author DLX
 *@Data 2022/7/28 11:20
 *@Since JDK1.8
 *@Version 1.0
 ***********************************/
public class _01_入门程序WordCount_批 {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment batchEnv = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> textFile = batchEnv.readTextFile("data/wc/input/wc.txt");
        FlatMapOperator<String, Tuple2<String, Integer>> flatMapOperator = textFile.flatMap(new MyFlatMapFunc());
        AggregateOperator<Tuple2<String, Integer>> sum = flatMapOperator.groupBy(0).sum(1);
        sum.print();
//        batchEnv.execute();
    }
}
class MyFlatMapFunc implements FlatMapFunction<String,Tuple2<String, Integer>>{
    @Override
    public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
        String[] strings = s.split("_");
        for (String str : strings) {
            collector.collect(Tuple2.of(str, 1));
        }
    }
}