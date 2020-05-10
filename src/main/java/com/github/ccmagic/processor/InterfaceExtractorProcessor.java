package com.github.ccmagic.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

public class InterfaceExtractorProcessor extends AbstractProcessor {

    private ProcessingEnvironment mProcessingEnvironment;

    public InterfaceExtractorProcessor(ProcessingEnvironment processingEnvironment) {
        this.mProcessingEnvironment = processingEnvironment;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        return false;
    }
}
