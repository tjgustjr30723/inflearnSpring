package hello.core.autowired;

import hello.core.member.Member;
import io.micrometer.common.lang.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
/*
        @Autowired(required = false)
        public void setNoBean1(Member noBear1) {
            System.out.println(noBear1);
        }
        @Autowired
        public void setNoBean2(@Nullable Member noBear2) {
            System.out.println(noBear2);
        }
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> noBear3) {
            System.out.println(noBear3);
        }*/
    }
}
