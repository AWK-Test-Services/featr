package com.awk.featr.model.gherkin;

public class DocString extends StepArgument {
    private final String content;

    public DocString(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
