package com.maxkudla.reserve.di.common;

public class Injector {
    @SuppressWarnings("unchecked")
    public static <C> C getComponent(Object o, Class<C> component) {
        return component.cast(((HasComponent) o).getComponent());
    }
}
