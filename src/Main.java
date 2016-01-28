import org.lwjgl.glfw.GLFW;

public class Main {
    private void start() {
        Window window = new Window("Void Renderer", 900, 600, false, true);

//        Mesh triangle = new Mesh(new float[]{
//                -0.5f, -0.5f, 0.0f,
//                0.0f, 0.5f, 0.0f,
//                0.5f, -0.5f, 0.0f,
//        }, 3, new float[]{
//                0.0f, 0.0f,
//                1.0f, 0.0f,
//                1.0f, 1.0f,
//        });

        Vector3f camera = new Vector3f(-16.0f, -9.0f, -12.0f);
        Vector3f pos = new Vector3f(0.0f, 1.2f, 0.0f);
        Vector3f deadRot = new Vector3f(0.0f, 0.0f, 0.0f);
        
        Matrix4f model;
        Matrix4f pr; //Matrix4f.translate(camera).
//                multiply(Matrix4f.perspective(90.0f, 700 / 500, 0.1f, 100.0f));

        Texture bunnyTexture = new Texture("res/textures/fur_textures.jpg");
        Model bunny = OBJLoader.loadOBjModel("res/models/bunny.obj");
        Mesh cube = new Mesh(bunny.verticesArray, 3, bunny.indicesArray, bunny.textureArray);

        Texture deadPoolTexture = new Texture("res/textures/dead_tool.jpg");
        Model deadPoolModel = OBJLoader.loadOBjModel("res/models/dead_pool.obj");
        Mesh deadPool = new Mesh(deadPoolModel.verticesArray, 3, deadPoolModel.indicesArray, deadPoolModel.textureArray);

        Texture grassTexture = new Texture("res/textures/grass.jpg");
        Model terraModel = OBJLoader.loadOBjModel("res/models/tera.obj");
        Mesh terra = new Mesh(terraModel.verticesArray, 3, terraModel.indicesArray, terraModel.textureArray);
        
        float delta = 0.0f;

        window.enableDepthTest();
        window.cullFace();
        
        Shader.init();
        while (!window.closed()) {
            window.clear(true, true);
            window.clearColor(0.1f, 0.1f, 0.1f, 1.0f);

            delta = (float) GLFW.glfwGetTime();

            Shader.BASIC.bind();

//            camera = new Vector3f(-12.0f, -18.0f, -16.0f);
            pr = Matrix4f.translate(camera).
                    multiply(Matrix4f.rotate(-30.0f, new Vector3f(0.0f, 1.0f, 0.0f))).
                    multiply(Matrix4f.rotate(45.0f, new Vector3f(1.0f, 0.0f, 0.0f))).
                    multiply(Matrix4f.perspective(45.0f, 900/600, 0.1f, 100.0f));
            Shader.BASIC.setMat4f("pr", pr);

            if (GLFW.glfwGetMouseButton(window.getWindowID(), GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS) {
                grassTexture.bind();
                model = Matrix4f.scale(new Vector3f(1.4f, 1.4f, 1.4f))
                        .multiply(Matrix4f.rotate(-100, new Vector3f(0.0f, 1.0f, 0.0f)))
                        .multiply(Matrix4f.translate(new Vector3f(0.0f, 0.0f, -2.0f)));
                Shader.BASIC.setMat4f("model", model);
                terra.draw(terraModel.verticesArray.length * 2);
                grassTexture.unbind();
                continue;
            }
            
            bunnyTexture.bind();
        	model = Matrix4f.scale(new Vector3f(0.5f, 0.5f, 0.5f))
        			.multiply(Matrix4f.rotate(deadRot.y, new Vector3f(0.0f, 1.0f, 0.0f)))
        			.multiply(Matrix4f.translate(pos));
        	Shader.BASIC.setMat4f("model", model);
            cube.draw(bunny.verticesArray.length*2);
            bunnyTexture.unbind();
                        
            deadPoolTexture.bind();
        	model = Matrix4f.scale(new Vector3f(0.5f, 0.5f, 0.5f))
        			.multiply(Matrix4f.rotate(-45.0f, new Vector3f(0.0f, 1.0f, 0.0f)))
        			.multiply(Matrix4f.translate(new Vector3f(0.0f, 0.0f, -6.0f)));
        	Shader.BASIC.setMat4f("model", model);
            deadPool.draw(deadPoolModel.verticesArray.length*2);
            deadPoolTexture.unbind();
                        
            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS)
            	pos.z -= 0.05f;
            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS)
            	pos.z += 0.05f;
            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS)
            	pos.x -= 0.05f;
            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS)
            	pos.x += 0.05f;
            	
//            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS)
//            	deadRot.y += 2.4f;
//            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS)
//            	deadRot.y -= 2.4f;

            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS)
                camera.x++;
            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS)
                camera.x--;
            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_UP) == GLFW.GLFW_PRESS)
                camera.y++;
            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_DOWN) == GLFW.GLFW_PRESS)
                camera.y--;
            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_DOWN) == GLFW.GLFW_PRESS
                    && GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS)
                camera.z++;
            if (GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_UP) == GLFW.GLFW_PRESS
                    && GLFW.glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS)
                camera.z--;


            Shader.BASIC.unbind();

            window.update();
        }
        window.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
