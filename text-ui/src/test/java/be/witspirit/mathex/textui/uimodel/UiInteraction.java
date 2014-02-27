package be.witspirit.mathex.textui.uimodel;

import be.witspirit.mathex.textui.TextUi;

/**
 * Representation of the Text UI from a Test perspective (inspired by the Page model pattern)
 */
public class UiInteraction {

    protected TextUi ui;
    protected String content;

    protected UiInteraction(TextUi ui, String content) {
        this.ui = ui;
        this.content = content;
    }

    public static UiInteraction launch() {
        TextUi ui = new TextUi();
        return new UiInteraction(ui, ui.start());
    }

    public TextUi getUi() {
        return ui;
    }

    public String getContent() {
        return content;
    }

    public UiInteraction stop() {
        return new UiInteraction(ui, ui.command("S"));
    }

    public UiInteraction command(String command) {
        return new UiInteraction(ui, ui.command(command));
    }

}
