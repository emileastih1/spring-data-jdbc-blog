@org.springframework.modulith.ApplicationModule(
        type = ApplicationModule.Type.OPEN,
        allowedDependencies = {"content::dto", "content"}
)
package com.eas.blog.account;

import org.springframework.modulith.ApplicationModule;