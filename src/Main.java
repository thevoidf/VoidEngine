import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class Main {

    private Random rand = new Random();

    private void start() {
        Window window = new Window("Void Renderer", 900, 600, true, true);

        Mesh triangle = new Mesh(new float[]{
                -0.5f, -0.5f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
        }, 3, new float[]{
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
        });

        Vector3f camera = new Vector3f(0.0f, 0.0f, -10.0f);
        Matrix4f model = Matrix4f.identity();
        Matrix4f pr = Matrix4f.translate(camera).
                multiply(Matrix4f.perspective(45.0f, 700 / 500, 0.1f, 100.0f));

        Texture boxTexture = new Texture("res/textures/stall.png");
        Shader.init();

        Model cubeModel = OBJLoader.loadOBjModel("res/models/stall.obj");
        Mesh cube = new Mesh(cubeModel.verticesArray, 3, cubeModel.indicesArray, cubeModel.textureArray);

        float delta = 0.0f;

        window.enableDepthTest();
//        window.cullFace();
        while (!window.closed()) {
            window.clear(true, true);
            window.clearColor(0.1f, 0.1f, 0.1f, 1.0f);

            delta = (float) GLFW.glfwGetTime();

            Shader.BASIC.bind();

            Shader.BASIC.setMat4f("pr", pr);
            
            boxTexture.bind();
        	model = Matrix4f.rotate(delta * 40, new Vector3f(0.0f, 1.0f, 0.0f))
        			.multiply(Matrix4f.translate(new Vector3f(0.0f, 0.0f, -10.0f)));
        	Shader.BASIC.setMat4f("model", model);
            cube.draw(cubeModel.verticesArray.length*3);
        
            Shader.BASIC.unbind();
            boxTexture.unbind();

            window.update();
        }
        window.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
