package com.eas.blog;

import com.eas.blog.config.TestcontainersPostgresInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = TestcontainersPostgresInitializer.class)
class BlogJdbcApplicationTests {

    @Test
    void contextLoads() {
    }

}
