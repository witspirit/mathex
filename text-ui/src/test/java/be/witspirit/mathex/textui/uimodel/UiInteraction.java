package be.witspirit.mathex.textui.uimodel;

import be.witspirit.mathex.textui.TextUi;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of the Text UI from a Test perspective (inspired by the Page model pattern)
 */
public class UiInteraction {

    private TextUi ui;
    private String content;
    private List<UiLine> lines = new ArrayList<>();

    protected UiInteraction(TextUi ui, String content) {
        this.ui = ui;
        this.content = content;

        for (String line : content.split("\n")) {
            lines.add(new UiLine(line));
        }

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

    public UiInteraction assertLines(String... expextedLines) {
        String[] contentLines = content.split("\n");

        Assert.assertArrayEquals(expextedLines, contentLines);

        return this;
    }

    public UiLine line(int lineNr) {
        return lines.get(lineNr);
    }

}
