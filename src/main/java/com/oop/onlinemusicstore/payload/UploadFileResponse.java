package com.oop.onlinemusicstore.payload;

public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String artist;
    private String genre;
    private String year;
    private String imageFileDownloadUri;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, String artist, String genre, String year,String imageFileDownloadUri ) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.imageFileDownloadUri = imageFileDownloadUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenere() {
        return genre;
    }

    public void setGenere(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageFileDownloadUri() {
        return imageFileDownloadUri;
    }

    public void setImageFileDownloadUri(String imageFileDownloadUri) {
        this.imageFileDownloadUri = imageFileDownloadUri;
    }

    // Getters and Setters (Omitted for brevity)
}