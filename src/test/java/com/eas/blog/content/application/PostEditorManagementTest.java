package com.eas.blog.content.application;

import com.eas.blog.account.application.dto.Editors;
import com.eas.blog.config.TestcontainersPostgresInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(initializers = TestcontainersPostgresInitializer.class)
class PostEditorManagementTest {

    @Autowired
    PostEditorManagement postEditorManagement;

    @Test
    void should_return_all_editors_for_post(){
        List<Editors> allEditorsForPost = postEditorManagement.findAllEditorsForPost(1);
        assertNotNull(allEditorsForPost);
    }
}