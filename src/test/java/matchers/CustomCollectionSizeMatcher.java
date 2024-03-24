package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import java.util.Collection;

public class CustomCollectionSizeMatcher extends TypeSafeMatcher<Collection<?>> {

    private final int expectedSize;

    public CustomCollectionSizeMatcher(int expectedSize) {
        this.expectedSize = expectedSize;
    }

    @Override
    protected boolean matchesSafely(Collection<?> collection) {
        return collection.size() == expectedSize;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a collection with size ").appendValue(expectedSize);
    }

    @Override
    protected void describeMismatchSafely(Collection<?> collection, Description mismatchDescription) {
        mismatchDescription.appendText("the collection size was ").appendValue(collection.size());
    }

    public static CustomCollectionSizeMatcher hasSize(int expectedSize) {
        return new CustomCollectionSizeMatcher(expectedSize);
    }
}
