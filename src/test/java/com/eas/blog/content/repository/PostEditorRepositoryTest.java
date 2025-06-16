package com.eas.blog.content.repository;

import com.eas.blog.account.application.dto.Editors;
import com.eas.blog.config.TestcontainersPostgresInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(initializers = TestcontainersPostgresInitializer.class)
class PostEditorRepositoryTest {

    @Autowired
    private PostEditorRepository postEditorRepository;

    @Test
    void findAllEditorsByPostId() {
        List<Editors> editors = postEditorRepository.findAllEditorsByPostId(1);
        assertNotNull(editors);
        assertFalse(editors.isEmpty());
    }
}