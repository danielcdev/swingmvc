package com.danielcotter.swingmvc.framework;

import com.danielcotter.swingmvc.inherited.Controller;

public class ControllerContainer {

	private String name;
	private Class<Controller> classLoader;
	private Controller instance;

	public ControllerContainer() {

	}

	/**
	 * @param name
	 */
	public ControllerContainer(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	protected String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the classLoader
	 */
	protected Class<Controller> getClassLoader() {
		return classLoader;
	}

	/**
	 * @param classLoader
	 *            the classLoader to set
	 */
	protected void setClassLoader(Class<Controller> classLoader) {
		this.classLoader = classLoader;
	}

	/**
	 * @return the instance
	 */
	protected Controller getInstance() {
		return instance;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */
	protected void setInstance(Controller instance) {
		this.instance = instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControllerContainer other = (ControllerContainer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ControllerContainer [name=" + name + ", classLoader=" + classLoader + ", instance=" + instance + "]";
	}
}
