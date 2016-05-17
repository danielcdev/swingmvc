package com.danielcotter.swingmvc.framework;

import java.util.ArrayList;
import java.util.List;

import com.danielcotter.swingmvc.config.Configuration;
import com.danielcotter.swingmvc.inherited.Controller;

public class Pager {

	private static List<ControllerContainer> controllers = new ArrayList<ControllerContainer>();
	private static Object mutex = new Object();

	public static void loadController(String controllerName) {
		synchronized (mutex) {
			if (controllers.contains(new ControllerContainer(controllerName)))
				return;

			groundControl(controllerName);
		}
	}

	public static void renderController(String controllerName) {
		synchronized (mutex) {
			if (!controllers.contains(new ControllerContainer(controllerName)))
				return;

			controllers.get(controllers.indexOf(new ControllerContainer(controllerName))).getInstance().render();
		}
	}

	public static void launchController(String controllerName) {
		synchronized (mutex) {
			if (controllers.contains(new ControllerContainer(controllerName)))
				return;

			groundControl(controllerName);
			controllers.get(controllers.indexOf(new ControllerContainer(controllerName))).getInstance().render();
		}
	}

	@SuppressWarnings("unchecked")
	private static void groundControl(String controllerName) {
		try {
			ControllerContainer container = new ControllerContainer();
			container.setClassLoader((Class<Controller>) Class.forName(
					Configuration.getRequiredSetting("swingmvc.controller.autodetection") + "." + controllerName));
			container.setName(controllerName);
			container.setInstance(container.getClassLoader().newInstance());

			controllers.add(container);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
