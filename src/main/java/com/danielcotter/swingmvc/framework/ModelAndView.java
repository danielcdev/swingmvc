package com.danielcotter.swingmvc.framework;

import java.awt.Window;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.danielcotter.swingmvc.annotation.Component;

public class ModelAndView {

    private View view;
    private Class<View> classLoader;
    private Map<String, JComponent> viewComponents = new HashMap<String, JComponent>();
    private Window window;

    @SuppressWarnings("unchecked")
    public ModelAndView(String viewName) {
	try {
	    this.classLoader = (Class<View>) Class.forName(viewName);
	    this.view = (View) classLoader.newInstance();
	    processViewAnnotations();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void render() {
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		view.renderView();
	    }
	});
    }

    public JComponent getComponent(String name) {
	return viewComponents.get(name);
    }

    public Window getWindow() {
	return window;
    }

    private void processViewAnnotations() throws IllegalArgumentException,
	    IllegalAccessException {
	for (Field field : classLoader.getDeclaredFields()) {
	    if (field.isAnnotationPresent(Component.class)
		    && JComponent.class.isAssignableFrom(field.getType()))
		processComponent(field);

	    if (field
		    .isAnnotationPresent(com.danielcotter.swingmvc.annotation.Window.class)
		    && Window.class.isAssignableFrom(field.getType()))
		processWindow(field);
	}
    }

    private void processComponent(Field field) throws IllegalArgumentException,
	    IllegalAccessException {
	String name = (field.getAnnotation(Component.class).name().equals("")) ? field
		.getName() : field.getAnnotation(Component.class).name();

	viewComponents.put(name, (JComponent) getFieldValue(field));
    }

    private void processWindow(Field field) throws IllegalArgumentException,
	    IllegalAccessException {
	window = (Window) getFieldValue(field);
    }

    private Object getFieldValue(Field field) throws IllegalArgumentException,
	    IllegalAccessException {
	boolean changeAccess = (!field.isAccessible()) ? true : false;
	Object object = null;

	if (changeAccess)
	    field.setAccessible(true);

	object = field.get(view);

	if (changeAccess)
	    field.setAccessible(false);

	return object;
    }
}
