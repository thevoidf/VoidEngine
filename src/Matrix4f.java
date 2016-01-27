import static java.lang.Math.*;

public class Matrix4f {

    public float[] elements = new float[4 * 4];

    public Matrix4f() {
        for (int i = 0; i < 4 * 4; i++) {
            elements[i] = 0;
        }
    }

    public static Matrix4f identity() {
        Matrix4f result = new Matrix4f();

        for (int i = 0; i < 4 * 4; i++) {
            result.elements[i] = 0;
        }

        result.elements[0 + 0 * 4] = 1.0f;
        result.elements[1 + 1 * 4] = 1.0f;
        result.elements[2 + 2 * 4] = 1.0f;
        result.elements[3 + 3 * 4] = 1.0f;

        return result;
    }

    public Matrix4f multiply(Matrix4f matrix) {
        Matrix4f result = new Matrix4f();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                float sum = 0.0f;
                for (int e = 0; e < 4; e++) {
                    sum += this.elements[e + y * 4] * matrix.elements[x + e * 4];
                }
                result.elements[x + y * 4] = sum;
            }
        }

        return result;
    }

    public static Matrix4f translate(Vector3f vector) {
        Matrix4f result = identity();

        result.elements[0 + 3 * 4] = vector.x;
        result.elements[1 + 3 * 4] = vector.y;
        result.elements[2 + 3 * 4] = vector.z;

        return result;
    }

    public static Matrix4f scale(Vector3f vec) {
        Matrix4f result = identity();

        result.elements[0 + 0 * 4] = vec.x;
        result.elements[1 + 1 * 4] = vec.y;
        result.elements[2 + 2 * 4] = vec.z;

        return result;
    }

    public static Matrix4f rotate(float angle, Vector3f vec) {
        Matrix4f result = identity();

        float r = (float) toRadians(angle);
        float c = (float) cos(r);
        float s = (float) sin(r);
        float omc = 1.0f - c;

        float x = vec.x;
        float y = vec.y;
        float z = vec.z;

        result.elements[0 + 0 * 4] = x * omc + c;
        result.elements[1 + 0 * 4] = y * x * omc + z * s;
        result.elements[2 + 0 * 4] = x * z * omc - y * s;

        result.elements[0 + 1 * 4] = x * y * omc - z * s;
        result.elements[1 + 1 * 4] = y * omc + c;
        result.elements[2 + 1 * 4] = y * z * omc + x * s;

        result.elements[0 + 2 * 4] = x * z * omc + y * s;
        result.elements[1 + 2 * 4] = y * z * omc - x * s;
        result.elements[2 + 2 * 4] = z * omc + c;

        return result;
    }

    public static Matrix4f ortho(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f result = identity();

        result.elements[0 + 0 * 4] = 2.0f / (right - left);

        result.elements[1 + 1 * 4] = 2.0f / (top - bottom);

        result.elements[2 + 2 * 4] = 2.0f / (near - far);

        result.elements[0 + 3 * 4] = (left + right) / (left - right);
        result.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
        result.elements[0 + 3 * 4] = (near + far) / (far - near);

        return result;
    }

    public static Matrix4f perspective(float fov, float aspect, float near, float far) {
        Matrix4f result = new Matrix4f();

        float q = (float) (1.0f / tan(toRadians(0.5f * fov)));
        float a = q / aspect;

        float b = (near + far) / (near - far);
        float c = (2.0f * near * far) / (near - far);

        result.elements[0 + 0 * 4] = a;
        result.elements[1 + 1 * 4] = q;
        result.elements[2 + 2 * 4] = b;
        result.elements[3 + 2 * 4] = -1.0f;
        result.elements[2 + 3 * 4] = c;

        return result;
    }
}