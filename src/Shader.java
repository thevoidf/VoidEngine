import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    public static Shader BASIC = null;

    private int program;
    private int vert, frag;

    public Shader(String vertPath, String fragPath) {
        vert = compile(vertPath, GL_VERTEX_SHADER);
        frag = compile(fragPath, GL_FRAGMENT_SHADER);

        program = glCreateProgram();
        glAttachShader(program, vert);
        glAttachShader(program, frag);

        glLinkProgram(program);
        glValidateProgram(program);
    }

    public static void init() {
        BASIC = new Shader("res/shaders/basic.vert", "res/shaders/basic.frag");
    }

    public int compile(String filePath, int type) {
        int result = glCreateShader(type);
        String src = FileUtils.readFile(filePath);

        glShaderSource(result, src);
        glCompileShader(result);
        if (glGetShaderi(result, GL_COMPILE_STATUS) == GL_FALSE) {
            if (type == GL_VERTEX_SHADER)
                System.err.println("Vertex Shader failed to compile!");
            else
                System.err.println("Fragment Shader failed to compile!");
            System.out.println(glGetShaderInfoLog(result));
        }

        return result;
    }

    public int getUniformLocation(String name) {
        return glGetUniformLocation(program, name);
    }

    public void setUniform1i(String name, int value) {
        glUniform1i(getUniformLocation(name), value);
    }

    public void setUniiform1f(String name, float value) {
        glUniform1f(getUniformLocation(name), value);
    }

    public void setMat4f(String name, Matrix4f mat) {
        glUniformMatrix4fv(getUniformLocation(name), false, BufferUtils.createFloatBuffer(mat.elements));
    }

    public void bind() {
        glUseProgram(program);
    }

    public void unbind() {
        glUseProgram(0);
    }

}
