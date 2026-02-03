package com.aitu.library.model;

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

    // --- BUILDER PATTERN ---
    public static class Builder {
        private String title;
        private String isbn;
        private String author;
        private double fileSizeMB;
        private String fileFormat;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setFileSizeMB(double fileSizeMB) {
            this.fileSizeMB = fileSizeMB;
            return this;
        }

        public Builder setFileFormat(String fileFormat) {
            this.fileFormat = fileFormat;
            return this;
        }

        public EBook build() {
            EBook book = new EBook(title, isbn, author);
            book.setFileSizeMB(fileSizeMB);
            book.setFileFormat(fileFormat);
            return book;
        }
    }
}