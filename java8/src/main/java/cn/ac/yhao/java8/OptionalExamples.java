package cn.ac.yhao.java8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @description:
 * @author: Daniel Young
 * @create: 2021-10-09 22:48
 */
public class OptionalExamples {

    @Test
    public void demo() {
        Optional<String> a = Optional.of("a");
        Assertions.assertEquals("a", a.get());

        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);

        Assertions.assertFalse(emptyOptional.isPresent());
        Assertions.assertTrue(a.isPresent());

        Assertions.assertEquals("a", a.orElse("b"));
        Assertions.assertEquals("b", emptyOptional.orElse("b"));
        Assertions.assertEquals("c", emptyOptional.orElseGet(()->"c"));
    }

}
