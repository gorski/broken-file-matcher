package net.mgorski.brokenfilefinder.dto;

import java.beans.Transient;
import java.io.File;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class FileDto {

    private final DescriptorDto descriptor = new DescriptorDto();
    private String filename;
    private String path;
    private Integer size;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public DescriptorDto getDescriptor() {
        return descriptor;
    }

    @Transient
    public String getFullPath(){
        return this.getPath() + File.separator + this.getFilename();
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "filename='" + filename + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", descriptor=" + descriptor +
                '}';
    }
}
