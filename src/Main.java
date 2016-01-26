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

        Vector3f camera = new Vector3f(0.0f, 0.0f, -20.0f);
        Matrix4f model = Matrix4f.identity();
        Matrix4f pr = Matrix4f.translate(camera).
                multiply(Matrix4f.perspective(45.0f, 700 / 500, 0.1f, 100.0f));

//        Texture wall = new Texture("res/textures/wall.jpg");
        Shader.init();

        Vector3f[] dirs = new Vector3f[30];
        for (int i = 0; i < 30; i++) {
            dirs[i] = new Vector3f(
                    rand.nextFloat() * (float) rand.nextGaussian() * 6.0f,
                    rand.nextFloat() * (float) rand.nextGaussian() * 16.0f,
                    rand.nextFloat() * (float) rand.nextGaussian() * 6.0f
            );
        }

        Model cubeModel = OBJLoader.loadOBjModel("res/models/cube.obj");
        Mesh cube = new Mesh(cubeModel.verticesArray, 3, new float[] {
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,

                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,

                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,

                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,

                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,

                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
        }, 3, cubeModel.indicesArray);

        float delta = 0.0f;
        float tmp = 0.0f;

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        while (!window.closed()) {
            window.clear(true, true);
            window.clearColor(0.1f, 0.1f, 0.1f, 1.0f);

            delta = (float) GLFW.glfwGetTime();

            Shader.BASIC.bind();
            camera.z += delta * 0.005f;
            pr = Matrix4f.translate(camera).
                    multiply(Matrix4f.perspective(45.0f, 700 / 500, 0.1f, 100.0f));
            Shader.BASIC.setMat4f("pr", pr);

//            for (int i = 0; i < dirs.length; i++) {
//                model = Matrix4f.translate(dirs[i])
//                        .multiply(new Matrix4f().rotate(delta * 180, new Vector3f(0.0f, 1.0f, 0.0f)));
//                Shader.BASIC.setMat4f("model", model);
//                cube.draw(36);
//            }
            triangle.draw(3);
            Shader.BASIC.unbind();

            window.update();
        }
        window.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
