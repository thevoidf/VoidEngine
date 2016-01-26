import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Mesh {

    private int vao, vbos[];

    private boolean isQuad = false;

    public Mesh(float[] vertices, int componentCount) {

        FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertBuffer.put(vertices).flip();

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbos = new int[1];

        vbos[0] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[0]);
        glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, componentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public Mesh(FloatBuffer verices, int componentCount) {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbos = new int[1];

        vbos[0] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[0]);
        glBufferData(GL_ARRAY_BUFFER, verices, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, componentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public Mesh(FloatBuffer vertices, int vertexComponentCount, ByteBuffer indices) {
        isQuad = true;
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbos = new int[2];

        vbos[0] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[0]);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, vertexComponentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        vbos[1] = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbos[1]);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glBindVertexArray(0);
    }

    public Mesh(float[] vertices, int vertexComponentCount, float[] colors, int colorComponentCount) {

        FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertBuffer.put(vertices).flip();

        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorBuffer.put(colors).flip();

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbos = new int[2];

        vbos[0] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[0]);
        glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, vertexComponentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        vbos[1] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[1]);
        glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, colorComponentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindVertexArray(0);
    }

    public Mesh(float[] vertices, int componentCount, float[] uvs) {

        FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertBuffer.put(vertices).flip();

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbos = new int[2];

        vbos[0] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[0]);
        glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, componentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        FloatBuffer uvBuffer = BufferUtils.createFloatBuffer(uvs.length);
        uvBuffer.put(uvs).flip();

        vbos[1] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[1]);
        glBufferData(GL_ARRAY_BUFFER, uvBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindVertexArray(0);
    }

    public Mesh(float[] vertices, int vertexComponentCount, int[] indices) {
        isQuad = true;

        FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertBuffer.put(vertices).flip();

        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbos = new int[2];

        vbos[0] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[0]);
        glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, vertexComponentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        vbos[1] = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbos[1]);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBindVertexArray(0);
    }

    public Mesh(float[] vertices, int vertexComponentCount, float[] colors, int colorComponentCount, byte[] indices) {
        isQuad = true;

        FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertBuffer.put(vertices).flip();

        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorBuffer.put(colors).flip();

        ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbos = new int[3];

        vbos[0] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[0]);
        glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, vertexComponentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        vbos[1] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[1]);
        glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, colorComponentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        vbos[2] = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbos[2]);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBindVertexArray(0);
    }

    public Mesh(float[] vertices, int vertexComponentCount, float[] colors, int colorComponentCount, int[] indices) {
        isQuad = true;

        FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertBuffer.put(vertices).flip();

        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorBuffer.put(colors).flip();

        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbos = new int[3];

        vbos[0] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[0]);
        glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, vertexComponentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        vbos[1] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[1]);
        glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, colorComponentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        vbos[2] = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbos[2]);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBindVertexArray(0);
    }


    public Mesh(float[] vertices, int vertexComponentCount, float[] colors, int colorComponentCount, byte[] indices, float[] uvs) {
        isQuad = true;

        FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertBuffer.put(vertices).flip();

        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorBuffer.put(colors).flip();

        ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbos = new int[4];

        vbos[0] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[0]);
        glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, vertexComponentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        vbos[1] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[1]);
        glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, colorComponentCount, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        FloatBuffer uvBuffer = BufferUtils.createFloatBuffer(uvs.length);
        uvBuffer.put(uvs).flip();

        vbos[2] = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbos[2]);
        glBufferData(GL_ARRAY_BUFFER, uvBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        vbos[3] = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbos[3]);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBindVertexArray(0);
    }


    public void bind() {
        if (isQuad)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbos[1]);
        glBindVertexArray(vao);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

    public void draw(int drawCount) {
        bind();
        if (isQuad)
            glDrawElements(GL_TRIANGLES, drawCount, GL_UNSIGNED_INT, 0);
        else {
            glDrawArrays(GL_TRIANGLES, 0, drawCount);
        }
        unbind();
    }

}
