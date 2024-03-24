package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import java.util.List;

public class HasSizeMatcher extends TypeSafeMatcher<List<?>> {

    private final int expectedSize;

    public HasSizeMatcher(int expectedSize) {
        this.expectedSize = expectedSize;
    }

    @Override
    protected boolean matchesSafely(List<?> list) {
        return list.size() == expectedSize;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("expecting collection of size: ").appendValue(expectedSize);
    }

    @Override
    protected void describeMismatchSafely(List<?> list, Description mismatchDescription) {
        mismatchDescription.appendText("but collection size was: ").appendValue(list.size());
    }

    public static HasSizeMatcher hasSize(int size) {
        return new HasSizeMatcher(size);
    }
}
