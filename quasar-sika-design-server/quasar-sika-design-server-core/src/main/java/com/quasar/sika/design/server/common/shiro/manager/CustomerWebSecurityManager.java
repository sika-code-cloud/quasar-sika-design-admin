package com.quasar.sika.design.server.common.shiro.manager;

import com.quasar.sika.design.server.common.shiro.context.CustomerSubjectContext;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.subject.WebSubjectContext;

/**
 * @author daiqi
 * @create 2021-01-12 0:14
 */
public class CustomerWebSecurityManager extends DefaultWebSecurityManager {
    @Override
    protected SessionContext createSessionContext(SubjectContext subjectContext) {
        subjectContext = createSubjectContext();
        return super.createSessionContext(subjectContext);
    }

    @Override
    protected SubjectContext copy(SubjectContext subjectContext) {
        return new CustomerSubjectContext((WebSubjectContext) subjectContext);
    }

    @Override
    protected SubjectContext createSubjectContext() {
        return new CustomerSubjectContext();
    }
}
