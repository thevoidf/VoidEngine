import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.system.MemoryUtil.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {
	private String title;
	private int width, height;
	private boolean resizable;
	private boolean centered;

	private long window;

	private GLFWKeyCallback keyCallback;
	private GLFWMouseButtonCallback mouseButtonCallback;

	public Window(String title, int width, int height) {
		set(title, width, height, false, false);
		window = create(title, width, height, resizable, false);
	}

	public Window(String title, int width, int height, boolean resizable) {
		set(title, width, height, resizable, false);
		window = create(title, width, height, resizable, false);
	}

	public Window(String title, int width, int height, boolean resizable, boolean centered) {
		set(title, width, height, resizable, centered);
		window = create(title, width, height, resizable, centered);
	}

	private void set(String title, int width, int height, boolean resizable, boolean centered) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.resizable = resizable;
		this.centered = centered;
	}

	private void initGLFW() {
		if (glfwInit() != GLFW_TRUE)
			throw new IllegalStateException("Failed to initGLFW GLFW!");
	}

	private void initGL() {
		GL.createCapabilities();
	}

	private long create(String title, int width, int height, boolean resizable, boolean centered) {
		long window = 0L;

		initGLFW();
		GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		int wCenter = (vidMode.width() - width) / 2;
		int hCenter = (vidMode.height() - height) / 2;

		if (resizable)
			glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		else
			glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		if (centered) {
			glfwSetWindowPos(window, wCenter, hCenter);
		}
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		initGL();

		return window;
	}

	public void clear(boolean color, boolean depth) {
		if (color && depth)
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		else if (color)
			glClear(GL_COLOR_BUFFER_BIT);
		else
			glClear(GL_DEPTH_BUFFER_BIT);
	}

	public void clearColor(float r, float g, float b, float a) {
		glClearColor(r, g, b, a);
	}

	public void update() {
		glfwPollEvents();
		glfwSwapBuffers(window);
	}

	public void pollEvemts() {
		glfwPollEvents();
	}

	public void swapBuffers() {
		glfwSwapBuffers(window);
	}

	public void destroy() {
		glfwDestroyWindow(window);
		glfwTerminate();
	}

	public boolean closed() {
		return glfwWindowShouldClose(window) == GLFW_TRUE;
	}

	public void enableDepthTest() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	public void cullFace() {
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	public String getTile() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isResizable() {
		return resizable;
	}

	public boolean isCentered() {
		return centered;
	}

	public long getWindowID() {
		return window;
	}
}