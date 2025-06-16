package com.eas.blog.content.repository;

import com.eas.blog.account.application.dto.Editors;
import com.eas.blog.content.domain.PostEditor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostEditorRepository extends ListCrudRepository<PostEditor, Integer> {
    @Query("""
            SELECT a.name, a.email
            FROM author a JOIN post_editor pe ON a.id = pe.editor_id
            WHERE pe.post_id = :postId
            """)
    List<Editors> findAllEditorsByPostId(Integer postId);
}
