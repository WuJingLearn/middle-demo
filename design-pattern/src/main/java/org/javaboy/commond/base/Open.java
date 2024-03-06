package org.javaboy.commond.base;

public class Open implements Command {
    private Editor editor;
    public Open(Editor editor) {
        this.editor = editor;
    }
    @Override
    public void execute() {
        editor.open();
    }
}