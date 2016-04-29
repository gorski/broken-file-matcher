package net.mgorski.brokenfilefinder.dto;

import net.mgorski.brokenfilefinder.Constants;

import java.util.Arrays;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class DescriptorDto {

    private byte[] bytesFromStart;
    private byte[] bytesFromEnd;

    private byte[] bytesFromFFD8;

    private byte[] bytesFromOffset;
    private byte[] bytesFromEndOffset;

    public byte[] getBytesFromStart() {
        return bytesFromStart;
    }

    public byte[] getBytesFromEnd() {
        return bytesFromEnd;
    }

    public byte[] getBytesFromFFD8() {
        return bytesFromFFD8;
    }

    public byte[] getBytesFromOffset() {
        return bytesFromOffset;
    }

    public byte[] getBytesFromEndOffset() {
        return bytesFromEndOffset;
    }

    public boolean isBytesFromStart() {
        return bytesFromStart != null;
    }

    public boolean isBytesFromEnd() {
        return bytesFromEnd!= null;
    }

    public boolean isBytesFromFFD8() {
        return bytesFromFFD8!= null;
    }

    public boolean isBytesFromOffset() {
        return bytesFromOffset!= null;
    }

    public boolean isBytesFromEndOffset() {
        return bytesFromEndOffset!= null;
    }

    public void setBytesFromStart(byte[] bytesFromStart) {
        this.bytesFromStart = bytesFromStart;
    }

    public void setBytesFromEnd(byte[] bytesFromEnd) {
        this.bytesFromEnd = bytesFromEnd;
    }

    public void setBytesFromFFD8(byte[] bytesFromFFD8) {
        this.bytesFromFFD8 = bytesFromFFD8;
    }

    public void setBytesFromOffset(byte[] bytesFromOffset) {
        this.bytesFromOffset = bytesFromOffset;
    }

    public void setBytesFromEndOffset(byte[] bytesFromEndOffset) {
        this.bytesFromEndOffset = bytesFromEndOffset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DescriptorDto that = (DescriptorDto) o;

        if (!Arrays.equals(bytesFromStart, that.bytesFromStart)) return false;
        if (!Arrays.equals(bytesFromEnd, that.bytesFromEnd)) return false;
        if (!Arrays.equals(bytesFromFFD8, that.bytesFromFFD8)) return false;
        if (!Arrays.equals(bytesFromOffset, that.bytesFromOffset)) return false;
        return Arrays.equals(bytesFromEndOffset, that.bytesFromEndOffset);

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(bytesFromStart);
        result = 31 * result + Arrays.hashCode(bytesFromEnd);
        result = 31 * result + Arrays.hashCode(bytesFromFFD8);
        result = 31 * result + Arrays.hashCode(bytesFromOffset);
        result = 31 * result + Arrays.hashCode(bytesFromEndOffset);
        return result;
    }

    @Override
    public String toString() {
        return "Descriptor" +
                "\t" + Arrays.toString(bytesFromStart) +
                "\t" + Arrays.toString(bytesFromFFD8) +
                "\t" + Arrays.toString(bytesFromEnd) +
                "\t" + Arrays.toString(bytesFromOffset) +
                '}';
    }
}
