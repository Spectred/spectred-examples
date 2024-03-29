package pers.spectred.flink.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


/**
 * 单词统计（批数据处理）
 */
public class WordCountBatch {

    public static void main(String[] args) throws Exception {
        String inputPath = "/Users/swd/logs/flink/input.txt";
        String outputPath = "/Users/swd/logs/flink/output";

        // 1. 获取Flink执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // 2. 用Flink的运行环境，读取文件内容(待分析数据)
        DataSource<String> lines = env.readTextFile(inputPath);

        // 3. 对数据进行处理
        DataSet<Tuple2<String, Integer>> dataSet = lines
                // 将每一行数据进行映射转换
                .flatMap(new SplitLine())
                // 把相同的单词聚合到一起，Tuple(word,1),0表示word
                .groupBy(0)
                // 累加，field:1表示1号的field,即1
                .sum(1);
        // 4. 保存处理结果
        dataSet.writeAsText(outputPath);
        // 5. 触发执行程序
        env.execute("WordCount Batch Process");
    }

    /**
     * 如hello world ，映射为 (hello,1) ,(world,1)
     */
    static class SplitLine implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String line, Collector<Tuple2<String, Integer>> collector) throws Exception {
            for (String word : line.split(" "))
                collector.collect(new Tuple2<>(word, 1));
        }
    }
}
