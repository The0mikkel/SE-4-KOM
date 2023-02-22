package dk.sdu.student.mialb21.common.data;

public class Color {
    /**
     * Red
     */
    private float r;
    /**
     * Green
     */
    private float g;
    /**
     * Blue
     */
    private float b;
    /**
     * Alpha
     */
    private float a;

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * Get the value of R
     * 
     * @return float value of R
     */
    public float getR() {
        return r;
    }

    /**
     * Set the value of R
     * 
     * @param r new value of R
     */
    public void setR(float r) {
        this.r = r;
    }

    /**
     * Get the value of G
     * 
     * @return float value of G
     */
    public float getG() {
        return g;
    }

    /**
     * Set the value of G
     * 
     * @param g new value of G
     */
    public void setG(float g) {
        this.g = g;
    }

    /**
     * Get the value of B
     * 
     * @return float value of B
     */
    public float getB() {
        return b;
    }

    /**
     * Set the value of B
     * 
     * @param b new value of B
     */
    public void setB(float b) {
        this.b = b;
    }

    /**
     * Get the value of A
     * 
     * @return float value of A
     */
    public float getA() {
        return a;
    }

    /**
     * Set the value of A
     * 
     * @param a new value of A
     */
    public void setA(float a) {
        this.a = a;
    }
}
