package org.example.sink;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.example.bean.EventLog;
import scala.Tuple4;


import java.util.HashMap;
import java.util.Map;


/***********************************
 *@Desc TODO
 *@ClassName csvSink
 *@Author DLX
 *@Data 2022/8/1 15:42
 *@Since JDK1.8
 *@Version 1.0
 ***********************************/
public class CsvSink {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<EventLog> streamSource = env.addSource(new MyRichSourceFunc());
        streamSource.map(new MapFunction<EventLog, Tuple2<String,String>>() {
            @Override
            public Tuple2<String, String> map(EventLog eventLog) throws Exception {
                return Tuple2.of(eventLog.getEventId(),eventLog.getSessionId());
            }
        }).writeAsCsv("data/sink/output/test1", FileSystem.WriteMode.OVERWRITE);
        env.execute("");
    }
}

class MyRichSourceFunc extends RichSourceFunction<EventLog> {
    boolean flag = true;
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        RuntimeContext runtimeContext = getRuntimeContext();
        //获取当前subtask名称
        String taskName = runtimeContext.getTaskName();
        //获取当前subTaskId
        int indexOfThisSubtask = runtimeContext.getIndexOfThisSubtask();

    }

    @Override
    public void run(SourceContext<EventLog> sourceContext) throws Exception {
        EventLog eventLog = new EventLog();
        HashMap<String, String> evetInfoMap = new HashMap<>();
        String[] event = {"appLaunch","pageLoad","adShow","adClick","iteamShare","iteamCollect","putBack","wakeUp","appClose"};
        while (flag){
            eventLog.setGuid(RandomUtils.nextLong(1,1000));
            eventLog.setEventId(event[RandomUtils.nextInt(0,event.length)]);
            //加长sessionId长度便于观察文件生成情况
            eventLog.setSessionId(RandomStringUtils.randomAlphabetic(1024).toUpperCase());
            eventLog.setTimeStamp(System.currentTimeMillis());
            evetInfoMap.put(RandomStringUtils.randomAlphabetic(1),RandomStringUtils.randomAlphabetic(2));
            eventLog.setEventInfo(evetInfoMap);
            sourceContext.collect(eventLog);
            evetInfoMap.clear();
            Thread.sleep(1000);
        }
    }

    @Override
    public void cancel() {
        flag = false;
    }

    @Override
    public void close() throws Exception {
        super.close();
        System.out.println("组件被关闭了·······");
    }
}
