package org.example.bean;

import lombok.*;

import java.util.Map;

/***********************************
 *@Desc TODO
 *@ClassName EventLog
 *@Author DLX
 *@Data 2022/8/1 16:01
 *@Since JDK1.8
 *@Version 1.0
 ***********************************/
//需要给IDEA引入Lombok插件
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventLog {
    private long guid;
    private String sessionId;
    private String eventId;
    private long timeStamp;
    private Map<String,String> eventInfo;
}
