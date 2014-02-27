package be.witspirit.mathex.textui.uimodel;

import org.junit.Assert;

import java.util.regex.Pattern;

/**
 * Wraps a line of Ui output to apply asserts
 */
public class UiLine {
    private String content;

    public UiLine(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public UiLine assertEquals(String expected) {
        Assert.assertEquals(expected, content);
        return this;
    }

    public UiLine assertMatches(String regexp) {
        Assert.assertTrue(content+" does not match regexp "+regexp, Pattern.matches(regexp, content));
        return this;
    }
}

