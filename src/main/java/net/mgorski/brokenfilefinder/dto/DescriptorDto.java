package net.mgorski.brokenfilefinder.dto;

import net.mgorski.brokenfilefinder.Constants;

import java.util.Arrays;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class DescriptorDto {

    private byte[] bytesFromStart = new byte[Constants.PROBE_SIZE];
    private byte[] bytesFromEnd = new byte[Constants.PROBE_SIZE];

    private byte[] bytesFromFFD8 = new byte[Constants.PROBE_SIZE];

    private byte[] bytesFromOffset = new byte[Constants.PROBE_SIZE];
    private byte[] bytesFromEndOffset = new byte[Constants.PROBE_SIZE];

    public byte[] getBytesFromStart() {
        return bytesFromStart;
    }

    public void setBytesFromStart(byte[] bytesFromStart) {
        this.bytesFromStart = bytesFromStart;
    }

    public byte[] getBytesFromFFD8() {
        return bytesFromFFD8;
    }

    public void setBytesFromFFD8(byte[] bytesFromFFD8) {
        this.bytesFromFFD8 = bytesFromFFD8;
    }

    public byte[] getBytesFromEnd() {
        return bytesFromEnd;
    }

    public void setBytesFromEnd(byte[] bytesFromEnd) {
        this.bytesFromEnd = bytesFromEnd;
    }

    public byte[] getBytesFromOffset() {
        return bytesFromOffset;
    }

    public void setBytesFromOffset(byte[] bytesFromOffset) {
        this.bytesFromOffset = bytesFromOffset;
    }

    public byte[] getBytesFromEndOffset() {
        return bytesFromEndOffset;
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
