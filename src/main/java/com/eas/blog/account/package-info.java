@ApplicationModule(
        type = ApplicationModule.Type.OPEN,
        allowedDependencies = {"content::contentAPI", "content::contentDTO"}
        //allowedDependencies = "content")
)
package com.eas.blog.account;

import org.springframework.modulith.ApplicationModule;

