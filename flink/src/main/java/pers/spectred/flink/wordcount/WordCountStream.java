package pers.spectred.flink.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * 单词统计（流式数据）
 */
public class WordCountStream {

    public static void main(String[] args) throws Exception {
        // 1. 获取Flink流执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 2. 获取流式数据
        DataStreamSource<String> dataStreamSource = env.socketTextStream("localhost", 7777);
        // 3. 处理数据
        DataStream<Tuple2<String, Integer>> dataStream = dataStreamSource
                .flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String line, Collector<Tuple2<String, Integer>> collector) throws Exception {
                        for (String word : line.split(" ")) {
                            collector.collect(Tuple2.of(word, 1));
                        }
                    }
                })
                .keyBy(0)
                .sum(1);
        // 4. 数据打印
        dataStream.print();
        // 5. 执行
        env.execute("WordCount Stream Process");
    }
}
