import java.util.ArrayList;
import java.util.List;

public class Model {
    public List<Vector2f> vertices = new ArrayList<>();
    public List<Vector2f> normals = new ArrayList<>();
    public List<Face> faces = new ArrayList<>();

    public float[] verticesArray;
    public float[] textureArray;
    public int[] indicesArray;

    public Model() {
    }

    public Model(float[] verticesArray, float[]textureArray, int[]indicesArray) {
        this.verticesArray = verticesArray;
        this.textureArray = textureArray;
        this.indicesArray = indicesArray;
    }

}
