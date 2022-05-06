package TestContext;

import com.google.gson.internal.Primitives;

import java.util.HashMap;
import java.util.Set;

public class TestContext extends HashMap<String, Object>  {

    public static ThreadLocal<TestContext> context = new ThreadLocal();

    public TestContext() {
    }

    public static TestContext getContext() {
        return (TestContext)context.get();
    }

    private static void setContext(TestContext cont) {
        context.set(cont);
    }

    public static TestContext getInstance() {
        setContext(new TestContext());
        return getContext();
    }


    public void setProperty(String property, Object value) {
        this.put(property, value);
    }

    public Object getProperty(String property) {
        Object propertyValue = this.get(property);
        if (propertyValue != null) {
            return propertyValue;
        } else {
            return null;
        }
    }

    public <Z> Z getProperty(String property, Class<Z> clazz) {
        Object propertyValue = this.get(property);
        if (this.get(property) != null) {
            return Primitives.wrap(clazz).cast(propertyValue);
        } else {
            return null;
        }
    }

    public Set<String> getProperties() {
        return this.keySet();
    }

    public HashMap<String, Object> getPropertiesMap() {
        return this;
    }

}
