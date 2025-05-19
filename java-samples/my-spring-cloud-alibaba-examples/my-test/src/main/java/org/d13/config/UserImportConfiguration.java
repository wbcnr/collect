package org.d13.config;

import org.d13.support.MyImportBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(MyImportBean.class)
@Configuration
public class UserImportConfiguration {

}
