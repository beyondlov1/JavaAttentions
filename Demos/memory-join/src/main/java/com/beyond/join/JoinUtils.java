package com.beyond.join;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JoinUtils {


    public static <T2> RootJoinResult<T2> start(List<T2> schools, Class<T2> rightClass) {
        return new RootJoinResult<T2>(schools, rightClass);
    }


    private static <T1, T2> Map<T1, List<T2>> one2ManyJoin(List<T1> list1, List<T2> list2, Function<T1, Object> key1, Function<T2, Object> key2) {
        Map<T1, List<T2>> result = new HashMap<>();
        Map<Object, List<T2>> t2GroupMap = list2.stream().collect(Collectors.groupingBy(key2));
        for (T1 t1 : list1) {
            List<T2> t2ListOfT1 = t2GroupMap.get(key1.apply(t1));
            result.put(t1, t2ListOfT1);
        }
        return result;
    }

    private static <T1, T2> Object Many2OneJoin(List<T1> list1, List<T2> list2, Function<T1, Object> key1, Function<T2, Object> key2) {
        return null;
    }

    private static <T1, T2> Map<T1, T2> one2OneJoin(List<T1> list1, List<T2> list2, Function<T1, Object> key1, Function<T2, Object> key2) {
        Map<T1, T2> result = new HashMap<>();
        Map<Object, T2> t2GroupMap = list2.stream().collect(Collectors.toMap(key2, t2 -> t2, (v1, v2) -> v2));
        for (T1 t1 : list1) {
            T2 t2OfT1 = t2GroupMap.get(key1.apply(t1));
            result.put(t1, t2OfT1);
        }
        return result;
    }

    static class JoinResultChain {
        private List<JoinResult<?, ?>> joinResults = new ArrayList<>();
        private Map<Class, JoinResult> joinResultMap = new HashMap<>();

        public <T1, T2> void addJoinResult(JoinResult<T1, T2> joinResult, Class<T1> leftClass, Class<T2> rightClass) {
            joinResults.add(joinResult);
            if (joinResultMap.get(leftClass) != null){
                throw new RuntimeException("join class duplicate (no duplicate class is allowed between start() and end())");
            }
            joinResultMap.put(leftClass, joinResult);
        }

        @SuppressWarnings("unchecked")
        public <T1, T2> Map<T1, List<T2>> getOne2ManyMap(Class<T1> t1Class, Class<T2> t2Class) {
            Map<T1, List<T2>> data = ((One2ManyJoinResult<T1, T2>) (joinResultMap.get(t1Class))).data;
            checkType(data.values().stream().filter(Objects::nonNull).collect(Collectors.toList()), t2Class);
            return data;
        }

        @SuppressWarnings("unchecked")
        public <T1, T2> Map<T1, T2> getOne2OneMap(Class<T1> t1Class, Class<T2> t2Class) {
            Map<T1, T2> data = ((One2OneJoinResult<T1, T2>) (joinResultMap.get(t1Class))).data;
            checkType(data.values(), t2Class);
            return data;
        }

        @SuppressWarnings("unchecked")
        private void checkType(Collection collection, Class cls){
            collection.stream().findAny().ifPresent(
                    x -> {
                        if (!cls.isAssignableFrom(x.getClass())) {
                            throw new RuntimeException(String.format("class is not right, require:%s; provided:%s", cls.getSimpleName(), x.getClass().getSimpleName()));
                        }
                    }
            );
        }

        @Override
        public String toString() {
            return "JoinResultChain{" +
                    "joinResults=" + joinResults +
                    ", joinResultMap=" + joinResultMap +
                    '}';
        }


    }

    interface JoinResult<T1, T2> {
        <S2> JoinResult<T2, S2> one2ManyJoin(List<S2> list2, Class<S2> s2Class, Function<T2, Object> key1, Function<S2, Object> key2);

        <S2> JoinResult<T2, S2> one2OneJoin(List<S2> list2, Class<S2> s2Class, Function<T2, Object> key1, Function<S2, Object> key2);

        Class<T1> getLeftClass();

        Class<T2> getRightClass();

        JoinResultChain end();
    }

    static abstract class AbstractJoinResult<T1, T2> implements JoinResult<T1, T2> {

    }


    static class One2ManyJoinResult<T1, T2> implements JoinResult<T1, T2> {

        private Map<T1, List<T2>> data;
        private Class<T1> leftClass;
        private Class<T2> rightClass;
        private JoinResultChain joinResultChain;

        public One2ManyJoinResult(Map<T1, List<T2>> data, JoinResultChain joinResultChain, Class<T1> leftClass, Class<T2> rightClass) {
            this.data = data;
            this.leftClass = leftClass;
            this.rightClass = rightClass;
            this.joinResultChain = joinResultChain;
            joinResultChain.addJoinResult(this, leftClass, rightClass);

        }

        @Override
        public <S2> One2ManyJoinResult<T2, S2> one2ManyJoin(List<S2> list2, Class<S2> s2Class, Function<T2, Object> key1, Function<S2, Object> key2) {
            if (data == null || data.size() == 0) {
                return new One2ManyJoinResult<>(new HashMap<>(), joinResultChain, rightClass, s2Class);
            }
            List<T2> leftList = data.values().stream().filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
            Map<T2, List<S2>> t2ListMap = JoinUtils.one2ManyJoin(leftList, list2, key1, key2);
            return new One2ManyJoinResult<>(t2ListMap, joinResultChain, rightClass, s2Class);
        }

        @Override
        public <S2> One2OneJoinResult<T2, S2> one2OneJoin(List<S2> list2, Class<S2> s2Class, Function<T2, Object> key1, Function<S2, Object> key2) {
            if (data == null || data.size() == 0) {
                return new One2OneJoinResult<>(new HashMap<>(), joinResultChain, rightClass, s2Class);
            }
            List<T2> leftList = data.values().stream().filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
            Map<T2, S2> t2ListMap = JoinUtils.one2OneJoin(leftList, list2, key1, key2);
            return new One2OneJoinResult<>(t2ListMap, joinResultChain, rightClass, s2Class);
        }

        @Override
        public Class<T1> getLeftClass() {
            return leftClass;
        }

        @Override
        public Class<T2> getRightClass() {
            return rightClass;
        }

        @Override
        public JoinResultChain end() {
            return joinResultChain;
        }

        @Override
        public String toString() {
            return "One2ManyJoinResult{" +
                    "data=" + data +
                    ", leftClass=" + leftClass +
                    ", rightClass=" + rightClass +
                    '}';
        }
    }


    static class One2OneJoinResult<T1, T2> implements JoinResult<T1, T2> {

        private Map<T1, T2> data;
        private Class<T1> leftClass;
        private Class<T2> rightClass;
        private JoinResultChain joinResultChain;

        public One2OneJoinResult(Map<T1, T2> data, JoinResultChain joinResultChain, Class<T1> leftClass, Class<T2> rightClass) {
            this.data = data;
            this.leftClass = leftClass;
            this.rightClass = rightClass;
            this.joinResultChain = joinResultChain;
            joinResultChain.addJoinResult(this, leftClass, rightClass);
        }

        @Override
        public <S2> One2ManyJoinResult<T2, S2> one2ManyJoin(List<S2> list2, Class<S2> s2Class, Function<T2, Object> key1, Function<S2, Object> key2) {
            if (data == null || data.size() == 0) {
                return new One2ManyJoinResult<>(new HashMap<>(), joinResultChain, rightClass, s2Class);
            }
            List<T2> leftList = data.values().stream().filter(Objects::nonNull).collect(Collectors.toList());
            Map<T2, List<S2>> t2ListMap = JoinUtils.one2ManyJoin(leftList, list2, key1, key2);
            return new One2ManyJoinResult<>(t2ListMap, joinResultChain, rightClass, s2Class);
        }

        @Override
        public <S2> One2OneJoinResult<T2, S2> one2OneJoin(List<S2> list2, Class<S2> s2Class, Function<T2, Object> key1, Function<S2, Object> key2) {
            if (data == null || data.size() == 0) {
                return new One2OneJoinResult<>(new HashMap<>(), joinResultChain, rightClass, s2Class);
            }
            List<T2> leftList = data.values().stream().filter(Objects::nonNull).collect(Collectors.toList());
            Map<T2, S2> t2ListMap = JoinUtils.one2OneJoin(leftList, list2, key1, key2);
            return new One2OneJoinResult<>(t2ListMap, joinResultChain, rightClass, s2Class);
        }

        @Override
        public Class<T1> getLeftClass() {
            return leftClass;
        }

        @Override
        public Class<T2> getRightClass() {
            return rightClass;
        }

        @Override
        public JoinResultChain end() {
            return joinResultChain;
        }

        @Override
        public String toString() {
            return "One2ManyJoinResult{" +
                    "data=" + data +
                    ", leftClass=" + leftClass +
                    ", rightClass=" + rightClass +
                    '}';
        }
    }


    public static class RootJoinResult<T> extends One2ManyJoinResult<Root, T> {

        public RootJoinResult(List<T> data, Class<T> tClass) {
            this(Collections.singletonMap(Root.INSTANCE, data), new JoinResultChain(), Root.class, tClass);
        }

        public RootJoinResult(Map<Root, List<T>> data, JoinResultChain joinResultChain, Class<Root> leftClass, Class<T> rightClass) {
            super(data, joinResultChain, leftClass, rightClass);
        }
    }

    public static class Root {
        public static final Root INSTANCE = new Root();
    }
}
