package org.example.source;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.example.bean.EventLog;

import java.util.HashMap;


/***********************************
 *@Desc TODO
 *@ClassName 自定义Source
 *@Author DLX
 *@Data 2022/7/28 15:47
 *@Since JDK1.8
 *@Version 1.0
 ***********************************/
public class 自定义Source {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setParallelism(2);
        DataStreamSource<EventLog> streamSource = env.addSource(new MyRichSourceFunc());
        streamSource.map(JSON::toJSONString).print();
        env.execute();
    }
}

//单并行方法
class MySourceFunc implements SourceFunction<EventLog>{
    boolean flag = true;
    @Override
    public void run(SourceContext<EventLog> sourceContext) throws Exception {
        EventLog eventLog = new EventLog();
        HashMap<String, String> evetInfoMap = new HashMap<>();
        String[] event = {"appLaunch","pageLoad","adShow","adClick","iteamShare","iteamCollect","putBack","wakeUp","appClose"};
        while (flag){
            eventLog.setGuid(RandomUtils.nextLong(1,1000));
            eventLog.setEventId(event[RandomUtils.nextInt(0,event.length)]);
            eventLog.setSessionId(RandomStringUtils.randomAlphabetic(12).toUpperCase());
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
}

//多并行方法
class MyParallelSourceFunc implements ParallelSourceFunction<EventLog>{

    @Override
    public void run(SourceContext<EventLog> sourceContext) throws Exception {

    }

    @Override
    public void cancel() {

    }
}

class MyRichSourceFunc extends RichSourceFunction<EventLog>{
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
            eventLog.setSessionId(RandomStringUtils.randomAlphabetic(12).toUpperCase());
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

class MyRichParalleSourceFunc extends RichParallelSourceFunction<EventLog>{

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
    }

    @Override
    public void run(SourceContext<EventLog> sourceContext) throws Exception {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void close() throws Exception {
        super.close();
    }
}

////需要给IDEA引入Lombok插件
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//class EventLog{
//    private long guid;
//    private String sessionId;
//    private String eventId;
//    private long timeStamp;
//    private Map<String,String> eventInfo;
//}