package test_for_1027;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestReview01AnswerA {

    @Test
    public void TestAnswerA() {
        // 时间戳1，这里以当前时间为例
        long timestamp1 = System.currentTimeMillis();

        // 时间戳2，这里以当前时间往前推12小时为例
        long timestamp2 = System.currentTimeMillis() - 12 * 60 * 60 * 1000;

        // 判断两个时间戳是否在同一天内
        boolean isSameDay = isTimestampInSameDay(timestamp1, timestamp2);

        System.out.println("时间戳 " + timestamp1 + " 和 " + timestamp2 + " 是否在同一天内: " + isSameDay);
    }

    public boolean isTimestampInSameDay(long timestamp1, long timestamp2) {
        // 将时间戳转换为本地日期时间
        LocalDateTime dateTime1 = LocalDateTime.ofInstant(ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp1),ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault()).withNano(0);
        LocalDateTime dateTime2 = LocalDateTime.ofInstant(ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp2),ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault()).withNano(0);

        // 判断两个时间戳是否在同一天内
        return dateTime1.toLocalDate().equals(dateTime2.toLocalDate());
    }

}
