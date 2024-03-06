package org.javaboy.commond.base;

public class Save implements Command {
    private Editor editor;
    public Save(Editor editor) {
        this.editor = editor;
    }
    @Override
    public void execute() {
        editor.save();
    }
}