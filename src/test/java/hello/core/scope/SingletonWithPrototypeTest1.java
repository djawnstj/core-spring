package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Prototype.class);
        Prototype prototype1 = ac.getBean(Prototype.class);
        prototype1.addCount();
        assertThat(prototype1.getCount()).isEqualTo(1);

        Prototype prototype2 = ac.getBean(Prototype.class);
        prototype2.addCount();
        assertThat(prototype2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, Prototype.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);

    }

    @Scope("singleton")
    static class ClientBean {

        private final Prototype prototype;

        @Autowired
        public ClientBean(Prototype prototype) {
            this.prototype = prototype;
        }

        public int logic() {
            prototype.addCount();
            return prototype.getCount();
        }

    }

    @Scope("prototype")
    static class Prototype {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("Prototype.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Prototype.destroy");
        }

    }

}
