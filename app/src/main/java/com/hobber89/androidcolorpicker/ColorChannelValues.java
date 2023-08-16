package com.hobber89.androidcolorpicker;

public class ColorChannelValues {
    private short r;
    private short g;
    private short b;

    public ColorChannelValues() {
        r = 0;
        g = 0;
        b = 0;
    }

    public short getR() {
        return r;
    }

    public void setR(short r) {
        this.r = r;
    }

    public short getG() {
        return g;
    }

    public void setG(short g) {
        this.g = g;
    }

    public short getB() {
        return b;
    }

    public void setB(short b) {
        this.b = b;
    }

    public void setRGB(short r, short g, short b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getColor() {
        int a = 255;
        int color = (b & 0xff) + ((g & 0xff) << 8) + ((r  & 0xff) << 16) + (a << 24);
        return color;
    }
}
