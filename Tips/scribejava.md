#### scribejava 是一个oauh库

#### 如果错误显示不支持PATCH的httpMethod

可参考: https://stackoverflow.com/questions/25163131/httpurlconnection-invalid-http-method-patch

如果能改源码, 可用第一种, 如果不能改源码:

```
private void allowMethods(String... methods) {
    try {
        Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

        methodsField.setAccessible(true);

        String[] oldMethods = (String[]) methodsField.get(null);
        Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
        methodsSet.addAll(Arrays.asList(methods));
        String[] newMethods = methodsSet.toArray(new String[0]);

        methodsField.set(null/*static field*/, newMethods);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        throw new IllegalStateException(e);
    }
}
```

在用之前调用这个方法, 亲测可用, 很牛逼的样子