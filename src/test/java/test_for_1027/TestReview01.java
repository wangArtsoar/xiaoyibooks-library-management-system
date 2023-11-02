package test_for_1027;

import org.junit.jupiter.api.Test;

import java.time.*;

public class TestReview01 {

    @Test
    public void TestAnswerB(){
        // 时间戳1
        long timestamp1 = 1633022400000L;
        // 时间戳2
        long timestamp2 = 1633061199000L;

        boolean isSameDay = isTimestampInSameDay(timestamp1, timestamp2);
        System.out.println("时间戳 " + timestamp1 + " 和 " + timestamp2 + " 是否在同一天内: " + isSameDay);
    }

    public boolean isTimestampInSameDay(long timestamp1, long timestamp2) {
        // 将时间戳转换为Instant对象
        Instant instant1 = Instant.ofEpochMilli(timestamp1);
        Instant instant2 = Instant.ofEpochMilli(timestamp2);

        // 使用系统默认时区将Instant对象转换为LocalDate对象
        LocalDate localDate1 = LocalDate.ofInstant(instant1, ZoneId.systemDefault());
        LocalDate localDate2 = LocalDate.ofInstant(instant2, ZoneId.systemDefault());

        // 比较两个LocalDate对象是否相等
        return localDate1.isEqual(localDate2);
    }
}
