public class HtmlPrinter implements Printer {
    String printerDescription;
    public void Print(Page page){
        System.out.println("HTML" + page.getPageText());
    }
}
