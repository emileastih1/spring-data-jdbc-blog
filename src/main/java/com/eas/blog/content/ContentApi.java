package com.eas.blog.content;

import com.eas.blog.account.application.dto.Editors;
import com.eas.blog.content.dto.PostCreateRequest;
import com.eas.blog.content.dto.PostSimpleView;
import com.eas.blog.content.dto.PostView;

import java.util.List;

public interface ContentApi {
    List<PostSimpleView> findAllPostsForAuthor(Integer authorId);

    List<PostSimpleView> findLatestPostsByAuthor(Integer authorId, Integer limit);

    List<Editors> findAllEditorsByPostId(Integer postId);

    PostView createPostForAuthorWithEditors(Integer mainAuthorId, List<Integer> editorIds, PostCreateRequest postCreateRequest);
}
