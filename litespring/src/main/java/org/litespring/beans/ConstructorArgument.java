package org.litespring.beans;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/21 下午11:53
 **/
public class ConstructorArgument {

    private List<ValueHolder> argumentValues = new LinkedList<>();

    public void addArgumentValueHolder(ValueHolder holder) {
        this.argumentValues.add(holder);
    }

    public List<ValueHolder> getArgumentValues() {
        return Collections.unmodifiableList(this.argumentValues);
    }

    public int getArgumentCount(){ return this.argumentValues.size();}

    public boolean isEmpty() {
        return this.argumentValues.isEmpty();
    }

    public void clear() {
        this.argumentValues.clear();
    }

    public static class ValueHolder {

        private Object value;
        private String type;
        private String name;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public ValueHolder(Object value, String type) {
            this.value = value;
            this.type = type;
        }

        public ValueHolder(Object value, String type, String name) {
            this.value = value;
            this.type = type;
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
