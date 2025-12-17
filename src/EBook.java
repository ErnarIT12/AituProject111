public class EBook {
        //Attributes
        private String title;
        private String  isbn;
        private String author;
        private boolean available;
        private double fileSizeMB;
        private String fileFormat;
        private boolean isDownloaded;
        //init or construct
        public EBook(String title, String isbn, String author){
            this.author = author;
            this.isbn = isbn;
            this.title = title;
        }
        //Getter
        public String getTitle(){return title;}
        public String getIsbn(){return isbn;}
        public String getAuthor(){return author;}
        public boolean isAvailable(){return available;}
        //Setter
        public void setTitle(String title){this.title = title;}
        public void setIsbn(String isbn){this.isbn = isbn;}
        public void setAuthor(){this.author = author;}


}
