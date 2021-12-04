package cn.ac.yhao.java8.lambda.stream;

import cn.ac.yhao.java8.StreamExamples;
import mockit.Tested;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;


class StreamExamplesTest {

    @Tested
    StreamExamples streamExamples;

    @Test
    void addUp() {
        int s = streamExamples.addUp(Stream.of(1, 2, 3, 4));
        Assertions.assertEquals(s, 10);
    }

}
