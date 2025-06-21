package Week_1_Design_patterns_and_principles;

class DocumentFactoryExample {

    interface Document {
        void open();
    }

    static class WordDocument implements Document {
        public void open() {
            System.out.println("Opening a Word document.");
        }
    }

    static class PdfDocument implements Document {
        public void open() {
            System.out.println("Opening a PDF document.");
        }
    }

    static class ExcelDocument implements Document {
        public void open() {
            System.out.println("Opening an Excel document.");
        }
    }

    static abstract class DocumentFactory {
        public abstract Document createDocument();
    }

    static class WordDocumentFactory extends DocumentFactory {

        public Document createDocument() {
            return new WordDocument();
        }
    }

    static class PdfDocumentFactory extends DocumentFactory {

        public Document createDocument() {
            return new PdfDocument();
        }
    }

    static class ExcelDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new ExcelDocument();
        }
    }
}

public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        DocumentFactoryExample.DocumentFactory wordFactory = new DocumentFactoryExample.WordDocumentFactory();
        DocumentFactoryExample.Document wordDoc = wordFactory.createDocument();
        wordDoc.open();

        DocumentFactoryExample.DocumentFactory pdfFactory = new DocumentFactoryExample.PdfDocumentFactory();
        DocumentFactoryExample.Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();

        DocumentFactoryExample.DocumentFactory excelFactory = new DocumentFactoryExample.ExcelDocumentFactory();
        DocumentFactoryExample.Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
    }
}
