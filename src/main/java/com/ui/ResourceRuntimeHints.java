package com.ui;

import gg.jte.generated.precompiled.JteindexGenerated;
import gg.jte.generated.precompiled.layout.JtemainGenerated;
import gg.jte.generated.precompiled.layout.JtemastervmGenerated;
import gg.jte.generated.precompiled.pagesjte.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.stereotype.Component;

@Component
public class ResourceRuntimeHints  implements RuntimeHintsRegistrar {

    private static final Logger logger = LoggerFactory.getLogger(ResourceRuntimeHints.class);
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader){

        logger.info("Registering hints for resources");
        hints.resources()
                .registerPattern("**/*.bin");
        hints.reflection()
                .registerType(JteindexGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.resources()
                .registerPattern("**/layout/*.bin");
        hints.reflection()
                .registerType(JtemainGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JtemastervmGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.resources()
                .registerPattern("**/pages-jte/*.bin");
        hints.reflection()
                .registerType(JtecustomerformvmGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JtedefaultformvmGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JteecordersvmGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JteemployeeformvmGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JtehomevmGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JteitemsvmGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JteorderformvmGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JteuserformvmGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);

    }

}
