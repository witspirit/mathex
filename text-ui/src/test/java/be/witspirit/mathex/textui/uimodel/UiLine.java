package be.witspirit.mathex.textui.uimodel;

import be.witspirit.mathex.Sum;
import be.witspirit.mathex.Sum3;
import be.witspirit.mathex.support.Sum3RepAdapter;
import be.witspirit.mathex.support.SumRep;
import be.witspirit.mathex.support.SumRepAdapter;
import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wraps a line of Ui output to apply asserts
 */
public class UiLine {
    private static final Pattern TWO_TERM_EXERCISE_PATTERN = Pattern.compile("(\\d+) (\\+|-) (\\d+) = ");
    private static final Pattern THREE_TERM_EXERCISE_PATTERN = Pattern.compile("(\\d+) \\+ (\\d+) \\+ (\\d+) = ");

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

    public Sum extractSum() {
        Matcher matcher = TWO_TERM_EXERCISE_PATTERN.matcher(content);
        if (matcher.matches()) {
            return new Sum(num(matcher.group(1)), matcher.group(2), num(matcher.group(3)));
        }
        Assert.fail("No exercise could be extracted from "+content);
        return null; // This is in fact unreachable... But since that is not visible through static inspection, I have to keep this statement
    }

    public Sum3 extractSum3() {
        Matcher matcher = THREE_TERM_EXERCISE_PATTERN.matcher(content);
        if (matcher.matches()) {
            return new Sum3(num(matcher.group(1)), num(matcher.group(2)), num(matcher.group(3)));
        }
        Assert.fail("No exercise could be extracted from "+content);
        return null; // This is in fact unreachable... But since that is not visible through static inspection, I have to keep this statement
    }

    public boolean isSum() {
        return TWO_TERM_EXERCISE_PATTERN.matcher(content).matches();
    }

    public void assertSum() {
        Assert.assertTrue("No Sum found", isSum());
    }

    public boolean isSum3() {
        return THREE_TERM_EXERCISE_PATTERN.matcher(content).matches();
    }

    public void assertSum3() {
        Assert.assertTrue("No Sum3 found", isSum3());
    }

    public SumRep extractSumRep() {
        if (isSum()) {
            return new SumRepAdapter(extractSum());
        } else if (isSum3()) {
            return new Sum3RepAdapter(extractSum3());
        }
        Assert.fail("No exercise could be extracted from "+content);
        return null; // This is in fact unreachable... But since that is not visible through static inspection, I have to keep this statement
    }

    private int num(String string) {
        return Integer.parseInt(string);
    }
}

