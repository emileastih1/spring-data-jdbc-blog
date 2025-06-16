package com.eas.blog.content.application;

import com.eas.blog.account.application.dto.Editors;
import com.eas.blog.content.repository.PostEditorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostEditorManagement {

    private final PostEditorRepository postEditorRepository;

    public List<Editors> findAllEditorsForPost(Integer postId) {
        return postEditorRepository.findAllEditorsByPostId(postId);
    }
}
