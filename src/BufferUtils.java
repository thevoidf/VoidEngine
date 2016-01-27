import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtils {

    private BufferUtils() {
    }

    public static ByteBuffer createBytetBuffer(byte[] arr) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(arr.length).order(ByteOrder.nativeOrder());
        buffer.put(arr).flip();
        return buffer;
    }

    public static IntBuffer createIntBuffer(int[] arr) {
        IntBuffer buffer = ByteBuffer.allocateDirect(arr.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
        buffer.put(arr).flip();
        return buffer;
    }

    public static DoubleBuffer createDoubleBuffer(double[] arr) {
        DoubleBuffer buffer = ByteBuffer.allocateDirect(arr.length << 2).order(ByteOrder.nativeOrder())
                .asDoubleBuffer();
        buffer.put(arr).flip();
        return buffer;
    }

    public static FloatBuffer createFloatBuffer(float[] arr) {
        FloatBuffer buffer = ByteBuffer.allocateDirect(arr.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(arr).flip();
        return buffer;
    }

}
