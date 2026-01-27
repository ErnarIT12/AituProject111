package com.aitu.library;

public class EBook extends Item {
    //Attributes
    private double fileSizeMB;
    private String fileFormat;
    private boolean isDownloaded;

    // Пустой конструктор нужен для JSON десериализации (Jackson)
    public EBook() {
        super("", "", "");
    }

    //init or construct
    public EBook(String title, String isbn, String author) {
        super(title, isbn, author);
    }

    // Getter
    public double getFileSizeMB() {
        return fileSizeMB;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public boolean getDownloaded() {
        return isDownloaded;
    }

    //Setter
    public void setFileSizeMB(double fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public void setDownloaded(boolean isDownloaded) {
        this.isDownloaded = isDownloaded;
    }
}