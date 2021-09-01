import model.UrlRequest;
import service.Downloader;
import service.LinksAggregator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        UrlRequest request = new UrlRequest(args);
        Downloader.download(LinksAggregator.getLinks(request), request.getDownloadPath());
    }
}
